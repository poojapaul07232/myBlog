package com.myblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

///@Table(name="posts")
//@Table(
//        name="posts",
//        uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
//)
//lombok is a java library ,it minimize the boilerplate code (predefined code)

@Data  //this will create a getter  all getter n setters
@NoArgsConstructor //gives the constructor
@AllArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private  int id;

    private String  title;

    private String description;

    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)//one=POst, many=comment class
  //  private Set<Comment> comments =new HashSet<>();
         List<Comment> comments= new ArrayList<>();


}
