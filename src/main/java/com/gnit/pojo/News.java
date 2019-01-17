package com.gnit.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 新闻表
 * 
 * @author JDRY-SJM
 *
 */
@Entity
@Table(name = "t_gnit_news")
public class News {
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column
	private String id;// 主键
	@Column
	private String title;// 标题
	@Column
	private int type;// 新闻类型，1 为学校新闻 2 为行业新闻
	@Column
	private String author;// 作者
	@Column
	private String content;// 内容
	@Column(nullable = true)
	private Boolean isPubish;// 是否发布
	@Column(nullable = true)
	private String pubishStatus;// 发布状态
	@Column
	private String createTime;// 创建时间
	@Column
	private String publishTime;// 发布时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getIsPubish() {
		return isPubish;
	}

	public void setIsPubish(Boolean isPubish) {
		this.isPubish = isPubish;
	}

	public String getPubishStatus() {
		return pubishStatus;
	}

	public void setPubishStatus(String pubishStatus) {
		this.pubishStatus = pubishStatus;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

}
