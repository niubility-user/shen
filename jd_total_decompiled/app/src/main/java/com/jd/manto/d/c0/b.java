package com.jd.manto.d.c0;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.jd.manto.sdkimpl.video.v2.MantoVideoPlayerV2;
import com.jingdong.common.jdreactFramework.views.pureVideo.JDPureVideoManager;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.base.MantoCallback;
import com.jingdong.manto.jsapi.coverview.CoverViewContainer;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager;
import com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.jsapi.refact.video.JsApiVideoPlayer;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.tencent.smtt.sdk.TbsReaderView;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/* loaded from: classes17.dex */
public class b extends JsApiVideoPlayer {

    /* renamed from: h  reason: collision with root package name */
    private static Pattern f6628h = Pattern.compile("[0-9]+");

    /* renamed from: g  reason: collision with root package name */
    private Map<Integer, c> f6629g = new HashMap();

    /* loaded from: classes17.dex */
    class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ View f6630g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Bundle f6631h;

        a(View view, Bundle bundle) {
            this.f6630g = view;
            this.f6631h = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jd.manto.d.c0.a c2;
            View view = this.f6630g;
            if ((view instanceof CoverViewContainer) && (c2 = b.this.c(view, this.f6631h.getString("appid"))) != null) {
                String string = this.f6631h.getString("cm_type");
                String string2 = this.f6631h.getString("cm_data", "");
                string.hashCode();
                char c3 = '\uffff';
                switch (string.hashCode()) {
                    case -802181223:
                        if (string.equals("exitFullScreen")) {
                            c3 = 0;
                            break;
                        }
                        break;
                    case 3443508:
                        if (string.equals("play")) {
                            c3 = 1;
                            break;
                        }
                        break;
                    case 3526264:
                        if (string.equals(JsApiVideoPlayer.CM_SEEK)) {
                            c3 = 2;
                            break;
                        }
                        break;
                    case 3540994:
                        if (string.equals("stop")) {
                            c3 = 3;
                            break;
                        }
                        break;
                    case 106440182:
                        if (string.equals("pause")) {
                            c3 = 4;
                            break;
                        }
                        break;
                    case 458133450:
                        if (string.equals("requestFullScreen")) {
                            c3 = 5;
                            break;
                        }
                        break;
                    case 1355420059:
                        if (string.equals(JsApiVideoPlayer.CM_PLAY_RATE)) {
                            c3 = 6;
                            break;
                        }
                        break;
                }
                switch (c3) {
                    case 0:
                        c2.c();
                        return;
                    case 1:
                        c2.playIfNotPlaying();
                        return;
                    case 2:
                        String replace = string2.replace("[", "").replace("]", "");
                        if (TextUtils.isEmpty(replace) || !b.this.e(replace)) {
                            return;
                        }
                        c2.i(Integer.valueOf(replace).intValue() * 1000);
                        return;
                    case 3:
                        c2.stop();
                        return;
                    case 4:
                        c2.pauseIfPlaying();
                        return;
                    case 5:
                        c2.f();
                        return;
                    case 6:
                        String replace2 = string2.replace("[", "").replace("]", "");
                        if (TextUtils.isEmpty(replace2)) {
                            return;
                        }
                        try {
                            c2.setSpeed(Float.valueOf(replace2).floatValue());
                            return;
                        } catch (Exception unused) {
                            return;
                        }
                    default:
                        return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.jd.manto.d.c0.a c(View view, String str) {
        return (com.jd.manto.d.c0.a) ((CoverViewContainer) view).convertTo(MantoVideoPlayerV2.class);
    }

    private c d(int i2) {
        if (this.f6629g.get(Integer.valueOf(i2)) == null) {
            c cVar = new c(i2, this);
            this.f6629g.put(Integer.valueOf(i2), cVar);
            return cVar;
        }
        return this.f6629g.get(Integer.valueOf(i2));
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public MantoLifecycleLisener addLifecycleLisener(Bundle bundle) {
        c d = d(bundle.getInt("hashCode"));
        if (d != null) {
            return d.d;
        }
        return null;
    }

    public void b(int i2) {
        if (this.f6629g.get(Integer.valueOf(i2)) != null) {
            this.f6629g.remove(Integer.valueOf(i2));
        }
    }

    public boolean e(String str) {
        return f6628h.matcher(str).matches();
    }

    @Override // com.jingdong.manto.jsapi.refact.video.JsApiVideoPlayer, com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public View getCustomView(MantoCore mantoCore, Bundle bundle) {
        Activity activity = mantoCore != null ? mantoCore.getActivity() : null;
        if (mantoCore == null || activity == null) {
            return null;
        }
        return new CoverViewContainer(activity, new MantoVideoPlayerV2(activity));
    }

    @Override // com.jingdong.manto.jsapi.refact.video.JsApiVideoPlayer, com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public void handleMethod(String str, View view, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        MantoThreadUtils.runOnUIThread(new a(view, bundle));
    }

    @Override // com.jingdong.manto.jsapi.refact.video.JsApiVideoPlayer, com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public boolean onViewInsert(Bundle bundle, View view, MantoCore mantoCore) {
        String string = bundle.getString("appId");
        com.jd.manto.d.c0.a c2 = c(view, string);
        String string2 = bundle.getString("scaleType");
        int i2 = bundle.getInt("hashCode");
        boolean z = bundle.getBoolean(JDPureVideoManager.SourceKey.LOOP, false);
        int i3 = bundle.getInt(AbstractMantoViewManager.VIEW_ID_KEY);
        String string3 = bundle.getString("poster", "");
        try {
            c2.e(i3);
            c2.setAppId(string);
            c2.setLoop(z);
            c d = d(i2);
            if (d != null) {
                d.d(mantoCore, c2, i3);
            }
            c2.b(string2);
            c2.q(bundle.getBoolean("muted", false));
            c2.h(bundle.getBoolean("showFullscreenBtn", true));
            c2.m(bundle.getBoolean("showBasicControls", true));
            c2.n(bundle.getBoolean("useCache", false));
            c2.setAutoPlay(bundle.getBoolean("autoplay", false));
            c2.p(bundle.getBoolean("showSpeedBtn", true));
            if (!TextUtils.isEmpty(string3)) {
                c2.j(string3);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            c cVar = this.f6629g.get(Integer.valueOf(i2));
            if (cVar != null) {
                cVar.e(i3);
            }
        }
        return true;
    }

    @Override // com.jingdong.manto.jsapi.refact.video.JsApiVideoPlayer, com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public boolean onViewRemove(Bundle bundle, View view, MantoCore mantoCore) {
        com.jd.manto.d.c0.a c2 = c(view, bundle.getString("appId"));
        int i2 = bundle.getInt(AbstractMantoViewManager.VIEW_ID_KEY);
        int i3 = bundle.getInt("hashCode");
        if (c2 != null) {
            c2.destroy();
        }
        c cVar = this.f6629g.get(Integer.valueOf(i3));
        if (cVar != null) {
            cVar.e(i2);
            return true;
        }
        return true;
    }

    @Override // com.jingdong.manto.jsapi.refact.video.JsApiVideoPlayer, com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public boolean onViewUpdate(MantoCore mantoCore, View view, Bundle bundle) {
        super.onViewUpdate(mantoCore, view, bundle);
        com.jd.manto.d.c0.a c2 = c(view, bundle.getString("appId"));
        String string = bundle.getString(TbsReaderView.KEY_FILE_PATH, "");
        int i2 = bundle.getInt("initialTime", 0);
        int i3 = bundle.getInt("hashCode");
        int i4 = bundle.getInt(AbstractMantoViewManager.VIEW_ID_KEY);
        String string2 = bundle.getString("data", "");
        int i5 = i2 * 1000;
        String string3 = bundle.getString("scaleType");
        String string4 = bundle.getString("poster", "");
        int i6 = bundle.getInt("mobilenetHintType", 1);
        try {
            c2.d(string2);
            c2.b(string3);
            c2.h(bundle.getBoolean("showFullscreenBtn", true));
            c2.o(i6);
            c2.m(bundle.getBoolean("showBasicControls", true));
            if (!TextUtils.isEmpty(string4)) {
                c2.j(string4);
            }
            if (!TextUtils.isEmpty(string)) {
                if (i5 > 0) {
                    c2.k(string, i5);
                } else {
                    c2.a(string);
                }
            }
            c2.q(bundle.getBoolean("muted", false));
        } catch (Exception e2) {
            e2.printStackTrace();
            c cVar = this.f6629g.get(Integer.valueOf(i3));
            if (cVar != null) {
                cVar.e(i4);
            }
        }
        return true;
    }

    @Override // com.jingdong.manto.jsapi.refact.video.JsApiVideoPlayer, com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public boolean onViewUpdate(MantoCore mantoCore, View view, Bundle bundle, MantoCallback mantoCallback) {
        return false;
    }
}
