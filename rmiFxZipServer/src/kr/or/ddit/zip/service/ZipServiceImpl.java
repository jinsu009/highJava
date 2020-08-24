package kr.or.ddit.zip.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import kr.or.ddit.zip.dao.IZipDao;
import kr.or.ddit.zip.dao.ZipDaoImpl;
import kr.or.ddit.zip.vo.ZipVO;

public class ZipServiceImpl extends UnicastRemoteObject implements IZipService{

	private IZipDao dao;
	
	private static ZipServiceImpl service;
	
	public ZipServiceImpl() throws RemoteException {
		dao = ZipDaoImpl.getInstance();
	}
	
	public static ZipServiceImpl getInstance() {
			try {
				if(service==null)
				service = new ZipServiceImpl();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		return service;
	}
	
	@Override
	public List<ZipVO> searchZip(String searnum) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.searchZip(searnum);
	}

	@Override
	public List<ZipVO> searchDong(String Dong) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.searchDong(Dong);
	}

}
