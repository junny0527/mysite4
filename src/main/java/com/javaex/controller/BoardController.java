package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	// 필드
	@Autowired
	BoardService boardService;

	//리스트
		@RequestMapping(value="/list", method= {RequestMethod.GET,RequestMethod.POST})
		public String list(Model model) {
			System.out.println("BoardCotroller > List");
			List<BoardVo> boardList = boardService.getBoardList();
			System.out.println(boardList);
			model.addAttribute("boardList",boardList);
			
			return "/board/list";
		}
		//글쓰기폼
		@RequestMapping(value="/writeForm",method= {RequestMethod.GET,RequestMethod.POST})
		public String writeForm() {
			System.out.println("BoardCotroller > writeForm");
			
			return "/board/writeForm";
		}
		//글쓰기
		@RequestMapping(value="/write" ,method = {RequestMethod.GET,RequestMethod.POST})
		public String write(@ModelAttribute BoardVo boardVo, HttpSession session) {
			System.out.println("BoardController > write");
			
			
			UserVo authuser = (UserVo)session.getAttribute("authUser");
			int no = authuser.getNo();
			boardVo.setUserNo(no);
			
			boardService.write(boardVo);
			return "redirect:/board/list";
		}
		//글 불러오기
		@RequestMapping(value="/read",method= {RequestMethod.GET,RequestMethod.POST})
		public String read(@RequestParam("no") int no ,Model model) {
			System.out.println("BoardController > read");
			
			BoardVo boardVo = boardService.getBoard(true,no);
			
			System.out.println(boardVo);
			model.addAttribute("boardVo" , boardVo);
			
			
			return "/board/read";
		}
		//수정폼
		@RequestMapping(value="/modifyForm", method= {RequestMethod.GET,RequestMethod.POST})
		public String modifyForm(@RequestParam("no") int no ,Model model) {
			System.out.println("BoardController > modifyForm");
			
			BoardVo boardVo = boardService.getBoard(false,no);
			System.out.println(boardVo);
			
			model.addAttribute("boardVo", boardVo);
			return "/board/modifyForm";
		}
		//수정
		@RequestMapping(value="/modify" ,method= {RequestMethod.GET,RequestMethod.POST})
		public String modify(@ModelAttribute BoardVo boardVo) {
			System.out.println("BoardController > modify");
			boardService.modify(boardVo);
			System.out.println(boardVo);
			
			return "redirect:/board/list";
		}
		//삭제
		@RequestMapping(value="/delete", method = {RequestMethod.GET,RequestMethod.POST})
		public String delete(@RequestParam("no") int no ) {
			System.out.println("BoardController > delete");
			boardService.delete(no);
			return "redirect:/board/list";
		}
		//리스트 불러오기 (검색기능 +)
		   @RequestMapping(value="/search", method = {RequestMethod.GET, RequestMethod.POST})
		   public String list(Model model, @RequestParam(value = "keyword", defaultValue = "", required = false) String word) {
		      System.out.println("BoardController > list");
		      
		      List<BoardVo> bList = boardService.listSearch(word);
		      model.addAttribute("bList",bList);
		      return "board/list";
		   }
		   
		
}
