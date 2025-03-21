package com.ezen.www.repository;

import java.util.List;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	BoardVO getDetail(int bno);

	int update(BoardVO bvo);

	void remove(int bno);

	int getTotal(PagingVO pgvo);

	int selectOneBno();

	void updateReadCount(int bno);

	void updateFileCnt(int bno);

	void countCmt();

}
