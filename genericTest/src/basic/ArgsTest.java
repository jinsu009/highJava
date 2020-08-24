package basic;

//20.02.11-01

import java.util.*;

public class ArgsTest {

	// 가변형 인수 ==> 메소드의 매개변수의 갯수가 실행 될 때마다 다를때 사용한다.
	// 가변형 인수를 이용한 메소드를 만들어보자
	// 형식)) 접근제한자 반환값 메서드명( 자료형명... 변수명 )
	public int sumArg(int... data)
	// public int sumArg(float k, String name, int...data)
	// 가변형변수를 여러개 사용할경우 가변형을 마지막에 기술해준다.
	{
		/*
		 * 가변형인수는 메서드 내에서는 배열로 처리된다. 메소드에서 가변형 인수는 한가지만 사용한다. 가변형인수와 다른 매개변수를 같이
		 * 사용할 경우에는 가변형 인수를 제일 뒤에 배치 해야한다.
		 */
		int sum = 0;

		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}

		return sum;
	}

	// 배열을 이용한 메소드 ==>> 매개변수로 정수값들을 받아서 이 값들의 합계를 반환하는 메소드 작성
	public int sumArr(int[] data) {
		int sum = 0;

		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}

		return sum;
	}

	public void te(int a) {

	}

	public static void main(String[] args) {
		ArgsTest test = new ArgsTest();

		// 매개변수에 들어갈 값을 인수라고 한다.

		int[] nums = { 100, 200, 300, 400 };
		System.out.println("1>> " + test.sumArr(nums));
		System.out.println("1>> " + test.sumArr(new int[] { 1, 2, 3, 4, 5 }));

		int k = 100;
		test.te(k);
		test.te(1000);
		// 호출해서 변수값을 넣거나 직접 숫자를 줘서 넘겨줄 수 있다.

		System.out.println();
		System.out.println("2>> " + test.sumArg(100, 200, 300, 400));
		System.out.println("2>> " + test.sumArg(1, 2, 3, 4, 5));

	}

}
