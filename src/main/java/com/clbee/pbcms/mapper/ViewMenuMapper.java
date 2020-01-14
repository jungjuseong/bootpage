package com.clbee.pbcms.mapper;

import com.clbee.pbcms.vo.GroupMenuVO;
import com.clbee.pbcms.vo.GroupUserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface ViewMenuMapper {
    GroupUserVO selectViewMenuInfo(int groupSeq);
    GroupMenuVO selectViewMenu(int menuType, HashMap item);
}
