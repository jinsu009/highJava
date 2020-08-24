package basic;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//20.02.19 -- 04
public class DataIOStreamTest {
	public static void main(String[] args) {
		try {
			FileOutputStream fout = new FileOutputStream("d:/d_other/test.dat");

			// DataOutputStream은 출력용 데이터를 자료형에 맞게 출력해 준다.
			DataOutputStream dout = new DataOutputStream(fout);

			dout.writeInt(200); // 정수형으로 데이터출력
			// 200을 이진수로 바꿔서 32bit로 저장

			dout.writeFloat(131.4f); // 실수형으로 데이터 출력
			// float는 지수 가수로 저장해서 값의 형태가 조금 다르다.

			dout.writeBoolean(false); // 논리형으로 데이터 출력

			System.out.println("출력 완료");

			dout.close();

			// 출력 끝
			// ---------------------------------
			// 출력한 자료를 읽어오기 !!

			FileInputStream fin = new FileInputStream("d:/d_other/test.dat");

			DataInputStream din = new DataInputStream(fin);

			// DataInputStream 을 이용해서 자룔를 읽어올때는 지정한 순서대로 읽어와야한다.
			// 순서를 맞춰줘야지만 내가 저장한 데이터를 정확하게 읽어올 수 있따.
			System.out.println("정수형 >> " + din.readInt());
			System.out.println("실수형 >> " + din.readFloat());
			System.out.println("논리형 >> " + din.readBoolean());
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
