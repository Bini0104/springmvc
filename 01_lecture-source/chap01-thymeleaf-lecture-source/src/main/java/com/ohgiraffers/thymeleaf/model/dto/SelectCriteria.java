package com.ohgiraffers.thymeleaf.model.dto;

public class SelectCriteria {

    private int starPage;
    private int endPage;
    private int pageNo;

    public SelectCriteria(){}

    public SelectCriteria(int starPage, int endPage, int pageNo) {
        this.starPage = starPage;
        this.endPage = endPage;
        this.pageNo = pageNo;
    }

    public int getStarPage() {
        return starPage;
    }

    public void setStarPage(int starPage) {
        this.starPage = starPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public String toString() {
        return "SelectCriteria{" +
                "starPage=" + starPage +
                ", endPage=" + endPage +
                ", pageNo=" + pageNo +
                '}';
    }
}
