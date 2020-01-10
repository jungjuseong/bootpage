package com.clbee.pbcms.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    int getCompanySeqForInappContentCopy(int userSeq);
    String selectCompanyName(int companySeq);
}
