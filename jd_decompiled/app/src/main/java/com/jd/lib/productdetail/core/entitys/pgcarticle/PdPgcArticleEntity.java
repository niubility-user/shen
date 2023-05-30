package com.jd.lib.productdetail.core.entitys.pgcarticle;

import java.util.ArrayList;

/* loaded from: classes15.dex */
public class PdPgcArticleEntity {
    public String author;
    public String authorPic;
    public String exposure;
    public String exposureIcon;
    public String flagVideoOrArticle;
    public String image;
    public String materialId;
    public String moreJumpUrl;
    public boolean newGeFlag;
    public String paperTitle;
    public String style;
    public String title;
    public int totalNum;
    public boolean universalPGCFlag;
    public String url;
    public ArrayList<PdPgcVideoEntity> videoList;
    public int videoNumber;

    public boolean isDyn() {
        return this.universalPGCFlag;
    }
}
