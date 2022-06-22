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
		List<GuestBookVo> guestList = guestbookDao.guestBookList();
		return guestList;
	}

	public int guestBookInsert(GuestBookVo guestbookVo) {

		return guestbookDao.guestBookInsert(guestbookVo);

	}
	public int guestBookDelete(GuestBookVo guestBookVo) {
		
	int count = guestbookDao.guestBookDelete(guestBookVo);
	return count;
		
	}
	public GuestBookVo getGuest(int no) {
		GuestBookVo guestbookVo = guestbookDao.getGuestBookList(no);
		return guestbookVo;
	}
}
