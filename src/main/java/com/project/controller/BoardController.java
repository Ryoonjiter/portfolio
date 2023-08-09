package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.service.BoardService;
import com.project.vo.BoardVO;
import com.project.vo.CommVO;
import com.project.vo.PagingVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("board")
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@GetMapping(value="/list") //게시판목록
	public String list(@ModelAttribute CommVO commVO, Model model) {
		
		log.info("넘어온값 : {}",commVO);
		model.addAttribute("cv", commVO);
		PagingVO<BoardVO> pv = boardService.selectList(commVO.getCurrentPage(), commVO.getSizeOfPage(),commVO.getSizeOfBlock());
		model.addAttribute("pv", pv);
		log.info("가져온값 : {}",pv);
		return "board/list";
	}
	
	
	//저장폼
	@GetMapping(value="/insert")
	public String insert(@ModelAttribute CommVO commVO, Model model) {
		log.info("넘어온값 : {}", commVO);
		model.addAttribute("cv", commVO);
		return "board/insert";
	}
	//저장: get방식일 경우 리스트로 강제이동
	@GetMapping(value="/insertOk")
	public String insertOkGet(@ModelAttribute CommVO commVO, Model model) {
		return "redirect:board/list";
	}
	//저장하기:post방식일때 저장하고 1페이지로 이동
	@PostMapping(value="/insertOk")
	public String insertPost(@ModelAttribute CommVO commVO, 
			@ModelAttribute BoardVO boardVO) {
		log.info("넘어온값1:{}", commVO);
	
		boardService.insert(boardVO);
		return "redirect:/board/list?p=1&s=" + commVO.getSizeOfPage()+"&b="+commVO.getSizeOfBlock();
	}
	
	
	@GetMapping(value="/view")
	public String view(@ModelAttribute CommVO commVO, Model model) {
		log.info("넘어온값1 : {}", commVO);
		BoardVO boardVO = boardService.selectByIdx(commVO.getBoard_idx(), true);//조회수 받고
		log.info("DB에서 가져온값 : {}", boardVO);
		
		if(boardVO!=null) {
			boardVO.setView_cnt(boardVO.getView_cnt()+1); //조회수 증가
			model.addAttribute("cv", commVO);
			model.addAttribute("vo", boardVO);
			model.addAttribute("newLine", "\n");
			model.addAttribute("br", "<br/>");
			model.addAttribute("vo", boardVO);
			return "board/view";	
		}else {
			return "redirect:board/list";
		}		
	}
	
	
	@GetMapping(value="/update")
	public String update(@ModelAttribute CommVO commVO, Model model) {
		log.info("넘어온값 : {}", commVO);
		model.addAttribute("cv", commVO);
		BoardVO boardVO = boardService.selectByIdx(commVO.getBoard_idx(), false);
		log.info("DB에서 가져온값 : {}", boardVO);
		
		if(boardVO!=null) {
			model.addAttribute("cv",commVO);
			model.addAttribute("vo",boardVO);
			
			return "board/update";
		}else {
			return "redirect:/board/list";
		}
	}
	//저장: get방식일 경우 리스트로 강제이동
		@GetMapping(value="/updateOk")
		public String updateOkGet(@ModelAttribute CommVO commVO, Model model) {
			return "redirect:/board/list";
		}
		//저장하기:post방식일때 저장하고 1페이지로 이동
		@PostMapping(value="/updateOk")
		public String updatePost(@ModelAttribute CommVO commVO, 
				@ModelAttribute BoardVO boardVO) {
			log.info("넘어온값1:{}", commVO);
		
			boardService.update(boardVO);
			return "redirect:/board/view?board_idx="+commVO.getBoard_idx()+"&p=" + commVO.getCurrentPage() +"&s="+ commVO.getSizeOfPage()+"&b="+commVO.getSizeOfBlock();
		}
	
		//삭제폼
		@GetMapping(value="/delete")
		public String delete(@ModelAttribute CommVO commVO, Model model) {
			log.info("넘어온값 : {}", commVO);
			model.addAttribute("cv", commVO);
			BoardVO boardVO = boardService.selectByIdx(commVO.getBoard_idx(), false);
			log.info("DB에서 가져온값 : {}", boardVO);
			
			if(boardVO!=null) {
				model.addAttribute("cv",commVO);
				model.addAttribute("vo",boardVO);
				
				return "board/delete";
			}else {
				return "redirect:/board/list";
			}
		}
		//저장: get방식일 경우 리스트로 강제이동
			@GetMapping(value="/deleteOk")
			public String deleteOkGet(@ModelAttribute CommVO commVO, Model model) {
				return "redirect:/board/list";
			}
			//저장하기:post방식일때 저장하고 1페이지로 이동
			@PostMapping(value="/deleteOk")
			public String deletePost(@ModelAttribute CommVO commVO, 
					@ModelAttribute BoardVO boardVO) {
				log.info("넘어온값1:{}", commVO);
			
				boardService.delete(boardVO);
				return "redirect:/board/list?p=" + commVO.getCurrentPage() +"&s="+ commVO.getSizeOfPage()+"&b="+commVO.getSizeOfBlock();
			}
	
}
