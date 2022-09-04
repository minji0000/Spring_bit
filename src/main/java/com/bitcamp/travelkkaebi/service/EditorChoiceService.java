package com.bitcamp.travelkkaebi.service;

import com.bitcamp.travelkkaebi.dto.EditorChoiceResponseDTO;
import com.bitcamp.travelkkaebi.mapper.EditorChoiceMapper;
import com.bitcamp.travelkkaebi.model.EditorChoiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EditorChoiceService {

    private final EditorChoiceMapper editorChoiceMapper;

    private final int PAGE_SIZE = 10;

    /**
     * 게시글 작성
     * @param editorChoiceDTO
     * @param userId
     * @return writtenId
     * @throws Exception
     */

    public int write(EditorChoiceDTO editorChoiceDTO, int userId) throws Exception {
        System.out.println("게시글 작성 서비스");

        int writtenId;

        // 로그인 된 유저가 에디터 인지 확인하는 코드
        String role = editorChoiceMapper.selectRole(editorChoiceDTO.getEditorChoiceId());

        if(role.equals("EDITOR")) {

            if(editorChoiceDTO != null ) {
                editorChoiceDTO.setUserId(userId);
                writtenId = editorChoiceMapper.insert(editorChoiceDTO);
            } else {
                return 0;
            }

        } else {
            return 0;
        }

        return writtenId;
    }

    /**
     * 게시글 수정
     * @param editorChoiceDTO
     * @param userId
     * @return updatedId
     * @throws Exception
     */

    @Transactional
    public int update(EditorChoiceDTO editorChoiceDTO, int userId) throws Exception {
        int updatedId = 0;

        if (userId == editorChoiceDTO.getUserId()) {
            return updatedId = editorChoiceMapper.update(editorChoiceDTO);
        } else {
            return 0;
        }
    }

    /**
     * 게시글 삭제
     * @param editorChoiceDTO
     * @param userId
     * @return deletedId
     * @throws Exception
     */

    @Transactional
    public int delete(EditorChoiceDTO editorChoiceDTO, int userId) throws Exception {
        int deletedId;

        if(userId == editorChoiceDTO.getUserId()) {
            return deletedId = editorChoiceMapper.delete(editorChoiceDTO.getEditorChoiceId());
        } else {
            return 0;
        }
    }

    /**
     * 게시글 리스트 출력
     * @param pageNo
     * @return list
     * @throws Exception
     */

    public List<EditorChoiceResponseDTO> selectAllByPage(int pageNo) throws Exception {
        List<EditorChoiceResponseDTO> list;

        HashMap<String, Integer> pageMap = new HashMap<>();
        int startNum = (pageNo -1) * PAGE_SIZE;

        pageMap.put("startNum", startNum);
        pageMap.put("PAGE_SIZE", PAGE_SIZE);

        return list = editorChoiceMapper.selectAllByPage(pageMap);
    }

    /**
     * 게시글 상세보기
     * @param editorChoiceId
     * @return editorChoiceResponseDTO
     */
    public EditorChoiceResponseDTO selectOne(int editorChoiceId) {
        editorChoiceMapper.viewPlus(editorChoiceId);
        EditorChoiceResponseDTO editorChoiceResponseDTO = editorChoiceMapper.selectOne(editorChoiceId);
        return editorChoiceResponseDTO;
    }

    /**
     * 전체 게시글 갯수 리턴
     */
    public int count() throws Exception {
        return editorChoiceMapper.editorChoiceCount();
    }

    /**
     * selectAll 할 때 페이지 수 리턴
     */
    public int pageCount() throws Exception {
        int total = count();

        if(total % PAGE_SIZE == 0) {
            return total / PAGE_SIZE;
        } else {
            return (total / PAGE_SIZE ) + 1;
        }
    }

    /**
     * 특정 제목으로 검색
     * @param title
     * @return titleList
     * @throws Exception
     */

    public List<EditorChoiceResponseDTO> searchByTitle(String title) throws Exception {

        List<EditorChoiceResponseDTO> titleList;

        if(title != null) {
            titleList = editorChoiceMapper.searchByTitle(title);
        } else {
            return null;
        }

        return titleList;
    }

    /**
     * 특정 내용으로 검색
     * @param content
     * @return contentList
     * @throws Exception
     */

    public List<EditorChoiceResponseDTO> searchByContent(String content) throws Exception {

        List<EditorChoiceResponseDTO> contentList;

        if(content != null) {
            contentList = editorChoiceMapper.searchByContent(content);
        } else {
            return null;
        }

        return contentList;
    }

    /**
     * 특정 작성자로 검색
     * @param writer
     * @return writerList
     * @throws Exception
     */

    public List<EditorChoiceResponseDTO> searchByWriter(String writer) throws Exception {

        List<EditorChoiceResponseDTO> writerList;

        if(writer != null) {
            writerList = editorChoiceMapper.searchByWriter(writer);
        } else {
            return null;
        }

        return writerList;
    }

    /**
     * (지역) 키워드로 검색
     * @param region
     * @return
     * @throws Exception
     */

    public List<EditorChoiceResponseDTO> keywordByRegion(String region) throws Exception {

        List<EditorChoiceResponseDTO> regionList;

        if(region != null) {
            regionList = editorChoiceMapper.keywordByRegion(region);
        } else {
            return null;
        }

        return regionList;
    }













































}
