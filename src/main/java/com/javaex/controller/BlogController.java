package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.service.PostService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.PostVo;

@Controller
public class BlogController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private PostService postService;

	// 블로그 메인화면
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String main(@PathVariable("id") String id, Model model) {
		System.out.println("blog controller");

		Map<String, Object> bMap = blogService.getBlogMain(id);

		model.addAttribute("bMap", bMap);

		return "/blog/blog-main";
	}

	// 블로그 관리
	@RequestMapping(value = "/{id}/admin/basic", method = { RequestMethod.GET, RequestMethod.POST })
	public String basicModify(@PathVariable("id") String id, Model model) {
		System.out.println("blogbasic controller");

		Map<String, Object> bMap = blogService.getBlogMain(id);

		model.addAttribute("bMap", bMap);

		return "blog/admin/blog-admin-basic";
	}

	// 기본설정변경 (블로그제목,로고이미지)
	@RequestMapping(value = "/{id}/admin/basic/upload", method = { RequestMethod.GET, RequestMethod.POST })
	public String basicUpload(@ModelAttribute BlogVo blogVo, @RequestParam("file") MultipartFile file, @PathVariable("id") String id) {
		System.out.println("blogbasic upload controller");

		blogService.upload(file, blogVo); 

		return "redirect:/" + id + "/admin/basic";
	}
	@RequestMapping(value = "/{id}/admin/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String post(@PathVariable("id") String id, Model model) {

		Map<String, Object> bMap = blogService.getBlogMain(id);
		model.addAttribute("bMap", bMap);

		List<PostVo> postList = postService.getPost(id);
		model.addAttribute("postList", postList);

		return "/blog/admin/blog-admin-write";
	}
	
}
