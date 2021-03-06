let index = {
	init: function(){
		$("#btn-save").on("click",()=>{ //this를 바인딩하기 위해서 ()=> 를 사용, function을 사용하면 this가 window객체를 가리키게됨
			this.save(); 
		});
		$("#btn-update").on("click",()=>{ 
			this.update(); 
		});

	},
	
	save: function(){
		// 컨텍스트패스 받아오기.
		function getContextPath() {
			var hostIndex = location.href.indexOf( location.host ) + location.host.length;
			return location.href.substring( hostIndex, location.href.indexOf("/", hostIndex + 1) );
		  };

		//alert('user의 save 함수 호출');
		let data={
			username: $('#username').val(),
			password: $('#password').val(),
			email: $('#email').val()
		};
		
		//ajax 통신을 이용하여 3개의 데이터를 json으로 변경하여 insert 요청을 할 예정
		//ajax 호출시 default가 비동기 호출
		//ajax가 통신을 성공하고 서버가 json을 응답하면 자동으로 json을 javascript 오브젝트로 변경해준다.
		$.ajax({
			//회원가입 수행 요청
			type:"POST", //요청 method 타입
			url: getContextPath()+"/auth/joinProc", //요청보낼 url
			data: JSON.stringify(data), //보내는 data => json으로 넘기기
			contentType:"application/json;charset=utf-8", //마임타입 지정
			dataType:"json" // 응답 데이터 타입 지정 ( default는 버퍼로 전송되기때문에 string을 전송됨), 만일 json이 넘어오면 자바스크립트 object로 변경해준다.
		}).done(function(resp){
			if(resp.status==500){
				alert("회원가입에 실패하였습니다.");
			}else{
				alert("회원가입 완료");
				location.href=getContextPath(); // 성공시 반환 위치
			}
		}).fail(function(error){
			alert(JSON.stringify(error));
		
		}); 
		
	},
	update: function(){
		// 컨텍스트패스 받아오기.
		function getContextPath() {
			var hostIndex = location.href.indexOf( location.host ) + location.host.length;
			return location.href.substring( hostIndex, location.href.indexOf("/", hostIndex + 1) );
		  };

		//alert('user의 save 함수 호출');
		let data={
			id: $('#id').val(),
			username: $("#username").val(),
			password: $('#password').val(),
			email: $('#email').val()
		};
		alert(data.id,data.email);
		//ajax 통신을 이용하여 3개의 데이터를 json으로 변경하여 insert 요청을 할 예정
		//ajax 호출시 default가 비동기 호출
		//ajax가 통신을 성공하고 서버가 json을 응답하면 자동으로 json을 javascript 오브젝트로 변경해준다.
		$.ajax({
			//회원가입 수행 요청
			type:"PUT", //요청 method 타입
			url: getContextPath()+"/user", //요청보낼 url
			data: JSON.stringify(data), //보내는 data => json으로 넘기기
			contentType:"application/json;charset=utf-8", //마임타입 지정
			dataType:"json" // 응답 데이터 타입 지정 ( default는 버퍼로 전송되기때문에 string을 전송됨), 만일 json이 넘어오면 자바스크립트 object로 변경해준다.
		}).done(function(resp){
			alert("회원 수정 완료");
			location.href=getContextPath(); // 성공시 반환 위치
		}).fail(function(error){
			alert(JSON.stringify(error));
		
		}); 
		
	}	
	
}
index.init();