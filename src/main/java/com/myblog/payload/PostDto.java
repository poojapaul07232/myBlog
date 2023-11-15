package com.myblog.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private  int id;

    @NotEmpty
    @Size(min=2 , message="Post  titile should have atlest 2 charcater")
    private String  title;

    @NotEmpty
    @Size(min=2 , message="Post  description should have atleast 2 charcater")
    private String description;

    @NotEmpty
    @Size(min=2 , message="Post  content should have atlest 2 charcater")
    private String content;
}
