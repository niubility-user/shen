package com.jingdong.manto.jsapi.refact.live;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.jd.dynamic.DYConstants;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.base.MantoCallback;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import tv.danmaku.ijk.media.ext.report.ReportConstant;

/* loaded from: classes15.dex */
public class JsApiLivePlayer extends AbstractMantoViewManager {
    public static final String CM_DATA = "cm_data";
    public static final String CM_EXIT_FULLSCREEN = "exitFullScreen";
    public static final String CM_MUTE = "mute";
    public static final String CM_PAUSE = "pause";
    public static final String CM_PLAY = "play";
    public static final String CM_REQUEST_FULLSCREEN = "requestFullScreen";
    public static final String CM_RESUME = "resume";
    public static final String CM_STOP = "stop";
    public static final String CM_TYPE = "cm_type";
    public static final String LIVE_PLAYER_ID = "livePlayerId";
    private static final String OPERATE_LIVE_PLAYER = "operateLivePlayer";

    private Bundle getBundle(JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putInt(AbstractMantoViewManager.VIEW_ID_KEY, jSONObject.optInt(LIVE_PLAYER_ID));
        if (jSONObject.has("objectFit")) {
            bundle.putString("scaleType", jSONObject.optString("objectFit", "contain"));
        }
        if (jSONObject.has("muted")) {
            bundle.putBoolean("muted", jSONObject.optBoolean("muted", false));
        }
        if (jSONObject.has("autoplay")) {
            bundle.putBoolean("autoplay", jSONObject.optBoolean("autoplay", false));
        }
        if (jSONObject.has("mode")) {
            bundle.putString("mode", jSONObject.optString("mode", WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_LIVE));
        }
        if (jSONObject.has(ReportConstant.CommonInfo.PLAY_URL)) {
            bundle.putString(ReportConstant.CommonInfo.PLAY_URL, jSONObject.optString(ReportConstant.CommonInfo.PLAY_URL, ""));
        }
        if (jSONObject.has(MBaseKeyNames.KEY_ORIENTATION)) {
            bundle.putString(MBaseKeyNames.KEY_ORIENTATION, jSONObject.optString(MBaseKeyNames.KEY_ORIENTATION, DYConstants.DY_SCROLL_VERTICAL));
        }
        if (jSONObject.has("soundMode")) {
            bundle.putString("soundMode", jSONObject.optString("soundMode", "speaker"));
        }
        if (jSONObject.has("debug")) {
            bundle.putBoolean("debug", jSONObject.optBoolean("debug", false));
        }
        if (jSONObject.has("backgroundMute")) {
            bundle.putBoolean("backgroundMute", jSONObject.optBoolean("backgroundMute", false));
        }
        if (jSONObject.has("minCache")) {
            bundle.putInt("minCache", jSONObject.optInt("minCache", 1));
        }
        if (jSONObject.has("maxCache")) {
            bundle.putInt("maxCache", jSONObject.optInt("maxCache", 3));
        }
        bundle.putString("data", jSONObject.optString("data", ""));
        if (jSONObject.has("pictureInPictureMode")) {
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("pictureInPictureMode");
                boolean z = false;
                boolean z2 = false;
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    if (TextUtils.equals("pop", jSONArray.getString(i2))) {
                        z = true;
                    }
                    if (TextUtils.equals("push", jSONArray.getString(i2))) {
                        z2 = true;
                    }
                }
                bundle.putBoolean("picInPicPop", z);
                bundle.putBoolean("picInPicPush", z2);
            } catch (Exception unused) {
            }
        }
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public View getCustomView(MantoCore mantoCore) {
        return null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final String getModuleName() {
        return "MantoLiveView";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final String getViewName() {
        return "LivePlayer";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final Bundle handleInsertData(MantoCore mantoCore, JSONObject jSONObject) {
        return getBundle(jSONObject);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public void handleMethod(String str, View view, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final Bundle handleRemoveData(MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putInt(AbstractMantoViewManager.VIEW_ID_KEY, jSONObject.optInt(LIVE_PLAYER_ID));
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final Bundle handleUpdateData(MantoCore mantoCore, JSONObject jSONObject) {
        return getBundle(jSONObject);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        int optInt = jSONObject.optInt(LIVE_PLAYER_ID);
        String optString = jSONObject.optString("type");
        String optString2 = jSONObject.optString("data");
        bundle.putInt(AbstractMantoViewManager.VIEW_ID_KEY, optInt);
        bundle.putString("cm_type", optString);
        bundle.putString("cm_data", optString2);
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    protected final void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod(OPERATE_LIVE_PLAYER, 5));
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public boolean onViewInsert(Bundle bundle, View view, MantoCore mantoCore) {
        return false;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public boolean onViewRemove(Bundle bundle, View view, MantoCore mantoCore) {
        return false;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public boolean onViewUpdate(MantoCore mantoCore, View view, Bundle bundle) {
        return false;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public boolean onViewUpdate(MantoCore mantoCore, View view, Bundle bundle, MantoCallback mantoCallback) {
        return false;
    }
}
