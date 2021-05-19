package com.notice.repository;

import com.notice.model.entity.NoticeEntity;
import com.notice.model.vo.JoinVo;


public interface NoticeRepositoryCustom {
	/**
	 * <pre>
	 * file id�� �ش��ϴ� �������� �������� 
	 *   
	 * </pre>
	 * @param userId
	 * @return
	 */
	public JoinVo selectJoinDetail(Long noticeId);	
	
	/**
	 * <pre>
	 * ������Ʈ (Notice , File)
	 *   
	 * </pre>
	 * @param userId
	 * @return
	 */
	public Long updateCustom(NoticeEntity noticeEntity);	
}
