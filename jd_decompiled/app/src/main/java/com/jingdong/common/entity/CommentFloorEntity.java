package com.jingdong.common.entity;

import java.util.Date;

/* loaded from: classes5.dex */
public class CommentFloorEntity {
    public static final String COMMENT_AGAIN = "again";
    public static final String COMMENT_MAIN = "main";
    private String commentContent;
    private String commentType;
    private Date lastModify;
    private String orderId;
    private String productId;

    public String getCommentContent() {
        return this.commentContent;
    }

    public String getCommentType() {
        return this.commentType;
    }

    public Date getLastModify() {
        return this.lastModify;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setCommentContent(String str) {
        this.commentContent = str;
    }

    public void setCommentType(String str) {
        this.commentType = str;
    }

    public void setLastModify(Date date) {
        this.lastModify = date;
    }

    public void setOrderId(String str) {
        this.orderId = str;
    }

    public void setProductId(String str) {
        this.productId = str;
    }
}
