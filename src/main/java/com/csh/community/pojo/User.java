package com.csh.community.pojo;

public class User {

    private  Integer id ;
    private  String name;
    private  String account_Id;
    private  String token;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", account_Id='" + account_Id + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount_Id() {
        return account_Id;
    }

    public void setAccount_Id(String account_Id) {
        this.account_Id = account_Id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



}
