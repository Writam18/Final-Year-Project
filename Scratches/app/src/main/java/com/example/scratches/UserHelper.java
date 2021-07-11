package com.example.scratches;

public class UserHelper {

    String name,username,email,phnno,password;

    public UserHelper( ) {
    }

    public UserHelper(String name, String username, String email, String phnno, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phnno = phnno;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhnno() {
        return phnno;
    }

    public void setPhnno(String phnno) {
        this.phnno = phnno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

  //  UserHelper userHelper= snapshot.getValue(UserHelper.class);
}
