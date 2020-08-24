package basic.controlls;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ComboBoxTest2 extends Application {

	@Override
	public void start(Stage primaryStage) {
		VBox vbox = new VBox(10);
		vbox.setPadding(new Insets(10));
		vbox.setAlignment(Pos.CENTER); 
		
		ComboBox<MyFriend> combo = new ComboBox<MyFriend>();
		TextArea taResult = new TextArea();
		
		ObservableList<MyFriend> dataList = FXCollections.observableArrayList(
				new MyFriend("a001","홍길이","010-9999-9999","대전"),
				new MyFriend("b001","봉길이","010-1111-1111","서울"),
				new MyFriend("c001","동길이","010-2222-2222","부산"),
				new MyFriend("d001","공길이","010-3333-3333","대구"),
				new MyFriend("e001","복길이","010-4444-4444","울산"),
				new MyFriend("f001","몽길이","010-5555-5555","포항")
				);
		
		combo.setItems(dataList); //참조값이 나온다. 
		
		// combobox의 목록이 보여지는 곳의 내용을 변경하기 
		// 화면에 나타는 셀의 내용을 변경하는 부분 
		combo.setCellFactory(new Callback<ListView<MyFriend>, ListCell<MyFriend>>() {
			
			@Override
			public ListCell<MyFriend> call(ListView<MyFriend> param) {
				// TODO Auto-generated method stub
				return new ListCell<MyFriend>() {
					@Override
					protected void updateItem(MyFriend item, boolean empty) {
						// TODO Auto-generated method stub
						super.updateItem(item, empty);
						if(item==null || empty ) {
							setText(null);
						}else {
							setText(item.getId() + " [ " + item.getName() + " ] ");
						}
					}
				};
			}
		});
		
		// 콤보박스에서 항목을 선택하면 선택된 내용이 나타나는 곳을 buttoncell이라고 하는데 
		// 이곳의 데이터도 변경되도록 해야한다. 
		combo.setButtonCell(new ListCell<MyFriend>() {
			@Override
			protected void updateItem(MyFriend item, boolean empty) {
				// TODO Auto-generated method stub
				super.updateItem(item, empty);
				if(item == null || empty ) {
					setText(null);
				}
				else {
					setText(item.getId()+" [ "+item.getName()+" ] ");
				}
			}
		});
		
		// 콤보박스의 데이터를 선택했을 때 이벤트 처리 
		combo.setOnAction(e->{
			// 콤보박스에서 현재 선택된 값 구하기 
			// 방법1 >> getSelectionModel() 을 이용하기 
			//MyFriend selData = combo.getSelectionModel().getSelectedItem();
			
			// 방법 2 >> getValue() 이용하기 
			MyFriend selData = combo.getValue();
			
			taResult.setText("ID > " + selData.getId() +"\n");
			taResult.appendText("이름 > " + selData.getName() +"\n");
			taResult.appendText("전화 > " + selData.getTel()+"\n");
			taResult.appendText("주소 > " + selData.getAddr() +"\n");
			
		});
		
		vbox.getChildren().addAll(combo, taResult);
		
		Scene scene = new Scene(vbox, 400, 300);
		primaryStage.setScene(scene);
		primaryStage.setTitle("combobox 연습 2");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	
	class MyFriend {
		private String id;
		private String name;
		private String tel;
		private String addr;
		
		// 기본생성자 
		public MyFriend() {}

		//using field
		public MyFriend(String id, String name, String tel, String addr) {
			super();
			this.id = id;
			this.name = name;
			this.tel = tel;
			this.addr = addr;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
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
