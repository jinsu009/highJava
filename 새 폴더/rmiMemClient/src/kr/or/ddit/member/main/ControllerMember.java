package kr.or.ddit.member.main;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.util.AlertUtil;
import kr.or.ddit.member.vo.MemberVO;

public class ControllerMember {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField InputID;

    @FXML
    private TextField InputName;

    @FXML 
    private TextField InputTel;

    @FXML
    private TextField InputAdd;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnConfirm;

    @FXML
    private Button btnCancel;

    @FXML
    private TableView<MemberVO> memTable;

    @FXML
    private TableColumn<MemberVO, String> memId;

    @FXML
    private TableColumn<MemberVO, String> memName;

    @FXML
    private TableColumn<MemberVO, String> memTel;

    @FXML
    private TableColumn<MemberVO, String> memAdd;

    MemberVO memVO = null;
    
    
    boolean insert_mem = false;
    boolean update_mem = false;
    @FXML
    void memberAdd(ActionEvent event) {
    	//추가 버튼을 누르면 수정과 삭제는 비활성화가 되어야 하고 , 확인과 취소버튼이 활성화 되어야 한다.
    	//textfield도 비활성화상태에서 활성화로 변경시켜줘야 한다. 
    	
    		insert_mem = true;
    	
    		InputID.setDisable(false);
    		InputName.setDisable(false);
    		InputTel.setDisable(false);
    		InputAdd.setDisable(false);
    		
    		btnEdit.setDisable(true);
    		btnDelete.setDisable(true);
    		btnConfirm.setDisable(false);
    		btnCancel.setDisable(false);
    		
    		}
    
