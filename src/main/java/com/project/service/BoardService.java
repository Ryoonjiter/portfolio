package com.project.service;

import com.project.vo.BoardVO;
import com.project.vo.PagingVO;

public interface BoardService {
	//목록보기
	PagingVO<BoardVO>selectList(int currentPage, int sizeOfPage, int sizeOfBlock);
	
	//저장
	
	boolean insert(BoardVO boardVO);
	
	//내용보기
	BoardVO selectByIdx(int board_idx, boolean isView_cnt); 
	
	//수정
	boolean update(BoardVO boardVO);
	
	//삭제
	boolean delete(BoardVO boardVO);

}
