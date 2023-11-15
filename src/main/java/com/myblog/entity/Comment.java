package com.myblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.NavigableMap;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   private String name;
   private String email;
   private  String body;

   @ManyToOne(fetch = FetchType.LAZY)      //many here is comment and one is post
   @JoinColumn(name = "post_id")
   private Post post;

}
