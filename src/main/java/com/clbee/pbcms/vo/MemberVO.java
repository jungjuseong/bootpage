package com.clbee.pbcms.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the tb_member database table.
 *
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tb_member")
@DynamicInsert(true)
@DynamicUpdate(true)
public class MemberVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="user_seq")
    private int userSeq;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="chg_dt")
    private Date chgDt;

    @Column(name="chg_ip")
    private String chgIp;

    @Column(name="company_gb")
    private String companyGb;

    @Column(name="company_seq")
    private int companySeq;

    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="email_chk_dt")
    private Date emailChkDt;

    @Column(name="email_chk_gb")
    private String emailChkGb;

    @Column(name="email_chk_session")
    private String emailChkSession;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="login_dt")
    private Date loginDt;

    private String phone;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="reg_dt")
    private Date regDt;

    @Column(name="reg_ip")
    private String regIp;

    @Column(name="user_gb")
    private String userGb;

    @Column(name="user_id")
    private String userId;

    @Column(name="user_pw")
    private String userPw;

    @Column(name="user_status")
    private String userStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="withdrawal_dt")
    private Date withdrawalDt;

    @Column(name="onedepartment_seq")
    private Integer onedepartmentSeq;

    @Column(name="twodepartment_seq")
    private Integer twodepartmentSeq;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="user_start_dt")
    private Date userStartDt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="user_end_dt")
    private Date userEndDt;

    @Column(name="date_gb")
    private String dateGb;

    @Column(name="session_id")
    private String sessionId;

    @Column(name="login_status")
    private String loginStatus;

    @Column(name="login_deviceuuid")
    private String loginDeviceuuid;

    @Column(name="group_name")
    private String groupName;

    @NotFound( action = NotFoundAction.IGNORE )
    @ManyToOne( optional = true )
    @JoinColumn( nullable=true, name="company_seq",  insertable=false, updatable=false )
    private CompanyVO companyVO;

    @Formula("(select tb_departmentcategory.department_name from tb_departmentcategory where tb_departmentcategory.department_seq = onedepartment_seq limit 1)")
    private String onedepartmentName;

    @Formula("(select tb_departmentcategory.department_name from tb_departmentcategory where tb_departmentcategory.department_seq = twodepartment_seq limit 1)")
    private String twodepartmentName;

    @Formula("(select tb_group_user.group_name from tb_group_user where tb_group_user.group_seq=group_name limit 1)")
    private String userGroup;

    @Builder
    public MemberVO(int userSeq, Date chgDt, String chgIp, String companyGb, int companySeq,
        String email, Date emailChkDt, String emailChkGb, String emailChkSession, String firstName,
        String lastName, Date loginDt, String phone, Date regDt, String regIp, String userGb,
        String userId, String userPw, String userStatus, Date withdrawalDt, Integer onedepartmentSeq, Integer twodepartmentSeq, Date userStartDt,
        Date userEndDt,  String dateGb, String sessionId, String loginStatus, String loginDeviceuuid, String groupName,
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

    public void setMemberVO(MemberVO updatedVO) {
        this.chgDt = updatedVO.getChgDt();
        this.chgIp = updatedVO.getChgIp();
        this.companyGb = updatedVO.getCompanyGb();
        this.companySeq = updatedVO.getCompanySeq();
        this.email = updatedVO.getEmail();
        this.emailChkDt = updatedVO.getEmailChkDt();
        this.emailChkGb = updatedVO.getEmailChkGb();
        this.emailChkSession = updatedVO.getEmailChkSession();
        this.firstName = updatedVO.getFirstName();
        this.lastName = updatedVO.getLastName();
        this.loginDt = updatedVO.getLoginDt();
        this.phone = updatedVO.getPhone();
        this.regDt = updatedVO.getRegDt();
        this.regIp = updatedVO.getRegIp();
        this.userGb = updatedVO.getUserGb();
        this.userId = updatedVO.getUserId();
        this.userPw = updatedVO.getUserPw();
        this.userStatus = updatedVO.getUserStatus();
        this.withdrawalDt = updatedVO.getWithdrawalDt();
        this.onedepartmentSeq = updatedVO.getOnedepartmentSeq();
        this.twodepartmentSeq = updatedVO.getTwodepartmentSeq();
        this.userStartDt = updatedVO.getUserStartDt();
        this.userEndDt = updatedVO.getUserEndDt();
        this.dateGb = updatedVO.getDateGb();
        this.sessionId = updatedVO.getSessionId();
        this.loginStatus = updatedVO.getLoginStatus();
        this.loginDeviceuuid = updatedVO.getLoginDeviceuuid();
        this.groupName = updatedVO.getGroupName();
        this.userGroup = updatedVO.getUserGroup();
    }
}
