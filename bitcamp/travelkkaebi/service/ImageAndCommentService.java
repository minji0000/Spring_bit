package com.bitcamp.travelkkaebi.service;

import com.bitcamp.travelkkaebi.mapper.ImageAndCommentMapper;
import com.bitcamp.travelkkaebi.model.ImageAndCommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageAndCommentService {

    private final AwsS3service awsS3service;
    private final ImageAndCommentMapper imageAndCommentMapper;

    /**
     * 사진 및 코멘트 업로드
     * @param imageList
     * @param commentList
     * @param userId
     * @return
     * @throws Exception
     */
    public boolean insert(List<MultipartFile> imageList,
      List<ImageAndCommentDTO> commentList, int userId) throws Exception {
        System.out.println("이미지 AND 코멘트 서비스 도착");

        int successCount = 0;
        for(int i = 0; i < commentList.size(); i++) {
            ImageAndCommentDTO imageAndCommentDTO = commentList.get(i);

            // 로그인 한 유저의 식별자 set
            imageAndCommentDTO.setUserId(userId);

            // 아마존 s3에 이미지 저장 및 url set
            imageAndCommentDTO.setImageUrl(awsS3service.upload(imageList.get(i), "static"));

            successCount += imageAndCommentMapper.insert(imageAndCommentDTO);
        }
        return (successCount == commentList.size() ? true : false);
    }

    public void test(List<MultipartFile> imageList, int userId) throws Exception {
        System.out.println("imageList.size() = " + imageList.size());


        for(int i = 0; i < imageList.size(); i++) {
            ImageAndCommentDTO imageAndCommentDTO = null;

            imageAndCommentDTO.setImageUrl(awsS3service.upload(imageList.get(i), "static"));
            imageAndCommentDTO.setUserId(userId);
            imageAndCommentMapper.test(imageAndCommentDTO);
        }
    }

    /**
     * 사진 및 코멘트 수정
     * @param imageList
     * @param commentList
     * @param userId
     * @return
     * @throws Exception
     */
    @Transactional
    public int update(List<MultipartFile> imageList, List<ImageAndCommentDTO> commentList, int userId) throws Exception {
        int updatedId = 0;


        for(int i = 0; i < commentList.size(); i++) {
           ImageAndCommentDTO imageAndCommentDTO = commentList.get(i);

            // 로그인한 유저가 글의 작성자인지 확인
            if(userId == imageAndCommentDTO.getUserId()) {
               imageAndCommentDTO.setImageUrl(awsS3service.upload(imageList.get(i), "static"));
               imageAndCommentDTO.setComment(imageAndCommentDTO.getComment());

               updatedId += imageAndCommentMapper.update(imageAndCommentDTO);
           } else {
               return 0;
           }
        }
        return updatedId;
    }

    /**
     * 사진 삭제
     * @param imageIdList
     * @param userId
     * @return
     * @throws Exception
     */
    @Transactional
    public int delete(List<ImageAndCommentDTO> imageIdList, int userId) throws Exception {
        int deleteId = 0;

        for(ImageAndCommentDTO imageAndCommentDTO : imageIdList) {
            // 로그인한 유저가 글의 작성자인지 확인
            if(userId == imageAndCommentDTO.getUserId()) {
                return imageAndCommentMapper.delete(imageAndCommentDTO.getImageAndCommentId());
            } else {
                return 0;
            }
        }
        return deleteId;
    }

    /**
     * 게시글 상세보기
     *@param imageAndCommentDTO
     * @return
     */
    public List<ImageAndCommentDTO> selectAll(ImageAndCommentDTO imageAndCommentDTO) {
        return imageAndCommentMapper.selectAll(imageAndCommentDTO);
    }


}
