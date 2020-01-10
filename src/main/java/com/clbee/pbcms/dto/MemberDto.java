package com.clbee.pbcms.dto;

import com.clbee.pbcms.vo.CompanyVO;
import com.clbee.pbcms.vo.MemberVO;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {
    int userSeq;
    Date chgDt;
    String chgIp;
    String companyGb;
    int companySeq;
    String email;
    Date emailChkDt;
    String emailChkGb;
    String emailChkSession;
    String firstName;
    String lastName;
    Date loginDt;
    String phone;
    Date regDt;
    String regIp;
    String userGb;
    String userId;
    String userPw;
    String userStatus;
    Date withdrawalDt;
    Integer onedepartmentSeq;
    Integer twodepartmentSeq;
    Date userStartDt;
    Date userEndDt;
    String dateGb;
    String sessionId;
    String loginStatus;
    String loginDeviceuuid;
    String groupName;
    CompanyVO companyVO;
    String onedepartmentName;
    String twodepartmentName;
    String userGroup;

    public MemberVO toEntity(){
        return MemberVO.builder()
		.userSeq(userSeq)
		.chgDt(chgDt)
		.chgIp(chgIp)
		.companyGb(companyGb)
		.companySeq(companySeq)
		.email(email)
		.emailChkDt(emailChkDt)
		.emailChkGb(emailChkGb)
		.emailChkSession(emailChkSession)
		.firstName(firstName)
		.lastName(lastName)
		.loginDt(loginDt)
		.phone(phone)
		.regDt(regDt)
		.regIp(regIp)
		.userGb(userGb)
		.userId(userId)
		.userPw(userPw)
		.userStatus(userStatus)
		.withdrawalDt(withdrawalDt)
		.onedepartmentSeq(onedepartmentSeq)
		.twodepartmentSeq(twodepartmentSeq)
		.userStartDt(userStartDt)
		.userEndDt(userEndDt)
		.dateGb(dateGb)
		.sessionId(sessionId)
		.loginStatus(loginStatus)
		.loginDeviceuuid(loginDeviceuuid)
		.groupName(groupName)
		.companyVO(companyVO)
		.onedepartmentName(onedepartmentName)
		.twodepartmentName(twodepartmentName)
		.userGroup(userGroup)
        .build();
    }

    @Builder
    public MemberDto(int userSeq, Date chgDt, String chgIp, String companyGb, int companySeq,
                    String email, Date emailChkDt, String emailChkGb, String emailChkSession, String firstName,
                    String lastName, Date loginDt, String phone, Date regDt, String regIp, String userGb,
                    String userId, String userPw, String userStatus, Date withdrawalDt, Integer onedepartmentSeq, Integer twodepartmentSeq, Date userStartDt,
                    Date userEndDt, String dateGb, String sessionId, String loginStatus, String loginDeviceuuid, String groupName,
                    CompanyVO companyVO, String onedepartmentName, String twodepartmentName, String userGroup) {

        this.userSeq = userSeq;
        this.chgDt = chgDt;
        this.chgIp = chgIp;
        this.companyGb = companyGb;
        this.companySeq = companySeq;
        this.email = email;
        this.emailChkDt = emailChkDt;
        this.emailChkGb = emailChkGb;
        this.emailChkSession = emailChkSession;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginDt = loginDt;
        this.phone = phone;
        this.regDt = regDt;
        this.regIp = regIp;
        this.userGb = userGb;
        this.userId = userId;
        this.userPw = userPw;
        this.userStatus = userStatus;
        this.withdrawalDt = withdrawalDt;
        this.onedepartmentSeq = onedepartmentSeq;
        this.twodepartmentSeq = twodepartmentSeq;
        this.userStartDt = userStartDt;
        this.userEndDt = userEndDt;
        this.dateGb = dateGb;
        this.sessionId = sessionId;
        this.loginStatus = loginStatus;
        this.loginDeviceuuid = loginDeviceuuid;
        this.groupName = groupName;
        this.companyVO = companyVO;
        this.onedepartmentName = onedepartmentName;
        this.twodepartmentName = twodepartmentName;
        this.userGroup = userGroup;
    }
}