package com.bitcamp.travelkkaebi.service;

import com.bitcamp.travelkkaebi.model.ReviewDTO;
import com.bitcamp.travelkkaebi.model.ReviewReplyDTO;
import com.bitcamp.travelkkaebi.repository.ReviewReplyRepository;
import com.bitcamp.travelkkaebi.repository.ReviewRepository;
import com.bitcamp.travelkkaebi.model.LikeOrDislikeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewReplyRepository reviewReplyRepository;
    private final LikeOrDislikeService likeOrDislikeService;
    private final ReviewReplyService reviewReplyService;


    // 후기 게시판에 글 등록하는 메소드
    public int writeReview(ReviewDTO review, @AuthenticationPrincipal int userId) {
        System.out.println("서비스");
        LikeOrDislikeDTO likeOrDislikeDTO = new LikeOrDislikeDTO();
        int writtenReviewId;
        int likeOrDislikeId;

        try {
            review.setCategoryId(review.getCategoryId());
            // 마의 구간,,,

            review.setWriterId(userId);
            review.setTitle(review.getTitle());
            review.setContent(review.getContent());
            review.setRegion(review.getRegion());


            // 좋아요 상태 추가
            likeOrDislikeDTO.setCategoryId(review.getCategoryId());
            likeOrDislikeDTO.setBoardId(review.getReviewId());

            likeOrDislikeService.createLikeOrDislike(likeOrDislikeDTO);

            // 게시글 등록 성공 !
            writtenReviewId = reviewRepository.insert(review);

        } catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
            return writtenReviewId;
    }

    // 후기글 수정하는 메소드
    public int update(ReviewDTO review) {
        int updatedReviewId;

        try {
            review.setTitle(review.getTitle());
            review.setContent(review.getContent());
            review.setRegion(review.getRegion());

            updatedReviewId = reviewRepository.update(review);

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        // 성공했을 경우 updatedReviewid 리턴, 실패 시 0 리턴
        return updatedReviewId;
    }


    // 후기 게시글 삭제하는 메소드
    public int delete(int reviewId) {
        int deletedReviewId;

        try {
            deletedReviewId = reviewRepository.delete(reviewId);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        // 성공했을 경우 deletedReviewId 리턴, 실패 시 0 리턴
        return deletedReviewId;
    }

    // 게시글 리스트를 출력해줄 메소드
    public List<ReviewDTO> selectAll() {
        List<ReviewDTO> list = reviewRepository.selectAll();

        // 작성자 닉네임 보여주기 위한 코드는
        // userService에서 가져오려고 지금은 임시로 식별자(writerId)를 보여줄 예정
        //HashMap<Integer, String> nicknameMap = new HashMap<>();

        return list;
    }

    // 게시글 상세보기
    @GetMapping("/selectOne")
    public ReviewDTO selectOne(@RequestBody ReviewDTO review, @AuthenticationPrincipal int userId) {
        LikeOrDislikeDTO l = null;

        l.setCategoryId(review.getCategoryId());
        l.setBoardId(review.getReviewId());
        l.setUserId(review.getWriterId());

        // likeOrDislike

        // 게시물을 클릭했다는 의미이므로 조회수부터 +1 시켜준다.
        reviewRepository.viewPlus(review.getReviewId());

        // --------_ ? ?? ? ? ? ?? ?
        // 좋아요 클릭 시
        HashMap<String, Boolean> likeMap = ReviewStatus(review);
        if(likeMap.get("liked") == true) {
            likeOrDislikeService.clickLike(l);
        } else {
            likeOrDislikeService.clickDislike(l);
        }




        // 작성된 게시글을 보여주는 코드
        review = reviewRepository.selectOne(review.getReviewId());

        return review;
    }


    // 게시글 좋아요 상태 리턴해주는 메소드
    public HashMap<String, Boolean> ReviewStatus(ReviewDTO review) {
        LikeOrDislikeDTO l = null;

        l.setCategoryId(review.getCategoryId());
        l.setBoardId(review.getReviewId());
        l.setUserId(review.getWriterId());
        HashMap<String, Boolean> likeMap = likeOrDislikeService.isLikeOrDislikeChecked(l);

        return likeMap;
    }
}
