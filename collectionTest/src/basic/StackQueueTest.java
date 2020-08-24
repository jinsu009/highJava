package basic;
//20.02.05
import java.util.*;

public class StackQueueTest {
	/*
	 *  - Stack : 후입선출(LIFO) >> Stack LinkedList
	 * 
	 *  - Queue : 선입선출(FIFO) >> LinkedList
	 */
	
	public static void main(String[] args) {
		
		/*
		 * stack 의 명령 
		 * 1. 자료 입력 : push(입력할값)
		 * 2. 자료 출력 : pop()  > 자료를 꺼내온 후 꺼내온 자료를 stack에서 삭제한다.
		 * 			   peak() > 자료를 꺼내온다.  
		 * 3. 스택이 비었는지 여부 검사 : isEmpty() > 비었으면 true, 그렇지 않으면 false
		 * (isEmpty 는 array, arraylist에 전부 존재하는 기능)
		 */
		
		LinkedList<String> stack = new LinkedList<>();
		
		System.out.println("비었는지 여부 >> "+ stack.isEmpty());
		
		stack.push("사과");
		//add를 사용해도 좋지만 예상치 못한 오류가 발생할 수 있다. 
		stack.push("포도");
		stack.push("바나나");
		stack.push("용과");
		
		System.out.println("현재의 stack >> " + stack);
		//출력순서 : 용과 , 바나나, 포도, 사과 
		//제일 마지막에 입력한 녀석이 제일 앞의 인덱스에 저장된다. 
		
		System.out.println("비었는지 여부 >> "+ stack.isEmpty());

		
		String data = stack.pop();
		
		System.out.println("꺼내온 자료 >> " + data);
		System.out.println("꺼내온 자료 >> " + stack.pop());
		System.out.println("현재의 stack >> " + stack);		
	
		System.out.println("현재 사용할 수있는 자료 : " + stack.peek());
		System.out.println("현재의 stack >> " + stack);		

		stack.push("키위");
		System.out.println("현재의 stack >> " + stack);	
		System.out.println("꺼내온 자료 >> " + stack.pop());
		System.out.println("현재의 stack >> " + stack);		

		//---------------------------------------------
		
		System.out.println("===============================");
		
		/*
		 * Queue 명령 
		 * 1. 자료 입력 : offer(입력할 자료)
		 * 2. 자료 출력 : poll > 큐에서 자료를 꺼내고 꺼내온 자료는 큐에서 삭제한다.
		 * 			   peek > 큐에서 자료를 꺼내지만 삭제는 하지 않는다. 
		 */
		
		LinkedList<String> queue = new LinkedList<>();
		
		queue.offer("강아지");
		queue.offer("고양이");
		queue.offer("쿼카");
		queue.offer("코알라");
		
		System.out.println("현재의 queue >> " + queue);
		
		String temp = queue.poll();
		System.out.println("꺼내온 자료 >> " + temp);
		System.out.println("꺼내온 자료 >> " + queue.poll());
		System.out.println("현재의 queue >> " + queue);
		
		
		System.out.println("현재 사용할 수 있는 queue 데이터 >> " + queue.peek());
		System.out.println("현재의 queue >> " + queue);
		
		queue.offer("퓨마");
		System.out.println("현재의 queue >> " + queue);
		System.out.println("꺼내온 자료 >> " + queue.poll());
		System.out.println("현재의 queue >> " + queue);

		
		
	}

}
