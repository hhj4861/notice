package com.notice.repository;

import com.notice.model.entity.NoticeEntity;
import com.notice.model.vo.JoinVo;


public interface NoticeRepositoryCustom {
	/**
	 * <pre>
	 * file id에 해당하는 공지사항 가져오기 
	 *   
	 * </pre>
	 * @param userId
	 * @return
	 */
	public JoinVo selectJoinDetail(Long noticeId);	
	
	/**
	 * <pre>
	 * 업데이트 (Notice , File)
	 *   
	 * </pre>
	 * @param userId
	 * @return
	 */
	public Long updateCustom(NoticeEntity noticeEntity);	
}
