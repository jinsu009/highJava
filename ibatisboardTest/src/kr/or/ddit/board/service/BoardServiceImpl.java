package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.BoardDaoInter;
import kr.or.ddit.board.vo.BoardVo;

public class BoardServiceImpl implements BoardServiceInter{
	
	private BoardDaoInter dao;
	
	private static BoardServiceImpl service;
	
	private BoardServiceImpl(){
	
		dao = BoardDaoImpl.getInstance();
	}
	
	public static BoardServiceImpl getInstance(){
		if(service==null) service = new BoardServiceImpl();
		return service;
	}

	@Override
	public List<BoardVo> getAllList() {
		return dao.getAllList();
	}

	@Override
	public int insertBoard(BoardVo boardVo) {
		return dao.insertBoard(boardVo);
	}

	@Override
	public BoardVo LookBoard(int board_no) {
		return dao.LookBoard(board_no);
	}

	@Override
	public List<BoardVo> searchBoard(String board_title) {
		return dao.searchBoard(board_title);
	}

	@Override
	public int updateBoard(BoardVo boardVo) {
		return dao.updateBoard(boardVo);
	}

	@Override
	public int deleteBoard(int board_no) {
		return dao.deleteBoard(board_no);
	}

	@Override
	public int BoardCount(int board_no) {
		return dao.BoardCount(board_no);
	}

}
