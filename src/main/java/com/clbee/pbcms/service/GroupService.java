package com.clbee.pbcms.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.clbee.pbcms.dao.GroupDao;
import com.clbee.pbcms.vo.GroupList;
import com.clbee.pbcms.vo.GroupMenuVO;
import com.clbee.pbcms.vo.GroupUserVO;

@Service
public class GroupService {

    private final GroupDao groupDao;

    int pageSize = 10;
    int maxResult = 10;
    int totalCount = 0;
    int startNo = 0;

    public GroupService(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public List<GroupMenuVO> selectMenu( String menu_type ) {
        return groupDao.selectMenu("selectMenu", menu_type);
    }

    public void insertGroupUser(GroupUserVO groupUserVO) {
        // TODO Auto-generated method stub
        groupDao.insertGroupUser("insertGroupUser", groupUserVO);
    }

    public GroupList selectList( GroupList groupList ) {
        // TODO Auto-generated method stub
        List<GroupUserVO> vo = null;

        if(groupList.getCompanySeq() == 0) {//회원인경우
            totalCount = groupDao.totalCount("totalCountMemberGroup", groupList);

            groupList.calc(pageSize, totalCount, groupList.getCurrentPage(), maxResult);

            if(groupList.getSearchValue() == "") {
                groupList.setSearchValue(null);
            }

            vo = groupDao.selectList("selectListMemberGroup", groupList);
        }else {//사용자인경우
            totalCount = groupDao.totalCount("totalCountUserGroup", groupList);

            groupList.calc(pageSize, totalCount, groupList.getCurrentPage(), maxResult);

            if(groupList.getSearchValue() == "") {
                groupList.setSearchValue(null);
            }

            vo = groupDao.selectList("selectListUserGroup", groupList);
        }

        groupList.setList(vo);
        return groupList;
    }

    public int groupNameOverlap(String groupName) {
        // TODO Auto-generated method stub
        return groupDao.groupNameOverlap("groupNameOverlap", groupName);
    }

    public int deleteGroup(int numGroupSeq) {
        int cnt = groupDao.deleteCheck("deleteCheck", numGroupSeq);

        if(cnt == 0) {
            return groupDao.deleteGroup("deleteGroup", numGroupSeq);
        }else if(cnt > 0) {
            return 2;
        }else {
            return 0;
        }
    }

    public GroupUserVO selectGroupInfo(int groupSeq) {
        // TODO Auto-generated method stub
        return groupDao.selectGroupInfo("selectGroupInfo", groupSeq);
    }

    public void updateGroupUser(GroupUserVO groupUserVO) {
        // TODO Auto-generated method stub
        groupDao.updateGroupUser("updateGroupUser", groupUserVO);
    }

    public List<GroupUserVO> getSelectListGroup(int companySeq) {
        // TODO Auto-generated method stub
        return groupDao.getSelectListGroup("getSelectListGroup", companySeq);
    }

}
