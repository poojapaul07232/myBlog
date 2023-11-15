package com.myblog.controller;

import com.myblog.payload.CommentDto;
import com.myblog.repository.CommentRepo;
import com.myblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {
//http://localhost:8080/api/comments/{postId}

  @Autowired
  private CommentService cService;
    @PostMapping("{postId}")
    public ResponseEntity<CommentDto> saveComment(@RequestBody CommentDto cdto, @PathVariable int  postId)
    {
            CommentDto c=cService.saveComments(cdto,postId);

    return  new ResponseEntity<>(c, HttpStatus.CREATED);
    }

    @DeleteMapping("{postId}")
     public  ResponseEntity<String> deleteComment(@PathVariable int postId){
      cService.deleteComment(postId);
      return new ResponseEntity<>("comment is delted",HttpStatus.OK);

    }
  @PutMapping("{postId}")
  public  ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto cdto,@PathVariable int postId){
   CommentDto commentDto= cService.updateComment(cdto,postId);
    return new ResponseEntity<>(commentDto,HttpStatus.OK);

  }
}
