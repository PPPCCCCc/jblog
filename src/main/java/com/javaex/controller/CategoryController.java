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
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.BlogService;
import com.javaex.service.CategoryService;
import com.javaex.vo.CategoryVo;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BlogService blogService;
	
	//폼
	@RequestMapping(value = "/{id}/admin/category", method = { RequestMethod.GET, RequestMethod.POST })
	public String category(@PathVariable("id") String id, Model model) {

		Map<String, Object> bMap = blogService.getBlogMain(id);
		model.addAttribute("bMap", bMap);
		return "blog/admin/blog-admin-cate";
	}

	//리스트
	@ResponseBody
	@RequestMapping(value = "/{id}/admin/category/list", method = { RequestMethod.GET, RequestMethod.POST })
	public List<CategoryVo> cateList(@PathVariable("id") String id, Model model) {

		List<CategoryVo> cateList = categoryService.getCate(id);

		return cateList;
	}
	
	//추가
	@ResponseBody
	@RequestMapping(value = "{id}/admin/category/write", method = { RequestMethod.GET, RequestMethod.POST })
	public CategoryVo write(@ModelAttribute CategoryVo categoryVo, @PathVariable("id") String id) {

		CategoryVo resultCateVo = categoryService.writeCate(categoryVo);

		return resultCateVo;
	}
	
	//삭제
		@ResponseBody
		@RequestMapping(value = "{id}/admin/category/remove", method = {RequestMethod.GET, RequestMethod.POST})
		public boolean remove(@RequestParam("cateNo") int cateNo) {
			
			boolean delete= categoryService.remove(cateNo);
			
			return delete;
		}


}