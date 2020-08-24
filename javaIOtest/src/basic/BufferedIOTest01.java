package basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//20.02.19 -- 02 

public class BufferedIOTest01 {
	public static void main(String[] args) {
		// 입출력 성능 향상을 위해 Buffer스트림을 이용한다.

		try {
			FileOutputStream fout = new FileOutputStream(
					"d:/d_other/bufferTest.txt");

			// 크기가 5인 버퍼스트림 객체 생성
			// 버퍼의 크기가 생략되면 기본적으로 8192byte 크기로 설정된다.
			BufferedOutputStream bout = new BufferedOutputStream(fout, 5);

			for (char ch = '1'; ch <= '9'; ch++) {
				bout.write(ch);
			}

			// bout.flush();
			// 버퍼에 남아있는 데이터들을 모두 출력한 후 버퍼를 비워준다.

			bout.close();
			// flush 대신에 close를 사용해도 된다.
			// 그 이유는 close안에서 이미 버퍼에 남아있는 데이터를 비워주는 행위를 할수 있기 때문에
			// 버퍼스트림을 close해도 남아 있는 버퍼를 모두 출력해 주지만 안전을 위해
			// 출력작업의 마지막에는 flush()메소드를 호출해주는 것이 좋다.

			System.out.println("작업 끝");

		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
