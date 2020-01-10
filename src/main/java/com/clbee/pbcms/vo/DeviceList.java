package com.clbee.pbcms.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DeviceList {
    private List<DeviceVO> list;	// 1페이지 분량
    private int pageSize;			// 한번에 보여줄 최대 페이지개수
    private int maxResult;			// 페이지당 리스트 갯수
    private int totalCount;			// 전체 개수
    private int totalPage;			// 전체 페이지
    private int currentPage;		// 현재 페이지
    private int startNo;			// 시작 글번호
    private int endNo;				// 끝글번호
    private int startPage;			// 시작 페이지 번호
    private int endPage;			// 마지막 페이지 번호

    public DeviceList(int pageSize, int totalCount, int currentPage, int maxResult) {
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
}

