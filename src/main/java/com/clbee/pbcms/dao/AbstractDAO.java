package com.clbee.pbcms.dao;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractDAO extends SqlSessionDaoSupport {

    protected Logger log = LoggerFactory.getLogger(super.getClass());

    private SqlSession sqlSession;

    public int insert(String queryId, Object parameterObject) {
        return sqlSession.insert(queryId, parameterObject);
    }

    public int update(String queryId, Object parameterObject) {
        return sqlSession.update(queryId, parameterObject);
    }

    public int delete(String queryId, Object parameterObject) {
        return sqlSession.delete(queryId, parameterObject);
    }

    public Object selectOne(String queryId, Object parameterObject) {
        return sqlSession.selectOne(queryId, parameterObject);
    }

    public List list(String queryId, Object parameterObject) {
        return sqlSession.selectList(queryId, parameterObject);
    }

    // RowBounds 이용한 페이징은 큰 데이터에서 성능저하 있으므로 안쓰는게 나음
    public List listWithPaging(String queryId, Object parameterObject, int pageIndex, int pageSize) {
        int skipResults = pageIndex * pageSize;
        int maxResults = pageIndex * pageSize + pageSize;
        return sqlSession.selectList(queryId, parameterObject,
                new RowBounds(skipResults, maxResults));
    }
}
