package com.syafrizal.kopinow.models;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String key;
    private String name;
    private String username;
    private String password;
    private String role;
    private String telephone;
    private String email;

    public User() {

    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.key);
        dest.writeString(this.name);
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeString(this.role);
        dest.writeString(this.telephone);
        dest.writeString(this.email);
    }

    protected User(Parcel in) {
        this.key = in.readString();
        this.name = in.readString();
        this.username = in.readString();
        this.password = in.readString();
        this.role = in.readString();
        this.telephone = in.readString();
        this.email = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
