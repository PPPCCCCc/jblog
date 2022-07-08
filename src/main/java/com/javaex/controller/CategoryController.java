package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.CategoryService;
import com.javaex.vo.CategoryVo;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/{id}/admin/category/list", method = { RequestMethod.GET, RequestMethod.POST })
	public List<CategoryVo> cateList(@PathVariable("id") String id, Model model){
		System.out.println("카테고리 리스트");
		
		List<CategoryVo> cateList = categoryService.getList(id);
		
		return cateList;
	}

}
