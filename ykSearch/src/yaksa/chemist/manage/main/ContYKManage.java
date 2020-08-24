package yaksa.chemist.manage.main;


import java.awt.Dimension;
import java.awt.Point;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import util.AlertUtil;
import yaksa.chemist.manage.service.ManageServiceImpl;
import yaksa.chemist.manage.vo.PharmVO;
import ykMap.service.SearchServiceImpl;

public class ContYKManage {
	private JSObject javascriptConnector;
	private JavaConnector javaConnector = new JavaConnector();
	
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label PharmName;

	@FXML
	private TextField IDField;

	@FXML
	private TextField PWField;

	@FXML
	private TextField CHField;

	@FXML
	private TextField Reg1Field;

	@FXML
	private TextField Reg2Field;

	@FXML
	private TextField LiField;

	@FXML
	private TextField CorField;

	@FXML
	private TextField EM1Field;

	@FXML
	private TextField EM2Field;

	@FXML
	private TextField ZCField;

	@FXML
	private Button ZipcodeBtn;

	@FXML
	private TextField Add1Field;

	@FXML
	private TextField Add2Field;

	@FXML
	private TextField TelField;

	@FXML
	private Button UpdateBtn;

	@FXML
	private Button UpdateFinBtn;

	@FXML
	private Button UpdateCanBtn;

	@FXML
	private Button DeleteBtn;

	@FXML
	private ToggleGroup weekend;

	@FXML
	private RadioButton MFDayRd;

	@FXML
	private RadioButton SatRd;

	@FXML
	private RadioButton SunRd;

	@FXML
	private ComboBox<String> OpenCB;

	@FXML
	private ComboBox<String> CloseCB;

	@FXML
	private Button TimeUpdateBtn;
	
    @FXML
    private Label participant;

	@FXML
	private Label Point;

	@FXML
	private Label Rate;

	private ManageServiceImpl service;
	private SearchServiceImpl service2;
	PharmVO pvo = null;

	@FXML
	void DeleteFin(ActionEvent event) {
		// 약국 회원 탈퇴
		// 비밀번호 입력후 탈퇴 승인 약국 메인으로 이동

		pvo = new PharmVO();
 
		String pharmID = pvo.getPharm_id();

		Alert conf = new Alert(AlertType.CONFIRMATION);
		conf.setTitle("경고");
		conf.setHeaderText("경고");
		conf.setContentText("회원을 탈퇴 하시겠습니까?");

		// confirmation 창을 보여주고 사용자가 누른 버튼 값 읽어오기
		ButtonType confResult = conf.showAndWait().get();

		// ok 버튼 또는 취소버튼 중 클릭한 버튼 구분하기
		if (confResult == ButtonType.OK) {
			TextInputDialog prompt = new TextInputDialog("비밀번호입력"); // 기본값은 생략가능
			prompt.setTitle("탈퇴");
			prompt.setHeaderText("비밀번호를 입력하세요");
			prompt.setContentText("입력 : "); // contentText 는 굳이 안써도 된다.

			// 창을 보여주고, 창에서 입력한 값 가져오기
			Optional<String> result = prompt.showAndWait();

			// 입력한 값을 출력하기
			String strData = null; // 입력한 값이 저장될 변수

			if (result.isPresent()) {
				// 값이 있다면.
				strData = result.get(); // 실제 입력한 값 가져오기
			}
			// 비밀번호가 일치하면 delete문 실행

			String pharmpw = pvo.getPharm_pw();

			if (pharmpw.equals(strData)) {
				pvo.setPharm_available("1");// 탈퇴한 회원 : 1
				ManageServiceImpl.getInstance().deleteYK(pharmID);
			} else {
				AlertUtil.errorMsg("경고", "패스워드가 다릅니다");
				return;
			}

			AlertUtil.infoMsg("알림", "탈퇴 되었습니다.");

			// 메인 화면으로 이동

		} else if (confResult == ButtonType.CANCEL) {
			return;
		}
	}

	@FXML
	void TimeUpdate(ActionEvent event) { 
		
		// 평일, 주말 의 영업시간 선택후 적용 버튼을 눌러야 update되도록 구현
//    	pvo = new PharmVO();
		Map<String, String> map = new HashMap<String, String>();
		if (weekend.getSelectedToggle() != null) {
			if (MFDayRd.isSelected() == true) {
				
				map.put("day_open", "pharm_opentime"); // 키 값, 속성값
				map.put("opentime", OpenCB.getValue()); // 키 값, 속성에 넣을 값
				map.put("day_close", "pharm_closetime"); // 키 값, 속성값
				map.put("closetime", CloseCB.getValue()); // 키 값, 속성에 넣을 값
				service.UpdateTimeYK(map);
			}

			if (SatRd.isSelected() == true) {
				map.put("day_open", "pharm_satopentime");
				map.put("opentime", OpenCB.getValue());
				map.put("day_close", "pharm_satclosetime");
				map.put("closetime", CloseCB.getValue());
				service.UpdateTimeYK(map);
			}

			if (SunRd.isSelected()==true) {
				map.put("day_open", "pharm_sunopentime");
				map.put("opentime", OpenCB.getValue());
				map.put("day_close", "pharm_sunclosetime");
				map.put("closetime", CloseCB.getValue());
				service.UpdateTimeYK(map);
			}
		}

		AlertUtil.infoMsg("확인", "영업시간이 적용되었습니다.");
	}

