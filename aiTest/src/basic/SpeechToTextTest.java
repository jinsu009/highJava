package basic;

import java.io.FileInputStream;
import java.io.IOException;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.speech_to_text.v1.SpeechToText;
import com.ibm.watson.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.speech_to_text.v1.model.SpeechModels;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResult;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResults;
import com.ibm.watson.speech_to_text.v1.websocket.BaseRecognizeCallback;

/*
 *  IBM Watson Speech to text 서비스는 IBM의 음성인식 기능을 응용프로그램에 
 *  추가할 수 있는 API를 제공한다. 
 *  이 서비스는 다양한 언어 및 오디오 형식의 음성을 빠르게 문자로 변환한다. 
 *  모든 응답내용은 UTF-8 인코딩의 JSON형식으로 변환한다.
 */
public class SpeechToTextTest {
	
	// IBM Watson의 SpeechToText 서비스에 등록하여 사용 권한이 있는 
	// key와 url을 제공 받아야한다. 
	// 암호화된 key라고 생각하면 좋다
	String API_KEY = "wJ09suK3sSkYvht8W18lbEflI5iaeCdoq2iCeVvOjhMs";
	String URL = "https://api.us-south.speech-to-text.watson.cloud.ibm.com/instances/7aa2e569-c1e8-4742-a99f-afd42546b97b";
	
	// speechtotext 서비스 객체 변수 선언
	SpeechToText service;
	
	// 서비스에 연결하는 메소드 (제공받은 API_KEY와 URL을 이용해서 서비스에 연결한다.)
	public void setService() {
		IamAuthenticator auth = new IamAuthenticator(API_KEY);
		service = new SpeechToText(auth);
		service.setServiceUrl(URL);
	}
	
	
	//IBM에서 제공하는 모델 List를 출력하는 메소드 
	public void getModelList() {
		SpeechModels speechModels = service.listModels().execute().getResult();
		System.out.println(speechModels);
	}
	
	// 서비스를 처리하는 메소드 
	public void executeService() {
		FileInputStream fis = null; 
		try {
			// 읽어올 audio 파일용 FileInputStream객체 생성 
			fis = new FileInputStream(SpeechToText.class.getResource("speech.mp3").getPath());
			
			// 서비스의 옵션 설정 
			RecognizeOptions options = new RecognizeOptions.Builder()
					.audio(fis) //처리할 audio파일지정
					.contentType("audio/mp3") // audio파일의 종류 지정 
					.model("en-US_BroadbandModel") //왓슨에서 제공하는 언어 모델 지정(영어)
					.interimResults(true) // 중간 결과를 반환할지 여부 결정 
					.maxAlternatives(3) // 반환될 결과 최대 수 >> 보통 하나가 반환된다. 
					.build();
			
			// 콜백 설정 >> 서비스 실행 후 처리 할 내용을 기술한다. 
			BaseRecognizeCallback callback = new BaseRecognizeCallback() {
				
				// 문자 반환시 처리할 내용 기술 
				@Override
				public void onTranscription(SpeechRecognitionResults speechResults) {
					for(SpeechRecognitionResult transcript : speechResults.getResults()) {
						String text = transcript.getAlternatives().get(0).getTranscript();
						System.out.println(text);
					}
				}
				@Override
				public void onDisconnected() {
					System.exit(0);
				}
				
			};
			
			// 설정된 옵션과 콜백을 지정하여 서비스를 실행한다. 
			service.recognizeUsingWebSocket(options, callback);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(fis!=null) try { fis.close(); } catch(IOException e) {}
		}
	}
	
	public static void main(String[] args) {
		SpeechToTextTest test = new SpeechToTextTest();
		test.setService();
//		test.getModelList();
		test.executeService();
	}

}
