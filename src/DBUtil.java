import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

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

	//데이터 베이스에서 게시글 목록을 가져오는 메서드
	public ArrayList<Board> getBoard() {
		SqlSession session = sqlSessionFactory.openSession();//새로운 데이터 베이스의 세션을 시작
		BoardMapper mapper = session.getMapper(BoardMapper.class);//boardmapper의 메서드를 데이터 베이스와 상호작용하는 mapper 객체에 넣음
		ArrayList<Board> BoardList = mapper.getBoard();//mapper 객체를 사용하여 게시글 목록을 가져옴

		return BoardList;
	}

	//게시글을 새롭게 추가하는 메서드
	public void insertBoard(String btitle, String bcontent, String bwriter, Date bdate) {
		SqlSession session = sqlSessionFactory.openSession();//새로운 데이터 베이스의 세션을 시작
		BoardMapper mapper = session.getMapper(BoardMapper.class);//boardmapper의 메서드를 데이터 베이스와 상호작용하는 mapper 객체에 넣음
		Board board = new Board(btitle, bcontent, bwriter, bdate);//새로운 게시글 객체를 데이터를 받아서 생성
		mapper.insertBoard(board);//mapper 객체를 사용하여 데이터베이스에 게시글 추가

		session.commit();
	}

	//게시글의 bno로 데이터베이스에서 게시글을 가져오는 메소드
	public ArrayList<Board> readBoard(int bno){
		SqlSession session = sqlSessionFactory.openSession();
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		ArrayList<Board> specificBoard = mapper.readBoard(bno);//bno를 기반으로 데이터베이스에서 게시글을 검색
		return specificBoard;//게시글 반환

	}

	//데이터베이스의 게시글을 수정하는 메서드
	public void updateBoard(int bno, String btitle, String bcontent, String bwriter, Date bdate) {
		SqlSession session = sqlSessionFactory.openSession();
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		Board board = new Board(bno, btitle, bcontent, bwriter, bdate);
		mapper.updateBoard(board);

		session.commit();
	}

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


	//게시글을 cli에 출력하는 메서드
	public void printBoard(ArrayList<Board> boardList) {

		System.out.println("======================== 게시글 목록 ========================");
		for (int i = 0; i < boardList.size(); i++) {
			System.out.printf("%-6s%-12s%-16s%-40s\n", "no", "writer", "title", "date");
			System.out.printf("%-6s%-12s%-16s%-40s\n", boardList.get(i).getBno(), boardList.get(i).getBwriter(), boardList.get(i).getBtitle(), boardList.get(i).getBdate());
		}
	}

	//특정 게시글을 cli에 출력하는 메서드
	public void readSpecificBoard(ArrayList<Board> specificBoard){
		System.out.println("-------------------------------------------------------");
		System.out.println("번호:" +specificBoard.get(0).getBno());
		System.out.println("작성일:" +specificBoard.get(0).getBdate());
		System.out.println("작성자:" +specificBoard.get(0).getBwriter());
		System.out.println("제목:" +specificBoard.get(0).getBtitle());
		System.out.println("내용:" +specificBoard.get(0).getBcontent());
		System.out.println("-------------------------------------------------------");
	}

}