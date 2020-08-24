package kr.or.ddit.studentTe.main;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import basic.util.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import kr.or.ddit.studentTe.service.IStudentTeService;
import kr.or.ddit.studentTe.service.StudentTeServiceImpl;
import kr.or.ddit.studentTe.vo.StudentTeVO;

public class StudentTeBarChartCont {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BarChart<String,Number> bcStudent;

    @FXML
    private Button btnClose;

    private IStudentTeService service;
    

    // 닫기
    @FXML
    void btnCloseClicked(ActionEvent event) {
    	Stage currentStage = (Stage) btnClose.getScene().getWindow();
    	currentStage.close();
    }

    @FXML
    void initialize() {
    	service = StudentTeServiceImpl.getInstance();
    	
    	// DB에서 전체 학생 성적 가져오기
    	List<StudentTeVO> stdList = service.getAllStudentList();
    	if(stdList==null || stdList.size()==0) {
    		AlertUtil.warnMsg("자료없음", "그래프로 나타낼 학생 성적자료가 하나도 없습니다.");
    		return;
    	}
    	
    	// Chart에 나타낼 데이터 구성하기
    	
    	// 국어 점수
    	XYChart.Series<String, Number> serKor = new XYChart.Series<String, Number>();
    	serKor.setName("국어");
    	for(StudentTeVO stdVo : stdList) {
    		serKor.getData().add(new Data<>(stdVo.getStd_name(), stdVo.getStd_kor()));
    	}
    	
    	// 영어 점수
    	XYChart.Series<String, Number> serEng = new XYChart.Series<String, Number>();
    	serEng.setName("영어");
    	for(StudentTeVO stdVo : stdList) {
    		serEng.getData().add(new Data<>(stdVo.getStd_name(), stdVo.getStd_eng()));
    	}
    	
    	// 수학 점수
    	XYChart.Series<String, Number> serMat = new XYChart.Series<String, Number>();
    	serMat.setName("수학");
    	for(StudentTeVO stdVo : stdList) {
    		serMat.getData().add(new Data<>(stdVo.getStd_name(), stdVo.getStd_mat()));
    	}
    	
    	bcStudent.getData().addAll(serKor, serEng, serMat);

    }
}




