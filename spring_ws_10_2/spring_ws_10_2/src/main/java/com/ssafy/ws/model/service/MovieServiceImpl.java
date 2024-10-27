package com.ssafy.ws.model.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.ws.model.dao.MovieDao;
import com.ssafy.ws.model.dto.Movie;

// bean으로 등록 할수 있도록 Service를 선언한다.
@Service
public class MovieServiceImpl implements MovieService {
	
	ResourceLoader resourceLoader;
	
	private MovieDao movieDao;

	public MovieServiceImpl(MovieDao movieDao) {
		this.movieDao = movieDao;
	}

	@Override
	public boolean insertMovie(Movie movie){
		
		int res = movieDao.insert(movie);
		
		return res == 1;
	}

	@Override
	public boolean updateMovie(Movie movie) {
		int res = movieDao.update(movie);
		return res ==  1;
	}

	@Override
	public boolean removeMovie(int id) {
		int res = movieDao.remove(id);
		return res == 1;
	}

	@Override
	public List<Movie> getMovies() {
		return movieDao.selectAll();
	}

	@Override
	public List<Movie> searchMovies(String title) {
		return movieDao.searchByTitle(title);
	}

	@Override
	public Movie getMovie(int id) {
		return movieDao.selectOne(id);
	}

	

}
