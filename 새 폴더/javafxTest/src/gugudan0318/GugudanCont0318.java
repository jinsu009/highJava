package gugudan0318;

import java.net.URL;
import java.util.ResourceBundle;

import basic.util.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;

public class GugudanCont0318 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> DanBox;

    @FXML
    private Button btnSubmit;

    @FXML
    private TextArea Output;

    
    // 버튼을 클릭했을때 이벤트 처리 
    @FXML
    void calc(ActionEvent event) {
    	
    	// 콤보박스에서 출력할 단을 선택한 후 '출력'버튼을 클릭하면 선택한 단의 구구단을 area영역에 출력하시오
    	
    	
    	Output.setText("");

    	if(DanBox.getValue()==null) {
    		//Output.setText("단 을 선택하세요");
    		
//    		Alert alert = new Alert(AlertType.ERROR);
//    		alert.setTitle("오류");
//    		alert.setHeaderText("작업오류");
//    		alert.setContentText("출력할 단을 선택 하세요 ");
//    		alert.showAndWait();
    		AlertUtil.errorMsg("작업오류", "출력할 단을 선택하세요");
    		
    		return;
    	}
    	
    	// 콤보박스에서 선택한 값을 읽어온다. 
    	int num = DanBox.getValue();

    	Output.setText(num+" 단 \n\n");
    	
    	for(int i=1; i<=9;i++) {
    		int res = (num*i);
    		Output.appendText(num + " * " + i +" = "+res+"\n");
    	}
    	
    	
    
    }

    
    // 방법 2.
    ObservableList<Integer> danList = FXCollections.observableArrayList(); //observelist 객체 생성
    
    @FXML
    void initialize() {
    	// 방법 2.
    	danList.addAll(1,2,3,4,5,6,7,8,9);
    	DanBox.setItems(danList);
    	
    	//방법 1.
    	//DanBox.getItems().addAll(2,3,4,5,6,7,8,9);
    	//DanBox.setItems(DanBox.getItems());

    	
    	DanBox.setCellFactory(new Callback<ListView<Integer>, ListCell<Integer>>() {
		
    		//콤보박스를 눌렀을때 나오는 목록들을 설정하는 부분
			@Override
			public ListCell<Integer> call(ListView<Integer> param) {
				// TODO Auto-generated method stub
				return new ListCell<Integer>() {
					@Override
					protected void updateItem(Integer item, boolean empty) {
						// TODO Auto-generated method stub
						super.updateItem(item, empty);
						if(item==null || empty) {
							setText(null);
						}else {
							setText(item+ " 단");
						}
					}
				};
			}
		});
    	
    	//콤보박스에서 값을 선택했을때 나오는 부분을 설정하는 부분 
    	DanBox.setButtonCell(new ListCell<Integer>() {
    		@Override
    		protected void updateItem(Integer item, boolean empty) {
    			// TODO Auto-generated method stub
    			super.updateItem(item, empty);
    			if(item == null || empty) {
    				setText(null);
    			}else {
    				setText(item+ " 단");
    			}
    		}
    	});
    	
    	
    	

    }
}
