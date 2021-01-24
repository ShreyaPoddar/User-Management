package com.paytm.inpg.entity;



public class AuthResponse{
//    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwt;

    public AuthResponse(String jwt)
    {
        this.jwt=jwt;
    }
    public String getJwt()
    {
        return jwt;
    }
}
