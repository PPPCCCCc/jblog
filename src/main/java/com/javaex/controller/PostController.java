package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.PostService;
import com.javaex.vo.PostVo;

@Controller
public class PostController {

	@Autowired
	private PostService postService;

	@RequestMapping(value = "/{id}/admin/basic/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String postWrite(@ModelAttribute PostVo postVo, @PathVariable("id") String id, Model model) {
		System.out.println(postVo);

		model.addAttribute("blogId", id);

		postService.postWrite(postVo);
		return "redirect:/" + id + "/admin/writeForm";
	}
}
