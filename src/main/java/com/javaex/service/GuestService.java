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

	public int guestBookInsert(GuestBookVo guestBookVo) {
		System.out.println("GuestBookService > guestBookInsert()");
		return guestbookDao.guestBookInsert(guestBookVo);

	}
	//ajax용
	public GuestBookVo addGuest(GuestBookVo guestBookVo) {
		System.out.println("GuestBookService > addGuest()");
		//저장
				System.out.println("전-->" + guestBookVo);
				guestbookDao.insertguest(guestBookVo);
				System.out.println("후-->" + guestBookVo);
				
				int no =guestBookVo.getNo();
				System.out.println(no);
				//방금저장한 1개의 데이터를 가져온다
				GuestBookVo gVo = guestbookDao.getGuest(no);
				
				return gVo;

	}
	//방명록 삭제
		public String removeGuest(GuestBookVo guestBookVo) {
			System.out.println("GuestBookService>removeGuest()");
			
			String state;
			
			int count = guestbookDao.guestDelete(guestBookVo);
			
			if(count>0) {
				state = "succeess";
			}else {
				state = "fail";
			}
			
			return state;
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
