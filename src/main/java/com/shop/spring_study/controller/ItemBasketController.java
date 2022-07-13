package com.shop.spring_study.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.spring_study.repository.ItemBasketRepository;
import com.shop.spring_study.vo.ItemBasketVo;
import com.shop.spring_study.vo.MemberVo;


@Controller
@RequestMapping("/itemBasket")
public class ItemBasketController {
	
	@Autowired 
	ItemBasketRepository ibr;
	
	@GetMapping("/list.do")
	public String list(Model model, HttpSession session) {
		MemberVo memVo=(MemberVo) session.getAttribute("memVo");
		model.addAttribute("basketList", ibr.findByMemberId(memVo.getId()));
		return "/itemBasket/list";
	}
	@PostMapping("/insert.do")
	public String insert(int itemNum,int count,HttpSession session) {
		MemberVo memVo=(MemberVo) session.getAttribute("memVo");
		ItemBasketVo basketVo=ibr.findByItemNumAndMemberId(itemNum, memVo.getId());
		if(basketVo==null) {
			basketVo=new ItemBasketVo();
			basketVo.setMemberId(memVo.getId());
			basketVo.setItemNum(itemNum);
			basketVo.setCount(count);
			ibr.save(basketVo); //기존의 값이 없으면 insert
		}else {
			basketVo.setCount(basketVo.getCount()+count);
			ibr.save(basketVo); //기존의 값이 있고 basket_num이 정의되어있으면 Update
		}
		//이미 저장된 primary key가 들어오면 update를 한다
		return "redirect:/itemBasket/list.do";
	}
}
