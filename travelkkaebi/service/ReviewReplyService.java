package com.bitcamp.travelkkaebi.service;

import com.bitcamp.travelkkaebi.model.ReviewDTO;
import com.bitcamp.travelkkaebi.model.ReviewReplyDTO;
import com.bitcamp.travelkkaebi.repository.ReviewReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewReplyService {

    private final ReviewReplyRepository reviewReplyRepository;

    // 후기 등록하는 메소드
    public int writeReply(ReviewReplyDTO reply, ReviewDTO review, @AuthenticationPrincipal int userId) {

        int writtenReplyId;

        try {
            reply.setBoardId(review.getReviewId());
            reply.setCategoryId(review.getCategoryId());
            reply.setWriterId(userId);
            reply.setComment(reply.getComment());

            reviewReplyRepository.insert(reply);

            // 댓글 등록 성공 시
            writtenReplyId = reviewReplyRepository.insert(reply);

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        return writtenReplyId;
    }

    // 후기 수정하는 메소드
    public int update(ReviewReplyDTO reply) {
        int updatedReplyId;

        try {
            reply.setComment(reply.getComment());
            updatedReplyId = reviewReplyRepository.update(reply);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        return updatedReplyId;
    }

    // 후기 삭제하는 메소드
    public int delete(int replyId) {
        int deletedReplyId;

        try {
            deletedReplyId = reviewReplyRepository.delete(replyId);

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        return deletedReplyId;
    }

    // 후기 댓글 보여주는 메소드
    public List<ReviewReplyDTO> selectOne(int reviewId) {

        List<ReviewReplyDTO> list = reviewReplyRepository.selectOne(reviewId);

        return list;
    }





}
