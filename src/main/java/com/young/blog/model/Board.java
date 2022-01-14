package com.young.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	@OneToMany(mappedBy = "board",fetch = FetchType.EAGER) // 한개의 글은 여러개의 댓글을 가질수 있다. // mappedBy가 있으면 연관관계의 주인이 아니라는 의미
	// db에 컬럼 만들지마라는 의미(fk 가 아니라는 의미) // mapped by의 이름은 해당 엔티티의 변수명을 기입
	// one to many는 기본이 lazy 전략이지만 현재 프로젝트에는 댓글도 무조건 노출하기 때문에 eager 전략으로 변경함
	private List<Reply> reply;
	/*
	 * ManyToONE : 기본적인 fetch (가져오는)전략 : FetchType.EAGER => 하나의 정보만 가져오면 되기때문 : 무조건 들고옴
	 * OneToMany : 기본적인 fetch (가져오는)전략 : FetchType.LAZY => 무조건 들고오지 않음
	 * 
	 * */
	@CreationTimestamp
	private Timestamp createDate;
}
