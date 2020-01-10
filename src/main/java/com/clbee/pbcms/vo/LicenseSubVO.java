package com.clbee.pbcms.vo;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

@Data
@Entity
@Table(name="tb_license_sub")
@DynamicInsert(true)
public class LicenseSubVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="licensesub_seq")
    private Integer licensesubSeq;

    @Column(name="license_seq")
    private Integer licenseSeq;

    @Column(name="license_user_seq")
    private Integer licenseUserSeq;

    @Column(name="device_name")
    private String deviceName;

    @Column(name="device_os")
    private String deviceOs;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "use_start_dt")
    private Date useStartDt;

    private String userId;


}
