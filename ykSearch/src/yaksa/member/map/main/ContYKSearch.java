package yaksa.member.map.main;


//webview를 사용하기 위한 jfxrt 의 imoport
//build path > jdk1.8 > jre > lib > ext 에서 외부 jar 파일 추가 
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;
import netscape.javascript.JSObject;
import yaksa.member.map.service.SearchServiceImpl;
import yaksa.member.map.vo.PharmVO;

public class ContYKSearch {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<String> jusoCB;

	@FXML
	private TextField phSearch;

	@FXML
	private Button SearchBtn;

	@FXML
	private Button ReloadBtn;

	@FXML
	private Button MaskBtn;

	@FXML
	private TableView<PharmVO> pharmTb;

	@FXML
	private TableColumn<PharmVO, String> phName;

	@FXML
	private TableColumn<PharmVO, String> phTel;

	@FXML
	private TableColumn<PharmVO, String> phAdd1;

//	@FXML
//	private WebView MapView;
	
	@FXML
    private VBox MapView;
	
	private SearchServiceImpl service;
	private PharmVO pvo;
	// ====================웹 서버 연결 통신 
	private JavaConnector javaConnector = new JavaConnector();;
	private JSObject javascriptConnector;
	
	@FXML
	void MaskSaleMap(ActionEvent event) {
		// 마스크 판매하는 지도를 보여주는 fxml 호출

		try {
			FXMLLoader insert = new FXMLLoader(ContYKSearch.class.getResource("../fxml/MaskSaleYK.fxml"));

			Parent childRoot = insert.load();
//			ContJdbcSearch contsearch = insert.getController();

			Scene scene = new Scene(childRoot);
			Stage primaryStage = (Stage) MaskBtn.getScene().getWindow();

			primaryStage.setScene(scene);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	 // ======================= html 연동 
 

   	// ================ 검색버튼을 눌렀을 때 실행되는 메소드 
	@FXML
	void PharmSearch(ActionEvent event) {
		pharmTb.getItems().clear();
		// 버튼을 눌렀을 때 텍스트 필드의 값을 service에 넘겨줘서 비교한다.
		service = SearchServiceImpl.getInstance();
		List<PharmVO> pharmName = new ArrayList<PharmVO>();
		List<PharmVO> pdata = new ArrayList<PharmVO>();
		String str = "";
		if (jusoCB.getValue().equals("약국이름")) {
			str = "pharm_name";
		}
		if (jusoCB.getValue().equals("번호")) {
			str = "pharm_tel";
		}
		if (jusoCB.getValue().equals("시군")) {
			str = "pharm_add1";
		}
		if (jusoCB.getValue().equals("도로명")) {
			str = "pharm_add2";
		}
		Map<String, String> map = new HashMap<>();
		map.put("pharm_add", str);
		map.put("add", phSearch.getText());
		pdata = service.searchYK(map);
		ObservableList<PharmVO> pharmdata = FXCollections.observableArrayList(pdata);
		pharmTb.setItems(pharmdata);
		addButtonToTable();
		
	}

	// 2. addButtonToTable()메소드 이용
	private void addButtonToTable() {
		TableColumn<PharmVO, Void> colBtn2 = new TableColumn("상세보기");
		// 테이블에 추가할 새로운 컬럼을 선언한다. (컬럼명은 채팅)
		Callback<TableColumn<PharmVO, Void>, TableCell<PharmVO, Void>> cellFactory = new Callback<TableColumn<PharmVO, Void>, TableCell<PharmVO, Void>>() {

			@Override
			public TableCell<PharmVO, Void> call(final TableColumn<PharmVO, Void> param) {

				final TableCell<PharmVO, Void> cell = new TableCell<PharmVO, Void>() {

					private final Button btn2 = new Button("상세보기"); // 버튼 생성

					// 버튼을 눌렀을 때 실행할 action
					{
						btn2.setOnAction(e -> {
							try {
								int index = ((TableRow<PharmVO>) ((Button) e.getSource()).getParent().getParent())
										.getIndex();
								// System.out.println("===>"+((TableRow<PharmVO>)((Button)e.getSource()).getParent().getParent()).getIndex());
								// PharmVO pvo = pharmTb.getSelectionModel().getSelectedItem();
								PharmVO pvo = pharmTb.getItems().get(index);
								Stage primaryStage = (Stage) SearchBtn.getScene().getWindow();
								FXMLLoader detail = new FXMLLoader(
										ContYKSearch.class.getResource("../fxml/YKSearchDetail.fxml"));
								Parent childRoot = detail.load();
								ContYKDetail contdetail = detail.getController();
								contdetail.showupdate(pvo);

								Stage detailStage = new Stage();
//		        					Stage detailStage = new Stage(StageStyle.DECORATED);
//		        					detailStage.initModality(Modality.WINDOW_MODAL);
								detailStage.initOwner(primaryStage);

								Scene scene = new Scene(childRoot);

								detailStage.setScene(scene);
								detailStage.setTitle(pvo.getPharm_chname());
								detailStage.show();

							} catch (IOException e2) {
								e2.printStackTrace();
							}
						});

					}

					// 수업시간에 했던 ListViewTest
					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn2);
						}
					}
				};
				return cell; // cell
			}
		};

