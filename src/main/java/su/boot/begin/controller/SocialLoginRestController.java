package su.boot.begin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import su.boot.begin.service.SocialLoginService;
import su.boot.begin.social.vo.KakaoResponseVO;
import su.boot.begin.social.vo.NaverResponseVO;

@RestController
public class SocialLoginRestController {
	
	@Autowired
	private SocialLoginService socialLoginService;
	
	// 네이버 로그인 
	@GetMapping("/api/naver-login-callback")
	public ResponseEntity<NaverResponseVO> naverLoginCallback(@RequestParam("code") String code, @RequestParam("state") String state) {
		
		// 1. 사용자 로그인 등록으로 access token 생성
		String accessToken = socialLoginService.getNaverAccessToken(code).getAccess_token();
		
		// 2. access token 으로 접근해 사용자의 정보 요청
		NaverResponseVO naverResponseVO = socialLoginService.getNaverProfile(accessToken);
		
		// 3. 사용자의 정보를 응답으로 반환
		if(naverResponseVO != null) {
			return new ResponseEntity<>(naverResponseVO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	// 네이버 로그아웃
	@GetMapping("/naver-logout")
	public String naverLogout() {

		return "";
	}
	
	
	
	// 카카오 로그인 
	@GetMapping("/api/kakao-login-callback")
	public ResponseEntity<KakaoResponseVO> kakaoLoginCallback(@RequestParam("code") String code) {
		
		// 1. 사용자 로그인 등록으로 access token 생성
		String accessToken = socialLoginService.getKakaoAccessToken(code).getAccess_token();
		
		// 2. access token 으로 접근해 사용자의 정보 요청
		KakaoResponseVO kakaoResponseVO = socialLoginService.getKakaoProfile(accessToken);
		
		// 3. 사용자의 정보를 응답으로 반환
		if(kakaoResponseVO != null) {
			return new ResponseEntity<>(kakaoResponseVO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	// 카카오 로그아웃
	@GetMapping("/kakao-logout")
	public String kakaoLogout() {
		
		return "";
	}

}
