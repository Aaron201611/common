/*
 * Copyright Notice =========================================================
 * This file contains proprietary information of 中云智慧(北京)科技有限公司 Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2017 =======================================================
 * Company: yunkouan.com<br/>
 * 创建日期:<br/> 2017年1月13日 下午4:24:47<br/>
 * @author andy wang<br/>
 */
package com.yunkouan.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pagehelper.Page;


/**
 * 返回结果对象<br/><br/>
 * <b>创建日期</b>:<br/> 2017年1月13日 下午4:24:47<br/>
 * @author andy wang<br/>
 */
@SuppressWarnings("rawtypes")
public class ResultModel implements Serializable {
	private static final long serialVersionUID = -5002832418898018163L;

	/* 属性 *********************************************/
	/**
	 * 请求状态
	 * 1 - 正常访问
	 * -1 - 访问失败
	 * <b>创建日期</b>:<br/> 2017年2月9日 下午10:30:47<br/>
	 * @author andy wang<br/>
	 */
	private Integer status=1;
	
	/**
	 * 返回的信息
	 * <b>创建日期</b>:<br/> 2017年2月9日 下午10:30:47<br/>
	 * @author andy wang<br/>
	 */
	private List<String> msg;
	
	/**
	 * 返回数据
	 * <b>创建日期</b>:<br/> 2017年2月9日 下午10:30:47<br/>
	 * @author andy wang<br/>
	 */
	private Map<String,Object> result= new HashMap<String, Object>();

	/* 属性 *********************************************/
	public static final String RESULT_LIST = "list";
	public static final String RESULT_OBJ = "obj";
	public static final String RESULT_PAGE = "page";
	public static final String RESULT_PAGECOUNT = "pageCount";
	public static final String RESULT_TOTALCOUNT = "totalCount";
	
	/**
	 * 设置错误
	 * 创建日期:<br/> 2017年2月9日 下午10:31:32<br/>
	 * @author andy wang<br/>
	 */
	public ResultModel setError() {
		this.status = 0;
		return this;
	}
	
	/**
	 * 获取分页对象
	 * @return 分页对象
	 * 创建日期:<br/> 2017年2月9日 下午10:32:16<br/>
	 * @author andy wang<br/>
	 */
	@JsonIgnore
	public Page getPage() {
		if ( this.result == null ) {
			return null;
		}
		return (Page) this.result.get(RESULT_PAGE);
	}
	
	/**
	 * 设置返回对象集合
	 * @param list 列表
	 * 创建日期:<br/> 2017年2月9日 下午10:33:05<br/>
	 * @author andy wang<br/>
	 */
	public ResultModel setList( List list ) {
		if ( this.result == null ) {
			this.result = new HashMap<String, Object>();
		}
		this.result.put(RESULT_LIST, list);
		return this;
	}
	
	/**
	 * 获取返回对象集合
	 * @return
	 * 创建日期:<br/> 2017年2月10日 下午2:25:19<br/>
	 * @author andy wang<br/>
	 */
	@JsonIgnore
	public List getList() {
		if ( this.result == null ) {
			return null;
		}
		return (List) this.result.get(RESULT_LIST);
	}
	
	/**
	 * 设置返回对象
	 * @param obj
	 * 创建日期:<br/> 2017年2月10日 下午2:24:36<br/>
	 * @author andy wang<br/>
	 */
	public ResultModel setObj( Object obj ) {
		if ( this.result == null ) {
			this.result = new HashMap<String, Object>();
		}
		this.result.put(RESULT_OBJ, obj);
		return this;
	}
	
	/**
	 * 获取返回对象
	 * @return
	 * 创建日期:<br/> 2017年2月10日 下午2:23:16<br/>
	 * @author andy wang<br/>
	 */
	@JsonIgnore
	public Object getObj() {
		if ( this.result == null ) {
			return null;
		}
		return this.result.get(RESULT_OBJ);
	}
	
