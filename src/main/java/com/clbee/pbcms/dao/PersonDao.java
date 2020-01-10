package com.clbee.pbcms.dao;

import org.apache.ibatis.session.SqlSession;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDao {
    private final SessionFactory sessionFactory;
    private final SqlSession sqlSession;

    public PersonDao(SessionFactory sessionFactory, SqlSession sqlSession) {
        this.sessionFactory = sessionFactory;
        this.sqlSession = sqlSession;
    }

}
