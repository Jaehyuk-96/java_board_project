import java.util.Date;

public class Board {
	private int bno;//게시글 번호 멤버변수
	private String btitle;//게시글 제목 멤버변수
	private String bcontent;//게시글 내용 멤버변수
	private String bwriter;//게시글 작성자 멤버변수
	private Date bdate;//게시글 작성일 멤버변수

	public Board(int bno, String btitle, String bcontent, String bwriter, Date bdate) {//게시글의 생성자
		super();
		this.bno = bno;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bwriter = bwriter;
		this.bdate = bdate;
	}
	public Board(String btitle, String bcontent, String bwriter, Date bdate) {//게시글의 생성자
		super();
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bwriter = bwriter;
		this.bdate = bdate;
	}

	//getter setter 설정
	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public String getBwriter() {
		return bwriter;
	}

	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}

	public Date getBdate() {
		return bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
}


