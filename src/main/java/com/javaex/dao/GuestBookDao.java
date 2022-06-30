package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestBookVo;

@Repository
public class GuestBookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestBookVo> guestBookList() {
		System.out.println("GuestBookDao > guestBookList()");
		List<GuestBookVo>  guestBookList = sqlSession.selectList("guestbook.selectList");
		System.out.println(guestBookList);

		return guestBookList;
	}
	public int guestBookInsert(GuestBookVo guestBookVo) {
		System.out.println("GuestBookDao > guestBookInsert()");

		int count = sqlSession.insert("guestbook.guestBookInsert", guestBookVo);

		return count;
	}
	public int insertguest(GuestBookVo guestBookVo) {
		System.out.println("GuestBookDao > insertguest()");
		System.out.println(guestBookVo);
		int count = sqlSession.insert("guestbook.insertSelectKey", guestBookVo);

		return count;
	}
	//ajax
	public GuestBookVo getGuest(int no) {
		System.out.println("GuestbookDao>getGuest()");
		GuestBookVo bVo = sqlSession.selectOne("guestbook.getGuest", no);
		System.out.println(bVo);
		return bVo;
		
	}
	//ajax방명록 삭제
			public int guestDelete(GuestBookVo guestbookVo) {
				System.out.println("GuestbookDao>guestDelete()");
				System.out.println(guestbookVo);
				return sqlSession.delete("guestbook.delete", guestbookVo);
				
			}
	


	public int guestBookDelete(GuestBookVo guestBookVo) {
		System.out.println("GuestBookDao > guestBookDelete()");

		 int count =sqlSession.delete("guestbook.guestBookDelete", guestBookVo);
		 
		 return count;
		
	}
	public GuestBookVo getGuestBookList(int no) {
		System.out.println("GuestBookDao > getGuestBookList()");
		GuestBookVo getbookList = sqlSession.selectOne("guestbook.getguestBookList",no);
		System.out.println(getbookList);

		return getbookList;
	}
	public int guestBookUpdate(GuestBookVo guestBookVo) {
		System.out.println("GuestBookDao > guestBookUpdate()");

		int count = sqlSession.update("guestbook.guestBookUpdate", guestBookVo);

		return count;
	}
	

}
