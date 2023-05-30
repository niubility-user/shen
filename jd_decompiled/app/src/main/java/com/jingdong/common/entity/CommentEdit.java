package com.jingdong.common.entity;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class CommentEdit {
    private List<String> businessMediaTags;
    private List<String> comments;
    private String content;
    private List<String> imgs;
    private Date lastModify;
    private List<String> mediaIds;
    private List<String> multiDimenInfos;
    private String orderId;
    private int score;
    private String sku;

    public List<String> getBusinessMediaTags() {
        return this.businessMediaTags;
    }

    public String getBusinessMediaTagsString() {
        StringBuilder sb = new StringBuilder();
        List<String> list = this.businessMediaTags;
        if (list != null && list.size() > 0) {
            Iterator<String> it = this.businessMediaTags.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append(";");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public List<String> getComments() {
        return this.comments;
    }

    public String getCommentsString() {
        StringBuilder sb = new StringBuilder();
        List<String> list = this.comments;
        if (list != null && list.size() > 0) {
            for (String str : this.comments) {
                if (str != null) {
                    sb.append(str);
                }
                sb.append("/");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public String getContent() {
        return this.content;
    }

    public List<String> getImgs() {
        return this.imgs;
    }

    public String getImgsString() {
        StringBuilder sb = new StringBuilder();
        List<String> list = this.imgs;
        if (list != null && list.size() > 0) {
            Iterator<String> it = this.imgs.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append(";");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public Date getLastModify() {
        return this.lastModify;
    }

    public List<String> getMediaIds() {
        return this.mediaIds;
    }

    public String getMediaIdsString() {
        StringBuilder sb = new StringBuilder();
        List<String> list = this.mediaIds;
        if (list != null && list.size() > 0) {
            Iterator<String> it = this.mediaIds.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append(";");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public String getMultiDimenInfoString() {
        StringBuilder sb = new StringBuilder();
        List<String> list = this.multiDimenInfos;
        if (list != null && list.size() > 0) {
            Iterator<String> it = this.multiDimenInfos.iterator();
            while (it.hasNext()) {
                sb.append(it.next() + ";");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public List<String> getMultiDimenInfos() {
        return this.multiDimenInfos;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public int getScore() {
        return this.score;
    }

    public String getSku() {
        return this.sku;
    }

    public boolean isTheSameOne(CommentEdit commentEdit) {
        return this.orderId.equals(commentEdit.getOrderId()) && this.sku.equals(commentEdit.getSku());
    }

    public void setBusinessMediaTags(List<String> list) {
        this.businessMediaTags = list;
    }

    public void setComments(List<String> list) {
        this.comments = list;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setImgs(List<String> list) {
        this.imgs = list;
    }

    public void setLastModify(Date date) {
        this.lastModify = date;
    }

    public void setMediaIds(List<String> list) {
        this.mediaIds = list;
    }

    public void setMultiDimenInfos(List<String> list) {
        this.multiDimenInfos = list;
    }

    public void setOrderId(String str) {
        this.orderId = str;
    }

    public void setScore(int i2) {
        this.score = i2;
    }

    public void setSku(String str) {
        this.sku = str;
    }
}
