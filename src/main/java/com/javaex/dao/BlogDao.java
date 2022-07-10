package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;

	//회원정보
	public void insertBlog(UserVo userVo) {

		String id = userVo.getId();
		String blogTitle = userVo.getUserName() + "의 블로그입니다.";

		BlogVo blogVo = new BlogVo(id, blogTitle);

		sqlSession.insert("blog.insertBlog", blogVo);
	}

	//id로 블로그 
	public BlogVo getBlog(String id) {

		BlogVo blogVo = sqlSession.selectOne("blog.getBlog", id);

		return blogVo;
	}

	//블로그 업로드
	public void upload(BlogVo blogVo) {

		sqlSession.update("blog.basicUpload", blogVo);
	}

	// 타이틀 업로드
	public void uploadTitle(BlogVo blogVo) {

		sqlSession.update("blog.titleUpload", blogVo);
	}
}
