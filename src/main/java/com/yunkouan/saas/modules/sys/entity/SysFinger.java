package com.yunkouan.saas.modules.sys.entity;

import javax.persistence.Id;

import com.yunkouan.base.BaseEntity;

/**
* @Description: 【指纹】实体类
* @author tphe06
* @date 2017年3月16日
*/
public class SysFinger extends BaseEntity {
	private static final long serialVersionUID = 1504170520001101061L;

	/**@Fields 指纹id */
	@Id
	private String fingerId;
	/**@Fields 用户id */
	private String userId;

	/**@Fields 指纹模板【BASE64】 */
	private String fingerPhoto;
	/**@Fields 指纹图片【BASE64】 */
	private String fingerImage1;
	/**@Fields 指纹图片【BASE64】 */
	private String fingerImage2;
	/**@Fields 指纹图片【BASE64】 */
	private String fingerImage3;
	/**@Fields 指纹图片名称 */
	private String fingerPhotoName;

	/**@Fields 指纹脉模板【BASE64】 */
	private String fingerVein;
	/**@Fields 指纹脉图片【BASE64】 */
	private String fingerVeinImage1;
	/**@Fields 指纹脉图片【BASE64】 */
	private String fingerVeinImage2;
	/**@Fields 指纹脉图片【BASE64】 */
	private String fingerVeinImage3;
	/**@Fields 指纹脉图片名称 */
	private String fingerVeinName;

	public String getFingerId() {
		return fingerId;
	}
	public String getUserId() {
		return userId;
	}
	public String getFingerPhoto() {
		return fingerPhoto;
	}
	public String getFingerPhotoName() {
		return fingerPhotoName;
	}
	public SysFinger setFingerId(String fingerId) {
		this.fingerId = fingerId;
		return this;
	}
	public SysFinger setUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public SysFinger setFingerPhoto(String fingerPhoto) {
		this.fingerPhoto = fingerPhoto;
		return this;
	}
	public SysFinger setFingerPhotoName(String fingerPhotoName) {
		this.fingerPhotoName = fingerPhotoName;
		return this;
	}
	public String getFingerVein() {
		return fingerVein;
	}
	public String getFingerVeinName() {
		return fingerVeinName;
	}
	public SysFinger setFingerVein(String fingerVein) {
		this.fingerVein = fingerVein;
		return this;
	}
	public SysFinger setFingerVeinName(String fingerVeinName) {
		this.fingerVeinName = fingerVeinName;
		return this;
	}
	public String getFingerImage1() {
		return fingerImage1;
	}
	public String getFingerImage2() {
		return fingerImage2;
	}
	public String getFingerImage3() {
		return fingerImage3;
	}
	public String getFingerVeinImage1() {
		return fingerVeinImage1;
	}
	public String getFingerVeinImage2() {
		return fingerVeinImage2;
	}
	public String getFingerVeinImage3() {
		return fingerVeinImage3;
	}
	public SysFinger setFingerImage1(String fingerImage1) {
		this.fingerImage1 = fingerImage1;
		return this;
	}
	public SysFinger setFingerImage2(String fingerImage2) {
		this.fingerImage2 = fingerImage2;
		return this;
	}
	public SysFinger setFingerImage3(String fingerImage3) {
		this.fingerImage3 = fingerImage3;
		return this;
	}
	public SysFinger setFingerVeinImage1(String fingerVeinImage1) {
		this.fingerVeinImage1 = fingerVeinImage1;
		return this;
	}
	public SysFinger setFingerVeinImage2(String fingerVeinImage2) {
		this.fingerVeinImage2 = fingerVeinImage2;
		return this;
	}
	public SysFinger setFingerVeinImage3(String fingerVeinImage3) {
		this.fingerVeinImage3 = fingerVeinImage3;
		return this;
	}
}