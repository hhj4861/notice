package com.notice.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QNoticeEntity is a Querydsl query type for NoticeEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNoticeEntity extends EntityPathBase<NoticeEntity> {

    private static final long serialVersionUID = -1806770664L;

    public static final QNoticeEntity noticeEntity = new QNoticeEntity("noticeEntity");

    public final StringPath author = createString("author");

    public final StringPath contents = createString("contents");

    public final StringPath createdDate = createString("createdDate");

    public final NumberPath<Long> fileId = createNumber("fileId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lastDate = createString("lastDate");

    public final StringPath title = createString("title");

    public QNoticeEntity(String variable) {
        super(NoticeEntity.class, forVariable(variable));
    }

    public QNoticeEntity(Path<? extends NoticeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNoticeEntity(PathMetadata metadata) {
        super(NoticeEntity.class, metadata);
    }

}

