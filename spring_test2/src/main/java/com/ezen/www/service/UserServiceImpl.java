package com.ezen.www.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.www.domain.AuthVO;
import com.ezen.www.domain.UserVO;
import com.ezen.www.repository.UserDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService{
	
	private final UserDAO udao;
	
	@Transactional
	@Override
	public int register(UserVO uvo) {
		//권한 추가
		int isOk = udao.register(uvo);
		
		return udao.insertAuthInit(uvo.getEmail());
	}

	@Override
	public List<UserVO> getList() {
		List<UserVO> userList = udao.getList();
		for(UserVO uvo : userList) {
			uvo.setAuthList(udao.selectAuths(uvo.getEmail()));
		}
		log.info("jimyung >> {}",userList);
		return  userList;
	}
	
	@Transactional
	@Override
	public int delete(String email) {
		int isOk = udao.delete(email);
		isOk = udao.deleteauth(email);
		return isOk;
	}

	@Override
	public void updateNick(UserVO uvo) {
		udao.updateNick(uvo);
		
	}

	@Override
	public void updateWithPwd(UserVO uvo) {
		udao.updateWithPwd(uvo);
		
	}

	@Override
	public UserVO check(String email) {
		UserVO isOk = udao.check(email);
		return isOk;
	}

	/*
	 * @Override public void userModify(UserVO uvo) { UserVO principal =
	 * (UserVO)SecurityContextHolder.getContext().getAuthentication().getPrincipal()
	 * .; if(uvo.getPwd() == null || uvo.getPwd().length() == 0) { } }
	 */
}