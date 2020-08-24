package basic.chart;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

public class PieChartTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		//축이 없으므로 시리즈가 없다. 
		PieChart pc = new PieChart();
		
		ObservableList<PieChart.Data> pieList = 
				FXCollections.observableArrayList(
						new PieChart.Data("사과", 100),
						new PieChart.Data("바나나", 400),
						new PieChart.Data("복숭아", 500),
						new PieChart.Data("체리", 900),
						new PieChart.Data("멜론", 200)
				); 
		
		pc.setData(pieList);
		
		pc.setTitle("과일별 재고량");
		
		Scene scene = new Scene(pc);
		primaryStage.setScene(scene);
		primaryStage.setTitle("PieChart Test");
		primaryStage.show();
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
