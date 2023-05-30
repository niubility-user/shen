package com.jd.manto.d.d0;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.common.bing.utils.JDBingConst;
import com.jingdong.common.jdrtc.bean.RtcMpUserInfo;
import com.jingdong.common.jdrtc.event.RtcMpInfoInterface;
import com.jingdong.common.jdrtc.event.RtcMpStateInterface;
import com.jingdong.common.jdrtc.utils.JDMpRtcUtils;
import com.jingdong.sdk.jdhttpdns.pojo.IpModelOld;
import java.util.Observable;
import java.util.Observer;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class c {

    /* loaded from: classes17.dex */
    class a implements Observer {
        final /* synthetic */ f a;
        final /* synthetic */ Context b;

        a(f fVar, Context context) {
            this.a = fVar;
            this.b = context;
        }

        @Override // java.util.Observer
        public void update(Observable observable, Object obj) {
            String str = "meetingMpIRouterCallback msg = " + obj.toString();
            try {
                int optInt = new JSONObject(obj.toString()).optInt("type");
                if (optInt == 400) {
                    this.a.a();
                    c.n(this.b, this.a);
                    c.m(this.b, this.a);
                } else if (optInt == 401) {
                    this.a.c("error code 401");
                }
            } catch (Exception unused) {
                this.a.c("error");
            }
        }
    }

    /* loaded from: classes17.dex */
    class b implements JDMpRtcUtils.IJdmpRtcDownload {
        final /* synthetic */ e a;

        b(e eVar) {
            this.a = eVar;
        }

        @Override // com.jingdong.common.jdrtc.utils.JDMpRtcUtils.IJdmpRtcDownload
        public void fail() {
            this.a.failed();
        }

        @Override // com.jingdong.common.jdrtc.utils.JDMpRtcUtils.IJdmpRtcDownload
        public void success() {
            this.a.success();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.manto.d.d0.c$c  reason: collision with other inner class name */
    /* loaded from: classes17.dex */
    public class C0188c implements RtcMpStateInterface {
        final /* synthetic */ f a;

        C0188c(f fVar) {
            this.a = fVar;
        }

        @Override // com.jingdong.common.jdrtc.event.RtcMpStateInterface
        public void onRtcCreate(String str) {
            this.a.onRtcCreate(str);
        }

        @Override // com.jingdong.common.jdrtc.event.RtcMpStateInterface
        public void onRtcInvite(String str, RtcMpUserInfo rtcMpUserInfo, boolean z, String str2) {
            this.a.b(str, rtcMpUserInfo.getPin(), rtcMpUserInfo.getName(), rtcMpUserInfo.getAvatar(), rtcMpUserInfo.getSubAppId(), z, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class d implements RtcMpInfoInterface {
        final /* synthetic */ f a;

        d(f fVar) {
            this.a = fVar;
        }

        @Override // com.jingdong.common.jdrtc.event.RtcMpInfoInterface
        public void onRtcCamera(boolean z, boolean z2, boolean z3) {
            this.a.onRtcCamera(z, z2, z3);
        }

        @Override // com.jingdong.common.jdrtc.event.RtcMpInfoInterface
        public void onRtcLeave(boolean z) {
            this.a.onRtcLeave(z);
            c.o(com.jingdong.a.g());
        }

        @Override // com.jingdong.common.jdrtc.event.RtcMpInfoInterface
        public void onRtcStart(boolean z) {
            this.a.onRtcStart(z);
        }
    }

    /* loaded from: classes17.dex */
    public interface e {
        void failed();

        void success();
    }

    /* loaded from: classes17.dex */
    public interface f {
        void a();

        void b(String str, String str2, String str3, String str4, String str5, boolean z, String str6);

        void c(String str);

        void d();

        void onRtcCamera(boolean z, boolean z2, boolean z3);

        void onRtcCreate(String str);

        void onRtcLeave(boolean z);

        void onRtcStart(boolean z);
    }

    public static void a(Context context) {
        JDMpRtcUtils.rtcMpAcceptInvite(context, "manto.rtc");
    }

    public static View d(Context context) {
        return JDMpRtcUtils.getRtcSelfMpSurface(context, "manto.rtc");
    }

    public static View e(Context context) {
        return JDMpRtcUtils.getRtcThatMpSurface(context, "manto.rtc");
    }

    public static void f(Context context) {
        JDMpRtcUtils.rtcMpHangUp(context, "manto.rtc");
        o(context);
    }

    public static void g(e eVar) {
        JDMpRtcUtils.installRtcBundle(new b(eVar));
    }

    public static void h(Context context, boolean z) {
        JDMpRtcUtils.rtcHandFreeOpen(context, "manto.rtc", z);
    }

    public static void i(Context context, boolean z) {
        JDMpRtcUtils.rtcMpSpeakerOpen(context, "manto.rtc", z);
    }

    public static void j(Context context) {
        JDMpRtcUtils.rtcMpToggleCamera(context, "manto.rtc");
    }

    public static void k(Context context, boolean z) {
        JDMpRtcUtils.rtcMpVideoOpen(context, "manto.rtc", z);
    }

    public static void l(Context context, String str, String str2, f fVar) {
        if (JDMpRtcUtils.getMpRegisterStatus(context, "manto.rtc", str, "jd.program_yf", str2)) {
            fVar.d();
        } else if (JDMpRtcUtils.registerMpByInstance(context, "ap1-rtc.jd.com", IpModelOld.PORT_HTTPS, str, "jd.program_yf", str2, "manto.rtc", "android", JDBingConst.PARAM_ERROR_CODE, new a(fVar, context))) {
        } else {
            fVar.c("plugin install failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void m(Context context, f fVar) {
        JDMpRtcUtils.setRtcMpInfoInterface(context, "manto.rtc", new d(fVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void n(Context context, f fVar) {
        JDMpRtcUtils.setRtcMpStateInterface(context, "manto.rtc", new C0188c(fVar));
    }

    public static void o(Context context) {
        JDMpRtcUtils.releaseRtcAllMpSurface(context, "manto.rtc");
    }

    public static void p(Context context, String str, String str2, String str3, String str4, boolean z, String str5, String str6, boolean z2) {
        RtcMpUserInfo rtcMpUserInfo = new RtcMpUserInfo();
        rtcMpUserInfo.setPin(str);
        rtcMpUserInfo.setAppId("jd.program_yf");
        if (!TextUtils.isEmpty(str3)) {
            rtcMpUserInfo.setSubAppId(str3);
        }
        if (TextUtils.isEmpty(str6)) {
            JDMpRtcUtils.rtcMpCall(context, "manto.rtc", str4, str2, rtcMpUserInfo, z, str5);
        } else {
            JDMpRtcUtils.rtcMpCall(context, "manto.rtc", str4, str2, rtcMpUserInfo, z, str5, str6, z2);
        }
    }

    public static void q(Context context) {
        JDMpRtcUtils.unRegisterMpByInstance(context, "manto.rtc");
    }
}
