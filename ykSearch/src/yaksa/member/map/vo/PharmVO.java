package yaksa.member.map.vo;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class PharmVO {
	private  String pharm_id;
	private  String pharm_chname;
	private  String pharm_name;
	private  String pharm_pw;
	private  String pharm_regno1;
	private  String pharm_regno2;
	private  String pharm_email1;
	private  String pharm_email2;
	private  String pharm_license_num;
	private  String pharm_corpor_num;
	private int pharm_zip_code;
	private  String pharm_add1;
	private  String pharm_add2;
	private  String pharm_tel;
	private int pharm_starrate;
	private int pharm_totalsale;
	private  String pharm_available; // 탈퇴한 회원인지 아닌지 검사 
	
	private  String pharm_opentime;
	private  String pharm_closetime;
	
	private  String pharm_satopentime;
	private  String pharm_satclosetime;
	
	private  String pharm_sunopentime;
	private  String pharm_sunclosetime;
	
	private Button chatBtn;
	
	public PharmVO() {
		this.chatBtn = new Button("채팅");
	}
	
	public Button getChatBtn() {
		return chatBtn;
	}
	public void setChatBtn(Button chatBtn) {
		this.chatBtn = chatBtn;
	}
	
	
	public String getPharm_id() {
		return pharm_id;
	}
	public void setPharm_id(String pharm_id) {
		this.pharm_id = pharm_id;
	}
	public String getPharm_chname() {
		return pharm_chname;
	}
	public void setPharm_chname(String pharm_chname) {
		this.pharm_chname = pharm_chname;
	}
	public String getPharm_name() {
		return pharm_name;
	}
	public void setPharm_name(String pharm_name) {
		this.pharm_name = pharm_name;
	}
	public String getPharm_pw() {
		return pharm_pw;
	}
	public void setPharm_pw(String pharm_pw) {
		this.pharm_pw = pharm_pw;
	}
	public String getPharm_regno1() {
		return pharm_regno1;
	}
	public void setPharm_regno1(String pharm_regno1) {
		this.pharm_regno1 = pharm_regno1;
	}
	public String getPharm_regno2() {
		return pharm_regno2;
	}
	public void setPharm_regno2(String pharm_regno2) {
		this.pharm_regno2 = pharm_regno2;
	}
	public String getPharm_email1() {
		return pharm_email1;
	}
	public void setPharm_email1(String pharm_email1) {
		this.pharm_email1 = pharm_email1;
	}
	public String getPharm_email2() {
		return pharm_email2;
	}
	public void setPharm_email2(String pharm_email2) {
		this.pharm_email2 = pharm_email2;
	}
	public String getPharm_license_num() {
		return pharm_license_num;
	}
	public void setPharm_license_num(String pharm_license_num) {
		this.pharm_license_num = pharm_license_num;
	}
	public String getPharm_corpor_num() {
		return pharm_corpor_num;
	}
	public void setPharm_corpor_num(String pharm_corpor_num) {
		this.pharm_corpor_num = pharm_corpor_num;
	}
	public int getPharm_zip_code() {
		return pharm_zip_code;
	}
	public void setPharm_zip_code(int pharm_zip_code) {
		this.pharm_zip_code = pharm_zip_code;
	}
	public String getPharm_add1() {
		return pharm_add1;
	}
	public void setPharm_add1(String pharm_add1) {
		this.pharm_add1 = pharm_add1;
	}
	public String getPharm_add2() {
		return pharm_add2;
	}
	public void setPharm_add2(String pharm_add2) {
		this.pharm_add2 = pharm_add2;
	}
	public String getPharm_tel() {
		return pharm_tel;
	}
	public void setPharm_tel(String pharm_tel) {
		this.pharm_tel = pharm_tel;
	}
	public int getPharm_starrate() {
		return pharm_starrate;
	}
	public void setPharm_starrate(int pharm_starrate) {
		this.pharm_starrate = pharm_starrate;
	}
	public int getPharm_totalsale() {
		return pharm_totalsale;
	}
	public void setPharm_totalsale(int pharm_totalsale) {
		this.pharm_totalsale = pharm_totalsale;
	}
	public String getPharm_available() {
		return pharm_available;
	}
	public void setPharm_available(String pharm_available) {
		this.pharm_available = pharm_available;
	}
	public String getPharm_opentime() {
		return pharm_opentime;
	}
	public void setPharm_opentime(String pharm_opentime) {
		this.pharm_opentime = pharm_opentime;
	}
	public String getPharm_closetime() {
		return pharm_closetime;
	}
	public void setPharm_closetime(String pharm_closetime) {
		this.pharm_closetime = pharm_closetime;
	}
	public String getPharm_satopentime() {
		return pharm_satopentime;
	}
	public void setPharm_satopentime(String pharm_satopentime) {
		this.pharm_satopentime = pharm_satopentime;
	}
	public String getPharm_satclosetime() {
		return pharm_satclosetime;
	}
	public void setPharm_satclosetime(String pharm_satclosetime) {
		this.pharm_satclosetime = pharm_satclosetime;
	}
	public String getPharm_sunopentime() {
		return pharm_sunopentime;
	}
	public void setPharm_sunopentime(String pharm_sunopentime) {
		this.pharm_sunopentime = pharm_sunopentime;
	}
	public String getPharm_sunclosetime() {
		return pharm_sunclosetime;
	}
	public void setPharm_sunclosetime(String pharm_sunclosetime) {
		this.pharm_sunclosetime = pharm_sunclosetime;
	}
}
