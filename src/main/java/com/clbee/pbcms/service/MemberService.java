package com.clbee.pbcms.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.clbee.pbcms.dao.MemberDao;
import com.clbee.pbcms.domain.Role;
import com.clbee.pbcms.dto.MemberDto;
import com.clbee.pbcms.security.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.clbee.pbcms.vo.MemberList;
import com.clbee.pbcms.vo.MemberVO;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

    private MemberDao memberDao;

    @Transactional
    public void joinUser(MemberDto memberDto) {
        // 비밀번호 암호화
        memberDto.setUserPw(PasswordEncoder.changeSHA256(memberDto.getUserPw()));
        addMember(memberDto.toEntity());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberVO member = findByUserName(username);

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (member.getUserStatus() == "4") {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN_SERVICE.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.COMPANY_USER.getValue()));
        }

        return new User(member.getUserId(), member.getUserPw(), authorities);
    }

    public void addMember(MemberVO member) {
        memberDao.addMember(member);
    }

    public int verifyIfExists(String DBName, String itSelf){
        return memberDao.selectItselfForExisting(DBName, itSelf);
    }

    public int logInVerify(String username, String password){
        List<MemberVO> appList = memberDao.logInVerify(username, password);

        Iterator iter = appList.iterator();

        if(iter.hasNext()) {
            MemberVO memberVO =  (MemberVO)iter.next();
            if("1".equals(memberVO.getUserStatus())){
                // Ż��
                return 3;
            }else if("2".equals(memberVO.getUserStatus())) {
                // ����
                return 4;
            }else if("3".equals(memberVO.getUserStatus())) {
                // ���� Ż��
                return 5;
            }else if("5".equals(memberVO.getUserStatus())) {
                // ��� ���
                return 6;
            }else if("4".equals(memberVO.getUserStatus())) {

                if(!"1".equals(memberVO.getUserGb())) {
                    return 1;
                }else {
                    if("2".equals(memberVO.getDateGb())) {
                        return 1;
                    }else{
                        try {
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
                            DateFormat inputDF  = new SimpleDateFormat("MM/dd/yy");
                            Date date = new Date();
                            Date formattedDate;

                            formattedDate = inputDF.parse(format.format(date));


                            Calendar cal = Calendar.getInstance();
                            cal.setTime(formattedDate);

                            int month = cal.get(Calendar.MONTH);
                            int day = cal.get(Calendar.DAY_OF_MONTH);
                            int year = cal.get(Calendar.YEAR);


                            formattedDate = inputDF.parse(format.format(memberVO.getUserStartDt()));

                            cal.setTime(formattedDate);
                            int stMonth = cal.get(Calendar.MONTH);
                            int stDay = cal.get(Calendar.DAY_OF_MONTH);
                            int stYear = cal.get(Calendar.YEAR);

                            formattedDate = inputDF.parse(format.format(memberVO.getUserEndDt()));

                            cal.setTime(formattedDate);
                            int endMonth = cal.get(Calendar.MONTH);
                            int endDay = cal.get(Calendar.DAY_OF_MONTH);
                            int endYear = cal.get(Calendar.YEAR);

                            // 1. year(���� �⵵)�� stYear���� Ŭ��� month�� day�� �ƹ�����̾���
                            // 2. year�� stYear�� ������� month�� ����
                            // 3. �̶� month�� stMonth���� Ŭ��� day�� �ƹ������ ����
                            // 4. month�� stMonth�� ������� stDay�� ����
                            // endYear�� 1, 2, 3, 4 �� ����
                            if(year > stYear || (year == stYear && (month > stMonth || (month == stMonth && day >= stDay)))){
                                if(year < endYear || (year == endYear && (month < endMonth || (month == endMonth && day <= endDay)))){
                                    return 1;
                                }else {
                                    return 2;
                                }
                            }else {
                                return 2;
                            }
                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            return -1;
                        }
                    }
                }
            }else {
                return -1;
            }
        }else {
            return 7;
        }
    }

    public MemberVO findByUserName(String username){
        return memberDao.findByUserName(username);
    }

    public MemberVO findByCustomInfo(String DBName, String value){
        return memberDao.findByCustomInfo(DBName, value);
    }

    public int updateMemberInfo(MemberVO m, int userNum){
        return memberDao.updateMemberInfo(m, userNum);
    }

    public MemberVO selectMemberSuccessYn(MemberVO memberVO) {
        return memberDao.selectMemberSuccessYn(memberVO);
    }

    public Integer selectMemberCount(MemberVO memberVO) {
        return memberDao.selectMemberCount(memberVO);
    }

    public MemberVO selectMemberSuccessYn_(MemberVO memberVO) {
        return memberDao.selectMemberSuccessYn_(memberVO);
    }

    public Integer selectMemberCount_(MemberVO memberVO) {
        return memberDao.selectMemberCount_(memberVO);
    }

    public void updateMemberPw(MemberVO memberVO) {
        memberDao.updateMemberPw(memberVO);
    }

    public MemberList getListMember(int currentPage, int companySeq, int maxResult, String searchType, String searchValue, String isAvailable, boolean isMember) {

        MemberList list = null;
        int pageSize = 10;
        int totalCount = 0;
        int startNo = 0;
        try{
            totalCount = memberDao.getListMemberCount(companySeq, searchType, searchValue, isAvailable, isMember);
            startNo = (currentPage - 1) * maxResult;
            List<MemberVO> vo = memberDao.getListMember(startNo, companySeq, maxResult, searchType, searchValue, isAvailable, isMember);
            list = new MemberList(pageSize, totalCount, currentPage, maxResult);
            list.setList(vo);
        }catch(Exception e){
            System.out.println("����");
            e.printStackTrace();
        }
        return list;
    }

    public MemberVO findByCustomInfo(String DBName, int value) {
        return memberDao.findByCustomInfo(DBName, value);
    }

    public String findCompanyMemberIdByCompanySeqAndUserGb(int companySeq){

        MemberVO memberVO = memberDao.findCompanyMemberIdByCompanySeqAndUserGb(companySeq);

        if (memberVO != null)
            return memberVO.getUserId();
        else return "";
    }

    public int selectCountWithPermisionUserByCompanySeq(int companySeq){
        return memberDao.selectCountWithPermisionUserByCompanySeq(companySeq);
    }

    public List<MemberVO> getUserList(int companySeq, String[] useS, String searchValue, String searchType ) {
        // TODO Auto-generated method stub
        return memberDao.getUserList(companySeq, useS, searchValue, searchType);
    }

    public List<MemberVO> getPermitList(int companySeq, String[] useS) {
        // TODO Auto-generated method stub
        return memberDao.getPermitList(companySeq, useS);
    }

    //20180213 - lsy : user delete

    public int deleteMemberInfo(int value) {
        return memberDao.deleteMemberInfo(value);
    }

    //20180619 - lsy : when app request(Library), load inapp info

    public int getCompanySeqForInappContentCopy(int userSeq) {
        // TODO Auto-generated method stub
        return memberDao.getCompanySeqForInappContentCopy("getCompanySeqForInappContentCopy", userSeq);
    }

    public String selectCompanyName(int company_seq) {
        // TODO Auto-generated method stub
        return memberDao.selectCompanyName("selectCompanyName", company_seq);
    }
}
