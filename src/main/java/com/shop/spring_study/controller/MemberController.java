package com.shop.spring_study.controller;


import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.shop.spring_study.repository.MemberRepository;
import com.shop.spring_study.vo.MemberVo;

@Controller
@RequestMapping("/mem")
public class MemberController {
	@Autowired 
	MemberRepository mr;
	@GetMapping("/admin/list.do")
	public String list(Model model) {
		Iterable<MemberVo> memList=mr.findAllByOrderBySignupTimeDesc();
		model.addAttribute("memList",memList);
		System.out.println(memList);
		model.addAttribute("test",10);
		return "/mem/list";
	}
	@GetMapping(value="/login")
	public String login() {
		return "/mem/login";
	}
	@PostMapping("/login")
	public String login(
			String id,
			String pw,
			HttpSession session) {
		System.out.println(id);
		System.out.println(pw);
		MemberVo memVo=mr.findByIdAndPw(id, pw);
		System.out.println(memVo);
		if(memVo!=null) {
			session.setAttribute("memVo", memVo);
			return "redirect:/";
		}else {
			return "redirect:/mem/login";
		}
		
	}
	@GetMapping("/signup")
	public ModelAndView signup(ModelAndView model) {
		model.setViewName("mem/signup");
		return model;
	}
	@PostMapping("/signup")
	public String signup(Model model,MemberVo memVo,HttpSession session) {
		boolean insert=false;
		try {
			Optional<MemberVo> memOption=mr.findById(memVo.getId());
			if(memOption.isEmpty()) {				
				MemberVo insertMem=mr.save(memVo);
				System.out.println(insertMem);			
				if(insertMem!=null) {
					insert=true;
				}
			}else {
				session.setAttribute("msg", "존재하는 아이디 입니다.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("msg", "Email이나 phone이 존재합니다.");
		}
		if(insert) {
			return "redirect:/";
		}else {
			return "redirect:/mem/signup";			
		}
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); //session만료
		return "redirect:/";
	}
	@GetMapping("/ajax/findid/{id}")
	public @ResponseBody Optional<MemberVo> findId(@PathVariable String id) {
		return mr.findById(id);
	}
	@GetMapping("/ajax/findphone/{phone}")
	public @ResponseBody Optional<MemberVo> findPhone(@PathVariable String phone){
		return mr.findByPhone(phone);
	}
	@GetMapping("/ajax/findemail/{email}")
	public @ResponseBody Optional<MemberVo> findEmail(@PathVariable String email){
		//return mr.selectByEmail(email);
		return mr.selectJPQLByEmail(email);
	}
	@GetMapping("/list/{page}")
	public String pageableList(@PathVariable int page,
								@RequestParam(defaultValue = "id") String sort,
								@RequestParam(defaultValue = "desc") String desc,
								Model m) {
		int size=5;
		Pageable pageable=null;
		if(desc.equals("desc")) {
			pageable=PageRequest.of(page-1, size, Sort.by(sort).descending()); 
		}else if(desc.equals("asc")) {
			pageable=PageRequest.of(page-1, size, Sort.by(sort).ascending()); 
		}
		Page<MemberVo> memList=mr.findAll(pageable);
		m.addAttribute("memList",memList);
		return "/mem/pageableList";
	}
}
