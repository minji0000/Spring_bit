package com.bitcamp.travelkkaebi.service;

import com.bitcamp.travelkkaebi.mapper.ImageMapper;
import com.bitcamp.travelkkaebi.model.ImageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final AwsS3service awsS3service;
    private final ImageMapper imageMapper;

    public List<ImageDTO> selectAll(ImageDTO imageDTO) {
        return imageMapper.selectAll(imageDTO);
    }

    public boolean insert(List<MultipartFile> imageList, List<ImageDTO> imageDTOList, int userId) throws Exception {
        int successCount = 0;
        for (int i = 0; i < imageDTOList.size(); i++) {
            ImageDTO imageDTO = imageDTOList.get(i);
            //로그인한 유저의 식별자set
            imageDTO.setUserId(userId);
            //아마존s3에 이미지저장하고 url Set해주는 부분
            imageDTO.setImageUrl(awsS3service.upload(imageList.get(i), "static"));
            successCount += imageMapper.insert(imageDTO);
        }
        //이미지파일 저장과 경로삽입이 모두 다 성공적으로 끝나면 true리턴
        return (successCount == imageDTOList.size());
    }

    @Transactional
    public boolean update(List<MultipartFile> imageList, List<ImageDTO> imageDTOList, int userId) throws Exception {
        int successCount = 0;
        for (int i = 0; i < imageDTOList.size(); i++) {
            ImageDTO imageDTO = imageDTOList.get(i);
            //로그인한 유저의 식별자set
            imageDTO.setUserId(userId);
            //아마존s3에 이미지저장하고 url Set해주는 부분
            imageDTO.setImageUrl(awsS3service.upload(imageList.get(i), "static"));
            successCount += imageMapper.update(imageDTO);
        }
        //업데이트된 이미지파일들의 저장과 경로update가 모두 다 성공적으로 끝나면 true리턴
        return (successCount == imageDTOList.size());
    }

    @Transactional
    public boolean delete(List<Integer> imageIdList, int userId) throws Exception {
        int successCount = 0;
        for (int imageId : imageIdList) {
            ImageDTO imageDTO = ImageDTO.builder().imageId(imageId).userId(userId).build();
            successCount += imageMapper.delete(imageDTO);
        }
        return (successCount == imageIdList.size());
    }
}