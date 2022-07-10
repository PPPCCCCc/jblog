package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.vo.PostVo;

@Service
public class PostDao {

	@Autowired
	private SqlSession sqlSession;

	public List<PostVo> getPost(String id) {

		List<PostVo> postList = sqlSession.selectList("post.getPost", id);

		return postList;
	}

	//등록
	public void postWrite(PostVo postVo) {

		sqlSession.insert("postWrite", postVo);
	}
}
