package com.clbee.pbcms.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {
    String selectFirstUrl(int groupSeq);
}