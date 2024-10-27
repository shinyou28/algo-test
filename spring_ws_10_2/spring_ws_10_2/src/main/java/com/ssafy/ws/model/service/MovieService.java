package com.ssafy.ws.model.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.ws.model.dto.Movie;

public interface MovieService {
	
	// 영화 등록
	boolean insertMovie(Movie movie);
	
	// 영화 수정
	boolean updateMovie(Movie movie);
	
	// 영화 삭제
	boolean removeMovie(int id);
	
	// 전체 영화 조회
	List<Movie> getMovies();
	
	// 영화 제목으로 검색 
	List<Movie> searchMovies(String title);
	
	// id로 영화 검색
	Movie getMovie(int id);
}
