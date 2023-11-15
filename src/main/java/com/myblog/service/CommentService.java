package com.myblog.service;

import com.myblog.payload.CommentDto;
import org.springframework.stereotype.Service;


public interface CommentService {
     CommentDto saveComments(CommentDto dto, int postId);
     void deleteComment(int postId);
     CommentDto  updateComment(CommentDto dto,int postId);

}
