package ykMap.main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.AlertUtil;
import ykMap.service.SearchServiceImpl;
import ykMap.vo.PharmVO;

public class ContYKDetail {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label LaPharmNM;

	@FXML
	private Label LaAdd1;

	@FXML
	private Label LaAdd2;

	@FXML
	private Label LaTel;

	@FXML
	private Label LaOPTime;

	@FXML
	private Label LaCLTime;

	@FXML
	private Label LaStar;

	@FXML
	private Button CloseBtn;

	@FXML
	private Button ChatBtn;

	@FXML
	private Button PcSendBtn;

	private SearchServiceImpl service;
	private PharmVO pvo;

	@FXML
	void PrecSend(ActionEvent event) {
		// 처방전 전송 버튼 눌렀을 때
		AlertUtil.infoMsg("처방전전송", "임시 처방전 전송창 ");
	}

	@FXML
	void Chat(ActionEvent event) {
		// 채팅버튼 눌렀을 때
		AlertUtil.infoMsg("채팅창", "임시채팅창");
	}

	@FXML
	void Close(ActionEvent event) {
		Stage currentStage = (Stage) CloseBtn.getScene().getWindow();
		currentStage.close();
	}

	void showupdate(PharmVO pvo) {
		this.pvo = pvo;
		LaPharmNM.setText(pvo.getPharm_name());
		LaAdd1.setText(pvo.getPharm_add1());
		LaAdd2.setText(pvo.getPharm_add2());
		LaTel.setText(pvo.getPharm_tel());
		LaOPTime.setText(pvo.getPharm_opentime());
		LaCLTime.setText(pvo.getPharm_closetime());
		LaStar.setText(Integer.toString(pvo.getPharm_starrate()));
	}

	@FXML
	void initialize() {
		service = SearchServiceImpl.getInstance();
	}
}
