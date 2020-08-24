package yaksa.chemist.chart.main;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import yaksa.chemist.chart.service.ChartServiceImpl;
import yaksa.chemist.chart.vo.PharmPresVO;
import yaksa.chemist.chart.vo.PharmVO;



public class ContYKChart {
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label ChmiName;

    @FXML
    private ComboBox<String> ChartCB;

    @FXML
    private Button OkBtn;

    @FXML
    private VBox ChartVBox;

    @FXML
    void LookChart(ActionEvent event) {

    }
    
    BarChart<String, Number> AgeBarChart(){
    	// 연령별 방문 차트 
    	
    	int one =0;
    	int two =0;
    	int thr =0;
    	int four =0;
    	int five =0;
   	 
    	one = service.onecnt();
    	two = service.twocnt();
    	thr = service.thrcnt();
    	four = service.fourcnt();
    	five = service.fivecnt();
    	
    	CategoryAxis x = new CategoryAxis();
    	NumberAxis y = new NumberAxis();
    	BarChart<String, Number> bc = new BarChart<String, Number>(x, y);
    	bc.setTitle("연령");
    	x.setLabel("나이");
    	y.setLabel("방문횟수");
    	 
    	XYChart.Series<String, Number> ser1 =  new XYChart.Series<String, Number>();
    	ser1.setName("2020년 03월");
    	ser1.getData().add(new XYChart.Data<String, Number>("10대",one));
    	ser1.getData().add(new XYChart.Data<String, Number>("20대",two));
    	ser1.getData().add(new XYChart.Data<String, Number>("30대",thr));
    	ser1.getData().add(new XYChart.Data<String, Number>("40대",four));
    	ser1.getData().add(new XYChart.Data<String, Number>("50대",five));
    	
    	System.out.println(one+","+two+","+thr+","+four+","+five);
    	
     	XYChart.Series<String, Number> ser2 =  new XYChart.Series<String, Number>();
     	ser2.setName("2020년 04월");
     	ser2.getData().add(new XYChart.Data<String, Number>("10대",10));
     	ser2.getData().add(new XYChart.Data<String, Number>("20대",66));
     	ser2.getData().add(new XYChart.Data<String, Number>("30대",80));
     	ser2.getData().add(new XYChart.Data<String, Number>("40대",40));
     	ser2.getData().add(new XYChart.Data<String, Number>("50대",30));
    	
    	bc.getData().add(ser1);
    	bc.getData().add(ser2);
    	
    	return bc;
		
    }
    
    BarChart<String, Number> MonthBarChart() {
    	// 월별 방문자 수 
    	
    	CategoryAxis x = new CategoryAxis();
    	NumberAxis y = new NumberAxis();
    	BarChart<String, Number> bc = new BarChart<String, Number>(x, y);
//    	bc.setTitle("월");
//    	x.setLabel("월");
//    	y.setLabel("방문자 수");
//    	
//    	XYChart.Series<String, Number> ser =  new XYChart.Series<String, Number>();
//    	ser.setName("2020년");
    	
//    	for(int i=1; i<=12; i++) {
//    		int a = 0;
//    		a = service.monthcnt(i);
//    		ser.getData().add(new XYChart.Data<String, Number>(i+"월",a));
//    	}
    	
    	
    	
    	//환자가 방문한 약국 통계
    	// 사용되는 vo => phprvo
    	
    	bc.setTitle("환자가 방문한 약국 통계");
    	x.setLabel("월");
    	y.setLabel("방문횟수");
    	
    	XYChart.Series<String, Number> ser =  new XYChart.Series<String, Number>();
    	ser.setName("2020년 4월");
    	
    	List<PharmPresVO> pList = service.ykvisit("nar0328");
    	
    	for(int i=0;i<pList.size();i++) {
    		ser.getData().add(new XYChart.Data<String, Number>(pList.get(i).getPharm_name(),pList.get(i).getCnt()));
    	}
    	
    	bc.getData().add(ser);
    	return bc;
    }
    
    PieChart GenderPieChart() {
    	// 성별
    	// ChartPane 에 그래프값을 넘겨준다 . 
    	PieChart pc = new PieChart();
    	
    	int a = service.womancnt();
    	int b = service.mancnt();
		
		ObservableList<PieChart.Data> pieList = 
				FXCollections.observableArrayList(
						new PieChart.Data("여성", a),
						new PieChart.Data("남성", b)
				); 
		
		pc.setData(pieList);
		
		pc.setTitle("성별");
		
		return (PieChart) pc;
		
		
    }
    
   private PharmVO pvo = null;
   private PharmPresVO phprvo = null;
   private ChartServiceImpl service;
  

    @FXML
    void initialize() {
    	service = ChartServiceImpl.getInstance();
    	List<PharmVO> pvolist = service.getAllYK();
    	pvo = pvolist.get(0);
    	ChmiName.setText(pvo.getPharm_name());
    	
    	ChartCB.getItems().addAll("방문자 차트","연령","월","성별");
    	ChartCB.setValue(ChartCB.getItems().get(0));
    	
    	// 1. 연령 선택 했을 경우  barchart pane에 불러오기 
    	// 2. 월 선택 했을 경우 barchart pane에 불러오기 
    	// 3. 성별 선택 했을 경우 piechart pane에 불러오기  
    	// 확인 버튼을 눌렀을 때 차트 불러오기 
    	
    	OkBtn.setOnAction(e->{
    		if(ChartVBox.isVisible()==true) {
    			// 차트가 담겨진 vbox를 clear시켜준다 
    			ChartVBox.getChildren().clear();
    		}
    		if(ChartCB.getValue().equals("연령")) {
    			ChartVBox.getChildren().addAll(AgeBarChart());
    		}
    		if(ChartCB.getValue().equals("월")) {
    			ChartVBox.getChildren().addAll(MonthBarChart());
    		}
    		if(ChartCB.getValue().equals("성별")) {
    			ChartVBox.getChildren().add(GenderPieChart());
    		}
    		
    	});
}
}
