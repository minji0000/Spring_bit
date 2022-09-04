package com.bitcamp.travelkkaebi.mapper;

import com.bitcamp.travelkkaebi.dto.JoinMeListDTO;
import com.bitcamp.travelkkaebi.dto.JoinMeOneDTO;
import com.bitcamp.travelkkaebi.dto.PageAndWordDTO;
import com.bitcamp.travelkkaebi.model.JoinMeDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface JoinMeMapper {
    int getPageCount();
    int getPageCountByKeyword(String keyword);
    List<JoinMeListDTO> selectAllByPage(PageAndWordDTO pageAndWordDTO);
    List<JoinMeListDTO> selectAllByPageAndKeyword(PageAndWordDTO pageAndWordDTO);
    List<JoinMeListDTO> selectAllByPageAndTitle(PageAndWordDTO pageAndWordDTO);
    List<JoinMeListDTO> selectAllByPageAndNickname(PageAndWordDTO pageAndWordDTO);
    Optional<JoinMeOneDTO> selectOne(int joinMeId);
    int insert(JoinMeDTO joinMeDTO);
    int update(JoinMeDTO joinMeDTO);
    int updateView(int joinMeId);
    int updateClosed(JoinMeListDTO joinMeListDTO);
    int delete(JoinMeDTO joinMeDTO);
}