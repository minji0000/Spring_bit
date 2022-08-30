package com.bitcamp.travelkkaebi.mapper;

import com.bitcamp.travelkkaebi.model.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import org.hibernate.annotations.Parameter;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface ReviewMapper {

    @Results({
            @Result(column = "review_id", property = "reviewId"),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "title", property = "title"),
            @Result(column = "content", property = "content"),
            @Result(column = "region", property = "region"),
            @Result(column = "like_count", property = "likeCount"),
            @Result(column = "dislike_count", property = "dislikeCount"),
            @Result(column = "view", property = "view"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Select("SELECT * FROM board_review ORDER BY review_id DESC LIMIT #{r.startNum}, #{r.PAGE_SIZE}")
    List<ReviewDTO> selectAllByPage(@Param("r") HashMap<String, Integer> pageMap);


    @Results({
            @Result(column = "review_id", property = "reviewId"),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "title", property = "title"),
            @Result(column = "content", property = "content"),
            @Result(column = "region", property = "region"),
            @Result(column = "like_count", property = "likeCount"),
            @Result(column = "dislike_count", property = "dislikeCount"),
            @Result(column = "view", property = "view"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Select("SELECT * FROM board_review WHERE review_id = #{reviewId}")
    ReviewDTO selectOne(int reviewId);

    @Results({
            @Result(column = "review_id", property = "reviewId"),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "title", property = "title"),
            @Result(column = "content", property = "content"),
            @Result(column = "region", property = "region"),
            @Result(column = "like_count", property = "likeCount"),
            @Result(column = "dislike_count", property = "dislikeCount"),
            @Result(column = "view", property = "view"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Insert("INSERT INTO board_review (category_id, user_id, title, content, region)" +
            "VALUES (#{r.categoryId}, #{r.userId}, #{r.title}, #{r.content}, #{r.region})")
    int insert(@Param("r") ReviewDTO reviewDTO);

    @Results({
            @Result(column = "review_id", property = "reviewId"),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "title", property = "title"),
            @Result(column = "content", property = "content"),
            @Result(column = "region", property = "region"),
            @Result(column = "like_count", property = "likeCount"),
            @Result(column = "dislike_count", property = "dislikeCount"),
            @Result(column = "view", property = "view"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Update("UPDATE board_review SET title = #{r.title}, content = #{r.content}, region = #{r.region}" +
            "WHERE review_id = #{r.reviewId}")
    int update(@Param("r") ReviewDTO reviewDTO);

    @Results({
            @Result(column = "review_id", property = "reviewId"),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "title", property = "title"),
            @Result(column = "content", property = "content"),
            @Result(column = "region", property = "region"),
            @Result(column = "like_count", property = "likeCount"),
            @Result(column = "dislike_count", property = "dislikeCount"),
            @Result(column = "view", property = "view"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Delete("DELETE FROM board_review WHERE review_id = #{reviewId}")
    int delete(int reviewId);

    @Results({
            @Result(column = "review_id", property = "reviewId"),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "title", property = "title"),
            @Result(column = "content", property = "content"),
            @Result(column = "region", property = "region"),
            @Result(column = "like_count", property = "likeCount"),
            @Result(column = "dislike_count", property = "dislikeCount"),
            @Result(column = "view", property = "view"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Update("UPDATE board_review SET view = view +1 WHERE review_id = #{reviewId}")
    int viewPlus(int reviewId);


    @Results({
            @Result(column = "review_id", property = "reviewId"),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "title", property = "title"),
            @Result(column = "content", property = "content"),
            @Result(column = "region", property = "region"),
            @Result(column = "like_count", property = "likeCount"),
            @Result(column = "dislike_count", property = "dislikeCount"),
            @Result(column = "view", property = "view"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Update("UPDATE board_review SET like_count = #{likeCount} + 1 " +
            "WHERE review_id = #{reviewId}")
    int likeUp(int reviewId);

    @Results({
            @Result(column = "review_id", property = "reviewId"),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "title", property = "title"),
            @Result(column = "content", property = "content"),
            @Result(column = "region", property = "region"),
            @Result(column = "like_count", property = "likeCount"),
            @Result(column = "dislike_count", property = "dislikeCount"),
            @Result(column = "view", property = "view"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Update("UPDATE board_review SET like_count = #{likeCount} - 1 " +
            "WHERE review_id = #{reviewId}")
    int likeDown(int reviewId);

    @Results({
            @Result(column = "review_id", property = "reviewId"),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "title", property = "title"),
            @Result(column = "content", property = "content"),
            @Result(column = "region", property = "region"),
            @Result(column = "like_count", property = "likeCount"),
            @Result(column = "dislike_count", property = "dislikeCount"),
            @Result(column = "view", property = "view"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Update("UPDATE board_review SET dislike_count = #{dislikeCount} + 1 " +
            "WHERE review_id = #{reviewId}")
    int dislikeUp(int reviewId);

    @Results({
            @Result(column = "review_id", property = "reviewId"),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "title", property = "title"),
            @Result(column = "content", property = "content"),
            @Result(column = "region", property = "region"),
            @Result(column = "like_count", property = "likeCount"),
            @Result(column = "dislike_count", property = "dislikeCount"),
            @Result(column = "view", property = "view"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Update("UPDATE board_review SET dislike_count = #{dislikeCount} - 1 " +
            "WHERE review_id = #{reviewId}")
    int dislikeDown(int reviewId);

    @Results({
            @Result(column = "review_id", property = "reviewId"),
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "title", property = "title"),
            @Result(column = "content", property = "content"),
            @Result(column = "region", property = "region"),
            @Result(column = "like_count", property = "likeCount"),
            @Result(column = "dislike_count", property = "dislikeCount"),
            @Result(column = "view", property = "view"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Select("SELECT COUNT(*) FROM board_review")
    int reviewCount();


}