		// 동적을 생성된 열에 동적으로 생성된 버튼과 그에 따른 action기능을 set 시켜준다.
		colBtn2.setCellFactory(cellFactory);
		// 테이블에 colBtn 컬럼을 붙여준다.
		pharmTb.getColumns().add(colBtn2);
	}

	// webview를 불러오는 메소드
//	private void initFX() {
//		JFrame frame = new JFrame("FX");
//
//		frame.getContentPane().setLayout(null);
//
//		final JFXPanel fxPanel = new JFXPanel();
//
//		frame.add(fxPanel);
//		frame.setVisible(true);
//
//		fxPanel.setSize(new Dimension(380, 420));
////		fxPanel.setLocation(new Point(0, 27));
//
//		frame.getContentPane().setPreferredSize(new Dimension(380, 420));
//		frame.pack();
//		frame.setResizable(false);
//
//		Platform.runLater(new Runnable() {
//			public void run() {
//				initAndLoadWebView(fxPanel);
//			}
//		});
//	}
//
//	private void initAndLoadWebView(final JFXPanel fxPanel) {
//		Group group = new Group();
//		Scene scene = new Scene(group);
//		fxPanel.setScene(scene);
//
//		WebView webview = new WebView();
//		group.getChildren().add(webview);
//		webview.setMinSize(380, 420);
//		webview.setMaxSize(380, 420);
//
//		WebEngine webEngine = webview.getEngine();
//		webEngine.load("http://localhost/ykMap/DJYKMap.html");
//		
//		// ============= webview에서 값을 받음 
//      webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
//		    if (Worker.State.SUCCEEDED == newValue) {
//              JSObject window = (JSObject) webEngine.executeScript("window");
//              window.setMember("javaConnector", javaConnector);
//              javascriptConnector = (JSObject) webEngine.executeScript("getJsConnector()");
//          }
//      });
//		
//	}

	@FXML
	void Reload(ActionEvent event) {}
    
	@FXML
	void initialize() {

		jusoCB.getItems().addAll("약국이름", "번호", "시군", "도로명");
		jusoCB.setValue(jusoCB.getItems().get(0));

		// webview 로 지도 mapview에 띄우기 tomcat server 를 실행시켜야 한다.

		WebView webview = new WebView();
		WebEngine webEngine = webview.getEngine();
//		webEngine.load("http://localhost/ykMap/DJYKMap.html");
		MapView.getChildren().add(webview);
		
//		// ============= webview에서 값을 받음 
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
		    if (Worker.State.SUCCEEDED == newValue) {
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("javaConnector", javaConnector);
                javascriptConnector = (JSObject) webEngine.executeScript("getJsConnector()");
            }
        });
        webEngine.load("http://localhost/ykMap/NewFile1.html"); // 정민이가 수정한 페이지 
        
		// 새로고침 버튼을 눌렀을 때 지도 화면을 reload
		ReloadBtn.setOnAction(e -> {
			webview.getEngine().reload();
		});

		// 테이블에 약국정보 띄우기
		phName.setCellValueFactory(new PropertyValueFactory<PharmVO, String>("pharm_name"));
		phTel.setCellValueFactory(new PropertyValueFactory<PharmVO, String>("pharm_tel"));
//		phAdd.setCellValueFactory(new PropertyValueFactory<PharmVO, String>("pharm_add1"));
		phAdd1.setCellValueFactory(new PropertyValueFactory<PharmVO, String>("pharm_add2"));
//		phChatBtn.setCellValueFactory(new PropertyValueFactory<PharmVO, String>("chatBtn")); 
		// 버튼 동적생성 방법 1
		// 이 경우에는 scenebuilder에서 처음부터 컬럼이 존재해야한다.
		// 그렇지 않읅경우 컬럼을 동적으로 생성해주는 방법을 사용해야 해당 버튼 값을 호출 할 수 있다.

		
// ===================================================================

		// 해당약국의 열을 선택하면 그 약국의 상세정보가 보인다 .
		
		pharmTb.setOnMouseClicked(e->{ // 테이블을 선택 했을 때 
			
			try {
		
//			// 선택한 약국 데이터 구하기 
			if(!pharmTb.getSelectionModel().isEmpty()) {

				javaConnector.toLowerCase(pharmTb.getSelectionModel().getSelectedItem().getPharm_add1()+
						pharmTb.getSelectionModel().getSelectedItem().getPharm_add2());
//					
			}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		});

	}
	
	
  	public class JavaConnector {
        public void toLowerCase(String value) {
        	
        	System.out.println(value);
      	   if (null != value) {
                 javascriptConnector.call("showResult", value);
             }
        }
   }

	

}
