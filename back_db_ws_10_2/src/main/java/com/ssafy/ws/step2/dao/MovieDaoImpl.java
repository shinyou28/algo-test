package com.ssafy.ws.step2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.ws.step2.util.DBUtil;
import com.ssafy.ws.step2.dto.Movie;

public class MovieDaoImpl implements MovieDao{
	/**
	 * DB 사용을 위해 DBUtil 객체를 가져온다.
	 */
	private final DBUtil util = DBUtil.getInstance();

	private static MovieDao instance = new MovieDaoImpl(); 

	private MovieDaoImpl() {}

	public static MovieDao getInstance() {
		return instance;
	}
	
	// DB에 저장된 모든 영화 목록 조회
	@Override
	public List<Movie> selectAllMovies() throws SQLException {
		// 사용할 sql문
		String sql = "SELECT * FROM Movies";
		// 모든 Movie 정보를 저장할 list
		ArrayList<Movie> result = new ArrayList<Movie>();
		// DB 연결 객체
		Connection conn = null;
		// sql문 실행 객체
		PreparedStatement pstmt = null;
		// sql문 실행 결과 집합
		ResultSet rs = null;

		try {
			// 연결 객체 얻기
			conn = util.getConnection();
			// 위에서 사용할 sql문을 통해 pstmt 구문 객체 생성
			pstmt = conn.prepareStatement(sql);
			// sql 실행(처리할 결과가 있는 경우)
			rs = pstmt.executeQuery(sql);
			// sql 실행 결과 처리
			while (rs.next()) {
				Movie Movie = new Movie();
				Movie.setTitle(rs.getString("Title"));
				Movie.setDirector(rs.getString("Director"));
				Movie.setGenre(rs.getString("Genre"));
				Movie.setRunningTime(rs.getInt("RunningTime"));
				result.add(Movie);
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		} finally {
			// 자원 해제
			util.close(conn, pstmt, rs);;
		}
		
		// list 반환
		return result;
	}

	// DB에 Movie 정보 삽입
	public boolean insertMovie(Movie Movie) {
		String sql = "INSERT INTO Movies (Title, Director, Genre, RunningTime) VALUES (?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Movie.getTitle());
			pstmt.setString(2, Movie.getDirector());
			pstmt.setString(3, Movie.getGenre());
			pstmt.setInt(4, Movie.getRunningTime());
			result = pstmt.executeUpdate() > 0 ? true : false;
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			util.close(conn, pstmt);
		}
		return result;
	}

		// DB에 등록된 Movie 정보 갯수
	public int MovieCount() {
		String sql = "SELECT COUNT(*) FROM Movies";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);

			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			util.close(conn, pstmt, rs);
		}
		
		return result;
	}

}