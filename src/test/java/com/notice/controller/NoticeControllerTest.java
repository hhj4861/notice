package com.notice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notice.model.entity.NoticeEntity;
import com.notice.repository.NoticeRepository;
import com.notice.util.DateUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class NoticeControllerTest {
	
    @Autowired
    NoticeRepository nRepo;

    @Autowired
    private MockMvc mockMvc;
   
    @Before
    public void insertBoard() throws Exception{
        for(int i =1; i<=10; i++){
            NoticeEntity noticeEntity = new NoticeEntity();
            
            noticeEntity.setAuthor("작성자 + "+i);
            noticeEntity.setContents("내용 + "+i);
            noticeEntity.setTitle("제목 + "+i);
            noticeEntity.setCreatedDate(DateUtil.getCurrentTimeToDataFormat(System.currentTimeMillis()));
            noticeEntity.setLastDate(DateUtil.getCurrentTimeToDataFormat(System.currentTimeMillis()));
            noticeEntity.setFileId(0L);
            nRepo.save(noticeEntity);
        }
    }

    @Test
    public void addNotice() throws Exception {

    	NoticeEntity noticeEntity = new NoticeEntity();
        
        noticeEntity.setAuthor("추가작성자");
        noticeEntity.setContents("추가내용");
        noticeEntity.setTitle("추가제목");
        noticeEntity.setCreatedDate(DateUtil.getCurrentTimeToDataFormat(System.currentTimeMillis()));
        noticeEntity.setLastDate(DateUtil.getCurrentTimeToDataFormat(System.currentTimeMillis()));
        noticeEntity.setFileId(0L);
        
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/notice")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(noticeEntity)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        this.getNotice();
    }

    @Test
    public void getNotice() throws Exception {
        mockMvc.perform(get("/")
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andDo(print())
            .andExpect(status().isOk());
    }
    
    
    @Test
    public void editBoard() throws Exception{
        
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setAuthor("수정작성자");
        noticeEntity.setContents("수정내용");
        noticeEntity.setTitle("수정제목");
        noticeEntity.setLastDate(DateUtil.getCurrentTimeToDataFormat(System.currentTimeMillis()));
        noticeEntity.setFileId(0L);
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(put("/notice/edit/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(noticeEntity)))
                .andDo(print())
                .andExpect(status().isOk());
        this.getNotice();
    }
    
    @Test
    public void getDetail() throws Exception {
        mockMvc.perform(get("/notice/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void delNotice() throws Exception{
        mockMvc.perform(delete("/notice/2")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
        this.getNotice();
    }
    
    
    @Test
    public void notice_BadRequest_Detail() throws Exception{ 

        mockMvc.perform(get("/notice/-1")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void notice_BadRequest_Edit() throws Exception{
        
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setAuthor("수정작성자");
        noticeEntity.setContents("수정내용");
        noticeEntity.setTitle("수정제목");
        noticeEntity.setLastDate(DateUtil.getCurrentTimeToDataFormat(System.currentTimeMillis()));
        noticeEntity.setFileId(0L);
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(put("/notice/edit/-1")
        		.accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(noticeEntity)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}