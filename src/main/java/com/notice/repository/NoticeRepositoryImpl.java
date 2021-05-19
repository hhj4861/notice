package com.notice.repository;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.notice.model.entity.NoticeEntity;
import com.notice.model.vo.JoinVo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.notice.model.entity.QNoticeEntity;
import com.notice.model.entity.QFileEntity;

public class NoticeRepositoryImpl extends QuerydslRepositorySupport implements NoticeRepositoryCustom {
	
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	
	
	public NoticeRepositoryImpl() {
		super(NoticeEntity.class);
	}

	
	@Override
	public JoinVo selectJoinDetail(Long noticeId) {
		QNoticeEntity qNoti = QNoticeEntity.noticeEntity;
		QFileEntity qFile = QFileEntity.fileEntity;
		
		ConstructorExpression<JoinVo> construtor = Projections.constructor(JoinVo.class,
				qNoti.id,qNoti.title,qNoti.createdDate,qNoti.author,qNoti.lastDate,qNoti.contents,qNoti.fileId,qFile.orgFileNm);		
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if(noticeId > 0) {
			builder.and(qNoti.id.eq(noticeId));
		}
		
		return jpaQueryFactory
				.select(construtor)
				.from(qNoti).join(qFile).on(qNoti.fileId.eq(qFile.id))
				.where(builder)
				.fetchOne();
	}

	@Transactional
	@Override
	public Long updateCustom(NoticeEntity noticeEntity) {
		QNoticeEntity qNoti = QNoticeEntity.noticeEntity;
		
		long affectedCnt = 0;
			
		affectedCnt = jpaQueryFactory
				.update(qNoti)
				.set(qNoti.title, noticeEntity.getTitle())
				.set(qNoti.author, noticeEntity.getAuthor())
				.set(qNoti.fileId, noticeEntity.getFileId())
				.set(qNoti.contents, noticeEntity.getContents())
				.set(qNoti.lastDate, noticeEntity.getLastDate())
				.where(qNoti.id.eq(noticeEntity.getId()))
				.execute();
		
		return affectedCnt;
	}
	
	

}
