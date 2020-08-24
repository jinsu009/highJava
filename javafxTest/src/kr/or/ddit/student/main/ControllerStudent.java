package kr.or.ddit.student.main;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import basic.util.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kr.or.ddit.student.service.StudentServiceImpl;
import kr.or.ddit.student.vo.StudentVO;

public class ControllerStudent {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<StudentVO> StudentTable;

    @FXML
    private TableColumn<StudentVO, String> NameCol;

    @FXML
    private TableColumn<StudentVO, Integer> KorCol;

    @FXML
    private TableColumn<StudentVO, Integer> MathCol;

    @FXML
    private TableColumn<StudentVO, Integer> EngCol;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnChart;
    
    private List<StudentVO> stuList;
    private StudentServiceImpl service;
    private ObservableList<StudentVO> data;
    
    @FXML
    void AddStu(ActionEvent event) {
    	
    	// 추가 창 FxmlStudentAdd.fxml 띄우기 
    	
    	try {
    		Stage dialog = new Stage();
    		Parent childRoot = FXMLLoader.load(ControllerStudent.class.getResource("../fxml/FxmlStudentAdd.fxml"));
    		Scene childScene = new Scene(childRoot);
    		
    		dialog.setScene(childScene);
    		dialog.setTitle("추가");
    		dialog.showAndWait();
    		 
    		setStuData();
    		
		} catch (Exception e) {
			System.out.println("창이 안떠");
		}
    }

    @FXML
    void ChartLook(ActionEvent event) {
    	//학생별막대 그래프 보기 
    	
    	VBox root = new VBox();
    	root.setPadding(new Insets(10));    	
    	
    	CategoryAxis xx = new CategoryAxis();
    	NumberAxis yy = new NumberAxis();
    	
    	BarChart<String, Number> bc = new BarChart<String, Number>(xx, yy);
    	bc.setTitle("막대 그래프");

    	stuList = StudentTable.getItems();
    	
    	XYChart.Series<String, Number> kor = new XYChart.Series<String, Number>();
    	XYChart.Series<String, Number> math = new XYChart.Series<String, Number>();
    	XYChart.Series<String, Number> eng = new XYChart.Series<String, Number>();
    	for(StudentVO i:stuList) {
    		kor.getData().add(new XYChart.Data<String, Number>(i.getStd_name(),i.getStd_kor()));
    		math.getData().add(new XYChart.Data<String, Number>(i.getStd_name(),i.getStd_mat()));
    		eng.getData().add(new XYChart.Data<String, Number>(i.getStd_name(),i.getStd_eng()));
    	}
    	kor.setName("국어");
    	math.setName("수학");
    	eng.setName("영어");
    	
    	Button canbtn = new Button("닫기");
    	root.setAlignment(Pos.CENTER);
    	
    	bc.getData().addAll(kor, math, eng);
    	bc.setBarGap(10);
    	bc.setCategoryGap(20);
    	
    	root.getChildren().addAll(bc, canbtn);
    	try {
    		Stage dialog = new Stage();
    		Scene chscene = new Scene(root);
    		
    		//자식창객체 스테이지에 scene객체추가 
    		dialog.setScene(chscene);
    		dialog.setTitle("막대그래프");
    		dialog.show();
    		canbtn.setOnAction(e->{
    			//닫기버튼 
    			dialog.close();
    		});
			
		} catch (Exception e) {
			System.out.println("막대그래프 오류!!!!!!");
		}
    	
    }


// 테이블에 변경된 DB를 띄워주도록 한다. 
    private void setStuData() {
    	List<StudentVO> stuList = service.getAllStudent();
    	data = FXCollections.observableArrayList(stuList);
    	StudentTable.setItems(data);
    }
    
    //테이블에서 학생을 선택하면 원형그래프가 나오도록
    @FXML
    void studentClick(MouseEvent event) {
    	
    	if(StudentTable.getSelectionModel().isEmpty()) {
    		AlertUtil.errorMsg("작업오류", "성적을 볼 학생을 클릭하세요");
    		return;
    	}
    	
    	VBox root = new VBox();
    	root.setPadding(new Insets(10));    	
    	
    	StudentVO std = StudentTable.getSelectionModel().getSelectedItem();
    	PieChart pc = new PieChart();
    	
    	ObservableList<PieChart.Data> pl =
    			FXCollections.observableArrayList(
    					new PieChart.Data("국어",std.getStd_kor()),
    					new PieChart.Data("수학",std.getStd_mat()),
    					new PieChart.Data("국어",std.getStd_eng())
    			);
    	pc.setData(pl);
    	pc.setTitle("원형그래프");
    	
    	Button canbtn = new Button("닫기");
    	root.setAlignment(Pos.CENTER);
    	
    	root.getChildren().addAll(pc, canbtn);
    	try {
    		Stage dialog = new Stage();
    		Scene chscene = new Scene(root);
    		
    		//자식창객체 스테이지에 scene객체추가 
    		dialog.setScene(chscene);
    		dialog.setTitle("막대그래프");
    		dialog.show();
    		canbtn.setOnAction(e->{
    			//닫기버튼 
    			dialog.close();
    		});
			
		} catch (Exception e) {
			System.out.println("막대그래프 오류!!!!!!");
		}
    }

    @FXML
    void initialize() {
    	
    	// 처음 화면을 시작했을때 학생들의 정보가 나타난 테이블이 보여야 한다. 
    	// 회원을 클릭하면 파이그래프로 개인의 점수표를 나타내며 
    	// 학생별 막대 그래프 를 누르면 barchart가 보여야 한다.
    	// 추가버튼을 누르면 학생정보 추가 창이 나오면서 이름과 각과목의 점수를 저장해야한다. 
    	
    	service = StudentServiceImpl.getInstance();
    	    	
    	NameCol.setCellValueFactory(new PropertyValueFactory<StudentVO, String>("std_name"));
    	KorCol.setCellValueFactory(new PropertyValueFactory<StudentVO, Integer>("std_kor"));
    	MathCol.setCellValueFactory(new PropertyValueFactory<StudentVO, Integer>("std_mat"));
    	EngCol.setCellValueFactory(new PropertyValueFactory<StudentVO, Integer>("std_eng"));

    	setStuData();
    }
}
    

