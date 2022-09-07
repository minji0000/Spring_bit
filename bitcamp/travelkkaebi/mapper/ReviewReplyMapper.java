package com.bitcamp.travelkkaebi.mapper;

import com.bitcamp.travelkkaebi.dto.ReviewReplyResponseDTO;
import com.bitcamp.travelkkaebi.model.ReviewReplyDTO;
import org.apache.ibatis.annotations.Mapper;
import com.bitcamp.travelkkaebi.model.ReviewDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewReplyMapper {

    int insert( ReviewReplyDTO replyDTO);

    int update(ReviewReplyDTO replyDTO);

    int delete( int replyId);

    int deleteByBoardId(int boardId);

    List<ReviewReplyResponseDTO> selectAllByBoardId(int boardId);


















}
