package com.myblog.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
 //getter and setter

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private List<PostDto> content;
    private  int pageNo;
    private int pageSize;
    private int totalPages;
    private long totalElement;
    private boolean last;
}
