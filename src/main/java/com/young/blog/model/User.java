package com.young.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//ORM-> JAVA OBJECT 테이블로 매핑해주는 기술
@Entity // User 클래스가 테이블화 된다. 자동으로 mysql에 테이블 생성
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
//@DynamicInsert // insert할때 null 값은 defualt 들어가게 해줌
public class User {
	@Id //기본키 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 넘버링 전략 : 프로젝트에서 연결된 DB의 넘버링 전략을 따른다는 말
	private int id;// 오라클 시퀀스/ auto_increment
	
	@Column(nullable = false,length=200,unique = true) // null 허용x / 20자 내외 / 중복불가(unique=true)
	private String username;
	
	@Column(length=200) //null 허용 x  / 100자 내외 : 이유 => 비밀번호 암호화 보관할거라서
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//@ColumnDefault("'user'") // 안에 따옴표를 2중으로 해줘야함
	//db에는 roletype이라는게 없기때문에 따로 지정해줘야한다.
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum을 사용하는게 좋다. //admin/user/manager
	
	@Enumerated(EnumType.STRING)
	private LoginType loginType;
	
	@CreationTimestamp // 시간이 자동 입력
	private Timestamp createDate;
}
