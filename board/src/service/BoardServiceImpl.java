package service;

import java.util.List;
import java.util.Map;
import dao.BoardDaoImpl;
import dao.BoardDaoInter;
import vo.BoardVo;

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
	public List<BoardVo> searchBoard(BoardVo boardVo) {
		return dao.searchBoard(boardVo);
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
