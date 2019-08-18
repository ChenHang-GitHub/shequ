package com.csh.community.dao;


import com.csh.community.pojo.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PublishMapper {


    void createMapper(Question question);
}
