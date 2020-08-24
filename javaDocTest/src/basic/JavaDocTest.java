package basic;

/**
 * 20.02.25--04
 * @author PC-19 .. 작성자 
 * 
 * @version 1.0
 * 
 * html태그작성가능
 * 
 * <p> 파일명: JavaDocTest.java <br>
 *    설명: JavaDoc문서 작성 연습을 위한 Interface <br>
 *    
 *    수정 이력 <br>
 *    ------------------------------------<br>
 *    수정 일자 : 2020-02-25<br>
 *    수정인 : 아무개 <br>
 *    수정내용 : 최초생성 <br> 
 *    ------------------------------------<br>
 *    
 * </p>
 *
 */
public interface JavaDocTest {

	/**
	 * 메소드를 먼저 선언후 주석처리를 한다. 자동으로  골뱅이param 생성
	 * 
	 * methodTest ==> 반환 값이 없는 메소드 
	 * 
	 * @param a 첫번째 매개변수 (정수형)
	 * @param b 두번째 매개변수 (정수형)
	 */
	
	public void methodTest(int a, int b);
	
	/**
	 * methodAdd ==> 반환값이 있는 메소드 
	 * @param x  정수형 첫번째 매개변수 
	 * @param y  정수형 두전째 매개변수 
	 * @return  처리된 결과를 정수형으로 반환한다. 
	 */
	public int methodAdd(int x, int y);
	
	/**
	 * methodSub ==> 반환값은 있지만 매개변수는 없는 메소드
	 * @return  정수형으로 반환한다. 
	 */
	public int methodSub();
	
}