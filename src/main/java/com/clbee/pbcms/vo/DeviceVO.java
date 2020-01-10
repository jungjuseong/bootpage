package com.clbee.pbcms.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Getter
@Setter
@Entity
@Table(name="tb_device")
@DynamicInsert(true)
@DynamicUpdate(true)
public class DeviceVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="device_seq")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int deviceSeq;

    @Column(name="company_seq")
    private Integer companySeq;

    @Column(name="device_uuid")
    private String deviceUuid;

    @Column(name="device_info")
    private String deviceInfo;

    @Column(name="device_type")
    private String deviceType;

    @Column(name="use_gb")
    private String useGb;

    @Column(name="reg_user_seq")
    private Integer regUserSeq;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="reg_dt")
    private Date regDt;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(  optional = true)
    @JoinColumn( name="reg_user_seq",  referencedColumnName="user_seq", insertable=false, updatable=false )
    private MemberVO memberVO;
}
