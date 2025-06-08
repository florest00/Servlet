package com.kh.app.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDao {

	public int join(Connection conn, MemberVo vo) throws Exception {
		//쿼리준비
		String sql = """
				INSERT INTO MEMBER
				(
				    NO
				    ,USER_ID
				    ,USER_PWD
				    ,USER_NICK
				)
				VALUES
				(
				    SEQ_MEMBER.NEXTVAL
				    , ?
				    , ?
				    , ?
				)
				""";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getUserId());
		pstmt.setString(2, vo.getUserPwd());
		pstmt.setString(3, vo.getUserNick());
		//쿼리실행
		int result = pstmt.executeUpdate();
		//자원반납
		pstmt.close();
		return result;
	}

	public MemberVo login(Connection conn, MemberVo vo) throws Exception {
		String sql = """
				SELECT
				    NO
				    ,USER_ID
				    ,USER_PWD
				    ,USER_NICK
				    ,CREATED_DATE
				    ,DEL_YN
				FROM MEMBER
				WHERE USER_ID = ?
				AND USER_PWD = ?
				AND DEL_YN = 'N'
				""";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getUserId());
		pstmt.setString(2, vo.getUserPwd());
		ResultSet rs = pstmt.executeQuery();
		
		//rs -> vo
		MemberVo loginMember = null;
		if(rs.next()) {
			String no = rs.getString("NO");
			String userId = rs.getString("USER_ID");
			String userPwd = rs.getString("USER_PWD");
			String userNick = rs.getString("USER_NICK");
			String createdDate = rs.getString("CREATED_DATE");
			String delYn = rs.getString("DEL_YN");
			
			loginMember = new MemberVo(no, userId, userPwd, userNick, createdDate, delYn);
		}
		//close
		rs.close();
		pstmt.close();
		
		return loginMember;
	}

}
