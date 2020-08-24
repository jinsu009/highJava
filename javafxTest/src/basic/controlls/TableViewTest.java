package basic.controlls;

import java.util.ArrayList;
import java.util.List;

import basic.util.AlertUtil;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TableViewTest extends Application {

	@Override
	public void start(Stage primaryStage) {
//		ObservableList<Member> data = FXCollections.observableArrayList(
//				new Member("홍길동", "gildong", 33, "010-1111-1111","대전"),
//				new Member("홍길서", "gilseo", 11, "010-2222-2222","서울"),
//				new Member("홍길남", "gilnam", 22, "010-1444-3331","광주"),
//				new Member("홍길북", "gilbuk", 55, "010-5555-6666","울산")
//				);
		
		List<Member> memList = new ArrayList<Member>();
		memList.add(new Member("홍길동", "gildong", 33, "010-1111-1111","대전"));
		memList.add(new Member("홍길서", "gilseo", 11, "010-2222-2222","서울"));
		memList.add(new Member("홍길남", "gilnam", 22, "010-1444-3331","광주"));
		memList.add(new Member("홍길북", "gilbuk", 55, "010-5555-6666","울산"));
		
		ObservableList<Member> data = FXCollections.observableArrayList(memList);
		//javafx에 변화된 memList정보를 내보낸다
		
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(10));
		// tableview 객체 생성 및 데이터 셋팅
		// 방법1.
		//TableView<Member> table = new TableView<Member>();
		//table.setItems(data);
		
		// 방법2.
		TableView<Member> table = new TableView<Member>(data);
		
		//------------------
		// tableview에 포함될 tablecolumn 객체를 생성하고 출력할 데이터를 매핑한다. 
		// (출력할 데이터 객체의 멤버변수와 컬럼을 연결한다.)
		// 들어갈 데이터의 자료형을 넣어준다.
		
		//nameCol 컬럼을 생성 정보는 member에서 string타입으로 가져오고 이름은 "name"
		TableColumn<Member, String> nameCol = new TableColumn<Member, String>("name");
		
		TableColumn<Member, String> korNameCol = new TableColumn<Member, String>("한글");
		korNameCol.setCellValueFactory(new PropertyValueFactory<Member, String>("korName"));
		// korName >> member객체 중 이 컬럼에 출력할  '멤버 변수명', 테이블에 담을 membervo객체의 변수명을 가져온다.
		
		TableColumn<Member, String> engNameCol = new TableColumn<Member, String>("영어");
		engNameCol.setCellValueFactory(new PropertyValueFactory<Member, String>("engName"));
		
		// '이름'컬럼에 '한글' 컬럼과 '영문'컬럼을 추가한다. 
		nameCol.getColumns().addAll(korNameCol, engNameCol);
		
		TableColumn<Member, Integer> ageCol = new TableColumn<Member, Integer>("나이");
		ageCol.setCellValueFactory(new PropertyValueFactory<Member, Integer>("age"));
		
		TableColumn<Member, String> telCol = new TableColumn<Member, String>("전화번호");
		telCol.setCellValueFactory(new PropertyValueFactory<Member, String>("tel"));
		
		TableColumn<Member, String> addrCol = new TableColumn<Member, String>("주소");
		addrCol.setCellValueFactory(new PropertyValueFactory<Member, String>("addr"));
		
		// 만들어진 각 컬럼객체들을 tableview에 추가한다.
		table.getColumns().addAll(nameCol,ageCol,telCol,addrCol);
		
		GridPane grid = new GridPane();
		//gridpane container : 그리드로 컨트롤을 배치하고 셀의 크기가 유동적인 컨테이너 셀 병합이 가능하다.
		grid.setPadding(new Insets(10));
		grid.setHgap(5);
		grid.setVgap(5);
		
		Text txt1 = new Text("한글이름");
		Text txt2 = new Text("영어이름");
		Text txt3 = new Text("나이");
		Text txt4 = new Text("전화번호");
		Text txt5 = new Text("주소");
		
		TextField tfKorName = new TextField();
		TextField tfEngName = new TextField();
		TextField tfAge = new TextField();
		TextField tfTel = new TextField();
		TextField tfAddr = new TextField();
		
		grid.add(txt1, 1, 1);
		grid.add(txt2, 2, 1);
		grid.add(txt3, 3, 1);
		grid.add(txt4, 4, 1);
		grid.add(txt5, 5, 1);
		
		grid.add(tfKorName, 1, 2);
		grid.add(tfEngName, 2, 2);
		grid.add(tfAge, 3, 2);
		grid.add(tfTel, 4, 2);
		grid.add(tfAddr, 5, 2);
		
		VBox vbox = new VBox(5);
		vbox.setPadding(new Insets(5));
		Button btnAdd = new Button("추가");
		btnAdd.setOnAction(e->{
			String korname = tfKorName.getText();
			String engname = tfEngName.getText();
			String age = tfAge.getText();
			String tel = tfTel.getText();
			String addr = tfAddr.getText();
			if(korname.isEmpty() || engname.isEmpty() || age.isEmpty() || tel.isEmpty() || addr.isEmpty()) {
				AlertUtil.errorMsg("입력오류", "빈 항목이 있습니다.");
				return;
			}
			Member mem = new Member(korname,engname,Integer.parseInt(age),tel,addr);
			data.add(mem);
			AlertUtil.infoMsg("작업결과", korname + " 씨의 정보를 추가했습니다.");
		});
		
		Button btnEdit = new Button("수정");
		btnEdit.setOnAction(e->{
			if(table.getSelectionModel().isEmpty()) {
				AlertUtil.errorMsg("직업오류", "수정할 데이터를 선택하세요");
				return;
			}
			
			//데이터 수정 작업 >> 입력부분이랑 똑같다. 
			String korname = tfKorName.getText();
			String engname = tfEngName.getText();
			String age = tfAge.getText();
			String tel = tfTel.getText();
			String addr = tfAddr.getText();
			if(korname.isEmpty() || engname.isEmpty() || age.isEmpty() || tel.isEmpty() || addr.isEmpty()) {
				AlertUtil.errorMsg("입력오류", "빈 항목이 있습니다.");
				return;
			}
			Member mem = new Member(korname,engname,Integer.parseInt(age),tel,addr);
			// tableview에서 현재 선택된 데이터의 index값 구하기 >> getSelectedIndex()메소드 이용
			int index = table.getSelectionModel().getSelectedIndex();
			data.set(index, mem);
			
			AlertUtil.infoMsg("작업결과", korname + " 씨의 정보를 수정했습니다.");
		
		});
		
		Button btnDel = new Button("삭제");
		// 컨트롤 객체를 활성화 및 비활성화 상태로 만들기 
		// >> setDisable : true >> 비활성화, false >> 활성화
		// >> 컨트롤 객체가 비활성화되면 설정된 이벤트가 처리 되지 않는다. 
		btnDel.setDisable(false);
		
		btnDel.setOnAction(e->{
			if(table.getSelectionModel().isEmpty()) {
				AlertUtil.errorMsg("작업오류", "삭제할 데이터를 선택하세요");
				return;
			}
			int index = table.getSelectionModel().getSelectedIndex();
			// 삭제될 member의 korname값 구하기 
			String korname = table.getSelectionModel().getSelectedItem().getKorName();
			
			data.remove(index);
			//data.clear();
			
			AlertUtil.infoMsg("작업결과", korname + " 씨의 정보가 삭제되었습니다.");
		});
		
		Button btnTest = new Button("기타");
		btnTest.setOnAction(e->{
			// textfield나 textarea, passwordfield등의 값을 편집 할 수 있게 하거나 편집 할 수 없게 하기 
			
			// setEditable : true >> 편집 가능 , false >> 편집불가
			//tfKorName.setEditable(false); // 편집 불가 상태로 만든다.
			
			// 컨트롤 객체를 활성화 및 비활성화 상태로 만들기 
			// >> setDisable : true >> 비활성화, false >> 활성화
			// >> 컨트롤 객체가 비활성화되면 설정된 이벤트가 처리 되지 않는다. 
			//btnAdd.setDisable(true);
			//btnDel.setDisable(false);
			
			tfEngName.setPromptText("영어이름입력 : ");
			
		});
		
		
		vbox.getChildren().addAll(btnAdd, btnEdit, btnDel, btnTest);
		
		//마우스로 tableview를 클릭했을때 하단의 textfield에해당값을 출력할수 있게 끔
		table.setOnMouseClicked(e->{
			if(table.getSelectionModel().isEmpty()) {
				AlertUtil.warnMsg("경고!", "테이블뷰에서 데이터가 있는 곳을 선택하세요");
				return;
			}
			// tableview에서 선택한 데이터 객체 구하기 
			Member mem = table.getSelectionModel().getSelectedItem();
			tfKorName.setText(mem.getKorName());
			tfEngName.setText(mem.getEngName());
			tfAge.setText(String.valueOf(mem.getAge()));
			tfTel.setText(mem.getTel());
			tfAddr.setText(mem.getAddr());
			
		});
		
		root.setCenter(table);
		root.setBottom(grid);
		root.setRight(vbox);
		
		Scene scene = new Scene(root, 500, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("tableview 연습");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	// 테이블에 저장할 정보를 담아두는 vo객체 필요
	public class Member{
		private String korName;
		private String engName;
		private int age;
		private String tel;
		private String addr;
		
		public Member() {}

		public Member(String korName, String engName, int age, String tel, String addr) {
			super();
			this.korName = korName;
			this.engName = engName;
			this.age = age;
			this.tel = tel;
			this.addr = addr;
		}

		public String getKorName() {
			return korName;
		}

		public void setKorName(String korName) {
			this.korName = korName;
		}

		public String getEngName() {
			return engName;
		}

		public void setEngName(String engName) {
			this.engName = engName;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getTel() {
			return tel;
		}

		public void setTel(String tel) {
			this.tel = tel;
		}

		public String getAddr() {
			return addr;
		}

		public void setAddr(String addr) {
			this.addr = addr;
		}
		
	}
	
}



















