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
		
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
