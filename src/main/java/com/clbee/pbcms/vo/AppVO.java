package com.clbee.pbcms.vo;

import com.clbee.pbcms.Json.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Getter
@Setter
@Entity
@Table(name = "tb_app")
@DynamicInsert(true)
@DynamicUpdate(true)
public class AppVO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_seq")
    private Integer appSeq;
    @Column(name = "app_contents_amt")
    private String appContentsAmt;
    @Column(name = "app_contents_gb")
    private String appContentsGb;
    @Column(name = "app_name")
    private String appName;
    private String app_resultCode;
    @Column(name = "app_size")
    private String appSize;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "chg_dt")
    private Date chgDt;
    @Lob
    @Column(name = "chg_text")
    private String chgText;
    @Column(name = "chg_user_gb")
    private String chgUserGb;
    @Column(name = "chg_user_id")
    private String chgUserId;
    @Column(name = "chg_user_seq")
    private Integer chgUserSeq;
    @Column(name = "complet_gb")
    private String completGb;
    @Column(name = "coupon_gb")
    private String couponGb;
    @Column(name = "coupon_num")
    private String couponNum;
    @Lob
    @Column(name = "description_text")
    private String descriptionText;
    @Column(name = "distr_gb")
    private String distrGb;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "icon_org_file")
    private String iconOrgFile;
    @Column(name = "icon_save_file")
    private String iconSaveFile;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "limit_dt")
    private Date limitDt;
    @Column(name = "limit_gb")
    private String limitGb;
    @Column(name = "mem_down_amt")
    private String memDownAmt;
    @Column(name = "mem_down_cnt")
    private String memDownCnt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "mem_down_end_dt")
    private Date memDownEndDt;
    @Column(name = "mem_down_gb")
    private String memDownGb;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "mem_down_start_dt")
    private Date memDownStartDt;
    @Column(name = "nonmem_down_amt")
    private String nonmemDownAmt;
    @Column(name = "nonmem_down_cnt")
    private String nonmemDownCnt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "nonmem_down_end_dt")
    private Date nonmemDownEndDt;
    @Column(name = "nonmem_down_gb")
    private String nonmemDownGb;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "nonmem_down_star_dt")
    private Date nonmemDownStarDt;
    @Column(name = "install_gb")
    private String installGb;
    private String ostype;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reg_dt")
    private Date regDt;
    @Column(name = "reg_gb")
    private String regGb;
    @Column(name = "reg_user_gb")
    private String regUserGb;
    @Column(name = "reg_user_id")
    private String regUserId;
    @Column(name = "reg_user_seq")
    private Integer regUserSeq;
    @Column(name = "store_bundle_id")
    private String storeBundleId;
    @Column(name = "provision_gb")
    private String provisionGb;
    @Column(name = "template_name")
    @Formula("(select tb_template.template_name from tb_template where tb_template.template_seq = template_seq limit 1)")
    private String templateName;
    @Column(name = "template_seq")
    private Integer templateSeq;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "use_avail_dt")
    private Date useAvailDt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "use_disable_dt")
    private Date useDisableDt;
    @Column(name = "use_gb")
    private String useGb;
    @Column(name = "ver_num")
    private String verNum;
    @Column(name = "version_code")
    private String versionCode;
    @Column(name = "use_user_gb")
    private String useUserGb;
    @Column(name = "login_time")
    private String loginTime;
    @Column(name = "logout_time")
    private String logoutTime;
    @Column(name = "login_gb")
    private String loginGb;
    @Column(name="app_schema")
    private String appSchema;
    @Column(name="app_host")
    private String appHost;

    //20180403 : lsy - add column complet_dt, chg_contents_dt
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "distribute_req_dt")
    private Date distributeReqDt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "chg_contents_dt")
    private Date chgContentsDt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "distribute_complet_dt")
    private Date distributeCompletDt;

    @Column(name="distribute_accept_id")
    private String distributeAcceptId;

    @Column(name="distribute_request_id")
    private String distributeRequestId;
    //20180403 : lsy - add column complet_dt, chg_contents_dt - end

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(optional = true)
    @JoinColumn(nullable = true, name = "reg_user_seq", referencedColumnName = "user_seq", insertable = false, updatable = false)
    private MemberVO regMemberVO;
    @NotFound(action = NotFoundAction.IGNORE)
    @OneToMany
    @Cascade({ org.hibernate.annotations.CascadeType.DELETE })
    @JoinColumn(nullable = true, name = "app_seq", referencedColumnName = "app_seq", insertable = false, updatable = false)
    private List<AppSubVO> appSubVO;
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(optional = true)
    @JoinColumn(nullable = true, name = "chg_user_seq", referencedColumnName = "user_seq", insertable = false, updatable = false)
    private MemberVO chgMemberVO;

    public AppVO(LinkedHashMap<Object, Object> map) throws ParseException {
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        appSeq = Integer.valueOf(((Long) map.get("appSeq")).intValue());
        appContentsAmt = ((String) map.get("appContentsAmt"));
        appContentsGb = ((String) map.get("appContentsGb"));
        appName = ((String) map.get("appName"));
        app_resultCode = ((String) map.get("app_resultCode"));
        appSize = ((String) map.get("appSize"));
        if ((String) map.get("chgDt") != null)
            chgDt = transFormat.parse((String) map.get("chgDt"));
        chgText = ((String) map.get("chgText"));
        chgUserGb = ((String) map.get("chgUserGb"));
        chgUserId = ((String) map.get("chgUserId"));
        chgUserSeq = Integer.valueOf(((Long) map.get("chgUserSeq")).intValue());
        completGb = ((String) map.get("completGb"));
        couponGb = ((String) map.get("couponGb"));
        couponNum = ((String) map.get("couponNum"));
        descriptionText = ((String) map.get("descriptionText"));
        distrGb = ((String) map.get("distrGb"));
        fileName = ((String) map.get("fileName"));
        iconOrgFile = ((String) map.get("iconOrgFile"));
        iconSaveFile = ((String) map.get("iconSaveFile"));
        if ((String) map.get("limitDt") != null)
            limitDt = transFormat.parse((String) map.get("limitDt"));
        limitGb = ((String) map.get("limitGb"));
        memDownAmt = ((String) map.get("memDownAmt"));
        memDownCnt = ((String) map.get("memDownCnt"));
        if ((String) map.get("memDownEndDt") != null)
            memDownEndDt = transFormat.parse((String) map.get("memDownEndDt"));
        memDownGb = ((String) map.get("memDownGb"));
        if ((String) map.get("memDownStartDt") != null)
            memDownStartDt = transFormat.parse((String) map.get("memDownStartDt"));
        nonmemDownAmt = ((String) map.get("nonmemDownAmt"));
        nonmemDownCnt = ((String) map.get("nonmemDownCnt"));
        if ((String) map.get("nonmemDownEndDt") != null)
            nonmemDownEndDt = transFormat.parse((String) map.get("nonmemDownEndDt"));
        nonmemDownGb = ((String) map.get("nonmemDownGb"));
        if ((String) map.get("nonmemDownStarDt") != null)
            nonmemDownStarDt = transFormat.parse((String) map.get("nonmemDownStarDt"));
        installGb = ((String) map.get("installGb"));
        ostype = ((String) map.get("ostype"));
        if ((String) map.get("regDt") != null)
            regDt = transFormat.parse((String) map.get("regDt"));
        regGb = ((String) map.get("regGb"));
        regUserGb = ((String) map.get("regUserGb"));
        regUserId = ((String) map.get("regUserId"));
        regUserSeq = Integer.valueOf(((Long) map.get("regUserSeq")).intValue());
        storeBundleId = ((String) map.get("storeBundleId"));
        provisionGb = ((String) map.get("provisionGb"));
        templateName = ((String) map.get("templateName"));
        templateSeq = Integer.valueOf(((Long) map.get("templateSeq")).intValue());
        if ((String) map.get("useAvailDt") != null)
            useAvailDt = transFormat.parse((String) map.get("useAvailDt"));
        if ((String) map.get("useDisableDt") != null)
            useDisableDt = transFormat.parse((String) map.get("useDisableDt"));
        useGb = ((String) map.get("useGb"));
        verNum = ((String) map.get("verNum"));
        versionCode = ((String) map.get("versionCode"));
        useUserGb = ((String) map.get("useUserGb"));
        loginGb = ((String) map.get("loginGb"));
        loginTime = ((String) map.get("loginTime"));
        logoutTime = ((String) map.get("logoutTime"));
        this.appSchema = (String)map.get("appSchema");
        this.appHost = (String)map.get("appHost");

        //20180403 : lsy - add column complet_dt, chg_contents_dt
        if ((String) map.get("distributeReqDt") != null) {
            distributeReqDt = transFormat.parse((String) map.get("distributeReqDt"));
        }
        if ((String) map.get("chgContentsDt") != null) {
            chgContentsDt = transFormat.parse((String) map.get("chgContentsDt"));
        }
        if ((String) map.get("distributeCompletDt") != null) {
            distributeCompletDt = transFormat.parse((String) map.get("distributeCompletDt"));
        }
        distributeAcceptId = ((String) map.get("distributeAcceptId"));
        distributeRequestId = ((String) map.get("distributeRequestId"));
        //20180403 : lsy - add column complet_dt, chg_contents_dt - end
    }

    public void setAppVO(AppVO updatedVO) {
        appContentsAmt = updatedVO.getAppContentsAmt();
        appContentsGb = updatedVO.getAppContentsGb();
        appName = updatedVO.getAppName();
        app_resultCode = updatedVO.getApp_resultCode();
        appSize = updatedVO.getAppSize();
        chgDt = updatedVO.getChgDt();
        chgText = updatedVO.getChgText();
        chgUserGb = updatedVO.getChgUserGb();
        chgUserId = updatedVO.getChgUserId();
        chgUserSeq = updatedVO.getChgUserSeq();
        completGb = updatedVO.getCompletGb();
        couponGb = updatedVO.getCouponGb();
        couponNum = updatedVO.getCouponNum();
        descriptionText = updatedVO.getDescriptionText();
        distrGb = updatedVO.getDistrGb();
        fileName = updatedVO.getFileName();
        iconOrgFile = updatedVO.getIconOrgFile();
        iconSaveFile = updatedVO.getIconSaveFile();
        limitDt = updatedVO.getLimitDt();
        limitGb = updatedVO.getLimitGb();
        memDownAmt = updatedVO.getMemDownAmt();
        memDownCnt = updatedVO.getMemDownCnt();
        memDownEndDt = updatedVO.getMemDownEndDt();
        memDownGb = updatedVO.getMemDownGb();
        memDownStartDt = updatedVO.getMemDownStartDt();
        nonmemDownAmt = updatedVO.getNonmemDownAmt();
        nonmemDownCnt = updatedVO.getNonmemDownCnt();
        nonmemDownEndDt = updatedVO.getNonmemDownEndDt();
        nonmemDownGb = updatedVO.getNonmemDownGb();
        nonmemDownStarDt = updatedVO.getNonmemDownStarDt();
        installGb = updatedVO.getInstallGb();
        ostype = updatedVO.getOstype();
        regDt = updatedVO.getRegDt();
        regGb = updatedVO.getRegGb();
        regUserGb = updatedVO.getRegUserGb();
        regUserId = updatedVO.getRegUserId();
        regUserSeq = updatedVO.getRegUserSeq();
        storeBundleId = updatedVO.getStoreBundleId();
        provisionGb = updatedVO.getProvisionGb();
        templateName = updatedVO.getTemplateName();
        templateSeq = updatedVO.getTemplateSeq();
        useAvailDt = updatedVO.getUseAvailDt();
        useDisableDt = updatedVO.getUseDisableDt();
        useGb = updatedVO.getUseGb();
        verNum = updatedVO.getVerNum();
        versionCode = updatedVO.getVersionCode();
        useUserGb = updatedVO.getUseUserGb();
        loginGb = updatedVO.getLoginGb();
        loginTime = updatedVO.getLoginTime();
        logoutTime = updatedVO.getLogoutTime();
        this.appSchema = updatedVO.getAppSchema();
        this.appHost = updatedVO.getAppHost();

        //20180403 : lsy - add column complet_dt, chg_contents_dt
        distributeReqDt = updatedVO.getDistributeReqDt();
        chgContentsDt = updatedVO.getChgContentsDt();
        distributeCompletDt = updatedVO.getDistributeCompletDt();
        distributeAcceptId = updatedVO.getDistributeAcceptId();
        distributeRequestId = updatedVO.getDistributeRequestId();
        //20180403 : lsy - add column complet_dt, chg_contents_dt - end
    }

    public String toString() {
        return

                "AppVO [appSeq=" + appSeq + ", appContentsAmt=" + appContentsAmt + ", appContentsGb=" + appContentsGb
                        + ", appName=" + appName + ", app_resultCode=" + app_resultCode + ", appSize=" + appSize + ", chgDt="
                        + chgDt + ", chgText=" + chgText + ", chgUserGb=" + chgUserGb + ", chgUserId=" + chgUserId
                        + ", chgUserSeq=" + chgUserSeq + ", completGb=" + completGb + ", couponGb=" + couponGb + ", couponNum="
                        + couponNum + ", descriptionText=" + descriptionText + ", distrGb=" + distrGb + ", fileName=" + fileName
                        + ", iconOrgFile=" + iconOrgFile + ", iconSaveFile=" + iconSaveFile + ", limitDt=" + limitDt
                        + ", limitGb=" + limitGb + ", memDownAmt=" + memDownAmt + ", memDownCnt=" + memDownCnt
                        + ", memDownEndDt=" + memDownEndDt + ", memDownGb=" + memDownGb + ", memDownStartDt=" + memDownStartDt
                        + ", nonmemDownAmt=" + nonmemDownAmt + ", nonmemDownCnt=" + nonmemDownCnt + ", nonmemDownEndDt="
                        + nonmemDownEndDt + ", nonmemDownGb=" + nonmemDownGb + ", nonmemDownStarDt=" + nonmemDownStarDt
                        + ", ostype=" + ostype + ", regDt=" + regDt + ", regGb=" + regGb + ", regUserGb=" + regUserGb
                        + ", regUserId=" + regUserId + ", regUserSeq=" + regUserSeq + ", storeBundleId=" + storeBundleId
                        + ", provisionGb=" + provisionGb + ", templateName=" + templateName + ", templateSeq=" + templateSeq
                        + ", useAvailDt=" + useAvailDt + ", useDisableDt=" + useDisableDt + ", useGb=" + useGb + ", verNum="
                        + verNum + ", regMemberVO=" + regMemberVO + ", chgMemberVO=" + chgMemberVO+ ", appSchema=" + appSchema
                        + ", appHost=" + appHost + "]";
    }
}
