package com.evan.bs.pojo;

public class Result {
    private int code;   // 响应码

    public Result(int code){
        this.code = code;
    }

    public int getCode(){
        return this.code;
    }

    public void setCode(int code){
        this.code = code;
    }
}
