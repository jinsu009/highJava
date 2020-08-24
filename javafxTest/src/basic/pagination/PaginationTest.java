package basic.pagination;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

public class PaginationTest extends Application {

	private int rowsPerPage = 10; //한 화면에 보여줄 데이터 갯수 저장
	private int totalCount; // 전체 레코드 수가 저장될 변수 선언
	private int pageCount; //페이지수 
	
	private TableView<MemberVO> table; //tableview 객체 변수 선언
	private Pagination pagination; //pagination 객체 변수 선언
	private List<MemberVO> data;
	
	private IMemberService service; // 서비스 객체 변수 선언
	
	@Override
	public void start(Stage primaryStage) {
		//생성자 
		service = MemberServiceImpl.getInstance(); 
		
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(10));
		
		table = new TableView<MemberVO>();
		
		TableColumn<MemberVO, String> idCol = new TableColumn<MemberVO, String>("id");
		idCol.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
		idCol.setPrefWidth(150);
		
		TableColumn<MemberVO, String> nameCol = new TableColumn<MemberVO, String>("name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
		nameCol.setPrefWidth(170);
		
		TableColumn<MemberVO, String> telCol = new TableColumn<MemberVO, String>("tel");
		telCol.setCellValueFactory(new PropertyValueFactory<>("mem_tel"));
		telCol.setPrefWidth(200);
		
		TableColumn<MemberVO, String> addrCol = new TableColumn<MemberVO, String>("address");
		addrCol.setCellValueFactory(new PropertyValueFactory<>("mem_addr"));
		addrCol.setPrefWidth(250);
		
		table.getColumns().addAll(idCol,nameCol, telCol, addrCol);
		
		//전체 레코드 수를 구한다. 
		totalCount = service.getAllMemberCount();
		
		//전체페이지수를 구한다. 
		//pageCount = ((totalCount % rowsPerPage) == 0)? totalCount/rowsPerPage : (totalCount/rowsPerPage)+1;
		pageCount = (int) Math.ceil((double)totalCount/rowsPerPage); //나머지가 있으면 무조건 올림 
		
		pagination = new Pagination();
		// pagination객체에 전체 페이지수와 처음에 보여줄 index값을 설정한다.
		// index값은 1페이지일 경우 0으로 지정한다. 
		pagination.setPageCount(pageCount); //전체 페이지 수 지정 
		pagination.setCurrentPageIndex(0); //처음 선택될 페이지의 index값 지정 
		
		//첫페이지의 데이터를 가져온다. 
		changeTableView(0);
		
		// pagination의 페이지 번호를 변경했을때 이벤트 처리 
		pagination.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
			
		@Override
		public void changed(ObservableValue<? extends Number> observable,
				Number oldValue, Number newValue) {
			changeTableView(newValue.intValue());
			
		}
		});
		
		
		
		root.setCenter(table);
		root.setBottom(pagination);
		Scene scene = new Scene(root,800,600);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	// pagination의 현재 선택된 index값을 매개 값으로 받아서 해당 페이지에 맞는 데이터를 가져와 tableview에 넣어주는 메소드
	// index는 0 => 1page, 1=>2page,....
	public void changeTableView(int index) {
		int start = index * rowsPerPage; // 시작번호 계산 
		int end = Math.min(start + rowsPerPage, totalCount); // 끝번호 계산
		//math.min : 둘중에 더 작은 값을 저장 
		
		Map<String, Integer> pageMap = new HashMap<String, Integer>();
		pageMap.put("start", start);
		pageMap.put("end",end);
		data = service.getPagingMemberList(pageMap); // 시작번호 부터 끝번호까지 자료가져오기 
		
		// tableview에 가져온데이터 셋팅하기 
		table.setItems(FXCollections.observableArrayList(data));
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
