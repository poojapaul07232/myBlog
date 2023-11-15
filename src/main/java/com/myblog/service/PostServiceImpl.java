package com.myblog.service;

import com.myblog.entity.Post;
import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;
import com.myblog.repository.PostRepository;
import com.myblog.exception.ResourceNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
  @Autowired
   private PostRepository postRepository;
 @Autowired
  private ModelMapper modelMapper;


    //public PostServiceImpl(PostRepository postRepository)
   // {
   //     this.postRepository = postRepository;
  //  }
    @Override
    public PostDto createPost(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());

        Post updatePost = postRepository.save(post);

        PostDto dto = new PostDto();
        dto.setId(updatePost.getId());
        dto.setTitle(updatePost.getTitle());
        dto.setContent(updatePost.getContent());
        dto.setDescription(updatePost.getDescription());

        return dto;
    }

    @Override
    public void deleteById(int postId) {
       // postRepository.deleteById(postId)\

        postRepository.findById(postId).orElseThrow(
                //()->  ResourceNotFound("post")
                ()->new ResourceNotFound("Post not found with id" + postId)
        );

        postRepository.deleteById(postId);

    }

    @Override
    public PostDto getPostByPostId(int id) {
       Post post= postRepository.findById(id).orElseThrow(
               ()->new ResourceNotFound("Post not found with id" + id)
       );
          return  mapToDto(post);
    }

//    @Override
//    public List<PostDto> getAllPost(int pageNo, int pageSize, String sortby, String sortDir )
//    {
//       Sort sort= sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?Sort.by(sortby).ascending() : Sort.by(sortby).descending();
//        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);//Sort.by=this will covert the string into sort object
//        Page<Post> all =postRepository.findAll(pageable);
//        List<Post> posts=all.getContent();
//
//        List<PostDto> p= posts.stream().map(post->mapToDto(post)).collect(Collectors.toList());
//        return p;
//    }

    @Override
    public PostResponse getAllPost(int pageNo, int pageSize, String sortby, String sortDir )
    {

        Sort sort= sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?Sort.by(sortby).ascending() : Sort.by(sortby).descending();
        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);//Sort.by=this will covert the string into sort object
        Page<Post> all =postRepository.findAll(pageable);
        List<Post> posts=all.getContent();

        List<PostDto> p= posts.stream().map(post->mapToDto(post)).collect(Collectors.toList());
        PostResponse pr= new PostResponse();
        pr.setContent(p);
        pr.setPageNo(all.getNumber());

        pr.setTotalPages(all.getTotalPages());
        pr.setPageSize(all.getSize());
        pr.setTotalElement(all.getTotalElements());
        pr.setLast(all.isLast());


        return pr;
    }

    public PostDto mapToDto(Post post) {
        PostDto pdto=modelMapper.map(post,PostDto.class);
//        PostDto pdto = new PostDto();
//        pdto.setId(post.getId());
//        pdto.setContent(post.getContent());
//        pdto.setDescription(post.getDescription());
//        pdto.setTitle(post.getTitle());
        return pdto;
     }
    }
