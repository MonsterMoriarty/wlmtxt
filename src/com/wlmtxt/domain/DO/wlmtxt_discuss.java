package com.wlmtxt.domain.DO;

public class wlmtxt_discuss {
	//id
	private String discuss_id;
	//父评论id
	private String discuee_father_discuss_id;
	//评论者id
	private String discuss_user_id;
	//评论内容
	private String discuss_content;
	//是否删除
	private String discuss_deleted;
	public String getDiscuss_deleted() {
		return discuss_deleted;
	}
	public void setDiscuss_deleted(String discuss_deleted) {
		this.discuss_deleted = discuss_deleted;
	}
	//创建时间
	private String discuss_gmt_create;
	//修改时间
	private String discuss_gmt_modified;
	public String getDiscuss_id() {
		return discuss_id;
	}
	public void setDiscuss_id(String discuss_id) {
		this.discuss_id = discuss_id;
	}
	public String getDiscuee_father_discuss_id() {
		return discuee_father_discuss_id;
	}
	public void setDiscuee_father_discuss_id(String discuee_father_discuss_id) {
		this.discuee_father_discuss_id = discuee_father_discuss_id;
	}
	public String getDiscuss_user_id() {
		return discuss_user_id;
	}
	public void setDiscuss_user_id(String discuss_user_id) {
		this.discuss_user_id = discuss_user_id;
	}
	public String getDiscuss_content() {
		return discuss_content;
	}
	public void setDiscuss_content(String discuss_content) {
		this.discuss_content = discuss_content;
	}
	public String getDiscuss_gmt_create() {
		return discuss_gmt_create;
	}
	public void setDiscuss_gmt_create(String discuss_gmt_create) {
		this.discuss_gmt_create = discuss_gmt_create;
	}
	public String getDiscuss_gmt_modified() {
		return discuss_gmt_modified;
	}
	public void setDiscuss_gmt_modified(String discuss_gmt_modified) {
		this.discuss_gmt_modified = discuss_gmt_modified;
	}
	@Override
	public String toString() {
		return "wlmtxt_discuss [discuss_id=" + discuss_id + ", discuee_father_discuss_id=" + discuee_father_discuss_id
				+ ", discuss_user_id=" + discuss_user_id + ", discuss_content=" + discuss_content
				+ ", discuss_gmt_create=" + discuss_gmt_create + ", discuss_gmt_modified=" + discuss_gmt_modified + "]";
	}
	
}
