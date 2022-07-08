package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	//회원가입
	public int insert(UserVo userVo) {
		System.out.println("userDao insert");

		int count = sqlSession.insert("user.insertUser", userVo);
		return count;
	}

	// 중복체크

	// 로그인 사용자 
	public UserVo selectUser(UserVo userVo) {
		System.out.println("userDao user");

		UserVo authUser = sqlSession.selectOne("user.selectUser", userVo);

		System.out.println("유저다오의: " + authUser);

		return authUser;
	}
}
