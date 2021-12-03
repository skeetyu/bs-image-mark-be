package com.evan.bs.result;

public class ResultMark {
    private int code;
    private String path;
    private int state;
    private String notation;
    private String tag;
    private String description;

    public ResultMark() { }
    public ResultMark(int code, String path, int state, String notation, String tag, String description){
        this.code = code;
        this.path = path;
        this.state = state;
        this.notation = notation;
        this.tag = tag;
        this.description = description;
    }

    public int getCode(){
        return this.code;
    }

    public void setCode(int code){
        this.code = code;
    }

    public String getPath(){
        return this.path;
    }

    public void setPath(String path){
        this.path = path;
    }

    public int getState(){
        return this.state;
    }

    public void setState(int state){
        this.state = state;
    }

    public String getNotation(){
        return this.notation;
    }

    public void setNotation(String notation){
        this.notation = notation;
    }

    public String getTag(){
        return this.tag;
    }

    public void setTag(String tag){
        this.tag = tag;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
