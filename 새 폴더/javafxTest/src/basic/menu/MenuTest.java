package basic.menu;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		//root.setPadding(new Insets(10));
		
		MenuBar menuBar = new MenuBar(); //MenuBar 객체 생성
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty()); //메뉴바의 너비를 현재창의 너비와 연동되도록한다. 
		root.setTop(menuBar);
		
		// menu객체 생성 
		Menu fileMenu = new Menu("File");
		
		// menu 객체에 속할 부메뉴 객체 생성(MenuItem 객체) 생성
		MenuItem newMenuItem = new MenuItem("New");
		// MenuItem에 이미지 추가하기
		Image img = new Image(
				MenuTest.class.getResourceAsStream("../../img/Project.png"));
		ImageView imgView = new ImageView(img);
		newMenuItem.setGraphic(imgView); // 메뉴아이템에 이미지 넣기 
		
		MenuItem saveMenuItem = new MenuItem("Save");
		MenuItem exitMenuItem = new MenuItem("Exit");
		
		// MenuItem에 단축키 설정하기 (Ctrl + X)
		exitMenuItem.setAccelerator(
				new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN /*, KeyCombination.ALT_DOWN*/));
		
		// 메뉴를 선택했을때의 이벤트 처리 (버튼을 클릭했을 때 이벤트 처리와 같은 방법으로 기술한다.)
		exitMenuItem.setOnAction(e ->{
			Platform.exit();
		});
		
		// Menu 객체에 MenuItem객체 추가하기 
		fileMenu.getItems().addAll(newMenuItem, saveMenuItem,
				new SeparatorMenuItem() ,exitMenuItem);
		
		//new SeparatorMenuItem() : menuitem들 사이의 '선'그리기
		
		//----------------
		Menu webMenu = new Menu("Web");
		// 체크박스는 여러개 중복해서 선택 할 수 잇다. 
		CheckMenuItem htmlItem = new CheckMenuItem("HTML");
		CheckMenuItem cssItem = new CheckMenuItem("CSS");
		cssItem.setSelected(true); //css가 맨처음에 체크되어 있도록 셋팅
		CheckMenuItem scriptItem = new CheckMenuItem("JavaScript");
		
		webMenu.getItems().addAll(htmlItem, cssItem, scriptItem);
		//----------------
		Menu dbMenu = new Menu("DataBase");
		// 라디오 버튼 toggle group 생성 .. 하나만 선택할 수 있다. 
		ToggleGroup tg = new ToggleGroup();
		RadioMenuItem mySqlItem = new RadioMenuItem("MySql");
		mySqlItem.setToggleGroup(tg);
		RadioMenuItem oracleItem = new RadioMenuItem("Oracle");
		oracleItem.setToggleGroup(tg);
		RadioMenuItem msSqlItem = new RadioMenuItem("Ms-Sql");
		msSqlItem.setToggleGroup(tg);
		
		dbMenu.getItems().addAll(mySqlItem, oracleItem, msSqlItem);
		//----------------
		//부메뉴의 부메뉴 만들기 
		// 부메뉴도 메뉴객체로 만든다. 
		Menu tutorialMenu = new Menu("Tutorial");
		tutorialMenu.getItems().addAll(
				new CheckMenuItem("Java 초급"),
				new CheckMenuItem("Java 중급"),
				new CheckMenuItem("UI 설계")
				);
		
		dbMenu.getItems().addAll(new SeparatorMenuItem(), tutorialMenu);
		
		// menubar객체에 menu객체 추가 
		menuBar.getMenus().addAll(fileMenu, webMenu, dbMenu);
		
		
		Scene scene = new Scene(root, 300, 250);
		primaryStage.setScene(scene);
		primaryStage.setTitle("메뉴 만들기");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
