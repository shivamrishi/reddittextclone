package com.shivamr.reddittextclone;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Interaction{
	
	public static enum interactionType{
		COMMENT,
		SAVE_TAG_TO_FEED,
		REACTION
	}
	
	private String data;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer interactionId;
	
	@ManyToOne
	private UserE user;
	
	@ManyToOne
	private Post post;
	
	private interactionType InteractionValue;
	
	public Integer getId() {
		return interactionId;
	}
	
	public interactionType getInteractionValue() {
		return InteractionValue;
	}

	public void setInteractionValue(interactionType interactionValue) {
		InteractionValue = interactionValue;
	}

	public UserE getUser() {
		return user;
	}

	public void setUser(UserE user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
	