	@FXML
	void UpdateCancle(ActionEvent event) {

		AlertUtil.infoMsg("확인", "정보변경이 취소되었습니다.");

		// textfield에 값 다시 불러오기
		pharmGetAll();

		// textfield 비활성화
		PWField.setDisable(true); // 패스워드
		CHField.setDisable(true); // 약사 이름
		EM1Field.setDisable(true); // 이메일주소1
		EM2Field.setDisable(true); // 이메일주소2
		ZCField.setDisable(true); // 우편번호
		Add1Field.setDisable(true); // 주소1
		Add2Field.setDisable(true); // 주소2
		TelField.setDisable(true); // 전화번호

		UpdateCanBtn.setDisable(true); // 수정취소 버튼 비활성화
		UpdateFinBtn.setDisable(true); // 수정완료 버튼 비활성화
		DeleteBtn.setDisable(true); // 회원탈퇴 버튼 비활성화
		ZipcodeBtn.setDisable(true); // 우편번호검색 버튼 비활성화
	}

	public class JavaConnector{
		public void toLowerCase(String zipcode,String addr) {
		
			// kakaozipcode와 연결되어서 webview에 입력된 값을 그대로 가져와 pharmvo에 업데이트 시킨다. 
			// zipcode, add1, add2
			pvo = new PharmVO();
			ZCField.setText(zipcode);
			Add1Field.setText(addr);
			
			pvo.setPharm_zip_code(Integer.parseInt(zipcode));
			pvo.setPharm_add1(addr);
			pvo.setPharm_add2(Add2Field.getText());
			
			service.updateZipYK(pvo);

		}
	}
	
	@FXML
	void UpdateFinish(ActionEvent event) {
		// TextField에 새로 입력된 값들이 update되도록 한다.
		
		pvo = new PharmVO();

		String input_pw = PWField.getText();
		String input_ch = CHField.getText();
		String input_em1 = EM1Field.getText();
		String input_em2 = EM2Field.getText();
		String input_tel = TelField.getText();

		if (input_pw.isEmpty() || input_ch.isEmpty() || input_em1.isEmpty() || input_em2.isEmpty()||input_tel.isEmpty()) {
			AlertUtil.errorMsg("입력오류", "빈 항목이 있습니다.");
			return;
		}

		// textfield에 입력된 값들을 vo에 업데이트 시킨다.

		pvo.setPharm_pw(PWField.getText());
		pvo.setPharm_chname(CHField.getText());
		pvo.setPharm_email1(EM1Field.getText());
		pvo.setPharm_email2(EM2Field.getText());
		pvo.setPharm_tel(TelField.getText());
		
		service.updateYK(pvo);
		
		AlertUtil.infoMsg("확인", "정보가 변경되었습니다");

		PWField.setDisable(true); // 패스워드
		CHField.setDisable(true); // 약사 이름
		EM1Field.setDisable(true); // 이메일주소1
		EM2Field.setDisable(true); // 이메일주소2
		ZCField.setDisable(true); // 우편번호
		Add1Field.setDisable(true); // 주소1
		Add2Field.setDisable(true); // 주소2
		TelField.setDisable(true); // 전화번호

		UpdateCanBtn.setDisable(true); // 수정취소 버튼 비활성화
		UpdateFinBtn.setDisable(true); // 수정완료 버튼 비활성화
		DeleteBtn.setDisable(true); // 회원탈퇴 버튼 비활성화
		ZipcodeBtn.setDisable(true); // 우편번호검색 버튼 비활성화
	}

	@FXML
	void YKUpdate(ActionEvent event) {
		// TextField 를 활성화

		PWField.setDisable(false); // 패스워드
		CHField.setDisable(false); // 약사 이름
		EM1Field.setDisable(false); // 이메일주소1
		EM2Field.setDisable(false); // 이메일주소2
		ZCField.setDisable(false); // 우편번호
		Add1Field.setDisable(false); // 주소1
		Add2Field.setDisable(false); // 주소2
		TelField.setDisable(false); // 전화번호

		UpdateCanBtn.setDisable(false); // 수정취소 버튼 활성화
		UpdateFinBtn.setDisable(false); // 수정완료 버튼 활성화
		DeleteBtn.setDisable(false); // 회원탈퇴 버튼 활성화
		ZipcodeBtn.setDisable(false); // 우편번호검색 버튼 비활성화
	}

