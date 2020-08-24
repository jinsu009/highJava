package basic.menu;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class JavaFxMenu_hw extends Application {

	private String fileName = "NoName.txt";
	private File fileDir = new File("d:/D_Other"); //문서를 읽어올 기본 폴더 설정
	
	@Override
	public void start(Stage primaryStage) {
		
		TextArea taTemp = new TextArea();
		
		BorderPane root = new BorderPane();
		
		MenuBar menuBar = new MenuBar(); 
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty()); //메뉴바의 너비
		root.setTop(menuBar);
		
		Menu fileMenu = new Menu("File");
		
		MenuItem newMenuItem = new MenuItem("새로만들기");
		newMenuItem.setOnAction(e->{
			taTemp.setText("");
			primaryStage.setTitle("NoName.txt");
		});
		
		MenuItem openMenuItem = new MenuItem("열기");
		openMenuItem.setOnAction(e->{
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(
					new ExtensionFilter("Text File","*.txt"),
					new ExtensionFilter("All File","*.*")
					);
			File selectedFile = fileChooser.showOpenDialog(primaryStage);
			fileChooser.setInitialDirectory(fileDir);
			
			File selFile = fileChooser.showOpenDialog(primaryStage);
			if(selFile==null) return; //열기취소 
			
			fileDir = selFile.getParentFile();
			fileName = selFile.getName();
			
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(selFile));
				taTemp.clear();
				char[] data = new char[1024];
				int len =0;
				while((len=br.read(data))>0) {
					String strTemp = new String(Arrays.copyOf(data, len));
					taTemp.appendText(strTemp);
				}
			} catch (IOException e2) {
				fileName="NoName.txt";
				e2.printStackTrace();
			}finally {
				if(br!=null) {
					try {br.close();} catch(IOException e3) {}
				}
			}
			
			primaryStage.setTitle(selectedFile.getName());
			
			
//			//File fpath = new File(selectedFile.getPath());
//			String str = null;
//			if(selectedFile!=null) {
//				//taTemp.setText(selectedFile.getAbsolutePath());
//				try {
//					FileInputStream fin = new FileInputStream(selectedFile);
//					InputStreamReader isr = new InputStreamReader(fin, "UTF-8");
//					int c;
//					str="";
//					while((c=isr.read()) != -1) {
//						str += (char) c;
//					}
//					taTemp.setText(str);
//					isr.close();
//					
//				} catch (IOException e2) {
//					// TODO: handle exception
//				}
//			}
		});
		
		
		
		MenuItem saveMenuItem = new MenuItem("새 이름으로 저장");
		saveMenuItem.setOnAction(e->{
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(
					new ExtensionFilter("Text File","*.txt"),
					new ExtensionFilter("All File","*.*")
					);
			File selectedFile = fileChooser.showSaveDialog(primaryStage);
			if(selectedFile==null) {return;}
			
			fileDir = selectedFile.getParentFile();
			//저장할 파일명
			fileName = selectedFile.getName();
			BufferedWriter bw = null;
			if(selectedFile!=null) {
				try {
					bw = new BufferedWriter(new FileWriter(selectedFile));
					
					// 줄바꿈 (\n)을 (\n\r)로 변경한다. 
					bw.write(taTemp.getText().replace("\n", "\r\n"));
				} catch (IOException e2) {
					e2.printStackTrace();
				}finally {
					if(bw!=null)try {bw.close();}catch(IOException e3) {}
				}
				System.out.println("저장완료");
			}
		});
		
		MenuItem exitMenuItem = new MenuItem("닫기");
		exitMenuItem.setOnAction(e ->{
			Platform.exit();
		});
		
		
		fileMenu.getItems().addAll(newMenuItem, openMenuItem, saveMenuItem,new SeparatorMenuItem(),exitMenuItem);
		
		menuBar.getMenus().addAll(fileMenu);
		
		
		
		root.setCenter(taTemp);
		
		Scene scene = new Scene(root, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("NoName.txt");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
