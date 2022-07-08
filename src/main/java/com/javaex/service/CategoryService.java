package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired CategoryDao categoryDao;
	
	public List<CategoryVo> getList(String id){
		System.out.println("카테고리 리스트서비스");
		
		List<CategoryVo> cateList = categoryDao.getcateList(id);
		System.out.println(cateList);
		
		return cateList;
	}
}
