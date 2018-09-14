package com.yunkouan.base;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 基础实体类
 * <P>BaseEntity.java</P>
 * <P>Description: </P>
 * <P>Copyright: Copyright(c) 2016</P>
 * <P>Company: yunkouan.com</P>
 * <P>Date: 2016年12月1日下午5:36:09</P>
 * @author andy
 * @version 1.0.0
 */
@JsonIgnoreProperties(value={"createTime","updateTime","createPerson","updatePerson"},allowGetters=true)
public abstract class BaseEntity extends BaseObj {
	private static final long serialVersionUID = -2077106029477648403L;

	/**
	 * 创建时间
	 * @version 2017年3月7日下午4:57:00<br/>
	 * @author andy wang<br/>
	 */
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",locale = "zh" , timezone="GMT+8")
	private Date createTime;
	
	/**
	 * 修改时间
	 * @version 2017年3月7日下午4:57:12<br/>
	 * @author andy wang<br/>
	 */
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",locale = "zh" , timezone="GMT+8")
	private Date updateTime;
	
	/**
	 * 创建人
	 * @version 2017年3月7日下午4:57:16<br/>
	 * @author andy wang<br/>
	 */
	private String createPerson;		
	
	/**
	 * 最后修改人
	 * @version 2017年3月7日下午4:57:24<br/>
	 * @author andy wang<br/>
	 */
	private String updatePerson;

	/* getset ********************************************/
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 属性 createPerson getter方法
	 * @return 属性createPerson
	 * @author andy wang<br/>
	 */
	public String getCreatePerson() {
		return createPerson;
	}

	/**
	 * 属性 createPerson setter方法
	 * @param createPerson 设置属性createPerson的值
	 * @author andy wang<br/>
	 */
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	/**
	 * 属性 updatePerson getter方法
	 * @return 属性updatePerson
	 * @author andy wang<br/>
	 */
	public String getUpdatePerson() {
		return updatePerson;
	}

	/**
	 * 属性 updatePerson setter方法
	 * @param updatePerson 设置属性updatePerson的值
	 * @author andy wang<br/>
	 */
	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}

	/* method ********************************************/
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/**
	 * 清空实体类中无需前端传送的字段值
	 */
	public void clear() {
		this.createTime = null;
		this.updateTime = null;
    	/**不能由前端修改，添加时候由程序赋值，修改时候置空不更新数据库的值**/
    	this.createPerson = null;
    	/**不能由前端修改，修改时候由程序赋值，添加时候置空不给数据库赋值**/
    	this.updatePerson = null;
	}
}