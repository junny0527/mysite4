package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@Service
public class GuestService {
	@Autowired
	private GuestBookDao guestbookDao;

	public List<GuestBookVo> getGuestList() {
		System.out.println("GuestBookService > getGuestList()");
		List<GuestBookVo> guestList = guestbookDao.guestBookList();
		return guestList;
	}

	public int guestBookInsert(GuestBookVo guestbookVo) {
		System.out.println("GuestBookService > guestBookInsert()");
		return guestbookDao.guestBookInsert(guestbookVo);

	}
	//ajax용
	public GuestBookVo addGuest(GuestBookVo guestbookVo) {
		System.out.println("GuestBookService > addGuest()");
		//저장
				System.out.println("전-->" + guestbookVo);
				int count = guestbookDao.insertguest(guestbookVo);
				System.out.println("후-->" + guestbookVo);
				
				int no =guestbookVo.getNo();
				
				//방금저장한 1개의 데이터를 가져온다
				GuestBookVo gVo = guestbookDao.getGuest(no);
				
				return gVo;

	}
	public int guestBookDelete(GuestBookVo guestBookVo) {
		System.out.println("GuestBookService > guestBookDelete()");
		int count = guestbookDao.guestBookDelete(guestBookVo);
		return count;
		
	}
	public GuestBookVo getGuest(int no) {
		System.out.println("GuestBookService > getGuest()");
		GuestBookVo guestbookVo = guestbookDao.getGuestBookList(no);
		return guestbookVo;
	}
}
