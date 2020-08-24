package basic;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FxmlLayout extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		// fxml 파일을 읽어와서 현재의 stage에 적용하는 방법
		// fxml 파일을 읽어오는 방법1 
		// rootelement를 반환시켜준다. 
		// 예외처리를 해준다. 
		//VBox root = FXMLLoader.load(FxmlLayout.class.getResource("FxmlLayout.fxml"));
		//보통은 parent를 많이 사용한다. 
		//Parent root = FXMLLoader.load(FxmlLayout.class.getResource("FxmlLayout.fxml"));
		
		 //fxml 파일을 읽어오는 방법 2 (FXMLLoader객체를 따로 만듦)
	     FXMLLoader loader = new FXMLLoader(FxmlLayout.class.getResource("FxmlLayout.fxml"));
	     VBox root = loader.load();
	     //Parent root = loader.load();
	
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Fxml파일을 이용한 레이아웃 연습");
		primaryStage.show();
		
		//차이점 : 두번째방법을 사용해야지만 컨트롤러의 값을 가져올수 있다. 
	}

	public static void main(String[] args) {
		launch(args);
	}
}
