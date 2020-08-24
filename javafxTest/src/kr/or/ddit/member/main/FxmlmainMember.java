package kr.or.ddit.member.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxmlmainMember extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(FxmlmainMember.class.getResource("../fxml/FxmlMember.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("회원관리");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
