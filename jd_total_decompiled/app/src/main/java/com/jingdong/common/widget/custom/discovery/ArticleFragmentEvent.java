package com.jingdong.common.widget.custom.discovery;

import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.entity.discovery.ArticleFooterEntity;
import com.jingdong.common.entity.discovery.HeaderEntity;
import com.jingdong.common.entity.discovery.IFloorEntity;
import java.util.List;

/* loaded from: classes12.dex */
public class ArticleFragmentEvent extends BaseEvent {
    public static final String KEY_COMMENT_ENTITY = "KEY_COMMENT_ENTITY";
    public static final String TYPE_ACTIVITY_SHOW_FAILED = "TYPE_ACTIVITY_SHOW_FAILED";
    public static final String TYPE_ACTIVITY_UPDATE_FLOORS = "TYPE_ACTIVITY_UPDATE_FLOORS";
    public static final String TYPE_ACTIVITY_UPDATE_FOOTER = "TYPE_ACTIVITY_UPDATE_FOOTER";
    public static final String TYPE_ACTIVITY_UPDATE_TITLE = "TYPE_ACTIVITY_UPDATE_TITLE";
    public static final String TYPE_CLICK_DELETE_VIEW = "TYPE_CLICK_DELETE_VIEW";
    public static final String TYPE_CLICK_ITEM_VIEW = "TYPE_CLICK_ITEM_VIEW";
    public static final String TYPE_FINISH_SELF = "TYPE_FINISH_SELF";
    public static final String TYPE_LIKE_SUCCESS_VIEW = "TYPE_LIKE_SUCCESS_VIEW";
    private ArticleFooterEntity articleFooterEntity;
    private List<IFloorEntity> floorEntityList;
    private List<IFloorEntity> goodsEntityList;
    private HeaderEntity headerEntity;
    public String soleTag;

    public ArticleFragmentEvent(String str) {
        super(str);
    }

    public ArticleFooterEntity getArticleFooterEntity() {
        return this.articleFooterEntity;
    }

    public List<IFloorEntity> getFloorEntityList() {
        return this.floorEntityList;
    }

    public List<IFloorEntity> getGoodsEntityList() {
        return this.goodsEntityList;
    }

    public HeaderEntity getHeaderEntity() {
        return this.headerEntity;
    }

    public ArticleFragmentEvent setSoleTag(String str) {
        this.soleTag = str;
        return this;
    }

    public ArticleFragmentEvent(String str, List<IFloorEntity> list) {
        super(str);
        this.floorEntityList = list;
    }

    public ArticleFragmentEvent(String str, List<IFloorEntity> list, List<IFloorEntity> list2) {
        super(str);
        this.floorEntityList = list;
        this.goodsEntityList = list2;
    }

    public ArticleFragmentEvent(String str, ArticleFooterEntity articleFooterEntity) {
        super(str);
        this.articleFooterEntity = articleFooterEntity;
    }

    public ArticleFragmentEvent(String str, HeaderEntity headerEntity) {
        super(str);
        this.headerEntity = headerEntity;
    }
}
