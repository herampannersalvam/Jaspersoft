package com.Teatre.Member.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.Teatre.Member.entity.Member;
import com.Teatre.Member.repository.MemberRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;

	public List<Member> createMember(List<Member> member) {
		// TODO Auto-generated method stub
		return  memberRepository.saveAll(member);
	}
	public String exportReport(String reportFormat) throws FileNotFoundException, JRException
	{
		String filepath="C:\\Users\\heram\\Documents\\workspace-spring-tool-suite-4-4.20.1.RELEASE\\Member\\src\\main\\resources\\Report\\Member.jrxml";
		String path="C:\\Users\\heram\\Jasper";
	List<Member> member= memberRepository.findAll();

	File file=ResourceUtils.getFile(filepath);
	JasperReport jasperReport=JasperCompileManager.compileReport(file.getAbsolutePath());
	JRBeanCollectionDataSource datasource=new JRBeanCollectionDataSource(member);
	Map<String,Object> map=new HashMap<>();
	map.put("createdBy","Studentdetails");
	JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,map,datasource);
	if(reportFormat.equalsIgnoreCase("html"))
	{
		JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\mem.html");
	}
	if(reportFormat.equalsIgnoreCase("pdf"))
	{
		JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\mem.pdf");
	}
	return "report generated in path:"+path;
	}
	
	
	
	public List<Member> getAll() {
		
		return memberRepository.findAll();
	}
	
	public byte[] generateReport() throws JRException, FileNotFoundException {

		List<Member> address = memberRepository.findAll();
		String filepath = "C:\\Users\\heram\\Documents\\workspace-spring-tool-suite-4-4.20.1.RELEASE\\Member\\src\\\\main\\resources\\Report\\Member.jrxml";

		// load file and compile it
		File file = ResourceUtils.getFile(filepath);
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		// maping jasper report and find all
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(address);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("createdBy", "😎");

		// print jasper report
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, dataSource);
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	/*
	 * public byte[] createkey(Object key) throws JRException,FileNotFoundException
	 * { // TODO Auto-generated method stub List<Object> address =
	 * memberRepository.getkey(key); String filepath =
	 * "C:\\Users\\heram\\Documents\\workspace-spring-tool-suite-4-4.20.1.RELEASE\\Member\\src\\main\\resources\\Report\\Member.jrxml";
	 * 
	 * // load file and compile it File file = ResourceUtils.getFile(filepath);
	 * JasperReport jasperReport =
	 * JasperCompileManager.compileReport(file.getAbsolutePath()); // maping jasper
	 * report and find all JRBeanCollectionDataSource dataSource = new
	 * JRBeanCollectionDataSource(address);
	 * 
	 * Map<String, Object> map = new HashMap<String, Object>(); map.put("createdBy",
	 * "😎");
	 * 
	 * // print jasper report JasperPrint jasperPrint =
	 * JasperFillManager.fillReport(jasperReport, map, dataSource); return
	 * JasperExportManager.exportReportToPdf(jasperPrint); }
	 */public byte[] getBykey(Object key) throws FileNotFoundException, JRException {
			
			List<Member> address = memberRepository.findBykey(key);
			String filepath = "C:\\Users\\heram\\Documents\\workspace-spring-tool-suite-4-4.20.1.RELEASE\\Member\\src\\main\\resources\\Report\\Member.jrxml";

			// load file and compile it
			File file = ResourceUtils.getFile(filepath);
			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
			// maping jasper report and find all
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(address);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("createdBy", "😎");

			// print jasper report
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, dataSource);
			return JasperExportManager.exportReportToPdf(jasperPrint);
		}
	public String getreport( Object key,String format) throws FileNotFoundException, JRException {
		// TODO Auto-generated method stub
		String filepath="C:\\Users\\heram\\Documents\\workspace-spring-tool-suite-4-4.20.1.RELEASE\\Member\\src\\main\\resources\\Report\\Member.jrxml";
		String path="C:\\Users\\heram\\Jasper";
	List<Member> member= memberRepository.getfilter(key);

	File file=ResourceUtils.getFile(filepath);
	JasperReport jasperReport=JasperCompileManager.compileReport(file.getAbsolutePath());
	JRBeanCollectionDataSource datasource=new JRBeanCollectionDataSource(member);
	Map<String,Object> map=new HashMap<>();
	map.put("createdBy","Studentdetails");
	JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,map,datasource);
	if(format.equalsIgnoreCase("html"))
	{
		JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\mem.html");
	}
	if(format.equalsIgnoreCase("pdf"))
	{
		JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\mem.pdf");
	}
	return "report generated in path:"+path;

	}
}
	
	


