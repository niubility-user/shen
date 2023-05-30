package com.jingdong.app.mall.bundle.jd_component.pentagram.entity;

import android.text.TextUtils;
import java.util.List;

/* loaded from: classes2.dex */
public class RadarEntity {
    private CompareEntity compareEntity;
    private String nullData;
    private ScoreEntity scoreEntity;
    private TitleEntity titleEntity;
    private List<RaderViewData> userScoreTagList;

    public CompareEntity getCompareEntity() {
        return this.compareEntity;
    }

    public String getNullData() {
        return this.nullData;
    }

    public ScoreEntity getScoreEntity() {
        return this.scoreEntity;
    }

    public TitleEntity getTitleEntity() {
        return this.titleEntity;
    }

    public List<RaderViewData> getUserScoreTagList() {
        return this.userScoreTagList;
    }

    public boolean isShow() {
        return valid() || showPlaceHolder();
    }

    public void setCompareEntity(CompareEntity compareEntity) {
        this.compareEntity = compareEntity;
    }

    public void setNullData(String str) {
        this.nullData = str;
    }

    public void setScoreEntity(ScoreEntity scoreEntity) {
        this.scoreEntity = scoreEntity;
    }

    public void setTitleEntity(TitleEntity titleEntity) {
        this.titleEntity = titleEntity;
    }

    public void setUserScoreTagList(List<RaderViewData> list) {
        this.userScoreTagList = list;
    }

    public boolean showPlaceHolder() {
        return !TextUtils.isEmpty(this.nullData);
    }

    public boolean valid() {
        return (getUserScoreTagList() == null || getScoreEntity() == null || getTitleEntity() == null) ? false : true;
    }
}
