package kr.or.ddit.zip.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.zip.dao.IZipDao;
import kr.or.ddit.zip.dao.ZipDaoImpl;
import kr.or.ddit.zip.vo.ZipVO;

public class ZipServiceImpl implements IZipService{

	private IZipDao dao;
	
	private static ZipServiceImpl service;
	
	public ZipServiceImpl() {
		dao = ZipDaoImpl.getInstance();
	}
	
	public static ZipServiceImpl getInstance() {
		if(service==null) service = new ZipServiceImpl();
		return service;
	}
	
	@Override
	public List<ZipVO> searchZip(String searnum) {
		// TODO Auto-generated method stub
		return dao.searchZip(searnum);
	}

	@Override
	public List<ZipVO> searchDong(String Dong) {
		// TODO Auto-generated method stub
		return dao.searchDong(Dong);
	}

}
