package com.evan.bs.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Password {
    String oldpassword;
    String newpassword;

    Password(){ }
    Password(String oldpassword, String newpassword){
        this.oldpassword = oldpassword;
        this.newpassword = newpassword;
    }

    public String getOldpassword(){
        return this.oldpassword;
    }

    public void setOldpassword(String oldpassword){
        this.oldpassword = oldpassword;
    }

    public String getNewpassword(){
        return this.newpassword;
    }

    public void setNewpassword(String newpassword){
        this.newpassword = newpassword;
    }
}
