package com.bitcamp.travelkkaebi.mapper;

import com.bitcamp.travelkkaebi.model.ImageDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ImageMapper {
    List<ImageDTO> selectAll(ImageDTO imageDTO);
    ImageDTO selectOne(int imageId);
    int insert(ImageDTO imageDTO);
    int update(ImageDTO imageDTO);
    int delete(ImageDTO imageDTO);
}