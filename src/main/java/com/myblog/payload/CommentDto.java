package com.myblog.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CommentDto {
    private int id;
    private String name;
    private String email;
    private  String body;



}
