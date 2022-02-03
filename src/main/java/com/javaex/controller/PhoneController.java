package com.javaex.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping(value="/phone")
public class PhoneController {
	
	//필드
	
	//생성자
	
	//메소드 gs
	
	//메소드 일반 
	
	
	@RequestMapping(value="/writeForm", method={RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("phoneController>writeForm()");
		
		
		return "writeForm";
	}
	
	
	@RequestMapping(value="/write", method={RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute PersonVo personVo) { //Dispatcherservlet이 만들었음 
		System.out.println("phoneController>write()");
		System.out.println(personVo);
		
		//저장 
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personInsert(personVo); 
		
		
		//리다이렉트 
		return "redirect:/phone/list";   //write에서 입력하면 주소치고 엔터쳐준 격 
	}
	
	
	
	
	/*
	@RequestMapping(value="/phone/write", method={RequestMethod.GET, RequestMethod.POST})
	public String write(@RequestParam("name") String name, 
						@RequestParam("hp") String hp, 
						@RequestParam("company") String company) {
		System.out.println("phoneController>write()");
		
		System.out.println(name);
		System.out.println(hp);
		System.out.println(company);
		
		//저장 
		PersonVo personVo = new PersonVo(name, hp, company);
		
		PhoneDao phonedao = new PhoneDao();
		phonedao.personInsert(personVo);
		
		//리다이렉트
		
		return "";
	}
	*/
	
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("PhoneController>list()");
		
		//Dao에서 List를 가져온다 
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> personList = phoneDao.getPersonList();
		System.out.println(personList.toString());
		
		//Controller --> DS데이터를 보낸다 : 이걸 model이라고 함 
		model.addAttribute("personList", personList); //규격화된 box같은 model에 담는다. 
		
		//jsp정보를 return한다 (view) 
		return"list";
	}
	
	@RequestMapping(value="/test", method= {RequestMethod.GET, RequestMethod.POST})
	public String test(@RequestParam(value="name") String name,
						@RequestParam(value="age",required =false, defaultValue = "-1" ) int age) {
		
			System.out.println(name);
			System.out.println(age);
		
		return "writeForm";
	}
	
	
	

}
