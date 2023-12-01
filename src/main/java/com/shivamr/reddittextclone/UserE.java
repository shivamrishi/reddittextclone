package com.shivamr.reddittextclone;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserE{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer userId;
	
	private String userName;

	private ArrayList<String> tagList = new ArrayList<String>();

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void addTag(String tag) {
		tagList.add(tag);
	}
	
	public void setTagList(ArrayList<String> tl) {
		this.tagList = tl;
	}
	public ArrayList<String> getTagList() {
		return tagList;
	}
}