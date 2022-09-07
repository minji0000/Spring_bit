package com.bitcamp.travelkkaebi.mapper;

import com.bitcamp.travelkkaebi.dto.EditorChoiceResponseDTO;
import com.bitcamp.travelkkaebi.model.EditorChoiceDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface EditorChoiceMapper {

    List<EditorChoiceResponseDTO> selectAllByPage(HashMap<String, Integer> pageMap);

    EditorChoiceResponseDTO selectOne (int editorChoiceId);

    String selectRole (int editorChoiceId);

    int insert (EditorChoiceDTO editorChoiceDTO);

    int update(EditorChoiceDTO editorChoiceDTO);

    int delete(int editorChoiceId);

    int viewPlus(int editorChoiceId);

    int editorChoiceCount();

    List<EditorChoiceResponseDTO> searchByTitle(String title);

    List<EditorChoiceResponseDTO> searchByContent(String content);

    List<EditorChoiceResponseDTO> searchByWriter(String writer);

    List<EditorChoiceResponseDTO> keywordByRegion(String region);

    List<EditorChoiceResponseDTO> selectAllNew();
}
