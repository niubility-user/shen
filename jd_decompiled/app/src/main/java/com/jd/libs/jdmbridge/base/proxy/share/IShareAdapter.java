package com.jd.libs.jdmbridge.base.proxy.share;

import com.jd.libs.jdmbridge.base.callback.ShareCallback;
import org.json.JSONObject;

/* loaded from: classes16.dex */
interface IShareAdapter {
    public static final String SHARE_ACTION_OPEN = "O";
    public static final String SHARE_ACTION_PANE = "P";
    public static final String SHARE_ACTION_SET = "S";

    /* loaded from: classes16.dex */
    public static class KeyParam {
        public String keyChannel;
        public String keyContent;
        public String keyEndTime;
        public String keyId;
        public String keyImg;
        public String keyTitle;
        public String sourceCode;
        public String url;
    }

    /* loaded from: classes16.dex */
    public static class QrParam {
        public String isDecodeDirectUrl;
        public String mid_pic;
        public String mid_pic_x;
        public String qr_direct;
        public String qr_type;
    }

    /* loaded from: classes16.dex */
    public static class ShareInfo {
        public String channel;
        public String content;
        public String event_id;
        public String img;
        public KeyParam keyParam;
        public String mpIconUrl;
        public String mpId;
        public String mpPath;
        public String mpType;
        public QrParam qrParam;
        public String shareActionType;
        public String timeline_title;
        public String title;
        public String url;
    }

    ShareInfo parseShareInfoFromJson(JSONObject jSONObject);

    void sendShare(ShareInfo shareInfo, String str, ShareCallback shareCallback);

    void setShareInfo(ShareInfo shareInfo);

    void showShareDialog(ShareInfo shareInfo, String str, ShareCallback shareCallback);
}
