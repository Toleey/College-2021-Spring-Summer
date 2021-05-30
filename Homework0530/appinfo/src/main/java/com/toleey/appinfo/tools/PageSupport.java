package com.toleey.appinfo.tools;

/**
 * 分页工具类
 * @author 
 *
 */
public class PageSupport {
	//当前页码-来自于用户输入
	private int currentPageNo = 1;
	
	//总数量（表）
	private int totalCount = 0;
	
	//页面容量
	private int pageSize = 0;
	
	//总页数-totalCount/pageSize（+1）
	private int totalPageCount = 1;

	//构造方法是我后来加上的，用set总是报错 /zero
	public PageSupport() {
	}

	public PageSupport(int currentPageNo, int totalCount, int pageSize) {
		this.currentPageNo = currentPageNo;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		if(currentPageNo > 0){
			this.currentPageNo = currentPageNo;
		}
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		if(totalCount > 0){
			this.totalCount = totalCount;
			//设置总页数
			this.setTotalPageCountByRs();
		}
	}
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if(pageSize > 0){
			this.pageSize = pageSize;
		}
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	
	public void setTotalPageCountByRs(){
		if(this.totalCount % this.pageSize == 0){
			this.totalPageCount = this.totalCount / this.pageSize;
		}else if(this.totalCount % this.pageSize > 0){
			this.totalPageCount = this.totalCount / this.pageSize + 1;
		}else{
			this.totalPageCount = 0;
		}
	}

	@Override
	public String toString() {
		return "PageSupport{" +
				"currentPageNo=" + currentPageNo +
				", totalCount=" + totalCount +
				", pageSize=" + pageSize +
				", totalPageCount=" + totalPageCount +
				'}';
	}
}