package kr.or.ddit.jdbcboard.main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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

public class ContJdbcSearch {

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
    private Button btnDelete;

    @FXML
    private Button btnLookList;
    
    private JdbcboardServiceImpl service;
    private JdbcVO jvo;
    

    @FXML
    void DeleteClicked(ActionEvent event) {
    	
    	//삭제를 눌렀을 경우 해당 게시글이 삭제되고 리스트 화면으로 전환된다. 
    	
    	service.getInstance().deleteBoard(jvo.getBoard_no());
    	 
    	//메인화면으로 돌아가기 
    	try {
			Parent main = FXMLLoader.load(getClass().getResource("../fxml/FxmlJdbcboardMain.fxml"));
			Scene scene = new Scene(main);
			Stage primaryStage = (Stage) btnDelete.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

    }

    @FXML
    void LookListClicked(ActionEvent event) {
    	//리스트 보기 메인화면으로 돌아간다. 
    	try {
			Parent main = FXMLLoader.load(getClass().getResource("../fxml/FxmlJdbcboardMain.fxml"));
			Scene scene = new Scene(main);
			Stage primaryStage = (Stage) btnDelete.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }

//    private JdbcVO jvo = null;
    @FXML
    void UpdateClicked(ActionEvent event) {
    	//수정버튼을 누르면 update화면으로 넘어간다. 
    	try {
//			Parent main = FXMLLoader.load(getClass().getResource("../fxml/FxmlJdbcboardUpdate.fxml"));
//			Scene scene = new Scene(main);
//			Stage primaryStage = (Stage) btnDelete.getScene().getWindow();
//			primaryStage.setScene(scene);
    		
			FXMLLoader insert = new FXMLLoader(ContJdbcMain.class.getResource("../fxml/FxmlJdbcboardUpdate.fxml"));

			// 현재 선택된 게시글이 그대로 넘어가야한다. 
//			jvo=
			
			Parent childRoot = insert.load();
			ContJdbcUpdate contupdate = insert.getController();
			contupdate.showupdate(jvo); 
			Scene scene = new Scene(childRoot);
			Stage primaryStage = (Stage) btnUpdate.getScene().getWindow();
			primaryStage.setScene(scene);
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	

    } 
    
    public void showupdate(JdbcVO jvo) {
    	
    	// main에서 선택된 정보를 가지고 와서 정보를 보여준다. 
//    	List<JdbcVO> jdata = new ArrayList<JdbcVO>();
//    	jdata = (List<JdbcVO>) service.LookBoard(jvo.getBoard_no());
    	
//    	JdbcVO jdata = service.LookBoard(jvo.getBoard_no());
    	List<JdbcVO> jdata = new ArrayList<JdbcVO>();
    	
    	jdata = service.getAllBoard();   
    	
    	LabelWri.setText(jvo.getBoard_writer()); 
    	LabelDate.setText(jvo.getBoard_date());
    	TextTitle.setText(jvo.getBoard_title());
    	TextContent.setText(jvo.getBoard_content());
    	
    }

    @FXML
    void initialize() {
    	// 검색할 내용은 textfield로 한다. 
    	// 검색버튼을 누르면 검색결과가 테이블 뷰에 나타나도록 한다. 
    	// 검색 내용을 입력하지 않고 검색버튼을 누르면 전체 데이터가 나오도록 한다. 
    	// 새글 쓰기 버튼을 누르면 새글쓰기 화면으로 전환된다. 
    	
    	service = JdbcboardServiceImpl.getInstance();

    }
}
