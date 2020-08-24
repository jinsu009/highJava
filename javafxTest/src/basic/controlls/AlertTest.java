package basic.controlls;

import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AlertTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		VBox root = new VBox(10);
		root.setPadding(new Insets(20));
		
		HBox hbTop = new HBox(10);
		hbTop.setPadding(new Insets(10));
		hbTop.setAlignment(Pos.CENTER);

		HBox hbBottom = new HBox(10);
		hbBottom.setPadding(new Insets(10));
		hbBottom.setAlignment(Pos.CENTER);
		
		// 사용방법이 거의 똑같다.
		Button btnInfo = new Button("Info");
		Button btnError = new Button("Error");
		Button btnWarn = new Button("Warning");
		//-------------
		Button btnConfirm = new Button("Confirmation");
		Button btnProm = new Button("Prompt");
		
		btnInfo.setOnAction(e->{
			// information 창 
			Alert info = new Alert(AlertType.INFORMATION);
			info.setTitle("INFORMATION");
			info.setHeaderText("INFORMATION 보기");
			info.setContentText("INFORMATION alert 창 입니다. "); // 실제 메시지
			info.showAndWait(); // 창을 보여주고 제어를 창이 닫힐때까지 멈춘다.
			//info.show();// 창을띄우고 다음 명령어가 자동으로 실행된다. 그래서 잘 사용하지 않는다
			//System.out.println("hello");
		});
		
		btnError.setOnAction(e->{
			// error 창
			Alert err = new Alert(AlertType.ERROR);
			err.setTitle("ERROR");
			err.setHeaderText("ERROR 보기");
			err.setContentText("ERROR alert 창 입니다. "); // 실제 메시지
			err.showAndWait();
		});
		
		btnWarn.setOnAction(e->{
			// warning 창 
			Alert warn = new Alert(AlertType.WARNING);
			warn.setTitle("WARNING");
			warn.setHeaderText("WARNING 보기");
			warn.setContentText("WARNING alert 창 입니다. "); // 실제 메시지
			warn.showAndWait();
		});
		
		//---------------
		
		btnConfirm.setOnAction(e->{
			// confirm 창
			// 버튼이 두개 나오고 선택하게 만들어 준다.-> 반환값이 존재 한다.  
			Alert conf = new Alert(AlertType.CONFIRMATION);
			conf.setTitle("CONFIRMATION");
			conf.setHeaderText("CONFIRMATION 보기");
			conf.setContentText("CONFIRMATION alert 창 입니다. "); // 실제 메시지
			
			// confirmation 창을 보여주고 사용자가 누른 버튼 값 읽어오기 
			ButtonType confResult = conf.showAndWait().get();
			
			// ok 버튼 또는 취소버튼 중 클릭한 버튼 구분하기 
			if(confResult == ButtonType.OK) {
				System.out.println("ok");
			}else if(confResult == ButtonType.CANCEL) {
				System.out.println("cancel");
			}
		});
		
		btnProm.setOnAction(e->{
			// 자바스크립트의 prompt창과 같은 기능입니다. 
			TextInputDialog prompt = new TextInputDialog("기본값"); // 기본값은 생략가능 
			
			prompt.setTitle("prompt");
			prompt.setHeaderText("prompt 자료입니다.");
			prompt.setContentText("입력 : "); //contentText 는 굳이 안써도 된다. 
			
			// 창을 보여주고, 창에서 입력한 값 가져오기 
			Optional<String> result = prompt.showAndWait();
			
			// 입력한 값을 출력하기 
			String strData = null; // 입력한 값이 저장될 변수 
			
			if(result.isPresent()) {
				// 값이 있다면. 
				strData = result.get(); // 실제 입력한 값 가져오기 
			}
			System.out.println("입력값 >> " + strData);
			// 확인 버튼을 누르면 입력한 값이 출력되지만 그냥 취소를 누르거나 입력값을 넣고 취소를 누른 경우 null값이 나온다. 
		});
		
		hbTop.getChildren().addAll(btnInfo,btnError,btnWarn);
		hbBottom.getChildren().addAll(btnConfirm,btnProm);
		
		root.getChildren().addAll(hbTop, hbBottom);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("alert 연습");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
