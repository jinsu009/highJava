package basic.thread;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ThreadTimerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblTime;

    @FXML
    private Button btnStart;

    @FXML
    private Button btnStop;
    
    private boolean isStop; // 쓰레드가 멈출지 여부를 나타내는 변수 (true이면 멈춘다.)

    //시작 버튼 
    @FXML
    void btnStartClick(ActionEvent event) {

    	isStop = false;
    	 
    	Thread th = new Thread() {
    		@Override
    		public void run() {
    			// 시간 출력형식 설정
    			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); 
    			while(!isStop) {
    				// 현재 시스템의 시간 정보를 가져와서 시간 출력 형식에 맞는 문자열을 구한다. 
    				String strTime = sdf.format(new Date());
    				//Date : import> Util
    				
    				
    				// 일반 쓰레드에서 javafx의 UI컨트롤의 내용을 변경하려면 
    				// Platform.runLater()메소드를 이용해서 출력해야한다.
    				// 이 메소드에서는 runnable인터페이스를 구현한 구현체가 매개값으로 들어간다. 
    				
    				//매개변수 없는 람다식 작성
    				Platform.runLater(()->{
    					// 이 영역에 UI컨트롤을 변경하는 명령을사용한다. 
    					lblTime.setText(strTime);
    				});
    				
    				
    				try { Thread.sleep(100); //쉬는 타임 
    				} catch (InterruptedException e) { }
    				
    			}
    		}
    	}; // 쓰레드 끝! .. 현재시간을 가져와 label에 담아준다. 
    	
    	th.setDaemon(true);
    	th.start();
    }

    //종료 버튼 
    @FXML
    void btnStopClick(ActionEvent event) {
    	isStop = true;
    }

    @FXML
    void initialize() {
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'ThreadTimer.fxml'.";
        assert btnStart != null : "fx:id=\"btnStart\" was not injected: check your FXML file 'ThreadTimer.fxml'.";
        assert btnStop != null : "fx:id=\"btnStop\" was not injected: check your FXML file 'ThreadTimer.fxml'.";

    }
}
