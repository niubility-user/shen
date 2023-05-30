package com.jingdong.common.widget.custom;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.entity.FollowGiftDetailEntity;
import com.jingdong.common.frame.IResumeListener;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes12.dex */
public class CustomFollowUtil {
    private FollowProgress followProgress;
    private FollowStateChangeObservable followStateChangeObservable;
    private int hasFollowed;
    private int needFollowGift;
    private boolean needJingDou;
    private SetoutSideViewEnabled setoutSideViewEnabled;
    private final int INVENTORY_FOLLOW_LOGIN_REQUEST_CODE = R2.attr.progressBarAutoRotateInterval;
    private final int END_WHAT = R2.attr.progressBarImage;
    private final int ERROR_WHAT = R2.attr.prospectNum;
    private Handler handler = new Handler() { // from class: com.jingdong.common.widget.custom.CustomFollowUtil.1
        {
            CustomFollowUtil.this = this;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i2 = message.what;
            if (1561 != i2) {
                if (1568 == i2) {
                    CustomFollowUtil.this.followProgress.fail(((Boolean) message.obj).booleanValue());
                    return;
                }
                return;
            }
            int i3 = message.getData().getInt("hasfollowed");
            JDJSONObject fastJsonObject = ((HttpResponse) message.obj).getFastJsonObject();
            try {
                if (!"0".equals(fastJsonObject.optString("code")) || (!"success".equals(fastJsonObject.optString("msg")) && !"hasFollowed".equals(fastJsonObject.optString("msg")))) {
                    CustomFollowUtil.this.handler.sendMessage(Message.obtain(CustomFollowUtil.this.handler, R2.attr.prospectNum, Boolean.valueOf(1 == i3)));
                    return;
                }
                CustomFollowUtil.this.followProgress.success(i3 == 0 ? 1 : 0, "hasFollowed".equals(fastJsonObject.optString("msg")));
                if (fastJsonObject.containsKey("followNumStr") && CustomFollowUtil.this.followStateChangeObservable != null) {
                    CustomFollowUtil.this.followStateChangeObservable.stateChangedWithResult(i3 == 0 ? 1 : 0, "hasFollowed".equals(fastJsonObject.optString("msg")), fastJsonObject.optString("followNumStr"));
                    CustomFollowUtil.this.parseJingDou(fastJsonObject);
                }
                CustomFollowUtil.this.parseFollowGiftInfo(fastJsonObject);
            } catch (Exception unused) {
                CustomFollowUtil.this.handler.sendMessage(Message.obtain(CustomFollowUtil.this.handler, R2.attr.prospectNum, Boolean.valueOf(1 == i3)));
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.common.widget.custom.CustomFollowUtil$2 */
    /* loaded from: classes12.dex */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ String val$authorId;
        final /* synthetic */ FollowProgress val$followProgress;
        final /* synthetic */ int val$hasfollowed;
        final /* synthetic */ int val$needFollowGift;
        final /* synthetic */ boolean val$needJingDou;
        final /* synthetic */ BaseActivity val$thisActivity;

        AnonymousClass2(int i2, String str, int i3, boolean z, BaseActivity baseActivity, FollowProgress followProgress) {
            CustomFollowUtil.this = r1;
            this.val$hasfollowed = i2;
            this.val$authorId = str;
            this.val$needFollowGift = i3;
            this.val$needJingDou = z;
            this.val$thisActivity = baseActivity;
            this.val$followProgress = followProgress;
        }

        @Override // java.lang.Runnable
        public void run() {
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setFunctionId("discoveryFollow");
            httpSetting.putJsonParam("action", Integer.valueOf(this.val$hasfollowed == 0 ? 1 : 2));
            JSONArray jSONArray = new JSONArray();
            try {
                jSONArray.put(0, this.val$authorId);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            httpSetting.putJsonParam("authorIDList", jSONArray);
            httpSetting.putJsonParam("needFollowGift", Integer.valueOf(this.val$needFollowGift));
            httpSetting.putJsonParam("jingBeanEventSource", Integer.valueOf(this.val$needJingDou ? 1 : 0));
            httpSetting.setHost(Configuration.getPortalHost());
            httpSetting.setAttempts(1);
            httpSetting.setPost(true);
            httpSetting.setCacheMode(2);
            httpSetting.setUseFastJsonParser(true);
            httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.widget.custom.CustomFollowUtil.2.1
                {
                    AnonymousClass2.this = this;
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    Message obtain = Message.obtain(CustomFollowUtil.this.handler, R2.attr.progressBarImage, httpResponse);
                    Bundle bundle = new Bundle();
                    bundle.putInt("hasfollowed", AnonymousClass2.this.val$hasfollowed);
                    obtain.setData(bundle);
                    CustomFollowUtil.this.handler.sendMessage(obtain);
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    final String str;
                    int i2;
                    if (httpError == null || httpError.getHttpResponse() == null || httpError.getHttpResponse().getFastJsonObject() == null) {
                        str = "";
                        i2 = 0;
                    } else {
                        i2 = httpError.getHttpResponse().getFastJsonObject().optInt("code");
                        str = httpError.getHttpResponse().getFastJsonObject().optString("msg");
                    }
                    if (i2 != 140) {
                        CustomFollowUtil.this.handler.sendMessage(Message.obtain(CustomFollowUtil.this.handler, R2.attr.prospectNum, Boolean.valueOf(1 == AnonymousClass2.this.val$hasfollowed)));
                        return;
                    }
                    if (TextUtils.isEmpty(str)) {
                        str = "\u65e0\u6cd5\u7ee7\u7eed\u5173\u6ce8\u4e86\uff0c\u6bcf\u4f4d\u7528\u6237\u6700\u591a\u5173\u6ce81000\u4e2a\u8d26\u53f7\u54e6~";
                    }
                    AnonymousClass2.this.val$thisActivity.post(new Runnable() { // from class: com.jingdong.common.widget.custom.CustomFollowUtil.2.1.1
                        {
                            AnonymousClass1.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            ToastUtils.showToastInCenter(str);
                            FollowProgress followProgress = AnonymousClass2.this.val$followProgress;
                            if (followProgress != null) {
                                followProgress.end();
                            }
                        }
                    });
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
                public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                }
            });
            this.val$thisActivity.getHttpGroupaAsynPool().add(httpSetting);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public abstract class FollowProgress {
        public FollowProgress() {
            CustomFollowUtil.this = r1;
        }

        public void end() {
            if (CustomFollowUtil.this.setoutSideViewEnabled != null) {
                CustomFollowUtil.this.setoutSideViewEnabled.update(true);
            }
        }

        public void fail(boolean z) {
            end();
        }

        public void start(int i2) {
            if (CustomFollowUtil.this.setoutSideViewEnabled != null) {
                CustomFollowUtil.this.setoutSideViewEnabled.update(false);
            }
            if (CustomFollowUtil.this.followStateChangeObservable != null) {
                CustomFollowUtil.this.followStateChangeObservable.click(i2 == 0 ? 1 : 0);
            }
        }

        public void success(int i2, boolean z) {
            end();
            if (CustomFollowUtil.this.followStateChangeObservable != null) {
                CustomFollowUtil.this.followStateChangeObservable.stateChanged(i2, z);
            }
        }
    }

    /* loaded from: classes12.dex */
    public static abstract class FollowStateChangeObservable {
        public abstract void click(int i2);

        public void jingDouCallBack(JingBeanSentInfo jingBeanSentInfo) {
        }

        @Deprecated
        public void stateChanged(int i2, boolean z) {
        }

        public void stateChangedWithResult(int i2, boolean z, String str) {
        }

        public void updateGiftUI(String str, List<FollowGiftDetailEntity> list) {
        }
    }

    /* loaded from: classes12.dex */
    public interface SetoutSideViewEnabled {
        void update(boolean z);
    }

    public void parseFollowGiftInfo(JDJSONObject jDJSONObject) {
        if (jDJSONObject.containsKey("followGiftInfo")) {
            JDJSONObject optJSONObject = jDJSONObject.optJSONObject("followGiftInfo");
            if (optJSONObject != null) {
                String optString = optJSONObject.optString("title");
                JDJSONArray optJSONArray = optJSONObject.optJSONArray("detail");
                ArrayList arrayList = new ArrayList();
                if (optJSONArray != null) {
                    for (int i2 = 0; i2 < optJSONArray.size(); i2++) {
                        arrayList.add((FollowGiftDetailEntity) JDJSON.parseObject(optJSONArray.optJSONObject(i2).toString(), FollowGiftDetailEntity.class));
                    }
                }
                FollowStateChangeObservable followStateChangeObservable = this.followStateChangeObservable;
                if (followStateChangeObservable == null || this.hasFollowed != 0) {
                    return;
                }
                followStateChangeObservable.updateGiftUI(optString, arrayList);
                return;
            }
            updateErrorGiftUI();
            return;
        }
        updateErrorGiftUI();
    }

    public void parseJingDou(JDJSONObject jDJSONObject) {
        FollowStateChangeObservable followStateChangeObservable;
        JingBeanSentInfo jingBeanSentInfo = (JingBeanSentInfo) JDJSON.parseObject(jDJSONObject.optString("jingBeanSentInfo"), JingBeanSentInfo.class);
        if (!this.needJingDou || jingBeanSentInfo == null || (followStateChangeObservable = this.followStateChangeObservable) == null) {
            return;
        }
        followStateChangeObservable.jingDouCallBack(jingBeanSentInfo);
    }

    private void updateErrorGiftUI() {
        FollowStateChangeObservable followStateChangeObservable;
        if (this.needFollowGift == 1 && (followStateChangeObservable = this.followStateChangeObservable) != null && this.hasFollowed == 0) {
            followStateChangeObservable.updateGiftUI("", new ArrayList());
        }
    }

    public void follow(final BaseActivity baseActivity, String str, int i2, int i3, boolean z, final FollowProgress followProgress) {
        this.needJingDou = z;
        this.followProgress = followProgress;
        this.needFollowGift = i3;
        this.hasFollowed = i2;
        followProgress.start(i2);
        LoginUserHelper.getInstance().executeLoginRunnable(baseActivity, new AnonymousClass2(i2, str, i3, z, baseActivity, followProgress), R2.attr.progressBarAutoRotateInterval);
        baseActivity.addResumeListener(new IResumeListener() { // from class: com.jingdong.common.widget.custom.CustomFollowUtil.3
            {
                CustomFollowUtil.this = this;
            }

            @Override // com.jingdong.common.frame.IResumeListener
            public void onResume() {
                baseActivity.removeResumeListener(this);
                followProgress.end();
            }
        });
    }

    public void setOnFollowStateChangeObservable(FollowStateChangeObservable followStateChangeObservable) {
        this.followStateChangeObservable = followStateChangeObservable;
    }

    public void setoutSideViewEnabled(SetoutSideViewEnabled setoutSideViewEnabled) {
        this.setoutSideViewEnabled = setoutSideViewEnabled;
    }
}
