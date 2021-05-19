package com.notice.model.vo;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.notice.model.vo.QJoinVo is a Querydsl Projection type for JoinVo
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QJoinVo extends ConstructorExpression<JoinVo> {

    private static final long serialVersionUID = 551440874L;

    public QJoinVo(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> createdDate, com.querydsl.core.types.Expression<String> author, com.querydsl.core.types.Expression<String> lastDate, com.querydsl.core.types.Expression<String> contents, com.querydsl.core.types.Expression<Long> fileId, com.querydsl.core.types.Expression<String> orgFileNm) {
        super(JoinVo.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class, String.class, long.class, String.class}, id, title, createdDate, author, lastDate, contents, fileId, orgFileNm);
    }

}

