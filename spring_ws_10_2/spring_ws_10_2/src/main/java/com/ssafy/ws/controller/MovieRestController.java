package com.ssafy.ws.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.ws.model.dto.Movie;
import com.ssafy.ws.model.dto.User;
import com.ssafy.ws.model.service.MovieService;
import com.ssafy.ws.model.service.UserService;


@RestController
@RequestMapping("/movieapi")
@CrossOrigin("*")
public class MovieRestController {
	
	ResourceLoader resourceLoader;
	
	public final MovieService movieService;
	
	public final UserService userServiece;
	
	public MovieRestController(MovieService movieService, UserService userServiece, ResourceLoader resourceLoader) {
		this.movieService = movieService;
		this.userServiece = userServiece;
		this.resourceLoader=resourceLoader;
	}
	
	// 영화 조회
	@GetMapping("/movie")
	public ResponseEntity<List<Movie>> list(){
		List<Movie> list = movieService.getMovies();
		
		return new ResponseEntity<List<Movie>>(list, HttpStatus.OK);
	}
	
	// 영화 제목으로 조회
	@GetMapping("/movie/{title}")
	public ResponseEntity<List<Movie>> search(@PathVariable("title") String title){
		System.out.println(title);
		List<Movie> movie = movieService.searchMovies(title);
		System.out.println(movie);
		return new ResponseEntity<List<Movie>>(movie, HttpStatus.OK);
	}
	
	// 영화 등록
	@PostMapping("/movie")
	public ResponseEntity<String> write(@ModelAttribute Movie movie, @PathVariable("file") MultipartFile file) throws IllegalStateException, IOException{
		String orgName = file.getOriginalFilename();
		movie.setOrgImg(orgName);
		String newName = LocalDate.now() + "_" + orgName;
		movie.setImg(newName);
		Resource resource = resourceLoader.getResource("classpath:/static/upload");
		file.transferTo(new File(resource.getFile(), newName));
		
		boolean res = movieService.insertMovie(movie);
		
		if (res) 
			return ResponseEntity.status(HttpStatus.OK).body("Movie Inserted");
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed");
	}
	
	// 영화 수정
	@PutMapping("/movie/{id}")
	public ResponseEntity<String> update(@PathVariable("id") int id, @ModelAttribute Movie movie, @PathVariable("file") MultipartFile file) throws IllegalStateException, IOException {
		movie.setId(id);
		if (file != null && file.getSize() > 0) {
			String orgName = file.getOriginalFilename();
			movie.setOrgImg(orgName);
			String newName = LocalDate.now() + "_" + orgName;
			movie.setImg(newName);
			Resource resource = resourceLoader.getResource("classpath:/static/upload");
			file.transferTo(new File(resource.getFile(), newName));
		}
		boolean res = movieService.updateMovie(movie);
		System.out.println(movie);
		if (res)
			return ResponseEntity.status(HttpStatus.OK).body("Movie updated");
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed");
	}
	
	// 영화 삭제
	@DeleteMapping("/movie/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int id){
		boolean res = movieService.removeMovie(id);
		
		if (res)
			return ResponseEntity.status(HttpStatus.OK).body("Movie deleted");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed");
	}
	
	// 유저 조회
	@GetMapping("/user/{id}")
	public ResponseEntity<User> user(@PathVariable("id") String id){
		
		User user = userServiece.selectUser(id);
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
}
