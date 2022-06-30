package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.FileDao;
import com.javaex.vo.FileVo;

@Service
public class FileService {
	
	  @Autowired private FileDao fileDao;
	 
	
	public String save(MultipartFile file) {
		System.out.println("FileService>save()");
		
		String saveDir = "C:\\javaStudy\\upload";
		
		System.out.println(file.getOriginalFilename());
		
		//파일 정보 (DB저장) 추출 저장
		//오리지날 파일명 , 저장경로+파일(랜덤)명,파일 사이즈	
		
		String orgName = file.getOriginalFilename();
		
		//확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		//System.out.println(exName);
		
		//저장파일명
		String saveName = UUID.randomUUID().toString() + System.currentTimeMillis()+exName;
		//System.out.println(saveName);
		
		//파일경로(파일경로+저장파일명)
		String filePath = saveDir +"\\"+ saveName;
		System.out.println(filePath);
		
		//파일 사이즈
		long fileSize = file.getSize();
		//Vo로묶기
		FileVo fileVo = new FileVo(orgName,saveName,filePath,fileSize);
		System.out.println(fileVo);
		
		//DB저장 -->과제
		fileDao.picSave(fileVo);
		//(2)파일 저장
		try {
			byte[] fileData = file.getBytes();
			OutputStream os= new FileOutputStream(filePath); 
				
			BufferedOutputStream bos = new BufferedOutputStream(os);
			bos.write(fileData);
			
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return saveName;
		
		}
}
