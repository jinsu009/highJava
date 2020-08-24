package kr.or.ddit.prod.main;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.prod.vo.LprodVO;
import kr.or.ddit.prod.vo.ProdVO;

public class ControllerProd {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<LprodVO> LprodBox;

    @FXML
    private ComboBox<ProdVO> ProdBox;

    @FXML
    private TableView<ProdVO> TableView;

    @FXML
    private TableColumn<ProdVO, String> Prod_ID;

    @FXML
    private TableColumn<ProdVO, String> Prod_Name;

    @FXML
    private TableColumn<ProdVO, String> Prod_LGU;

    @FXML
    private TableColumn<ProdVO, String> Prod_Buyer;

    @FXML
    private TableColumn<ProdVO, Integer> Prod_Cost;

    @FXML
    private TableColumn<ProdVO, Integer> Prod_Price;

    @FXML
    private TableColumn<ProdVO, Integer> Prod_Sale;

    @FXML
    private TableColumn<ProdVO, String> Prod_OutLine;

    @FXML
    private TableColumn<ProdVO, String> Prod_Detail;

    @FXML
    void lprodser(ActionEvent event) {
    	//lprod 콤보박스의 데이터를선택하면 prod콤보박스의 값이 해당 gu에 맞는 nm값을 호출 시켜 서 prod콤보박스에 셋팅
    	
    	List<ProdVO> pdata = new ArrayList<ProdVO>();
    	//vo에서 가져온 정보를 pdta의 List에 저장시킨다. 
    	pdata = service.searchProdLgu(LprodBox.getValue().getLprod_gu());
    	//pdata는 service의 serchprodlgu라는 메소드를 이용하여 
    	//lprodbox의 lpordgu값을 조건으로 넣어주어 검색된 결과를 담아준다. 
    	ObservableList<ProdVO> proddata =  FXCollections.observableArrayList(pdata);
    	//javafx의 table에 맞게 변환 시켜주기 위해 observable시켜준다. 
    	ProdBox.setItems(proddata);
    	//prod콤보박스에 위에서 가져온 gu의 값을 저장시킨다. 
    	ProdBox.setValue(proddata.get(0));
    	//항상 0번지의 값을 노출시키도록 설정한다. 
     	
    	
    	//왼쪽콤보박스를 선택했지만 오른쪽에 값이 없을 경우 
    	if(proddata.size()==0) {
    		ProdVO temp = new ProdVO();
    		temp.setProd_name("해당상품 없음 ");
    		
    	}
    }

    @FXML
    void prodser(ActionEvent event) {
    	//prod의 콤보박스에서 데이터를 선택하면 해당 데이터에 맞는 vo 값을 가져온다. 
    	
    	List<ProdVO> pdata = new ArrayList<ProdVO>();
    	pdata = service.searchPordId(ProdBox.getValue().getProd_id());
    	// pdata에 service의 searchprodid라는 메소드를 이용하여 
    	// prodbox의 prodid값을 조건으로 넣어주어 검색된 결과를 담아준다. 
    	ObservableList<ProdVO> proddata = FXCollections.observableArrayList(pdata);
    	// javafx의 table에 맞게 변환 
    	 
    	
    	// 해당 컬럼에 vo에 담겨진 정보들을 셋팅 시킨다. 
    	Prod_ID.setCellValueFactory(new PropertyValueFactory<ProdVO, String>("prod_id"));
    	Prod_Name.setCellValueFactory(new PropertyValueFactory<ProdVO, String>("prod_name"));
    	Prod_LGU.setCellValueFactory(new PropertyValueFactory<ProdVO, String>("prod_lgu"));
    	Prod_Buyer.setCellValueFactory(new PropertyValueFactory<ProdVO, String>("prod_buyer"));
    	Prod_Cost.setCellValueFactory(new PropertyValueFactory<ProdVO, Integer>("prod_cost"));
    	Prod_Price.setCellValueFactory(new PropertyValueFactory<ProdVO, Integer>("prod_price"));
    	Prod_Sale.setCellValueFactory(new PropertyValueFactory<ProdVO, Integer>("prod_sale"));
    	Prod_OutLine.setCellValueFactory(new PropertyValueFactory<ProdVO, String>("prod_outline"));
    	Prod_Detail.setCellValueFactory(new PropertyValueFactory<ProdVO, String>("prod_detail"));
    	
    	//테이블에는 observable시켜준 data를 나타나게 해준다. 
    	TableView.setItems(proddata);
    }
    
