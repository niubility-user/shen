package com.jingdong.common.jdreactFramework.modules.uphone_sdk;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import org.pjsip.pjsua2.AudioMedia;
import org.pjsip.pjsua2.Call;
import org.pjsip.pjsua2.CallInfo;
import org.pjsip.pjsua2.CallMediaInfo;
import org.pjsip.pjsua2.CallMediaInfoVector;
import org.pjsip.pjsua2.Endpoint;
import org.pjsip.pjsua2.OnCallMediaStateParam;
import org.pjsip.pjsua2.OnCallStateParam;
import org.pjsip.pjsua2.OnInstantMessageParam;
import org.pjsip.pjsua2.pjmedia_type;
import org.pjsip.pjsua2.pjsip_inv_state;
import org.pjsip.pjsua2.pjsua_call_media_status;

/* loaded from: classes5.dex */
public class UphoneCall extends Call {
    public JDReactNativeUphoneModule backer;
    public Endpoint m_ep;

    public UphoneCall(UphoneAccount uphoneAccount, int i2, Endpoint endpoint) {
        super(uphoneAccount, i2);
        this.backer = null;
        this.m_ep = endpoint;
    }

    @Override // org.pjsip.pjsua2.Call
    public void onCallMediaState(OnCallMediaStateParam onCallMediaStateParam) {
        try {
            CallMediaInfoVector media = getInfo().getMedia();
            int i2 = 0;
            while (true) {
                long j2 = i2;
                if (j2 >= media.size()) {
                    return;
                }
                CallMediaInfo callMediaInfo = media.get(i2);
                if (callMediaInfo.getType() == pjmedia_type.PJMEDIA_TYPE_AUDIO && (callMediaInfo.getStatus() == pjsua_call_media_status.PJSUA_CALL_MEDIA_ACTIVE || callMediaInfo.getStatus() == pjsua_call_media_status.PJSUA_CALL_MEDIA_REMOTE_HOLD)) {
                    AudioMedia typecastFromMedia = AudioMedia.typecastFromMedia(getMedia(j2));
                    try {
                        Endpoint endpoint = this.m_ep;
                        if (endpoint != null) {
                            endpoint.audDevManager().getCaptureDevMedia().startTransmit(typecastFromMedia);
                            typecastFromMedia.startTransmit(this.m_ep.audDevManager().getPlaybackDevMedia());
                        }
                    } catch (Exception unused) {
                    }
                }
                i2++;
            }
        } catch (Exception unused2) {
        }
    }

    @Override // org.pjsip.pjsua2.Call
    public void onCallState(OnCallStateParam onCallStateParam) {
        super.onCallState(onCallStateParam);
        WritableMap createMap = Arguments.createMap();
        createMap.putString("messageType", "CALL");
        try {
            CallInfo info = getInfo();
            if (info.getState() == pjsip_inv_state.PJSIP_INV_STATE_CONNECTING) {
                this.backer.invite_callback("connecting", createMap);
            } else if (info.getState() == pjsip_inv_state.PJSIP_INV_STATE_CONFIRMED) {
                createMap.putString("messageText", "connected");
                this.backer.invite_callback("connected", createMap);
            } else if (info.getState() == pjsip_inv_state.PJSIP_INV_STATE_DISCONNECTED) {
                createMap.putString("messageText", "Hangup");
                this.backer.hangup_callback("disconnected", createMap);
            }
        } catch (Exception unused) {
            if (this.backer != null) {
                createMap.putString("messageText", "Hangup");
                this.backer.hangup_callback("disconnected", createMap);
            }
        }
    }

    @Override // org.pjsip.pjsua2.Call
    public void onInstantMessage(OnInstantMessageParam onInstantMessageParam) {
        super.onInstantMessage(onInstantMessageParam);
        String msgBody = onInstantMessageParam.getMsgBody();
        JDReactNativeUphoneModule jDReactNativeUphoneModule = this.backer;
        if (jDReactNativeUphoneModule != null) {
            jDReactNativeUphoneModule.recv_msg(msgBody);
        }
    }
}
