package com.clbee.pbcms.vo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@Entity
@Table(name="tb_inapp_sub")
@DynamicInsert(true)
@DynamicUpdate(true)
public class InappSubVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="inappsub_seq")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer inappsubSeq;

    @Column(name="inapp_seq")
    private Integer inappSeq;

    @Column(name="user_seq")
    private Integer userSeq;

    @Column(name="department_seq")
    private Integer departmentSeq;
}
