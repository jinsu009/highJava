package kr.or.ddit.jdbcboard.main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import basic.util.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kr.or.ddit.jdbcboard.service.JdbcboardServiceImpl;
import kr.or.ddit.jdbcboard.vo.JdbcVO;

public class ContJdbcUpdate {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label LabelWri;

    @FXML
    private Label LabelDate;

    @FXML
    private TextField TextTitle;

    @FXML
    private TextArea TextContent;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnCancle;

    @FXML
    void CancleClicked(ActionEvent event) {
    	
    	AlertUtil.infoMsg("정보창", "게시글 수정이 취소 되었습니다.");
    	//내용보기 화면으로 전환
    	try {
			Parent main = FXMLLoader.load(getClass().getResource("../fxml/FxmlJdbcboardSearch.fxml"));
			Scene scene = new Scene(main);
			Stage primaryStage = (Stage) btnCancle.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    
    private JdbcboardServiceImpl service;
    private JdbcVO jvo; 

    @FXML
    void UpdateClicked(ActionEvent event) {
    	//main list 화면으로 돌아가는 부분
    	
    	
    	// 수정이 안됨 
    	
    	service.getInstance().updateBoard(jvo);
    	
    	AlertUtil.infoMsg("알림창", "내용이 수정되었습니다.");
    	
    	try {
			Parent main = FXMLLoader.load(getClass().getResource("../fxml/FxmlJdbcboardMain.fxml"));
			Scene scene = new Scene(main);
			Stage primaryStage = (Stage) btnCancle.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

    } 
    
    public void showupdate(JdbcVO jvo) {
    	
    	// main에서 선택된 정보를 가지고 와서 정보를 보여준다. 
    	
    	
    	List<JdbcVO> jdata = new ArrayList<JdbcVO>();
    	
    	jdata = service.getAllBoard();   	 
    	
    	LabelWri.setText(jvo.getBoard_writer()); 
    	LabelDate.setText(jvo.getBoard_date());
    	TextTitle.setText(jvo.getBoard_title());
    	TextContent.setText(jvo.getBoard_content());
    	
    }
    
    @FXML
    void initialize() { 
    	//수정
    	// 제목 내용만 편집할 수 있도록 한다. 
    	// 저장 버튼을 누르면 리스트 화면으로 전환한다. 
    	// 취소버튼을 누르면 내용보기 화면으로 전환된다. 
    	// 전 화면에서 내가 선택한 게시글의 정보를 들고 올 수있는 것이 중요
    	service = JdbcboardServiceImpl.getInstance();
    }
}
