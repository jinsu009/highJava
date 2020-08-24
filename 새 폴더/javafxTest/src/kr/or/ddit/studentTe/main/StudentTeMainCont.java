package kr.or.ddit.studentTe.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.studentTe.service.IStudentTeService;
import kr.or.ddit.studentTe.service.StudentTeServiceImpl;
import kr.or.ddit.studentTe.vo.StudentTeVO;

public class StudentTeMainCont {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<StudentTeVO> stdTable;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> korCol;

    @FXML
    private TableColumn<?, ?> engCol;

    @FXML
    private TableColumn<?, ?> matCol;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBarChart;

    private IStudentTeService service;
    private ObservableList<StudentTeVO> stdData;
    
    @FXML
    void btnAddClicked(ActionEvent event) {
    	//추가버튼 
    	try {
			//현재창의 컨트롤 객체를 이용해서 해당 창의 stage객체 구하기 
    		Stage parentStage = (Stage) btnAdd.getScene().getWindow();
    		Stage addStage = new Stage(StageStyle.DECORATED);
    		addStage.initModality(Modality.WINDOW_MODAL);
    		addStage.initOwner(parentStage);
    		
    		//fxmlloader 객체 생성
    		FXMLLoader loader = new FXMLLoader(StudentTeMainCont.class.getResource("../fxml/StudentTeAddCont.fxml"));
    		
    		//loader객체를 이용해서 fxml문서를 읽어온다. 
    		Parent addRoot = loader.load();
    		
    		//추가창(addcont.fxml)의 controller객체를 구한다. 
    		//loader객체를 이용해서 자식창의 controller객체를 구한다. 
    		StudentTeAddCont addcont = loader.getController();
    		
    		//자식창의 부모창을 저장할 변수에 부모창을 저장한다. 
    		//자식창에서 getter setter 선언 
    		addcont.setMainController(this);
    		
    		Scene scene = new Scene(addRoot);
    		addStage.setScene(scene);
    		addStage.setTitle("추가");
    		addStage.show();
    		
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

    }

    @FXML
    void btnBarChartClicked(ActionEvent event) {
    	//학생별 막대 그래프 버튼 
    	try {
			Stage parentStage = (Stage) btnBarChart.getScene().getWindow();
			Stage barChartStage = new Stage(StageStyle.DECORATED);
			barChartStage.initModality(Modality.WINDOW_MODAL);
			barChartStage.initOwner(parentStage);
			
			Parent childRoot = FXMLLoader.load(
					StudentTeMainCont.class.getResource("../fxml/StudentTeBarChar.fxml")
					);
			Scene scene = new Scene(childRoot);
			barChartStage.setScene(scene);
			barChartStage.setTitle("막대그래프");
			barChartStage.show();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

    }

    @FXML
    void stdTableClicked(MouseEvent event) {
    	//테이블을 클릭했을 때 
    	if(stdTable.getSelectionModel().isEmpty()) {
    		return;
    		//아무것도선택하지 않았을 경우
    	}
    	//선택한 학생 데이터 구하기 
    	StudentTeVO stuvo = stdTable.getSelectionModel().getSelectedItem();
    	Stage parentStage = (Stage) btnAdd.getScene().getWindow();

    	try {
			FXMLLoader loader = 
					new FXMLLoader(
						StudentTeMainCont.class.getResource("../fxml/StudentTePieChart.fxml")	
						);
			Parent childRoot = loader.load();
			
			StudentTePieChartCont pieController = loader.getController();
			
			// 구해온 자식창의 controller객체의 차트를 그려주는 메소드르 호출 한다. 
			pieController.showChart(stuvo);
			
			Stage pieStage = new Stage(StageStyle.DECORATED);
			pieStage.initModality(Modality.WINDOW_MODAL);
			pieStage.initOwner(parentStage);
			
			Scene scene = new Scene(childRoot);
			
			pieStage.setScene(scene);
			pieStage.setTitle(stuvo.getStd_name() + " 학생의 성적");
			pieStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void setTableView() {
    	stdData = FXCollections.observableArrayList(service.getAllStudentList());
    	stdTable.setItems(stdData);
    }
    
    
    @FXML
    void initialize() {
    	service = StudentTeServiceImpl.getInstance();
    	
    	nameCol.setCellValueFactory(new PropertyValueFactory<>("std_name"));
    	korCol.setCellValueFactory(new PropertyValueFactory<>("std_kor"));
    	engCol.setCellValueFactory(new PropertyValueFactory<>("std_eng"));
    	matCol.setCellValueFactory(new PropertyValueFactory<>("std_mat"));
    	
    	setTableView();

    }
}
