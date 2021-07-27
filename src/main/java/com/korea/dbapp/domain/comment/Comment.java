package com.korea.dbapp.domain.comment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.korea.dbapp.domain.post.Post;
import com.korea.dbapp.domain.user.User;

import lombok.Data;


// getter, setter를 만들어준다(데이터 지우고 다시 적어야 함)
@Data
@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 프라이머리 키
	private String text;
	
	// 한개의 댓글은 여러 유저가 쓸 수 있다.
	@JsonIgnoreProperties({"posts"})
	@JoinColumn(name = "user_id")
	@ManyToOne
	private User user;
	
	
	@JsonIgnoreProperties({"user"})
	@JoinColumn(name = "post_id")
	@ManyToOne
	private Post post;
}
