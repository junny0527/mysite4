package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestBookVo;

@Controller
@RequestMapping(value="/api/guestbook")
public class ApiGuestbookController {
	@Autowired
	private GuestService guestService;
	
	@RequestMapping(value = "/addlist", method = {RequestMethod.GET , RequestMethod.POST})
	public String addlist() {
		
		System.out.println("ApiGuestbookController > addlist()");
		
		return "apiGuestbook/addlist";
	}
	  //벙명록 저장2
	   @ResponseBody
	   @RequestMapping(value="/add2",method= {RequestMethod.GET,RequestMethod.POST})
	   public GuestBookVo add2(@RequestBody GuestBookVo guestbookVo) {
	      System.out.println("apiGuestBookController > add2");
	      
	      GuestBookVo gVo = guestService.addGuest(guestbookVo);
	      
	      System.out.println(gVo);
	      
	      return gVo;
	   }
	
	//방명록 리스트 데이터만 요청하기
	@ResponseBody
	@RequestMapping(value = "/list", method = {RequestMethod.GET , RequestMethod.POST})
	public List<GuestBookVo> list() {
		System.out.println("ApiGuestbookController > list()");
		List<GuestBookVo> guestbookList = guestService.getGuestList();
		
		return guestbookList;
	}
	//추가 
	@RequestMapping(value = "/add", method = {RequestMethod.GET , RequestMethod.POST})
	public GuestBookVo add(@ModelAttribute GuestBookVo guestBookVo) {
		System.out.println("ApiGuestbookController > add()");
		//저장하고 저장된값 리턴
				GuestBookVo gVo = guestService.addGuest(guestBookVo);
				System.out.println(gVo);
				
				return gVo;
	}
	//방명록 삭제
		@ResponseBody
		@RequestMapping(value="/remove", method = {RequestMethod.GET, RequestMethod.POST})
		public String remove(@ModelAttribute GuestBookVo guestBookVo) {
			System.out.println("ApiGuestbookController>remove()");
			
			String state = guestService.removeGuest(guestBookVo);
			
			return state;
		}
}
