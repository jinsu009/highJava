package gugudan;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GugudanEventController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField input;

    @FXML
    private Button result;

    @FXML
    private TextArea resultArea;

    @FXML
    void calculator(ActionEvent event) {

    	int num = Integer.parseInt(input.getText());

    	for(int i=1; i<=9;i++) {
    		int res = (num*i);
    		resultArea.appendText(num + " * " + i +" = "+res+"\n");
    	}
    }

    @FXML
    void initialize() {
        assert input != null : "fx:id=\"input\" was not injected: check your FXML file 'gugudanEvent.fxml'.";
        assert result != null : "fx:id=\"result\" was not injected: check your FXML file 'gugudanEvent.fxml'.";
        assert resultArea != null : "fx:id=\"resultArea\" was not injected: check your FXML file 'gugudanEvent.fxml'.";

    }
}
