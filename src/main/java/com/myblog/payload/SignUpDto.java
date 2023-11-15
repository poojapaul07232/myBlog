package com.myblog.payload;

import lombok.Data;

@Data  //gives create setter and getters
public class SignUpDto {
    private  String name;
    private  String username;
    private  String email;
    private  String password;


}
