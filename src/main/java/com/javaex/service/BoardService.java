package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;

	// 리스트가져오기
	public List<BoardVo> getBoardList() {
		System.out.println("BoardService > getBoardList");

		List<BoardVo> boardList = boardDao.getBoardList();

		return boardList;
	}

	// 글쓰기
	public int write(BoardVo boardVo) {
		System.out.println("BoardService > write");
		int count = boardDao.write(boardVo);
		System.out.println(count + "건이 등록되었습니다");

		return count;
	}

	// 한명 가져오기
	public BoardVo getBoard(boolean up,int no) {
		System.out.println("BoardService > getBoard");
		if (up) {
		boardDao.hitUpdate(no);
		}
		BoardVo bVo = boardDao.getBoard(no);
		return bVo;
	}

	// 수정
	public int modify(BoardVo boardVo) {
		System.out.println("BoardService > modify");
		int count = boardDao.modify(boardVo);
		System.out.println(count + "건 변경되었습니다");

		return count;
	}

	// 삭제
	public int delete(int no) {
		System.out.println("BoardService> delete");
		int count = boardDao.delete(no);
		System.out.println(count + "건 삭제되었습니다");
		return count;
	}

	// 검색
	public List<BoardVo> listSearch(String word) {
		System.out.println("BoardService > listSearch");
		List<BoardVo> bList = boardDao.listSearch(word);

		return bList;
	}
}
