package com.clbee.pbcms.vo;

import lombok.Data;
import java.util.List;

@Data
public class LicenseList {
    private List<LicenseVO> list;
    private int pageSize;
    private int maxResult;
    private int totalCount;
    private int totalPage;
    private int currentPage;
    private int startNo;
    private int endNo;
    private int startPage;
    private int endPage;

    private String searchMember;
    private String searchStatus;

    public LicenseList() {
        // TODO Auto-generated constructor stub
    }

    public LicenseList(int pageSize, int totalCount, int currentPage, int maxResult) {
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.maxResult = maxResult;
        calc();
    }

    // ������ ������ ����ϱ�
    private void calc(){
        totalPage = (totalCount-1)/maxResult + 1;
        currentPage = currentPage>totalPage ? totalPage : currentPage;
        startNo = (currentPage-1) * pageSize;
        endNo = startNo + pageSize - 1;
        endNo = endNo>totalCount ? totalCount : endNo;
        startPage = ((currentPage-1)/pageSize) * pageSize + 1;
        endPage = startPage + pageSize -1;
        endPage  = endPage>totalPage ? totalPage : endPage;
    }

    public void calc(int pageSize, int totalCount, Integer currentPage, int maxResult){
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.maxResult = maxResult;
        totalPage = (totalCount-1)/maxResult + 1;
        currentPage = currentPage>totalPage ? totalPage : currentPage;
        startNo = (currentPage-1) * maxResult;
        endNo = startNo + maxResult - 1;
        endNo = endNo>totalCount ? totalCount : endNo;
        startPage = ((currentPage-1)/pageSize) * pageSize + 1;
        endPage = startPage + pageSize -1;
        endPage  = endPage>totalPage ? totalPage : endPage;
    }

}
