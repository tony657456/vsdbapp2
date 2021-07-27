package com.korea.dbapp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // 빈 생성자
@AllArgsConstructor // 생성자
@Data
// 데이터베이스의 테이블이랑 같은 오브젝트를 모델,
// 행위가 없는 오브젝트를 빈이라고 부른다.
// 모델은 원래 데이터베이스랑만 데이터를 교환하는것
// 통신한 데이터는 dto로 받는게 정상이다.
public class CMRespDto<T> {
	private int code;
	private String msg;
	private T data;

}
