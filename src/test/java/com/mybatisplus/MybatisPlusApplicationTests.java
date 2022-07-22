package com.mybatisplus;

import com.mybatisplus.Service.UserService;
import com.mybatisplus.entity.User;
import com.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;

@SpringBootTest
class MybatisPlusApplicationTests {

	@Resource
	private UserMapper userMapper;

	@Autowired
	private UserService userService;

	@Test
	void contextLoads() {

	}

	@Test
	void test1(){
		/**
		 * SELECT id,name,age,email FROM user WHERE id=?
		 */
		System.out.println(userMapper.selectById(1));
		/**
		 *  SELECT id,name,age,email FROM user
		 */
		userMapper.selectList(null).forEach(System.out::println);
	}

	/**
	 * 新增
	 */
	@Test
	void test2(){
		User user = new User();
		user.setName("CC");
		user.setAge(23);
		user.setEmail("test@qq.com");

		System.out.println(userMapper.insert(user));
		System.out.println(user.getId());
	}

	/**
	 * 删除
	 */
	@Test
	void test3(){
		System.out.println(userMapper.deleteById(1538117853524316162L));
		HashMap<String, Object> map = new HashMap<>();
		map.put("name","张三");
		map.put("age",23);
		userMapper.deleteByMap(map);
		List<Long> longs = Arrays.asList(1L, 2L, 3L);
		userMapper.deleteBatchIds(longs);
	}

	/**
	 * 修改
	 */
	@Test
	void test4(){
		User user = new User();
		user.setId(4L);
		user.setName("AA");
		userMapper.updateById(user);
	}

	/**
	 * 查询
	 */
	@Test
	void test5(){
		System.out.println(userMapper.selectById(4));
		List<Long> longs = Arrays.asList(4L, 5L);
		System.out.println(userMapper.selectBatchIds(longs));
		HashMap<String, Object> map = new HashMap<>();
		map.put("name","AA");
		map.put("age",23);
		System.out.println(userMapper.selectByMap(map));
		System.out.println(userMapper.selectList(null));

	}

	@Test
	void test6(){
		Map<String, ?> stringMap = userMapper.selectMapById(4L);
		System.out.println(stringMap);
	}

	@Test
	void  test7(){
		System.out.println(userService.count());
		//userService.c

		List<User> list=new ArrayList<>();
		for (int i = 0; i <50 ; i++) {
			User user=new User();
			user.setName("AA"+i);
			user.setAge(i);
			user.setEmail("test@qq.com");
			list.add(user);
		}
		userService.saveBatch(list);
	}
}
