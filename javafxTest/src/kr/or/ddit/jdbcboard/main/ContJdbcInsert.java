package kr.or.ddit.jdbcboard.main;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import basic.util.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kr.or.ddit.jdbcboard.dao.JdbcboardDaoImpl;
import kr.or.ddit.jdbcboard.vo.JdbcVO;

public class ContJdbcInsert {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField TextWriter;

    @FXML
    private TextField TextTitle;

    @FXML
    private TextArea TextContent;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancle;

    @FXML
    void CancleClicked(ActionEvent event) {
    	AlertUtil.infoMsg("작업결과", "작성이 취소되었습니다.");
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

    private JdbcboardDaoImpl service;
    private JdbcVO jvo = null;
    
    @FXML
    void SaveClicked(ActionEvent event) {

    	jvo = new JdbcVO();
    	
    	String title = TextTitle.getText();
    	String wri = TextWriter.getText();
    	String con = TextContent.getText();
    	
    	if(title.isEmpty()||wri.isEmpty()||con.isEmpty()) {
    		AlertUtil.errorMsg("입력오류", "빈 항목이 있습니다.");
    		return;
    	}
    	
    	jvo.setBoard_title(TextTitle.getText());
    	jvo.setBoard_writer(TextWriter.getText());
    	jvo.setBoard_content(TextTitle.getText());
    	
    	service.getInstance().insertBoard(jvo);
    	AlertUtil.infoMsg("작업결과", "게시글이 등록되었습니다.");
    	
    	// 게시글이 수정되고 main list 화면으로 돌아가는 부분
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
    

    @FXML
    void initialize() { 
    	
    	// 새글쓰기 화면 
    	// 제목, 작성자, 내용을 입력할 수 있도록 한다. 
    	// 제목, 작성자는 textfiled로 하고 내용은 textarea로 구성 
    	// 저장버튼을 누르면 DB에 글번호, 제목, 작성자, 작성날짜를 저장하고 리스트화면으로 전환
    	// 취소버튼을 누르면 리스트화면으로 전환한다. 
    	
    	

    }
}
