/*
 * Copyright Notice =========================================================
 * This file contains proprietary information of 中云智慧(北京)科技有限公司 Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2017 =======================================================
 * Company: yunkouan.com<br/>
 * @version 2017年3月8日 上午10:08:00<br/>
 * @author andy wang<br/>
 */
package com.yunkouan.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pagehelper.Page;

import tk.mybatis.mapper.entity.Example;

/**
 * 基础VO<br/><br/>
 * @version 2017年3月8日 上午10:08:00<br/>
 * @author andy wang<br/>
 */
public abstract class BaseVO extends BaseObj {
	private static final long serialVersionUID = 5830304202346548961L;
	
	/** 当前页面 <br/> add by andy wang */
	private Integer currentPage;
	/** 每页显示数量 <br/> add by andy wang */
	private Integer pageSize;

	/**分页数量*/
	private Integer pageCount;
	/**总记录数量*/
	private Long totalCount;
	/**前端显示的序号*/
	private Long sortNo;

	/**默认排序方式*/
//	protected String orderBy = "create_time desc ";

	/* getset begin*************************************************/
	public Integer getCurrentPage() {
		if(currentPage == null) return 0;
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		if(pageSize == null) return 8;
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	@JsonIgnore
	public Integer getPageCount() {
		return pageCount;
	}
	@JsonIgnore
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	@JsonIgnore
	public Long getTotalCount() {
		return totalCount;
	}
	@JsonIgnore
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	@JsonIgnore
	public Long getSortNo() {
		return sortNo;
	}
	@JsonIgnore
	public void setSortNo(Long sortNo) {
		this.sortNo = sortNo;
	}
//	@JsonIgnore
//	public String getOrderBy() {
//		return orderBy;
//	}
//	@JsonIgnore
//	public void setOrderBy(String orderBy) {
//		this.orderBy = orderBy;
//	}
	/* getset end *************************************************/

	/**
	 * 复制page对象数量
	 * @param page
	 * <P>@author andy</P>
	 * <P>Date 2017年1月11日 下午8:22:13</P>
	 */
	public void setPage( Page page ) {
		if ( page == null ) {
			return ;
		}
		this.currentPage = page.getPageNum();
		this.pageSize = page.getPageSize();
	}

	/**
	 * 生成Example
	 * @return
	 * <P>@author andy</P>
	 * <P>Date 2017年1月13日 下午11:08:19</P>
	 */
	@JsonIgnore
	public Example getExample(){
		return null;
	}

	@JsonIgnore
	public void retset(){
		this.currentPage = null;
		this.pageSize = null;
		this.pageCount = null;
		this.totalCount = null;
//		this.orderBy = null;
	}
}