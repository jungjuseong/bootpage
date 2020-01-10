package com.clbee.pbcms.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

@Data
@Entity
@Table(name="tb_license")
@DynamicInsert(true)
public class LicenseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="license_seq")
    private Integer licenseSeq;

    @Column(name="license_num")
    private String licenseNum;

    @Column(name="license_status")
    private String licenseStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "generate_dt")
    private Date generateDt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "regist_dt")
    private Date registDt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expire_dt")
    private Date expireDt;

    @Column(name="user_copy_count")
    private Integer userCopyCount;

    @Column(name="license_user_seq")
    private Integer licenseUserSeq;

    @Column(name="license_gb")
    private String licenseGb;

    @Column(name="period_gb")
    private String periodGb;

    @Lob
    @Column(name="disposal_reason")
    private String disposalReason;

    private String userId;
    private int remainDt;
    private int licenseUser;

}
