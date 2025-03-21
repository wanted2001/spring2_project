package com.ezen.www.controller;


import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.handler.PagingHandler;
import com.ezen.www.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comment/*")
@Controller
public class CommentController {

	private final CommentService csv;

	@PostMapping(value = "/post", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> post(@RequestBody CommentVO cvo) {
		int isOk = csv.post(cvo);
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/{bno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PagingHandler> list(@PathVariable("bno") int bno, @PathVariable("page") int page) {
		// pagingVO / pagingHandler
		PagingVO pgvo = new PagingVO(page, 5);
		PagingHandler ph = csv.getList(bno, pgvo);
		return new ResponseEntity<PagingHandler>(ph, HttpStatus.OK);
	}

	/*
	 * @PutMapping(value = "/update", consumes = "application/json", produces =
	 * MediaType.TEXT_PLAIN_VALUE ) public ResponseEntity<String>
	 * update(@RequestBody CommentVO cvo) { int isOk = csv.update(cvo); return isOk
	 * > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new
	 * ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR); }
	 */
	
	// reponsebody 사용방식
	@ResponseBody
	@PutMapping("/update")
	public String update(@RequestBody CommentVO cvo) {
		int isOk = csv.update(cvo);
		return isOk > 0 ? "1" : "0";
	}
	
	@ResponseBody
	@DeleteMapping(value = "/delete/{cno}" )
	public String delete(@PathVariable("cno")int cno){
		log.info("cno >> {}",cno);
		int isOk = csv.delete(cno);
		return isOk > 0 ? "1" : "0";
	}
	
}
