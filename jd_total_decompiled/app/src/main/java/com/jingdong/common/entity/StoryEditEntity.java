package com.jingdong.common.entity;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class StoryEditEntity {
    private String content;
    private Date lastModify;
    private List<String> productImgEntities;
    private String relatedCircleId;
    private String storyEditID;
    private List<String> storyImgEntities;

    public String getContent() {
        return this.content;
    }

    public Date getLastModify() {
        return this.lastModify;
    }

    public List<String> getProductImgEntities() {
        return this.productImgEntities;
    }

    public String getProductImgEntitiesStr() {
        StringBuilder sb = new StringBuilder();
        List<String> list = this.productImgEntities;
        if (list != null && list.size() > 0) {
            Iterator<String> it = this.productImgEntities.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append(";");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public String getRelatedCircleId() {
        return this.relatedCircleId;
    }

    public String getStoryEditID() {
        return this.storyEditID;
    }

    public List<String> getStoryImgEntities() {
        return this.storyImgEntities;
    }

    public String getStoryImgEntitiesStr() {
        StringBuilder sb = new StringBuilder();
        List<String> list = this.storyImgEntities;
        if (list != null && list.size() > 0) {
            Iterator<String> it = this.storyImgEntities.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append(";");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setLastModify(Date date) {
        this.lastModify = date;
    }

    public void setProductImgEntities(List<String> list) {
        this.productImgEntities = list;
    }

    public void setRelatedCircleId(String str) {
        this.relatedCircleId = str;
    }

    public void setStoryEditID(String str) {
        this.storyEditID = str;
    }

    public void setStoryImgEntities(List<String> list) {
        this.storyImgEntities = list;
    }
}
