package com.evan.bs.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    int uid;
    
    String username;
    String password;
    String email;
    String salt;
    String phone;
    String profile;
    String address;

    public User(){
        
    }

    public User(String username, String password, String email){
        // this.uid = 0;
        this.username = username;
        this.password = password;
        this.email = email;
        this.salt = "";
    }

    public User(int uid, String username, String password, String email, String salt,
                String phone, String profile, String address){
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.salt = salt;
        this.phone = phone;
        this.profile = profile;
        this.address = address;
    }

    public int getUid(){
        return this.uid;
    }

    public void setUid(int uid){
        this.uid = uid;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getSalt(){
        return this.salt;
    }

    public void setSalt(String salt){
        this.salt = salt;
    }

    public String getPhone(){
        return this.phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getProfile(){
        return this.profile;
    }

    public void setProfile(String profile){
        this.profile = profile;
    }

    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address){
        this.address = address;
    }
}
