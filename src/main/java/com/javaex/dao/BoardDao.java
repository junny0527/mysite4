package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlSession;

	// 리스트
	public List<BoardVo> getBoardList() {
		System.out.println("BoardDao > getBoardList");

		List<BoardVo> boardList = sqlSession.selectList("board.BoardList");

		return boardList;

	}

	// 글쓰기
	public int write(BoardVo boardVo) {
		System.out.println("BoardDao > write");
		int count = sqlSession.insert("board.BoardWrite", boardVo);

		return count;
	}

	// 한명 정보 가져오기
	public BoardVo getBoard(int no) {
		System.out.println("BoardDao > getBoard");
		BoardVo bVo = sqlSession.selectOne("board.getBoard", no);
		return bVo;
	}

	// 수정
	public int modify(BoardVo boardVo) {
		System.out.println("boardDao > modify");
		int count = sqlSession.update("board.boardModify", boardVo);

		return count;
	}

	// 조회수
	public int hitUpdate(int no) {
		System.out.println("boardDao > hitUpdate");
		int count = sqlSession.update("board.hitUpdate", no);

		return count;
	}

	// 삭제
	public int delete(int no) {
		System.out.println("boardDao > delete");
		int count = sqlSession.delete("board.boardDelete", no);

		return count;
	}

	// 검색기능
	public List<BoardVo> listSearch(String title) {
		System.out.println("boardDao > listSearch");
		List<BoardVo> boardList = sqlSession.selectList("board.boardSearch", title);

		return boardList;
	}

}
