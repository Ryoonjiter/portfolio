package com.project.service;

import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.BoardDAO;
import com.project.vo.BoardVO;
import com.project.vo.PagingVO;

import lombok.extern.slf4j.Slf4j;

@Service("boardService")
@Slf4j
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;

	// 목록보기
	@Override
	public PagingVO<BoardVO> selectList(int currentPage, int sizeOfPage, int sizeOfBlock) {
		PagingVO<BoardVO> pv = null;

		try {
			// 전체 개수
			int totalCount = boardDAO.selectCount();

			// 페이지 계산
			pv = new PagingVO<>(totalCount, currentPage, sizeOfPage, sizeOfBlock);

			if (pv.getTotalCount() > 0) {
				HashMap<String, Integer> map = new HashMap<>();
				map.put("startNo", pv.getStartNo());
				map.put("pageSize", pv.getSizeOfPage());
				pv.setList(boardDAO.selectList(map));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("서비스 리스트 리턴 : {}", pv);
		return pv;
	}

	// 저장
	@Override
	public boolean insert(BoardVO boardVO) {
		boolean result = false;
		if (boardVO != null) {
			try {
				boardDAO.insert(boardVO);
				result = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 내용보기
	@Override
	public BoardVO selectByIdx(int board_idx, boolean isView_cnt) {
		BoardVO boardVO = null;
		try {
			boardVO = boardDAO.selectByIdx(board_idx);
			if (boardVO != null && isView_cnt) {
				// 해당 글번호의 글이 존재하면서 조회수가 참이면 조회수를 증가
				boardDAO.incrementView_cnt(board_idx);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardVO;
	}

	// 수정
	@Override
	public boolean update(BoardVO boardVO) {
		boolean result = false;
		if (boardVO != null) {
			try {
				boardDAO.update(boardVO);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 삭제
	@Override
	public boolean delete(BoardVO boardVO) {
		boolean result = false;
		if (boardVO != null) {
			try {
				boardDAO.delete(boardVO);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}
}
