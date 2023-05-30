package com.jingdong.common.entity;

import com.jd.framework.json.JDJSON;

/* loaded from: classes5.dex */
public class JumpEntityProxy {
    public String des;
    public String eventId;
    public Params params;
    public ShareEntity shareInfo;
    public String srv;

    /* loaded from: classes5.dex */
    public static class Params {
        public String activityId;
        public String needLogin;
        public String position;
        public String url;
    }

    public JumpEntity proxy() {
        JumpEntity jumpEntity = new JumpEntity();
        jumpEntity.des = this.des;
        jumpEntity.params = JDJSON.toJSONString(this.params);
        jumpEntity.srv = this.srv;
        jumpEntity.eventId = this.eventId;
        jumpEntity.setShareInfo(this.shareInfo);
        return jumpEntity;
    }
}