	void pharmGetAll() {
		List<PharmVO> pList = service.getAllYK();

		pvo = pList.get(0);

		IDField.setText(pvo.getPharm_id());
		PWField.setText(pvo.getPharm_pw());
		CHField.setText(pvo.getPharm_chname());
		Reg1Field.setText(pvo.getPharm_regno1());
		Reg2Field.setText(pvo.getPharm_regno2());
		LiField.setText(pvo.getPharm_license_num());
		CorField.setText(pvo.getPharm_corpor_num());
		EM1Field.setText(pvo.getPharm_email1());
		EM2Field.setText(pvo.getPharm_email2());
		ZCField.setText(Integer.toString(pvo.getPharm_zip_code()));
		Add1Field.setText(pvo.getPharm_add1());
		Add2Field.setText(pvo.getPharm_add2());
		TelField.setText(pvo.getPharm_tel());
	}
	
	@FXML
	void ZipcodeSearch(ActionEvent event) {
		// 우편번호 검색 버튼
		// 주소 창 업데이트
		initFX();
	}
	// webview를 불러오는 메소드
		private void initFX() {
			JFrame frame = new JFrame("FX");

			frame.getContentPane().setLayout(null);

			final JFXPanel fxPanel = new JFXPanel();

			frame.add(fxPanel);
			frame.setVisible(true);

			fxPanel.setSize(new Dimension(380, 420));
			fxPanel.setLocation(new Point(0, 27));

			frame.getContentPane().setPreferredSize(new Dimension(380, 420));
			frame.pack();
			frame.setResizable(false);

			Platform.runLater(new Runnable() {
				public void run() {
					initAndLoadWebView(fxPanel);
				}
			});
		}

		private void initAndLoadWebView(final JFXPanel fxPanel) {
			Group group = new Group();
			Scene scene = new Scene(group);
			fxPanel.setScene(scene);

			WebView webview = new WebView();
			group.getChildren().add(webview);
			webview.setMinSize(380, 420);
			webview.setMaxSize(380, 420);

			WebEngine webEngine = webview.getEngine();
			webEngine.load("http://localhost/ykMap/kakaozipcode.html");
			
			// kakaozipcode의 webview와 연동되어서 값을 가져오는 부분 
			webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue)->{
			if(Worker.State.SUCCEEDED==newValue) {
				JSObject window = (JSObject) webEngine.executeScript("window");
				window.setMember("javaConnector", javaConnector);
				javascriptConnector = (JSObject) webEngine.executeScript("getJsConnector()");
			}
		});
			
		}
		
	@FXML
	void initialize() {

		service = ManageServiceImpl.getInstance();
		service2 = SearchServiceImpl.getInstance();
		
		List<PharmVO> plist = service.getAllYK();
		
		pvo = plist.get(0);
		
		//label에 값 넣기 
		PharmName.setText(pvo.getPharm_chname());
		participant.setText("60");
		Point.setText("★★★★★");
		Rate.setText(Integer.toString(pvo.getPharm_starrate()));

		OpenCB.getItems().addAll("휴무", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00");
		OpenCB.setValue(OpenCB.getItems().get(0));
		
		CloseCB.getItems().addAll("휴무", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "00:00");
		CloseCB.setValue(CloseCB.getItems().get(0));
	
		IDField.setDisable(true); // 아이디
		PWField.setDisable(true); // 패스워드
		CHField.setDisable(true); // 약사 이름
		Reg1Field.setDisable(true); // 주민번호1
		Reg2Field.setDisable(true); // 주민번호2
		LiField.setDisable(true); // 면허 번호
		CorField.setDisable(true); // 사업자 번호
		EM1Field.setDisable(true); // 이메일주소1
		EM2Field.setDisable(true); // 이메일주소2
		ZCField.setDisable(true); // 우편번호
		Add1Field.setDisable(true); // 주소1
		Add2Field.setDisable(true); // 주소2
		TelField.setDisable(true); // 전화번호

		UpdateCanBtn.setDisable(true); // 수정취소 버튼 비활성화
		UpdateFinBtn.setDisable(true); // 수정완료 버튼 비활성화
		DeleteBtn.setDisable(true); // 회원탈퇴 버튼 비활성화
		ZipcodeBtn.setDisable(true); // 우편번호검색 버튼 비활성화

		pharmGetAll();
	}
	
}


