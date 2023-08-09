package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.vo.UserVO;

@Mapper
public interface UserDAO {
	
	 void insert(UserVO userVO); //가입
	 
	 List<UserVO> selectAll();  //전체보기
	 
	 UserVO selectByIdx(int user_idx); //자세히보기
	 
	 UserVO selectById(String id); //아이디로 찾기
	 
	 void update(UserVO userVO);  //수정
	 
	 void delete(int user_idx);  //삭제
	 
	 int selectByidCount(String id); //아이디 중복확인
	
	
	
}
