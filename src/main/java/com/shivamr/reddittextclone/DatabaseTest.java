package com.shivamr.reddittextclone;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseTest implements ApplicationRunner {
	
	@Autowired
	private InteractionRepository ir;
	
	@Autowired
	private UserRepository ur;
	
	@Autowired
	private PostRepository pr;
	
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Your code here
        // This method will be executed at application startup
    	System.out.println("Testing Database...");
    	
    	// create user
    	
    	UserE test = new UserE();
    	test.setUserName("test");
    	test.addTag("cats");
    	// create Post
    	ur.save(test);
    	
    	Post testPost = new Post();
    	testPost.setTitle("test sort");
    	testPost.setContent("testing sorting");
    	testPost.setUser(test);
    	testPost.setTag("sort");
    	testPost.setCreationTime(new Date());
    	testPost = pr.saveAndFlush(testPost);
    	System.out.println(testPost.getId());
    	System.out.println(testPost.getCreationTime());

    	// create Interaction
    	
    	
    }

	public InteractionRepository getIr() {
		return ir;
	}

	public void setIr(InteractionRepository ir) {
		this.ir = ir;
	}

	public UserRepository getUr() {
		return ur;
	}

	public void setUr(UserRepository ur) {
		this.ur = ur;
	}

	public PostRepository getPr() {
		return pr;
	}

	public void setPr(PostRepository pr) {
		this.pr = pr;
	}
	
	
}