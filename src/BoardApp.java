import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class BoardApp {//게시판 작동

	public static void main(String[] args) {

		DBUtil db = new DBUtil();//DButil 객체생성
		Scanner scan = new Scanner(System.in);//사용자로부터 입력받을수 있게 scanner 객체생성
		db.init();//db 에있는 mybatis 초기화 실행


		while (true) {//create read clear exit 입력 받을때까지 반복
			ArrayList<Board> boardList = db.getBoard();//게시글리스트 가져옴
			db.printBoard(boardList);//게시글 리스트 출력
			System.out.println("-------------------------------------------");
			System.out.println("메뉴: Create | Read | Clear | Exit");
			System.out.print("메뉴 선택:");
			String sel = scan.nextLine();//사용자에게 4개의 선택지 받음


			if (sel.equals("create")) {//create 입력시 사용자에게 여러 변수 입력받음
				System.out.println("========= 게시글 등록 =========");
				System.out.print("title : ");
				String btitle = scan.nextLine();
				System.out.print("content : ");
				String bcontent = scan.nextLine();
				System.out.print("writer : ");
				String bwriter = scan.nextLine();
				Date bdate = new Date();


				while (true) {//게시글 등록 취소를 입력할때까지 반복
					System.out.println("계시물을 등록하시겠습니까?");
					System.out.println("1:등록 2:취소");
					System.out.print("입력 :");
					String submit = scan.nextLine();
					if (submit.equals("1")) {//등록시 insertBoard 매서드 실행
						db.insertBoard(btitle, bcontent, bwriter, bdate);
						System.out.println("게시물 등록 완료.");
						System.out.println("============================");
						break;
					} else if (submit.equals("2")) {
						System.out.println("게시물 등록 취소.");
						break;
					} else {
						System.out.println("등록, 취소를 입력하세요");
					}
				}


			} else if (sel.equals("read")) {//read입력시 실행
				System.out.println("1: 조회 2: 수정 3:삭제");
				System.out.print("입력:");
				while (true) {//조회, 수정 ,삭제 입력받을떄까지 반복
					String option = scan.nextLine();
					if (option.equals("1")) {//1 입력시 게시물 조회
						System.out.println("몇번 게시물을 조회하시겠어요?");
						System.out.print("게시물 번호:");
						int bno = Integer.parseInt(scan.nextLine());//bno 입력받음
						ArrayList<Board> specificBoard = db.readBoard(bno);//readBoard메소드를 bno를 기반으로 실행하고 specificboard 가져옴
						db.readSpecificBoard(specificBoard);//readSpecificBoard메소드를 이용해서 board출력
						break;

					} else if (option.equals("2")) {//수정 입력시 실행
						db.printBoard(boardList);//board리스트 보여줌
						System.out.println("몇번 게시물을 수정하시겠어요?");
						int bno = Integer.parseInt(scan.nextLine());//bno 입력받음
						System.out.print("작성자:");
						String writer = scan.nextLine();
						System.out.print("제목:");
						String btitle = scan.nextLine();
						System.out.print("내용:");
						String content = scan.nextLine();
						Date bdate = new Date();
						while (true) {
							System.out.println("1:확인 2:취소");
							String register = scan.nextLine();
							if (register.equals("1")) {
								db.updateBoard(bno, writer, btitle, content, bdate);//updateBoard 메서드 실행
								System.out.println("게시물 수정 완료");
								break;
							} else if (register.equals("2")) {
								System.out.println("게시물 수정 취소");
								break;
							} else {
								System.out.println("확인, 취소를 입력하세요");

							}

						}break;


					} else if (option.equals("3")) {//삭제 입력시 실행
						System.out.println("몇번 게시물을 삭제하시겠어요?");
						System.out.print("게시물 번호:");
						int bno = Integer.parseInt(scan.nextLine());

						while (true) {
							System.out.println("1:삭제 2:취소");
							String delete = scan.nextLine();
							if (delete.equals("1")) {
								db.deleteBoard(bno);//deleteBaord 메서드 실행
								System.out.println("삭제 완료");
								break;
							} else if (delete.equals("2")) {
								System.out.println("삭제 취소");
								break;
							} else {
								System.out.println("삭제 또는 취소를 입력하세요.");
							}
						}
					}
					break;
				}


			} else if (sel.equals("clear")) {
				System.out.println("게시물 전체를 삭제하시겠습니까?");
				while (true) {
					System.out.println("1:확인 2:취소");
					String decide = scan.nextLine();
					if (decide.equals("1")) {
						db.clearBoard(boardList);//clearBaord 메서드 실행해서 전체 boardList 삭제
						System.out.println("전체 게시물 삭제 완료");
						break;
					} else if (decide.equals("2")) {
						System.out.println("전체 게시물 삭제 취소");
						break;
					} else {
						System.out.println("삭제 또는 취소를 입력하세요.");
					}
				}
			}
		}
	}
}

