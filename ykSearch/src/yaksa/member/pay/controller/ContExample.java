package yaksa.member.pay.controller;

import java.awt.Dimension;
import java.awt.Point;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JButton;
//================
import javax.swing.JFrame;

import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

public class ContExample {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Pane webview; // 카카오페이 웹 페이지가 나올 pane

	@FXML
	private TextField examfield; // 임시 필드 

	@FXML
	private Button btn; // 임시 버튼 

	private JSObject javascriptConnector;

	private JavaConnector javaConnector = new JavaConnector();;

	private void initFX() {

		JFrame frame = new JFrame("FX");
		frame.getContentPane().setLayout(null);
		final JFXPanel fxPanel = new JFXPanel();
		frame.add(fxPanel);
		frame.setVisible(true);
		fxPanel.setSize(new Dimension(600, 600));
		fxPanel.setLocation(new Point(0, 27));
		frame.getContentPane().setPreferredSize(new Dimension(600, 600));
		frame.pack();
		frame.setResizable(false);
		initAndLoadWebView(fxPanel);
	}

	public class JavaConnector {
		/**
		 * called when the JS side wants a String to be converted.
		 *
		 * @param value the String to convert
		 */
		public void toLowerCase() {
			// 멤버의 결제 가 완성이 되면 alert창 혹은 다른 알림창을 띄워주거나 메인화면으로 돌아가도록 설정?
			System.out.println("결재완료");
		}
		public void send(String yaksa, String price) {
			// 약국이름과 가격을 전송 
			javascriptConnector.call("showResult", yaksa, price);
		}
	}

	private void initAndLoadWebView(final JFXPanel fxPanel) {
		Group group = new Group();
		Scene scene = new Scene(group);
		fxPanel.setScene(scene);
		WebView webView = new WebView();
		group.getChildren().add(webView);
		webView.setMinSize(600, 450);
		webView.setMaxSize(600, 550);
		WebEngine webEngine = webView.getEngine();

//		webEngine.load("http://localhost:8081/JqueryPro/NewFile2.html");
		webEngine.load("http://localhost/ykMap/kakaopay.html");

		webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
			if (Worker.State.SUCCEEDED == newValue) {
				// set an interface object named 'javaConnector' in the web engine's page
				JSObject window = (JSObject) webEngine.executeScript("window");
				window.setMember("javaConnector", javaConnector);

				// get the Javascript connector object.
				javascriptConnector = (JSObject) webEngine.executeScript("getJsConnector()");
			}
		});

	}

	// 임시로 만든 메소드 
	public void toKaKao(String Yaksa, String price) {

		javaConnector.send(Yaksa, price);
	}

	@FXML
	void initialize() {
		// vo에서 call("showResult", 약국이름, 가격 )
		
		initFX(); 
		
		btn.setOnAction(e->{
			toKaKao("미소담약국", "8000");
		});

		assert webview != null : "fx:id=\"webview\" was not injected: check your FXML file 'example.fxml'.";

	}
}
