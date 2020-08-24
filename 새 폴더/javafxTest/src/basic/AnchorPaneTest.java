package basic;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AnchorPaneTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		AnchorPane root = new AnchorPane();
		root.setPrefSize(300,200);
		Label lblId = new Label("ID >> " );
		lblId.setLayoutX(62);
		lblId.setLayoutY(22);
		
		Label lblPass = new Label("PW >> ");
		lblPass.setLayoutX(62);
		lblPass.setLayoutY(60);
		
		TextField tfId = new TextField();
		tfId.setLayoutX(130);
		tfId.setLayoutY(22);
		
		PasswordField tfPass = new PasswordField();
		tfPass.setLayoutX(130);
		tfPass.setLayoutY(60);
		
		Button btnLogin = new Button("Login");
		btnLogin.setLayoutX(132);
		btnLogin.setLayoutY(100);
		
		Button btnCancel = new Button("Cancel");
		btnCancel.setLayoutX(200);
		btnCancel.setLayoutY(100);
		
		root.getChildren().addAll(lblId, lblPass, tfId, tfPass, 
				btnLogin, btnCancel);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("AnchorPane parc.");
		primaryStage.show();		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
