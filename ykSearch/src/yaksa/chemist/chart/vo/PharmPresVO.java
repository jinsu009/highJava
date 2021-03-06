package yaksa.chemist.chart.vo;

public class PharmPresVO {

	private  String phpres_code;
	private  String phpres_date;
	private  String phpres_validity;
	private  String phpres_persue;
	private  String pharm_id;
	private  String mem_id;
	
	//=======================================
	// 약국통계에 대한 vo를 따로 생성 또는 필요한 변수 추가 
	private String pharm_name;
	public String getPharm_name() {
		return pharm_name;
	}
	public void setPharm_name(String pharm_name) {
		this.pharm_name = pharm_name;
	}
	private int cnt; 
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	//=======================================
	
	public String getPhpres_code() {
		return phpres_code;
	}
	public void setPhpres_code(String phpres_code) {
		this.phpres_code = phpres_code;
	}
	public String getPhpres_date() {
		return phpres_date;
	}
	public void setPhpres_date(String phpres_date) {
		this.phpres_date = phpres_date;
	}
	public String getPhpres_validity() {
		return phpres_validity;
	}
	public void setPhpres_validity(String phpres_validity) {
		this.phpres_validity = phpres_validity;
	}
	public String getPhpres_persue() {
		return phpres_persue;
	}
	public void setPhpres_persue(String phpres_persue) {
		this.phpres_persue = phpres_persue;
	}
	public String getPharm_id() {
		return pharm_id;
	}
	public void setPharm_id(String pharm_id) {
		this.pharm_id = pharm_id;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
}
