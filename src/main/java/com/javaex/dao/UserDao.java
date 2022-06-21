package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	//필드
	@Autowired
	private SqlSession sqlSession;
	//생성자- 디폴트 생성자
	
	//메소드 gs
	
	//메소드 일반
	
	
	
	// 회원정보 1명 가져오기(로그인용)
		public UserVo getUser(UserVo userVo) {
			System.out.println("UserDao.getUser()");
			return sqlSession.selectOne("user.getUser", userVo);
		}
		// 저장 메소드
		public int userInsert(UserVo userVo) {
			System.out.println("UserDao.userInsert()");
			int count = sqlSession.insert("user.userInsert", userVo);
			return count;
		}
		// 회원정보 가져오기
		public UserVo getUsers(int no) {
			System.out.println("UserDao.insert()");
			return sqlSession.selectOne("user.getUsers", no);
		}
		//업데이트
		public int userUpdate(UserVo userVo) {
			System.out.println("UserDao.userUpdate()]");
			int count = sqlSession.update("user.userUpdate", userVo);
			return count;
		}
}
