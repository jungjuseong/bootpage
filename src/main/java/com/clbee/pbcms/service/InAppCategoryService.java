package com.clbee.pbcms.service;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clbee.pbcms.dao.InAppCategoryDao;
import com.clbee.pbcms.vo.AppList;
import com.clbee.pbcms.vo.AppVO;
import com.clbee.pbcms.vo.InappcategoryVO;

@Service
@AllArgsConstructor
public class InAppCategoryService {

    final InAppCategoryDao dao;

    public int insertInAppInfo(InappcategoryVO inCateVo) {
        return dao.insertInAppInfo(inCateVo);
    }

    public List<InappcategoryVO> selectInAppList(InappcategoryVO inCateVo) {
        List<InappcategoryVO> Result = null;
        try{
            Result = dao.selectInAppList(inCateVo);
        }catch(Exception e){
            e.printStackTrace();
        }
        return Result;
    }

    public void updateInAppInfo(InappcategoryVO inCateVo) {
        dao.updateInAppInfo(inCateVo);
    }

    public void deleteInAppInfo(InappcategoryVO inCateVo) {
        dao.deleteInAppInfo(inCateVo);

    }

    public Object[] selectInAppList2(InappcategoryVO inCateVo) {

        Object[] Result = null;

        try{
            Result = dao.selectInAppList2(inCateVo);
        }catch(Exception e){
            e.printStackTrace();
        }
        return Result;
    }

    public InappcategoryVO findByCustomInfo(String DBName, int intValue) {
        return dao.findByCustomInfo(DBName, intValue);
    }

    public InappcategoryVO findByCustomInfo(String DBName, String Value) {
        return dao.findByCustomInfo(DBName, Value);
    }

    public List<InappcategoryVO> getListInAppCategory(String DBName, String storeBundleId) {
        // TODO Auto-generated method stub
        return dao.getListInAppCategory(DBName, storeBundleId);
    }

    public List<InappcategoryVO> getListInAppCategoryforOneDepth(String DBName,
                                                                 String storeBundleId) {
        // TODO Auto-generated method stub
        return dao.getListInAppCategoryforOneDepth(DBName, storeBundleId);
    }

    public int categoryIsDuplicated(String storeBundleId, String categoryName) {
        // TODO Auto-generated method stub
        return dao.categoryIsDuplicated(storeBundleId, categoryName);
    }
}
