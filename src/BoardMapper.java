import java.util.ArrayList;

public interface BoardMapper {//인터페이스 구현
	
	public ArrayList<Board> getBoard();//게시글 가져오는 메서드
	public ArrayList<Board> readBoard(int bno);//특정 bno 게시글을 읽는 메서드
	public void insertBoard(Board board);//Board 클래스의 객체를 매개변수로 하는 게시글 추가 메서드
	public void updateBoard(Board board);//Board 클래스의 객체를 매개변수로 하는 게시글 수정 메서드
	public void deleteBoard(int bno);//특정 bno 게시글을 지우는 메서드
	public void clearBoard(ArrayList<Board> board);//Arraylist를 매개변수로 하는 초기화 메서드


}
