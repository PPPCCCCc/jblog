package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	public List<CategoryVo> getCate(String id) {

		List<CategoryVo> cateList = categoryDao.getCate(id);

		return cateList;
	}

	public CategoryVo writeCate(CategoryVo categoryVo) {

		int count = categoryDao.writeCate(categoryVo);

		int cateNo = categoryVo.getCateNo();

		CategoryVo resultCateVo = categoryDao.selectCategory(cateNo);

		return resultCateVo;
	}

	public boolean remove(int cateNo) {
		boolean delete = false;
		int count = categoryDao.removeCategory(cateNo);

		if (count > 0) {
			System.out.println("삭제완료");
			delete = true;
		} else
			System.out.println("삭제실패");

		return delete;
	}
}
