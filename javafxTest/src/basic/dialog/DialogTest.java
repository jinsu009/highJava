package basic.dialog;


import java.awt.Dialog;
import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DialogTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		FlowPane root = new FlowPane();
		
		root.setPrefSize(400, 300); 
		root.setPadding(new Insets(10));
		root.setVgap(10);
		root.setHgap(10);
		
		Button btnOpen = new Button("Open FileChooser 연습");
		btnOpen.setOnAction(e->{
			FileChooser fileChooser = new FileChooser();
			// 화면에 보여줄 파일 종류를 결정하기
			fileChooser.getExtensionFilters().addAll(
					new ExtensionFilter("Text File","*.txt"),
					new ExtensionFilter("Image File","*.png","*.jpg","*.gjf"),
					new ExtensionFilter("All File","*.*")
					);
			
			// 창이 열렸을 때 보여줄 폴더(디렉토리) 설정
			File showDir = new File("d:/D_Other/");
			fileChooser.setInitialDirectory(showDir);
			
			// '열기 창'을 보여주고 사용자가 선택한 파일 정보를 반환한다. 
			File selectedFile = fileChooser.showOpenDialog(primaryStage);
			if(selectedFile!=null) {
				// 이 부분에서 실제 선택할 파일의 내용을 읽어노는 작업을 기술한다. 
				System.out.println("선택한 파일 정보 >> "+selectedFile.getPath());
			}
		});
		
		Button btnSave = new Button("Save FileChooser 연습");
		btnSave.setOnAction(e->{
			FileChooser fileChooser = new FileChooser();
			// 화면에 보여줄 파일 종류를 결정하기
			fileChooser.getExtensionFilters().addAll(
					new ExtensionFilter("Text File","*.txt"),
					new ExtensionFilter("All File","*.*")
					);
			// '열기 창'을 보여주고 사용자가 선택한 파일 정보를 반환한다. 
			File selectedFile = fileChooser.showSaveDialog(primaryStage);
			if(selectedFile!=null) {
				// 이 부분에서 선택한 파일 정보를 이용해서 실제 저장하는 작업을 기술한다.
				System.out.println("선택한 파일 정보 >> "+selectedFile.getPath());
			}
			
		});
		
		Button btnDir = new Button("DirectoryChooser 연습"); // 폴더선택
		btnDir.setOnAction(e->{
			DirectoryChooser dirChooser = new DirectoryChooser();
			File selectedDir = dirChooser.showDialog(primaryStage);
			if(selectedDir != null) {
				System.out.println("선택한 폴더 : " + selectedDir.getPath());
			}
		});
		
		Button btnPop = new Button("Popup 연습");
		btnPop.setOnAction(e->{
			// 알림창과 같이 간단한 내용을 보여줄때 사용한다. 
			Popup popup = new Popup();
			
			HBox popRoot = new HBox();
			popRoot.setAlignment(Pos.CENTER_LEFT);
			popRoot.setStyle("-fx-background-color:black; -fx-background-radius:20;");
			ImageView imgview = new ImageView();
			imgview.setImage(new Image
					(DialogTest.class.getResourceAsStream("../../img/ok.png")));
			imgview.setFitHeight(30);
			imgview.setFitWidth(30);
			imgview.setOnMouseClicked(ee->{
				popup.hide(); // popup 닫기 
			});
			
			Label lblMsg = new Label("메시지가 왔습니다.");
			lblMsg.setTextFill(Color.RED);
			HBox.setMargin(lblMsg, new Insets(0,5,0,5));
			popRoot.getChildren().addAll(imgview, lblMsg);
			popup.getContent().add(popRoot);
			//popup.setAutoHide(true); //Popup영역이외의 부분을 누르면 자동으로 닫힌다. 
			popup.show(primaryStage); // 부모창을 매개값으로 준다. 
		});
		
		
		Button btnCustom = new Button("Custom Dialog 연습");
		
		btnCustom.setOnAction(e->{
			try {
				//자식창의 stage객체 생성 
				Stage dialog = new Stage(StageStyle.DECORATED); // 새 창 띄우기 : stage
				//Stage dialog = new Stage();//=decorated
				//Stage dialog = new Stage(StageStyle.UNDECORATED);
				//Stage dialog = new Stage(StageStyle.UTILITY);
				//Stage dialog = new Stage(StageStyle.UNIFIED);
				//Stage dialog = new Stage(StageStyle.TRANSPARENT); // 투명창
				//Stage dialog = new Stage(StageStyle.TRANSPARENT); // 투명창 만들기 조건 1.
				
				//stagestyle 을 생략하면 그냥 기본창이 나온다. 
				dialog.initModality(Modality.WINDOW_MODAL); //모달창 여부 지정
				
				//모달창 : 창이 나올때 자식창이 닫혀야지만 뒤에 있던 부모창을 열수 있따. 
				
				// 부모창 지정 
				dialog.initOwner(primaryStage);
				
				//자식창의 fxml 문서 읽기 
				Parent childRoot = FXMLLoader.load(DialogTest.class.getResource("custom.fxml"));
				
				//childRoot.setStyle("-fx-background-color:transparent;");// 투명창 만들기 조건 2.
				
				//새 창이 생겼을 때를 대비해서 controller클래스를 따로 만들어두는 것이 좋다. 
				// 이번예제처럼 간단한경우에는 ..
				//자식창 컨트롤에 설정된 id값을 이용해서 해당컨트롤 객체를 구할 수 있다. 
				// 형식 )) fxml묹서를 load한 parent 객체변수.lookup("#컨트롤의 아이디");
				Button btnTest = (Button) childRoot.lookup("#btnOk"); //반환값이 object이기 때문에 형변환이 필요
				btnTest.setOnAction(ee->{
//					Platform.exit(); // 부모창도 같이 종료되어 버린다. 
					dialog.close(); // 자식창 닫기 
				});
				
				
				// 자식창에 추가할 scene객체 생성 
				Scene childScene = new Scene(childRoot);
				
				//childScene.setFill(Color.TRANSPARENT);// 투명창 만들기 조건 3.
				
				// 자식창 stage에 scene 객체 추가 
				dialog.setScene(childScene);
				dialog.setTitle("자식창 연습");
				
				// 자식창 보이기 
				dialog.show();
				
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		});
	
		root.getChildren().addAll(btnOpen, btnSave, btnDir, btnPop, btnCustom);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Dialog 연습");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
