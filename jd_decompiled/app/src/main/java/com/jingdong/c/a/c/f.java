package com.jingdong.c.a.c;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.utils.ShareUtil;
import com.jingdong.common.utils.ShareValues;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes7.dex */
public class f {
    public int a;
    public ShareInfo b;

    /* renamed from: c */
    public c f12276c;
    public boolean d;

    /* renamed from: e */
    public String f12277e;

    /* renamed from: f */
    public String f12278f;

    /* renamed from: g */
    public String f12279g;

    /* renamed from: h */
    public String f12280h;

    /* renamed from: i */
    public JDJSONObject f12281i;

    /* renamed from: j */
    public JDJSONObject f12282j;

    /* renamed from: k */
    public boolean f12283k;

    /* renamed from: l */
    public boolean f12284l;

    /* renamed from: m */
    public boolean f12285m;

    /* renamed from: n */
    public byte[] f12286n;
    public e o = new e();
    public g p = new g();
    public ShareUtil.CallbackListener q;
    public ShareUtil.ClickCallbackListener r;
    public String s;
    public Bitmap t;
    public boolean u;
    public boolean v;

    public void a() {
        String config;
        ShareInfo shareInfo = this.b;
        if (shareInfo != null) {
            ShareValues.isNewWeiXinShareUI = "1".equals(shareInfo.getIsNewWeixinShareUI());
            this.s = this.b.getJdMpTaskAction();
            this.f12277e = this.b.getPanelBanner();
            this.d = !TextUtils.isEmpty(r0);
            if (this.b.getShareImageInfo() != null) {
                this.v = this.b.getShareImageInfo().imageShareType == 1;
            }
            if (!TextUtils.isEmpty(this.b.getMpId())) {
                this.f12285m = true;
            }
            if (TextUtils.isEmpty(this.b.getKeyShareJsonStr())) {
                return;
            }
            this.f12281i = JDJSON.parseObject(this.b.getKeyShareJsonStr());
            if (ShareUtil.isUseSwitchQuery()) {
                config = SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.SWITCH_SHARE_JCOMM_CREATE_SWITCH, true) ? DYConstants.DY_FALSE : DYConstants.DY_TRUE;
            } else {
                config = JDMobileConfig.getInstance().getConfig("JDShare", "jComRecognise", SwitchQueryFetcher.SWITCH_SHARE_JCOMM_CREATE_SWITCH);
            }
            if (this.f12281i != null && DYConstants.DY_FALSE.equals(config)) {
                if (!TextUtils.isEmpty(this.f12281i.optString("sourceCode")) && !TextUtils.isEmpty(this.f12281i.optString("keyChannel"))) {
                    if (ShareValues.isNewWeiXinShareUI && this.a != 2) {
                        List asList = Arrays.asList(this.f12281i.optString("keyChannel").split(DYConstants.DY_REGEX_COMMA));
                        for (int i2 = 0; i2 < this.b.getChannelsList().size(); i2++) {
                            if (asList.contains(this.b.getChannelsList().get(i2))) {
                                this.f12283k = true;
                                if (asList.contains("Wxfriends")) {
                                    this.f12284l = true;
                                }
                            }
                        }
                    } else {
                        this.f12283k = true;
                    }
                    if (this.f12281i.optString("keyTitle").length() > 100) {
                        JDJSONObject jDJSONObject = this.f12281i;
                        jDJSONObject.put("keyTitle", (Object) jDJSONObject.optString("keyTitle").substring(0, 100));
                    }
                    if (this.f12281i.optString("keyContent", "").length() > 300) {
                        JDJSONObject jDJSONObject2 = this.f12281i;
                        jDJSONObject2.put("keyContent", (Object) jDJSONObject2.optString("keyContent").substring(0, 300));
                    }
                    JDJSONObject optJSONObject = this.f12281i.optJSONObject("pictorial");
                    if (optJSONObject != null) {
                        this.f12278f = optJSONObject.optString("img", "");
                        this.f12279g = optJSONObject.optString("maintitle", "");
                        this.f12280h = optJSONObject.optString("subtitle", "");
                    }
                    OKLog.d("PanelModel", "is key share");
                } else {
                    OKLog.d("PanelModel", "key share param is abnormal,plz check");
                    ExceptionReporter.reportKeyShareException("checkKeyParam", "paramErr", this.f12281i.toJSONString(), "");
                }
                OKLog.d("PanelModel", "key share json: " + this.f12281i.toJSONString());
                return;
            }
            OKLog.d("PanelModel", "key share json is null , else switch query is false");
        }
    }

    public boolean b() {
        c cVar;
        List<b> list;
        return (!f() || (cVar = this.f12276c) == null || (list = cVar.d) == null || list.isEmpty()) ? false : true;
    }

    public boolean c() {
        return this.a == 2;
    }

    public boolean d() {
        ShareInfo shareInfo = this.b;
        return shareInfo != null && ((!TextUtils.isEmpty(shareInfo.getUrl()) && this.b.getUrl().toLowerCase().startsWith("http")) || this.b.getShareImageInfo() != null);
    }

    public boolean e() {
        return this.a == 4;
    }

    public boolean f() {
        return this.a == 1;
    }

    public boolean g() {
        return this.a == 3;
    }
}
