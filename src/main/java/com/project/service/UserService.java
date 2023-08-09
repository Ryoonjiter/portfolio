package com.project.service;

import java.util.List;

import com.project.vo.UserVO;

public interface UserService {

 void insert(UserVO userVO); //저장(회원가입)
	 
	 List<UserVO> SelectList();  //모두얻기(회원목록)
	 
	 UserVO selectByIdx(int user_idx); //1개얻기(자세히보기)
	 
	 UserVO selectById(String id); //아이디로 찾기
	 
	 void update(UserVO userVO);  //수정
	 
	 void delete(UserVO userVO);  //삭제
	 
	 int selectByidCount(String id); //아이디 중복확인
	 
	 //idx와 비번을 받아 비번일치 여부 확인
	 boolean passwordCheck(int user_idx, String password);
	
	
}
