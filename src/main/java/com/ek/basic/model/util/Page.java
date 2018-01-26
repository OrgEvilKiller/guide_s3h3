package com.ek.basic.model.util;

public  class Page{
	private Integer currentPageNum; //当前页数
	private Integer perPageNum; //每页记录数
	private Integer maxPageNum; //最大页数
	private Integer maxRowNum; //最大记录数
	private String url;
	
	public Integer getCurrentPageNum() {
		return currentPageNum;
	}
	public void setCurrentPageNum(Integer currentPageNum) {
		this.currentPageNum = currentPageNum;
	}
	public Integer getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(Integer perPageNum) {
		this.perPageNum = perPageNum;
	}
	public Integer getMaxPageNum() {
		return maxPageNum;
	}
	public void setMaxPageNum(Integer maxPageNum) {
		this.maxPageNum = maxPageNum;
	}
	public Integer getMaxRowNum() {
		return maxRowNum;
	}
	public void setMaxRowNum(Integer maxRowNum) {
		this.maxRowNum = maxRowNum;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}