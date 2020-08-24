package basic.controlls;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FxmlGugudancontroller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfDan;

    @FXML
    private Button btnOut;

    @FXML
    private TextArea taResult;

    @FXML
    void btnOutClicked(ActionEvent event) {
    	//출력버튼을 클릭했을 때 이벤트 처리
    	//textField에 입력한 값 읽어오기 
    	String strDan = tfDan.getText();
    	//입력한 값을 숫자형으로 변환

    	if(strDan.isEmpty()) {
    		//System.out.println("출력단을 입력하세요 ");
    		taResult.setText("출력할 단을 입력하세요");
    		return;
    	}
    	if(!Pattern.matches("[1-9][0-9]*", strDan)) {
    		taResult.setText("숫자를 입력해주세요");
    		
    	}
    	
    	int dan = Integer.parseInt(strDan);
    	
    	taResult.setText(""); //내용지우기
    	//taResult.clear(); //내용지우기
    	
    	tfDan.clear();
    	tfDan.requestFocus(); //포커스를 주는 것
    	
    	taResult.setText(dan+"단\n\n");
    	for(int i=1;i<=9;i++) {
    		int r = dan*i;
    		taResult.appendText(dan+"*"+i+"="+r+"\n");
    	}
    	
    }

    @FXML
    void initialize() {
        assert tfDan != null : "fx:id=\"tfDan\" was not injected: check your FXML file 'fxmlGugudan.fxml'.";
        assert btnOut != null : "fx:id=\"btnOut\" was not injected: check your FXML file 'fxmlGugudan.fxml'.";
        assert taResult != null : "fx:id=\"taResult\" was not injected: check your FXML file 'fxmlGugudan.fxml'.";

    }
}
