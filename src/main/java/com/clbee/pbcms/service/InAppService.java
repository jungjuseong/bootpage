package com.clbee.pbcms.service;


import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.clbee.pbcms.dao.InAppDao;
import com.clbee.pbcms.vo.InAppList;
import com.clbee.pbcms.vo.InappMetaVO;
import com.clbee.pbcms.vo.InappSubVO;
import com.clbee.pbcms.vo.InappVO;
import com.clbee.pbcms.vo.MemberVO;

@Service
@AllArgsConstructor
public class InAppService {

    private InAppDao inAppDao;

    public InappVO findByCustomInfo(String DBName, int intValue) {
        // TODO Auto-generated method stub
        return inAppDao.findByCustomInfo(DBName, intValue);
    }

    public InappVO findByCustomInfo(String DBName, String Value) {
        // TODO Auto-generated method stub
        return inAppDao.findByCustomInfo(DBName, Value);
    }

    public List<InappVO> getListInappVO(String DBName, String storeBundleId, int userSeq) {
        // TODO Auto-generated method stub
        return inAppDao.getListInappVO(DBName, storeBundleId, userSeq);
    }

    public List<InappVO> getListInappVO(String DBName, String value) {
        // TODO Auto-generated method stub
        return inAppDao.getListInappVO(DBName, value);
    }

    public InAppList getListByBundleId(InappVO vo, InAppList inAppList, MemberVO memberVO) {
        // TODO Auto-generated method stub
        //AppList list = null;
        int pageSize = 10;
        int maxResult = 10;
        int totalCount = 0;

        try{
            totalCount = inAppDao.getListCntByBundleId(vo, inAppList, memberVO);
            System.out.println("totalCount = " + totalCount);

            inAppList.calc(pageSize, totalCount, inAppList.getCurrentPage(), maxResult);

            List<InappVO> list = inAppDao.getListByBundleId(vo, inAppList, memberVO);

            inAppList.setList(list);

            System.out.println("[ListService] - selectList method");
            System.out.println("selectList[] " + list.size());
            System.out.println(list.size());

        }catch(Exception e){
            System.out.println("����");
            e.printStackTrace();
        }
        return inAppList;
    }

    public int getSeqAfterInsertInAppInfo(InappVO vo) {
        // TODO Auto-generated method stub
        return inAppDao.getSeqAfterInsertAppInfo(vo);
    }

    public InappVO selectForUpdate(InappVO ivo, MemberVO memberVO) {
        // TODO Auto-generated method stub
        return inAppDao.selectForUpdate(ivo, memberVO);
    }

    public void updateInAppInfo(InappVO ivo, int inappSeq) {
        // TODO Auto-generated method stub
        inAppDao.updateInAppInfo(ivo, inappSeq);
    }

    public Object[] getListInAppForRelatedApp(String appSeq) {
        // TODO Auto-generated method stub
        return inAppDao.getListInAppForRelatedApp( appSeq );
    }

    public List findListByCustomInfo(String DBName, String value) {
        // TODO Auto-generated method stub
        return inAppDao.findListByCustomInfo(DBName, value);
    }

    public List findListByCustomInfo(String DBName, int value) {
        // TODO Auto-generated method stub
        return inAppDao.findListByCustomInfo(DBName, value);
    }

    public List<InappSubVO> selectInAppSubList(int inAppSeq) {
        // TODO Auto-generated method stub
        return inAppDao.selectInAppSubList(inAppSeq);
    }

    public int insertInAppSubInfo(InappSubVO inAppSubVO) {
        // TODO Auto-generated method stub
        return inAppDao.insertInAppSubInfo(inAppSubVO);
    }

    public void deleteInAppSubInfo(InappSubVO inAppSubVO) {
        // TODO Auto-generated method stub
        inAppDao.deleteInAppSubInfo(inAppSubVO);
    }

    public boolean checkInappNameIfExist(String InappName, String storeBundleId, String verNum) {
        // TODO Auto-generated method stub
        return inAppDao.checkInappNameIfExist(InappName, storeBundleId, verNum);
    }

    public List<InappVO> getListInappIsAvailableByStoreBundleId (String storeBundleId) {
        // TODO Auto-generated method stub
        return inAppDao.getListInappIsAvailableByStoreBundleId (storeBundleId);
    }

    public int insertInAppMetaInfo(InappMetaVO inappMetaVO) {
        // TODO Auto-generated method stub
        return inAppDao.insertInAppMetaInfo(inappMetaVO);
    }

    public InappMetaVO findByCustomInfoForMetaVO(String DBName, int intValue) {
        // TODO Auto-generated method stub
        return inAppDao.findByCustomInfoForMetaVO(DBName, intValue);
    }

