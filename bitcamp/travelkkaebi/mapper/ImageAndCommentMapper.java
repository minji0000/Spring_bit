package com.bitcamp.travelkkaebi.mapper;

import com.bitcamp.travelkkaebi.model.ImageAndCommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImageAndCommentMapper {

    int insert(ImageAndCommentDTO imageAndCommentDTO);

    int update(ImageAndCommentDTO imageAndCommentDTO);

    int delete(int imageId);

    List<ImageAndCommentDTO> selectAll(ImageAndCommentDTO imageAndCommentDTO);

    void test(ImageAndCommentDTO imageAndCommentDTO);
}
