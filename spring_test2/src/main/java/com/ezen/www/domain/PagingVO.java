package com.ezen.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Setter
@Getter
public class PagingVO {
	private int pageNo;
	private int qty;
	private String type;
	private String keyword;
	
	public PagingVO() {
		this.pageNo = 1;
		this.qty = 10;
	}
	// 댓글용
	public PagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}
	//DB에서 사용될 시작번지 구하기
	//select * from board limit 번지, 갯수 => 0부터 시작
	//1page limit 0,10 => 2page limit 10, 10 => 3page limit 20, 10
	public int getPageStart() {
		return (this.pageNo - 1) * this.qty;
	}
	//type의 복합 타입을 배열로 생성
	//복합 타입의 키워드 일 경우 각자 검색하게 하기 위해 배열로 생성
	public String[] getTypeToArray() {
		return this.type == null ? new String[] {} : this.type.split("");
	}
}
