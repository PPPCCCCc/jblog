package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;

@Repository
public class CategoryDao {
	
	@Autowired SqlSession sqlSession;
	
	public List<CategoryVo>  getcateList(String id){
		System.out.println("카테고리 리스트 dao");
		List<CategoryVo> cateList = sqlSession.selectList("category.cateSelect",id);
		
		return cateList;
	}
}
