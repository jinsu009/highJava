package basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

//20.02.18--03

public class ByteArrayIOTest01 {
	public static void main(String[] args) throws IOException {
		byte[] inSrc = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		byte[] outSrc = null;

		// 입력용스트림 객체 생성
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);

		// 출력용 스트림 객체 생성
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		// 읽어온 자료를 저장할 변수
		int data;

		// 더 이상 읽어올 자료가 없으면 -1을 반환
		while ((data = input.read()) != -1) {
			// 입출력 흐름을 알기위해서
			output.write(data);
			// 읽어온 데이터를 출력하기
		}
		// 스트림에 출력된 값을 배열로 변환해서 반환
		outSrc = output.toByteArray();

		System.out.println("inSrc" + Arrays.toString(inSrc));
		System.out.println("outSrc" + Arrays.toString(outSrc));

		input.close();
		output.close();

	}
}
