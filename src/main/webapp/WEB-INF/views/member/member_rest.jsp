<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<script src="./js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="./js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>
	Rest Controller!!! 
	
	<script type="text/javascript">
	
	$(function(){
		function loadData(){
			$.ajax({
				url: '/members/',
				method: 'GET',
				success: function(data){
					console.log("json List<Member> 데이터 확인 : ", data);
				},
				error: function(error){
					console.log("get all members fail")
				}
			})
		};
		
		loadData();
		
		function findByMemberId(){
			$.ajax({
				url: './members/admin',
				method: 'GET',
				success: function(data){
					console.log("1번 상세 조회 데이터: ", data)
				},
				error: function(error){
					console.log("error")
				}
			})
		}
		
		findByMemberId()
		
		function deleteByMemberId(){
			$.ajax({
				url: './members/test2',
				method: 'DELETE',
				success: function(data){
					console.log("회원 삭제 결과: ", data)
				},
				error: function(error){
					console.log("error")
				}
			})
		}
		
		//deleteByMemberId()
		
		function insertMember(){
			$.ajax({
				url: './members',
				method: 'POST',
				contentType: 'application/json',
				data: JSON.stringify({
 				
					member_id: $('#member_id').val(),
					id_check: $('#id_check').val(),
					member_password: $('#member_password').val(),
					member_name: $('#member_name').val(),
					member_email: $('#member_email').val(),
					member_phone: $('#member_phone').val(),
					member_address: $('#member_address').val(),
					member_birth: $('#member_birth').val(),
					member_create: $('#member_create').val(),
					member_update: $('#member_update').val(),
					member_status: $('#member_status').val(),
					member_rate: $('#member_rate').val()
					
					
				}),
				success: function(data){
					console.log("member insert data: " + data);
					alert("member insert")
				},
				error: function(error){
					console.log("member insert error" + error.message);
				}
			})
		}
		
		$('#sign_up_form').on('submit', function(event){
			event.preventDefault();
			insertMember();
		});

	})
	
	</script>
	
	
<main>
	
		<form id="sign_up_form">
		
		  <div class="mb-3 row">
			<label for="member_id" class="col-sm-2 col-form-label">아이디</label>
			<div class="col-sm-10">
			  <input type="text" class="form-control" id="member_id" name="member_id">
			</div>

			<button type="button" class="btn btn-primary" id="db_id_check">중복확인</button>
			<input type="hidden" class="form-control" id="id_check" name="id_check" value="0">

		  </div>	
		
		  <div class="mb-3 row">
			<label for="inputPassword" class="col-sm-2 col-form-label">비밀번호</label>
			<div class="col-sm-10">
			  <input type="password" class="form-control" id="member_password" name="member_password">
			</div>
		  </div>	
		
		  <div class="mb-3 row">
			<label for="member_name" class="col-sm-2 col-form-label">이름</label>
			<div class="col-sm-10">
			  <input type="text" class="form-control" id="member_name" name="member_name">
			</div>
		  </div>
		  
		  <div class="mb-3 row">
			<label for="member_email" class="col-sm-2 col-form-label">이메일</label>
			<div class="col-sm-10">
			  <input type="email" class="form-control" id="member_email" name="member_email">
			</div>
		  </div>
		  
		  <div class="mb-3 row">
			<label for="member_phone" class="col-sm-2 col-form-label">전화 번호</label>
			<div class="col-sm-10">
			  <input type="text" class="form-control" id="member_phone" name="member_phone">
			</div>
		  </div>
		  
		  <div class="mb-3 row">
			<label for="member_address" class="col-sm-2 col-form-label">주소</label>
			<div class="col-sm-10">
			  <input type="text" class="form-control" id="member_address" name="member_address">
			</div>
		  </div>
		  
		  <div class="mb-3 row">
			<label for="member_birth" class="col-sm-2 col-form-label">생년월일</label>
			<div class="col-sm-10">
			  <input type="date" class="form-control" id="member_birth" name="member_birth">
			</div>
		  </div>
		  
		  <input type="hidden" class="form-control" id="member_create" name="member_create">
		  <script type="text/javascript">
		  	document.getElementById('member_create').value = new Date().toISOString().split('T')[0];
		  </script>
		  
		  <input type="hidden" class="form-control" id="member_update" name="member_update">
		  <script type="text/javascript">
		  	document.getElementById('member_update').value = new Date().toISOString().split('T')[0];
		  </script>

		  <input type="hidden" class="form-control" id="member_status" name="member_status" value="active">
		  <input type="hidden" class="form-control" id="member_rate" name="member_rate" value="0">

		  <div class="col-auto">
			<button type="submit" class="btn btn-primary mb-3">회원가입</button>
			<button type="reset" class="btn btn-danger mb-3" onclick="window.history.back()">취소</button>
		  </div> 
		  
		</form>

	</main>

	
	
	
	
	
	
	
	
</body>
</html>