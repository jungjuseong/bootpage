package com.clbee.pbcms.dao;

import java.util.List;

import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.clbee.pbcms.vo.GroupList;
import com.clbee.pbcms.vo.GroupMenuVO;
import com.clbee.pbcms.vo.GroupUserVO;

@AllArgsConstructor
@Repository
public class GroupDao {

    private SqlSession sqlSession;

    public List<GroupMenuVO> selectMenu(String DBName, String menu_type ) {
        // TODO Auto-generated method stub
        return sqlSession.selectList(DBName, menu_type);
    }

    public void insertGroupUser(String DBName, GroupUserVO groupUserVO) {
        // TODO Auto-generated method stub
        sqlSession.insert(DBName, groupUserVO);
    }

    public List<GroupUserVO> selectList(String DBName, GroupList groupList ) {
        // TODO Auto-generated method stub
        return sqlSession.selectList(DBName, groupList);
    }

    public int totalCount( String DBName, GroupList groupList ){
        // TODO Auto-generated method stub
        return sqlSession.selectOne(DBName, groupList);
    }

    public int groupNameOverlap(String DBName, String groupName) {
        // TODO Auto-generated method stub
        return sqlSession.selectOne(DBName, groupName);
    }

    public int deleteCheck(String DBName, int numGroupSeq) {
        // TODO Auto-generated method stub
        return sqlSession.selectOne(DBName, numGroupSeq);
    }

    public int deleteGroup(String DBName, int numGroupSeq) {
        // TODO Auto-generated method stub
        return sqlSession.delete(DBName, numGroupSeq);
    }

    public GroupUserVO selectGroupInfo(String DBName, int groupSeq) {
        // TODO Auto-generated method stub
        return sqlSession.selectOne(DBName, groupSeq);
    }

    public void updateGroupUser(String DBName, GroupUserVO groupUserVO) {
        // TODO Auto-generated method stub
        sqlSession.update(DBName, groupUserVO);
    }

    public List<GroupUserVO> getSelectListGroup(String DBName, int companySeq) {
        // TODO Auto-generated method stub
        return sqlSession.selectList(DBName, companySeq);
    }
}
