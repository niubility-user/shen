package com.jingdong.manto.jsapi.refact.video;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.common.jdreactFramework.views.pureVideo.JDPureVideoManager;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.base.MantoCallback;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.t.c;
import com.jingdong.manto.t.d;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.r;
import com.tencent.smtt.sdk.TbsReaderView;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class JsApiVideoPlayer extends AbstractMantoViewManager {
    public static final String CM_DATA = "cm_data";
    public static final String CM_EXIT_FULLSCREEN = "exitFullScreen";
    public static final String CM_PAUSE = "pause";
    public static final String CM_PLAY = "play";
    public static final String CM_PLAY_RATE = "playbackRate";
    public static final String CM_REQUEST_FULLSCREEN = "requestFullScreen";
    public static final String CM_SEEK = "seek";
    public static final String CM_STOP = "stop";
    public static final String CM_TYPE = "cm_type";
    private static final String OPERATE_VIDEO_PLAYER = "operateVideoPlayer";
    public static final String VIDEO_PLAYER_ID = "videoPlayerId";

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public View getCustomView(MantoCore mantoCore) {
        return null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public View getCustomView(MantoCore mantoCore, Bundle bundle) {
        return null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final String getModuleName() {
        return "MantoVideoView";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final String getViewName() {
        return "VideoPlayer";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final Bundle handleInsertData(MantoCore mantoCore, JSONObject jSONObject) {
        int optInt = jSONObject.optInt(VIDEO_PLAYER_ID);
        String optString = jSONObject.optString("objectFit", "contains");
        boolean optBoolean = jSONObject.optBoolean("muted", false);
        boolean optBoolean2 = jSONObject.optBoolean("autoplay", false);
        boolean optBoolean3 = jSONObject.optBoolean("showBasicControls", true);
        boolean optBoolean4 = jSONObject.optBoolean(JDPureVideoManager.SourceKey.LOOP, false);
        boolean optBoolean5 = jSONObject.optBoolean("showFullScreenBtn", true);
        String optString2 = jSONObject.optString("poster", "");
        boolean optBoolean6 = jSONObject.optBoolean("useCache", false);
        boolean optBoolean7 = jSONObject.optBoolean("showSpeedBtn", true);
        Bundle bundle = new Bundle();
        bundle.putInt(AbstractMantoViewManager.VIEW_ID_KEY, optInt);
        bundle.putBoolean("muted", optBoolean);
        bundle.putString("scaleType", optString);
        bundle.putBoolean("autoplay", optBoolean2);
        bundle.putBoolean("showBasicControls", optBoolean3);
        bundle.putBoolean("showFullscreenBtn", optBoolean5);
        bundle.putBoolean(JDPureVideoManager.SourceKey.LOOP, optBoolean4);
        bundle.putString("poster", optString2);
        bundle.putBoolean("useCache", optBoolean6);
        bundle.putBoolean("showSpeedBtn", optBoolean7);
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public void handleMethod(String str, View view, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final Bundle handleRemoveData(MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putInt(AbstractMantoViewManager.VIEW_ID_KEY, jSONObject.optInt(VIDEO_PLAYER_ID));
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public final Bundle handleUpdateData(MantoCore mantoCore, JSONObject jSONObject) {
        String optString = jSONObject.optString(TbsReaderView.KEY_FILE_PATH);
        String optString2 = jSONObject.optString("objectFit", "contains");
        boolean optBoolean = jSONObject.optBoolean("muted", false);
        boolean optBoolean2 = jSONObject.optBoolean("autoplay", false);
        int optInt = jSONObject.optInt("initialTime", 0);
        boolean optBoolean3 = jSONObject.optBoolean("showBasicControls", true);
        boolean optBoolean4 = jSONObject.optBoolean("showFullScreenBtn", true);
        String optString3 = jSONObject.optString("data", "");
        String optString4 = jSONObject.optString("poster", "");
        int optInt2 = jSONObject.optInt("mobilenetHintType", 1);
        Bundle bundle = new Bundle();
        bundle.putInt(AbstractMantoViewManager.VIEW_ID_KEY, jSONObject.optInt(VIDEO_PLAYER_ID));
        bundle.putString(TbsReaderView.KEY_FILE_PATH, optString);
        bundle.putBoolean("muted", optBoolean);
        bundle.putString("scaleType", optString2);
        bundle.putBoolean("autoplay", optBoolean2);
        bundle.putInt("initialTime", optInt);
        bundle.putBoolean("showBasicControls", optBoolean3);
        bundle.putBoolean("showFullscreenBtn", optBoolean4);
        bundle.putString("data", optString3);
        bundle.putString("poster", optString4);
        bundle.putInt("mobilenetHintType", optInt2);
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        int optInt = jSONObject.optInt(VIDEO_PLAYER_ID);
        String optString = jSONObject.optString("type");
        String optString2 = jSONObject.optString("data");
        bundle.putInt(AbstractMantoViewManager.VIEW_ID_KEY, optInt);
        bundle.putString("cm_type", optString);
        bundle.putString("cm_data", optString2);
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    protected final void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod(OPERATE_VIDEO_PLAYER, 5));
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
        if (bundle == null || !bundle.containsKey(TbsReaderView.KEY_FILE_PATH)) {
            return false;
        }
        String string = bundle.getString(TbsReaderView.KEY_FILE_PATH);
        if (string.startsWith("jdfile://")) {
            String string2 = bundle.getString("appUniqueId", "");
            MantoLog.d("better", "appUniqueId: " + string2);
            d g2 = c.g(string2, string);
            if (g2 == null || TextUtils.isEmpty(g2.b) || !r.a(g2.b)) {
                return false;
            }
            bundle.putString(TbsReaderView.KEY_FILE_PATH, g2.b);
            return false;
        }
        return false;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
    public boolean onViewUpdate(MantoCore mantoCore, View view, Bundle bundle, MantoCallback mantoCallback) {
        return false;
    }
}