	/**
	 * 设置总页数
	 * @param page 分页对象
	 * 创建日期:<br/> 2017年2月10日 下午2:22:58<br/>
	 * @author andy wang<br/>
	 */
	public ResultModel setPage( Page page ) {
		if ( page == null ) {
			return this;
		}
		if ( this.result == null ) {
			this.result = new HashMap<String, Object>();
		}
//		this.result.put(RESULT_PAGE, page);
		this.result.put(RESULT_LIST, page);
//		this.result.put("currentPage", page.getPageNum());
//		this.result.put("pageSize", page.getPageSize());
		this.result.put(RESULT_PAGECOUNT, page.getPages());
		this.result.put(RESULT_TOTALCOUNT, page.getTotal());
		return this;
	}

	@JsonIgnore
	public ResultModel setPageCount(int pageCount) {
		if ( this.result == null ) {
			this.result = new HashMap<String, Object>();
		}
		this.result.put(RESULT_PAGECOUNT, pageCount);
		return this;
	}

	@JsonIgnore
	public ResultModel setTotalCount(long totalCount) {
		if ( this.result == null ) {
			this.result = new HashMap<String, Object>();
		}
		this.result.put(RESULT_TOTALCOUNT, totalCount);
		return this;
	}

	/**
	 * 获取状态
	 * @return
	 * 创建日期:<br/> 2017年2月10日 下午2:22:51<br/>
	 * @author andy wang<br/>
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置状态
	 * @param status 状态值
	 * 创建日期:<br/> 2017年2月10日 下午2:22:40<br/>
	 * @author andy wang<br/>
	 */
	public ResultModel setStatus(Integer status) {
		this.status = status;
		return this;
	}

	/**
	 * 获取所有返回结果
	 * @return
	 * 创建日期:<br/> 2017年2月10日 下午2:22:30<br/>
	 * @author andy wang<br/>
	 */
	public Map<String, Object> getResult() {
		return result;
	}
	
	/**
	 * 添加返回对象
	 * @param key 索引key
	 * @param obj 返回对象
	 * @return
	 * 创建日期:<br/> 2017年2月10日 下午2:22:05<br/>
	 * @author andy wang<br/>
	 */
	public Map<String,Object> put( String key , Object obj ){
		this.result.put(key, obj);
		return this.result;
	}
	
	/**
	 * 添加信息
	 * @param message
	 * 创建日期:<br/> 2017年2月10日 下午2:21:59<br/>
	 * @author andy wang<br/>
	 */
	public ResultModel addMessage ( String message ) {
		if ( this.msg == null ) {
			this.msg = new ArrayList<String>();
		}
		this.msg.add(message);
		return this;
	}
	
	/**
	 * 判断提示信息是否已经存在
	 * @param message 提示信息
	 * @return 是否已经存在
	 * @version 2017年3月10日 上午9:29:32<br/>
	 * @author andy wang<br/>
	 */
	public Boolean contains ( String message ) {
		if ( this.msg == null ) {
			return false;
		}
		return this.msg.contains(message);
	}
	
	/**
	 * 获取信息
	 * @return
	 * 创建日期:<br/> 2017年2月10日 下午2:21:48<br/>
	 * @author andy wang<br/>
	 */
	public String getMessage(){
		StringBuffer sb = new StringBuffer();
		if ( this.msg == null || this.msg.isEmpty() ) {
			return "";
		}
		for (int i = 0; i < this.msg.size(); i++) {
			sb.append(this.msg.get(i)+"\r\n");
		}
		return sb.toString();
	}
	
	/**
	 * 判断是否访问错误
	 * @return true - 访问成功<br/>
	 * fale - 访问失败
	 * 创建日期:<br/> 2017年2月11日 下午8:47:00<br/>
	 * @author andy wang<br/>
	 */
	public Boolean isSuccess () {
		return 1 == this.getStatus();
	}
	
	/**
	 * 对象toString方法，列出所有属性
	 * @return
	 * 创建日期:<br/> 2017年2月10日 下午2:21:15<br/>
	 * @author andy wang<br/>
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
