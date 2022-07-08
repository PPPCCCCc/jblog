package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;

	//블로그 아이디
	public BlogVo getBlogAdmin(String id) {

		BlogVo blogVo = blogDao.getBlog(id);

		return blogVo;
	}

	// 서비스 가져오기
	public Map<String, Object> getBlogMain(String id) {

		BlogVo blogVo = blogDao.getBlog(id);

		Map<String, Object> bMap = new HashMap<String, Object>();

		bMap.put("blogVo", blogVo);

		return bMap;
	}

	// 업로드
	public void upload(MultipartFile file, BlogVo blogVo) {
		// 파일사이즈
		long fileSize = file.getSize();

		if (fileSize > 0) {

			String saveDir = "C:\\javaStudy\\upload\\";

			// 오리지널파일명
			String orgName = file.getOriginalFilename();
			// 확장자
			String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			// 저장파일명
			String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
			// 파일저장경로
			String filePath = saveDir + saveName;
			
			blogVo.setLogoFile(saveName);

			// 파일저장
			try {
				byte[] fileDate = file.getBytes();
				OutputStream out = new FileOutputStream(filePath);
				BufferedOutputStream bout = new BufferedOutputStream(out);

				bout.write(fileDate);
				bout.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

			blogDao.upload(blogVo);
		} else {

			blogDao.uploadTitle(blogVo);
		}
	}
}
