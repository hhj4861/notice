package com.notice.service;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.notice.model.entity.FileEntity;
import com.notice.model.vo.FileVo;
import com.notice.repository.FileRepository;
import com.notice.util.MD5Generator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileService {

	@Autowired
	private FileRepository fRepo;

	@Transactional
	public Long saveFile(MultipartFile files) throws IllegalStateException, IOException, NoSuchAlgorithmException {		
		Long fileId = 0L;
		
		if(files.getSize() > 0) {
			String origFilename = files.getOriginalFilename();
            String filename = new MD5Generator(origFilename).toString();
            /* 실행되는 위치의 'files' 폴더에 파일이 저장 */
            String savePath = System.getProperty("user.dir") + "\\files";
            /* 파일이 저장되는 폴더가 없으면 폴더를 생성. */
            if (!new File(savePath).exists()) {
                try{
                    new File(savePath).mkdir();
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
            String filePath = savePath + "\\" + filename;
            
            files.transferTo(new File(filePath));
            
            FileVo fileVo = new FileVo();
            
            fileVo.setFileNm(filename);
            fileVo.setOrgFileNm(origFilename);
            fileVo.setFilepath(filePath);
            
            FileEntity fileEn = new FileEntity();
    		BeanUtils.copyProperties(fileVo, fileEn);	
    		fileId = fRepo.save(fileEn).getId();            
		}
		
		return fileId;
	}

	public FileVo getFile(Long id) {
		FileEntity fileEn = fRepo.findById(id).get();
		FileVo fileVo = new FileVo();
		BeanUtils.copyProperties(fileEn, fileVo);	

		return fileVo;
	}

}
