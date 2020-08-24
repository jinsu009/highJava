package basic.controlls;

import org.omg.CORBA.CODESET_INCOMPATIBLE;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ComboBoxTest extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		
		HBox hbox = new HBox(10);
		hbox.setPadding(new Insets(10));
		hbox.setAlignment(Pos.CENTER);
		TextArea taMsg = new TextArea();
		
		// combobox객체 생성 후 데이터 셋팅하기 방법 1
		ComboBox<String> combo = new ComboBox<String>();
		combo.getItems().addAll("한강","금강","영산강","낙동강");
		//combo.setValue("한강");
		combo.setValue(combo.getItems().get(0));
		
		// 방법 2
		ObservableList<String> data = FXCollections.observableArrayList(
				"사과","딸기","배","귤","다래","감"
				);
		ComboBox<String> combo2 = new ComboBox<String>(data);
	//	combo2.setValue(combo2.getItems().get(0)); // 이 부분이 없을 때 null 값을 넘긴다.  
		
		// 현재 설정된 데이터에 다른 데이터를 추가해주기 
		combo2.getItems().addAll("복숭아","대추","두리안");
		
		Button btn = new Button("OK");
		btn.setOnAction(e->{
			
			if(combo.getValue() == null) {
				taMsg.setText("첫번째 콤보박스에서 데이터를 선택하세용");
				return;
			}
			if(combo2.getValue() == null) {
				taMsg.setText("두번째 콤보박스에서 데이터를 선택하세용");
				return;
			}
			//콤보박스에서 현재 선택된 값은 getvalue메소드로 구할 수 있다. 
			taMsg.setText(combo.getValue()+" 유역의 과일은 ");
			taMsg.appendText(combo2.getValue()+" 가 유명합니다. ");
		});
		
		hbox.getChildren().addAll(combo, combo2, btn);
		
		root.setTop(hbox);
		root.setCenter(taMsg);
		
		Scene scene = new Scene(root, 500, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("COMBOBOX 연습");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
