package kr.or.ddit.zip.main;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import kr.or.ddit.zip.service.IZipService;
import kr.or.ddit.zip.util.AlertUtil;
import kr.or.ddit.zip.vo.ZipVO;

public class ControllerZip {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cbDong;

    @FXML
    private TextField InputZip;

    @FXML
    private Button btnSearch;

    @FXML
    private TableView<ZipVO> dongTable;

    @FXML
    private TableColumn<ZipVO, String> zipcode;

    @FXML
    private TableColumn<ZipVO, String> sido;

    @FXML
    private TableColumn<ZipVO, String> gugun;

    @FXML
    private TableColumn<ZipVO, String> dong;

    @FXML
    private TableColumn<ZipVO, String> bunji;
    private IZipService service;
    private ZipVO zvo;
    
    @FXML
    void SearchZip(ActionEvent event) {
    	try {
    //버튼을 눌렀을때  텍스트 필드의 값을 service에 넣어줘서 비교한다. 	
    //0번째인덱스를 선택했을때 
    	List<ZipVO> zdata = new ArrayList<ZipVO>();
//    	service = ZipServiceImpl.getInstance();
    	
    	// 서비스 객체 생성
    	try {
			Registry reg = LocateRegistry.getRegistry(9999);
			service = (IZipService) reg.lookup("zipService");
				
    	}catch (NotBoundException e) {
				e.printStackTrace();
		}
		 catch (RemoteException e) {
			 e.printStackTrace();
		 }
		
    	  
    	if(cbDong.getSelectionModel().getSelectedIndex()==0) {
    		zdata = service.searchDong(InputZip.getText()); 
    		if(zdata==null || zdata.size() == 0) {
    			AlertUtil.warnMsg("경고", "찾는 정보가 없습니다.");
    		}
    	}
    	else {
    		 
    		zdata = service.searchZip(InputZip.getText());
    		if(zdata==null || zdata.size()== 0) {
    			AlertUtil.warnMsg("경고",	 "찾는 정보가 없습니다.");
    		}
    	}
    	ObservableList<ZipVO> zipdata = FXCollections.observableArrayList(zdata);
    	dongTable.setItems(zipdata);
    	
    	
    	}catch(Exception ee) {
    		ee.printStackTrace();
    	}
    }
    
    @FXML
    void initialize() {
    	cbDong.getItems().addAll("동이름","우편번호");
    	cbDong.setValue("동이름");
    	
    	zipcode.setCellValueFactory(new PropertyValueFactory<ZipVO, String>("zipcode"));
    	sido.setCellValueFactory(new PropertyValueFactory<ZipVO, String>("sido"));
    	gugun.setCellValueFactory(new PropertyValueFactory<ZipVO, String>("gugun"));
    	dong.setCellValueFactory(new PropertyValueFactory<ZipVO, String>("dong"));
    	bunji.setCellValueFactory(new PropertyValueFactory<ZipVO, String>("bunji"));
    	
    	cbDong.setButtonCell(new ListCell<String>() {
    		@Override
    		protected void updateItem(String item, boolean empty) {
    			// TODO Auto-generated method stub
    			super.updateItem(item, empty);
    			if(item==null || empty) {
    				setText(null); 
    			}else {
    				setText(item);
    			}
    		}
    	});
    }
}
