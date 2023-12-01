package com.shivamr.reddittextclone;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.shivamr.reddittextclone.Interaction.interactionType;

@RestController
public class browse{
	@Autowired
	private InteractionRepository ir;
	
	@Autowired
	private UserRepository ur;
	
	@Autowired
	private PostRepository pr;

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
	
	@GetMapping("/")
	public ModelAndView index(/*Model model,*/) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        //modelAndView.addObject("ID", id);
        return modelAndView; // Return the name of the HTML template (without the file extension)
    }
	
	@GetMapping("/t2")
	public Authentication t2(/*Model model,*/) {
		Authentication authentication = SecurityContextHolder.getContext()
			    .getAuthentication();
		if(authentication instanceof AnonymousAuthenticationToken)
			return authentication;
		else 
			return null;
	}
	
	@RequestMapping(value = "/user/createNew", method = RequestMethod.GET)
	public ModelAndView createNewForm(/*Model model,*/) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("createNew");
        //modelAndView.addObject("ID", id);
        return modelAndView; // Return the name of the HTML template (without the file extension)
    }
	
	/*
	@GetMapping("/login/oauth2/github")
	public ModelAndView userLogin(@AuthenticationPrincipal UserDetails userDetails) {
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("user_name", userDetails.getUsername());
        return modelAndView;
	}*/
	
	@GetMapping("/user/test")
	public ModelAndView test() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication authentication = SecurityContextHolder.getContext()
			    .getAuthentication();
		DefaultOAuth2User U = (DefaultOAuth2User) authentication.getPrincipal();
        modelAndView.setViewName("index");
        UserE u;
		if(ur.findByuserName(U.getName()).isEmpty()) {
			u = new UserE();
			u.setUserName(U.getName());
			ur.save(u);
		}else {
			u = ur.findByuserName(U.getName()).get();
		}
        
        modelAndView.addObject("user_name", u.getUserName());
        return modelAndView;
	}
	
	@GetMapping("test2")
	public DefaultOAuth2User test2() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication authentication = SecurityContextHolder.getContext()
			    .getAuthentication();
		DefaultOAuth2User U = (DefaultOAuth2User) authentication.getPrincipal();
        return U;
	}
	
	@RequestMapping(value = "/user/createNew", method = RequestMethod.POST)
	public ModelAndView createNew(@RequestBody Post newP, ModelMap model) {
		System.out.println("in create new post");
		Authentication authentication = SecurityContextHolder.getContext()
			    .getAuthentication();
		DefaultOAuth2User U = (DefaultOAuth2User) authentication.getPrincipal();
		
		UserE u;
		if(ur.findByuserName(U.getName()).isEmpty()) {
			u = new UserE();
			u.setUserName(U.getName());
			u.addTag(newP.getTag());
			ur.save(u);
			System.out.println("created User");
		}else {
			u = ur.findByuserName(U.getName()).get();

			System.out.println("found User");
		}
		
		newP.setUser(u);
		pr.save(newP);
		Integer id = newP.getId();
		System.out.println(id);
		//ModelAndView modelAndView = new ModelAndView(); 
		//modelAndView.setViewName("redirect:/post?id="+id);
		model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:http://localhost:8080/post?id="+id, model);
	}
	
	@GetMapping("/post")
	public ModelAndView getById(@RequestParam(name="id") int id){
		Optional<Post> p = pr.findById(id);
		if(p.isEmpty())
			return null;
		else {
			Authentication authentication = SecurityContextHolder.getContext()
				    .getAuthentication();
			DefaultOAuth2User U = (DefaultOAuth2User) authentication.getPrincipal();
			String uid = U.getName();
			Post op = p.get();	
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("postDetails", op);
			modelAndView.addObject("userId", uid);
			modelAndView.setViewName("post");
			return modelAndView;
		}
	}
	
	@RequestMapping(value = "/user/Interact", method = RequestMethod.POST)
	public Interaction createNew(@RequestBody Interaction interaction, ModelMap model, @RequestHeader("postid") Integer pid, @RequestHeader("userid") Integer uid) {
		Authentication authentication = SecurityContextHolder.getContext()
			    .getAuthentication();
		DefaultOAuth2User U = (DefaultOAuth2User) authentication.getPrincipal();
		
		String uid1 = U.getName();

		System.out.println(uid);
		System.out.println(pid);
		System.out.println(interaction.getData());
		System.out.println(interaction.getInteractionValue());
		
		
		if(!ur.findByuserName(uid.toString()).isEmpty()&& !pr.findById(pid).isEmpty()) {
			System.out.println("saving interaction");
			UserE u = ur.findByuserName(uid.toString()).get();
			Post p = pr.findById(pid).get();
			interaction.setUser(u);
			interaction.setPost(p);
			
			if(interaction.getInteractionValue()== interactionType.SAVE_TAG_TO_FEED) {
				u.addTag(interaction.getData());
				ur.save(u);
			}
			
			p.incrRCount();
			pr.save(p);
		
			
			Interaction it = ir.saveAndFlush(interaction);
			return it;
		}
		
		return null;	
	}
	
	public Page<Post> globalFeed(Integer PageNum, Integer Count, Sort sortby){
		System.out.println(PageNum);
		System.out.println(Count);
		Pageable p = PageRequest.of(PageNum, Count, sortby);
		return pr.findAll(p);
	}
	
	@GetMapping("/feed")
	public Page<Post> getFeed(@RequestParam(required = false, name="page") Integer page, @RequestParam(required = false, name = "p-count") Integer count, @RequestParam(required = false, name = "new") boolean nF){
		
		Authentication authentication = SecurityContextHolder.getContext()
			    .getAuthentication();
		//if(authentication instanceof AnonymousAuthenticationToken){
			Sort.Order o1 = Sort.Order.by("creationTime").with(Sort.Direction.DESC).nullsFirst();
			Sort.Order o2 = Sort.Order.by("rCount").with(Sort.Direction.DESC).nullsFirst();
			Sort s = Sort.by(o2);
			
			Sort sort = Sort.by("creationTime").descending();
					  //.and(Sort.by("rCount").descending());
			return globalFeed(page, count, s);
		//}else {
		
		//	return null;
		//}
	}
	
}
