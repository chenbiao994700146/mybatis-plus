package com.mybatisplus.enums;

/**
 * @author chenbiao
 * @Data 2022/6/20 5:07 PM
 */
public enum StatusEnum {
    SUCCESS(1,"success"),
    ERROR(2,"error");

    private int id;
    private String Status;


    StatusEnum(int aceId, String ace) {
        this.aceId = aceId;
        this.ace = ace;
    }

    private int aceId;

    private String ace;


    public int getAceId() {
        return aceId;
    }

    public void setAceId(int aceId) {
        this.aceId = aceId;
    }

    public String getAce() {
        return ace;
    }

    public void setAce(String ace) {
        this.ace = ace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
