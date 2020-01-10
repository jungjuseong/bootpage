package com.clbee.pbcms.vo;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="tb_apphistory")
@DynamicInsert(true)
@DynamicUpdate(true)
public class AppHistoryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="app_seq")
    private Integer appSeq;

    @Column(name="app_contents_amt")
    private String appContentsAmt;

    @Column(name="app_contents_gb")
    private String appContentsGb;

    @Column(name="app_name")
    private String appName;

    private String app_resultCode;

    @Column(name="app_size")
    private String appSize;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="chg_dt")
    private Date chgDt;

    @Lob
    @Column(name="chg_text")
    private String chgText;

    @Column(name="chg_user_gb")
    private String chgUserGb;

    @Column(name="chg_user_id")
    private String chgUserId;

    @Column(name="chg_user_seq")
    private Integer chgUserSeq;

    @Column(name="complet_gb")
    private String completGb;

    @Column(name="coupon_gb")
    private String couponGb;

    @Column(name="coupon_num")
    private String couponNum;

    @Lob
    @Column(name="description_text")
    private String descriptionText;

    @Column(name="distr_gb")
    private String distrGb;

    @Column(name="file_name")
    private String fileName;

    @Column(name="icon_org_file")
    private String iconOrgFile;

    @Column(name="icon_save_file")
    private String iconSaveFile;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="limit_dt")
    private Date limitDt;

    @Column(name="limit_gb")
    private String limitGb;

    @Column(name="mem_down_amt")
    private String memDownAmt;

    @Column(name="mem_down_cnt")
    private String memDownCnt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="mem_down_end_dt")
    private Date memDownEndDt;

    @Column(name="mem_down_gb")
    private String memDownGb;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="mem_down_start_dt")
    private Date memDownStartDt;

    @Column(name="nonmem_down_amt")
    private String nonmemDownAmt;

    @Column(name="nonmem_down_cnt")
    private String nonmemDownCnt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="nonmem_down_end_dt")
    private Date nonmemDownEndDt;

    @Column(name="nonmem_down_gb")
    private String nonmemDownGb;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="nonmem_down_star_dt")
    private Date nonmemDownStarDt;

    @Column(name="install_gb")
    private String installGb;

    private String ostype;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="reg_dt")
    private Date regDt;

    @Column(name="reg_gb")
    private String regGb;

    @Column(name="reg_user_gb")
    private String regUserGb;

    @Column(name="reg_user_id")
    private String regUserId;

    @Column(name="reg_user_seq")
    private Integer regUserSeq;

    @Column(name="store_bundle_id")
    /*	@Formula("(select tb_bundle.bundle_name from tb_bundle where tb_bundle.app_seq = app_seq limit 1)")*/
    private String storeBundleId;

    @Column(name="provision_gb")
    private String provisionGb;

    @Column(name="template_name")
    private String templateName;

    @Column(name="template_seq")
    private Integer templateSeq;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="use_avail_dt")
    private Date useAvailDt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="use_disable_dt")
    private Date useDisableDt;

    @Column(name="use_gb")
    private String useGb;

    @Column(name="ver_num")
    private String verNum;

    @Column(name="version_code")
    private String versionCode;

    @Column(name="use_user_gb")
    private String useUserGb;

    @Column(name="login_time")
    private String loginTime;

    @Column(name="logout_time")
    private String logoutTime;

    @Column(name="login_gb")
    private String loginGb;

    @Column(name="app_schema")
    private String appSchema;

    @Column(name="app_host")
    private String appHost;

    //20180403 : lsy - add column complet_dt, chg_contents_dt
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "complet_dt")
    private Date completDt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "chg_contents_dt")
    private Date chgContentsDt;

    @Column(name="distribute_accept_id")
    private String distributeAcceptId;

    @Column(name="distribute_request_id")
    private String distributeRequestId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "distribute_req_dt")
    private Date distributeReqDt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "distribute_complet_dt")
    private Date distributeCompletDt;
    //20180403 : lsy - add column complet_dt, chg_contents_dt - end

    public AppHistoryVO( LinkedHashMap<Object, Object> map) throws ParseException {
        super();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        this.appSeq = ((Long)map.get("appSeq")).intValue();
        this.appContentsAmt = (String)map.get("appContentsAmt");
        this.appContentsGb = (String)map.get("appContentsGb");
        this.appName = (String)map.get("appName");
        this.app_resultCode = (String)map.get("app_resultCode");
        this.appSize = (String)map.get("appSize");
        if((String)map.get("chgDt") != null)
            this.chgDt = transFormat.parse((String)map.get("chgDt"));
        this.chgText = (String)map.get("chgText");
        this.chgUserGb = (String)map.get("chgUserGb");
        this.chgUserId = (String)map.get("chgUserId");
        this.chgUserSeq =((Long)map.get("chgUserSeq")).intValue();
        this.completGb = (String)map.get("completGb");
        this.couponGb = (String)map.get("couponGb");
        this.couponNum = (String)map.get("couponNum");
        this.descriptionText = (String)map.get("descriptionText");
        this.distrGb = (String)map.get("distrGb");
        this.fileName = (String)map.get("fileName");
        this.iconOrgFile = (String)map.get("iconOrgFile");
        this.iconSaveFile = (String)map.get("iconSaveFile");
        if((String)map.get("limitDt") != null)
            this.limitDt = transFormat.parse((String)map.get("limitDt"));
        this.limitGb = (String)map.get("limitGb");
        this.memDownAmt = (String)map.get("memDownAmt");
        this.memDownCnt = (String)map.get("memDownCnt");
        if((String)map.get("memDownEndDt") != null)
            this.memDownEndDt = transFormat.parse((String)map.get("memDownEndDt"));
        this.memDownGb = (String)map.get("memDownGb");
        if((String)map.get("memDownStartDt") != null)
            this.memDownStartDt = transFormat.parse((String)map.get("memDownStartDt"));
        this.nonmemDownAmt = (String)map.get("nonmemDownAmt");
        this.nonmemDownCnt = (String)map.get("nonmemDownCnt");
        if((String)map.get("nonmemDownEndDt") != null)
            this.nonmemDownEndDt = transFormat.parse((String)map.get("nonmemDownEndDt"));
        this.nonmemDownGb = (String)map.get("nonmemDownGb");
        if((String)map.get("nonmemDownStarDt") != null)
            this.nonmemDownStarDt = transFormat.parse((String)map.get("nonmemDownStarDt"));
        this.installGb = (String)map.get("installGb");
        this.ostype = (String)map.get("ostype");
        if((String)map.get("regDt") != null)
            this.regDt = transFormat.parse((String)map.get("regDt"));
        this.regGb = (String)map.get("regGb");
        this.regUserGb = (String)map.get("regUserGb");
        this.regUserId = (String)map.get("regUserId");
        this.regUserSeq = ((Long)map.get("regUserSeq")).intValue();
        this.storeBundleId = (String)map.get("storeBundleId");
        this.provisionGb = (String)map.get("provisionGb");
        this.templateName = (String)map.get("templateName");
        if(map.get("templateSeq") != null)
            this.templateSeq = ((Long)map.get("templateSeq")).intValue();
        if((String)map.get("useAvailDt") != null)
            this.useAvailDt = transFormat.parse((String)map.get("useAvailDt"));
        if((String)map.get("useDisableDt") != null)
            this.useDisableDt = transFormat.parse((String)map.get("useDisableDt"));
        this.useGb = (String)map.get("useGb");
        this.verNum = (String)map.get("verNum");
        this.versionCode = (String)map.get("versionCode");
        this.useUserGb = (String)map.get("useUserGb");
        this.loginGb = (String)map.get("loginGb");
        this.loginTime = (String)map.get("loginTime");
        this.logoutTime = (String)map.get("logoutTime");
        this.appSchema = (String)map.get("appSchema");
        this.appHost = (String)map.get("appHost");

        //20180403 : lsy - add column complet_dt, chg_contents_dt
        if ((String) map.get("completDt") != null) {
            completDt = transFormat.parse((String) map.get("completDt"));
        }
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

    public void setAppVO( AppHistoryVO updatedVO){

        this.appContentsAmt = updatedVO.getAppContentsAmt();
        this.appContentsGb = updatedVO.getAppContentsGb();
        this.appName = updatedVO.getAppName();
        this.app_resultCode = updatedVO.getApp_resultCode();
        this.appSize = updatedVO.getAppSize();
        this.chgDt = updatedVO.getChgDt();
        this.chgText = updatedVO.getChgText();
        this.chgUserGb = updatedVO.getChgUserGb();
        this.chgUserId = updatedVO.getChgUserId();
        this.chgUserSeq = updatedVO.getChgUserSeq();
        this.completGb = updatedVO.getCompletGb();
        this.couponGb = updatedVO.getCouponGb();
        this.couponNum = updatedVO.getCouponNum();
        this.descriptionText = updatedVO.getDescriptionText();
        this.distrGb = updatedVO.getDistrGb();
        this.fileName = updatedVO.getFileName();
        this.iconOrgFile = updatedVO.getIconOrgFile();
        this.iconSaveFile = updatedVO.getIconSaveFile();
        this.limitDt = updatedVO.getLimitDt();
        this.limitGb = updatedVO.getLimitGb();
        this.memDownAmt = updatedVO.getMemDownAmt();
        this.memDownCnt = updatedVO.getMemDownCnt();
        this.memDownEndDt = updatedVO.getMemDownEndDt();
        this.memDownGb = updatedVO.getMemDownGb();
        this.memDownStartDt = updatedVO.getMemDownStartDt();
        this.nonmemDownAmt = updatedVO.getNonmemDownAmt();
        this.nonmemDownCnt = updatedVO.getNonmemDownCnt();
        this.nonmemDownEndDt = updatedVO.getNonmemDownEndDt();
        this.nonmemDownGb = updatedVO.getNonmemDownGb();
        this.nonmemDownStarDt = updatedVO.getNonmemDownStarDt();
        this.installGb = updatedVO.getInstallGb();
        this.ostype = updatedVO.getOstype();
        this.regDt = updatedVO.getRegDt();
        this.regGb = updatedVO.getRegGb();
        this.regUserGb = updatedVO.getRegUserGb();
        this.regUserId = updatedVO.getRegUserId();
        this.regUserSeq = updatedVO.getRegUserSeq();
        this.storeBundleId = updatedVO.getStoreBundleId();
        this.provisionGb = updatedVO.getProvisionGb();
        this.templateName = updatedVO.getTemplateName();
        this.templateSeq = updatedVO.getTemplateSeq();
        this.useAvailDt = updatedVO.getUseAvailDt();
        this.useDisableDt = updatedVO.getUseDisableDt();
        this.useGb = updatedVO.getUseGb();
        this.verNum = updatedVO.getVerNum();
        this.versionCode = updatedVO.getVersionCode();
        this.useUserGb = updatedVO.getUseUserGb();
        this.loginGb = updatedVO.getLoginGb();
        this.loginTime = updatedVO.getLoginTime();
        this.logoutTime = updatedVO.getLogoutTime();
        this.appSchema = updatedVO.getAppSchema();
        this.appHost = updatedVO.getAppHost();

        //20180417 : lsy - add column distribute_accept_id
        this.distributeReqDt = updatedVO.getDistributeReqDt();
        this.chgContentsDt = updatedVO.getChgContentsDt();
        this.distributeCompletDt = updatedVO.getDistributeCompletDt();
        this.distributeAcceptId = updatedVO.getDistributeAcceptId();
        this.distributeRequestId = updatedVO.getDistributeRequestId();
        //20180417 : lsy - add column distribute_accept_id - end
    }

}
