package com.project.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.vo.BoardVO;

@Mapper
public interface BoardDAO {
	int selectCount()throws SQLException;
	BoardVO selectByIdx(int board_idx) throws SQLException; 
	List<BoardVO> selectList(HashMap<String, Integer>map) throws SQLException;	
		void insert(BoardVO boardVO) throws SQLException;
		void update(BoardVO boardVO) throws SQLException;
		void delete(BoardVO boardVO) throws SQLException;
		void incrementView_cnt(int board_idx)throws SQLException;
	
}
