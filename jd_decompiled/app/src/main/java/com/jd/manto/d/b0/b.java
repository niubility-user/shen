package com.jd.manto.d.b0;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.jd.dynamic.DYConstants;
import com.jd.manto.sdkimpl.live.v2.MantoLivePlayerV2;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.base.MantoCallback;
import com.jingdong.manto.jsapi.coverview.CoverViewContainer;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager;
import com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.jsapi.refact.live.JsApiLivePlayer;
import com.jingdong.manto.utils.MantoThreadUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import tv.danmaku.ijk.media.ext.report.ReportConstant;

/* loaded from: classes17.dex */
public class b extends JsApiLivePlayer {

    /* renamed from: g */
    private Map<Integer, c> f6623g = new HashMap();

    /* loaded from: classes17.dex */
    class a implements Runnable {

        /* renamed from: g */
        final /* synthetic */ View f6624g;

        /* renamed from: h */
        final /* synthetic */ Bundle f6625h;

        a(View view, Bundle bundle) {
            b.this = r1;
            this.f6624g = view;
            this.f6625h = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jd.manto.d.b0.a c2;
            View view = this.f6624g;
            if ((view instanceof CoverViewContainer) && (c2 = b.this.c(view, this.f6625h.getString("appid"))) != null) {
                String string = this.f6625h.getString("cm_type");
                String string2 = this.f6625h.getString("cm_data", "");
                string.hashCode();
                char c3 = '\uffff';
                switch (string.hashCode()) {
                    case -934426579:
                        if (string.equals(JsApiLivePlayer.CM_RESUME)) {
                            c3 = 0;
                            break;
                        }
                        break;
                    case -802181223:
                        if (string.equals("exitFullScreen")) {
                            c3 = 1;
                            break;
                        }
                        break;
                    case 3363353:
                        if (string.equals("mute")) {
                            c3 = 2;
                            break;
                        }
                        break;
                    case 3443508:
                        if (string.equals("play")) {
                            c3 = 3;
                            break;
                        }
                        break;
                    case 3540994:
                        if (string.equals("stop")) {
                            c3 = 4;
                            break;
                        }
                        break;
                    case 106440182:
                        if (string.equals("pause")) {
                            c3 = 5;
                            break;
                        }
                        break;
                    case 458133450:
                        if (string.equals("requestFullScreen")) {
                            c3 = 6;
                            break;
                        }
                        break;
                }
                switch (c3) {
                    case 0:
                        c2.resume();
                        return;
                    case 1:
                        c2.c();
                        return;
                    case 2:
                        c2.h(false);
                        return;
                    case 3:
                        c2.playIfNotPlaying();
                        return;
                    case 4:
                        c2.stop();
                        return;
                    case 5:
                        c2.pauseIfPlaying();
                        return;
                    case 6:
                        String replace = string2.replace("[", "").replace("]", "");
                        if (!TextUtils.equals(DYConstants.DY_I_90, replace) && !TextUtils.equals("-90", replace)) {
                            c2.setOrientation(1);
                        } else {
                            c2.setOrientation(6);
                        }
                        c2.f();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    static {
        Pattern.compile("[0-9]+");
    }

    public com.jd.manto.d.b0.a c(View view, String str) {
        return (com.jd.manto.d.b0.a) ((CoverViewContainer) view).convertTo(MantoLivePlayerV2.class);
    }

    private c d(int i2) {
        if (this.f6623g.get(Integer.valueOf(i2)) == null) {
            c cVar = new c(i2, this);
            this.f6623g.put(Integer.valueOf(i2), cVar);
            return cVar;
        }
        return this.f6623g.get(Integer.valueOf(i2));
    }

    private void e(MantoCore mantoCore, com.jd.manto.d.b0.a aVar, Bundle bundle) {
        int i2 = bundle.getInt("hashCode");
        if (bundle.containsKey("scaleType")) {
            aVar.b(bundle.getString("scaleType"));
        }
        if (bundle.containsKey("muted")) {
            aVar.h(!bundle.getBoolean("muted", false));
        }
        if (bundle.containsKey("autoplay")) {
            aVar.setAutoPlay(bundle.getBoolean("autoplay", false));
        }
        if (bundle.containsKey(MBaseKeyNames.KEY_ORIENTATION)) {
            if (TextUtils.equals(bundle.getString(MBaseKeyNames.KEY_ORIENTATION, DYConstants.DY_SCROLL_VERTICAL), DYConstants.DY_SCROLL_HORIZONTAL)) {
                aVar.setOrientation(6);
            } else {
                aVar.setOrientation(1);
            }
        }
        if (bundle.containsKey("soundMode")) {
            if (TextUtils.equals(bundle.getString("soundMode", "speaker"), "ear")) {
                f(false);
            } else {
                f(true);
            }
        }
        if (aVar instanceof MantoLivePlayerV2) {
            if (bundle.containsKey("picInPicPop") || bundle.containsKey("picInPicPush")) {
                boolean z = bundle.getBoolean("picInPicPop");
                boolean z2 = bundle.getBoolean("picInPicPush");
                aVar.j(z, z2);
                if (!z && !z2) {
                    removePicInPicPage(mantoCore, i2);
                } else {
                    addPicInPicPage(mantoCore, i2, z, z2);
                }
            }
        }
    }

    private void f(boolean z) {
        AudioManager audioManager;
        try {
            audioManager = (AudioManager) com.jingdong.a.g().getSystemService("audio");
        } catch (Throwable unused) {
            audioManager = null;
        }
        if (audioManager == null) {
            return;
        }
        if (z) {
            audioManager.setSpeakerphoneOn(true);
            audioManager.setMode(0);
            audioManager.setStreamVolume(3, audioManager.getStreamVolume(3), 0);
            return;
        }
        audioManager.setSpeakerphoneOn(false);
        if (Build.VERSION.SDK_INT >= 21) {
            audioManager.setMode(3);
            audioManager.setStreamVolume(3, audioManager.getStreamMaxVolume(3) / 2, 0);
            return;
        }
        audioManager.setMode(2);
        audioManager.setStreamVolume(3, audioManager.getStreamMaxVolume(3) / 2, 0);
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
        if (this.f6623g.get(Integer.valueOf(i2)) != null) {
            this.f6623g.remove(Integer.valueOf(i2));
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public View getCustomView(MantoCore mantoCore, Bundle bundle) {
        Activity activity = mantoCore != null ? mantoCore.getActivity() : null;
        if (mantoCore == null || activity == null) {
            return null;
        }
        return new CoverViewContainer(activity, new MantoLivePlayerV2(activity));
    }

    @Override // com.jingdong.manto.jsapi.refact.live.JsApiLivePlayer, com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public void handleMethod(String str, View view, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        MantoThreadUtils.runOnUIThread(new a(view, bundle));
    }

    @Override // com.jingdong.manto.jsapi.refact.live.JsApiLivePlayer, com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public boolean onViewInsert(Bundle bundle, View view, MantoCore mantoCore) {
        com.jd.manto.d.b0.a c2 = c(view, bundle.getString("appId"));
        int i2 = bundle.getInt("hashCode");
        int i3 = bundle.getInt(AbstractMantoViewManager.VIEW_ID_KEY);
        String string = bundle.getString("appId");
        String string2 = bundle.getString("path");
        try {
            c2.e(i3);
            c2.setAppId(string);
            c2.k(string2);
            c d = d(i2);
            if (d != null) {
                d.d(mantoCore, c2, i3);
            }
            e(mantoCore, c2, bundle);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            c cVar = this.f6623g.get(Integer.valueOf(i2));
            if (cVar != null) {
                cVar.e(i3);
                return true;
            }
            return true;
        }
    }

    @Override // com.jingdong.manto.jsapi.refact.live.JsApiLivePlayer, com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public boolean onViewRemove(Bundle bundle, View view, MantoCore mantoCore) {
        com.jd.manto.d.b0.a c2 = c(view, bundle.getString("appId"));
        int i2 = bundle.getInt(AbstractMantoViewManager.VIEW_ID_KEY);
        int i3 = bundle.getInt("hashCode");
        if (c2 != null) {
            c2.destroy();
        }
        c cVar = this.f6623g.get(Integer.valueOf(i3));
        if (cVar != null) {
            cVar.e(i2);
            return true;
        }
        return true;
    }

    @Override // com.jingdong.manto.jsapi.refact.live.JsApiLivePlayer, com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public boolean onViewUpdate(MantoCore mantoCore, View view, Bundle bundle) {
        com.jd.manto.d.b0.a c2 = c(view, bundle.getString("appId"));
        String string = bundle.getString(ReportConstant.CommonInfo.PLAY_URL, "");
        int i2 = bundle.getInt("hashCode");
        int i3 = bundle.getInt(AbstractMantoViewManager.VIEW_ID_KEY);
        try {
            c2.d(bundle.getString("data", ""));
            e(mantoCore, c2, bundle);
            if (TextUtils.isEmpty(string)) {
                return true;
            }
            c2.a(string);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            c cVar = this.f6623g.get(Integer.valueOf(i2));
            if (cVar != null) {
                cVar.e(i3);
                return true;
            }
            return true;
        }
    }

    @Override // com.jingdong.manto.jsapi.refact.live.JsApiLivePlayer, com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public boolean onViewUpdate(MantoCore mantoCore, View view, Bundle bundle, MantoCallback mantoCallback) {
        return false;
    }
}
