package com.clbee.pbcms.mapper;

import com.clbee.pbcms.vo.GroupList;
import com.clbee.pbcms.vo.GroupMenuVO;
import com.clbee.pbcms.vo.GroupUserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GroupMapper {
    GroupMenuVO selectMenu(String menu_type);
    void insertGroupUser(GroupUserVO groupUser);
    GroupUserVO selectListMemberGroup(GroupList groupList);
    int totalCountMemberGroup(int searchValue);
    GroupUserVO selectListUserGroup(GroupList list);
    int totalCountUserGroup(int searchValue);
    int groupNameOverlap(String groupName);
    void deleteGroup(int numGroupSeq);
    GroupUserVO selectGroupInfo(int groupSeq);
    void updateGroupUser(int groupSeq, GroupUserVO groupUser);
    GroupUserVO getSelectListGroup(int companySeq);
    int deleteCheck(int numGroupSeq);
}