package kr.or.ddit.jdbcboard.main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.helpers.Loader;

import basic.util.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import kr.or.ddit.jdbcboard.service.JdbcboardServiceImpl;
import kr.or.ddit.jdbcboard.vo.JdbcVO;

public class ContJdbcMain {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnInsert;

    @FXML
//    private ComboBox<JdbcVO> ComSear;
    private ComboBox<String> ComSear;

    @FXML
    private TextField TextSear;

    @FXML
    private Button btnSear;
    
    @FXML
    private TableView<JdbcVO> BoardTable;

    @FXML
    private TableColumn<JdbcVO, Integer> ColNo;

    @FXML
    private TableColumn<JdbcVO, String> ColTitle;

    @FXML
    private TableColumn<JdbcVO, String> ColWri;

    @FXML
    private TableColumn<JdbcVO, String> ColDate;
    
    @FXML
    private TableColumn<JdbcVO, Integer> Colcnt;

    @FXML
    private Pagination pagination;

    private JdbcVO jvo;
    private JdbcboardServiceImpl service;
   
    @FXML
    void BoardInsertClicked(ActionEvent event) {
    	//새글쓰기 버튼을 누르면 
    	// insertfxml화면으로 전환되도록한다. 
    	
    	try {
			Parent insert = FXMLLoader.load(getClass().getResource("../fxml/FxmlJdbcboardinsert.fxml"));
			Scene scene = new Scene(insert);
			Stage primaryStage = (Stage) btnSear.getScene().getWindow();
			primaryStage.setScene(scene);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void BoardSearchClicked(ActionEvent event) {

    	//버튼을클릭하면 텍스트 필드의 있는 값을 가져와서 select문에 넘겨주고 해당 정보가 일치하는 게시글만 테이블에 보여지도록 한다. 
    	List<JdbcVO> jdata = new ArrayList<JdbcVO>();
    	service =  JdbcboardServiceImpl.getInstance();
    	
    	//데이터를 보낼 키값을 담을 map이필요
    	ObservableList<JdbcVO> jdbcdata = FXCollections.observableArrayList(jdata);
    	
    	String com = "";
    	
    	// 만약 콤보박스에서 선택한 값이 "이름" 이라면 com안에 board_writer을 담아서 
    	// map으로 sql문의 combox에 board_writer값을 넘겨준다. 
    	if(ComSear.getValue().equals("이름")) { com = "board_writer";}
    	if(ComSear.getValue().equals("제목")) { com = "board_title";}
    	if(ComSear.getValue().equals("내용")) { com = "board_content";}
    	
    	Map<String, String> map = new HashMap<>();
    	map.put("combox", com);
    	map.put("textfield",TextSear.getText());
    	
    	jdata = service.searchBoard(map);
    	
    	if(jdata == null || jdata.size() ==0) {
    		AlertUtil.warnMsg("경고", "찾는 정보가 없습니다.");
    	}
//    	if(TextSear.equals(null)) {
//    		//텍스트 필드에 아무것도 입력하지 않았을때 테이블에는 모든 게시글이 나타나도록 한다.
//        	BoardTable.setItems(jdbcdata);
//    	}
    	
    	BoardTable.setItems(jdbcdata);
    }

    @FXML
    void initialize() {
    	// 첫화면, 
    	// 게시글 리스트는 테이블 뷰로 보여준다. 
    	// 번호, 제목, 작성자, 작성날짜, 조회수를 보여준다. 
    	// 게시글을 클릭하면 '내용보기 화면' 으로 전환하다. 한화면에 15개씩 보이도록 한다. 
    	
    	ComSear.getItems().addAll("이름","제목","내용");
    	ComSear.setValue("검색항목");
    	 
    	service = JdbcboardServiceImpl.getInstance();
    	
    	List<JdbcVO> jdata = new ArrayList<JdbcVO>();
    	jdata = service.getAllBoard();
    	
    	ObservableList<JdbcVO> jdbcdata = FXCollections.observableArrayList(jdata);
    	
    	ColNo.setCellValueFactory(new PropertyValueFactory<JdbcVO, Integer>("board_no"));
    	ColTitle.setCellValueFactory(new PropertyValueFactory<JdbcVO, String>("board_title"));
    	ColWri.setCellValueFactory(new PropertyValueFactory<JdbcVO, String>("board_writer"));
    	ColDate.setCellValueFactory(new PropertyValueFactory<JdbcVO, String>("board_date"));
    	Colcnt.setCellValueFactory(new PropertyValueFactory<JdbcVO, Integer>("board_cnt"));
    	
    	BoardTable.setItems(jdbcdata);
    	
    	//테이블을 선택하는 옵션
    	//게시글을 선택했을때 수정이 가능하도록 
    	BoardTable.setOnMouseClicked(e->{
    		//빈 공간을 선택했을 때
    		if(BoardTable.getSelectionModel().isEmpty()) {
    			return;
    		}
        	if(!BoardTable.getSelectionModel().isEmpty()){
        		try {
        			
        			FXMLLoader insert = new FXMLLoader(ContJdbcMain.class.getResource("../fxml/FxmlJdbcboardSearch.fxml"));
//        			FXMLLoader insert = new FXMLLoader(ContJdbcMain.class.getResource("../fxml/FxmlJdbcboardUpdate.fxml"));
        			
        			//jvo 에 내가 선택한 값을 넣어준다. 
        			JdbcVO jvo = BoardTable.getSelectionModel().getSelectedItem();
        			
        			Parent childRoot = insert.load(); 
//        			ContJdbcUpdate contupdate = insert.getController();
        			ContJdbcSearch contsearch = insert.getController();
        			
        			//jvo넘겨주기 
//        			contupdate.showupdate(jvo); 
        			contsearch.showupdate(jvo);
        			
        			Scene scene = new Scene(childRoot);
        		
        			//버튼이 보여지는 윈도우 창을 찾는다. 
        			Stage primaryStage = (Stage) btnSear.getScene().getWindow();
//        			Stage primaryStage = (Stage) BoardTable.getScene().getWindow();

        			primaryStage.setScene(scene);
//        			primaryStage.setTitle("000");
//        			primaryStage.show();
     
        		} catch (IOException ee) {
        			// TODO Auto-generated catch block
        			ee.printStackTrace();
        		}
        	} 
    		
    	});
    	
    	
    }
}
