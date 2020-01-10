package com.clbee.pbcms.dao;

import java.util.HashMap;
import java.util.List;

import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.clbee.pbcms.Json.ConnectLicenseInfo;
import com.clbee.pbcms.util.Entity;
import com.clbee.pbcms.vo.LicenseList;
import com.clbee.pbcms.vo.LicenseSubList;
import com.clbee.pbcms.vo.LicenseSubVO;
import com.clbee.pbcms.vo.LicenseVO;
import com.clbee.pbcms.vo.MemberVO;

@AllArgsConstructor
@Repository
public class LicenseDao {

    private SqlSession sqlSession;

    public int checkUseLicense(String DBName, int userSeq) {
        // TODO Auto-generated method stub
        return sqlSession.selectOne(DBName, userSeq);
    }

    public List<LicenseVO> selectList(String DBName, LicenseList licenseList ) {
        // TODO Auto-generated method stub
        return sqlSession.selectList(DBName, licenseList);
    }

    public int totalCount( String DBName, LicenseList licenseList ){
        // TODO Auto-generated method stub
        return sqlSession.selectOne(DBName, licenseList);
    }

    public int dupleCheck(String DBName, String license) {
        // TODO Auto-generated method stub
        return sqlSession.selectOne(DBName, license);
    }

    public void insertLicense(String DBName, LicenseVO licenseVO) {
        // TODO Auto-generated method stub
        sqlSession.insert(DBName, licenseVO);
    }

    public int disposalLicense(String DBName, Entity param) {
        // TODO Auto-generated method stub
        return sqlSession.update(DBName, param);
    }

    public List<LicenseVO> licenseRegistCheck(String DBName, String license) {
        // TODO Auto-generated method stub
        return sqlSession.selectList(DBName, license);
    }

    public void licenseRegist(String DBName, LicenseVO vo) {
        // TODO Auto-generated method stub
        sqlSession.update(DBName, vo);
    }

    public List<LicenseVO> selectMyLicense(String DBName, int userSeq) {
        // TODO Auto-generated method stub
        return sqlSession.selectList(DBName, userSeq);
    }

    public int licenseExpire(String DBName, int licenseSeq) {
        // TODO Auto-generated method stub
        return sqlSession.update(DBName, licenseSeq);
    }

    public void licenseExpireEveryday(String DBName) {
        // TODO Auto-generated method stub
        sqlSession.update(DBName);
    }

    public int totalCountDevice(String DBName, LicenseSubList licenseUseDevice) {
        // TODO Auto-generated method stub
        return sqlSession.selectOne(DBName, licenseUseDevice);
    }

    public List<LicenseSubVO> selectListDevice(String DBName, LicenseSubList licenseUseDevice) {
        // TODO Auto-generated method stub
        return sqlSession.selectList(DBName, licenseUseDevice);
    }

    public int deleteLicenseUseDevice(String DBName, int licensesubSeq) {
        // TODO Auto-generated method stub
        return sqlSession.delete(DBName, licensesubSeq);
    }

    public int deleteLicenseSub(String DBName, int licenseSeq) {
        // TODO Auto-generated method stub
        return sqlSession.delete(DBName, licenseSeq);
    }

    public List<MemberVO> checkAccountStatus(String DBName, ConnectLicenseInfo connectLicenseInfo) {
        // TODO Auto-generated method stub
        return sqlSession.selectList(DBName, connectLicenseInfo);
    }

    public List<LicenseVO> checkRegistLicenseWithAccount(String DBName, MemberVO account) {
        // TODO Auto-generated method stub
        return sqlSession.selectList(DBName, account);
    }

    public void licenseUserRegist(String DBName, ConnectLicenseInfo connectLicenseInfo) {
        // TODO Auto-generated method stub
        sqlSession.insert(DBName, connectLicenseInfo);
    }

    public String selectLicenseDisposalReason(String DBName, int licenseSeq) {
        // TODO Auto-generated method stub
        return sqlSession.selectOne(DBName, licenseSeq);
    }

    public int getLicenseUserCount(String DBName, int licenseSeq) {
        // TODO Auto-generated method stub
        return sqlSession.selectOne(DBName, licenseSeq);
    }

    public boolean checkLicenseUserExist(String DBName, int userSeq) {
        // TODO Auto-generated method stub
        int checkCount = sqlSession.selectOne(DBName, userSeq);
        if(checkCount > 0) {
            return true;
        }else {
            return false;
        }
    }

    public void deleteLicenseSubByUserSeq(String DBName, int userSeq) {
        // TODO Auto-generated method stub
        sqlSession.delete(DBName, userSeq);
    }

    public List<ConnectLicenseInfo> licenseAuthCheckWithDevice(String DBName, ConnectLicenseInfo connectLicenseInfo) {
        // TODO Auto-generated method stub
        return sqlSession.selectList(DBName, connectLicenseInfo);
    }

    public int checkAccountStatusByUserSeq(String DBName, int userSeq) {
        // TODO Auto-generated method stub
        return sqlSession.selectOne(DBName, userSeq);
    }

    public int checkLicenseStatusByLicenseSeq(String DBName, int licenseSeq) {
        // TODO Auto-generated method stub
        return sqlSession.selectOne(DBName, licenseSeq);
    }

    public List<HashMap<String, String>> licenseExpireAlertEveryday(String DBName) {
        // TODO Auto-generated method stub
        return sqlSession.selectList(DBName);
    }

    public String selectLicenseUseCompanyName(String DBName, int userSeq) {
        // TODO Auto-generated method stub
        return sqlSession.selectOne(DBName, userSeq);
    }
}
