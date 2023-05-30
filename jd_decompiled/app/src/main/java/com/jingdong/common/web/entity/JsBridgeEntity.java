package com.jingdong.common.web.entity;

import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.utils.ShareUtil;

/* loaded from: classes6.dex */
public class JsBridgeEntity {
    public boolean isNeedShare;
    public String jdPayAccountCallBack;
    public JdPayParam jdPayParam;
    public String jdPayScanCodeCallBack;
    public String jsCallback;
    public String metroPayData;
    public boolean isAndroidUploadImage = false;
    public String event_id = "";
    public ShareInfo shareInfo = new ShareInfo();
    public ShareUtil.CallbackListener shareCallbackListener = null;
    public ShareUtil.ClickCallbackListener shareClickCallbackListener = null;
    public boolean canJumpToPay = true;
    public boolean canReturnThirdApp = true;
    public int canControlBackByWeb = 0;

    /* loaded from: classes6.dex */
    public static class JdPayParam {
        public String callback;
        public String param;
        public String returnType;
        public String type;
    }
}
