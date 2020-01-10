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
@Table(name="tb_inapp_meta")
@DynamicInsert(true)
@DynamicUpdate(true)
public class InappMetaVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="inappmeta_seq")
    private Integer inappMetaSeq;

    @Column(name="inapp_seq")
    private Integer inappSeq;

    @Column(name="inappmeta_subtitle")
    private String inappMetaSubtitle;

    @Column(name="inappmeta_author")
    private String inappMetaAuthor;

    @Column(name="inappmeta_translator")
    private String inappMetaTranslator;

    @Column(name="inappmeta_page")
    private String inappMetaPage;

    @Column(name="inappmeta_ISBN")
    private String inappMetaISBN;

    @Column(name="inappmeta_status")
    private String inappMetaStatus;

    @Column(name="inappmeta_price")
    private String inappMetaPrice;

    @Column(name="inappmeta_distributor")
    private String inappMetaDistributor;

    @Column(name="inappmeta_size")
    private String inappMetaSize;

    @Column(name="inappmeta_description")
    private String inappMetaDescription;

    @Column(name="inappmeta_buildflag")
    private String inappMetaBuildflag;

    @Column(name="inappmeta_cover1")
    private String inappMetaCover1;

    @Column(name="inappmeta_cover2")
    private String inappMetaCover2;

    @Column(name="inappmeta_cover3")
    private String inappMetaCover3;

    @Column(name="inappmeta_cover4")
    private String inappMetaCover4;

    @Column(name="inappmeta_body")
    private String inappMetaBody;

    @Override
    public String toString() {
        return "InappMetaVO [inappMetaSeq=" + inappMetaSeq + ", inappSeq="
                + inappSeq
                + ", inappMetaSubtitle=" + inappMetaSubtitle
                + ", inappMetaAuthor=" + inappMetaAuthor
                + ", inappMetaTranslator=" + inappMetaTranslator
                + ", inappMetaPage=" + inappMetaPage + ", inappMetaISBN="
                + inappMetaISBN + ", inappMetaStatus=" + inappMetaStatus
                + ", inappMetaPrice=" + inappMetaPrice
                + ", inappMetaDistributor=" + inappMetaDistributor
                + ", inappMetaSize=" + inappMetaSize
                + ", inappMetaDescription=" + inappMetaDescription
                + ", inappMetaBuildflag=" + inappMetaBuildflag
                + ", inappMetaCover1=" + inappMetaCover1 + ", inappMetaCover2="
                + inappMetaCover2 + ", inappMetaCover3=" + inappMetaCover3
                + ", inappMetaCover4=" + inappMetaCover4 + ", inappMetaBody="
                + inappMetaBody + "]";
    }
}
