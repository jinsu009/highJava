package basic;

import javafx.application.Application;
import javafx.stage.Stage;


// Stage객체 ==> window(창)를 의미하는 객체
// Scene객체 ==> 하나의 무대(stage)에는 하나의 장면(scene객체)이 배치된다. 

// JavaFX용 프로그램의 전체적인 실행순서 (lifecycle)
// main()메소드 호출 -> launch()메소드호출 -> 해당 객체 생성 (생성자메소드 호출) 
//		-> init()메소드 호출 -> start()메소드 호출 -> 화면에 창이 나타난다. (사용자가 사용) 
// -> 종료 --> stop()메소드 호출 

// 종료되는 경우 
// 1. 마지막 윈도우(stage)가 닫힐때
// 2. 마지막 윈도우객체의 class()메소드가 호출되었을 때
// 3. Platform.exit() 명령을 호출했을 때 
// 4. System.exit(0); 명령을 호출했을 때  >> 이 방식으로 종료했을 경우에는 stop() 메소드를 호출하지 않는다. 


public class JavaFxLifeCycle extends Application {
	
	//생성자
	public JavaFxLifeCycle() {
		System.out.println(Thread.currentThread().getName()
				+ ">> 생성자 메소드  ( 여기가 실행될때는 어떤 스레드가 작동되는지 알아야하니까 스레드의 이름을 보자. ) ");
	}
	
	@Override
	public void init() throws Exception {
		//javafx용 프로그램의 초기화 작업에 주로 사용된다. 
		System.out.println(Thread.currentThread().getName()+ " >> init()메소드 ");
		
	}
	

	@Override
	public void start(Stage primaryStage) {
		//launch가 임의로 stage객체를 생성해서 넘겨준다. 
		// start()메소드의 매개값으로 넘어온 stage객체가 처음 만들어지는 창이 된다. 
		System.out.println(Thread.currentThread().getName()+" >> start()메소드");
		primaryStage.show(); // 창을 보이게 하는 메소드 
	}
	
	@Override
	public void stop() throws Exception {
		//주로 작업에 사용된 자원들을 반납하는 용도로 사용된다.
		System.out.println(Thread.currentThread().getName() + " >> stop()메소드 ");
	}

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + " >> main()메소드 " );
		launch(args);
	}
}
