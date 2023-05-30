package com.jingdong.manto.jsapi.refact.tcp;

import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes15.dex */
public class LocalInfo {
    public String address;
    public String family;
    public int port;

    public JSONObject toJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID, this.address);
            jSONObject.put(IMediaPlayer.OnNativeInvokeListener.ARG_FAMILIY, this.family);
            jSONObject.put(IMediaPlayer.OnNativeInvokeListener.ARG_PORT, this.port);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
