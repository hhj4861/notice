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

	private static final int BLOCK_PAGE_COUNT = 10; // ���� �����ϴ� ������ ��ȣ ��
	private static final int PAGE_COUNT = 5; // �� �������� �����ϴ� ���� ��

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
	 * �������� �����ȸ
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
			// ���� ���� copy
			BeanUtils.copyProperties(notice, noticeRes);
			log.debug(noticeRes.toString());			
			// ��ȯ����Ʈ ������ ��
			noticeResList.add(noticeRes);
		}
//		throw new CommonException("",ErrorCode.ERR_400,"test");
		return noticeResList;
	}

	/**
	 * �������� ����ȸ
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
	 * �������� ����¡ ó��
	 * 
	 * @return List<NoticeResVo>
	 * @throws Exception
	 */
	public Integer[] getPageList(Integer curPageNum) {
		Integer[] pageList = new Integer[BLOCK_PAGE_COUNT];

		log.debug("������ �� :" + nRepo.count());
		// �� �Խñ� ����
		Double postsTotalCount = Double.valueOf(nRepo.count());
		log.debug("postsTotalCount :" + postsTotalCount);
		// �� �Խñ� �������� ����� ������ ������ ��ȣ ��� (�ø����� ���)
		Integer totalLastPageNum = (int) (Math.ceil((postsTotalCount / PAGE_COUNT)));
		log.debug("totalLastPageNum :" + totalLastPageNum);
		// ���� �������� �������� ���� ������ ������ ��ȣ ���
		Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_COUNT) ? curPageNum + BLOCK_PAGE_COUNT
				: totalLastPageNum;
		log.debug("blockLastPageNum :" + blockLastPageNum);
		// ������ ���� ��ȣ ����
		// 10�������� �Ѿ�� ������ -2
		curPageNum = (curPageNum <= BLOCK_PAGE_COUNT) ? 1 : curPageNum - 2;
		// log.debug("curPageNum :"+curPageNum);
		// ������ ��ȣ �Ҵ�
		for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
			log.debug(String.format("val(%d) , idx(%d)", val, idx));
			pageList[idx] = val;
			log.debug("pageList[" + idx + "] : " + pageList[idx]);
		}

		return pageList;
	}

	/**
	 * �������� ����
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
	 * �������� ����
	 * 
	 * @return noticeId
	 * @throws Exception
	 */
	public Long updateNotice(NoticeReqVo noticeReqVo) throws CommonException {
		log.debug("���� ��� ID :"+noticeReqVo.getId());		
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
	 * �������� ����
	 */
	@Transactional
    public void delete(Long id) {
		nRepo.deleteById(id);
    }

	/**
	 * �����ϱ����� Entity Data Set
	 * 
	 * @param NoticeReqVo ��û������
	 * @param flag        1:���� , flag 2 : ����
	 * @return CustomerEntity
	 * @throws Exception
	 */
	private NoticeEntity noticeDataSet(NoticeReqVo noticeReqVo, int flag) throws CommonException,Exception {
		NoticeEntity noticeEntity = new NoticeEntity();
		
		// ������ & Ÿ�� copy
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
