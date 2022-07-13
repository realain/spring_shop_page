package com.shop.spring_study.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.multipart.MultipartFile;

import com.shop.spring_study.repository.ItemRepository;
import com.shop.spring_study.vo.ItemVo;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Value("${spring.servlet.multipart.location}")
	private String savePath;
	
	@Autowired
	private ItemRepository ir;
	
	@GetMapping("/list.do")
	public String list(Model m){
		m.addAttribute("itemList",ir.findAll());
		return "/item/list";
	}
	@GetMapping("/queryList.do")
	public String queryList(Model model) {
		Iterable<Object[]> Entitis_list=ir.findAllWithCategoryWithMember();
		model.addAttribute("itemList",Entitis_list);
		return "/item/queryList";
	}
	 
	@GetMapping("/entityGraphList.do")
	public String entityGraphList(Model model) {
		model.addAttribute("itemList",ir.findAllByOrderByPostTime());
		return "/item/entityGraphList";
	}
	
	@GetMapping("/list/{page}")
	public String pageableList(@PathVariable int page,
								@RequestParam(defaultValue = "postTime", name="sort") String sort,
								@RequestParam(defaultValue = "desc", name="desc") String desc,
								Model model) {
		int size=5;
		Pageable pageable=null;
		if(desc.equals("desc")) {			
			pageable=PageRequest.of(page-1, size, Sort.by(sort).descending()); //mysql limit(start,size)
		}else if(desc.equals("asc")) {
			pageable=PageRequest.of(page-1, size, Sort.by(sort).ascending());
		}
		Page<ItemVo> itemList=ir.findAll(pageable);
		System.out.println("화면에 출력할 size:"+itemList.getSize());
		System.out.println("총 page 수:"+itemList.getTotalPages());
		System.out.println("현재 페이지의 다음이 있는지?:"+itemList.hasNext());
		System.out.println("현재 페이지의 이전이 있는지?:"+itemList.hasPrevious());
		System.out.println("모든 엘리멘트(row)의 수:"+itemList.getTotalElements());
		System.out.println("현재 페이지 엘리멘트(row)의 수:"+itemList.getNumberOfElements());
		System.out.println("현재 페이지:"+itemList.getNumber());
		
		model.addAttribute("itemList",itemList);
		return "/item/pageableList";
	}
	@GetMapping("/seller/insert.do")
	public String insertForm() {
		return "/item/insert";
	}
	@PostMapping("/seller/insert.do")
	public String insert(ItemVo item,
						MultipartFile mainImgFile,
						MultipartFile datailImgFile,
						HttpSession session) {
		boolean insert=true;
		if(!mainImgFile.isEmpty()) {
			String mainImgExt=mainImgFile.getContentType().split("/")[1];
			String mainImgType=mainImgFile.getContentType().split("/")[0]; 
			String mainFileName="item_main_"+System.currentTimeMillis()+"_"+(int)(Math.random()*10000);
			Path mainImgFilePath=Paths.get(savePath+"/"+mainFileName+"."+mainImgExt);
			if(mainImgType.equals("image")) {
				try {
					mainImgFile.transferTo(mainImgFilePath); //임시 버퍼로 생성된 파일을 실제 파일로 옮겨서 저장
					File mainFile=new File(savePath+"/"+mainFileName+"."+mainImgExt); //저장된 원본파일 불러오기
					BufferedImage mainImg=ImageIO.read(mainFile);
					BufferedImage thumbImg=new BufferedImage(100, 100, BufferedImage.TYPE_3BYTE_BGR);
					thumbImg.getGraphics().drawImage(mainImg, 0, 0, 100, 100, null);
					ImageIO.write(thumbImg, mainImgExt, new File(savePath+"/thumb/"+mainFileName+"."+mainImgExt));
					item.setMainImg(mainFileName+"."+mainImgExt);
					ItemVo saveItem=ir.save(item);
				} catch (IllegalStateException | IOException e) {
					insert=false;
					e.printStackTrace();
				}			
			}
		}else {
			insert=false;
		}
		if(insert) {
			session.setAttribute("msg", "아이템 저장 성공");
			return "redirect:/item/list/1";
		}else {
			session.setAttribute("msg", "아이템 저장 실패");
			return "redirect:/item/seller/insert.do";
		}
	}
}
