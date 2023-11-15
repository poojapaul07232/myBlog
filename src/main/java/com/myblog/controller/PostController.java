package com.myblog.controller;

import com.myblog.entity.Post;
import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;
import com.myblog.service.PostService;
import net.bytebuddy.description.annotation.AnnotationValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
//http://localhost:8080/api
@RestController
@RequestMapping("/api/post") //This annotation maps HTTP requests with the specified URL path to the annotated method.
public class PostController
{
  @Autowired //automatically wire beans into the application
            // ,The @Autowired annotation automatically injects the dependent beans into the associated variables.
  private PostService postService;
   @PostMapping
    //public ResponseEntity<?> createPost(@Valid@RequestBody PostDto postDto, BindingResult result)-----------[?==this became general when return type is dynamic and u r not sure off]
   public ResponseEntity<Object> createPost(@Valid@RequestBody PostDto postDto, BindingResult result)

   {
       if(result.hasErrors()){
           return  new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
      PostDto dto = postService.createPost(postDto);
      return  new ResponseEntity<>(dto, HttpStatus.CREATED);
    }



    //http://localhost:8080/api/post/{postId}  ( http://localhost:8080/api/post/1 )
    @DeleteMapping("{postId}")
    public ResponseEntity<String> deleteById(@PathVariable int postId){

       postService.deleteById(postId);
        return new ResponseEntity<>("Post is deleted successfully", HttpStatus.OK);
    }

    @GetMapping("{postId}")
    public ResponseEntity<PostDto> getPostByPostId(@PathVariable int postId){

        PostDto pdto = postService.getPostByPostId(postId);
        return new ResponseEntity<>(pdto, HttpStatus.OK);
    }
//   -------------------------------------------PAGINATION----------------------------------------------------------------------------
    //http://localhost:8080/api/post?pageNo=0&pageSize=3&sortBy=content&sortDir=
//    @GetMapping()
//    public List<PostDto> getAllPost(
//                                  @RequestParam(value = "pageNo",defaultValue = "0" , required = false) int pageNo,
//                                  @RequestParam(value = "pageSize", defaultValue = "10" , required = false)int pageSize,
//                                  @RequestParam(value="sortBy", defaultValue="id", required = false) String sortBy,
//                                  @RequestParam(value="sortDir", defaultValue="asc", required = false) String sortDirection
//
//                             )
//      {
//        //  Sort sort= Sort.by(sortBy);
//          List<PostDto> p =  postService.getAllPost(pageNo,pageSize, sortBy,sortDirection);
//         return p;
//
//      }
//   -------------------------------------------PAGINATION-(postResponse)---------------------------------------------------------------------------

    @GetMapping()
    public PostResponse getAllPost(
            @RequestParam(value = "pageNo",defaultValue = "0" , required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10" , required = false)int pageSize,
            @RequestParam(value="sortBy", defaultValue="id", required = false) String sortBy,
            @RequestParam(value="sortDir", defaultValue="asc", required = false) String sortDirection

            )
       {
        //  Sort sort= Sort.by(sortBy);
           PostResponse p =  postService.getAllPost(pageNo,pageSize, sortBy,sortDirection);
          return p;

       }






}
