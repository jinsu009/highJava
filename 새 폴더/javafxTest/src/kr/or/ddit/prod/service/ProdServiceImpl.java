package kr.or.ddit.prod.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.prod.dao.IProdDao;
import kr.or.ddit.prod.dao.ProdDaoImpl;
import kr.or.ddit.prod.vo.LprodVO;
import kr.or.ddit.prod.vo.ProdVO;

public class ProdServiceImpl implements IProdService{
	
	private IProdDao dao;
	
	private static ProdServiceImpl service;
	
	private ProdServiceImpl() {
		dao = ProdDaoImpl.getInstance();
	}
	
	public static ProdServiceImpl getInstance() {
		if(service==null) {
			service = new ProdServiceImpl();
		}
		return service;
	}

	@Override
	public List<LprodVO> searchLprod() {
		// TODO Auto-generated method stub
		return dao.searchLprod();
	}

	@Override
	public List<ProdVO> searchProdLgu(String searchMap_Prodlgu) {
		// TODO Auto-generated method stub
		return dao.searchProdLgu(searchMap_Prodlgu);
	}

	@Override
	public List<ProdVO> searchPordId(String searchMap_ProdId) {
		// TODO Auto-generated method stub
		return dao.searchPordId(searchMap_ProdId);
	}
	

}
