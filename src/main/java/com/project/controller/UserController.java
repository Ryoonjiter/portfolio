package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.service.UserService;
import com.project.vo.UserVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	

	@GetMapping(value = "/") // 메인화면
	public String index() {
		return "index";
	}
	
	@GetMapping(value="/list")
	public String list(Model model) {
		List<UserVO> list = userService.SelectList();
		model.addAttribute("list", list);
		return "list";
	}
	

	@GetMapping(value = "/update")
	public String update(@ModelAttribute UserVO userVO, Model model) {
		if(userService.passwordCheck(userVO.getUser_idx(), userVO.getPassword())) {
			model.addAttribute("vo", userService.selectByIdx(userVO.getUser_idx()));
			return "update";
		}else {
			return "redirect:/list";
		}
	}
		
	@GetMapping(value = "/insertOk")
	public String insertOkGet() {
		return "redirect:/list";
	}
	
	@PostMapping(value = "/insertOk")
	public String insertOkPost(@ModelAttribute UserVO userVO) {
			userService.insert(userVO);
		return "redirect:/login";
	}
	
	@GetMapping(value = "/updateOk")
	public String updateOkGet() {
		return "redirect:/list";
	}
	
	@PostMapping(value="/updateOk")
	public String updateOkPost(@ModelAttribute UserVO userVO) {
		userService.update(userVO);
		return "redirect:/list";
	}
	
	@GetMapping(value="/deleteOk")
	public String deleteOk(@ModelAttribute UserVO userVO) {
		userService.delete(userVO);
		return "redirect:/list";
	}

	@GetMapping(value = "/view")
	public String view(@RequestParam(required =  true, defaultValue = "0") int user_idx, Model model) {
		model.addAttribute("vo", userService.selectByIdx(user_idx));
		return "view";
	}
	
	@GetMapping(value= "/join")
	public String join() {
		return "join";
	}
	
	@GetMapping("/login")
	public String goLogin() {
		return "login";
	}
	
	@GetMapping("/loginOk") // 주소창에 직접 입력하면 로그인 페이지로 보낸다.
	public String goLogOkGet() {
		return "login";
	}
	
	@PostMapping("/loginOk")
	public String goLogOkPost(@ModelAttribute UserVO userVO,HttpSession session) {
		// 그 아이디에 비번이 일치하는지 판단해서 로그인 처리
		if(userVO!=null) {
			// 서비스에서 id로 DB에서 정보를 받아와서
			UserVO dbVO = userService.selectById(userVO.getId());
			if(dbVO!=null) { // 아이디가 있다면
				// 비번이 같은지 판단해서 로그인 처리!!!
				if(dbVO.getPassword().equals(userVO.getPassword())) {
					session.setAttribute("userVO", dbVO);
					return "redirect:/";
				}
			}
		}
		return "login";
	}
	
	@GetMapping("/logout") // 주소창에 직접 입력하면 로그인 페이지로 보낸다.
	public String goLogOut(HttpSession session) {
		session.removeAttribute("userVO");
		return "redirect:/";
	}
	
	@GetMapping("/idCheck")
	@ResponseBody
	public int idCheck(@RequestParam(required =  true, defaultValue = "") String id) {
		return userService.selectByidCount(id);
	}
}
