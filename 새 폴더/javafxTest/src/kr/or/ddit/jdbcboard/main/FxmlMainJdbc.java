package kr.or.ddit.jdbcboard.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kr.or.ddit.member.main.FxmlmainMember;

public class FxmlMainJdbc extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(FxmlMainJdbc.class.getResource("../fxml/FxmlJdbcboardMain.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("JDBCboard Main");
		primaryStage.show();  
		
		// 안되는 것 
		// 삭제가 안됨
		
		// update로 전환은 되지만 내용이 수정되지 않음
		// 수정 -> 취소를 눌렀을때 기존에 있던 내용을 불러올 수 없음 
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
