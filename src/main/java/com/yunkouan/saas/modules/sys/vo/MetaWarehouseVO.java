/*
 * Copyright Notice =========================================================
 * This file contains proprietary information of 中云智慧(北京)科技有限公司 Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2017 =======================================================
 * Company: yunkouan.com<br/>
 * @version 2017年3月6日 下午4:07:06<br/>
 * @author andy wang<br/>
 */
package com.yunkouan.saas.modules.sys.vo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.yunkouan.base.BaseVO;
import com.yunkouan.saas.modules.sys.entity.MetaWarehouse;
import com.yunkouan.util.PubUtil;
import com.yunkouan.util.StringUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 仓库VO<br/><br/>
 * @version 2017年3月6日 下午4:07:06<br/>
 * @author andy wang<br/>
 */
public class MetaWarehouseVO extends BaseVO {

	private static final long serialVersionUID = 4249995957381257900L;

	public MetaWarehouseVO() {
		this( new MetaWarehouse() );
	}
	
	/**
	 * 构造方法
	 * @param warehouse
	 * @version 2017年3月6日 下午4:08:35<br/>
	 * @author andy wang<br/>
	 */
	public MetaWarehouseVO(MetaWarehouse warehouse) {
		super();
		this.warehouse = warehouse;
	}

	/**
	 * 仓库对象
	 * @version 2017年3月6日下午4:08:46<br/>
	 * @author andy wang<br/>
	 */
	@Valid
	private MetaWarehouse warehouse;

	
	/**
	 * 状态中文描述
	 * @version 2017年3月11日下午1:29:23<br/>
	 * @author andy wang<br/>
	 */
	private String wrhStatusComment;
	
	/**
	 * 类型中文描述
	 * @version 2017年3月11日下午1:32:31<br/>
	 * @author andy wang<br/>
	 */
	private String wrhTypeComment;
	
	/**
	 * 模糊查询联系人
	 * @version 2017年4月3日上午11:06:47<br/>
	 * @author andy wang<br/>
	 */
	private String likeContactPerson;
	
	/**
	 * 模糊查询联系地址
	 * @version 2017年4月3日上午11:06:50<br/>
	 * @author andy wang<br/>
	 */
	private String likeContactAddress;
	
	/**
	 * 模糊查询仓库名
	 * @version 2017年4月3日上午11:06:54<br/>
	 * @author andy wang<br/>
	 */
	private String likeWrhName;
	
	/**
	 * 是否需要企业id，这个查询条件
	 * @version 2017年5月3日下午1:52:57<br/>
	 * @author andy wang<br/>
	 */
	private Boolean whereOrg;
	
	/**
	 * 查询条件不等于集合中的id
	 * @version 2017年6月16日上午11:47:05<br/>
	 * @author andy wang<br/>
	 */
	private List<String> listNotId;
	
	/* getset *************************************/

	/**
	 * 属性 likeContactPerson getter方法
	 * @return 属性likeContactPerson
	 * @author andy wang<br/>
	 */
	public String getLikeContactPerson() {
		return likeContactPerson;
	}

	/**
	 * 属性 likeContactPerson setter方法
	 * @param likeContactPerson 设置属性likeContactPerson的值
	 * @author andy wang<br/>
	 */
	public void setLikeContactPerson(String likeContactPerson) {
		this.likeContactPerson = likeContactPerson;
	}

	/**
	 * 属性 listNotId getter方法
	 * @return 属性listNotId
	 * @author andy wang<br/>
	 */
	public List<String> getListNotId() {
		return listNotId;
	}

	/**
	 * 属性 listNotId setter方法
	 * @param listNotId 设置属性listNotId的值
	 * @author andy wang<br/>
	 */
	public void setListNotId(List<String> listNotId) {
		this.listNotId = listNotId;
	}

	/**
	 * 属性 whereOrg getter方法
	 * @return 属性whereOrg
	 * @author andy wang<br/>
	 */
	public Boolean getWhereOrg() {
		if ( this.whereOrg == null ) {
			return false;
		}
		return whereOrg;
	}

	/**
	 * 属性 whereOrg setter方法
	 * @param whereOrg 设置属性whereOrg的值
	 * @author andy wang<br/>
	 */
	public void setWhereOrg(Boolean whereOrg) {
		this.whereOrg = whereOrg;
	}

	/**
	 * 属性 likeContactAddress getter方法
	 * @return 属性likeContactAddress
	 * @author andy wang<br/>
	 */
	public String getLikeContactAddress() {
		return likeContactAddress;
	}

	/**
	 * 属性 likeContactAddress setter方法
	 * @param likeContactAddress 设置属性likeContactAddress的值
	 * @author andy wang<br/>
	 */
	public void setLikeContactAddress(String likeContactAddress) {
		this.likeContactAddress = likeContactAddress;
	}

