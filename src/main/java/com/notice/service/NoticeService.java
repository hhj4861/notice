package com.notice.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.notice.exception.CommonException;
import com.notice.exception.ErrorCode;
import com.notice.model.entity.NoticeEntity;
import com.notice.model.vo.JoinVo;
import com.notice.model.vo.NoticeReqVo;
import com.notice.model.vo.NoticeResVo;
import com.notice.repository.NoticeRepository;
import com.notice.util.DateUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NoticeService {

	private static final int BLOCK_PAGE_COUNT = 10; // 블럭에 존재하는 페이지 번호 수
	private static final int PAGE_COUNT = 5; // 한 페이지에 존재하는 공지 수

	@Autowired
	private NoticeRepository nRepo;

	
	@Transactional
	public Long save(NoticeEntity noticeEntity) {
		return nRepo.save(noticeEntity).getId();
	}
	
	@Transactional
	public Long update(NoticeEntity noticeEntity) {
		return nRepo.updateCustom(noticeEntity);
	}

	/**
	 * 공지사항 목록조회
	 * 
	 * @return List<NoticeResVo>
	 * @throws Exception
	 */
	public List<NoticeResVo> getNoticeList(Integer pageNum) throws CommonException {
		Page<NoticeEntity> page = nRepo
				.findAll(PageRequest.of(pageNum - 1, PAGE_COUNT, Sort.by(Sort.Direction.ASC, "createdDate")));
		log.debug("page.getTotalPages :" + page.getTotalPages());

		List<NoticeEntity> noticeList = page.getContent();
		List<NoticeResVo> noticeResList = new ArrayList<>();

		for (NoticeEntity notice : noticeList) {
			NoticeResVo noticeRes = new NoticeResVo();
			// 동일 변수 copy
			BeanUtils.copyProperties(notice, noticeRes);
			log.debug(noticeRes.toString());			
			// 반환리스트 데이터 셋
			noticeResList.add(noticeRes);
		}
//		throw new CommonException("",ErrorCode.ERR_400,"test");
		return noticeResList;
	}

	/**
	 * 공지사항 상세조회
	 * 
	 * @return NoticeResVo
	 * @throws Exception
	 */
	public JoinVo getDetail(Long noticeId) throws CommonException {	
//		JoinVo jo = nRepo.selectJoinDetail(noticeId);
//		log.debug(jo.toString());
		NoticeEntity noticeEn = nRepo.findById(noticeId).get();
		log.debug(noticeEn.toString());
		JoinVo jo = new JoinVo();
		if(noticeEn.getFileId() == null || noticeEn.getFileId() == 0 ) {
			BeanUtils.copyProperties(noticeEn, jo);
		}else {
			jo = nRepo.selectJoinDetail(noticeId);
		}
		log.debug(jo.toString());
		
		return jo;
	}

	/**
	 * 공지사항 페이징 처리
	 * 
	 * @return List<NoticeResVo>
	 * @throws Exception
	 */
	public Integer[] getPageList(Integer curPageNum) {
		Integer[] pageList = new Integer[BLOCK_PAGE_COUNT];

		log.debug("데이터 수 :" + nRepo.count());
		// 총 게시글 갯수
		Double postsTotalCount = Double.valueOf(nRepo.count());
		log.debug("postsTotalCount :" + postsTotalCount);
		// 총 게시글 기준으로 계산한 마지막 페이지 번호 계산 (올림으로 계산)
		Integer totalLastPageNum = (int) (Math.ceil((postsTotalCount / PAGE_COUNT)));
		log.debug("totalLastPageNum :" + totalLastPageNum);
		// 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
		Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_COUNT) ? curPageNum + BLOCK_PAGE_COUNT
				: totalLastPageNum;
		log.debug("blockLastPageNum :" + blockLastPageNum);
		// 페이지 시작 번호 조정
		// 10페이지가 넘어가면 페이지 -2
		curPageNum = (curPageNum <= BLOCK_PAGE_COUNT) ? 1 : curPageNum - 2;
		// log.debug("curPageNum :"+curPageNum);
		// 페이지 번호 할당
		for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
			log.debug(String.format("val(%d) , idx(%d)", val, idx));
			pageList[idx] = val;
			log.debug("pageList[" + idx + "] : " + pageList[idx]);
		}

		return pageList;
	}

	/**
	 * 공지사항 저장
	 * 
	 * @return noticeId
	 * @throws Exception
	 */
	public Long saveNotice(NoticeReqVo noticeReqVo) throws CommonException {
		try {
			return save(noticeDataSet(noticeReqVo, 1));
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 공지사항 수정
	 * 
	 * @return noticeId
	 * @throws Exception
	 */
	public Long updateNotice(NoticeReqVo noticeReqVo) throws CommonException {
		log.debug("수정 대상 ID :"+noticeReqVo.getId());		
		try {
			return update(noticeDataSet(noticeReqVo, 2));
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 공지사항 삭제
	 */
	@Transactional
    public void delete(Long id) {
		nRepo.deleteById(id);
    }

	/**
	 * 저장하기위해 Entity Data Set
	 * 
	 * @param NoticeReqVo 요청데이터
	 * @param flag        1:저장 , flag 2 : 수정
	 * @return CustomerEntity
	 * @throws Exception
	 */
	private NoticeEntity noticeDataSet(NoticeReqVo noticeReqVo, int flag) throws CommonException,Exception {
		NoticeEntity noticeEntity = new NoticeEntity();
		
		// 변수명 & 타입 copy
		BeanUtils.copyProperties(noticeReqVo, noticeEntity);
		log.debug("saveNotice Data :" + noticeEntity.toString());

		// currentTime to String
		if (flag == 1) {
			noticeEntity.setCreatedDate(DateUtil.getCurrentTimeToDataFormat(System.currentTimeMillis()));
		}
		noticeEntity.setLastDate(DateUtil.getCurrentTimeToDataFormat(System.currentTimeMillis()));

		return noticeEntity;
	}

}
