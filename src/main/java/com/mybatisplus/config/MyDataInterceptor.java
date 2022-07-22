package com.mybatisplus.config;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.mybatisplus.enums.StatusEnum;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;

/**
 * @author chenbiao
 * @Intercepts :mybatis的注解标识该类为拦截类
 * : 值为@Signature 注解。注解的值为数组，
 * @Data 2022/6/20 10:44 AM
 */
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class MyDataInterceptor implements Interceptor {


    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement statement = (MappedStatement) args[0];

        System.out.println("进入拦截");

        Method method = invocation.getMethod();
        System.out.println("方法为" + method.getName());

        String id = statement.getId();
        String substring = id.substring(0, id.lastIndexOf("."));
        String substring1 = id.substring(id.lastIndexOf(".") + 1);

        Class<?> aClass = Class.forName(substring);
        boolean flag = false;
        Method[] declaredMethods = aClass.getDeclaredMethods();
        String myAnnotation = null;
        for (Method declaredMethod : declaredMethods) {
            Annotation[] declaredAnnotations = declaredMethod.getDeclaredAnnotations();

            for (Annotation declaredAnnotation : declaredAnnotations) {
                System.out.println(declaredAnnotation.annotationType().getTypeName());
                //TODO 可以做到数据库配置。也可以写枚举类
                if ("com.mybatisplus.config.MyAnnotation".equals(declaredAnnotation.annotationType().getTypeName())) {
                    MyAnnotation annotation = declaredMethod.getAnnotation(MyAnnotation.class);
                    StatusEnum[] statusEnums = annotation.value();
                    for (StatusEnum statusEnum : statusEnums) {
                        System.out.println(statusEnum.getAce());
                    }

                    flag = true;
                }
                System.out.println("注解为" + declaredAnnotation.annotationType().getSimpleName());
                myAnnotation = declaredAnnotation.annotationType().getSimpleName();
            }
        }

        Annotation[] annotations = method.getDeclaredAnnotations();

        //MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);


        ParameterMap parameterMap = statement.getParameterMap();

        SqlSource sqlSource = statement.getSqlSource();


        BoundSql boundSql = statement.getBoundSql(args[1]);
        String sql = boundSql.getSql();
        if (flag) {
            sql = sql + " and 1=0";
        } else {
            return invocation.proceed();
        }


        BoundSql boundSqlnew = new BoundSql(statement.getConfiguration(), sql, boundSql.getParameterMappings(), boundSql.getParameterObject());

        BoundSqlSqlSource source = new BoundSqlSqlSource(boundSqlnew);

        MappedStatement statement1 = newMappedStatement(statement, source);

        Object[] queryArgs = invocation.getArgs();
        queryArgs[0] = statement1;


        Object target = invocation.getTarget();
        return invocation.proceed();
    }


    /**
     * 定义一个内部辅助类，作用是包装 SQL
     */
    static class BoundSqlSqlSource implements SqlSource {

        private final BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        @Override
        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }

    }


    private static MappedStatement newMappedStatement(MappedStatement ms, SqlSource newSqlSource) {


        /**
         * Configuration configuration, String id, SqlSource sqlSource, SqlCommandType sqlCommandType
         */
        MappedStatement.Builder mst = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());

        mst.resource(ms.getResource());
        mst.fetchSize(ms.getFetchSize());
        mst.statementType(ms.getStatementType());
        mst.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length > 0) {
            mst.keyProperty(ms.getKeyProperties()[0]);
        }
        mst.timeout(ms.getTimeout());
        mst.parameterMap(ms.getParameterMap());
        mst.resultMaps(ms.getResultMaps());
        mst.resultSetType(ms.getResultSetType());
        mst.cache(ms.getCache());
        mst.flushCacheRequired(ms.isFlushCacheRequired());
        mst.useCache(ms.isUseCache());

        return mst.build();
    }


    //DbType.MYSQL
}
