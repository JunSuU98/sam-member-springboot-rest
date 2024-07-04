package su.boot.begin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import su.boot.begin.entity.Member;
import su.boot.begin.service.LoginService;
import su.boot.begin.vo.MemberVO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginRestController {
	
	@Inject
	private final LoginService loginService;
	
	// 로그인
	@PostMapping
	public ResponseEntity<?> login(@RequestBody MemberVO memberVO, HttpSession httpSession){
		
		Member member = loginService.login(memberVO.getMember_id(), memberVO.getMember_password());
		
		if(member!= null) {
			httpSession.setAttribute("member_number", member.getMemberNumber());
			httpSession.setAttribute("member_id", member.getMemberId());
			httpSession.setAttribute("member_name", member.getMemberName());
			httpSession.setAttribute("login_status", "success");
			
			return new ResponseEntity<>(HttpStatus.OK);
			
		} else { // 로그인 실패 
			httpSession.setAttribute("login_status", "fail");

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpSession httpSession){
		httpSession.invalidate();
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// 아이디 찾기
	@PostMapping("/id-search")
	public ResponseEntity<?> idSearch(@RequestBody MemberVO memberVO){
		
		Member member = loginService.searchId(memberVO.getMember_name(), memberVO.getMember_birth(), memberVO.getMember_phone());
		
		if(member != null) {
			return new ResponseEntity<>(member, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	// 비밀번호 찾기 
	@PostMapping("/pw-search")
	public ResponseEntity<?> pwSearch(@RequestBody MemberVO memberVO){
		
		Member member = loginService.searchPassword(memberVO.getMember_id(), memberVO.getMember_name(), memberVO.getMember_birth(), memberVO.getMember_phone());
		
		if(member != null) {
			return new ResponseEntity<>(member, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

}
