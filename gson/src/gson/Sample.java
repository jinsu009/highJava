package gson;

import java.io.IOException;
import java.util.*;

import com.google.gson.Gson;


public class Sample{
	
	private String name = new String();
	private int age;
	private ArrayList<String> soulfood = new ArrayList<String>();
	private Map<String, String> etc = new HashMap<String, String>();
	
	
	public void testFromJson() throws IOException{
		StringBuffer sb = readJsonFile("Sample.json");
		
		Gson gson = new Gson();
		Sample sample = gson.fromJson(sb.toString(), Sample.class);
		
		assertEquals("홍길동", sample.getName());
		assertEquals("비빔밥", sample.soulfood().get(2));
		assertEquals("abc@naver.com",sample.getEtc().get("email"));
		}
	
	private StringBuffer readJsonFile(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public void testToJson(){
		Map<String, Object> testData = new HashMap<String, Object>();
		String name = "홍길동";
		Map<String, String> contact = new HashMap<String, String>();
		contact.put("mobile","010-1111-1411");
		contact.put("address","대전시");
		
		testData.put("name", name);
		testData.put("contact", contact);
		
		Gson gson = new Gson();
		String json = gson.toJson(testData);
		
	}
	
}
