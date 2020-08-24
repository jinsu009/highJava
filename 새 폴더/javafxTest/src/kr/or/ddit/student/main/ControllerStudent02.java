package kr.or.ddit.student.main;

import java.net.URL;
import java.util.ResourceBundle;

import basic.util.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import kr.or.ddit.student.service.StudentServiceImpl;
import kr.or.ddit.student.vo.StudentVO;

public class ControllerStudent02 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputKor;

    @FXML
    private TextField inputMat;

    @FXML
    private TextField inputEng;

    @FXML
    private Button btninsert;

    @FXML
    private Button btnCancle;

    @FXML
    void CancleClick(ActionEvent event) {
    	inputName.setText("");
    	inputKor.setText("");
    	inputMat.setText("");
    	inputEng.setText("");
    	
    	
    }
    
    private StudentServiceImpl service;
    StudentVO stuvo = null;

    @FXML
    void InsertClick(ActionEvent event) {
    	
    	stuvo = new StudentVO();
    	
    	String input_nm = inputName.getText();
    	String input_kr = inputKor.getText();
    	String input_ma = inputMat.getText();
    	String input_en = inputEng.getText();
    	
    	
    	if(input_nm.isEmpty()||input_kr.isEmpty()||input_ma.isEmpty()||input_en.isEmpty()) 
		{
			AlertUtil.errorMsg("입력오류", "빈 항목이 있습니다.");
			return;
		}
    	
//    	int count = service.getStuCount(input_nm);
//		if(count>0) {
//			AlertUtil.warnMsg("Id중복오류",input_nm +"는 있는 회원 입니다. ");
//			return;
//		}    	
		
		//정보 저장 
		stuvo.setStd_name(inputName.getText());
		stuvo.setStd_kor(Integer.parseInt(inputKor.getText()));
		stuvo.setStd_mat(Integer.parseInt(inputMat.getText()));
		stuvo.setStd_eng(Integer.parseInt(inputEng.getText()));
    	
		StudentServiceImpl.getInstance().insertStudent(stuvo);
		
		inputName.setText("");
    	inputKor.setText("");
    	inputMat.setText("");
    	inputEng.setText("");
    	
    	
    }

    @FXML
    void initialize() {
    	
    	// 학생들의 점수 입력창 
    	inputName.setDisable(false);
    	inputKor.setDisable(false);
    	inputMat.setDisable(false);
    	inputEng.setDisable(false);

    }
}
