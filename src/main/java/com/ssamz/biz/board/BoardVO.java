package com.ssamz.biz.board;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


//lombok 어노테이션을 사용하면 자동으로 implements해준다 , @Data 만 해도 일반적으로는 다 처리가된다.
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
//VO(Value Object) 클래스
public class BoardVO {
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
	private int cnt;
	
}
