package com.ezen.www.service;

import java.util.List;

import com.ezen.www.domain.AuthVO;
import com.ezen.www.domain.UserVO;

public interface UserService {

	int register(UserVO uvo);

	List<UserVO> getList();

	int delete(String email);

	void updateNick(UserVO uvo);

	void updateWithPwd(UserVO uvo);

	UserVO check(String email);


	

}
