package com.myblog.service;

import com.myblog.entity.Comment;
import com.myblog.entity.Post;
import com.myblog.exception.ResourceNotFound;
import com.myblog.payload.CommentDto;
import com.myblog.repository.CommentRepo;
import com.myblog.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
   private CommentRepo cRepo;
    @Autowired
    private PostRepository pRepo;
    @Autowired
    private ModelMapper modelMapper;

    //cone more methos is constructor based
    //public CommentServiceImpl(CommenRepository commentRepo)
    //{
    // this.commentRepo=commentRepo;
    //}
    @Override
    public CommentDto saveComments(CommentDto dto, int postId)
    {
        //the comment is for a particular post and that post is having a postId which identify that particular post
        //thts why we pass postId here as an argument
         Post post = pRepo.findById(postId).orElseThrow(
                 ()-> new ResourceNotFound("post not found with id:"+postId)
         );

//        Comment comment=new Comment();
//        comment.setId(dto.getId());
//        comment.setName(dto.getName());
//        comment.setEmail(dto.getEmail());
//        comment.setBody(dto.getBody());
//
//       comment.setPost(post);

        Comment comment=mapToComment(dto);
        comment.setPost(post);


        Comment saveComment= cRepo.save(comment);

       CommentDto commentDto = new CommentDto();
        commentDto.setId(saveComment.getId());
        commentDto.setName(saveComment.getName());
        commentDto.setEmail(saveComment.getEmail());
        commentDto.setBody(saveComment.getBody());

        return commentDto;
    }

    @Override
    public void deleteComment(int postId) {
        cRepo.deleteById(postId);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, int postId) {
        Comment comment = cRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Comment not found for id:" + postId)
        );
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment savedComment = cRepo.save(comment);

        CommentDto dto = new CommentDto();

        dto.setId(savedComment.getId());
        dto.setName(savedComment.getName());
        dto.setEmail(savedComment.getEmail());
        dto.setBody(savedComment.getBody());
        return dto;
    }

    public Comment mapToComment(CommentDto dto)
     {
      Comment comment=  modelMapper.map(dto,Comment.class);
      //  Comment comment=new Comment();
      //  comment.setId(dto.getId());
      //  comment.setName(dto.getName());
      //  comment.setEmail(dto.getEmail());
         //  comment.setBody(dto.getBody());
        return comment;
     }


  //  write a pro  to  make a game
    // u r having a n array of 3*3 =u will put random nom 0 -99
   // agra wo number hu array ka +5 number erewaredd or else -1 num will be decdecuted


}
