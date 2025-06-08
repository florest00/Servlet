package com.kh.app.board;

import java.sql.*;
import java.util.*;

public class BoardDao {
	
    // 게시글 등록

	public int insert(Connection conn, BoardVo vo) throws Exception {
	    String sql = "INSERT INTO BOARD(NO, TITLE, CONTENT, WRITER_NO) VALUES(SEQ_BOARD.NEXTVAL, ?, ?, ?)";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, vo.getTitle());
	    pstmt.setString(2, vo.getContent());
	    pstmt.setString(3, vo.getWriterNo());  // String 타입 writerNo 바인딩
	    int result = pstmt.executeUpdate();
	    pstmt.close();
	    return result;
	}

    // 게시글 목록 조회
    public List<BoardVo> selectList(Connection conn, BoardVo searchVo, int startRow, int endRow, String sort) throws Exception {
        List<BoardVo> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ( ");
        sql.append("  SELECT ROWNUM AS RNUM, T.* FROM ( ");
        sql.append("    SELECT B.NO, B.TITLE, B.CONTENT, B.WRITER_NO, B.HIT, B.CREATED_DATE ");
        sql.append("    FROM BOARD B ");
        sql.append("    WHERE 1=1 ");

        // 검색 조건
        if (searchVo != null) {
            if (searchVo.getTitle() != null) {
                sql.append(" AND B.TITLE LIKE '%' || ? || '%' ");
            } else if (searchVo.getContent() != null) {
                sql.append(" AND B.CONTENT LIKE '%' || ? || '%' ");
            } else if (searchVo.getWriterNo() != null) {
                sql.append(" AND B.WRITER_NO = ? ");
            } else if (searchVo.getNo() != null) {
                sql.append(" AND B.NO = ? ");
            }
        }

        // 정렬 조건
        if (sort != null) {
            switch (sort) {
                case "viewDesc":
                    sql.append(" ORDER BY B.HIT DESC ");
                    break;
                case "viewAsc":
                    sql.append(" ORDER BY B.HIT ASC ");
                    break;
                case "titleAsc":
                    sql.append(" ORDER BY B.TITLE ASC ");
                    break;
                case "titleDesc":
                    sql.append(" ORDER BY B.TITLE DESC ");
                    break;
                case "dateAsc":
                    sql.append(" ORDER BY B.CREATED_DATE ASC ");
                    break;
                case "dateDesc":
                    sql.append(" ORDER BY B.CREATED_DATE DESC ");
                    break;
                default:
                    sql.append(" ORDER BY B.NO DESC ");
                    break;
            }
        } else {
            sql.append(" ORDER BY B.NO DESC ");
        }
        sql.append("  ) T ");
        sql.append(") WHERE RNUM BETWEEN ? AND ? ");

        PreparedStatement pstmt = conn.prepareStatement(sql.toString());

        int paramIndex = 1;
        if (searchVo != null) {
            if (searchVo.getTitle() != null) {
                pstmt.setString(paramIndex++, searchVo.getTitle());
            } else if (searchVo.getContent() != null) {
                pstmt.setString(paramIndex++, searchVo.getContent());
            } else if (searchVo.getWriterNo() != null) {
                pstmt.setString(paramIndex++, searchVo.getWriterNo());
            } else if (searchVo.getNo() != null) {
                pstmt.setString(paramIndex++, searchVo.getNo());
            }
        }

        pstmt.setInt(paramIndex++, startRow);
        pstmt.setInt(paramIndex++, endRow);

        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            BoardVo vo = new BoardVo();
            vo.setNo(rs.getString("NO"));  // DB 숫자 -> String
            vo.setTitle(rs.getString("TITLE"));
            vo.setContent(rs.getString("CONTENT"));
            vo.setWriterNo(rs.getString("WRITER_NO"));
            vo.setHit(String.valueOf(rs.getInt("HIT")));  // int → String 변환
            Timestamp ts = rs.getTimestamp("CREATED_DATE");
            vo.setCreatedDate(ts != null ? ts.toString() : null);  // Timestamp → String
            list.add(vo);
        }

        rs.close();
        pstmt.close();
        return list;
    }

    // 전체 게시글 수
    public int getTotalCount(Connection conn, BoardVo searchVo) throws Exception {
        int count = 0;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(*) FROM BOARD B WHERE 1=1 ");

        if (searchVo != null) {
            if (searchVo.getTitle() != null) {
                sql.append(" AND B.TITLE LIKE '%' || ? || '%' ");
            } else if (searchVo.getContent() != null) {
                sql.append(" AND B.CONTENT LIKE '%' || ? || '%' ");
            } else if (searchVo.getWriterNo() != null) {
                sql.append(" AND B.WRITER_NO = ? ");
            } else if (searchVo.getNo() != null) {
                sql.append(" AND B.NO = ? ");
            }
        }

        PreparedStatement pstmt = conn.prepareStatement(sql.toString());

        if (searchVo != null) {
            if (searchVo.getTitle() != null) {
                pstmt.setString(1, searchVo.getTitle());
            } else if (searchVo.getContent() != null) {
                pstmt.setString(1, searchVo.getContent());
            } else if (searchVo.getWriterNo() != null) {
                pstmt.setString(1, searchVo.getWriterNo());
            } else if (searchVo.getNo() != null) {
                pstmt.setString(1, searchVo.getNo());
            }
        }

        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }

        rs.close();
        pstmt.close();
        return count;
    }

    // 상세 조회
    public BoardVo selectOneByNo(Connection conn, String no) throws Exception {
        BoardVo vo = null;

        String sql = "SELECT NO, TITLE, CONTENT, WRITER_NO, HIT, CREATED_DATE FROM BOARD WHERE NO = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, Integer.parseInt(no)); // DB는 숫자니까 파싱해서 세팅

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            vo = new BoardVo();
            vo.setNo(rs.getString("NO"));  // 숫자 → String
            vo.setTitle(rs.getString("TITLE"));
            vo.setContent(rs.getString("CONTENT"));
            vo.setWriterNo(rs.getString("WRITER_NO"));
            vo.setHit(String.valueOf(rs.getInt("HIT"))); // int → String 변환
            Timestamp ts = rs.getTimestamp("CREATED_DATE");
            vo.setCreatedDate(ts != null ? ts.toString() : null);
        }

        rs.close();
        pstmt.close();
        return vo;
    }
}
