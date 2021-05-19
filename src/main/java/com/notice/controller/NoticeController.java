package com.notice.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.notice.exception.CommonException;
import com.notice.model.vo.FileVo;
import com.notice.model.vo.NoticeReqVo;
import com.notice.model.vo.NoticeResVo;
import com.notice.service.FileService;
import com.notice.service.NoticeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private FileService fileService;

	@GetMapping("/error")
	public String error() {
		return "notice/error.html";
	}
	
	@GetMapping("/")
	public String list(Model model , @RequestParam(value="page", defaultValue = "1") Integer pageNum) throws CommonException {
		
		List<NoticeResVo> NoticeResList;
		try {
			NoticeResList = noticeService.getNoticeList(pageNum);
			Integer[] pageList = noticeService.getPageList(pageNum);
			model.addAttribute("pageList", pageList);
			model.addAttribute("noticeList", NoticeResList);
			return "notice/list.html";
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			model.addAttribute("error", e);
			e.printStackTrace();
		}
		return "notice/error.html";
	}

	@GetMapping("/notice")
	public String post() {
		return "notice/post.html";
	}

	@GetMapping("/notice/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        try {
			model.addAttribute("notice", noticeService.getDetail(id));
			return "notice/detail.html";
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			model.addAttribute("error", e);
			e.printStackTrace();
		}
		return "notice/error.html";
        
    }
	
    @GetMapping("/notice/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
    	try {
			model.addAttribute("notice", noticeService.getDetail(id));
			return "notice/edit.html";
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			model.addAttribute("error", e);
			e.printStackTrace();
		}
    	return "notice/error.html";
        
    }
    
    @PutMapping("/notice/edit/{id}")
    public String update(@RequestParam("file") MultipartFile files, NoticeReqVo noticeReqVo, Model model) throws CommonException {
    	log.debug("["+noticeReqVo.toString()+"]");
    	try {
			try {
				noticeReqVo.setFileId(fileService.saveFile(files));
			} catch (IllegalStateException | NoSuchAlgorithmException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			noticeService.updateNotice(noticeReqVo);
	        return "redirect:/";
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			
			model.addAttribute("error", e);
			e.printStackTrace();
		} 
    	return "notice/error.html";
    }
    
	@PostMapping("/notice")
	public String post(@RequestParam("file") MultipartFile files, NoticeReqVo noticeReqVo, Model model) throws CommonException {				
		try {
			try {
				noticeReqVo.setFileId(fileService.saveFile(files));
			} catch (IllegalStateException | NoSuchAlgorithmException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			noticeService.saveNotice(noticeReqVo);		
			return "redirect:/";
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			
			model.addAttribute("error", e);
			e.printStackTrace();
		} 
    	return "notice/error.html";
	}
	

    
    
	@GetMapping("/download/{fileId}")
	public ResponseEntity<Resource> fileDownload(@PathVariable("fileId") Long fileId) throws IOException {
		FileVo fileVo = fileService.getFile(fileId);
	    Path path = Paths.get(fileVo.getFilepath());
	    Resource resource = new InputStreamResource(Files.newInputStream(path));
	    return ResponseEntity.ok()
	            .contentType(MediaType.parseMediaType("application/octet-stream"))
	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileVo.getOrgFileNm() + "\"")
	            .body(resource);
	}

	@DeleteMapping("/notice/{id}")
    public String delete(@PathVariable("id") Long id) {
		noticeService.delete(id);
        return "redirect:/";
    }

}
