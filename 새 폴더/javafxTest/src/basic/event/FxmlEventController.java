package basic.event;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FxmlEventController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private TextField tfMsg;

    @FXML
    private TextArea txtArea;

    //첫번째 버튼에 대한 이벤트를 처리하는 메소드 
    // fxml문서에서 처리할 메소드명을 설정했다. 
    @FXML
    void btn1Clicked(ActionEvent event) {
    	String temp = tfMsg.getText();
    	txtArea.appendText("첫번째 버튼 처리 : " + temp +"\n");
    	}

    @FXML
    void initialize() {
    	
    	// 이 메소드는 fxml 문서가 load된 후 제일 먼저 자동으로 샐행되는 메소드 
    	// 그래서 이 메소드에서는 주로 초기화작업이나 이벤트 설정작업등을 기술한다. 
        assert btn1 != null : "fx:id=\"btn1\" was not injected: check your FXML file 'fxmlEvent.fxml'.";
        assert btn2 != null : "fx:id=\"btn2\" was not injected: check your FXML file 'fxmlEvent.fxml'.";
        assert tfMsg != null : "fx:id=\"tfMsg\" was not injected: check your FXML file 'fxmlEvent.fxml'.";
        assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'fxmlEvent.fxml'.";
        
        // 두번째 버튼에 대한 이벤트 설정(java코드로 이벤트 설정하는 방법으로 처리한다.)
        btn2.setOnAction(e->{
        	String temp = tfMsg.getText();
        	txtArea.appendText("두번째 버튼의 이벤트 처리 : " + temp +"\n");
        });
    }
}
