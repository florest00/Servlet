package com.kh.app.member;

import java.sql.Connection;
import java.sql.SQLException;

import com.kh.app.db.JDBCTemplate;

public class MemberService {
	
	private final MemberDao dao = new MemberDao();

	public int join(MemberVo vo) throws Exception {
		//biz
		//conn
		Connection conn = JDBCTemplate.getConn();
		//dao
		int result = dao.join(conn, vo);
		//tx
		if(result == 1) {
			conn.commit();
		} else {
			conn.rollback();
		}
		//close
		conn.close();
		return result;
	}

	public MemberVo login(MemberVo vo) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConn();
		
		//dao
		MemberVo loginMember = dao.login(conn, vo);
		//close
		conn.close();
		return loginMember;
		
	}

}
