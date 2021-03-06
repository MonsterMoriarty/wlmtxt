package com.wlmtxt.domain.VO;

import java.util.List;

import com.wlmtxt.domain.DTO.WorksDTO;

public class MyWorksVO {

	// 当前页
	private int pageIndex = 1;

	// 总记录数
	private int totalRecords = 0;

	// 每页显示记录数
	private int pageSize = 30;

	// 总页数
	private int totalPages = 1;

	// 是否有上一页
	private boolean havePrePage = false;

	// 是否有下一页
	private boolean haveNextPage = false;

	private List<WorksDTO> worksDTOList;

	@Override
	public String toString() {
		return "MyWorksVO [pageIndex=" + pageIndex + ", totalRecords=" + totalRecords + ", pageSize=" + pageSize
				+ ", totalPages=" + totalPages + ", havePrePage=" + havePrePage + ", haveNextPage=" + haveNextPage
				+ ", worksDTOList=" + worksDTOList + "]";
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isHavePrePage() {
		return havePrePage;
	}

	public void setHavePrePage(boolean havePrePage) {
		this.havePrePage = havePrePage;
	}

	public boolean isHaveNextPage() {
		return haveNextPage;
	}

	public void setHaveNextPage(boolean haveNextPage) {
		this.haveNextPage = haveNextPage;
	}

	public List<WorksDTO> getWorksDTOList() {
		return worksDTOList;
	}

	public void setWorksDTOList(List<WorksDTO> worksDTOList) {
		this.worksDTOList = worksDTOList;
	}

}
