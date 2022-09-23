package model;

import Enums.User_Type;

public class User {

    String name;
    String user_name;
    String password;
    User_Type type;

    public User() {
    }

    public User(String name, String user_name, String password, User_Type type) {
        this.name = name;
        this.user_name = user_name;
        this.password = password;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User_Type getType() {
        return type;
    }

    public void setType(User_Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" + "Name=" + name + ", user_name=" + user_name + ", password=" + password + ", Category=" + type + '}';
    }

    public User get_User(String user_name) {
        return null;
    }

}
