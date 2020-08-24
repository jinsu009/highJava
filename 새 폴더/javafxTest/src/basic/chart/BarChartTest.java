package basic.chart;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class BarChartTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		
		// x축
		CategoryAxis xAxis = new CategoryAxis();
		
		// y축
		NumberAxis yAxis = new NumberAxis();	 
		
		//x축 y축이 있는 그래프 셋팅 
		BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
		bc.setTitle("나라별 Data"); //축의제목
		xAxis.setLabel("나라이름"); //x축의 제목
		yAxis.setLabel("Value"); //y축의 제목
		
		// Chart에 출력할 데이터 구성하기
		
		// 1.series 객체 생성 
		XYChart.Series<String, Number> ser1 = new XYChart.Series<String, Number>();
		ser1.setName("2018년"); //series 이름 설정 
		
		// 2. 데이터 설정 
		ser1.getData().add(new XYChart.Data<String, Number>("호주",12345));
		ser1.getData().add(new XYChart.Data<String, Number>("덴마크",16222));
		ser1.getData().add(new XYChart.Data<String, Number>("네덜란드",14343));
		ser1.getData().add(new XYChart.Data<String, Number>("중국",15321));
		ser1.getData().add(new XYChart.Data<String, Number>("한국",19000));
		
		//시리즈만들고 데이터 추가하는 작업을 반복해준다.
		
		XYChart.Series<String, Number> ser2 =  new XYChart.Series<String, Number>();
		ser2.setName("2019년");
		
		
		ObservableList<XYChart.Data<String, Number>> ser2List = 
				FXCollections.observableArrayList();
		
		ser2List.addAll(
					new XYChart.Data<>("호주",8000),
					new XYChart.Data<>("덴마크",12300),
					new XYChart.Data<>("네덜란드",23400),
					new XYChart.Data<>("중국",11600),
					new XYChart.Data<>("한국",15400)
				);
		ser2.setData(ser2List);
		
		XYChart.Series<String, Number> ser3 =  new XYChart.Series<String, Number>();
		ser3.setName("2020년");
		ser3.getData().add(new XYChart.Data<>("호주",11000));
		ser3.getData().add(new XYChart.Data<>("덴마크",21000));
		ser3.getData().add(new XYChart.Data<>("네덜란드",31000));
		ser3.getData().add(new XYChart.Data<>("중국",11000));
		ser3.getData().add(new XYChart.Data<>("한국",21000));
		
		// 3. Series객체를 chart에 추가한다. 
		bc.getData().addAll(ser1,ser2,ser3);
		
		//bc.setBarGap(10); //bar끼리의 간격 
		//bc.setCategoryGap(30); // 카테고리간의 간격
		
		Scene scene = new Scene(bc);
		primaryStage.setScene(scene);
		primaryStage.setTitle("BarChart Test");
		primaryStage.show();		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
