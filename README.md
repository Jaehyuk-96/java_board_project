# Java_Board_Project

## 프로젝트 설명

* 자바와 데이터베이스 사이를 자동 매핑 기능을 지원하는 mybatis를 이용해서 mariaDB에 저장하고 게시판 CRUD를 만들어보는 프로젝트입니다.(참고: 이것이자바다)

## 기술스택

![image](https://github.com/Jaehyuk-96/java_board_project/assets/145963663/b5061fc7-7708-48cb-8d5f-77ece6976f20)<br><br>
![image](https://github.com/Jaehyuk-96/java_board_project/assets/145963663/f0a53ea5-cc17-4a17-a5d9-8aef761f5932)<br><br>
![image](https://github.com/Jaehyuk-96/java_board_project/assets/145963663/a4c9afc5-30fe-44ed-b9af-3a2af679c328)<br><br>

## 주요기능

### Java로 CRUD 기능을 가진 게시판을 구현
   <details>
    <summary>Create보기
    </summary>
    <img src="https://github.com/Jaehyuk-96/java_board_project/assets/145963663/73c5bd66-9c49-4f37-a4dd-c0f42603e676" height="400px"  width="400px"/>
  <br/>   
	   
```java
// 게시글을 새롭게 추가하는 메서드
public void insertBoard(String btitle, String bcontent, String bwriter, Date bdate) {
SqlSession session = sqlSessionFactory.openSession();//새로운 데이터 베이스의 세션을 시작
BoardMapper mapper = session.getMapper(BoardMapper.class);//boardmapper의 메서드를 데이터 베이스와 상호작용하는 mapper 객체에 넣음
Board board = new Board(btitle, bcontent, bwriter, bdate);//새로운 게시글 객체를 데이터를 받아서 생성
mapper.insertBoard(board);//mapper 객체를 사용하여 데이터베이스에 게시글 추가
session.commit();
}
}
```

```Html
<!--게시글 추가 sql 쿼리-->
<!--Board의 매개변수를 받아서 게시글을 추가함-->
<insert id="insertBoard" parameterType="Board" >
INSERT INTO t_board
SET `btitle` = #{btitle},
bcontent = #{bcontent},
bwriter = #{bwriter},
bdate = #{bdate}
</insert >
	}
```
</details>
<br/>
 <details>
    <summary>Read보기</summary>
   <img src="https://github.com/Jaehyuk-96/java_board_project/assets/145963663/df13d800-0fb0-4c52-9218-5404141de2f3" height="400px"  width="400px"/>
  <br/>
<br/>   

```java
//게시글의 bno로 데이터베이스에서 게시글을 가져오는 메소드
	public ArrayList<Board> readBoard(int bno){
		SqlSession session = sqlSessionFactory.openSession();
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		ArrayList<Board> specificBoard = mapper.readBoard(bno);//bno를 기반으로 데이터베이스에서 게시글을 검색
		return specificBoard;//게시글 반환
	}
```

```html
<!--게시글 읽기 sql 쿼리-->
<!--특정 bno의 게시글 반환-->
	<select id="readBoard" resultType="Board">
		Select *
		From t_board
		WHERE bno = #{bno}
	</select>
```
	
</details>
<br/>
 <details>
    <summary>Update보기</summary>
   <img src="https://github.com/Jaehyuk-96/java_board_project/assets/145963663/4bcd8d50-4163-4607-b1cd-893a8b49b28b" height="400px"  width="400px"/>
  <br/>   

```java
//데이터베이스의 게시글을 수정하는 메서드
	public void updateBoard(int bno, String btitle, String bcontent, String bwriter, Date bdate) {
		SqlSession session = sqlSessionFactory.openSession();
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		Board board = new Board(bno, btitle, bcontent, bwriter, bdate);
		mapper.updateBoard(board);

		session.commit();
	}
```

```html
<!--	updateBoard 메소드에 필요한 sql 쿼리-->
<!--	특정 bno를 받아서 그 게시글에 대한 정보만 매개변수를 받아 업데이트함-->
  	<update id="updateBoard" parameterType="Board">
  		UPDATE t_board
  		SET `btitle` = #{btitle},
		bcontent = #{bcontent},
    	bwriter = #{bwriter},
		bdate = #{bdate}
    	WHERE bno = #{bno}
  	</update>
```
	
</details>
<br/>
 <details>
    <summary>Delete보기</summary>
   <img src="https://github.com/Jaehyuk-96/java_board_project/assets/145963663/e094607b-85b0-4225-aca5-1a0be2af64a1" height="400px"  width="400px"/>
<img src="https://github.com/Jaehyuk-96/java_board_project/assets/145963663/4809a216-7222-4596-8451-ce62ebe605f4" height="400px"  width="400px"/>
  <br/>   

```java
//게시글의 bno로 데이터베이스의 게시글을 삭제하는 메서드
	public void deleteBoard(int bno) {
		SqlSession session = sqlSessionFactory.openSession();
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		mapper.deleteBoard(bno);//bno를 기반으로 데이터베이스에서 특정 게시물 삭제

		session.commit();
	}

//데이터 베이스의 게시글을 전체 초기화 하는 메서드
	public void clearBoard(ArrayList<Board> board){
		SqlSession session = sqlSessionFactory.openSession();
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		mapper.clearBoard(board);

		session.commit();

	}
```

```html
<!--	deleteBoard 메소드에 필요한 sql 쿼리-->
<!--	특정 번호 매개변수를 받아서 그번호의 게시글 삭제-->
  	<delete id="deleteBoard" parameterType="int">
  		DELETE FROM t_board
  		WHERE bno = #{bno}
  	</delete>

<!--	clearBoard 메소드에 필요한 sql 쿼리-->
<!--	매개변수 사용없이 전체 게시글 삭제-->
	<delete id="clearBoard" parameterType="Board">
		DELETE FROM t_board
	</delete>
```
	
</details>

### Mybatis를 이용해서 자바와 데이터베이스를 연동
  <details>
    <summary>Mybatis보기</summary>
    ```html
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
  <environments default="development">
    <environment id="development">
      <transactionManager type="JDBC"/>
      <dataSource type="POOLED">
        <property name="driver" value="org.mariadb.jdbc.Driver"/>
        <property name="url" value="jdbc:mariadb://127.0.0.1:3306/Board_prj"/>
        <property name="username" value="root"/>
        <property name="password" value="12345"/>
      </dataSource>
    </environment>
  </environments>
  <mappers>
    <mapper resource="BoardMapper.xml"/>
  </mappers>
</configuration>
```
	    
```html
<!--	clearBoard 메소드에 필요한 sql 쿼리-->
<!--	매개변수 사용없이 전체 게시글 삭제-->
	<delete id="clearBoard" parameterType="Board">
		DELETE FROM t_board
	</delete>
```

```java
public class DBUtil {//데이터 베이스 연결정보와 Mybatis 설정을 관리

	String url = "jdbc:mariadb://127.0.0.1:3306/Board_prj";//연결 url
	String user = "root";//데이터베이스 사용자이름
	String pass = "12345";//데이터베이스 사용자 비밀번호
	SqlSessionFactory sqlSessionFactory;

	public void init() {
		try {//mybatis 설정 초기화
			String resource = "mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		} catch (Exception e) {//초기화 중에 발생한 예외 처리
			System.out.println("MyBatis 설정 파일 가져오는 중 문제 발생!!");
			e.printStackTrace();
		}
	}
```
   

</details>

## 프로젝트후 느낀점

처음에 어떻게 만들어야 하는지 조차 막막했었는데 원래의 코드에 고치고 추가해 보니까 오히려 이해가 되는것 같다. 특히 특정 게시글을 읽어올때 어떻게 코드를 작성하는지 몰랐는데 같은 고민을 하던 친구가 해결해서 도움을 받았다. 또한  메소드나 클래스 멤버변수등이 어떻게 서로 맞물려서 프로젝트를 구성하는지 잘 몰랐는데 만들어 보니까 조금은 알것같다. mybatis를 사용하면 코드가 짧아지는게 좋은거 같아서 잘 배워서 사용해야겠다. 앞으로 다른것들도 더 많이 만들어봐서 나중에 여기에 여러기능들을 추가해보고 싶다.








