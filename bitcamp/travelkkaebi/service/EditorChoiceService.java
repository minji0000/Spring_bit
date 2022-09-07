package com.bitcamp.travelkkaebi.service;

import com.bitcamp.travelkkaebi.dto.EditorChoiceResponseDTO;
import com.bitcamp.travelkkaebi.entity.UserEntity;
import com.bitcamp.travelkkaebi.entity.UserRole;
import com.bitcamp.travelkkaebi.mapper.EditorChoiceMapper;
import com.bitcamp.travelkkaebi.model.EditorChoiceDTO;
import com.bitcamp.travelkkaebi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EditorChoiceService {

    private final EditorChoiceMapper editorChoiceMapper;
    private final UserRepository userRepository;
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

        UserEntity findUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("없습니다"));

        // 로그인 된 유저가 에디터 인지 확인하는 코드
        if (findUser.getRole().equals(UserRole.EDITOR)) {
            editorChoiceDTO.setUserId(userId);
            return editorChoiceMapper.insert(editorChoiceDTO);

        } else {
            return 0;
        }

        /**
         if(글쓴회원의 role이 에디터면){
         insert
         } else{
         에디터가 아닙니다}
         */

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

        if (userId == editorChoiceDTO.getUserId()) {
            // 리턴 값 바꾸기 **********
            return editorChoiceMapper.update(editorChoiceDTO) * editorChoiceDTO.getEditorChoiceId();
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

        if (userId == editorChoiceDTO.getUserId()) {
            return editorChoiceMapper.delete(editorChoiceDTO.getEditorChoiceId());
        } else {
            return 0;
        }
    }

    /**
     * 게시글 리스트
     * @param pageNo
     * @return list
     * @throws Exception
     */

    public List<EditorChoiceResponseDTO> selectAllByPage(int pageNo) throws Exception {

        HashMap<String, Integer> pageMap = new HashMap<>();
        int startNum = (pageNo - 1) * PAGE_SIZE;

        pageMap.put("startNum", startNum);
        pageMap.put("PAGE_SIZE", PAGE_SIZE);

        return editorChoiceMapper.selectAllByPage(pageMap);
    }

    /**
     * 게시글 리스트 (신규)
     * @return
     * @throws Exception
     */

    public List<EditorChoiceResponseDTO> selectAllNew() throws Exception {
        return editorChoiceMapper.selectAllNew();
    }


    /**
     * 게시글 상세보기
     * @param editorChoiceId
     * @return editorChoiceResponseDTO
     */
    public EditorChoiceResponseDTO selectOne(int editorChoiceId) {
        // 조회수 더해주는 코드
        editorChoiceMapper.viewPlus(editorChoiceId);
        return editorChoiceMapper.selectOne(editorChoiceId);
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

        if (total % PAGE_SIZE == 0) {
            return total / PAGE_SIZE;
        } else {
            return (total / PAGE_SIZE) + 1;
        }
    }

    /**
     * 특정 제목으로 검색
     * @param title
     * @return titleList
     * @throws Exception
     */

    public List<EditorChoiceResponseDTO> searchByTitle(String title) throws Exception {

        if (title != null) {
            return editorChoiceMapper.searchByTitle(title);
        } else {
            return null;
        }
    }

    /**
     * 특정 내용으로 검색
     * @param content
     * @return contentList
     * @throws Exception
     */

    public List<EditorChoiceResponseDTO> searchByContent(String content) throws Exception {

        if (content != null) {
            return editorChoiceMapper.searchByContent(content);
        } else {
            return null;
        }
    }

    /**
     * 특정 작성자로 검색
     * @param writer
     * @return writerList
     * @throws Exception
     */

    public List<EditorChoiceResponseDTO> searchByWriter(String writer) throws Exception {

        if (writer != null) {
            return editorChoiceMapper.searchByWriter(writer);
        } else {
            return null;
        }
    }


    /**
     * (지역) 키워드로 검색
     * @param region
     * @return
     * @throws Exception
     */

    public List<EditorChoiceResponseDTO> keywordByRegion(String region) throws Exception {

        if (region != null) {
            return editorChoiceMapper.keywordByRegion(region);
        } else {
            return null;
        }
    }
}
