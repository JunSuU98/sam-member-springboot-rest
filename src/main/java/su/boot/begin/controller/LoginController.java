package su.boot.begin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import su.boot.begin.entity.Member;
import su.boot.begin.service.LoginService;
import su.boot.begin.service.SocialLoginService;
import su.boot.begin.social.vo.KakaoResponseVO;
import su.boot.begin.social.vo.NaverResponseVO;
import su.boot.begin.vo.MemberVO;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	@Inject
	private final LoginService loginService;
	
	@Autowired
	private SocialLoginService socialLoginService;

	// 로그인 
	@GetMapping("/Login")
	public String login() {

		return "login/login_view";
	}
	
	@PostMapping("/Login")
	public String login(MemberVO memberVO, HttpSession httpSession, Model model) {
		
		Member member = loginService.login(memberVO.getMember_id(), memberVO.getMember_password());
		
		if (member != null) {
			httpSession.setAttribute("member_number", member.getMemberNumber());
			httpSession.setAttribute("member_id", member.getMemberId());
			httpSession.setAttribute("member_name", member.getMemberName());
			httpSession.setAttribute("login_status", "success");
			
		} else { // 로그인 실패
			httpSession.setAttribute("login_status", "fail");
		}
		
		
		return "login/login_check";
	}
	
	// 네이버 로그인 
	@GetMapping("/NaverLogin")
	public String naverLogin(Model model) {
		
		// Naver Access Token 요청 
		model.addAttribute("naver_login_url", socialLoginService.getNaverLogin());
		
		return "login/naver_login";
	}
	
	@GetMapping("/NaverLoginCallback")
	public String naverLoginCallback(@RequestParam("code") String code, @RequestParam("state") String state, Model model, HttpSession httpSession) {
		
		// 1. 사용자 로그인 등록으로 access token 생성
		String accessToken = socialLoginService.getNaverAccessToken(code).getAccess_token();
		
		// 2. access token 으로 접근해 사용자의 정보 요청
		NaverResponseVO naverResponseVO = socialLoginService.getNaverProfile(accessToken);
		
		// 3. 사용자의 정보를 세션에 저장
		httpSession.setAttribute("naver_access_token", accessToken);
		httpSession.setAttribute("naver_refresh_token", socialLoginService.getNaverAccessToken(code).getRefresh_token());
		
		httpSession.setAttribute("member_name", naverResponseVO.getNickname());

		
		
		return "index";
	}
	
	// 카카오 로그인 
	@GetMapping("/KakaoLogin")
	public String kakaoLogin(Model model) {
		
		model.addAttribute("kakao_login_url", socialLoginService.getKakaoLogin());
		
		return "login/kakao_login";
	}
	
	@GetMapping("/KakaoLoginCallback")
	public String kakaoLoginCallback(@RequestParam("code") String code, HttpSession httpSession) {
		
		// 1. 사용자 로그인 등록으로 access token 생성
		String accessToken = socialLoginService.getKakaoAccessToken(code).getAccess_token();
		
		// 2. access token 으로 접근해 사용자의 정보 요청
		KakaoResponseVO kakaoResponseVO = socialLoginService.getKakaoProfile(accessToken);
		
		// 3. 사용자의 정보를 세션에 저장
		httpSession.setAttribute("kakao_access_token", accessToken);
		
		httpSession.setAttribute("member_name", kakaoResponseVO.getKakao_account().getName());
		
		return "index";
	}
	
	// 로그아웃
	@GetMapping("/Logout")
	public String logout(HttpSession httpSession) {
		
		// 1. 네이버 로그아웃 세션값이 있다면 토큰 삭제
		if(httpSession.getAttribute("naver_access_token") != null || httpSession.getAttribute("naver_refresh_token") != null) {
			String accessToken = (String)httpSession.getAttribute("naver_access_token");
			String refreshToken = (String)httpSession.getAttribute("naver_refresh_token");
		
			socialLoginService.naverLogout(accessToken, refreshToken);

		}
			
		
		// 2. 카카오 로그아웃 세션값이 있다면 토큰 삭제
		if(httpSession.getAttribute("kakao_access_token") != null) {
			socialLoginService.kakaoLogout();
		}
		
		// 2. session 삭제
		httpSession.invalidate();
		
		
		return "login/logout";
	}
	
	
	// 아이디 찾기
	@GetMapping("/IdSearch")
	public String idSearch() {

		return "login/id_search_view";
	}
	
	@PostMapping("/IdSearch")
	public String idSearch(MemberVO memberVO, Model model) {
		
		Member member = loginService.searchId(memberVO.getMember_name(), memberVO.getMember_birth(), memberVO.getMember_phone());
		
		model.addAttribute("member", member);

		return "login/id_search";
	}
	
	// 비밀번호 찾기
	@GetMapping("/PasswordSearch")
	public String passwordSearch() {
		return "login/password_search_view";
	}
	
	@PostMapping("/PasswordSearch")
	public String passwordSearch(MemberVO memberVO, Model model) {
		
		Member member = loginService.searchPassword(memberVO.getMember_id(), memberVO.getMember_name(), memberVO.getMember_birth(), memberVO.getMember_phone());
		
		model.addAttribute("member", member);
		
		return "login/password_search";
	}
}
