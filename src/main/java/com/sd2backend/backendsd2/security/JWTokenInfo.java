package com.sd2backend.backendsd2.security;

public class JWTokenInfo {
  public static final String KEY = "tokenInfo";

  private String id;
  private String name;
  private String userType;


  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


}
