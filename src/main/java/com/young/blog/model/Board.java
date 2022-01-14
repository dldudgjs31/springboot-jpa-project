package com.young.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // 엔터티 같은 애노테이션을 가장 아래 배치하는게 좋다.
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length=100)
	private String title;
	
	@Lob//대용량 데이터 사용시 사용하는 애노테이션
	private String content; // 섬머노트 라이브러리 사용할 예정 <html>태그가 섞여서 디자인되기 때문에 사이즈가 크다
	
	@ColumnDefault("0")
	private int count;
	
	@ManyToOne //board To User 라는 뜻 한명의 유저는 여러개의 게시글을 작성할 수 있다는 의미 //ONE TO ONE 은 하나만 작성 가능
	@JoinColumn(name="userId")
	private User user; //DB는 오브젝트를 저장할 수 없지만 자바는 오브젝트를 저장할 수 있음
	
	@CreationTimestamp
	private Timestamp createDate;
}
