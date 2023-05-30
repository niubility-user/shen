package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class Call {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected Call(long j2, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j2;
    }

    protected static long getCPtr(Call call) {
        if (call == null) {
            return 0L;
        }
        return call.swigCPtr;
    }

    public static Call lookup(int i2) {
        long Call_lookup = pjsua2JNI.Call_lookup(i2);
        if (Call_lookup == 0) {
            return null;
        }
        return new Call(Call_lookup, false);
    }

    public void answer(CallOpParam callOpParam) throws Exception {
        pjsua2JNI.Call_answer(this.swigCPtr, this, CallOpParam.getCPtr(callOpParam), callOpParam);
    }

    public synchronized void delete() {
        long j2 = this.swigCPtr;
        if (j2 != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_Call(j2);
            }
            this.swigCPtr = 0L;
        }
    }

    public void dialDtmf(String str) throws Exception {
        pjsua2JNI.Call_dialDtmf(this.swigCPtr, this, str);
    }

    public String dump(boolean z, String str) throws Exception {
        return pjsua2JNI.Call_dump(this.swigCPtr, this, z, str);
    }

    protected void finalize() {
        delete();
    }

    public int getId() {
        return pjsua2JNI.Call_getId(this.swigCPtr, this);
    }

    public CallInfo getInfo() throws Exception {
        return new CallInfo(pjsua2JNI.Call_getInfo(this.swigCPtr, this), true);
    }

    public MediaTransportInfo getMedTransportInfo(long j2) throws Exception {
        return new MediaTransportInfo(pjsua2JNI.Call_getMedTransportInfo(this.swigCPtr, this, j2), true);
    }

    public Media getMedia(long j2) {
        long Call_getMedia = pjsua2JNI.Call_getMedia(this.swigCPtr, this, j2);
        if (Call_getMedia == 0) {
            return null;
        }
        return new Media(Call_getMedia, false);
    }

    public pj_stun_nat_type getRemNatType() throws Exception {
        return pj_stun_nat_type.swigToEnum(pjsua2JNI.Call_getRemNatType(this.swigCPtr, this));
    }

    public StreamInfo getStreamInfo(long j2) throws Exception {
        return new StreamInfo(pjsua2JNI.Call_getStreamInfo(this.swigCPtr, this, j2), true);
    }

    public StreamStat getStreamStat(long j2) throws Exception {
        return new StreamStat(pjsua2JNI.Call_getStreamStat(this.swigCPtr, this, j2), true);
    }

    public SWIGTYPE_p_void getUserData() {
        long Call_getUserData = pjsua2JNI.Call_getUserData(this.swigCPtr, this);
        if (Call_getUserData == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(Call_getUserData, false);
    }

    public void hangup(CallOpParam callOpParam) throws Exception {
        pjsua2JNI.Call_hangup(this.swigCPtr, this, CallOpParam.getCPtr(callOpParam), callOpParam);
    }

    public boolean hasMedia() {
        return pjsua2JNI.Call_hasMedia(this.swigCPtr, this);
    }

    public boolean isActive() {
        return pjsua2JNI.Call_isActive(this.swigCPtr, this);
    }

    public void makeCall(String str, CallOpParam callOpParam) throws Exception {
        pjsua2JNI.Call_makeCall(this.swigCPtr, this, str, CallOpParam.getCPtr(callOpParam), callOpParam);
    }

    public void onCallMediaEvent(OnCallMediaEventParam onCallMediaEventParam) {
        if (getClass() == Call.class) {
            pjsua2JNI.Call_onCallMediaEvent(this.swigCPtr, this, OnCallMediaEventParam.getCPtr(onCallMediaEventParam), onCallMediaEventParam);
        } else {
            pjsua2JNI.Call_onCallMediaEventSwigExplicitCall(this.swigCPtr, this, OnCallMediaEventParam.getCPtr(onCallMediaEventParam), onCallMediaEventParam);
        }
    }

    public void onCallMediaState(OnCallMediaStateParam onCallMediaStateParam) {
        if (getClass() == Call.class) {
            pjsua2JNI.Call_onCallMediaState(this.swigCPtr, this, OnCallMediaStateParam.getCPtr(onCallMediaStateParam), onCallMediaStateParam);
        } else {
            pjsua2JNI.Call_onCallMediaStateSwigExplicitCall(this.swigCPtr, this, OnCallMediaStateParam.getCPtr(onCallMediaStateParam), onCallMediaStateParam);
        }
    }

    public void onCallMediaTransportState(OnCallMediaTransportStateParam onCallMediaTransportStateParam) {
        if (getClass() == Call.class) {
            pjsua2JNI.Call_onCallMediaTransportState(this.swigCPtr, this, OnCallMediaTransportStateParam.getCPtr(onCallMediaTransportStateParam), onCallMediaTransportStateParam);
        } else {
            pjsua2JNI.Call_onCallMediaTransportStateSwigExplicitCall(this.swigCPtr, this, OnCallMediaTransportStateParam.getCPtr(onCallMediaTransportStateParam), onCallMediaTransportStateParam);
        }
    }

    public pjsip_redirect_op onCallRedirected(OnCallRedirectedParam onCallRedirectedParam) {
        return pjsip_redirect_op.swigToEnum(getClass() == Call.class ? pjsua2JNI.Call_onCallRedirected(this.swigCPtr, this, OnCallRedirectedParam.getCPtr(onCallRedirectedParam), onCallRedirectedParam) : pjsua2JNI.Call_onCallRedirectedSwigExplicitCall(this.swigCPtr, this, OnCallRedirectedParam.getCPtr(onCallRedirectedParam), onCallRedirectedParam));
    }

    public void onCallReplaceRequest(OnCallReplaceRequestParam onCallReplaceRequestParam) {
        if (getClass() == Call.class) {
            pjsua2JNI.Call_onCallReplaceRequest(this.swigCPtr, this, OnCallReplaceRequestParam.getCPtr(onCallReplaceRequestParam), onCallReplaceRequestParam);
        } else {
            pjsua2JNI.Call_onCallReplaceRequestSwigExplicitCall(this.swigCPtr, this, OnCallReplaceRequestParam.getCPtr(onCallReplaceRequestParam), onCallReplaceRequestParam);
        }
    }

    public void onCallReplaced(OnCallReplacedParam onCallReplacedParam) {
        if (getClass() == Call.class) {
            pjsua2JNI.Call_onCallReplaced(this.swigCPtr, this, OnCallReplacedParam.getCPtr(onCallReplacedParam), onCallReplacedParam);
        } else {
            pjsua2JNI.Call_onCallReplacedSwigExplicitCall(this.swigCPtr, this, OnCallReplacedParam.getCPtr(onCallReplacedParam), onCallReplacedParam);
        }
    }

    public void onCallRxOffer(OnCallRxOfferParam onCallRxOfferParam) {
        if (getClass() == Call.class) {
            pjsua2JNI.Call_onCallRxOffer(this.swigCPtr, this, OnCallRxOfferParam.getCPtr(onCallRxOfferParam), onCallRxOfferParam);
        } else {
            pjsua2JNI.Call_onCallRxOfferSwigExplicitCall(this.swigCPtr, this, OnCallRxOfferParam.getCPtr(onCallRxOfferParam), onCallRxOfferParam);
        }
    }

    public void onCallSdpCreated(OnCallSdpCreatedParam onCallSdpCreatedParam) {
        if (getClass() == Call.class) {
            pjsua2JNI.Call_onCallSdpCreated(this.swigCPtr, this, OnCallSdpCreatedParam.getCPtr(onCallSdpCreatedParam), onCallSdpCreatedParam);
        } else {
            pjsua2JNI.Call_onCallSdpCreatedSwigExplicitCall(this.swigCPtr, this, OnCallSdpCreatedParam.getCPtr(onCallSdpCreatedParam), onCallSdpCreatedParam);
        }
    }

    public void onCallState(OnCallStateParam onCallStateParam) {
        if (getClass() == Call.class) {
            pjsua2JNI.Call_onCallState(this.swigCPtr, this, OnCallStateParam.getCPtr(onCallStateParam), onCallStateParam);
        } else {
            pjsua2JNI.Call_onCallStateSwigExplicitCall(this.swigCPtr, this, OnCallStateParam.getCPtr(onCallStateParam), onCallStateParam);
        }
    }

    public void onCallTransferRequest(OnCallTransferRequestParam onCallTransferRequestParam) {
        if (getClass() == Call.class) {
            pjsua2JNI.Call_onCallTransferRequest(this.swigCPtr, this, OnCallTransferRequestParam.getCPtr(onCallTransferRequestParam), onCallTransferRequestParam);
        } else {
            pjsua2JNI.Call_onCallTransferRequestSwigExplicitCall(this.swigCPtr, this, OnCallTransferRequestParam.getCPtr(onCallTransferRequestParam), onCallTransferRequestParam);
        }
    }

    public void onCallTransferStatus(OnCallTransferStatusParam onCallTransferStatusParam) {
        if (getClass() == Call.class) {
            pjsua2JNI.Call_onCallTransferStatus(this.swigCPtr, this, OnCallTransferStatusParam.getCPtr(onCallTransferStatusParam), onCallTransferStatusParam);
        } else {
            pjsua2JNI.Call_onCallTransferStatusSwigExplicitCall(this.swigCPtr, this, OnCallTransferStatusParam.getCPtr(onCallTransferStatusParam), onCallTransferStatusParam);
        }
    }

    public void onCallTsxState(OnCallTsxStateParam onCallTsxStateParam) {
        if (getClass() == Call.class) {
            pjsua2JNI.Call_onCallTsxState(this.swigCPtr, this, OnCallTsxStateParam.getCPtr(onCallTsxStateParam), onCallTsxStateParam);
        } else {
            pjsua2JNI.Call_onCallTsxStateSwigExplicitCall(this.swigCPtr, this, OnCallTsxStateParam.getCPtr(onCallTsxStateParam), onCallTsxStateParam);
        }
    }

    public void onCallTxOffer(OnCallTxOfferParam onCallTxOfferParam) {
        if (getClass() == Call.class) {
            pjsua2JNI.Call_onCallTxOffer(this.swigCPtr, this, OnCallTxOfferParam.getCPtr(onCallTxOfferParam), onCallTxOfferParam);
        } else {
            pjsua2JNI.Call_onCallTxOfferSwigExplicitCall(this.swigCPtr, this, OnCallTxOfferParam.getCPtr(onCallTxOfferParam), onCallTxOfferParam);
        }
    }

    public void onCreateMediaTransport(OnCreateMediaTransportParam onCreateMediaTransportParam) {
        if (getClass() == Call.class) {
            pjsua2JNI.Call_onCreateMediaTransport(this.swigCPtr, this, OnCreateMediaTransportParam.getCPtr(onCreateMediaTransportParam), onCreateMediaTransportParam);
        } else {
            pjsua2JNI.Call_onCreateMediaTransportSwigExplicitCall(this.swigCPtr, this, OnCreateMediaTransportParam.getCPtr(onCreateMediaTransportParam), onCreateMediaTransportParam);
        }
    }

    public void onCreateMediaTransportSrtp(OnCreateMediaTransportSrtpParam onCreateMediaTransportSrtpParam) {
        if (getClass() == Call.class) {
            pjsua2JNI.Call_onCreateMediaTransportSrtp(this.swigCPtr, this, OnCreateMediaTransportSrtpParam.getCPtr(onCreateMediaTransportSrtpParam), onCreateMediaTransportSrtpParam);
        } else {
            pjsua2JNI.Call_onCreateMediaTransportSrtpSwigExplicitCall(this.swigCPtr, this, OnCreateMediaTransportSrtpParam.getCPtr(onCreateMediaTransportSrtpParam), onCreateMediaTransportSrtpParam);
        }
    }

    public void onDtmfDigit(OnDtmfDigitParam onDtmfDigitParam) {
        if (getClass() == Call.class) {
            pjsua2JNI.Call_onDtmfDigit(this.swigCPtr, this, OnDtmfDigitParam.getCPtr(onDtmfDigitParam), onDtmfDigitParam);
        } else {
            pjsua2JNI.Call_onDtmfDigitSwigExplicitCall(this.swigCPtr, this, OnDtmfDigitParam.getCPtr(onDtmfDigitParam), onDtmfDigitParam);
        }
    }

    public void onInstantMessage(OnInstantMessageParam onInstantMessageParam) {
        if (getClass() == Call.class) {
            pjsua2JNI.Call_onInstantMessage(this.swigCPtr, this, OnInstantMessageParam.getCPtr(onInstantMessageParam), onInstantMessageParam);
        } else {
            pjsua2JNI.Call_onInstantMessageSwigExplicitCall(this.swigCPtr, this, OnInstantMessageParam.getCPtr(onInstantMessageParam), onInstantMessageParam);
        }
    }

    public void onInstantMessageStatus(OnInstantMessageStatusParam onInstantMessageStatusParam) {
        if (getClass() == Call.class) {
            pjsua2JNI.Call_onInstantMessageStatus(this.swigCPtr, this, OnInstantMessageStatusParam.getCPtr(onInstantMessageStatusParam), onInstantMessageStatusParam);
        } else {
            pjsua2JNI.Call_onInstantMessageStatusSwigExplicitCall(this.swigCPtr, this, OnInstantMessageStatusParam.getCPtr(onInstantMessageStatusParam), onInstantMessageStatusParam);
        }
    }

    public void onStreamCreated(OnStreamCreatedParam onStreamCreatedParam) {
        if (getClass() == Call.class) {
            pjsua2JNI.Call_onStreamCreated(this.swigCPtr, this, OnStreamCreatedParam.getCPtr(onStreamCreatedParam), onStreamCreatedParam);
        } else {
            pjsua2JNI.Call_onStreamCreatedSwigExplicitCall(this.swigCPtr, this, OnStreamCreatedParam.getCPtr(onStreamCreatedParam), onStreamCreatedParam);
        }
    }

    public void onStreamDestroyed(OnStreamDestroyedParam onStreamDestroyedParam) {
        if (getClass() == Call.class) {
            pjsua2JNI.Call_onStreamDestroyed(this.swigCPtr, this, OnStreamDestroyedParam.getCPtr(onStreamDestroyedParam), onStreamDestroyedParam);
        } else {
            pjsua2JNI.Call_onStreamDestroyedSwigExplicitCall(this.swigCPtr, this, OnStreamDestroyedParam.getCPtr(onStreamDestroyedParam), onStreamDestroyedParam);
        }
    }

    public void onTypingIndication(OnTypingIndicationParam onTypingIndicationParam) {
        if (getClass() == Call.class) {
            pjsua2JNI.Call_onTypingIndication(this.swigCPtr, this, OnTypingIndicationParam.getCPtr(onTypingIndicationParam), onTypingIndicationParam);
        } else {
            pjsua2JNI.Call_onTypingIndicationSwigExplicitCall(this.swigCPtr, this, OnTypingIndicationParam.getCPtr(onTypingIndicationParam), onTypingIndicationParam);
        }
    }

    public void processMediaUpdate(OnCallMediaStateParam onCallMediaStateParam) {
        pjsua2JNI.Call_processMediaUpdate(this.swigCPtr, this, OnCallMediaStateParam.getCPtr(onCallMediaStateParam), onCallMediaStateParam);
    }

    public void processRedirect(pjsip_redirect_op pjsip_redirect_opVar) throws Exception {
        pjsua2JNI.Call_processRedirect(this.swigCPtr, this, pjsip_redirect_opVar.swigValue());
    }

    public void processStateChange(OnCallStateParam onCallStateParam) {
        pjsua2JNI.Call_processStateChange(this.swigCPtr, this, OnCallStateParam.getCPtr(onCallStateParam), onCallStateParam);
    }

    public void reinvite(CallOpParam callOpParam) throws Exception {
        pjsua2JNI.Call_reinvite(this.swigCPtr, this, CallOpParam.getCPtr(callOpParam), callOpParam);
    }

    public pjsip_dialog_cap_status remoteHasCap(int i2, String str, String str2) {
        return pjsip_dialog_cap_status.swigToEnum(pjsua2JNI.Call_remoteHasCap(this.swigCPtr, this, i2, str, str2));
    }

    public void sendInstantMessage(SendInstantMessageParam sendInstantMessageParam) throws Exception {
        pjsua2JNI.Call_sendInstantMessage(this.swigCPtr, this, SendInstantMessageParam.getCPtr(sendInstantMessageParam), sendInstantMessageParam);
    }

    public void sendRequest(CallSendRequestParam callSendRequestParam) throws Exception {
        pjsua2JNI.Call_sendRequest(this.swigCPtr, this, CallSendRequestParam.getCPtr(callSendRequestParam), callSendRequestParam);
    }

    public void sendTypingIndication(SendTypingIndicationParam sendTypingIndicationParam) throws Exception {
        pjsua2JNI.Call_sendTypingIndication(this.swigCPtr, this, SendTypingIndicationParam.getCPtr(sendTypingIndicationParam), sendTypingIndicationParam);
    }

    public void setHold(CallOpParam callOpParam) throws Exception {
        pjsua2JNI.Call_setHold(this.swigCPtr, this, CallOpParam.getCPtr(callOpParam), callOpParam);
    }

    public void setUserData(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.Call_setUserData(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        pjsua2JNI.Call_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        pjsua2JNI.Call_change_ownership(this, this.swigCPtr, true);
    }

    public void update(CallOpParam callOpParam) throws Exception {
        pjsua2JNI.Call_update(this.swigCPtr, this, CallOpParam.getCPtr(callOpParam), callOpParam);
    }

    public void xfer(String str, CallOpParam callOpParam) throws Exception {
        pjsua2JNI.Call_xfer(this.swigCPtr, this, str, CallOpParam.getCPtr(callOpParam), callOpParam);
    }

    public void xferReplaces(Call call, CallOpParam callOpParam) throws Exception {
        pjsua2JNI.Call_xferReplaces(this.swigCPtr, this, getCPtr(call), call, CallOpParam.getCPtr(callOpParam), callOpParam);
    }

    public Call(Account account, int i2) {
        this(pjsua2JNI.new_Call__SWIG_0(Account.getCPtr(account), account, i2), true);
        pjsua2JNI.Call_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    public Call(Account account) {
        this(pjsua2JNI.new_Call__SWIG_1(Account.getCPtr(account), account), true);
        pjsua2JNI.Call_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }
}