	/**
	 * 属性 likeWrhName getter方法
	 * @return 属性likeWrhName
	 * @author andy wang<br/>
	 */
	public String getLikeWrhName() {
		return likeWrhName;
	}

	/**
	 * 属性 likeWrhName setter方法
	 * @param likeWrhName 设置属性likeWrhName的值
	 * @author andy wang<br/>
	 */
	public void setLikeWrhName(String likeWrhName) {
		this.likeWrhName = likeWrhName;
	}

	/**
	 * 属性 warehouse getter方法
	 * @return 属性warehouse
	 * @author andy wang<br/>
	 */
	public MetaWarehouse getWarehouse() {
		return warehouse;
	}

	/**
	 * 属性 warehouse setter方法
	 * @param warehouse 设置属性warehouse的值
	 * @author andy wang<br/>
	 */
	public void setWarehouse(MetaWarehouse warehouse) {
		this.warehouse = warehouse;
	}
	

	/**
	 * 属性 wrhStatusComment getter方法
	 * @return 属性wrhStatusComment
	 * @author andy wang<br/>
	 */
	public String getWrhStatusComment() {
		return wrhStatusComment;
	}

	/**
	 * 属性 wrhStatusComment setter方法
	 * @param wrhStatusComment 设置属性wrhStatusComment的值
	 * @author andy wang<br/>
	 */
	public void setWrhStatusComment(String wrhStatusComment) {
		this.wrhStatusComment = wrhStatusComment;
	}

	/**
	 * 属性 wrhTypeComment getter方法
	 * @return 属性wrhTypeComment
	 * @author andy wang<br/>
	 */
	public String getWrhTypeComment() {
		return wrhTypeComment;
	}

	/**
	 * 属性 wrhTypeComment setter方法
	 * @param wrhTypeComment 设置属性wrhTypeComment的值
	 * @author andy wang<br/>
	 */
	public void setWrhTypeComment(String wrhTypeComment) {
		this.wrhTypeComment = wrhTypeComment;
	}


	/* method *************************************/
	@Override
	public Example getExample() {
		if ( this.warehouse == null ) {
			return null;
		}
		Example example = new Example(MetaWarehouse.class);
		Criteria criteria = example.createCriteria();
		if ( !StringUtil.isEmpty(this.warehouse.getOrgId()) ) {
			criteria.andEqualTo("orgId", this.warehouse.getOrgId());
		}
		if ( !StringUtil.isEmpty(this.warehouse.getWarehouseId()) ) {
			criteria.andEqualTo("warehouseId", this.warehouse.getWarehouseId());
		}
		if ( !StringUtil.isEmpty(this.warehouse.getWarehouseName()) ) {
			criteria.andEqualTo("warehouseName", this.warehouse.getWarehouseName());
		}
		if ( !StringUtil.isEmpty(this.warehouse.getWarehouseNo()) ) {
			criteria.andEqualTo("warehouseNo", this.warehouse.getWarehouseNo());
		}
		if ( !StringUtil.isEmpty(this.getLikeContactAddress()) ) {
			criteria.andLike("contactAddress", "%"+StringUtil.likeEscapeH(this.getLikeContactAddress())+"%");
		}
		if ( !StringUtil.isEmpty(this.getLikeContactPerson()) ) {
			criteria.andLike("contactPerson", "%"+StringUtil.likeEscapeH(this.getLikeContactPerson())+"%");
		}
		if ( !StringUtil.isEmpty(this.getLikeWrhName()) ) {
			criteria.andLike("warehouseName", "%"+StringUtil.likeEscapeH(this.getLikeWrhName())+"%");
		}
		if ( this.warehouse.getWarehouseStatus() != null ) {
			criteria.andEqualTo("warehouseStatus", this.warehouse.getWarehouseStatus());
		}
		if ( !PubUtil.isEmpty(this.getListNotId()) ) {
			criteria.andNotIn("warehouseId", this.getListNotId());
		}
		if ( this.warehouse.getWarehouseType() != null ) {
			criteria.andEqualTo("warehouseType", this.warehouse.getWarehouseType());
		}
		return example;
	}
	
	/**
	 * 添加不等于id条件
	 * @param notId
	 * @version 2017年6月16日 上午11:54:21<br/>
	 * @author andy wang<br/>
	 */
	public void addNotId ( String notId ) {
		if ( StringUtil.isTrimEmpty(notId) ) {
			return;
		}
		if ( PubUtil.isEmpty(this.listNotId ) ) {
			this.listNotId = new ArrayList<String>();
		}
		this.listNotId.add(notId);
	}
	
	/**
	 * 设置当前用户信息
	 * @version 2017年3月6日 下午4:20:01<br/>
	 * @author andy wang<br/>
	 */
//	public void setLoginUser() {
//		if ( this.warehouse == null ) {
//			return ;
//		}
//		this.warehouse.setOrgId(loginUser.getOrgId());
//	}
}