    private ProdServiceImpl service; //service객체 생성

    @FXML
    void initialize() {
    	service = ProdServiceImpl.getInstance();
    	List<LprodVO> ldata = service.searchLprod(); 
    	// service에서 모든 정보를 search시키는 메소드를 실행시켜 가져오는 값을 ldat에 저장
    	ObservableList<LprodVO> lproddata = FXCollections.observableArrayList(ldata);
    	LprodBox.setItems(lproddata);
    	LprodBox.setValue(LprodBox.getItems().get(0));
    	
    	//lprodbox에 주소값이 아닌 데이터를 셋팅 
    	LprodBox.setCellFactory(new Callback<ListView<LprodVO>, ListCell<LprodVO>>() {
			@Override
			public ListCell<LprodVO> call(ListView<LprodVO> param) {
				// TODO Auto-generated method stub
				return new ListCell<LprodVO>(){
					@Override
					protected void updateItem(LprodVO item, boolean empty) {
						// TODO Auto-generated method stub
						super.updateItem(item, empty);
						if(item==null||empty) {
							setText(null);
						}else {
							setText(item.getLprod_nm());
						}
					}
				};
			}
		});  
    	 	
    	
    	//화살표를 눌렀을때 나타나는 데이터를 셋팅
    	LprodBox.setButtonCell(new ListCell<LprodVO>() {
    		@Override
    		protected void updateItem(LprodVO item, boolean empty) {
    			// TODO Auto-generated method stub
    			super.updateItem(item, empty);
    			if(item == null || empty) {
    				setText(null);
    			}
    			else {
    				setText(item.getLprod_nm());
    			}
    		}
    	});
    	
    	
    	//선생님꺼 보기 오류남 
    	
//    	// 왼쪽의 콤보박스에서 다른 항목을 선택했을때 이벤트 처리 
//    	LprodBox.valueProperty().addListener(new ChangeListener<LprodVO>() {
//    		@Override
//    		public void changed(ObservableValue<? extends LprodVO> observable, LprodVO oldValue, LprodVO newValue) {
//
//    			// 왼쪽의 콤보박스에서 선택한 항목의 lprod_gu값을 구한다. 
//    			String lprodGu = newValue.getLprod_gu();
//    			
//    			// 새롭게 선택한 lprod_gu값을 이용해서 오른쪽 콤보박스에 데이터 셋팅하기 
//
//    			List<ProdVO> prodList = service.searchProdLgu(lprodGu);
//    			
//    			LprodBox = FXCollections.observableArrayList(prodList);
//    			ProdBox.setItems(LprodBox);
//    			
//    		}
//    	});
    	
    	
    	//prodbox에 주소값이 아닌 데이터를 셋팅 
    	List<ProdVO> pdata = new ArrayList<ProdVO>();
    	pdata = service.searchProdLgu(LprodBox.getValue().getLprod_gu());
    	ObservableList<ProdVO> proddata = FXCollections.observableArrayList(pdata);
    	ProdBox.setItems(proddata);
    	ProdBox.setValue(proddata.get(0));
    	ProdBox.setCellFactory(new Callback<ListView<ProdVO>, ListCell<ProdVO>>() {
			@Override
			public ListCell<ProdVO> call(ListView<ProdVO> param) {
				// TODO Auto-generated method stub
				return new ListCell<ProdVO>(){
					@Override
					protected void updateItem(ProdVO item, boolean empty) {
						// TODO Auto-generated method stub
						super.updateItem(item, empty);
						if(item==null||empty) {
							setText(null);
						}else {
							setText(item.getProd_name());
						}
					}
				};
			}
		});  
    	//화살표를 눌렀을때 나타나는 데이터를 셋팅
    	ProdBox.setButtonCell(new ListCell<ProdVO>() {
    		@Override
    		protected void updateItem(ProdVO item, boolean empty) {
    			// TODO Auto-generated method stub
    			super.updateItem(item, empty);
    			if(item == null || empty) {
    				setText(null);
    			}
    			else {
    				setText(item.getProd_name());
    			}
    		}
    	});
    	
    }
}











