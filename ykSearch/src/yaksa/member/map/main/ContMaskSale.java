package yaksa.member.map.main;

import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class ContMaskSale {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane MaskMapView;

    @FXML
    private Button BackStageBtn;

    @FXML
    void BackStage(ActionEvent event) {
    	//뒤로가기 버튼을 눌렀을 때 전화면으로 돌아간다. 
    	try {
			FXMLLoader insert = new FXMLLoader(ContYKSearch.class.getResource("../fxml/YKSearch.fxml"));
			Parent childRoot = insert.load();
			Scene scene = new Scene(childRoot);
			Stage primaryStage = (Stage) BackStageBtn.getScene().getWindow();
			primaryStage.setScene(scene);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
 // webview를 불러오는 메소드
 	private  void initFX() {
 		JFrame frame = new JFrame("FX");

 		frame.getContentPane().setLayout(null);

 		final JFXPanel fxPanel = new JFXPanel();

 		frame.add(fxPanel);
 		frame.setVisible(true);

 		fxPanel.setSize(new Dimension(750,400));
 		fxPanel.setLocation(new Point(0, 27));

 		frame.getContentPane().setPreferredSize(new Dimension(750,400));
 		frame.pack();
 		frame.setResizable(false);

 		Platform.runLater(new Runnable() {
 			public void run() {
 				initAndLoadWebView(fxPanel);
 			}
 		});
 	}

 	private  void initAndLoadWebView(final JFXPanel fxPanel) {
 		Group group = new Group();
 		Scene scene = new Scene(group);
 		fxPanel.setScene(scene);

 		WebView webview = new WebView();
 		group.getChildren().add(webview);
 		webview.setMinSize(750,400);
 		webview.setMaxSize(750,400);

 		WebEngine webEngine = webview.getEngine();
 		webEngine.load("http://localhost/ykMap/PublicMaskSale.html");
 	}

    @FXML
    void initialize() {
    	
    	WebView webview = new WebView();
		WebEngine webEngine = webview.getEngine();
		webEngine.load("http://localhost/ykMap/PublicMaskSale.html");
		MaskMapView.getChildren().add(webview);
    	
    }
}
