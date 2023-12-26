package com.Teatre.Member.controller;



import java.io.FileNotFoundException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Teatre.Member.entity.Member;
import com.Teatre.Member.service.MemberService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/api/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping("/postmember")
	public List<Member> createMmber(@RequestBody List<Member> member) {
		return memberService.createMember(member);

	}
	
	@GetMapping("get")
	public List<Member> getAll() {
		return memberService.getAll();
	}
	
	@GetMapping("/generatereport/{format}")
	public String generatedReport(@PathVariable String format) throws FileNotFoundException, JRException
	{
		memberService.exportReport(format);
		return "report generated";
	}
	
	@GetMapping(value = "getListReport" )
	public ResponseEntity<byte [] > generateReport() throws FileNotFoundException, JRException{
		
		byte [] reports = memberService.generateReport();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_PDF);
//		header.setContentDispositionFormData("attachment", "report.pdf");
		return new ResponseEntity<>(reports,header,HttpStatus.OK);
		
	}

}
