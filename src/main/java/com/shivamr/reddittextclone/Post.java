package com.shivamr.reddittextclone;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Post{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String title;
	
	private String content;
	
	@ManyToOne
	private UserE user;
	
	private Date creationTime;
	
	private String tag;
	
	
	private Integer rCount= Integer.valueOf(0);

	public Integer getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String Title) {
		title = Title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String Content) {
		content = Content;
	}

	public UserE getUser() {
		return user;
	}

	public void setUser(UserE user) {
		this.user = user;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getrCount() {
		return rCount;
	}
	
	public void setrCount(Integer r) {
		rCount = r;
	}

	public void incrRCount() {
		if(rCount==null || rCount==9)
			rCount = 1;
		else
			rCount++;
	}
	
}
