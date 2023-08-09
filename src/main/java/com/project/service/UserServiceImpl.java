package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.UserDAO;
import com.project.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public void insert(UserVO userVO) {
		try {
			if (userVO != null) {
				userDAO.insert(userVO);
				log.info("{} 저장", userVO);
			}
		} catch (Exception e) {
			log.info("{} 저장실패", userVO);
			e.printStackTrace();

		}
	}

	@Override
	public List<UserVO> SelectList() {
		List<UserVO> list = null;
		try {
			list = userDAO.selectAll();
			log.info("모두 얻음 : {}", list);
		} catch (Exception e) {
			log.info("모두 얻기실패");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public UserVO selectByIdx(int user_idx) {
		UserVO vo = null;
		try {
			vo = userDAO.selectByIdx(user_idx);
			log.info("1개얻음 : {}", vo);
		} catch (Exception e) {
			log.info("1개 얻기 실패");
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public void update(UserVO userVO) {
		try {
			if (userVO != null) {
				UserVO dbVO = userDAO.selectByIdx(userVO.getUser_idx());

				if (dbVO != null && dbVO.getPassword().equals(userVO.getPassword())) {

					userDAO.update(userVO);
					log.info("{} 수정됨", userVO);
				}
			}
		} catch (Exception e) {
			log.info("{}수정실패", userVO);
			e.printStackTrace();

		}
	}

	@Override
	public void delete(UserVO userVO) {
		try {
			if (userVO != null) {

				UserVO dbVO = userDAO.selectByIdx(userVO.getUser_idx());
				if (dbVO != null && dbVO.getPassword().equals(userVO.getPassword())) {

					userDAO.delete(userVO.getUser_idx());
					log.info("{} 삭제성공", userVO);
				}
			}
		} catch (Exception e) {
			log.info("{}삭제 실패", userVO);
			e.printStackTrace();
		}
	}

	@Override
	public int selectByidCount(String id) {
		int count = 0;
		try {
			count = userDAO.selectByidCount(id);
			log.info("id개수 얻음 : {}", count);
		} catch (Exception e) {
			log.info("id개수 얻기 실패");
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public boolean passwordCheck(int user_idx, String password) {
		boolean isCheck = false;
		try {
			UserVO dbVO = userDAO.selectByIdx(user_idx);
			if (dbVO != null && dbVO.getPassword().equals(password)) {
				isCheck = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isCheck;
	}

	// 아이디로 찾기
	@Override
	public UserVO selectById(String id) {
		UserVO vo = null;
		try {
			vo = userDAO.selectById(id);
			log.info("1개얻음 : {}", vo);
		} catch (Exception e) {
			log.info("1개 얻기 실패");
			e.printStackTrace();
		}
		return vo;
	}

}
