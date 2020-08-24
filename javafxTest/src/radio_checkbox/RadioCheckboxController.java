package radio_checkbox;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.corba.se.impl.orbutil.ObjectUtility;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class RadioCheckboxController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField InName;

    @FXML
    private RadioButton male;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton female;

    @FXML
    private CheckBox travel;

    @FXML
    private CheckBox moutain;

    @FXML
    private CheckBox reading;

    @FXML
    private CheckBox baduk;

    @FXML
    private CheckBox janggi;

    @FXML
    private CheckBox game;

    @FXML
    private CheckBox tennis;

    @FXML
    private CheckBox mintern;

    @FXML
    private Button btnLook;

    @FXML
    private TextArea OutValue;

    @FXML
    void submit(ActionEvent event) {
    	
    	String str = "";
    	
    	OutValue.setText("");
    	
    	String name = InName.getText();
    	if(name.isEmpty()) {
    		OutValue.setText("이름을 입력해주세요");
    		return;
    	}
    	
    	//OutValue.appendText(name+" 씨 \n 당신은 ");
    	str += name + " 씨 \n 당신은 ";
    	if(gender.getSelectedToggle()==female)
    	{
    		//OutValue.appendText("여성");
    		str +="여성";
    		
    	}else {
    		//OutValue.appendText("남성");
    		str += "남성";
    	}
    	//OutValue.appendText(" 이고 \n 취미는 ");
    	str += " 이고 \n 취미는 ";
    	
    	CheckBox chk = new CheckBox();
    	
    	boolean a = chk.isSelected();
    	
    	//체크박스 배열을만들어서 반복문돌리기 
    	CheckBox[] hobby = new CheckBox[] {travel,moutain,reading,baduk,janggi,game,tennis,mintern};
    	
    	boolean ch = true;
    	int cnt =0; 
    	
    	//성별도 변수를 따로 선언해서 따로 담는다. 
    	
    	/*
    	 * String hob ="";
    	 * for(int i=0; i<hobby.length; i++)
    	 * {
    	 * 		if(hobby[i].isSelected()){
    	 * 				if(!"".equals(hob)){ hob += ","; }
    	 * 				hob += hobby[i].getText();
    	 * 		}
    	 * }
    	 * OutValue.appendText("취미는 " +("".equals(hob) ? "없군요?" : hob+="이군요"));
    	 * 	OutValue.setText(hob);
    	 */
    	for(int i=0; i<hobby.length;i++) {
    		if(hobby[i].isSelected()) {
    			//OutValue.appendText(hobby[i].getText());
    			
    			if(hobby[i].isSelected()) { str+=","; }    			
    			str+=hobby[i].getText();
    			
    			ch=false;
    		}
    	}
    	
    	if(ch==true) {
    		//OutValue.appendText("없네요");
    		str += "없네요";
    	}
    	
    	OutValue.setText(str);
    }

    @FXML
    void initialize() {
        assert InName != null : "fx:id=\"InName\" was not injected: check your FXML file 'RadioCheckbox.fxml'.";
        assert male != null : "fx:id=\"male\" was not injected: check your FXML file 'RadioCheckbox.fxml'.";
        assert gender != null : "fx:id=\"gender\" was not injected: check your FXML file 'RadioCheckbox.fxml'.";
        assert female != null : "fx:id=\"female\" was not injected: check your FXML file 'RadioCheckbox.fxml'.";
        assert travel != null : "fx:id=\"travel\" was not injected: check your FXML file 'RadioCheckbox.fxml'.";
        assert moutain != null : "fx:id=\"moutain\" was not injected: check your FXML file 'RadioCheckbox.fxml'.";
        assert reading != null : "fx:id=\"reading\" was not injected: check your FXML file 'RadioCheckbox.fxml'.";
        assert baduk != null : "fx:id=\"baduk\" was not injected: check your FXML file 'RadioCheckbox.fxml'.";
        assert janggi != null : "fx:id=\"janggi\" was not injected: check your FXML file 'RadioCheckbox.fxml'.";
        assert game != null : "fx:id=\"game\" was not injected: check your FXML file 'RadioCheckbox.fxml'.";
        assert tennis != null : "fx:id=\"tennis\" was not injected: check your FXML file 'RadioCheckbox.fxml'.";
        assert mintern != null : "fx:id=\"mintern\" was not injected: check your FXML file 'RadioCheckbox.fxml'.";
        assert btnLook != null : "fx:id=\"btnLook\" was not injected: check your FXML file 'RadioCheckbox.fxml'.";
        assert OutValue != null : "fx:id=\"OutValue\" was not injected: check your FXML file 'RadioCheckbox.fxml'.";

    }
}
