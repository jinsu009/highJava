package time;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import util.AlertUtil;

public class ContYKTime {
	// 부모창의 controller 객체 변수 선언 getter,setter을 만든다
//	private ContYKManage ykmanage;
//	
//    public ContYKManage getYkmanage() {
//		return ykmanage;
//	}
//
//	public void setYkmanage(ContYKManage ykmanage) {
//		this.ykmanage = ykmanage;
//	}

	 @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private RadioButton MFDayRd;

	    @FXML
	    private ToggleGroup Week;

	    @FXML
	    private RadioButton SatRd;

	    @FXML
	    private RadioButton SunRd;

	    @FXML
	    private ComboBox<String> OpenCB;

	    @FXML
	    private ComboBox<String> CloseCB;

	    @FXML
	    private Button UpdateBtn;

	    @FXML
	    private Button OkBtn;

	    @FXML
	    private Button CancleBtn;
	    

	    @FXML
	    void Confirm(ActionEvent event) {
	    	// 모든 시간을 적용 시키고 나서 종료 
	    	AlertUtil.infoMsg("확인", "영업시간이 적용되었습니다.");
	    	Stage currentStage = (Stage)OkBtn.getScene().getWindow();
			currentStage.close();
	    }

	    @FXML
	    void TimeUpdate(ActionEvent event) {
	    	// 평일, 주말 의 영업시간 선택후 적용 버튼을 눌러야 update되도록 구현 
	    	
	    	AlertUtil.infoMsg("확인", "영업시간이 적용되었습니다.");
	    }

	    @FXML
	    void UpdateCancle(ActionEvent event) {

//	    	AlertUtil.confirm("확인", "변경사항이 적용되지 않을 수 있습니다. 종료하시겠습니까?");
	    	Alert conf = new Alert(AlertType.CONFIRMATION);
			conf.setTitle("경고");
			conf.setHeaderText("경고");
			conf.setContentText("변경사항이 적용되지 않을 수 있습니다. 종료하시겠습니까?"); 
			
			// confirmation 창을 보여주고 사용자가 누른 버튼 값 읽어오기 
			ButtonType confResult = conf.showAndWait().get();
			
			// ok 버튼 또는 취소버튼 중 클릭한 버튼 구분하기 
			if(confResult == ButtonType.OK) {
				// 현재 창 닫기
				Stage currentStage = (Stage)CancleBtn.getScene().getWindow();
				currentStage.close();
			}else if(confResult == ButtonType.CANCEL) {
				return;
			}
	    }

	    @FXML
	    void initialize() {
	    	
	    	OpenCB.getItems().addAll("휴무","08:00","09:00","10:00","11:00","12:00","13:00");
	    	OpenCB.setValue(OpenCB.getItems().get(0));
	    	
	    	CloseCB.getItems().addAll("휴무","16:00","17:00","18:00","19:00","20:00","21:00"
	    			,"22:00","23:00","00:00");
	    	CloseCB.setValue(CloseCB.getItems().get(0));
	    	
	    }
}