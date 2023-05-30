package com.jingdong.manto.jsapi.refact.udp;

import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes15.dex */
public class RemoteInfo {
    public String address;
    public String family;
    public int port;
    public int size;

    public JSONObject toJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID, this.address);
            jSONObject.put(IMediaPlayer.OnNativeInvokeListener.ARG_FAMILIY, this.family);
            jSONObject.put(IMediaPlayer.OnNativeInvokeListener.ARG_PORT, this.port);
            jSONObject.put(ApkDownloadTable.FIELD_SIZE, this.size);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