    @FXML
    void memberEdit(ActionEvent event) {
    	//수정 버튼을 누르면 추가와 삭제는 비활성화가 되어야 하고, 확인과 취소만 활성화가 되도록 한다. 
    	try {
    		memVO = new MemberVO();
    		
    		if(memTable.getSelectionModel().isEmpty()) {
    			AlertUtil.errorMsg("작업오류", "수정할 데이터를 선택하세요");
				return;
    		}

    		update_mem = true;
    		
	    	InputID.setDisable(true);
			InputName.setDisable(false);
			InputTel.setDisable(false);
			InputAdd.setDisable(false);
		
    		btnAdd.setDisable(true);
    		btnDelete.setDisable(true);
    		btnConfirm.setDisable(false);
    		btnCancel.setDisable(false);
    		
    		InputID.setText(memTable.getSelectionModel().getSelectedItem().getMem_id());
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @FXML
    void memberDelete(ActionEvent event) throws RemoteException {
    	//선택된 멤버의 정보가 삭제되도록 해야한다.
    	if(memTable.getSelectionModel().isEmpty()) {
    		AlertUtil.errorMsg("작업오류", "삭제할 데이터의 아이디를 선택하세요");
    		return;
    	}
    	
    	String memId = memTable.getSelectionModel().getSelectedItem().getMem_id();
    	if(AlertUtil.confirm("삭제여부확인", memId+" 정보를 정말 삭제 하시겠습니까?")) {
    	
//    		MemberServiceImpl.getInstance().deleteMember(memId);
    		service.deleteMember(memId);
    	
    		AlertUtil.infoMsg("작업결과", memId+" 씨 의 정보가 삭제되었습니다.");
    		setMemberData();
    	}
    	//삭제됨과 동시에 테이블에서 지워져야 하는데 지워지지 않음 
    }
    
    @FXML
    void memberOk(ActionEvent event) throws RemoteException {
    	// 평상시에는 비활성화 
    	// 추가 했을때 실행되는 부분과 수정했을때 실행되는 부분을 나누어 주어한다. 
    	
    	    memVO = new MemberVO();

    		btnAdd.setDisable(false);
    		btnEdit.setDisable(false);
    		btnDelete.setDisable(false);
    		btnConfirm.setDisable(true);
    		btnCancel.setDisable(true);
    		
    		String input_id = InputID.getText();
    		String input_name = InputName.getText();
    		String input_tel = InputTel.getText();
    		String input_add = InputAdd.getText();
    		
    		if(insert_mem == true)
    		{
    			if(input_id.isEmpty()||input_name.isEmpty()||input_tel.isEmpty()||input_add.isEmpty()) 
    			{
    				AlertUtil.errorMsg("입력오류", "빈 항목이 있습니다.");
    				return;
    			}
    			//id중복체크
    		
    			int count = service.getMemberCount(input_id);
    			if(count>0) {
    				AlertUtil.warnMsg("Id중복오류",input_id +"는 있는 회원 입니다. ");
    				return;
    			}
    				
    			memVO.setMem_id(InputID.getText());
        		memVO.setMem_name(InputName.getText());
        		memVO.setMem_tel(InputTel.getText());
        		memVO.setMem_addr(InputAdd.getText());
    			
//        		MemberServiceImpl.getInstance().insertMember(memVO);
        		service.insertMember(memVO);
        		
            	String memID = memTable.getSelectionModel().getSelectedItem().getMem_id();
            	AlertUtil.infoMsg("작업결과", memID+" 씨 의 정보가 등록되었습니다.");
            	setMemberData();
    		}
    		
    		
    		if(update_mem == true) {
    			if(input_name.isEmpty()||input_tel.isEmpty()||input_add.isEmpty()) {
    				AlertUtil.errorMsg("입력오류", "빈 항목이 있습니다.");
    				return;
    			}
    			String memID = memTable.getSelectionModel().getSelectedItem().getMem_id();
    			
    			memVO.setMem_name(InputName.getText());
        		memVO.setMem_tel(InputTel.getText());
        		memVO.setMem_addr(InputAdd.getText());
    			
        		memVO.setMem_id(memID);
        		
//    			MemberServiceImpl.getInstance().updateMember(memVO);
    			service.updateMember(memVO);
            	
            	AlertUtil.infoMsg("작업결과", memID+" 씨 의 정보가 수정되었습니다.");
            	setMemberData();
    		}
    		
    		InputID.setText("");
    		InputName.setText("");
    		InputTel.setText("");
    		InputAdd.setText("");
    	
    }

    @FXML
    void memberCancel(ActionEvent event) {
    	// 평상시에는 비활성화 
    	// 취소버튼을 눌렀을때 입력하고 있던 칸들이 reset되어야 한다. 
    	
    	 	insert_mem = false;
    	 	update_mem = false;
    	
    		btnAdd.setDisable(false);
    		btnEdit.setDisable(false);
    		btnDelete.setDisable(false);
    		btnConfirm.setDisable(true);
    		btnCancel.setDisable(true);
    		
    		InputID.setText("");
    		InputName.setText("");
    		InputTel.setText("");
    		InputAdd.setText("");
    }

    //서비스 객체 변수 선언  _ 선생님
    private IMemberService service;   // 생성자를 만들던가 initailize에 service객체를 생성한다. 
    private ObservableList<MemberVO> data;
   
    
    // 테이블에 변경된 DB를 띄워주도록 한다. 
    private void setMemberData() {
    	try {
    	List<MemberVO> memList = service.getAllMember();
    	data = FXCollections.observableArrayList(memList);
    	memTable.setItems(data); 
    
    	InputID.setText("");
		InputName.setText("");
		InputTel.setText("");
		InputAdd.setText("");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @FXML
    void initialize() {
    	//서비스객체 생성 _ 선생님
//    	service = MemberServiceImpl.getInstance();
    	try {
			Registry reg = LocateRegistry.getRegistry(9999);
			service = (IMemberService) reg.lookup("memService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	
    	
    	InputID.setDisable(true);
		InputName.setDisable(true);
		InputTel.setDisable(true);
		InputAdd.setDisable(true);
    	
    	//List<MemberVO> memList = service.getAllMember();
    	
    	//tableview에 셋팅될 observableList객체 변수      
    	//ObservableList<MemberVO> data = FXCollections.observableArrayList(memList);
    	
    	//memTable.setItems(data); 
    	
    	//tableview의 각 컬럼고 vo객체의 멤버변수를 매핑 시킨다. 
    	memId.setCellValueFactory(new PropertyValueFactory<MemberVO, String>("mem_id"));
    	memName.setCellValueFactory(new PropertyValueFactory<MemberVO, String>("mem_name"));
    	memTel.setCellValueFactory(new PropertyValueFactory<MemberVO, String>("mem_tel"));
    	memAdd.setCellValueFactory(new PropertyValueFactory<MemberVO, String>("mem_addr"));

    	setMemberData();
    	
    }
}
