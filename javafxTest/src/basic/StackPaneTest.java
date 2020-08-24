package basic;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StackPaneTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		StackPane root = new StackPane();
		root.setPrefSize(300, 200);
		
		ImageView imgView = new ImageView();
		Image img = new Image(StackPaneTest.class.getResourceAsStream("../img/javafx.jpg"));
		imgView.setImage(img);
		imgView.setFitWidth(250);
		imgView.setFitHeight(150);
		
		TextField taTest = new TextField();
		taTest.setPrefSize(100, 60);
		taTest.setStyle("-fx-background-color:pink");
		
		Button btn= new Button("Ok");
		root.getChildren().add(imgView);
		root.getChildren().add(taTest);
		root.getChildren().add(btn);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("StackPane prac.");
		primaryStage.show();
		
		
		//이미지뷰 -> 텍스트 필드 -> 버튼 순으로 나온다. 
	}

	public static void main(String[] args) {
		launch(args);
	}
}
