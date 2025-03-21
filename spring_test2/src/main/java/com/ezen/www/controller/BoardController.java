package com.ezen.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.FileVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.handler.FileHandler;
import com.ezen.www.handler.PagingHandler;
import com.ezen.www.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/board/*")
@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {
	private final BoardService bsv;
	private final FileHandler fh;
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/insert")
	public String insert(BoardVO bvo,
			@RequestParam(name="files", required = false)MultipartFile[] files) {
		List<FileVO> flist = null;
		if(files[0].getSize() >0) {
			//파일이 없다면
			flist = fh.uploadFile(files);
		}
		BoardDTO bdto = new BoardDTO(bvo,flist);
		int isOk =bsv.insert(bdto);
		return "redirect:/board/list";
	}
	@GetMapping("/list")
	public String list(Model m, PagingVO pgvo) {
		bsv.countCmt();
		List<BoardVO> list = bsv.getList(pgvo);
		//totalCount
		int totalCount = bsv.getTotal(pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		m.addAttribute("list", list);
		m.addAttribute("ph",ph);
		
		return "/board/list";
	}
	@GetMapping({"/detail", "/modify"})
	public void detail(Model m, @RequestParam("bno")int bno) {
		BoardDTO bdto = bsv.getDetail(bno);
		m.addAttribute("bdto", bdto);
	}
	@PostMapping("/modify")
	public String modify(BoardVO bvo, RedirectAttributes re,
			@RequestParam(name="files", required = false)MultipartFile[] files) {
		List<FileVO> flist = null;
		if(files[0].getSize()>0) {
			flist = fh.uploadFile(files);
		}
		int isOk = bsv.update(new BoardDTO(bvo, flist));
		re.addAttribute("bno", bvo.getBno());
		return "redirect:/board/detail";
	}
	@GetMapping("/remove")
	public String remove(@RequestParam("bno")int bno) {
		bsv.remove(bno);
		return "redirect:/board/list";
	}
	
	@DeleteMapping("/file/{uuid}")
	@ResponseBody
	public String removefile(@PathVariable("uuid")String uuid) {
		int isOk = bsv.removeFile(uuid);
		return isOk > 0 ? "1":"0";
	}
}
