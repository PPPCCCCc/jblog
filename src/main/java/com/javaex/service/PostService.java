package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.dao.PostDao;
import com.javaex.vo.PostVo;

@Repository
public class PostService {
	
	@Autowired
	private PostDao postDao;
	
	//블로그 아이디로 카테고리 정보 가져오기
	public List<PostVo> getPost(String id) {
		
		List<PostVo> postList = postDao.getPost(id);
		
		return postList;
	}
	
	//등록
	public void postWrite(PostVo postVo) {
		
		postDao.postWrite(postVo);
	}
}
