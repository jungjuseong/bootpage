package com.clbee.pbcms.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class InAppList {
    private List<InappVO> list;		// 1페이지 분량
    private int pageSize;			// 페이지당 개수
    private int maxResult;			// 페이지당 리스트 갯수
    private int totalCount;			// 전체 개수
    private int totalPage;			// 전체 페이지
    private Integer currentPage;	// 현재 페이지
    private int startNo;			// 시작 글번호
    private int endNo;				// 끝글번호
    private int startPage;			// 시작 페이지 번호
    private int endPage;			// 마지막 페이지 번호

    private String searchType;
    private String searchValue;
    private String isAvailable;

    public InAppList(int pageSize, int totalCount, Integer currentPage, int maxResult) {
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.maxResult = maxResult;
        calc();
    }
    // 나머지 변수들 계산하기
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

    @Override
    public String toString() {
        return "AppList [list=" + list + ", pageSize=" + pageSize
                + ", maxResult=" + maxResult + ", totalCount=" + totalCount
                + ", totalPage=" + totalPage + ", currentPage=" + currentPage
                + ", startNo=" + startNo + ", endNo=" + endNo + ", startPage="
                + startPage + ", endPage=" + endPage + ", searchType="
                + searchType + ", searchValue=" + searchValue + "]";
    }


}
