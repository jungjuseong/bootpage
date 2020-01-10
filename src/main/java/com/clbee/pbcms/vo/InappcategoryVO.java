package com.clbee.pbcms.vo;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * The persistent class for the tb_inappcategory database table.
 *
 */
@Getter
@Setter
@Entity
@Table(name="tb_inappcategory")
public class InappcategoryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="category_seq")
    private Integer categorySeq;

    @Column(name="store_bundle_id")
    private String storeBundleId;

    @Column(name="depth")
    private String depth;

    @Column(name="category_name")
    private String categoryName;

    @Column(name="category_parent")
    private Integer categoryParent;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="chg_dt")
    private Date chgDt;

    @Column(name="chg_user_gb")
    private String chgUserGb;

    @Column(name="chg_user_id")
    private String chgUserId;

    @Column(name="chg_user_seq")
    private Integer chgUserSeq;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="reg_dt")
    private Date regDt;

    @Column(name="reg_user_gb")
    private String regUserGb;

    @Column(name="reg_user_id")
    private String regUserId;

    @Column(name="reg_user_seq")
    private Integer regUserSeq;

    public InappcategoryVO() {
    }

    public void setInappcategoryVO( InappcategoryVO updatedVO) {
        this.storeBundleId = updatedVO.getStoreBundleId();
        this.categoryName = updatedVO.getCategoryName();
        this.depth = updatedVO.getDepth();
        this.categoryParent = updatedVO.getCategoryParent();
        this.chgDt = updatedVO.getChgDt();
        this.chgUserGb = updatedVO.getChgUserGb();
        this.chgUserId = updatedVO.getChgUserId();
        this.chgUserSeq = updatedVO.getChgUserSeq();
        this.regDt = updatedVO.getRegDt();
        this.regUserGb = updatedVO.getRegUserGb();
        this.regUserId = updatedVO.getRegUserId();
        this.regUserSeq = updatedVO.getRegUserSeq();
    }
}
