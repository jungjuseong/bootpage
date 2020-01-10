package com.clbee.pbcms.service;

import com.clbee.pbcms.dao.CompanyDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clbee.pbcms.vo.CompanyVO;
import com.clbee.pbcms.vo.MemberVO;

@AllArgsConstructor
@Service
@Transactional
public class CompanyService {

    private CompanyDao dao;

    public String id_overlap_chk(String id) {
        // TODO Auto-generated method stub
        return dao.id_overlap_chk(id);
    }

    public CompanyVO findByCustomInfo(String DBName, String value) {

        return dao.findByCustomInfo(DBName, value);
    }

    public CompanyVO findByCustomInfo(String DBName, int value) {

        return dao.findByCustomInfo(DBName, value);
    }

    public String sendEmailForId(String lastName, String firstName, String email){
        return dao.selectIdByUserNameAndEmail(lastName, firstName, email);
    }

    public String send_pw_mail_service(String myId, String myMail){
        return dao.send_pw_mail(myId, myMail);
    }

    public CompanyVO getCompanyInfo(String companyID) {
        return dao.getComInfo(companyID);
    }

    public int updateCompanyInfo( CompanyVO companyVO, int companySeq) {
        return dao.updateCompanyInfo( companyVO , companySeq );
    }

    public String changePwChk(MemberVO m, String userID, String inputPW) {
        return dao.changePwChk(m, userID, inputPW);
    }

    public CompanyVO selectByCompanyId(String companyId) {
        // TODO Auto-generated method stub
        return dao.selectByCompanyId(companyId);
    }

    public int insertCompanyInfoWithProcedure(CompanyVO companyVO) {
        // TODO Auto-generated method stub
        return dao.insertCompanyInfoWithProcedure(companyVO);
    }
}
