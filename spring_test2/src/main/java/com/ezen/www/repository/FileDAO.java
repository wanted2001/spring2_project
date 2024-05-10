package com.ezen.www.repository;

import java.util.List;

import com.ezen.www.domain.FileVO;

public interface FileDAO {

	public int insert(FileVO fvo);

	public List<FileVO> getList(int bno);

	public int removeFile(String uuid);

	public List<FileVO> selectListAllFile();
	

}