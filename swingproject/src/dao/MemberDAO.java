package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import dbconn.DBConn;
import vo.MemberVO;
import vo.TextListVO;

public class MemberDAO {
	public MemberDAO() {
		
	}
	
	//회원가입
	public void insertMember(MemberVO vo) {
		Connection              conn  = null;
		PreparedStatement pstmt = null;
		conn  = DBConn.getInstance();
		String sql ="INSERT INTO MEMBER (USERID, PASSWD, USERNAME, JOB, GENDER, INTRO) VALUES (?, ? ,?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getPasswd());
			pstmt.setString(3, vo.getUsername());
			pstmt.setString(4, vo.getJob());
			pstmt.setString(5, vo.getGender());
			pstmt.setString(6, vo.getIntro());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt != null)pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	public boolean updateMember(MemberVO vo) {
		boolean ok = false;
		Connection              conn  = null;
		PreparedStatement pstmt = null;
		conn  = DBConn.getInstance();
		String sql = "UPDATE MEMBER SET passwd = ?, username = ? , job=?, gender = ?, intro=? WHERE userid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPasswd());
			pstmt.setString(2, vo.getUsername());
			pstmt.setString(3, vo.getJob());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getIntro());
			pstmt.setString(6, vo.getUserid());
			
			int rs = pstmt.executeUpdate();
			if(rs == 1) {
				ok = true;
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt != null)pstmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return ok;
	}

	public int findByIdAndPwd(String userId, String password) {
		int result = -1;
		//DB연결
		Connection              conn  = null;
		PreparedStatement pstmt = null;
		ResultSet                 rs       = null;
		conn = DBConn.getInstance();
		
		//SQL작성
		String sql = "SELECT * FROM MEMBER WHERE USERID = ? AND PASSWD = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 1; //로그인 성공
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			
		}
		
		return result;
	}

	public Vector getMainTextList() {
		Vector                      v        = new Vector();
		//DB연결
		Connection              conn  = null;
		PreparedStatement pstmt = null;
		ResultSet                 rs       = null;
		
		conn = DBConn.getInstance();
		String sql = "SELECT TEXTINDEX, USERID, TITLE, TO_CHAR(REGTEXT, 'YYYY-MM-DD') as REGTEXT FROM TEXTLIST ORDER BY TEXTINDEX";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Vector  textList = new Vector();
				textList.add(rs.getString("TEXTINDEX"));
				textList.add(rs.getString("USERID"));
				textList.add(rs.getString("TITLE"));
				textList.add(rs.getString("REGTEXT"));
				
				v.add(textList);			
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
				try {
					if(rs != null)rs.close();
					if(pstmt != null)pstmt.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		}
		return v;
	}

	public boolean TextwriteInsert(String userId, String title, String contents) {
		boolean ok = false;
		Connection              conn  = null;
		PreparedStatement pstmt = null;
		conn  = DBConn.getInstance();
		
		String sql = "INSERT INTO textlist (TEXTINDEX, TITLE, CONTENTS, USERID) VALUES ((SELECT NVL(MAX(TEXTINDEX), 0)+1 FROM textlist), ?, ? ,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setString(3, userId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt == 1) {
				ok = true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
				try {
					if(pstmt != null)pstmt.close();
				} catch (SQLException e) {}
		}
		
		return ok;
	}

	public Vector getMemberInfo() {
		Vector v = new Vector();
		Connection              conn  = null;
		PreparedStatement pstmt = null;
		ResultSet                 rs       = null;
		
		conn = DBConn.getInstance();
		
		String sql = "SELECT userid, username, job, gender, TO_CHAR(indate, 'YYYY-MM-DD HH24:MM:SS') AS indate FROM member ORDER BY INDATE ASC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Vector vo = new Vector();
				vo.add(rs.getString("userid"));
				vo.add(rs.getString("username"));
				vo.add(rs.getString("job"));
				vo.add(rs.getString("gender"));
				vo.add(rs.getString("indate"));
				
				v.add(vo);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return v;
	}

	public Vector getSearchList(String SearchCont) {
		Vector v = new Vector();
		Connection              conn  = null;
		PreparedStatement pstmt = null;
		ResultSet                 rs       = null;
		conn = DBConn.getInstance();
		System.out.println("DAO : " + SearchCont );
		String sql = "SELECT TEXTINDEX, USERID, TITLE, TO_CHAR(REGTEXT, 'YYYY-MM-DD') as REGTEXT FROM TEXTLIST WHERE TITLE LIKE '%"+SearchCont+"%' ORDER BY TEXTINDEX";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Vector  textList = new Vector();
				textList.add(rs.getString("TEXTINDEX"));
				textList.add(rs.getString("USERID"));
				textList.add(rs.getString("TITLE"));
				textList.add(rs.getString("REGTEXT"));
				
				v.add(textList);			
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return v;
	}

	public TextListVO getDetailContents(String number, String id) {
		Connection              conn = null;
		PreparedStatement pstmt = null;
		ResultSet                  rs = null; 
		TextListVO vo = null;
		conn = DBConn.getInstance();
		
		String sql = " SELECT contents FROM textlist WHERE textindex = "+number+" AND userid = '"+id+"'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String contents = rs.getString("contents");
				vo = new TextListVO(contents);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return vo;
	}

	public boolean detailTextDelete(String number) {
		boolean ok = false;
		Connection              conn  = null;
		PreparedStatement pstmt = null;
		
		conn = DBConn.getInstance();
		String sql = "DELETE FROM textlist WHERE textindex = '"+number+"'";
		try {
			pstmt = conn.prepareStatement(sql);
			int cnt = pstmt.executeUpdate();
			
			if(cnt == 1) {
				ok = true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return ok;
	}


	
	
	
	
}

