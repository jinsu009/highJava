package basic;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BorderPaneTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		root.setPrefSize(300, 200);
		root.setPadding(new Insets(10));
		
		ToolBar toolbar = new ToolBar(
				new Button("first"), new Button("second")
		);
		TextArea taTemp = new TextArea();
		HBox hbox = new HBox(10); // VBox나 HBox의 생성자에 들어가는 숫자값은 spacing값이 된다. 
		
		hbox.setAlignment(Pos.CENTER);
		
		TextField tfTest = new TextField();
		Button btnOk = new Button("Ok");
		hbox.getChildren().addAll(tfTest, btnOk);
		root.setTop(toolbar); // borderpane의 Top영역에 추가하기
		root.setCenter(taTemp); //borderPane의 center영역에 추가하기 
		root.setBottom(hbox); //borderPane의 bottom영역에 추가하기 
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("BorderPane Practice");
		primaryStage.show();
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