    public InappMetaVO findByCustomInfoForMetaVO(String DBName, String value) {
        // TODO Auto-generated method stub
        return inAppDao.findByCustomInfoForMetaVO(DBName, value);
    }

    public void updateInAppMetaInfo(InappMetaVO updatedVO, int inappMetaSeq) {
        // TODO Auto-generated method stub
        inAppDao.updateInAppMetaInfo(updatedVO, inappMetaSeq);
    }

    public void deleteInAppInfo( String storeBundleId ) {
        // TODO Auto-generated method stub
        inAppDao.deleteInAppInfo(storeBundleId);
    }

    public String selectCompletGbBySeq( int inapp_seq ) {
        // TODO Auto-generated method stub
        return inAppDao.selectCompletGbBySeq("selectCompletGbBySeq", inapp_seq);
    }

    public String getSameInappSeq ( String inapp_name, String store_bundle_id ) {
        // TODO Auto-generated method stub
        return inAppDao.getSameInappSeq("getSameInappSeq", inapp_name, store_bundle_id);
    }

    public void insertInappHistory( String inapp_seq ){
        inAppDao.insertInappHistory("insertInappHistory", inapp_seq);
    }

    public void deleteInAppBySeq( String inapp_seq ){
        inAppDao.deleteInAppBySeq("deleteInAppBySeq", inapp_seq);
    }

    public List<InappVO> selectForHistory( String inapp_name, String store_bundle_id ) {
        return inAppDao.selectForHistory( "selectInappForHistory", inapp_name, store_bundle_id );
    }

    public int infoUpdateCheck(InappVO inappFormVO, int inapp_seq) {
        // TODO Auto-generated method stub
        int infoUpdate = 0;
        InappVO inappVO = inAppDao.selectInappBySeq( "selectInappBySeq", inapp_seq );

        if(inappVO.getCompletGb().equals("2") || inappVO.getCompletGb().equals("4")) {
            if( inappFormVO.getInappName() != null && !"".equals(inappFormVO.getInappName()) && !(inappFormVO.getInappName().equals(inappVO.getInappName())) ) {	infoUpdate += 1;	}
            if( inappFormVO.getVerNum() != null && !"".equals(inappFormVO.getVerNum()) && !(inappFormVO.getVerNum().equals(inappVO.getVerNum())) ) {	infoUpdate += 1;	}
            if( inappFormVO.getCategorySeq() != null && !"".equals(inappFormVO.getCategorySeq()) && !(inappFormVO.getCategorySeq().equals(inappVO.getCategorySeq())) ) {	infoUpdate += 1;	}
            if( inappFormVO.getCategoryName() != null && !"".equals(inappFormVO.getCategoryName()) && !(inappFormVO.getCategoryName().equals(inappVO.getCategoryName())) ) {	infoUpdate += 1;	}
            if( inappFormVO.getDescriptionText() != null && !"".equals(inappFormVO.getDescriptionText()) && !(inappFormVO.getDescriptionText().equals(inappVO.getDescriptionText())) ) {	infoUpdate += 1;	}

            if(inappFormVO.getIconOrgFile() != null && !"".equals(inappFormVO.getIconOrgFile()) && !(inappFormVO.getIconOrgFile().equals(inappVO.getIconOrgFile())) ) {	infoUpdate += 1;	}
            if(inappFormVO.getIconSaveFile() != null && !"".equals(inappFormVO.getIconSaveFile()) && !(inappFormVO.getIconSaveFile().equals(inappVO.getIconSaveFile())) ) {	infoUpdate += 1;	}

        }

        if( inappFormVO.getUseGb() != null && !"".equals(inappFormVO.getUseGb()) && !(inappFormVO.getUseGb().equals(inappVO.getUseGb())) ) {	infoUpdate += 1;	}
        if( inappFormVO.getUseUserGb() != null && !"".equals(inappFormVO.getUseUserGb()) && !(inappFormVO.getUseUserGb().equals(inappVO.getUseUserGb())) ) {	infoUpdate += 1;	}
        if( inappFormVO.getScreenType() != null && !"".equals(inappFormVO.getScreenType()) && !(inappFormVO.getScreenType().equals(inappVO.getScreenType())) ) {	infoUpdate += 1;	}

        return infoUpdate;
    }

    public InAppList getInAppByBundleId(InAppList inAppList, String store_bundle_id) {
        // TODO Auto-generated method stub
        int totalCount = 0;

        try{
            totalCount = inAppDao.getInAppCntByBundleId("getInAppCntByBundleId", store_bundle_id);
            inAppList.setTotalCount(totalCount);

            List<InappVO> list = inAppDao.getInAppByBundleId("getInAppByBundleId", store_bundle_id);
            inAppList.setList(list);
        }catch(Exception e){
            e.printStackTrace();
        }

        return inAppList;
    }
}
