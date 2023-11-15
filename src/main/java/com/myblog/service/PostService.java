package com.myblog.service;

import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;

import java.util.List;

public interface PostService
{
    PostDto createPost(PostDto postDto);
    public void deleteById(int id);

    public PostDto getPostByPostId(int id);


   // List<PostDto> getAllPost(int pageNo, int pageSize, String sort, String sortDir);

    PostResponse getAllPost(int pageNo, int pageSize, String sort, String sortDir);

}
