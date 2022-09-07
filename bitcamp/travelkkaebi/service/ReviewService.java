package com.bitcamp.travelkkaebi.service;

import com.bitcamp.travelkkaebi.dto.ReviewResponseDTO;
import com.bitcamp.travelkkaebi.mapper.ReviewMapper;
import com.bitcamp.travelkkaebi.model.LikeOrDislikeDTO;
import com.bitcamp.travelkkaebi.model.ReviewDTO;
import com.bitcamp.travelkkaebi.mapper.ReviewReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewMapper reviewMapper;

    private final int PAGE_SIZE = 10;

    /**
     * 게시글 등록
     * @param reviewDTO
     * @param userId
     * @return writtenReviewId
     */
    public int writeReview(ReviewDTO reviewDTO, int userId) {

        try {
            reviewDTO.setUserId(userId);
            return reviewMapper.insert(reviewDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 게시글 수정
     * @param review
     * @param userId
     * @return updatedReviewId
     */
    @Transactional
    public int update(ReviewDTO review, int userId) {

        if (userId == review.getUserId()) {
            try {
                return reviewMapper.update(review);
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        } else {
            return 0;
        }
        // 성공했을 경우 updatedReviewid 리턴, 실패 시 0 리턴
    }

    /**
     * 게시글 삭제
     * @param review
     * @param userId
     * @return deletedReviewId;
     */
    @Transactional
    public int delete(ReviewDTO review, int userId) {
        // 로그인 한 아이디와 게시글의 작성자 아이디를 확인!
        if (review.getUserId() == userId) {
            try {
                return reviewMapper.delete(review.getReviewId());
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        } else {
            System.out.println("작성자와 다름");
            return -1;
        }
    }

    /**
     * 게시글 리스트 출력
     */
    public List<ReviewResponseDTO> selectAllByPage(int pageNo) {

        try {
            HashMap<String, Integer> pageMap = new HashMap<>();
            int startNum = (pageNo - 1) * PAGE_SIZE;

            pageMap.put("startNum", startNum);
            pageMap.put("PAGE_SIZE", PAGE_SIZE);

            return reviewMapper.selectAllByPage(pageMap);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 게시글 상세보기
     * @param reviewId
     * @return review
     */

    public ReviewResponseDTO selectOne(int reviewId) {
        // 조회수 +1 시켜주는 코드
        reviewMapper.viewPlus(reviewId);
        return reviewMapper.selectOne(reviewId);
    }

    /**
     * 전체 게시글 갯수 리턴
     */
    public int count() throws Exception {
        return reviewMapper.reviewCount();
    }

    /**
     * selectAll (전체보기) 할 때 페이지 수 리턴
     */
    public int pageCount() throws Exception {
        int total = count();

        if(total % PAGE_SIZE == 0) {
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
    public List<ReviewResponseDTO> searchByTitle(String title) throws Exception {
        if (title != null) {
            return reviewMapper.searchByTitle(title);
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
    public List<ReviewResponseDTO> searchByContent(String content) throws Exception {

        if (content != null) {
            return reviewMapper.searchByContent(content);
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

    public List<ReviewResponseDTO> searchByWriter(String writer) throws Exception {

        if(writer != null) {
            return reviewMapper.searchByWriter(writer);
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

    public List<ReviewResponseDTO> keywordByRegion(String region) throws Exception {

        if(region != null) {
            return reviewMapper.keywordByRegion(region);
        } else {
            return null;
        }
    }
}