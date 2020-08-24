package basic;

import java.io.*;

//20.02.19 -- 05
public class ObjectIOTest {
	public static void main(String[] args) {
		Member mem1 = new Member("북극곰", 20, "북극");
		Member mem2 = new Member("펭귄", 40, "남극");
		Member mem3 = new Member("바다사자", 50, "북극");
		Member mem4 = new Member("코끼리", 80, "아프리카");

		File file = new File("d:/d_other/memObj.bin");

		try {
			// 객체 파일에 저장하기

			// 출력용스트림객체 생성
			ObjectOutputStream oos = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(file)));
			// 데이터가 많으면 buffer(보조스트림)를 사용하는것이 효율적이다.
			// 보조스트림으로 보조스트림을 묶은것

			// 쓰기 작업
			oos.writeObject(mem1);
			oos.writeObject(mem2);
			oos.writeObject(mem3);
			oos.writeObject(mem4);

			oos.close();
			System.out.println("쓰기 작업 완료!!");

			// --- 저장된 객체 읽어오기
			// 입력용스트림 객체 생성
			ObjectInputStream ois = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(file)));

			// 읽어온데이터는 일반적으로 오브젝트로 반환
			Object obj = null;
			// 읽어온 객체를 저장할 변수

			while ((obj = ois.readObject()) != null) {
				// 읽어온객체를 원래의 객체형으로 형변환 후 사용한다.

				if (obj instanceof Member) {
					Member mem = (Member) obj;
					System.out.println("이름 >> " + mem.getName());
					System.out.println("나이 >> " + mem.getAge());
					System.out.println("주소>> " + mem.getAddr());
					System.out.println("-------------------------");
				}
			}
		} catch (EOFException e) {
			// EOFException 은 readObject() 메서드에서 더이상 읽어올 자료가 없을때 발생하는
			// exception이다.
			System.out.println("읽기작업 완료");
		}

		catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}
	}
}

// 저장용 class 작성 ==> 직렬화가 가능하도록 Serializable 인터페이스를 구현해야한다.

class Member implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1432605378974552445L;
	// 없어도 상관 없지만 있는게 데이터의 안전을 위해서 더 좋다.

	// transient ==> 직렬화가 되지 않을 멤버변수에 지정한다.
	// 직렬화가 되지 않은 멤버변수는 기본값으로 초기화되어 저장된다.
	// (기본값 => 참조변수 : null , 숫자형 변수 : 0, 논리형 변수: false)
	private String name;
	private transient int age;
	private transient String addr;

	// 생성자
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

}
