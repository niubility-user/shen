package org.pjsip.pjsua2;

/* loaded from: classes.dex */
public class pjsua2JNI {
    static {
        swig_module_init();
    }

    public static final native int AccountCallConfig_holdType_get(long j2, AccountCallConfig accountCallConfig);

    public static final native void AccountCallConfig_holdType_set(long j2, AccountCallConfig accountCallConfig, int i2);

    public static final native int AccountCallConfig_prackUse_get(long j2, AccountCallConfig accountCallConfig);

    public static final native void AccountCallConfig_prackUse_set(long j2, AccountCallConfig accountCallConfig, int i2);

    public static final native long AccountCallConfig_timerMinSESec_get(long j2, AccountCallConfig accountCallConfig);

    public static final native void AccountCallConfig_timerMinSESec_set(long j2, AccountCallConfig accountCallConfig, long j3);

    public static final native long AccountCallConfig_timerSessExpiresSec_get(long j2, AccountCallConfig accountCallConfig);

    public static final native void AccountCallConfig_timerSessExpiresSec_set(long j2, AccountCallConfig accountCallConfig, long j3);

    public static final native int AccountCallConfig_timerUse_get(long j2, AccountCallConfig accountCallConfig);

    public static final native void AccountCallConfig_timerUse_set(long j2, AccountCallConfig accountCallConfig, int i2);

    public static final native long AccountConfig_callConfig_get(long j2, AccountConfig accountConfig);

    public static final native void AccountConfig_callConfig_set(long j2, AccountConfig accountConfig, long j3, AccountCallConfig accountCallConfig);

    public static final native String AccountConfig_idUri_get(long j2, AccountConfig accountConfig);

    public static final native void AccountConfig_idUri_set(long j2, AccountConfig accountConfig, String str);

    public static final native long AccountConfig_ipChangeConfig_get(long j2, AccountConfig accountConfig);

    public static final native void AccountConfig_ipChangeConfig_set(long j2, AccountConfig accountConfig, long j3, AccountIpChangeConfig accountIpChangeConfig);

    public static final native long AccountConfig_mediaConfig_get(long j2, AccountConfig accountConfig);

    public static final native void AccountConfig_mediaConfig_set(long j2, AccountConfig accountConfig, long j3, AccountMediaConfig accountMediaConfig);

    public static final native long AccountConfig_mwiConfig_get(long j2, AccountConfig accountConfig);

    public static final native void AccountConfig_mwiConfig_set(long j2, AccountConfig accountConfig, long j3, AccountMwiConfig accountMwiConfig);

    public static final native long AccountConfig_natConfig_get(long j2, AccountConfig accountConfig);

    public static final native void AccountConfig_natConfig_set(long j2, AccountConfig accountConfig, long j3, AccountNatConfig accountNatConfig);

    public static final native long AccountConfig_presConfig_get(long j2, AccountConfig accountConfig);

    public static final native void AccountConfig_presConfig_set(long j2, AccountConfig accountConfig, long j3, AccountPresConfig accountPresConfig);

    public static final native int AccountConfig_priority_get(long j2, AccountConfig accountConfig);

    public static final native void AccountConfig_priority_set(long j2, AccountConfig accountConfig, int i2);

    public static final native long AccountConfig_regConfig_get(long j2, AccountConfig accountConfig);

    public static final native void AccountConfig_regConfig_set(long j2, AccountConfig accountConfig, long j3, AccountRegConfig accountRegConfig);

    public static final native long AccountConfig_sipConfig_get(long j2, AccountConfig accountConfig);

    public static final native void AccountConfig_sipConfig_set(long j2, AccountConfig accountConfig, long j3, AccountSipConfig accountSipConfig);

    public static final native int AccountInfo_id_get(long j2, AccountInfo accountInfo);

    public static final native void AccountInfo_id_set(long j2, AccountInfo accountInfo, int i2);

    public static final native boolean AccountInfo_isDefault_get(long j2, AccountInfo accountInfo);

    public static final native void AccountInfo_isDefault_set(long j2, AccountInfo accountInfo, boolean z);

    public static final native String AccountInfo_onlineStatusText_get(long j2, AccountInfo accountInfo);

    public static final native void AccountInfo_onlineStatusText_set(long j2, AccountInfo accountInfo, String str);

    public static final native boolean AccountInfo_onlineStatus_get(long j2, AccountInfo accountInfo);

    public static final native void AccountInfo_onlineStatus_set(long j2, AccountInfo accountInfo, boolean z);

    public static final native int AccountInfo_regExpiresSec_get(long j2, AccountInfo accountInfo);

    public static final native void AccountInfo_regExpiresSec_set(long j2, AccountInfo accountInfo, int i2);

    public static final native boolean AccountInfo_regIsActive_get(long j2, AccountInfo accountInfo);

    public static final native void AccountInfo_regIsActive_set(long j2, AccountInfo accountInfo, boolean z);

    public static final native boolean AccountInfo_regIsConfigured_get(long j2, AccountInfo accountInfo);

    public static final native void AccountInfo_regIsConfigured_set(long j2, AccountInfo accountInfo, boolean z);

    public static final native int AccountInfo_regLastErr_get(long j2, AccountInfo accountInfo);

    public static final native void AccountInfo_regLastErr_set(long j2, AccountInfo accountInfo, int i2);

    public static final native String AccountInfo_regStatusText_get(long j2, AccountInfo accountInfo);

    public static final native void AccountInfo_regStatusText_set(long j2, AccountInfo accountInfo, String str);

    public static final native int AccountInfo_regStatus_get(long j2, AccountInfo accountInfo);

    public static final native void AccountInfo_regStatus_set(long j2, AccountInfo accountInfo, int i2);

    public static final native String AccountInfo_uri_get(long j2, AccountInfo accountInfo);

    public static final native void AccountInfo_uri_set(long j2, AccountInfo accountInfo, String str);

    public static final native boolean AccountIpChangeConfig_hangupCalls_get(long j2, AccountIpChangeConfig accountIpChangeConfig);

    public static final native void AccountIpChangeConfig_hangupCalls_set(long j2, AccountIpChangeConfig accountIpChangeConfig, boolean z);

    public static final native long AccountIpChangeConfig_reinviteFlags_get(long j2, AccountIpChangeConfig accountIpChangeConfig);

    public static final native void AccountIpChangeConfig_reinviteFlags_set(long j2, AccountIpChangeConfig accountIpChangeConfig, long j3);

    public static final native boolean AccountIpChangeConfig_shutdownTp_get(long j2, AccountIpChangeConfig accountIpChangeConfig);

    public static final native void AccountIpChangeConfig_shutdownTp_set(long j2, AccountIpChangeConfig accountIpChangeConfig, boolean z);

    public static final native int AccountMediaConfig_ipv6Use_get(long j2, AccountMediaConfig accountMediaConfig);

    public static final native void AccountMediaConfig_ipv6Use_set(long j2, AccountMediaConfig accountMediaConfig, int i2);

    public static final native boolean AccountMediaConfig_lockCodecEnabled_get(long j2, AccountMediaConfig accountMediaConfig);

    public static final native void AccountMediaConfig_lockCodecEnabled_set(long j2, AccountMediaConfig accountMediaConfig, boolean z);

    public static final native int AccountMediaConfig_srtpSecureSignaling_get(long j2, AccountMediaConfig accountMediaConfig);

    public static final native void AccountMediaConfig_srtpSecureSignaling_set(long j2, AccountMediaConfig accountMediaConfig, int i2);

    public static final native int AccountMediaConfig_srtpUse_get(long j2, AccountMediaConfig accountMediaConfig);

    public static final native void AccountMediaConfig_srtpUse_set(long j2, AccountMediaConfig accountMediaConfig, int i2);

    public static final native boolean AccountMediaConfig_streamKaEnabled_get(long j2, AccountMediaConfig accountMediaConfig);

    public static final native void AccountMediaConfig_streamKaEnabled_set(long j2, AccountMediaConfig accountMediaConfig, boolean z);

    public static final native long AccountMediaConfig_transportConfig_get(long j2, AccountMediaConfig accountMediaConfig);

    public static final native void AccountMediaConfig_transportConfig_set(long j2, AccountMediaConfig accountMediaConfig, long j3, TransportConfig transportConfig);

    public static final native boolean AccountMwiConfig_enabled_get(long j2, AccountMwiConfig accountMwiConfig);

    public static final native void AccountMwiConfig_enabled_set(long j2, AccountMwiConfig accountMwiConfig, boolean z);

    public static final native long AccountMwiConfig_expirationSec_get(long j2, AccountMwiConfig accountMwiConfig);

    public static final native void AccountMwiConfig_expirationSec_set(long j2, AccountMwiConfig accountMwiConfig, long j3);

    public static final native int AccountNatConfig_contactRewriteMethod_get(long j2, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_contactRewriteMethod_set(long j2, AccountNatConfig accountNatConfig, int i2);

    public static final native int AccountNatConfig_contactRewriteUse_get(long j2, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_contactRewriteUse_set(long j2, AccountNatConfig accountNatConfig, int i2);

    public static final native int AccountNatConfig_contactUseSrcPort_get(long j2, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_contactUseSrcPort_set(long j2, AccountNatConfig accountNatConfig, int i2);

    public static final native boolean AccountNatConfig_iceAggressiveNomination_get(long j2, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_iceAggressiveNomination_set(long j2, AccountNatConfig accountNatConfig, boolean z);

    public static final native boolean AccountNatConfig_iceAlwaysUpdate_get(long j2, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_iceAlwaysUpdate_set(long j2, AccountNatConfig accountNatConfig, boolean z);

    public static final native boolean AccountNatConfig_iceEnabled_get(long j2, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_iceEnabled_set(long j2, AccountNatConfig accountNatConfig, boolean z);

    public static final native int AccountNatConfig_iceMaxHostCands_get(long j2, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_iceMaxHostCands_set(long j2, AccountNatConfig accountNatConfig, int i2);

    public static final native boolean AccountNatConfig_iceNoRtcp_get(long j2, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_iceNoRtcp_set(long j2, AccountNatConfig accountNatConfig, boolean z);

    public static final native long AccountNatConfig_iceNominatedCheckDelayMsec_get(long j2, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_iceNominatedCheckDelayMsec_set(long j2, AccountNatConfig accountNatConfig, long j3);

    public static final native int AccountNatConfig_iceWaitNominationTimeoutMsec_get(long j2, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_iceWaitNominationTimeoutMsec_set(long j2, AccountNatConfig accountNatConfig, int i2);

    public static final native int AccountNatConfig_mediaStunUse_get(long j2, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_mediaStunUse_set(long j2, AccountNatConfig accountNatConfig, int i2);

    public static final native int AccountNatConfig_nat64Opt_get(long j2, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_nat64Opt_set(long j2, AccountNatConfig accountNatConfig, int i2);

    public static final native int AccountNatConfig_sdpNatRewriteUse_get(long j2, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_sdpNatRewriteUse_set(long j2, AccountNatConfig accountNatConfig, int i2);

    public static final native String AccountNatConfig_sipOutboundInstanceId_get(long j2, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_sipOutboundInstanceId_set(long j2, AccountNatConfig accountNatConfig, String str);

    public static final native String AccountNatConfig_sipOutboundRegId_get(long j2, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_sipOutboundRegId_set(long j2, AccountNatConfig accountNatConfig, String str);

    public static final native int AccountNatConfig_sipOutboundUse_get(long j2, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_sipOutboundUse_set(long j2, AccountNatConfig accountNatConfig, int i2);

    public static final native int AccountNatConfig_sipStunUse_get(long j2, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_sipStunUse_set(long j2, AccountNatConfig accountNatConfig, int i2);

    public static final native int AccountNatConfig_turnConnType_get(long j2, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_turnConnType_set(long j2, AccountNatConfig accountNatConfig, int i2);

    public static final native boolean AccountNatConfig_turnEnabled_get(long j2, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_turnEnabled_set(long j2, AccountNatConfig accountNatConfig, boolean z);

    public static final native int AccountNatConfig_turnPasswordType_get(long j2, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_turnPasswordType_set(long j2, AccountNatConfig accountNatConfig, int i2);

    public static final native String AccountNatConfig_turnPassword_get(long j2, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_turnPassword_set(long j2, AccountNatConfig accountNatConfig, String str);

    public static final native String AccountNatConfig_turnServer_get(long j2, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_turnServer_set(long j2, AccountNatConfig accountNatConfig, String str);

    public static final native String AccountNatConfig_turnUserName_get(long j2, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_turnUserName_set(long j2, AccountNatConfig accountNatConfig, String str);

    public static final native String AccountNatConfig_udpKaData_get(long j2, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_udpKaData_set(long j2, AccountNatConfig accountNatConfig, String str);

    public static final native long AccountNatConfig_udpKaIntervalSec_get(long j2, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_udpKaIntervalSec_set(long j2, AccountNatConfig accountNatConfig, long j3);

    public static final native int AccountNatConfig_viaRewriteUse_get(long j2, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_viaRewriteUse_set(long j2, AccountNatConfig accountNatConfig, int i2);

    public static final native long AccountPresConfig_headers_get(long j2, AccountPresConfig accountPresConfig);

    public static final native void AccountPresConfig_headers_set(long j2, AccountPresConfig accountPresConfig, long j3, SipHeaderVector sipHeaderVector);

    public static final native String AccountPresConfig_pidfTupleId_get(long j2, AccountPresConfig accountPresConfig);

    public static final native void AccountPresConfig_pidfTupleId_set(long j2, AccountPresConfig accountPresConfig, String str);

    public static final native boolean AccountPresConfig_publishEnabled_get(long j2, AccountPresConfig accountPresConfig);

    public static final native void AccountPresConfig_publishEnabled_set(long j2, AccountPresConfig accountPresConfig, boolean z);

    public static final native boolean AccountPresConfig_publishQueue_get(long j2, AccountPresConfig accountPresConfig);

    public static final native void AccountPresConfig_publishQueue_set(long j2, AccountPresConfig accountPresConfig, boolean z);

    public static final native long AccountPresConfig_publishShutdownWaitMsec_get(long j2, AccountPresConfig accountPresConfig);

    public static final native void AccountPresConfig_publishShutdownWaitMsec_set(long j2, AccountPresConfig accountPresConfig, long j3);

    public static final native String AccountRegConfig_contactParams_get(long j2, AccountRegConfig accountRegConfig);

    public static final native void AccountRegConfig_contactParams_set(long j2, AccountRegConfig accountRegConfig, String str);

    public static final native long AccountRegConfig_delayBeforeRefreshSec_get(long j2, AccountRegConfig accountRegConfig);

    public static final native void AccountRegConfig_delayBeforeRefreshSec_set(long j2, AccountRegConfig accountRegConfig, long j3);

    public static final native boolean AccountRegConfig_dropCallsOnFail_get(long j2, AccountRegConfig accountRegConfig);

    public static final native void AccountRegConfig_dropCallsOnFail_set(long j2, AccountRegConfig accountRegConfig, boolean z);

    public static final native long AccountRegConfig_firstRetryIntervalSec_get(long j2, AccountRegConfig accountRegConfig);

    public static final native void AccountRegConfig_firstRetryIntervalSec_set(long j2, AccountRegConfig accountRegConfig, long j3);

    public static final native long AccountRegConfig_headers_get(long j2, AccountRegConfig accountRegConfig);

    public static final native void AccountRegConfig_headers_set(long j2, AccountRegConfig accountRegConfig, long j3, SipHeaderVector sipHeaderVector);

    public static final native long AccountRegConfig_proxyUse_get(long j2, AccountRegConfig accountRegConfig);

    public static final native void AccountRegConfig_proxyUse_set(long j2, AccountRegConfig accountRegConfig, long j3);

    public static final native long AccountRegConfig_randomRetryIntervalSec_get(long j2, AccountRegConfig accountRegConfig);

    public static final native void AccountRegConfig_randomRetryIntervalSec_set(long j2, AccountRegConfig accountRegConfig, long j3);

    public static final native boolean AccountRegConfig_registerOnAdd_get(long j2, AccountRegConfig accountRegConfig);

    public static final native void AccountRegConfig_registerOnAdd_set(long j2, AccountRegConfig accountRegConfig, boolean z);

    public static final native String AccountRegConfig_registrarUri_get(long j2, AccountRegConfig accountRegConfig);

    public static final native void AccountRegConfig_registrarUri_set(long j2, AccountRegConfig accountRegConfig, String str);

    public static final native long AccountRegConfig_retryIntervalSec_get(long j2, AccountRegConfig accountRegConfig);

    public static final native void AccountRegConfig_retryIntervalSec_set(long j2, AccountRegConfig accountRegConfig, long j3);

    public static final native long AccountRegConfig_timeoutSec_get(long j2, AccountRegConfig accountRegConfig);

    public static final native void AccountRegConfig_timeoutSec_set(long j2, AccountRegConfig accountRegConfig, long j3);

    public static final native long AccountRegConfig_unregWaitMsec_get(long j2, AccountRegConfig accountRegConfig);

    public static final native void AccountRegConfig_unregWaitMsec_set(long j2, AccountRegConfig accountRegConfig, long j3);

    public static final native long AccountSipConfig_authCreds_get(long j2, AccountSipConfig accountSipConfig);

    public static final native void AccountSipConfig_authCreds_set(long j2, AccountSipConfig accountSipConfig, long j3, AuthCredInfoVector authCredInfoVector);

    public static final native String AccountSipConfig_authInitialAlgorithm_get(long j2, AccountSipConfig accountSipConfig);

    public static final native void AccountSipConfig_authInitialAlgorithm_set(long j2, AccountSipConfig accountSipConfig, String str);

    public static final native boolean AccountSipConfig_authInitialEmpty_get(long j2, AccountSipConfig accountSipConfig);

    public static final native void AccountSipConfig_authInitialEmpty_set(long j2, AccountSipConfig accountSipConfig, boolean z);

    public static final native String AccountSipConfig_contactForced_get(long j2, AccountSipConfig accountSipConfig);

    public static final native void AccountSipConfig_contactForced_set(long j2, AccountSipConfig accountSipConfig, String str);

    public static final native String AccountSipConfig_contactParams_get(long j2, AccountSipConfig accountSipConfig);

    public static final native void AccountSipConfig_contactParams_set(long j2, AccountSipConfig accountSipConfig, String str);

    public static final native String AccountSipConfig_contactUriParams_get(long j2, AccountSipConfig accountSipConfig);

    public static final native void AccountSipConfig_contactUriParams_set(long j2, AccountSipConfig accountSipConfig, String str);

    public static final native long AccountSipConfig_proxies_get(long j2, AccountSipConfig accountSipConfig);

    public static final native void AccountSipConfig_proxies_set(long j2, AccountSipConfig accountSipConfig, long j3, StringVector stringVector);

    public static final native int AccountSipConfig_transportId_get(long j2, AccountSipConfig accountSipConfig);

    public static final native void AccountSipConfig_transportId_set(long j2, AccountSipConfig accountSipConfig, int i2);

    public static final native void Account_addBuddy(long j2, Account account, long j3, Buddy buddy);

    public static final native void Account_change_ownership(Account account, long j2, boolean z);

    public static final native void Account_create__SWIG_0(long j2, Account account, long j3, AccountConfig accountConfig, boolean z) throws Exception;

    public static final native void Account_create__SWIG_1(long j2, Account account, long j3, AccountConfig accountConfig) throws Exception;

    public static final native void Account_director_connect(Account account, long j2, boolean z, boolean z2);

    public static final native long Account_enumBuddies(long j2, Account account) throws Exception;

    public static final native long Account_findBuddy__SWIG_0(long j2, Account account, String str, long j3, FindBuddyMatch findBuddyMatch) throws Exception;

    public static final native long Account_findBuddy__SWIG_1(long j2, Account account, String str) throws Exception;

    public static final native int Account_getId(long j2, Account account);

    public static final native long Account_getInfo(long j2, Account account) throws Exception;

    public static final native boolean Account_isDefault(long j2, Account account);

    public static final native boolean Account_isValid(long j2, Account account);

    public static final native long Account_lookup(int i2);

    public static final native void Account_modify(long j2, Account account, long j3, AccountConfig accountConfig) throws Exception;

    public static final native void Account_onIncomingCall(long j2, Account account, long j3, OnIncomingCallParam onIncomingCallParam);

    public static final native void Account_onIncomingCallSwigExplicitAccount(long j2, Account account, long j3, OnIncomingCallParam onIncomingCallParam);

    public static final native void Account_onIncomingSubscribe(long j2, Account account, long j3, OnIncomingSubscribeParam onIncomingSubscribeParam);

    public static final native void Account_onIncomingSubscribeSwigExplicitAccount(long j2, Account account, long j3, OnIncomingSubscribeParam onIncomingSubscribeParam);

    public static final native void Account_onInstantMessage(long j2, Account account, long j3, OnInstantMessageParam onInstantMessageParam);

    public static final native void Account_onInstantMessageStatus(long j2, Account account, long j3, OnInstantMessageStatusParam onInstantMessageStatusParam);

    public static final native void Account_onInstantMessageStatusSwigExplicitAccount(long j2, Account account, long j3, OnInstantMessageStatusParam onInstantMessageStatusParam);

    public static final native void Account_onInstantMessageSwigExplicitAccount(long j2, Account account, long j3, OnInstantMessageParam onInstantMessageParam);

    public static final native void Account_onMwiInfo(long j2, Account account, long j3, OnMwiInfoParam onMwiInfoParam);

    public static final native void Account_onMwiInfoSwigExplicitAccount(long j2, Account account, long j3, OnMwiInfoParam onMwiInfoParam);

    public static final native void Account_onRegStarted(long j2, Account account, long j3, OnRegStartedParam onRegStartedParam);

    public static final native void Account_onRegStartedSwigExplicitAccount(long j2, Account account, long j3, OnRegStartedParam onRegStartedParam);

    public static final native void Account_onRegState(long j2, Account account, long j3, OnRegStateParam onRegStateParam);

    public static final native void Account_onRegStateSwigExplicitAccount(long j2, Account account, long j3, OnRegStateParam onRegStateParam);

    public static final native void Account_onTypingIndication(long j2, Account account, long j3, OnTypingIndicationParam onTypingIndicationParam);

    public static final native void Account_onTypingIndicationSwigExplicitAccount(long j2, Account account, long j3, OnTypingIndicationParam onTypingIndicationParam);

    public static final native void Account_presNotify(long j2, Account account, long j3, PresNotifyParam presNotifyParam) throws Exception;

    public static final native void Account_removeBuddy(long j2, Account account, long j3, Buddy buddy);

    public static final native void Account_setDefault(long j2, Account account) throws Exception;

    public static final native void Account_setOnlineStatus(long j2, Account account, long j3, PresenceStatus presenceStatus) throws Exception;

    public static final native void Account_setRegistration(long j2, Account account, boolean z) throws Exception;

    public static final native void Account_setTransport(long j2, Account account, int i2) throws Exception;

    public static final native String AudDevManager_capName(long j2, AudDevManager audDevManager, int i2);

    public static final native long AudDevManager_enumDev(long j2, AudDevManager audDevManager) throws Exception;

    public static final native int AudDevManager_getCaptureDev(long j2, AudDevManager audDevManager) throws Exception;

    public static final native long AudDevManager_getCaptureDevMedia(long j2, AudDevManager audDevManager) throws Exception;

    public static final native boolean AudDevManager_getCng(long j2, AudDevManager audDevManager) throws Exception;

    public static final native long AudDevManager_getDevCount(long j2, AudDevManager audDevManager);

    public static final native long AudDevManager_getDevInfo(long j2, AudDevManager audDevManager, int i2) throws Exception;

    public static final native long AudDevManager_getEcTail(long j2, AudDevManager audDevManager) throws Exception;

    public static final native long AudDevManager_getExtFormat(long j2, AudDevManager audDevManager) throws Exception;

    public static final native long AudDevManager_getInputLatency(long j2, AudDevManager audDevManager) throws Exception;

    public static final native int AudDevManager_getInputRoute(long j2, AudDevManager audDevManager) throws Exception;

    public static final native long AudDevManager_getInputSignal(long j2, AudDevManager audDevManager) throws Exception;

    public static final native long AudDevManager_getInputVolume(long j2, AudDevManager audDevManager) throws Exception;

    public static final native long AudDevManager_getOutputLatency(long j2, AudDevManager audDevManager) throws Exception;

    public static final native int AudDevManager_getOutputRoute(long j2, AudDevManager audDevManager) throws Exception;

    public static final native long AudDevManager_getOutputSignal(long j2, AudDevManager audDevManager) throws Exception;

    public static final native long AudDevManager_getOutputVolume(long j2, AudDevManager audDevManager) throws Exception;

    public static final native int AudDevManager_getPlaybackDev(long j2, AudDevManager audDevManager) throws Exception;

    public static final native long AudDevManager_getPlaybackDevMedia(long j2, AudDevManager audDevManager) throws Exception;

    public static final native boolean AudDevManager_getPlc(long j2, AudDevManager audDevManager) throws Exception;

    public static final native boolean AudDevManager_getVad(long j2, AudDevManager audDevManager) throws Exception;

    public static final native int AudDevManager_lookupDev(long j2, AudDevManager audDevManager, String str, String str2) throws Exception;

    public static final native void AudDevManager_refreshDevs(long j2, AudDevManager audDevManager) throws Exception;

    public static final native void AudDevManager_setCaptureDev(long j2, AudDevManager audDevManager, int i2) throws Exception;

    public static final native void AudDevManager_setCng__SWIG_0(long j2, AudDevManager audDevManager, boolean z, boolean z2) throws Exception;

    public static final native void AudDevManager_setCng__SWIG_1(long j2, AudDevManager audDevManager, boolean z) throws Exception;

    public static final native void AudDevManager_setEcOptions(long j2, AudDevManager audDevManager, long j3, long j4) throws Exception;

    public static final native void AudDevManager_setExtFormat__SWIG_0(long j2, AudDevManager audDevManager, long j3, MediaFormatAudio mediaFormatAudio, boolean z) throws Exception;

    public static final native void AudDevManager_setExtFormat__SWIG_1(long j2, AudDevManager audDevManager, long j3, MediaFormatAudio mediaFormatAudio) throws Exception;

    public static final native void AudDevManager_setInputLatency__SWIG_0(long j2, AudDevManager audDevManager, long j3, boolean z) throws Exception;

    public static final native void AudDevManager_setInputLatency__SWIG_1(long j2, AudDevManager audDevManager, long j3) throws Exception;

    public static final native void AudDevManager_setInputRoute__SWIG_0(long j2, AudDevManager audDevManager, int i2, boolean z) throws Exception;

    public static final native void AudDevManager_setInputRoute__SWIG_1(long j2, AudDevManager audDevManager, int i2) throws Exception;

    public static final native void AudDevManager_setInputVolume__SWIG_0(long j2, AudDevManager audDevManager, long j3, boolean z) throws Exception;

    public static final native void AudDevManager_setInputVolume__SWIG_1(long j2, AudDevManager audDevManager, long j3) throws Exception;

    public static final native long AudDevManager_setNoDev(long j2, AudDevManager audDevManager);

    public static final native void AudDevManager_setNullDev(long j2, AudDevManager audDevManager) throws Exception;

    public static final native void AudDevManager_setOutputLatency__SWIG_0(long j2, AudDevManager audDevManager, long j3, boolean z) throws Exception;

    public static final native void AudDevManager_setOutputLatency__SWIG_1(long j2, AudDevManager audDevManager, long j3) throws Exception;

    public static final native void AudDevManager_setOutputRoute__SWIG_0(long j2, AudDevManager audDevManager, int i2, boolean z) throws Exception;

    public static final native void AudDevManager_setOutputRoute__SWIG_1(long j2, AudDevManager audDevManager, int i2) throws Exception;

    public static final native void AudDevManager_setOutputVolume__SWIG_0(long j2, AudDevManager audDevManager, long j3, boolean z) throws Exception;

    public static final native void AudDevManager_setOutputVolume__SWIG_1(long j2, AudDevManager audDevManager, long j3) throws Exception;

    public static final native void AudDevManager_setPlaybackDev(long j2, AudDevManager audDevManager, int i2) throws Exception;

    public static final native void AudDevManager_setPlc__SWIG_0(long j2, AudDevManager audDevManager, boolean z, boolean z2) throws Exception;

    public static final native void AudDevManager_setPlc__SWIG_1(long j2, AudDevManager audDevManager, boolean z) throws Exception;

    public static final native void AudDevManager_setSndDevMode(long j2, AudDevManager audDevManager, long j3) throws Exception;

    public static final native void AudDevManager_setVad__SWIG_0(long j2, AudDevManager audDevManager, boolean z, boolean z2) throws Exception;

    public static final native void AudDevManager_setVad__SWIG_1(long j2, AudDevManager audDevManager, boolean z) throws Exception;

    public static final native boolean AudDevManager_sndIsActive(long j2, AudDevManager audDevManager);

    public static final native void AudioDevInfoVector_add(long j2, AudioDevInfoVector audioDevInfoVector, long j3, AudioDevInfo audioDevInfo);

    public static final native long AudioDevInfoVector_capacity(long j2, AudioDevInfoVector audioDevInfoVector);

    public static final native void AudioDevInfoVector_clear(long j2, AudioDevInfoVector audioDevInfoVector);

    public static final native long AudioDevInfoVector_get(long j2, AudioDevInfoVector audioDevInfoVector, int i2);

    public static final native boolean AudioDevInfoVector_isEmpty(long j2, AudioDevInfoVector audioDevInfoVector);

    public static final native void AudioDevInfoVector_reserve(long j2, AudioDevInfoVector audioDevInfoVector, long j3);

    public static final native void AudioDevInfoVector_set(long j2, AudioDevInfoVector audioDevInfoVector, int i2, long j3, AudioDevInfo audioDevInfo);

    public static final native long AudioDevInfoVector_size(long j2, AudioDevInfoVector audioDevInfoVector);

    public static final native long AudioDevInfo_caps_get(long j2, AudioDevInfo audioDevInfo);

    public static final native void AudioDevInfo_caps_set(long j2, AudioDevInfo audioDevInfo, long j3);

    public static final native long AudioDevInfo_defaultSamplesPerSec_get(long j2, AudioDevInfo audioDevInfo);

    public static final native void AudioDevInfo_defaultSamplesPerSec_set(long j2, AudioDevInfo audioDevInfo, long j3);

    public static final native String AudioDevInfo_driver_get(long j2, AudioDevInfo audioDevInfo);

    public static final native void AudioDevInfo_driver_set(long j2, AudioDevInfo audioDevInfo, String str);

    public static final native long AudioDevInfo_extFmt_get(long j2, AudioDevInfo audioDevInfo);

    public static final native void AudioDevInfo_extFmt_set(long j2, AudioDevInfo audioDevInfo, long j3);

    public static final native long AudioDevInfo_inputCount_get(long j2, AudioDevInfo audioDevInfo);

    public static final native void AudioDevInfo_inputCount_set(long j2, AudioDevInfo audioDevInfo, long j3);

    public static final native String AudioDevInfo_name_get(long j2, AudioDevInfo audioDevInfo);

    public static final native void AudioDevInfo_name_set(long j2, AudioDevInfo audioDevInfo, String str);

    public static final native long AudioDevInfo_outputCount_get(long j2, AudioDevInfo audioDevInfo);

    public static final native void AudioDevInfo_outputCount_set(long j2, AudioDevInfo audioDevInfo, long j3);

    public static final native long AudioDevInfo_routes_get(long j2, AudioDevInfo audioDevInfo);

    public static final native void AudioDevInfo_routes_set(long j2, AudioDevInfo audioDevInfo, long j3);

    public static final native int AudioMediaPlayerInfo_formatId_get(long j2, AudioMediaPlayerInfo audioMediaPlayerInfo);

    public static final native void AudioMediaPlayerInfo_formatId_set(long j2, AudioMediaPlayerInfo audioMediaPlayerInfo, int i2);

    public static final native long AudioMediaPlayerInfo_payloadBitsPerSample_get(long j2, AudioMediaPlayerInfo audioMediaPlayerInfo);

    public static final native void AudioMediaPlayerInfo_payloadBitsPerSample_set(long j2, AudioMediaPlayerInfo audioMediaPlayerInfo, long j3);

    public static final native long AudioMediaPlayerInfo_sizeBytes_get(long j2, AudioMediaPlayerInfo audioMediaPlayerInfo);

    public static final native void AudioMediaPlayerInfo_sizeBytes_set(long j2, AudioMediaPlayerInfo audioMediaPlayerInfo, long j3);

    public static final native long AudioMediaPlayerInfo_sizeSamples_get(long j2, AudioMediaPlayerInfo audioMediaPlayerInfo);

    public static final native void AudioMediaPlayerInfo_sizeSamples_set(long j2, AudioMediaPlayerInfo audioMediaPlayerInfo, long j3);

    public static final native long AudioMediaPlayer_SWIGUpcast(long j2);

    public static final native void AudioMediaPlayer_change_ownership(AudioMediaPlayer audioMediaPlayer, long j2, boolean z);

    public static final native void AudioMediaPlayer_createPlayer__SWIG_0(long j2, AudioMediaPlayer audioMediaPlayer, String str, long j3) throws Exception;

    public static final native void AudioMediaPlayer_createPlayer__SWIG_1(long j2, AudioMediaPlayer audioMediaPlayer, String str) throws Exception;

    public static final native void AudioMediaPlayer_createPlaylist__SWIG_0(long j2, AudioMediaPlayer audioMediaPlayer, long j3, StringVector stringVector, String str, long j4) throws Exception;

    public static final native void AudioMediaPlayer_createPlaylist__SWIG_1(long j2, AudioMediaPlayer audioMediaPlayer, long j3, StringVector stringVector, String str) throws Exception;

    public static final native void AudioMediaPlayer_createPlaylist__SWIG_2(long j2, AudioMediaPlayer audioMediaPlayer, long j3, StringVector stringVector) throws Exception;

    public static final native void AudioMediaPlayer_director_connect(AudioMediaPlayer audioMediaPlayer, long j2, boolean z, boolean z2);

    public static final native long AudioMediaPlayer_getInfo(long j2, AudioMediaPlayer audioMediaPlayer) throws Exception;

    public static final native long AudioMediaPlayer_getPos(long j2, AudioMediaPlayer audioMediaPlayer) throws Exception;

    public static final native boolean AudioMediaPlayer_onEof(long j2, AudioMediaPlayer audioMediaPlayer);

    public static final native boolean AudioMediaPlayer_onEofSwigExplicitAudioMediaPlayer(long j2, AudioMediaPlayer audioMediaPlayer);

    public static final native void AudioMediaPlayer_setPos(long j2, AudioMediaPlayer audioMediaPlayer, long j3) throws Exception;

    public static final native long AudioMediaPlayer_typecastFromAudioMedia(long j2, AudioMedia audioMedia);

    public static final native long AudioMediaRecorder_SWIGUpcast(long j2);

    public static final native void AudioMediaRecorder_createRecorder__SWIG_0(long j2, AudioMediaRecorder audioMediaRecorder, String str, long j3, long j4, long j5) throws Exception;

    public static final native void AudioMediaRecorder_createRecorder__SWIG_1(long j2, AudioMediaRecorder audioMediaRecorder, String str, long j3, long j4) throws Exception;

    public static final native void AudioMediaRecorder_createRecorder__SWIG_2(long j2, AudioMediaRecorder audioMediaRecorder, String str, long j3) throws Exception;

    public static final native void AudioMediaRecorder_createRecorder__SWIG_3(long j2, AudioMediaRecorder audioMediaRecorder, String str) throws Exception;

    public static final native long AudioMediaRecorder_typecastFromAudioMedia(long j2, AudioMedia audioMedia);

    public static final native void AudioMediaVector_add(long j2, AudioMediaVector audioMediaVector, long j3, AudioMedia audioMedia);

    public static final native long AudioMediaVector_capacity(long j2, AudioMediaVector audioMediaVector);

    public static final native void AudioMediaVector_clear(long j2, AudioMediaVector audioMediaVector);

    public static final native long AudioMediaVector_get(long j2, AudioMediaVector audioMediaVector, int i2);

    public static final native boolean AudioMediaVector_isEmpty(long j2, AudioMediaVector audioMediaVector);

    public static final native void AudioMediaVector_reserve(long j2, AudioMediaVector audioMediaVector, long j3);

    public static final native void AudioMediaVector_set(long j2, AudioMediaVector audioMediaVector, int i2, long j3, AudioMedia audioMedia);

    public static final native long AudioMediaVector_size(long j2, AudioMediaVector audioMediaVector);

    public static final native long AudioMedia_SWIGUpcast(long j2);

    public static final native void AudioMedia_adjustRxLevel(long j2, AudioMedia audioMedia, float f2) throws Exception;

    public static final native void AudioMedia_adjustTxLevel(long j2, AudioMedia audioMedia, float f2) throws Exception;

    public static final native int AudioMedia_getPortId(long j2, AudioMedia audioMedia);

    public static final native long AudioMedia_getPortInfo(long j2, AudioMedia audioMedia) throws Exception;

    public static final native long AudioMedia_getPortInfoFromId(int i2) throws Exception;

    public static final native long AudioMedia_getRxLevel(long j2, AudioMedia audioMedia) throws Exception;

    public static final native long AudioMedia_getTxLevel(long j2, AudioMedia audioMedia) throws Exception;

    public static final native void AudioMedia_startTransmit(long j2, AudioMedia audioMedia, long j3, AudioMedia audioMedia2) throws Exception;

    public static final native void AudioMedia_stopTransmit(long j2, AudioMedia audioMedia, long j3, AudioMedia audioMedia2) throws Exception;

    public static final native long AudioMedia_typecastFromMedia(long j2, Media media);

    public static final native void AuthCredInfoVector_add(long j2, AuthCredInfoVector authCredInfoVector, long j3, AuthCredInfo authCredInfo);

    public static final native long AuthCredInfoVector_capacity(long j2, AuthCredInfoVector authCredInfoVector);

    public static final native void AuthCredInfoVector_clear(long j2, AuthCredInfoVector authCredInfoVector);

    public static final native long AuthCredInfoVector_get(long j2, AuthCredInfoVector authCredInfoVector, int i2);

    public static final native boolean AuthCredInfoVector_isEmpty(long j2, AuthCredInfoVector authCredInfoVector);

    public static final native void AuthCredInfoVector_reserve(long j2, AuthCredInfoVector authCredInfoVector, long j3);

    public static final native void AuthCredInfoVector_set(long j2, AuthCredInfoVector authCredInfoVector, int i2, long j3, AuthCredInfo authCredInfo);

    public static final native long AuthCredInfoVector_size(long j2, AuthCredInfoVector authCredInfoVector);

    public static final native String AuthCredInfo_akaAmf_get(long j2, AuthCredInfo authCredInfo);

    public static final native void AuthCredInfo_akaAmf_set(long j2, AuthCredInfo authCredInfo, String str);

    public static final native String AuthCredInfo_akaK_get(long j2, AuthCredInfo authCredInfo);

    public static final native void AuthCredInfo_akaK_set(long j2, AuthCredInfo authCredInfo, String str);

    public static final native String AuthCredInfo_akaOp_get(long j2, AuthCredInfo authCredInfo);

    public static final native void AuthCredInfo_akaOp_set(long j2, AuthCredInfo authCredInfo, String str);

    public static final native int AuthCredInfo_dataType_get(long j2, AuthCredInfo authCredInfo);

    public static final native void AuthCredInfo_dataType_set(long j2, AuthCredInfo authCredInfo, int i2);

    public static final native String AuthCredInfo_data_get(long j2, AuthCredInfo authCredInfo);

    public static final native void AuthCredInfo_data_set(long j2, AuthCredInfo authCredInfo, String str);

    public static final native String AuthCredInfo_realm_get(long j2, AuthCredInfo authCredInfo);

    public static final native void AuthCredInfo_realm_set(long j2, AuthCredInfo authCredInfo, String str);

    public static final native String AuthCredInfo_scheme_get(long j2, AuthCredInfo authCredInfo);

    public static final native void AuthCredInfo_scheme_set(long j2, AuthCredInfo authCredInfo, String str);

    public static final native String AuthCredInfo_username_get(long j2, AuthCredInfo authCredInfo);

    public static final native void AuthCredInfo_username_set(long j2, AuthCredInfo authCredInfo, String str);

    public static final native boolean BuddyConfig_subscribe_get(long j2, BuddyConfig buddyConfig);

    public static final native void BuddyConfig_subscribe_set(long j2, BuddyConfig buddyConfig, boolean z);

    public static final native String BuddyConfig_uri_get(long j2, BuddyConfig buddyConfig);

    public static final native void BuddyConfig_uri_set(long j2, BuddyConfig buddyConfig, String str);

    public static final native String BuddyInfo_contact_get(long j2, BuddyInfo buddyInfo);

    public static final native void BuddyInfo_contact_set(long j2, BuddyInfo buddyInfo, String str);

    public static final native boolean BuddyInfo_presMonitorEnabled_get(long j2, BuddyInfo buddyInfo);

    public static final native void BuddyInfo_presMonitorEnabled_set(long j2, BuddyInfo buddyInfo, boolean z);

    public static final native long BuddyInfo_presStatus_get(long j2, BuddyInfo buddyInfo);

    public static final native void BuddyInfo_presStatus_set(long j2, BuddyInfo buddyInfo, long j3, PresenceStatus presenceStatus);

    public static final native String BuddyInfo_subStateName_get(long j2, BuddyInfo buddyInfo);

    public static final native void BuddyInfo_subStateName_set(long j2, BuddyInfo buddyInfo, String str);

    public static final native int BuddyInfo_subState_get(long j2, BuddyInfo buddyInfo);

    public static final native void BuddyInfo_subState_set(long j2, BuddyInfo buddyInfo, int i2);

    public static final native int BuddyInfo_subTermCode_get(long j2, BuddyInfo buddyInfo);

    public static final native void BuddyInfo_subTermCode_set(long j2, BuddyInfo buddyInfo, int i2);

    public static final native String BuddyInfo_subTermReason_get(long j2, BuddyInfo buddyInfo);

    public static final native void BuddyInfo_subTermReason_set(long j2, BuddyInfo buddyInfo, String str);

    public static final native String BuddyInfo_uri_get(long j2, BuddyInfo buddyInfo);

    public static final native void BuddyInfo_uri_set(long j2, BuddyInfo buddyInfo, String str);

    public static final native void BuddyVector_add(long j2, BuddyVector buddyVector, long j3, Buddy buddy);

    public static final native long BuddyVector_capacity(long j2, BuddyVector buddyVector);

    public static final native void BuddyVector_clear(long j2, BuddyVector buddyVector);

    public static final native long BuddyVector_get(long j2, BuddyVector buddyVector, int i2);

    public static final native boolean BuddyVector_isEmpty(long j2, BuddyVector buddyVector);

    public static final native void BuddyVector_reserve(long j2, BuddyVector buddyVector, long j3);

    public static final native void BuddyVector_set(long j2, BuddyVector buddyVector, int i2, long j3, Buddy buddy);

    public static final native long BuddyVector_size(long j2, BuddyVector buddyVector);

    public static final native void Buddy_change_ownership(Buddy buddy, long j2, boolean z);

    public static final native void Buddy_create(long j2, Buddy buddy, long j3, Account account, long j4, BuddyConfig buddyConfig) throws Exception;

    public static final native void Buddy_director_connect(Buddy buddy, long j2, boolean z, boolean z2);

    public static final native long Buddy_getInfo(long j2, Buddy buddy) throws Exception;

    public static final native boolean Buddy_isValid(long j2, Buddy buddy);

    public static final native void Buddy_onBuddyEvSubState(long j2, Buddy buddy, long j3, OnBuddyEvSubStateParam onBuddyEvSubStateParam);

    public static final native void Buddy_onBuddyEvSubStateSwigExplicitBuddy(long j2, Buddy buddy, long j3, OnBuddyEvSubStateParam onBuddyEvSubStateParam);

    public static final native void Buddy_onBuddyState(long j2, Buddy buddy);

    public static final native void Buddy_onBuddyStateSwigExplicitBuddy(long j2, Buddy buddy);

    public static final native void Buddy_sendInstantMessage(long j2, Buddy buddy, long j3, SendInstantMessageParam sendInstantMessageParam) throws Exception;

    public static final native void Buddy_sendTypingIndication(long j2, Buddy buddy, long j3, SendTypingIndicationParam sendTypingIndicationParam) throws Exception;

    public static final native void Buddy_subscribePresence(long j2, Buddy buddy, boolean z) throws Exception;

    public static final native void Buddy_updatePresence(long j2, Buddy buddy) throws Exception;

    public static final native int CallInfo_accId_get(long j2, CallInfo callInfo);

    public static final native void CallInfo_accId_set(long j2, CallInfo callInfo, int i2);

    public static final native String CallInfo_callIdString_get(long j2, CallInfo callInfo);

    public static final native void CallInfo_callIdString_set(long j2, CallInfo callInfo, String str);

    public static final native long CallInfo_connectDuration_get(long j2, CallInfo callInfo);

    public static final native void CallInfo_connectDuration_set(long j2, CallInfo callInfo, long j3, TimeVal timeVal);

    public static final native int CallInfo_id_get(long j2, CallInfo callInfo);

    public static final native void CallInfo_id_set(long j2, CallInfo callInfo, int i2);

    public static final native String CallInfo_lastReason_get(long j2, CallInfo callInfo);

    public static final native void CallInfo_lastReason_set(long j2, CallInfo callInfo, String str);

    public static final native int CallInfo_lastStatusCode_get(long j2, CallInfo callInfo);

    public static final native void CallInfo_lastStatusCode_set(long j2, CallInfo callInfo, int i2);

    public static final native String CallInfo_localContact_get(long j2, CallInfo callInfo);

    public static final native void CallInfo_localContact_set(long j2, CallInfo callInfo, String str);

    public static final native String CallInfo_localUri_get(long j2, CallInfo callInfo);

    public static final native void CallInfo_localUri_set(long j2, CallInfo callInfo, String str);

    public static final native long CallInfo_media_get(long j2, CallInfo callInfo);

    public static final native void CallInfo_media_set(long j2, CallInfo callInfo, long j3, CallMediaInfoVector callMediaInfoVector);

    public static final native long CallInfo_provMedia_get(long j2, CallInfo callInfo);

    public static final native void CallInfo_provMedia_set(long j2, CallInfo callInfo, long j3, CallMediaInfoVector callMediaInfoVector);

    public static final native long CallInfo_remAudioCount_get(long j2, CallInfo callInfo);

    public static final native void CallInfo_remAudioCount_set(long j2, CallInfo callInfo, long j3);

    public static final native boolean CallInfo_remOfferer_get(long j2, CallInfo callInfo);

    public static final native void CallInfo_remOfferer_set(long j2, CallInfo callInfo, boolean z);

    public static final native long CallInfo_remVideoCount_get(long j2, CallInfo callInfo);

    public static final native void CallInfo_remVideoCount_set(long j2, CallInfo callInfo, long j3);

    public static final native String CallInfo_remoteContact_get(long j2, CallInfo callInfo);

    public static final native void CallInfo_remoteContact_set(long j2, CallInfo callInfo, String str);

    public static final native String CallInfo_remoteUri_get(long j2, CallInfo callInfo);

    public static final native void CallInfo_remoteUri_set(long j2, CallInfo callInfo, String str);

    public static final native int CallInfo_role_get(long j2, CallInfo callInfo);

    public static final native void CallInfo_role_set(long j2, CallInfo callInfo, int i2);

    public static final native long CallInfo_setting_get(long j2, CallInfo callInfo);

    public static final native void CallInfo_setting_set(long j2, CallInfo callInfo, long j3, CallSetting callSetting);

    public static final native String CallInfo_stateText_get(long j2, CallInfo callInfo);

    public static final native void CallInfo_stateText_set(long j2, CallInfo callInfo, String str);

    public static final native int CallInfo_state_get(long j2, CallInfo callInfo);

    public static final native void CallInfo_state_set(long j2, CallInfo callInfo, int i2);

    public static final native long CallInfo_totalDuration_get(long j2, CallInfo callInfo);

    public static final native void CallInfo_totalDuration_set(long j2, CallInfo callInfo, long j3, TimeVal timeVal);

    public static final native void CallMediaInfoVector_add(long j2, CallMediaInfoVector callMediaInfoVector, long j3, CallMediaInfo callMediaInfo);

    public static final native long CallMediaInfoVector_capacity(long j2, CallMediaInfoVector callMediaInfoVector);

    public static final native void CallMediaInfoVector_clear(long j2, CallMediaInfoVector callMediaInfoVector);

    public static final native long CallMediaInfoVector_get(long j2, CallMediaInfoVector callMediaInfoVector, int i2);

    public static final native boolean CallMediaInfoVector_isEmpty(long j2, CallMediaInfoVector callMediaInfoVector);

    public static final native void CallMediaInfoVector_reserve(long j2, CallMediaInfoVector callMediaInfoVector, long j3);

    public static final native void CallMediaInfoVector_set(long j2, CallMediaInfoVector callMediaInfoVector, int i2, long j3, CallMediaInfo callMediaInfo);

    public static final native long CallMediaInfoVector_size(long j2, CallMediaInfoVector callMediaInfoVector);

    public static final native int CallMediaInfo_audioConfSlot_get(long j2, CallMediaInfo callMediaInfo);

    public static final native void CallMediaInfo_audioConfSlot_set(long j2, CallMediaInfo callMediaInfo, int i2);

    public static final native int CallMediaInfo_dir_get(long j2, CallMediaInfo callMediaInfo);

    public static final native void CallMediaInfo_dir_set(long j2, CallMediaInfo callMediaInfo, int i2);

    public static final native long CallMediaInfo_index_get(long j2, CallMediaInfo callMediaInfo);

    public static final native void CallMediaInfo_index_set(long j2, CallMediaInfo callMediaInfo, long j3);

    public static final native int CallMediaInfo_status_get(long j2, CallMediaInfo callMediaInfo);

    public static final native void CallMediaInfo_status_set(long j2, CallMediaInfo callMediaInfo, int i2);

    public static final native int CallMediaInfo_type_get(long j2, CallMediaInfo callMediaInfo);

    public static final native void CallMediaInfo_type_set(long j2, CallMediaInfo callMediaInfo, int i2);

    public static final native long CallOpParam_opt_get(long j2, CallOpParam callOpParam);

    public static final native void CallOpParam_opt_set(long j2, CallOpParam callOpParam, long j3, CallSetting callSetting);

    public static final native long CallOpParam_options_get(long j2, CallOpParam callOpParam);

    public static final native void CallOpParam_options_set(long j2, CallOpParam callOpParam, long j3);

    public static final native String CallOpParam_reason_get(long j2, CallOpParam callOpParam);

    public static final native void CallOpParam_reason_set(long j2, CallOpParam callOpParam, String str);

    public static final native int CallOpParam_statusCode_get(long j2, CallOpParam callOpParam);

    public static final native void CallOpParam_statusCode_set(long j2, CallOpParam callOpParam, int i2);

    public static final native long CallOpParam_txOption_get(long j2, CallOpParam callOpParam);

    public static final native void CallOpParam_txOption_set(long j2, CallOpParam callOpParam, long j3, SipTxOption sipTxOption);

    public static final native String CallSendRequestParam_method_get(long j2, CallSendRequestParam callSendRequestParam);

    public static final native void CallSendRequestParam_method_set(long j2, CallSendRequestParam callSendRequestParam, String str);

    public static final native long CallSendRequestParam_txOption_get(long j2, CallSendRequestParam callSendRequestParam);

    public static final native void CallSendRequestParam_txOption_set(long j2, CallSendRequestParam callSendRequestParam, long j3, SipTxOption sipTxOption);

    public static final native long CallSetting_audioCount_get(long j2, CallSetting callSetting);

    public static final native void CallSetting_audioCount_set(long j2, CallSetting callSetting, long j3);

    public static final native long CallSetting_flag_get(long j2, CallSetting callSetting);

    public static final native void CallSetting_flag_set(long j2, CallSetting callSetting, long j3);

    public static final native boolean CallSetting_isEmpty(long j2, CallSetting callSetting);

    public static final native long CallSetting_reqKeyframeMethod_get(long j2, CallSetting callSetting);

    public static final native void CallSetting_reqKeyframeMethod_set(long j2, CallSetting callSetting, long j3);

    public static final native long CallSetting_videoCount_get(long j2, CallSetting callSetting);

    public static final native void CallSetting_videoCount_set(long j2, CallSetting callSetting, long j3);

    public static final native void Call_answer(long j2, Call call, long j3, CallOpParam callOpParam) throws Exception;

    public static final native void Call_change_ownership(Call call, long j2, boolean z);

    public static final native void Call_dialDtmf(long j2, Call call, String str) throws Exception;

    public static final native void Call_director_connect(Call call, long j2, boolean z, boolean z2);

    public static final native String Call_dump(long j2, Call call, boolean z, String str) throws Exception;

    public static final native int Call_getId(long j2, Call call);

    public static final native long Call_getInfo(long j2, Call call) throws Exception;

    public static final native long Call_getMedTransportInfo(long j2, Call call, long j3) throws Exception;

    public static final native long Call_getMedia(long j2, Call call, long j3);

    public static final native int Call_getRemNatType(long j2, Call call) throws Exception;

    public static final native long Call_getStreamInfo(long j2, Call call, long j3) throws Exception;

    public static final native long Call_getStreamStat(long j2, Call call, long j3) throws Exception;

    public static final native long Call_getUserData(long j2, Call call);

    public static final native void Call_hangup(long j2, Call call, long j3, CallOpParam callOpParam) throws Exception;

    public static final native boolean Call_hasMedia(long j2, Call call);

    public static final native boolean Call_isActive(long j2, Call call);

    public static final native long Call_lookup(int i2);

    public static final native void Call_makeCall(long j2, Call call, String str, long j3, CallOpParam callOpParam) throws Exception;

    public static final native void Call_onCallMediaEvent(long j2, Call call, long j3, OnCallMediaEventParam onCallMediaEventParam);

    public static final native void Call_onCallMediaEventSwigExplicitCall(long j2, Call call, long j3, OnCallMediaEventParam onCallMediaEventParam);

    public static final native void Call_onCallMediaState(long j2, Call call, long j3, OnCallMediaStateParam onCallMediaStateParam);

    public static final native void Call_onCallMediaStateSwigExplicitCall(long j2, Call call, long j3, OnCallMediaStateParam onCallMediaStateParam);

    public static final native void Call_onCallMediaTransportState(long j2, Call call, long j3, OnCallMediaTransportStateParam onCallMediaTransportStateParam);

    public static final native void Call_onCallMediaTransportStateSwigExplicitCall(long j2, Call call, long j3, OnCallMediaTransportStateParam onCallMediaTransportStateParam);

    public static final native int Call_onCallRedirected(long j2, Call call, long j3, OnCallRedirectedParam onCallRedirectedParam);

    public static final native int Call_onCallRedirectedSwigExplicitCall(long j2, Call call, long j3, OnCallRedirectedParam onCallRedirectedParam);

    public static final native void Call_onCallReplaceRequest(long j2, Call call, long j3, OnCallReplaceRequestParam onCallReplaceRequestParam);

    public static final native void Call_onCallReplaceRequestSwigExplicitCall(long j2, Call call, long j3, OnCallReplaceRequestParam onCallReplaceRequestParam);

    public static final native void Call_onCallReplaced(long j2, Call call, long j3, OnCallReplacedParam onCallReplacedParam);

    public static final native void Call_onCallReplacedSwigExplicitCall(long j2, Call call, long j3, OnCallReplacedParam onCallReplacedParam);

    public static final native void Call_onCallRxOffer(long j2, Call call, long j3, OnCallRxOfferParam onCallRxOfferParam);

    public static final native void Call_onCallRxOfferSwigExplicitCall(long j2, Call call, long j3, OnCallRxOfferParam onCallRxOfferParam);

    public static final native void Call_onCallSdpCreated(long j2, Call call, long j3, OnCallSdpCreatedParam onCallSdpCreatedParam);

    public static final native void Call_onCallSdpCreatedSwigExplicitCall(long j2, Call call, long j3, OnCallSdpCreatedParam onCallSdpCreatedParam);

    public static final native void Call_onCallState(long j2, Call call, long j3, OnCallStateParam onCallStateParam);

    public static final native void Call_onCallStateSwigExplicitCall(long j2, Call call, long j3, OnCallStateParam onCallStateParam);

    public static final native void Call_onCallTransferRequest(long j2, Call call, long j3, OnCallTransferRequestParam onCallTransferRequestParam);

    public static final native void Call_onCallTransferRequestSwigExplicitCall(long j2, Call call, long j3, OnCallTransferRequestParam onCallTransferRequestParam);

    public static final native void Call_onCallTransferStatus(long j2, Call call, long j3, OnCallTransferStatusParam onCallTransferStatusParam);

    public static final native void Call_onCallTransferStatusSwigExplicitCall(long j2, Call call, long j3, OnCallTransferStatusParam onCallTransferStatusParam);

    public static final native void Call_onCallTsxState(long j2, Call call, long j3, OnCallTsxStateParam onCallTsxStateParam);

    public static final native void Call_onCallTsxStateSwigExplicitCall(long j2, Call call, long j3, OnCallTsxStateParam onCallTsxStateParam);

    public static final native void Call_onCallTxOffer(long j2, Call call, long j3, OnCallTxOfferParam onCallTxOfferParam);

    public static final native void Call_onCallTxOfferSwigExplicitCall(long j2, Call call, long j3, OnCallTxOfferParam onCallTxOfferParam);

    public static final native void Call_onCreateMediaTransport(long j2, Call call, long j3, OnCreateMediaTransportParam onCreateMediaTransportParam);

    public static final native void Call_onCreateMediaTransportSrtp(long j2, Call call, long j3, OnCreateMediaTransportSrtpParam onCreateMediaTransportSrtpParam);

    public static final native void Call_onCreateMediaTransportSrtpSwigExplicitCall(long j2, Call call, long j3, OnCreateMediaTransportSrtpParam onCreateMediaTransportSrtpParam);

    public static final native void Call_onCreateMediaTransportSwigExplicitCall(long j2, Call call, long j3, OnCreateMediaTransportParam onCreateMediaTransportParam);

    public static final native void Call_onDtmfDigit(long j2, Call call, long j3, OnDtmfDigitParam onDtmfDigitParam);

    public static final native void Call_onDtmfDigitSwigExplicitCall(long j2, Call call, long j3, OnDtmfDigitParam onDtmfDigitParam);

    public static final native void Call_onInstantMessage(long j2, Call call, long j3, OnInstantMessageParam onInstantMessageParam);

    public static final native void Call_onInstantMessageStatus(long j2, Call call, long j3, OnInstantMessageStatusParam onInstantMessageStatusParam);

    public static final native void Call_onInstantMessageStatusSwigExplicitCall(long j2, Call call, long j3, OnInstantMessageStatusParam onInstantMessageStatusParam);

    public static final native void Call_onInstantMessageSwigExplicitCall(long j2, Call call, long j3, OnInstantMessageParam onInstantMessageParam);

    public static final native void Call_onStreamCreated(long j2, Call call, long j3, OnStreamCreatedParam onStreamCreatedParam);

    public static final native void Call_onStreamCreatedSwigExplicitCall(long j2, Call call, long j3, OnStreamCreatedParam onStreamCreatedParam);

    public static final native void Call_onStreamDestroyed(long j2, Call call, long j3, OnStreamDestroyedParam onStreamDestroyedParam);

    public static final native void Call_onStreamDestroyedSwigExplicitCall(long j2, Call call, long j3, OnStreamDestroyedParam onStreamDestroyedParam);

    public static final native void Call_onTypingIndication(long j2, Call call, long j3, OnTypingIndicationParam onTypingIndicationParam);

    public static final native void Call_onTypingIndicationSwigExplicitCall(long j2, Call call, long j3, OnTypingIndicationParam onTypingIndicationParam);

    public static final native void Call_processMediaUpdate(long j2, Call call, long j3, OnCallMediaStateParam onCallMediaStateParam);

    public static final native void Call_processRedirect(long j2, Call call, int i2) throws Exception;

    public static final native void Call_processStateChange(long j2, Call call, long j3, OnCallStateParam onCallStateParam);

    public static final native void Call_reinvite(long j2, Call call, long j3, CallOpParam callOpParam) throws Exception;

    public static final native int Call_remoteHasCap(long j2, Call call, int i2, String str, String str2);

    public static final native void Call_sendInstantMessage(long j2, Call call, long j3, SendInstantMessageParam sendInstantMessageParam) throws Exception;

    public static final native void Call_sendRequest(long j2, Call call, long j3, CallSendRequestParam callSendRequestParam) throws Exception;

    public static final native void Call_sendTypingIndication(long j2, Call call, long j3, SendTypingIndicationParam sendTypingIndicationParam) throws Exception;

    public static final native void Call_setHold(long j2, Call call, long j3, CallOpParam callOpParam) throws Exception;

    public static final native void Call_setUserData(long j2, Call call, long j3);

    public static final native void Call_update(long j2, Call call, long j3, CallOpParam callOpParam) throws Exception;

    public static final native void Call_xfer(long j2, Call call, String str, long j3, CallOpParam callOpParam) throws Exception;

    public static final native void Call_xferReplaces(long j2, Call call, long j3, Call call2, long j4, CallOpParam callOpParam) throws Exception;

    public static final native void CodecFmtpVector_add(long j2, CodecFmtpVector codecFmtpVector, long j3, CodecFmtp codecFmtp);

    public static final native long CodecFmtpVector_capacity(long j2, CodecFmtpVector codecFmtpVector);

    public static final native void CodecFmtpVector_clear(long j2, CodecFmtpVector codecFmtpVector);

    public static final native long CodecFmtpVector_get(long j2, CodecFmtpVector codecFmtpVector, int i2);

    public static final native boolean CodecFmtpVector_isEmpty(long j2, CodecFmtpVector codecFmtpVector);

    public static final native void CodecFmtpVector_reserve(long j2, CodecFmtpVector codecFmtpVector, long j3);

    public static final native void CodecFmtpVector_set(long j2, CodecFmtpVector codecFmtpVector, int i2, long j3, CodecFmtp codecFmtp);

    public static final native long CodecFmtpVector_size(long j2, CodecFmtpVector codecFmtpVector);

    public static final native String CodecFmtp_name_get(long j2, CodecFmtp codecFmtp);

    public static final native void CodecFmtp_name_set(long j2, CodecFmtp codecFmtp, String str);

    public static final native String CodecFmtp_val_get(long j2, CodecFmtp codecFmtp);

    public static final native void CodecFmtp_val_set(long j2, CodecFmtp codecFmtp, String str);

    public static final native void CodecInfoVector_add(long j2, CodecInfoVector codecInfoVector, long j3, CodecInfo codecInfo);

    public static final native long CodecInfoVector_capacity(long j2, CodecInfoVector codecInfoVector);

    public static final native void CodecInfoVector_clear(long j2, CodecInfoVector codecInfoVector);

    public static final native long CodecInfoVector_get(long j2, CodecInfoVector codecInfoVector, int i2);

    public static final native boolean CodecInfoVector_isEmpty(long j2, CodecInfoVector codecInfoVector);

    public static final native void CodecInfoVector_reserve(long j2, CodecInfoVector codecInfoVector, long j3);

    public static final native void CodecInfoVector_set(long j2, CodecInfoVector codecInfoVector, int i2, long j3, CodecInfo codecInfo);

    public static final native long CodecInfoVector_size(long j2, CodecInfoVector codecInfoVector);

    public static final native String CodecInfo_codecId_get(long j2, CodecInfo codecInfo);

    public static final native void CodecInfo_codecId_set(long j2, CodecInfo codecInfo, String str);

    public static final native String CodecInfo_desc_get(long j2, CodecInfo codecInfo);

    public static final native void CodecInfo_desc_set(long j2, CodecInfo codecInfo, String str);

    public static final native short CodecInfo_priority_get(long j2, CodecInfo codecInfo);

    public static final native void CodecInfo_priority_set(long j2, CodecInfo codecInfo, short s);

    public static final native long CodecParamInfo_avgBps_get(long j2, CodecParamInfo codecParamInfo);

    public static final native void CodecParamInfo_avgBps_set(long j2, CodecParamInfo codecParamInfo, long j3);

    public static final native long CodecParamInfo_channelCnt_get(long j2, CodecParamInfo codecParamInfo);

    public static final native void CodecParamInfo_channelCnt_set(long j2, CodecParamInfo codecParamInfo, long j3);

    public static final native long CodecParamInfo_clockRate_get(long j2, CodecParamInfo codecParamInfo);

    public static final native void CodecParamInfo_clockRate_set(long j2, CodecParamInfo codecParamInfo, long j3);

    public static final native int CodecParamInfo_fmtId_get(long j2, CodecParamInfo codecParamInfo);

    public static final native void CodecParamInfo_fmtId_set(long j2, CodecParamInfo codecParamInfo, int i2);

    public static final native long CodecParamInfo_frameLen_get(long j2, CodecParamInfo codecParamInfo);

    public static final native void CodecParamInfo_frameLen_set(long j2, CodecParamInfo codecParamInfo, long j3);

    public static final native long CodecParamInfo_maxBps_get(long j2, CodecParamInfo codecParamInfo);

    public static final native void CodecParamInfo_maxBps_set(long j2, CodecParamInfo codecParamInfo, long j3);

    public static final native long CodecParamInfo_maxRxFrameSize_get(long j2, CodecParamInfo codecParamInfo);

    public static final native void CodecParamInfo_maxRxFrameSize_set(long j2, CodecParamInfo codecParamInfo, long j3);

    public static final native long CodecParamInfo_pcmBitsPerSample_get(long j2, CodecParamInfo codecParamInfo);

    public static final native void CodecParamInfo_pcmBitsPerSample_set(long j2, CodecParamInfo codecParamInfo, long j3);

    public static final native long CodecParamInfo_pt_get(long j2, CodecParamInfo codecParamInfo);

    public static final native void CodecParamInfo_pt_set(long j2, CodecParamInfo codecParamInfo, long j3);

    public static final native boolean CodecParamSetting_cng_get(long j2, CodecParamSetting codecParamSetting);

    public static final native void CodecParamSetting_cng_set(long j2, CodecParamSetting codecParamSetting, boolean z);

    public static final native long CodecParamSetting_decFmtp_get(long j2, CodecParamSetting codecParamSetting);

    public static final native void CodecParamSetting_decFmtp_set(long j2, CodecParamSetting codecParamSetting, long j3, CodecFmtpVector codecFmtpVector);

    public static final native long CodecParamSetting_encFmtp_get(long j2, CodecParamSetting codecParamSetting);

    public static final native void CodecParamSetting_encFmtp_set(long j2, CodecParamSetting codecParamSetting, long j3, CodecFmtpVector codecFmtpVector);

    public static final native long CodecParamSetting_frmPerPkt_get(long j2, CodecParamSetting codecParamSetting);

    public static final native void CodecParamSetting_frmPerPkt_set(long j2, CodecParamSetting codecParamSetting, long j3);

    public static final native boolean CodecParamSetting_penh_get(long j2, CodecParamSetting codecParamSetting);

    public static final native void CodecParamSetting_penh_set(long j2, CodecParamSetting codecParamSetting, boolean z);

    public static final native boolean CodecParamSetting_plc_get(long j2, CodecParamSetting codecParamSetting);

    public static final native void CodecParamSetting_plc_set(long j2, CodecParamSetting codecParamSetting, boolean z);

    public static final native boolean CodecParamSetting_reserved_get(long j2, CodecParamSetting codecParamSetting);

    public static final native void CodecParamSetting_reserved_set(long j2, CodecParamSetting codecParamSetting, boolean z);

    public static final native boolean CodecParamSetting_vad_get(long j2, CodecParamSetting codecParamSetting);

    public static final native void CodecParamSetting_vad_set(long j2, CodecParamSetting codecParamSetting, boolean z);

    public static final native long CodecParam_info_get(long j2, CodecParam codecParam);

    public static final native void CodecParam_info_set(long j2, CodecParam codecParam, long j3, CodecParamInfo codecParamInfo);

    public static final native long CodecParam_setting_get(long j2, CodecParam codecParam);

    public static final native void CodecParam_setting_set(long j2, CodecParam codecParam, long j3, CodecParamSetting codecParamSetting);

    public static final native long ConfPortInfo_format_get(long j2, ConfPortInfo confPortInfo);

    public static final native void ConfPortInfo_format_set(long j2, ConfPortInfo confPortInfo, long j3, MediaFormatAudio mediaFormatAudio);

    public static final native long ConfPortInfo_listeners_get(long j2, ConfPortInfo confPortInfo);

    public static final native void ConfPortInfo_listeners_set(long j2, ConfPortInfo confPortInfo, long j3, IntVector intVector);

    public static final native String ConfPortInfo_name_get(long j2, ConfPortInfo confPortInfo);

    public static final native void ConfPortInfo_name_set(long j2, ConfPortInfo confPortInfo, String str);

    public static final native int ConfPortInfo_portId_get(long j2, ConfPortInfo confPortInfo);

    public static final native void ConfPortInfo_portId_set(long j2, ConfPortInfo confPortInfo, int i2);

    public static final native float ConfPortInfo_rxLevelAdj_get(long j2, ConfPortInfo confPortInfo);

    public static final native void ConfPortInfo_rxLevelAdj_set(long j2, ConfPortInfo confPortInfo, float f2);

    public static final native float ConfPortInfo_txLevelAdj_get(long j2, ConfPortInfo confPortInfo);

    public static final native void ConfPortInfo_txLevelAdj_set(long j2, ConfPortInfo confPortInfo, float f2);

    public static final native long Endpoint_audDevManager(long j2, Endpoint endpoint);

    public static final native void Endpoint_change_ownership(Endpoint endpoint, long j2, boolean z);

    public static final native long Endpoint_codecEnum(long j2, Endpoint endpoint) throws Exception;

    public static final native long Endpoint_codecGetParam(long j2, Endpoint endpoint, String str) throws Exception;

    public static final native void Endpoint_codecSetParam(long j2, Endpoint endpoint, String str, long j3, CodecParam codecParam) throws Exception;

    public static final native void Endpoint_codecSetPriority(long j2, Endpoint endpoint, String str, short s) throws Exception;

    public static final native void Endpoint_director_connect(Endpoint endpoint, long j2, boolean z, boolean z2);

    public static final native void Endpoint_handleIpChange(long j2, Endpoint endpoint, long j3, IpChangeParam ipChangeParam) throws Exception;

    public static final native void Endpoint_hangupAllCalls(long j2, Endpoint endpoint);

    public static final native long Endpoint_instance() throws Exception;

    public static final native void Endpoint_libCreate(long j2, Endpoint endpoint) throws Exception;

    public static final native void Endpoint_libDestroy___SWIG_0(long j2, Endpoint endpoint, long j3) throws Exception;

    public static final native void Endpoint_libDestroy___SWIG_1(long j2, Endpoint endpoint) throws Exception;

    public static final native int Endpoint_libGetState(long j2, Endpoint endpoint);

    public static final native int Endpoint_libHandleEvents(long j2, Endpoint endpoint, long j3);

    public static final native void Endpoint_libInit(long j2, Endpoint endpoint, long j3, EpConfig epConfig) throws Exception;

    public static final native boolean Endpoint_libIsThreadRegistered(long j2, Endpoint endpoint);

    public static final native void Endpoint_libRegisterThread(long j2, Endpoint endpoint, String str) throws Exception;

    public static final native void Endpoint_libStart(long j2, Endpoint endpoint) throws Exception;

    public static final native void Endpoint_libStopWorkerThreads(long j2, Endpoint endpoint);

    public static final native long Endpoint_libVersion(long j2, Endpoint endpoint);

    public static final native long Endpoint_mediaActivePorts(long j2, Endpoint endpoint);

    public static final native void Endpoint_mediaAdd(long j2, Endpoint endpoint, long j3, AudioMedia audioMedia);

    public static final native long Endpoint_mediaEnumPorts(long j2, Endpoint endpoint) throws Exception;

    public static final native boolean Endpoint_mediaExists(long j2, Endpoint endpoint, long j3, AudioMedia audioMedia);

    public static final native long Endpoint_mediaMaxPorts(long j2, Endpoint endpoint);

    public static final native void Endpoint_mediaRemove(long j2, Endpoint endpoint, long j3, AudioMedia audioMedia);

    public static final native void Endpoint_natCancelCheckStunServers__SWIG_0(long j2, Endpoint endpoint, long j3, boolean z) throws Exception;

    public static final native void Endpoint_natCancelCheckStunServers__SWIG_1(long j2, Endpoint endpoint, long j3) throws Exception;

    public static final native void Endpoint_natCheckStunServers(long j2, Endpoint endpoint, long j3, StringVector stringVector, boolean z, long j4) throws Exception;

    public static final native void Endpoint_natDetectType(long j2, Endpoint endpoint) throws Exception;

    public static final native int Endpoint_natGetType(long j2, Endpoint endpoint) throws Exception;

    public static final native void Endpoint_natUpdateStunServers(long j2, Endpoint endpoint, long j3, StringVector stringVector, boolean z) throws Exception;

    public static final native void Endpoint_onIpChangeProgress(long j2, Endpoint endpoint, long j3, OnIpChangeProgressParam onIpChangeProgressParam);

    public static final native void Endpoint_onIpChangeProgressSwigExplicitEndpoint(long j2, Endpoint endpoint, long j3, OnIpChangeProgressParam onIpChangeProgressParam);

    public static final native void Endpoint_onNatCheckStunServersComplete(long j2, Endpoint endpoint, long j3, OnNatCheckStunServersCompleteParam onNatCheckStunServersCompleteParam);

    public static final native void Endpoint_onNatCheckStunServersCompleteSwigExplicitEndpoint(long j2, Endpoint endpoint, long j3, OnNatCheckStunServersCompleteParam onNatCheckStunServersCompleteParam);

    public static final native void Endpoint_onNatDetectionComplete(long j2, Endpoint endpoint, long j3, OnNatDetectionCompleteParam onNatDetectionCompleteParam);

    public static final native void Endpoint_onNatDetectionCompleteSwigExplicitEndpoint(long j2, Endpoint endpoint, long j3, OnNatDetectionCompleteParam onNatDetectionCompleteParam);

    public static final native void Endpoint_onSelectAccount(long j2, Endpoint endpoint, long j3, OnSelectAccountParam onSelectAccountParam);

    public static final native void Endpoint_onSelectAccountSwigExplicitEndpoint(long j2, Endpoint endpoint, long j3, OnSelectAccountParam onSelectAccountParam);

    public static final native void Endpoint_onTimer(long j2, Endpoint endpoint, long j3, OnTimerParam onTimerParam);

    public static final native void Endpoint_onTimerSwigExplicitEndpoint(long j2, Endpoint endpoint, long j3, OnTimerParam onTimerParam);

    public static final native void Endpoint_onTransportState(long j2, Endpoint endpoint, long j3, OnTransportStateParam onTransportStateParam);

    public static final native void Endpoint_onTransportStateSwigExplicitEndpoint(long j2, Endpoint endpoint, long j3, OnTransportStateParam onTransportStateParam);

    public static final native void Endpoint_transportClose(long j2, Endpoint endpoint, int i2) throws Exception;

    public static final native int Endpoint_transportCreate(long j2, Endpoint endpoint, int i2, long j3, TransportConfig transportConfig) throws Exception;

    public static final native long Endpoint_transportEnum(long j2, Endpoint endpoint) throws Exception;

    public static final native long Endpoint_transportGetInfo(long j2, Endpoint endpoint, int i2) throws Exception;

    public static final native void Endpoint_transportSetEnable(long j2, Endpoint endpoint, int i2, boolean z) throws Exception;

    public static final native void Endpoint_transportShutdown(long j2, Endpoint endpoint, long j3) throws Exception;

    public static final native void Endpoint_utilAddPendingJob(long j2, Endpoint endpoint, long j3, PendingJob pendingJob);

    public static final native void Endpoint_utilLogWrite__SWIG_0(long j2, Endpoint endpoint, int i2, String str, String str2);

    public static final native void Endpoint_utilLogWrite__SWIG_1(long j2, Endpoint endpoint, long j3, LogEntry logEntry);

    public static final native long Endpoint_utilSslGetAvailableCiphers(long j2, Endpoint endpoint) throws Exception;

    public static final native String Endpoint_utilStrError(long j2, Endpoint endpoint, int i2);

    public static final native void Endpoint_utilTimerCancel(long j2, Endpoint endpoint, long j3);

    public static final native long Endpoint_utilTimerSchedule(long j2, Endpoint endpoint, long j3, long j4) throws Exception;

    public static final native int Endpoint_utilVerifySipUri(long j2, Endpoint endpoint, String str);

    public static final native int Endpoint_utilVerifyUri(long j2, Endpoint endpoint, String str);

    public static final native long EpConfig_logConfig_get(long j2, EpConfig epConfig);

    public static final native void EpConfig_logConfig_set(long j2, EpConfig epConfig, long j3, LogConfig logConfig);

    public static final native long EpConfig_medConfig_get(long j2, EpConfig epConfig);

    public static final native void EpConfig_medConfig_set(long j2, EpConfig epConfig, long j3, MediaConfig mediaConfig);

    public static final native long EpConfig_uaConfig_get(long j2, EpConfig epConfig);

    public static final native void EpConfig_uaConfig_set(long j2, EpConfig epConfig, long j3, UaConfig uaConfig);

    public static final native String Error_info__SWIG_0(long j2, Error error, boolean z);

    public static final native String Error_info__SWIG_1(long j2, Error error);

    public static final native String Error_reason_get(long j2, Error error);

    public static final native void Error_reason_set(long j2, Error error, String str);

    public static final native String Error_srcFile_get(long j2, Error error);

    public static final native void Error_srcFile_set(long j2, Error error, String str);

    public static final native int Error_srcLine_get(long j2, Error error);

    public static final native void Error_srcLine_set(long j2, Error error, int i2);

    public static final native int Error_status_get(long j2, Error error);

    public static final native void Error_status_set(long j2, Error error, int i2);

    public static final native String Error_title_get(long j2, Error error);

    public static final native void Error_title_set(long j2, Error error, String str);

    public static final native void FindBuddyMatch_change_ownership(FindBuddyMatch findBuddyMatch, long j2, boolean z);

    public static final native void FindBuddyMatch_director_connect(FindBuddyMatch findBuddyMatch, long j2, boolean z, boolean z2);

    public static final native boolean FindBuddyMatch_match(long j2, FindBuddyMatch findBuddyMatch, String str, long j3, Buddy buddy);

    public static final native boolean FindBuddyMatch_matchSwigExplicitFindBuddyMatch(long j2, FindBuddyMatch findBuddyMatch, String str, long j3, Buddy buddy);

    public static final native int INVALID_ID_get();

    public static final native void IntVector_add(long j2, IntVector intVector, int i2);

    public static final native long IntVector_capacity(long j2, IntVector intVector);

    public static final native void IntVector_clear(long j2, IntVector intVector);

    public static final native int IntVector_get(long j2, IntVector intVector, int i2);

    public static final native boolean IntVector_isEmpty(long j2, IntVector intVector);

    public static final native void IntVector_reserve(long j2, IntVector intVector, long j3);

    public static final native void IntVector_set(long j2, IntVector intVector, int i2, int i3);

    public static final native long IntVector_size(long j2, IntVector intVector);

    public static final native long IpChangeParam_restartLisDelay_get(long j2, IpChangeParam ipChangeParam);

    public static final native void IpChangeParam_restartLisDelay_set(long j2, IpChangeParam ipChangeParam, long j3);

    public static final native boolean IpChangeParam_restartListener_get(long j2, IpChangeParam ipChangeParam);

    public static final native void IpChangeParam_restartListener_set(long j2, IpChangeParam ipChangeParam, boolean z);

    public static final native long JbufState_avgBurst_get(long j2, JbufState jbufState);

    public static final native void JbufState_avgBurst_set(long j2, JbufState jbufState, long j3);

    public static final native long JbufState_avgDelayMsec_get(long j2, JbufState jbufState);

    public static final native void JbufState_avgDelayMsec_set(long j2, JbufState jbufState, long j3);

    public static final native long JbufState_burst_get(long j2, JbufState jbufState);

    public static final native void JbufState_burst_set(long j2, JbufState jbufState, long j3);

    public static final native long JbufState_devDelayMsec_get(long j2, JbufState jbufState);

    public static final native void JbufState_devDelayMsec_set(long j2, JbufState jbufState, long j3);

    public static final native long JbufState_discard_get(long j2, JbufState jbufState);

    public static final native void JbufState_discard_set(long j2, JbufState jbufState, long j3);

    public static final native long JbufState_empty_get(long j2, JbufState jbufState);

    public static final native void JbufState_empty_set(long j2, JbufState jbufState, long j3);

    public static final native long JbufState_frameSize_get(long j2, JbufState jbufState);

    public static final native void JbufState_frameSize_set(long j2, JbufState jbufState, long j3);

    public static final native long JbufState_lost_get(long j2, JbufState jbufState);

    public static final native void JbufState_lost_set(long j2, JbufState jbufState, long j3);

    public static final native long JbufState_maxDelayMsec_get(long j2, JbufState jbufState);

    public static final native void JbufState_maxDelayMsec_set(long j2, JbufState jbufState, long j3);

    public static final native long JbufState_maxPrefetch_get(long j2, JbufState jbufState);

    public static final native void JbufState_maxPrefetch_set(long j2, JbufState jbufState, long j3);

    public static final native long JbufState_minDelayMsec_get(long j2, JbufState jbufState);

    public static final native void JbufState_minDelayMsec_set(long j2, JbufState jbufState, long j3);

    public static final native long JbufState_minPrefetch_get(long j2, JbufState jbufState);

    public static final native void JbufState_minPrefetch_set(long j2, JbufState jbufState, long j3);

    public static final native long JbufState_prefetch_get(long j2, JbufState jbufState);

    public static final native void JbufState_prefetch_set(long j2, JbufState jbufState, long j3);

    public static final native long JbufState_size_get(long j2, JbufState jbufState);

    public static final native void JbufState_size_set(long j2, JbufState jbufState, long j3);

    public static final native long LogConfig_consoleLevel_get(long j2, LogConfig logConfig);

    public static final native void LogConfig_consoleLevel_set(long j2, LogConfig logConfig, long j3);

    public static final native long LogConfig_decor_get(long j2, LogConfig logConfig);

    public static final native void LogConfig_decor_set(long j2, LogConfig logConfig, long j3);

    public static final native long LogConfig_fileFlags_get(long j2, LogConfig logConfig);

    public static final native void LogConfig_fileFlags_set(long j2, LogConfig logConfig, long j3);

    public static final native String LogConfig_filename_get(long j2, LogConfig logConfig);

    public static final native void LogConfig_filename_set(long j2, LogConfig logConfig, String str);

    public static final native long LogConfig_level_get(long j2, LogConfig logConfig);

    public static final native void LogConfig_level_set(long j2, LogConfig logConfig, long j3);

    public static final native long LogConfig_msgLogging_get(long j2, LogConfig logConfig);

    public static final native void LogConfig_msgLogging_set(long j2, LogConfig logConfig, long j3);

    public static final native long LogConfig_writer_get(long j2, LogConfig logConfig);

    public static final native void LogConfig_writer_set(long j2, LogConfig logConfig, long j3, LogWriter logWriter);

    public static final native int LogEntry_level_get(long j2, LogEntry logEntry);

    public static final native void LogEntry_level_set(long j2, LogEntry logEntry, int i2);

    public static final native String LogEntry_msg_get(long j2, LogEntry logEntry);

    public static final native void LogEntry_msg_set(long j2, LogEntry logEntry, String str);

    public static final native int LogEntry_threadId_get(long j2, LogEntry logEntry);

    public static final native void LogEntry_threadId_set(long j2, LogEntry logEntry, int i2);

    public static final native String LogEntry_threadName_get(long j2, LogEntry logEntry);

    public static final native void LogEntry_threadName_set(long j2, LogEntry logEntry, String str);

    public static final native void LogWriter_change_ownership(LogWriter logWriter, long j2, boolean z);

    public static final native void LogWriter_director_connect(LogWriter logWriter, long j2, boolean z, boolean z2);

    public static final native void LogWriter_write(long j2, LogWriter logWriter, long j3, LogEntry logEntry);

    public static final native long LossType_burst_get(long j2, LossType lossType);

    public static final native void LossType_burst_set(long j2, LossType lossType, long j3);

    public static final native long LossType_random_get(long j2, LossType lossType);

    public static final native void LossType_random_set(long j2, LossType lossType, long j3);

    public static final native int MathStat_last_get(long j2, MathStat mathStat);

    public static final native void MathStat_last_set(long j2, MathStat mathStat, int i2);

    public static final native int MathStat_max_get(long j2, MathStat mathStat);

    public static final native void MathStat_max_set(long j2, MathStat mathStat, int i2);

    public static final native int MathStat_mean_get(long j2, MathStat mathStat);

    public static final native void MathStat_mean_set(long j2, MathStat mathStat, int i2);

    public static final native int MathStat_min_get(long j2, MathStat mathStat);

    public static final native void MathStat_min_set(long j2, MathStat mathStat, int i2);

    public static final native int MathStat_n_get(long j2, MathStat mathStat);

    public static final native void MathStat_n_set(long j2, MathStat mathStat, int i2);

    public static final native long MediaConfig_audioFramePtime_get(long j2, MediaConfig mediaConfig);

    public static final native void MediaConfig_audioFramePtime_set(long j2, MediaConfig mediaConfig, long j3);

    public static final native long MediaConfig_channelCount_get(long j2, MediaConfig mediaConfig);

    public static final native void MediaConfig_channelCount_set(long j2, MediaConfig mediaConfig, long j3);

    public static final native long MediaConfig_clockRate_get(long j2, MediaConfig mediaConfig);

    public static final native void MediaConfig_clockRate_set(long j2, MediaConfig mediaConfig, long j3);

    public static final native long MediaConfig_ecOptions_get(long j2, MediaConfig mediaConfig);

    public static final native void MediaConfig_ecOptions_set(long j2, MediaConfig mediaConfig, long j3);

    public static final native long MediaConfig_ecTailLen_get(long j2, MediaConfig mediaConfig);

    public static final native void MediaConfig_ecTailLen_set(long j2, MediaConfig mediaConfig, long j3);

    public static final native boolean MediaConfig_hasIoqueue_get(long j2, MediaConfig mediaConfig);

    public static final native void MediaConfig_hasIoqueue_set(long j2, MediaConfig mediaConfig, boolean z);

    public static final native long MediaConfig_ilbcMode_get(long j2, MediaConfig mediaConfig);

    public static final native void MediaConfig_ilbcMode_set(long j2, MediaConfig mediaConfig, long j3);

    public static final native int MediaConfig_jbInit_get(long j2, MediaConfig mediaConfig);

    public static final native void MediaConfig_jbInit_set(long j2, MediaConfig mediaConfig, int i2);

    public static final native int MediaConfig_jbMaxPre_get(long j2, MediaConfig mediaConfig);

    public static final native void MediaConfig_jbMaxPre_set(long j2, MediaConfig mediaConfig, int i2);

    public static final native int MediaConfig_jbMax_get(long j2, MediaConfig mediaConfig);

    public static final native void MediaConfig_jbMax_set(long j2, MediaConfig mediaConfig, int i2);

    public static final native int MediaConfig_jbMinPre_get(long j2, MediaConfig mediaConfig);

    public static final native void MediaConfig_jbMinPre_set(long j2, MediaConfig mediaConfig, int i2);

    public static final native long MediaConfig_maxMediaPorts_get(long j2, MediaConfig mediaConfig);

    public static final native void MediaConfig_maxMediaPorts_set(long j2, MediaConfig mediaConfig, long j3);

    public static final native boolean MediaConfig_noVad_get(long j2, MediaConfig mediaConfig);

    public static final native void MediaConfig_noVad_set(long j2, MediaConfig mediaConfig, boolean z);

    public static final native long MediaConfig_ptime_get(long j2, MediaConfig mediaConfig);

    public static final native void MediaConfig_ptime_set(long j2, MediaConfig mediaConfig, long j3);

    public static final native long MediaConfig_quality_get(long j2, MediaConfig mediaConfig);

    public static final native void MediaConfig_quality_set(long j2, MediaConfig mediaConfig, long j3);

    public static final native long MediaConfig_rxDropPct_get(long j2, MediaConfig mediaConfig);

    public static final native void MediaConfig_rxDropPct_set(long j2, MediaConfig mediaConfig, long j3);

    public static final native int MediaConfig_sndAutoCloseTime_get(long j2, MediaConfig mediaConfig);

    public static final native void MediaConfig_sndAutoCloseTime_set(long j2, MediaConfig mediaConfig, int i2);

    public static final native long MediaConfig_sndClockRate_get(long j2, MediaConfig mediaConfig);

    public static final native void MediaConfig_sndClockRate_set(long j2, MediaConfig mediaConfig, long j3);

    public static final native long MediaConfig_sndPlayLatency_get(long j2, MediaConfig mediaConfig);

    public static final native void MediaConfig_sndPlayLatency_set(long j2, MediaConfig mediaConfig, long j3);

    public static final native long MediaConfig_sndRecLatency_get(long j2, MediaConfig mediaConfig);

    public static final native void MediaConfig_sndRecLatency_set(long j2, MediaConfig mediaConfig, long j3);

    public static final native long MediaConfig_threadCnt_get(long j2, MediaConfig mediaConfig);

    public static final native void MediaConfig_threadCnt_set(long j2, MediaConfig mediaConfig, long j3);

    public static final native long MediaConfig_txDropPct_get(long j2, MediaConfig mediaConfig);

    public static final native void MediaConfig_txDropPct_set(long j2, MediaConfig mediaConfig, long j3);

    public static final native boolean MediaConfig_vidPreviewEnableNative_get(long j2, MediaConfig mediaConfig);

    public static final native void MediaConfig_vidPreviewEnableNative_set(long j2, MediaConfig mediaConfig, boolean z);

    public static final native long MediaEventData_fmtChanged_get(long j2, MediaEventData mediaEventData);

    public static final native void MediaEventData_fmtChanged_set(long j2, MediaEventData mediaEventData, long j3, MediaFmtChangedEvent mediaFmtChangedEvent);

    public static final native long MediaEventData_ptr_get(long j2, MediaEventData mediaEventData);

    public static final native void MediaEventData_ptr_set(long j2, MediaEventData mediaEventData, long j3);

    public static final native long MediaEvent_data_get(long j2, MediaEvent mediaEvent);

    public static final native void MediaEvent_data_set(long j2, MediaEvent mediaEvent, long j3, MediaEventData mediaEventData);

    public static final native long MediaEvent_pjMediaEvent_get(long j2, MediaEvent mediaEvent);

    public static final native void MediaEvent_pjMediaEvent_set(long j2, MediaEvent mediaEvent, long j3);

    public static final native int MediaEvent_type_get(long j2, MediaEvent mediaEvent);

    public static final native void MediaEvent_type_set(long j2, MediaEvent mediaEvent, int i2);

    public static final native long MediaFmtChangedEvent_newHeight_get(long j2, MediaFmtChangedEvent mediaFmtChangedEvent);

    public static final native void MediaFmtChangedEvent_newHeight_set(long j2, MediaFmtChangedEvent mediaFmtChangedEvent, long j3);

    public static final native long MediaFmtChangedEvent_newWidth_get(long j2, MediaFmtChangedEvent mediaFmtChangedEvent);

    public static final native void MediaFmtChangedEvent_newWidth_set(long j2, MediaFmtChangedEvent mediaFmtChangedEvent, long j3);

    public static final native long MediaFormatAudio_SWIGUpcast(long j2);

    public static final native long MediaFormatAudio_avgBps_get(long j2, MediaFormatAudio mediaFormatAudio);

    public static final native void MediaFormatAudio_avgBps_set(long j2, MediaFormatAudio mediaFormatAudio, long j3);

    public static final native long MediaFormatAudio_bitsPerSample_get(long j2, MediaFormatAudio mediaFormatAudio);

    public static final native void MediaFormatAudio_bitsPerSample_set(long j2, MediaFormatAudio mediaFormatAudio, long j3);

    public static final native long MediaFormatAudio_channelCount_get(long j2, MediaFormatAudio mediaFormatAudio);

    public static final native void MediaFormatAudio_channelCount_set(long j2, MediaFormatAudio mediaFormatAudio, long j3);

    public static final native long MediaFormatAudio_clockRate_get(long j2, MediaFormatAudio mediaFormatAudio);

    public static final native void MediaFormatAudio_clockRate_set(long j2, MediaFormatAudio mediaFormatAudio, long j3);

    public static final native long MediaFormatAudio_frameTimeUsec_get(long j2, MediaFormatAudio mediaFormatAudio);

    public static final native void MediaFormatAudio_frameTimeUsec_set(long j2, MediaFormatAudio mediaFormatAudio, long j3);

    public static final native long MediaFormatAudio_maxBps_get(long j2, MediaFormatAudio mediaFormatAudio);

    public static final native void MediaFormatAudio_maxBps_set(long j2, MediaFormatAudio mediaFormatAudio, long j3);

    public static final native void MediaFormatVector_add(long j2, MediaFormatVector mediaFormatVector, long j3, MediaFormat mediaFormat);

    public static final native long MediaFormatVector_capacity(long j2, MediaFormatVector mediaFormatVector);

    public static final native void MediaFormatVector_clear(long j2, MediaFormatVector mediaFormatVector);

    public static final native long MediaFormatVector_get(long j2, MediaFormatVector mediaFormatVector, int i2);

    public static final native boolean MediaFormatVector_isEmpty(long j2, MediaFormatVector mediaFormatVector);

    public static final native void MediaFormatVector_reserve(long j2, MediaFormatVector mediaFormatVector, long j3);

    public static final native void MediaFormatVector_set(long j2, MediaFormatVector mediaFormatVector, int i2, long j3, MediaFormat mediaFormat);

    public static final native long MediaFormatVector_size(long j2, MediaFormatVector mediaFormatVector);

    public static final native long MediaFormat_id_get(long j2, MediaFormat mediaFormat);

    public static final native void MediaFormat_id_set(long j2, MediaFormat mediaFormat, long j3);

    public static final native int MediaFormat_type_get(long j2, MediaFormat mediaFormat);

    public static final native void MediaFormat_type_set(long j2, MediaFormat mediaFormat, int i2);

    public static final native String MediaTransportInfo_srcRtcpName_get(long j2, MediaTransportInfo mediaTransportInfo);

    public static final native void MediaTransportInfo_srcRtcpName_set(long j2, MediaTransportInfo mediaTransportInfo, String str);

    public static final native String MediaTransportInfo_srcRtpName_get(long j2, MediaTransportInfo mediaTransportInfo);

    public static final native void MediaTransportInfo_srcRtpName_set(long j2, MediaTransportInfo mediaTransportInfo, String str);

    public static final native int Media_getType(long j2, Media media);

    public static final native long OnBuddyEvSubStateParam_e_get(long j2, OnBuddyEvSubStateParam onBuddyEvSubStateParam);

    public static final native void OnBuddyEvSubStateParam_e_set(long j2, OnBuddyEvSubStateParam onBuddyEvSubStateParam, long j3, SipEvent sipEvent);

    public static final native long OnCallMediaEventParam_ev_get(long j2, OnCallMediaEventParam onCallMediaEventParam);

    public static final native void OnCallMediaEventParam_ev_set(long j2, OnCallMediaEventParam onCallMediaEventParam, long j3, MediaEvent mediaEvent);

    public static final native long OnCallMediaEventParam_medIdx_get(long j2, OnCallMediaEventParam onCallMediaEventParam);

    public static final native void OnCallMediaEventParam_medIdx_set(long j2, OnCallMediaEventParam onCallMediaEventParam, long j3);

    public static final native long OnCallMediaTransportStateParam_medIdx_get(long j2, OnCallMediaTransportStateParam onCallMediaTransportStateParam);

    public static final native void OnCallMediaTransportStateParam_medIdx_set(long j2, OnCallMediaTransportStateParam onCallMediaTransportStateParam, long j3);

    public static final native int OnCallMediaTransportStateParam_sipErrorCode_get(long j2, OnCallMediaTransportStateParam onCallMediaTransportStateParam);

    public static final native void OnCallMediaTransportStateParam_sipErrorCode_set(long j2, OnCallMediaTransportStateParam onCallMediaTransportStateParam, int i2);

    public static final native int OnCallMediaTransportStateParam_state_get(long j2, OnCallMediaTransportStateParam onCallMediaTransportStateParam);

    public static final native void OnCallMediaTransportStateParam_state_set(long j2, OnCallMediaTransportStateParam onCallMediaTransportStateParam, int i2);

    public static final native int OnCallMediaTransportStateParam_status_get(long j2, OnCallMediaTransportStateParam onCallMediaTransportStateParam);

    public static final native void OnCallMediaTransportStateParam_status_set(long j2, OnCallMediaTransportStateParam onCallMediaTransportStateParam, int i2);

    public static final native long OnCallRedirectedParam_e_get(long j2, OnCallRedirectedParam onCallRedirectedParam);

    public static final native void OnCallRedirectedParam_e_set(long j2, OnCallRedirectedParam onCallRedirectedParam, long j3, SipEvent sipEvent);

    public static final native String OnCallRedirectedParam_targetUri_get(long j2, OnCallRedirectedParam onCallRedirectedParam);

    public static final native void OnCallRedirectedParam_targetUri_set(long j2, OnCallRedirectedParam onCallRedirectedParam, String str);

    public static final native long OnCallReplaceRequestParam_opt_get(long j2, OnCallReplaceRequestParam onCallReplaceRequestParam);

    public static final native void OnCallReplaceRequestParam_opt_set(long j2, OnCallReplaceRequestParam onCallReplaceRequestParam, long j3, CallSetting callSetting);

    public static final native long OnCallReplaceRequestParam_rdata_get(long j2, OnCallReplaceRequestParam onCallReplaceRequestParam);

    public static final native void OnCallReplaceRequestParam_rdata_set(long j2, OnCallReplaceRequestParam onCallReplaceRequestParam, long j3, SipRxData sipRxData);

    public static final native String OnCallReplaceRequestParam_reason_get(long j2, OnCallReplaceRequestParam onCallReplaceRequestParam);

    public static final native void OnCallReplaceRequestParam_reason_set(long j2, OnCallReplaceRequestParam onCallReplaceRequestParam, String str);

    public static final native int OnCallReplaceRequestParam_statusCode_get(long j2, OnCallReplaceRequestParam onCallReplaceRequestParam);

    public static final native void OnCallReplaceRequestParam_statusCode_set(long j2, OnCallReplaceRequestParam onCallReplaceRequestParam, int i2);

    public static final native int OnCallReplacedParam_newCallId_get(long j2, OnCallReplacedParam onCallReplacedParam);

    public static final native void OnCallReplacedParam_newCallId_set(long j2, OnCallReplacedParam onCallReplacedParam, int i2);

    public static final native long OnCallRxOfferParam_offer_get(long j2, OnCallRxOfferParam onCallRxOfferParam);

    public static final native void OnCallRxOfferParam_offer_set(long j2, OnCallRxOfferParam onCallRxOfferParam, long j3, SdpSession sdpSession);

    public static final native long OnCallRxOfferParam_opt_get(long j2, OnCallRxOfferParam onCallRxOfferParam);

    public static final native void OnCallRxOfferParam_opt_set(long j2, OnCallRxOfferParam onCallRxOfferParam, long j3, CallSetting callSetting);

    public static final native int OnCallRxOfferParam_statusCode_get(long j2, OnCallRxOfferParam onCallRxOfferParam);

    public static final native void OnCallRxOfferParam_statusCode_set(long j2, OnCallRxOfferParam onCallRxOfferParam, int i2);

    public static final native long OnCallSdpCreatedParam_remSdp_get(long j2, OnCallSdpCreatedParam onCallSdpCreatedParam);

    public static final native void OnCallSdpCreatedParam_remSdp_set(long j2, OnCallSdpCreatedParam onCallSdpCreatedParam, long j3, SdpSession sdpSession);

    public static final native long OnCallSdpCreatedParam_sdp_get(long j2, OnCallSdpCreatedParam onCallSdpCreatedParam);

    public static final native void OnCallSdpCreatedParam_sdp_set(long j2, OnCallSdpCreatedParam onCallSdpCreatedParam, long j3, SdpSession sdpSession);

    public static final native long OnCallStateParam_e_get(long j2, OnCallStateParam onCallStateParam);

    public static final native void OnCallStateParam_e_set(long j2, OnCallStateParam onCallStateParam, long j3, SipEvent sipEvent);

    public static final native String OnCallTransferRequestParam_dstUri_get(long j2, OnCallTransferRequestParam onCallTransferRequestParam);

    public static final native void OnCallTransferRequestParam_dstUri_set(long j2, OnCallTransferRequestParam onCallTransferRequestParam, String str);

    public static final native long OnCallTransferRequestParam_opt_get(long j2, OnCallTransferRequestParam onCallTransferRequestParam);

    public static final native void OnCallTransferRequestParam_opt_set(long j2, OnCallTransferRequestParam onCallTransferRequestParam, long j3, CallSetting callSetting);

    public static final native int OnCallTransferRequestParam_statusCode_get(long j2, OnCallTransferRequestParam onCallTransferRequestParam);

    public static final native void OnCallTransferRequestParam_statusCode_set(long j2, OnCallTransferRequestParam onCallTransferRequestParam, int i2);

    public static final native boolean OnCallTransferStatusParam_cont_get(long j2, OnCallTransferStatusParam onCallTransferStatusParam);

    public static final native void OnCallTransferStatusParam_cont_set(long j2, OnCallTransferStatusParam onCallTransferStatusParam, boolean z);

    public static final native boolean OnCallTransferStatusParam_finalNotify_get(long j2, OnCallTransferStatusParam onCallTransferStatusParam);

    public static final native void OnCallTransferStatusParam_finalNotify_set(long j2, OnCallTransferStatusParam onCallTransferStatusParam, boolean z);

    public static final native String OnCallTransferStatusParam_reason_get(long j2, OnCallTransferStatusParam onCallTransferStatusParam);

    public static final native void OnCallTransferStatusParam_reason_set(long j2, OnCallTransferStatusParam onCallTransferStatusParam, String str);

    public static final native int OnCallTransferStatusParam_statusCode_get(long j2, OnCallTransferStatusParam onCallTransferStatusParam);

    public static final native void OnCallTransferStatusParam_statusCode_set(long j2, OnCallTransferStatusParam onCallTransferStatusParam, int i2);

    public static final native long OnCallTsxStateParam_e_get(long j2, OnCallTsxStateParam onCallTsxStateParam);

    public static final native void OnCallTsxStateParam_e_set(long j2, OnCallTsxStateParam onCallTsxStateParam, long j3, SipEvent sipEvent);

    public static final native long OnCallTxOfferParam_opt_get(long j2, OnCallTxOfferParam onCallTxOfferParam);

    public static final native void OnCallTxOfferParam_opt_set(long j2, OnCallTxOfferParam onCallTxOfferParam, long j3, CallSetting callSetting);

    public static final native long OnCreateMediaTransportParam_flags_get(long j2, OnCreateMediaTransportParam onCreateMediaTransportParam);

    public static final native void OnCreateMediaTransportParam_flags_set(long j2, OnCreateMediaTransportParam onCreateMediaTransportParam, long j3);

    public static final native long OnCreateMediaTransportParam_mediaIdx_get(long j2, OnCreateMediaTransportParam onCreateMediaTransportParam);

    public static final native void OnCreateMediaTransportParam_mediaIdx_set(long j2, OnCreateMediaTransportParam onCreateMediaTransportParam, long j3);

    public static final native long OnCreateMediaTransportParam_mediaTp_get(long j2, OnCreateMediaTransportParam onCreateMediaTransportParam);

    public static final native void OnCreateMediaTransportParam_mediaTp_set(long j2, OnCreateMediaTransportParam onCreateMediaTransportParam, long j3);

    public static final native long OnCreateMediaTransportSrtpParam_cryptos_get(long j2, OnCreateMediaTransportSrtpParam onCreateMediaTransportSrtpParam);

    public static final native void OnCreateMediaTransportSrtpParam_cryptos_set(long j2, OnCreateMediaTransportSrtpParam onCreateMediaTransportSrtpParam, long j3);

    public static final native long OnCreateMediaTransportSrtpParam_mediaIdx_get(long j2, OnCreateMediaTransportSrtpParam onCreateMediaTransportSrtpParam);

    public static final native void OnCreateMediaTransportSrtpParam_mediaIdx_set(long j2, OnCreateMediaTransportSrtpParam onCreateMediaTransportSrtpParam, long j3);

    public static final native int OnCreateMediaTransportSrtpParam_srtpUse_get(long j2, OnCreateMediaTransportSrtpParam onCreateMediaTransportSrtpParam);

    public static final native void OnCreateMediaTransportSrtpParam_srtpUse_set(long j2, OnCreateMediaTransportSrtpParam onCreateMediaTransportSrtpParam, int i2);

    public static final native String OnDtmfDigitParam_digit_get(long j2, OnDtmfDigitParam onDtmfDigitParam);

    public static final native void OnDtmfDigitParam_digit_set(long j2, OnDtmfDigitParam onDtmfDigitParam, String str);

    public static final native int OnIncomingCallParam_callId_get(long j2, OnIncomingCallParam onIncomingCallParam);

    public static final native void OnIncomingCallParam_callId_set(long j2, OnIncomingCallParam onIncomingCallParam, int i2);

    public static final native long OnIncomingCallParam_rdata_get(long j2, OnIncomingCallParam onIncomingCallParam);

    public static final native void OnIncomingCallParam_rdata_set(long j2, OnIncomingCallParam onIncomingCallParam, long j3, SipRxData sipRxData);

    public static final native int OnIncomingSubscribeParam_code_get(long j2, OnIncomingSubscribeParam onIncomingSubscribeParam);

    public static final native void OnIncomingSubscribeParam_code_set(long j2, OnIncomingSubscribeParam onIncomingSubscribeParam, int i2);

    public static final native String OnIncomingSubscribeParam_fromUri_get(long j2, OnIncomingSubscribeParam onIncomingSubscribeParam);

    public static final native void OnIncomingSubscribeParam_fromUri_set(long j2, OnIncomingSubscribeParam onIncomingSubscribeParam, String str);

    public static final native long OnIncomingSubscribeParam_rdata_get(long j2, OnIncomingSubscribeParam onIncomingSubscribeParam);

    public static final native void OnIncomingSubscribeParam_rdata_set(long j2, OnIncomingSubscribeParam onIncomingSubscribeParam, long j3, SipRxData sipRxData);

    public static final native String OnIncomingSubscribeParam_reason_get(long j2, OnIncomingSubscribeParam onIncomingSubscribeParam);

    public static final native void OnIncomingSubscribeParam_reason_set(long j2, OnIncomingSubscribeParam onIncomingSubscribeParam, String str);

    public static final native long OnIncomingSubscribeParam_srvPres_get(long j2, OnIncomingSubscribeParam onIncomingSubscribeParam);

    public static final native void OnIncomingSubscribeParam_srvPres_set(long j2, OnIncomingSubscribeParam onIncomingSubscribeParam, long j3);

    public static final native long OnIncomingSubscribeParam_txOption_get(long j2, OnIncomingSubscribeParam onIncomingSubscribeParam);

    public static final native void OnIncomingSubscribeParam_txOption_set(long j2, OnIncomingSubscribeParam onIncomingSubscribeParam, long j3, SipTxOption sipTxOption);

    public static final native String OnInstantMessageParam_contactUri_get(long j2, OnInstantMessageParam onInstantMessageParam);

    public static final native void OnInstantMessageParam_contactUri_set(long j2, OnInstantMessageParam onInstantMessageParam, String str);

    public static final native String OnInstantMessageParam_contentType_get(long j2, OnInstantMessageParam onInstantMessageParam);

    public static final native void OnInstantMessageParam_contentType_set(long j2, OnInstantMessageParam onInstantMessageParam, String str);

    public static final native String OnInstantMessageParam_fromUri_get(long j2, OnInstantMessageParam onInstantMessageParam);

    public static final native void OnInstantMessageParam_fromUri_set(long j2, OnInstantMessageParam onInstantMessageParam, String str);

    public static final native String OnInstantMessageParam_msgBody_get(long j2, OnInstantMessageParam onInstantMessageParam);

    public static final native void OnInstantMessageParam_msgBody_set(long j2, OnInstantMessageParam onInstantMessageParam, String str);

    public static final native long OnInstantMessageParam_rdata_get(long j2, OnInstantMessageParam onInstantMessageParam);

    public static final native void OnInstantMessageParam_rdata_set(long j2, OnInstantMessageParam onInstantMessageParam, long j3, SipRxData sipRxData);

    public static final native String OnInstantMessageParam_toUri_get(long j2, OnInstantMessageParam onInstantMessageParam);

    public static final native void OnInstantMessageParam_toUri_set(long j2, OnInstantMessageParam onInstantMessageParam, String str);

    public static final native int OnInstantMessageStatusParam_code_get(long j2, OnInstantMessageStatusParam onInstantMessageStatusParam);

    public static final native void OnInstantMessageStatusParam_code_set(long j2, OnInstantMessageStatusParam onInstantMessageStatusParam, int i2);

    public static final native String OnInstantMessageStatusParam_msgBody_get(long j2, OnInstantMessageStatusParam onInstantMessageStatusParam);

    public static final native void OnInstantMessageStatusParam_msgBody_set(long j2, OnInstantMessageStatusParam onInstantMessageStatusParam, String str);

    public static final native long OnInstantMessageStatusParam_rdata_get(long j2, OnInstantMessageStatusParam onInstantMessageStatusParam);

    public static final native void OnInstantMessageStatusParam_rdata_set(long j2, OnInstantMessageStatusParam onInstantMessageStatusParam, long j3, SipRxData sipRxData);

    public static final native String OnInstantMessageStatusParam_reason_get(long j2, OnInstantMessageStatusParam onInstantMessageStatusParam);

    public static final native void OnInstantMessageStatusParam_reason_set(long j2, OnInstantMessageStatusParam onInstantMessageStatusParam, String str);

    public static final native String OnInstantMessageStatusParam_toUri_get(long j2, OnInstantMessageStatusParam onInstantMessageStatusParam);

    public static final native void OnInstantMessageStatusParam_toUri_set(long j2, OnInstantMessageStatusParam onInstantMessageStatusParam, String str);

    public static final native long OnInstantMessageStatusParam_userData_get(long j2, OnInstantMessageStatusParam onInstantMessageStatusParam);

    public static final native void OnInstantMessageStatusParam_userData_set(long j2, OnInstantMessageStatusParam onInstantMessageStatusParam, long j3);

    public static final native int OnIpChangeProgressParam_accId_get(long j2, OnIpChangeProgressParam onIpChangeProgressParam);

    public static final native void OnIpChangeProgressParam_accId_set(long j2, OnIpChangeProgressParam onIpChangeProgressParam, int i2);

    public static final native int OnIpChangeProgressParam_callId_get(long j2, OnIpChangeProgressParam onIpChangeProgressParam);

    public static final native void OnIpChangeProgressParam_callId_set(long j2, OnIpChangeProgressParam onIpChangeProgressParam, int i2);

    public static final native int OnIpChangeProgressParam_op_get(long j2, OnIpChangeProgressParam onIpChangeProgressParam);

    public static final native void OnIpChangeProgressParam_op_set(long j2, OnIpChangeProgressParam onIpChangeProgressParam, int i2);

    public static final native long OnIpChangeProgressParam_regInfo_get(long j2, OnIpChangeProgressParam onIpChangeProgressParam);

    public static final native void OnIpChangeProgressParam_regInfo_set(long j2, OnIpChangeProgressParam onIpChangeProgressParam, long j3, RegProgressParam regProgressParam);

    public static final native int OnIpChangeProgressParam_status_get(long j2, OnIpChangeProgressParam onIpChangeProgressParam);

    public static final native void OnIpChangeProgressParam_status_set(long j2, OnIpChangeProgressParam onIpChangeProgressParam, int i2);

    public static final native int OnIpChangeProgressParam_transportId_get(long j2, OnIpChangeProgressParam onIpChangeProgressParam);

    public static final native void OnIpChangeProgressParam_transportId_set(long j2, OnIpChangeProgressParam onIpChangeProgressParam, int i2);

    public static final native long OnMwiInfoParam_rdata_get(long j2, OnMwiInfoParam onMwiInfoParam);

    public static final native void OnMwiInfoParam_rdata_set(long j2, OnMwiInfoParam onMwiInfoParam, long j3, SipRxData sipRxData);

    public static final native int OnMwiInfoParam_state_get(long j2, OnMwiInfoParam onMwiInfoParam);

    public static final native void OnMwiInfoParam_state_set(long j2, OnMwiInfoParam onMwiInfoParam, int i2);

    public static final native String OnNatCheckStunServersCompleteParam_addr_get(long j2, OnNatCheckStunServersCompleteParam onNatCheckStunServersCompleteParam);

    public static final native void OnNatCheckStunServersCompleteParam_addr_set(long j2, OnNatCheckStunServersCompleteParam onNatCheckStunServersCompleteParam, String str);

    public static final native String OnNatCheckStunServersCompleteParam_name_get(long j2, OnNatCheckStunServersCompleteParam onNatCheckStunServersCompleteParam);

    public static final native void OnNatCheckStunServersCompleteParam_name_set(long j2, OnNatCheckStunServersCompleteParam onNatCheckStunServersCompleteParam, String str);

    public static final native int OnNatCheckStunServersCompleteParam_status_get(long j2, OnNatCheckStunServersCompleteParam onNatCheckStunServersCompleteParam);

    public static final native void OnNatCheckStunServersCompleteParam_status_set(long j2, OnNatCheckStunServersCompleteParam onNatCheckStunServersCompleteParam, int i2);

    public static final native long OnNatCheckStunServersCompleteParam_userData_get(long j2, OnNatCheckStunServersCompleteParam onNatCheckStunServersCompleteParam);

    public static final native void OnNatCheckStunServersCompleteParam_userData_set(long j2, OnNatCheckStunServersCompleteParam onNatCheckStunServersCompleteParam, long j3);

    public static final native String OnNatDetectionCompleteParam_natTypeName_get(long j2, OnNatDetectionCompleteParam onNatDetectionCompleteParam);

    public static final native void OnNatDetectionCompleteParam_natTypeName_set(long j2, OnNatDetectionCompleteParam onNatDetectionCompleteParam, String str);

    public static final native int OnNatDetectionCompleteParam_natType_get(long j2, OnNatDetectionCompleteParam onNatDetectionCompleteParam);

    public static final native void OnNatDetectionCompleteParam_natType_set(long j2, OnNatDetectionCompleteParam onNatDetectionCompleteParam, int i2);

    public static final native String OnNatDetectionCompleteParam_reason_get(long j2, OnNatDetectionCompleteParam onNatDetectionCompleteParam);

    public static final native void OnNatDetectionCompleteParam_reason_set(long j2, OnNatDetectionCompleteParam onNatDetectionCompleteParam, String str);

    public static final native int OnNatDetectionCompleteParam_status_get(long j2, OnNatDetectionCompleteParam onNatDetectionCompleteParam);

    public static final native void OnNatDetectionCompleteParam_status_set(long j2, OnNatDetectionCompleteParam onNatDetectionCompleteParam, int i2);

    public static final native boolean OnRegStartedParam_renew_get(long j2, OnRegStartedParam onRegStartedParam);

    public static final native void OnRegStartedParam_renew_set(long j2, OnRegStartedParam onRegStartedParam, boolean z);

    public static final native int OnRegStateParam_code_get(long j2, OnRegStateParam onRegStateParam);

    public static final native void OnRegStateParam_code_set(long j2, OnRegStateParam onRegStateParam, int i2);

    public static final native int OnRegStateParam_expiration_get(long j2, OnRegStateParam onRegStateParam);

    public static final native void OnRegStateParam_expiration_set(long j2, OnRegStateParam onRegStateParam, int i2);

    public static final native long OnRegStateParam_rdata_get(long j2, OnRegStateParam onRegStateParam);

    public static final native void OnRegStateParam_rdata_set(long j2, OnRegStateParam onRegStateParam, long j3, SipRxData sipRxData);

    public static final native String OnRegStateParam_reason_get(long j2, OnRegStateParam onRegStateParam);

    public static final native void OnRegStateParam_reason_set(long j2, OnRegStateParam onRegStateParam, String str);

    public static final native int OnRegStateParam_status_get(long j2, OnRegStateParam onRegStateParam);

    public static final native void OnRegStateParam_status_set(long j2, OnRegStateParam onRegStateParam, int i2);

    public static final native int OnSelectAccountParam_accountIndex_get(long j2, OnSelectAccountParam onSelectAccountParam);

    public static final native void OnSelectAccountParam_accountIndex_set(long j2, OnSelectAccountParam onSelectAccountParam, int i2);

    public static final native long OnSelectAccountParam_rdata_get(long j2, OnSelectAccountParam onSelectAccountParam);

    public static final native void OnSelectAccountParam_rdata_set(long j2, OnSelectAccountParam onSelectAccountParam, long j3, SipRxData sipRxData);

    public static final native boolean OnStreamCreatedParam_destroyPort_get(long j2, OnStreamCreatedParam onStreamCreatedParam);

    public static final native void OnStreamCreatedParam_destroyPort_set(long j2, OnStreamCreatedParam onStreamCreatedParam, boolean z);

    public static final native long OnStreamCreatedParam_pPort_get(long j2, OnStreamCreatedParam onStreamCreatedParam);

    public static final native void OnStreamCreatedParam_pPort_set(long j2, OnStreamCreatedParam onStreamCreatedParam, long j3);

    public static final native long OnStreamCreatedParam_streamIdx_get(long j2, OnStreamCreatedParam onStreamCreatedParam);

    public static final native void OnStreamCreatedParam_streamIdx_set(long j2, OnStreamCreatedParam onStreamCreatedParam, long j3);

    public static final native long OnStreamCreatedParam_stream_get(long j2, OnStreamCreatedParam onStreamCreatedParam);

    public static final native void OnStreamCreatedParam_stream_set(long j2, OnStreamCreatedParam onStreamCreatedParam, long j3);

    public static final native long OnStreamDestroyedParam_streamIdx_get(long j2, OnStreamDestroyedParam onStreamDestroyedParam);

    public static final native void OnStreamDestroyedParam_streamIdx_set(long j2, OnStreamDestroyedParam onStreamDestroyedParam, long j3);

    public static final native long OnStreamDestroyedParam_stream_get(long j2, OnStreamDestroyedParam onStreamDestroyedParam);

    public static final native void OnStreamDestroyedParam_stream_set(long j2, OnStreamDestroyedParam onStreamDestroyedParam, long j3);

    public static final native long OnTimerParam_msecDelay_get(long j2, OnTimerParam onTimerParam);

    public static final native void OnTimerParam_msecDelay_set(long j2, OnTimerParam onTimerParam, long j3);

    public static final native long OnTimerParam_userData_get(long j2, OnTimerParam onTimerParam);

    public static final native void OnTimerParam_userData_set(long j2, OnTimerParam onTimerParam, long j3);

    public static final native long OnTransportStateParam_hnd_get(long j2, OnTransportStateParam onTransportStateParam);

    public static final native void OnTransportStateParam_hnd_set(long j2, OnTransportStateParam onTransportStateParam, long j3);

    public static final native int OnTransportStateParam_lastError_get(long j2, OnTransportStateParam onTransportStateParam);

    public static final native void OnTransportStateParam_lastError_set(long j2, OnTransportStateParam onTransportStateParam, int i2);

    public static final native int OnTransportStateParam_state_get(long j2, OnTransportStateParam onTransportStateParam);

    public static final native void OnTransportStateParam_state_set(long j2, OnTransportStateParam onTransportStateParam, int i2);

    public static final native long OnTransportStateParam_tlsInfo_get(long j2, OnTransportStateParam onTransportStateParam);

    public static final native void OnTransportStateParam_tlsInfo_set(long j2, OnTransportStateParam onTransportStateParam, long j3, TlsInfo tlsInfo);

    public static final native String OnTransportStateParam_type_get(long j2, OnTransportStateParam onTransportStateParam);

    public static final native void OnTransportStateParam_type_set(long j2, OnTransportStateParam onTransportStateParam, String str);

    public static final native String OnTypingIndicationParam_contactUri_get(long j2, OnTypingIndicationParam onTypingIndicationParam);

    public static final native void OnTypingIndicationParam_contactUri_set(long j2, OnTypingIndicationParam onTypingIndicationParam, String str);

    public static final native String OnTypingIndicationParam_fromUri_get(long j2, OnTypingIndicationParam onTypingIndicationParam);

    public static final native void OnTypingIndicationParam_fromUri_set(long j2, OnTypingIndicationParam onTypingIndicationParam, String str);

    public static final native boolean OnTypingIndicationParam_isTyping_get(long j2, OnTypingIndicationParam onTypingIndicationParam);

    public static final native void OnTypingIndicationParam_isTyping_set(long j2, OnTypingIndicationParam onTypingIndicationParam, boolean z);

    public static final native long OnTypingIndicationParam_rdata_get(long j2, OnTypingIndicationParam onTypingIndicationParam);

    public static final native void OnTypingIndicationParam_rdata_set(long j2, OnTypingIndicationParam onTypingIndicationParam, long j3, SipRxData sipRxData);

    public static final native String OnTypingIndicationParam_toUri_get(long j2, OnTypingIndicationParam onTypingIndicationParam);

    public static final native void OnTypingIndicationParam_toUri_set(long j2, OnTypingIndicationParam onTypingIndicationParam, String str);

    public static final native int PJMEDIA_AUD_DEFAULT_CAPTURE_DEV_get();

    public static final native int PJMEDIA_AUD_DEFAULT_PLAYBACK_DEV_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_CNG_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_EC_TAIL_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_EC_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_EXT_FORMAT_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_INPUT_LATENCY_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_INPUT_ROUTE_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_INPUT_SIGNAL_METER_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_INPUT_SOURCE_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_INPUT_VOLUME_SETTING_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_MAX_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_OUTPUT_LATENCY_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_OUTPUT_ROUTE_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_OUTPUT_SIGNAL_METER_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_OUTPUT_VOLUME_SETTING_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_PLC_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_VAD_get();

    public static final native int PJMEDIA_AUD_DEV_ROUTE_BLUETOOTH_get();

    public static final native int PJMEDIA_AUD_DEV_ROUTE_CUSTOM_get();

    public static final native int PJMEDIA_AUD_DEV_ROUTE_DEFAULT_get();

    public static final native int PJMEDIA_AUD_DEV_ROUTE_EARPIECE_get();

    public static final native int PJMEDIA_AUD_DEV_ROUTE_LOUDSPEAKER_get();

    public static final native int PJMEDIA_AUD_INVALID_DEV_get();

    public static final native int PJMEDIA_DIR_CAPTURE_PLAYBACK_get();

    public static final native int PJMEDIA_DIR_CAPTURE_RENDER_get();

    public static final native int PJMEDIA_DIR_CAPTURE_get();

    public static final native int PJMEDIA_DIR_DECODING_get();

    public static final native int PJMEDIA_DIR_ENCODING_DECODING_get();

    public static final native int PJMEDIA_DIR_ENCODING_get();

    public static final native int PJMEDIA_DIR_NONE_get();

    public static final native int PJMEDIA_DIR_PLAYBACK_get();

    public static final native int PJMEDIA_DIR_RENDER_get();

    public static final native int PJMEDIA_EVENT_FMT_CHANGED_get();

    public static final native int PJMEDIA_EVENT_KEYFRAME_FOUND_get();

    public static final native int PJMEDIA_EVENT_KEYFRAME_MISSING_get();

    public static final native int PJMEDIA_EVENT_MOUSE_BTN_DOWN_get();

    public static final native int PJMEDIA_EVENT_ORIENT_CHANGED_get();

    public static final native int PJMEDIA_EVENT_WND_CLOSED_get();

    public static final native int PJMEDIA_EVENT_WND_CLOSING_get();

    public static final native int PJMEDIA_EVENT_WND_RESIZED_get();

    public static final native int PJMEDIA_FILE_NO_LOOP_get();

    public static final native int PJMEDIA_FILE_WRITE_ALAW_get();

    public static final native int PJMEDIA_FILE_WRITE_PCM_get();

    public static final native int PJMEDIA_FILE_WRITE_ULAW_get();

    public static final native int PJMEDIA_FORMAT_ALAW_get();

    public static final native int PJMEDIA_FORMAT_AMR_get();

    public static final native int PJMEDIA_FORMAT_AYUV_get();

    public static final native int PJMEDIA_FORMAT_BGRA_get();

    public static final native int PJMEDIA_FORMAT_DIB_get();

    public static final native int PJMEDIA_FORMAT_G729_get();

    public static final native int PJMEDIA_FORMAT_GBRP_get();

    public static final native int PJMEDIA_FORMAT_H261_get();

    public static final native int PJMEDIA_FORMAT_H263P_get();

    public static final native int PJMEDIA_FORMAT_H263_get();

    public static final native int PJMEDIA_FORMAT_H264_get();

    public static final native int PJMEDIA_FORMAT_I420JPEG_get();

    public static final native int PJMEDIA_FORMAT_I420_get();

    public static final native int PJMEDIA_FORMAT_I422JPEG_get();

    public static final native int PJMEDIA_FORMAT_I422_get();

    public static final native int PJMEDIA_FORMAT_ILBC_get();

    public static final native int PJMEDIA_FORMAT_IYUV_get();

    public static final native int PJMEDIA_FORMAT_L16_get();

    public static final native int PJMEDIA_FORMAT_MJPEG_get();

    public static final native int PJMEDIA_FORMAT_MPEG1VIDEO_get();

    public static final native int PJMEDIA_FORMAT_MPEG2VIDEO_get();

    public static final native int PJMEDIA_FORMAT_MPEG4_get();

    public static final native int PJMEDIA_FORMAT_NV21_get();

    public static final native int PJMEDIA_FORMAT_PCMA_get();

    public static final native int PJMEDIA_FORMAT_PCMU_get();

    public static final native int PJMEDIA_FORMAT_PCM_get();

    public static final native int PJMEDIA_FORMAT_RGB24_get();

    public static final native int PJMEDIA_FORMAT_RGB32_get();

    public static final native int PJMEDIA_FORMAT_RGBA_get();

    public static final native int PJMEDIA_FORMAT_ULAW_get();

    public static final native int PJMEDIA_FORMAT_UYVY_get();

    public static final native int PJMEDIA_FORMAT_YUY2_get();

    public static final native int PJMEDIA_FORMAT_YV12_get();

    public static final native int PJMEDIA_FORMAT_YVYU_get();

    public static final native int PJMEDIA_SRTP_NO_AUTHENTICATION_get();

    public static final native int PJMEDIA_SRTP_NO_ENCRYPTION_get();

    public static final native int PJMEDIA_TP_PROTO_NONE_get();

    public static final native int PJMEDIA_VID_DEFAULT_CAPTURE_DEV_get();

    public static final native int PJMEDIA_VID_DEFAULT_RENDER_DEV_get();

    public static final native int PJMEDIA_VID_DEV_CAP_FORMAT_get();

    public static final native int PJMEDIA_VID_DEV_CAP_INPUT_PREVIEW_get();

    public static final native int PJMEDIA_VID_DEV_CAP_INPUT_SCALE_get();

    public static final native int PJMEDIA_VID_DEV_CAP_MAX_get();

    public static final native int PJMEDIA_VID_DEV_CAP_ORIENTATION_get();

    public static final native int PJMEDIA_VID_DEV_CAP_OUTPUT_HIDE_get();

    public static final native int PJMEDIA_VID_DEV_CAP_OUTPUT_POSITION_get();

    public static final native int PJMEDIA_VID_DEV_CAP_OUTPUT_RESIZE_get();

    public static final native int PJMEDIA_VID_DEV_CAP_OUTPUT_WINDOW_FLAGS_get();

    public static final native int PJMEDIA_VID_DEV_CAP_OUTPUT_WINDOW_get();

    public static final native int PJMEDIA_VID_DEV_CAP_SWITCH_get();

    public static final native int PJMEDIA_VID_INVALID_DEV_get();

    public static final native int PJMEDIA_VID_PACKING_PACKETS_get();

    public static final native int PJMEDIA_VID_PACKING_WHOLE_get();

    public static final native int PJMEDIA_VID_STREAM_RC_NONE_get();

    public static final native int PJMEDIA_VID_STREAM_RC_SIMPLE_BLOCKING_get();

    public static final native int PJSIP_AC_AMBIGUOUS_get();

    public static final native int PJSIP_CRED_DATA_DIGEST_get();

    public static final native int PJSIP_CRED_DATA_EXT_AKA_get();

    public static final native int PJSIP_CRED_DATA_PLAIN_PASSWD_get();

    public static final native int PJSIP_DIALOG_CAP_SUPPORTED_get();

    public static final native int PJSIP_DIALOG_CAP_UNKNOWN_get();

    public static final native int PJSIP_DIALOG_CAP_UNSUPPORTED_get();

    public static final native int PJSIP_SC_ACCEPTED_get();

    public static final native int PJSIP_SC_ADDRESS_INCOMPLETE_get();

    public static final native int PJSIP_SC_ALTERNATIVE_SERVICE_get();

    public static final native int PJSIP_SC_BAD_EVENT_get();

    public static final native int PJSIP_SC_BAD_EXTENSION_get();

    public static final native int PJSIP_SC_BAD_GATEWAY_get();

    public static final native int PJSIP_SC_BAD_REQUEST_get();

    public static final native int PJSIP_SC_BUSY_EVERYWHERE_get();

    public static final native int PJSIP_SC_BUSY_HERE_get();

    public static final native int PJSIP_SC_CALL_BEING_FORWARDED_get();

    public static final native int PJSIP_SC_CALL_TSX_DOES_NOT_EXIST_get();

    public static final native int PJSIP_SC_DECLINE_get();

    public static final native int PJSIP_SC_DOES_NOT_EXIST_ANYWHERE_get();

    public static final native int PJSIP_SC_EXTENSION_REQUIRED_get();

    public static final native int PJSIP_SC_FORBIDDEN_get();

    public static final native int PJSIP_SC_GONE_get();

    public static final native int PJSIP_SC_INTERNAL_SERVER_ERROR_get();

    public static final native int PJSIP_SC_INTERVAL_TOO_BRIEF_get();

    public static final native int PJSIP_SC_LOOP_DETECTED_get();

    public static final native int PJSIP_SC_MESSAGE_TOO_LARGE_get();

    public static final native int PJSIP_SC_METHOD_NOT_ALLOWED_get();

    public static final native int PJSIP_SC_MOVED_PERMANENTLY_get();

    public static final native int PJSIP_SC_MOVED_TEMPORARILY_get();

    public static final native int PJSIP_SC_MULTIPLE_CHOICES_get();

    public static final native int PJSIP_SC_NOT_ACCEPTABLE_ANYWHERE_get();

    public static final native int PJSIP_SC_NOT_ACCEPTABLE_HERE_get();

    public static final native int PJSIP_SC_NOT_ACCEPTABLE_get();

    public static final native int PJSIP_SC_NOT_FOUND_get();

    public static final native int PJSIP_SC_NOT_IMPLEMENTED_get();

    public static final native int PJSIP_SC_OK_get();

    public static final native int PJSIP_SC_PAYMENT_REQUIRED_get();

    public static final native int PJSIP_SC_PRECONDITION_FAILURE_get();

    public static final native int PJSIP_SC_PROGRESS_get();

    public static final native int PJSIP_SC_PROXY_AUTHENTICATION_REQUIRED_get();

    public static final native int PJSIP_SC_QUEUED_get();

    public static final native int PJSIP_SC_REQUEST_ENTITY_TOO_LARGE_get();

    public static final native int PJSIP_SC_REQUEST_PENDING_get();

    public static final native int PJSIP_SC_REQUEST_TERMINATED_get();

    public static final native int PJSIP_SC_REQUEST_TIMEOUT_get();

    public static final native int PJSIP_SC_REQUEST_UPDATED_get();

    public static final native int PJSIP_SC_REQUEST_URI_TOO_LONG_get();

    public static final native int PJSIP_SC_RINGING_get();

    public static final native int PJSIP_SC_SERVER_TIMEOUT_get();

    public static final native int PJSIP_SC_SERVICE_UNAVAILABLE_get();

    public static final native int PJSIP_SC_SESSION_TIMER_TOO_SMALL_get();

    public static final native int PJSIP_SC_TEMPORARILY_UNAVAILABLE_get();

    public static final native int PJSIP_SC_TOO_MANY_HOPS_get();

    public static final native int PJSIP_SC_TRYING_get();

    public static final native int PJSIP_SC_TSX_TIMEOUT_get();

    public static final native int PJSIP_SC_TSX_TRANSPORT_ERROR_get();

    public static final native int PJSIP_SC_UNAUTHORIZED_get();

    public static final native int PJSIP_SC_UNDECIPHERABLE_get();

    public static final native int PJSIP_SC_UNSUPPORTED_MEDIA_TYPE_get();

    public static final native int PJSIP_SC_UNSUPPORTED_URI_SCHEME_get();

    public static final native int PJSIP_SC_USE_PROXY_get();

    public static final native int PJSIP_SC_VERSION_NOT_SUPPORTED_get();

    public static final native int PJSIP_SC__force_32bit_get();

    public static final native int PJSIP_SSLV23_METHOD_get();

    public static final native int PJSIP_SSLV2_METHOD_get();

    public static final native int PJSIP_SSLV3_METHOD_get();

    public static final native int PJSIP_SSL_UNSPECIFIED_METHOD_get();

    public static final native int PJSIP_TLSV1_1_METHOD_get();

    public static final native int PJSIP_TLSV1_2_METHOD_get();

    public static final native int PJSIP_TLSV1_METHOD_get();

    public static final native int PJSIP_TRANSPORT_DATAGRAM_get();

    public static final native int PJSIP_TRANSPORT_IPV6_get();

    public static final native int PJSIP_TRANSPORT_RELIABLE_get();

    public static final native int PJSIP_TRANSPORT_SECURE_get();

    public static final native int PJSIP_TRANSPORT_TCP6_get();

    public static final native int PJSIP_TRANSPORT_TLS6_get();

    public static final native int PJSIP_TRANSPORT_UDP6_get();

    public static final native int PJSIP_UAC_ROLE_get();

    public static final native int PJSIP_UAS_ROLE_get();

    public static final native int PJSUA_CALL_INCLUDE_DISABLED_MEDIA_get();

    public static final native int PJSUA_CALL_NO_SDP_OFFER_get();

    public static final native int PJSUA_CALL_REINIT_MEDIA_get();

    public static final native int PJSUA_CALL_UNHOLD_get();

    public static final native int PJSUA_CALL_UPDATE_CONTACT_get();

    public static final native int PJSUA_CALL_UPDATE_VIA_get();

    public static final native int PJSUA_DESTROY_NO_NETWORK_get();

    public static final native int PJSUA_DESTROY_NO_RX_MSG_get();

    public static final native int PJSUA_DESTROY_NO_TX_MSG_get();

    public static final native int PJSUA_INVALID_ID_get();

    public static final native int PJSUA_MED_TP_CLOSE_MEMBER_get();

    public static final native int PJSUA_SND_DEFAULT_CAPTURE_DEV_get();

    public static final native int PJSUA_SND_DEFAULT_PLAYBACK_DEV_get();

    public static final native int PJSUA_SND_DEV_NO_IMMEDIATE_OPEN_get();

    public static final native int PJSUA_SND_DEV_SPEAKER_ONLY_get();

    public static final native int PJSUA_SND_NO_DEV_get();

    public static final native int PJSUA_SND_NULL_DEV_get();

    public static final native int PJSUA_VID_REQ_KEYFRAME_RTCP_PLI_get();

    public static final native int PJSUA_VID_REQ_KEYFRAME_SIP_INFO_get();

    public static final native int PJ_FALSE_get();

    public static final native int PJ_LOG_HAS_COLOR_get();

    public static final native int PJ_LOG_HAS_CR_get();

    public static final native int PJ_LOG_HAS_DAY_NAME_get();

    public static final native int PJ_LOG_HAS_DAY_OF_MON_get();

    public static final native int PJ_LOG_HAS_INDENT_get();

    public static final native int PJ_LOG_HAS_LEVEL_TEXT_get();

    public static final native int PJ_LOG_HAS_MICRO_SEC_get();

    public static final native int PJ_LOG_HAS_MONTH_get();

    public static final native int PJ_LOG_HAS_NEWLINE_get();

    public static final native int PJ_LOG_HAS_SENDER_get();

    public static final native int PJ_LOG_HAS_SPACE_get();

    public static final native int PJ_LOG_HAS_THREAD_ID_get();

    public static final native int PJ_LOG_HAS_THREAD_SWC_get();

    public static final native int PJ_LOG_HAS_TIME_get();

    public static final native int PJ_LOG_HAS_YEAR_get();

    public static final native int PJ_O_APPEND_get();

    public static final native int PJ_O_RDONLY_get();

    public static final native int PJ_O_RDWR_get();

    public static final native int PJ_O_WRONLY_get();

    public static final native int PJ_QOS_PARAM_HAS_DSCP_get();

    public static final native int PJ_QOS_PARAM_HAS_SO_PRIO_get();

    public static final native int PJ_QOS_PARAM_HAS_WMM_get();

    public static final native int PJ_SSL_CERT_ECHAIN_TOO_LONG_get();

    public static final native int PJ_SSL_CERT_ECRL_FAILURE_get();

    public static final native int PJ_SSL_CERT_EIDENTITY_NOT_MATCH_get();

    public static final native int PJ_SSL_CERT_EINVALID_FORMAT_get();

    public static final native int PJ_SSL_CERT_EINVALID_PURPOSE_get();

    public static final native int PJ_SSL_CERT_EISSUER_MISMATCH_get();

    public static final native int PJ_SSL_CERT_EISSUER_NOT_FOUND_get();

    public static final native int PJ_SSL_CERT_EREVOKED_get();

    public static final native int PJ_SSL_CERT_ESUCCESS_get();

    public static final native int PJ_SSL_CERT_EUNKNOWN_get();

    public static final native int PJ_SSL_CERT_EUNTRUSTED_get();

    public static final native int PJ_SSL_CERT_EVALIDITY_PERIOD_get();

    public static final native int PJ_SSL_CERT_NAME_UNKNOWN_get();

    public static final native int PJ_SSL_CK_DES_192_EDE3_CBC_WITH_MD5_get();

    public static final native int PJ_SSL_CK_DES_64_CBC_WITH_MD5_get();

    public static final native int PJ_SSL_CK_IDEA_128_CBC_WITH_MD5_get();

    public static final native int PJ_SSL_CK_RC2_128_CBC_EXPORT40_WITH_MD5_get();

    public static final native int PJ_SSL_CK_RC2_128_CBC_WITH_MD5_get();

    public static final native int PJ_SSL_CK_RC4_128_EXPORT40_WITH_MD5_get();

    public static final native int PJ_SSL_CK_RC4_128_WITH_MD5_get();

    public static final native int PJ_SSL_FORTEZZA_KEA_WITH_FORTEZZA_CBC_SHA_get();

    public static final native int PJ_SSL_FORTEZZA_KEA_WITH_NULL_SHA_get();

    public static final native int PJ_SSL_FORTEZZA_KEA_WITH_RC4_128_SHA_get();

    public static final native int PJ_SSL_SOCK_PROTO_DEFAULT_get();

    public static final native int PJ_SSL_SOCK_PROTO_DTLS1_get();

    public static final native int PJ_SSL_SOCK_PROTO_SSL23_get();

    public static final native int PJ_SSL_SOCK_PROTO_SSL2_get();

    public static final native int PJ_SSL_SOCK_PROTO_SSL3_get();

    public static final native int PJ_SSL_SOCK_PROTO_TLS1_1_get();

    public static final native int PJ_SSL_SOCK_PROTO_TLS1_2_get();

    public static final native int PJ_SSL_SOCK_PROTO_TLS1_get();

    public static final native int PJ_SUCCESS_get();

    public static final native int PJ_TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA_get();

    public static final native int PJ_TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA_get();

    public static final native int PJ_TLS_DHE_DSS_WITH_AES_128_CBC_SHA256_get();

    public static final native int PJ_TLS_DHE_DSS_WITH_AES_128_CBC_SHA_get();

    public static final native int PJ_TLS_DHE_DSS_WITH_AES_256_CBC_SHA256_get();

    public static final native int PJ_TLS_DHE_DSS_WITH_AES_256_CBC_SHA_get();

    public static final native int PJ_TLS_DHE_DSS_WITH_DES_CBC_SHA_get();

    public static final native int PJ_TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA_get();

    public static final native int PJ_TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA_get();

    public static final native int PJ_TLS_DHE_RSA_WITH_AES_128_CBC_SHA256_get();

    public static final native int PJ_TLS_DHE_RSA_WITH_AES_128_CBC_SHA_get();

    public static final native int PJ_TLS_DHE_RSA_WITH_AES_256_CBC_SHA256_get();

    public static final native int PJ_TLS_DHE_RSA_WITH_AES_256_CBC_SHA_get();

    public static final native int PJ_TLS_DHE_RSA_WITH_DES_CBC_SHA_get();

    public static final native int PJ_TLS_DH_DSS_EXPORT_WITH_DES40_CBC_SHA_get();

    public static final native int PJ_TLS_DH_DSS_WITH_3DES_EDE_CBC_SHA_get();

    public static final native int PJ_TLS_DH_DSS_WITH_AES_128_CBC_SHA256_get();

    public static final native int PJ_TLS_DH_DSS_WITH_AES_128_CBC_SHA_get();

    public static final native int PJ_TLS_DH_DSS_WITH_AES_256_CBC_SHA256_get();

    public static final native int PJ_TLS_DH_DSS_WITH_AES_256_CBC_SHA_get();

    public static final native int PJ_TLS_DH_DSS_WITH_DES_CBC_SHA_get();

    public static final native int PJ_TLS_DH_RSA_EXPORT_WITH_DES40_CBC_SHA_get();

    public static final native int PJ_TLS_DH_RSA_WITH_3DES_EDE_CBC_SHA_get();

    public static final native int PJ_TLS_DH_RSA_WITH_AES_128_CBC_SHA256_get();

    public static final native int PJ_TLS_DH_RSA_WITH_AES_128_CBC_SHA_get();

    public static final native int PJ_TLS_DH_RSA_WITH_AES_256_CBC_SHA256_get();

    public static final native int PJ_TLS_DH_RSA_WITH_AES_256_CBC_SHA_get();

    public static final native int PJ_TLS_DH_RSA_WITH_DES_CBC_SHA_get();

    public static final native int PJ_TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA_get();

    public static final native int PJ_TLS_DH_anon_EXPORT_WITH_RC4_40_MD5_get();

    public static final native int PJ_TLS_DH_anon_WITH_3DES_EDE_CBC_SHA_get();

    public static final native int PJ_TLS_DH_anon_WITH_AES_128_CBC_SHA256_get();

    public static final native int PJ_TLS_DH_anon_WITH_AES_128_CBC_SHA_get();

    public static final native int PJ_TLS_DH_anon_WITH_AES_256_CBC_SHA256_get();

    public static final native int PJ_TLS_DH_anon_WITH_AES_256_CBC_SHA_get();

    public static final native int PJ_TLS_DH_anon_WITH_DES_CBC_SHA_get();

    public static final native int PJ_TLS_DH_anon_WITH_RC4_128_MD5_get();

    public static final native int PJ_TLS_NULL_WITH_NULL_NULL_get();

    public static final native int PJ_TLS_RSA_EXPORT_WITH_DES40_CBC_SHA_get();

    public static final native int PJ_TLS_RSA_EXPORT_WITH_RC2_CBC_40_MD5_get();

    public static final native int PJ_TLS_RSA_EXPORT_WITH_RC4_40_MD5_get();

    public static final native int PJ_TLS_RSA_WITH_3DES_EDE_CBC_SHA_get();

    public static final native int PJ_TLS_RSA_WITH_AES_128_CBC_SHA256_get();

    public static final native int PJ_TLS_RSA_WITH_AES_128_CBC_SHA_get();

    public static final native int PJ_TLS_RSA_WITH_AES_256_CBC_SHA256_get();

    public static final native int PJ_TLS_RSA_WITH_AES_256_CBC_SHA_get();

    public static final native int PJ_TLS_RSA_WITH_DES_CBC_SHA_get();

    public static final native int PJ_TLS_RSA_WITH_IDEA_CBC_SHA_get();

    public static final native int PJ_TLS_RSA_WITH_NULL_MD5_get();

    public static final native int PJ_TLS_RSA_WITH_NULL_SHA256_get();

    public static final native int PJ_TLS_RSA_WITH_NULL_SHA_get();

    public static final native int PJ_TLS_RSA_WITH_RC4_128_MD5_get();

    public static final native int PJ_TLS_RSA_WITH_RC4_128_SHA_get();

    public static final native int PJ_TLS_UNKNOWN_CIPHER_get();

    public static final native int PJ_TRUE_get();

    public static final native int PJ_TURN_TP_TCP_get();

    public static final native int PJ_TURN_TP_TLS_get();

    public static final native int PJ_TURN_TP_UDP_get();

    public static final native void PendingJob_execute(long j2, PendingJob pendingJob, boolean z);

    public static final native String PresNotifyParam_reason_get(long j2, PresNotifyParam presNotifyParam);

    public static final native void PresNotifyParam_reason_set(long j2, PresNotifyParam presNotifyParam, String str);

    public static final native long PresNotifyParam_srvPres_get(long j2, PresNotifyParam presNotifyParam);

    public static final native void PresNotifyParam_srvPres_set(long j2, PresNotifyParam presNotifyParam, long j3);

    public static final native String PresNotifyParam_stateStr_get(long j2, PresNotifyParam presNotifyParam);

    public static final native void PresNotifyParam_stateStr_set(long j2, PresNotifyParam presNotifyParam, String str);

    public static final native int PresNotifyParam_state_get(long j2, PresNotifyParam presNotifyParam);

    public static final native void PresNotifyParam_state_set(long j2, PresNotifyParam presNotifyParam, int i2);

    public static final native long PresNotifyParam_txOption_get(long j2, PresNotifyParam presNotifyParam);

    public static final native void PresNotifyParam_txOption_set(long j2, PresNotifyParam presNotifyParam, long j3, SipTxOption sipTxOption);

    public static final native boolean PresNotifyParam_withBody_get(long j2, PresNotifyParam presNotifyParam);

    public static final native void PresNotifyParam_withBody_set(long j2, PresNotifyParam presNotifyParam, boolean z);

    public static final native int PresenceStatus_activity_get(long j2, PresenceStatus presenceStatus);

    public static final native void PresenceStatus_activity_set(long j2, PresenceStatus presenceStatus, int i2);

    public static final native String PresenceStatus_note_get(long j2, PresenceStatus presenceStatus);

    public static final native void PresenceStatus_note_set(long j2, PresenceStatus presenceStatus, String str);

    public static final native String PresenceStatus_rpidId_get(long j2, PresenceStatus presenceStatus);

    public static final native void PresenceStatus_rpidId_set(long j2, PresenceStatus presenceStatus, String str);

    public static final native String PresenceStatus_statusText_get(long j2, PresenceStatus presenceStatus);

    public static final native void PresenceStatus_statusText_set(long j2, PresenceStatus presenceStatus, String str);

    public static final native int PresenceStatus_status_get(long j2, PresenceStatus presenceStatus);

    public static final native void PresenceStatus_status_set(long j2, PresenceStatus presenceStatus, int i2);

    public static final native int RegProgressParam_code_get(long j2, RegProgressParam regProgressParam);

    public static final native void RegProgressParam_code_set(long j2, RegProgressParam regProgressParam, int i2);

    public static final native boolean RegProgressParam_isRegister_get(long j2, RegProgressParam regProgressParam);

    public static final native void RegProgressParam_isRegister_set(long j2, RegProgressParam regProgressParam, boolean z);

    public static final native String RtcpSdes_cname_get(long j2, RtcpSdes rtcpSdes);

    public static final native void RtcpSdes_cname_set(long j2, RtcpSdes rtcpSdes, String str);

    public static final native String RtcpSdes_email_get(long j2, RtcpSdes rtcpSdes);

    public static final native void RtcpSdes_email_set(long j2, RtcpSdes rtcpSdes, String str);

    public static final native String RtcpSdes_loc_get(long j2, RtcpSdes rtcpSdes);

    public static final native void RtcpSdes_loc_set(long j2, RtcpSdes rtcpSdes, String str);

    public static final native String RtcpSdes_name_get(long j2, RtcpSdes rtcpSdes);

    public static final native void RtcpSdes_name_set(long j2, RtcpSdes rtcpSdes, String str);

    public static final native String RtcpSdes_note_get(long j2, RtcpSdes rtcpSdes);

    public static final native void RtcpSdes_note_set(long j2, RtcpSdes rtcpSdes, String str);

    public static final native String RtcpSdes_phone_get(long j2, RtcpSdes rtcpSdes);

    public static final native void RtcpSdes_phone_set(long j2, RtcpSdes rtcpSdes, String str);

    public static final native String RtcpSdes_tool_get(long j2, RtcpSdes rtcpSdes);

    public static final native void RtcpSdes_tool_set(long j2, RtcpSdes rtcpSdes, String str);

    public static final native long RtcpStat_peerSdes_get(long j2, RtcpStat rtcpStat);

    public static final native void RtcpStat_peerSdes_set(long j2, RtcpStat rtcpStat, long j3, RtcpSdes rtcpSdes);

    public static final native int RtcpStat_rtpTxLastSeq_get(long j2, RtcpStat rtcpStat);

    public static final native void RtcpStat_rtpTxLastSeq_set(long j2, RtcpStat rtcpStat, int i2);

    public static final native long RtcpStat_rtpTxLastTs_get(long j2, RtcpStat rtcpStat);

    public static final native void RtcpStat_rtpTxLastTs_set(long j2, RtcpStat rtcpStat, long j3);

    public static final native long RtcpStat_rttUsec_get(long j2, RtcpStat rtcpStat);

    public static final native void RtcpStat_rttUsec_set(long j2, RtcpStat rtcpStat, long j3, MathStat mathStat);

    public static final native long RtcpStat_rxIpdvUsec_get(long j2, RtcpStat rtcpStat);

    public static final native void RtcpStat_rxIpdvUsec_set(long j2, RtcpStat rtcpStat, long j3, MathStat mathStat);

    public static final native long RtcpStat_rxRawJitterUsec_get(long j2, RtcpStat rtcpStat);

    public static final native void RtcpStat_rxRawJitterUsec_set(long j2, RtcpStat rtcpStat, long j3, MathStat mathStat);

    public static final native long RtcpStat_rxStat_get(long j2, RtcpStat rtcpStat);

    public static final native void RtcpStat_rxStat_set(long j2, RtcpStat rtcpStat, long j3, RtcpStreamStat rtcpStreamStat);

    public static final native long RtcpStat_start_get(long j2, RtcpStat rtcpStat);

    public static final native void RtcpStat_start_set(long j2, RtcpStat rtcpStat, long j3, TimeVal timeVal);

    public static final native long RtcpStat_txStat_get(long j2, RtcpStat rtcpStat);

    public static final native void RtcpStat_txStat_set(long j2, RtcpStat rtcpStat, long j3, RtcpStreamStat rtcpStreamStat);

    public static final native long RtcpStreamStat_bytes_get(long j2, RtcpStreamStat rtcpStreamStat);

    public static final native void RtcpStreamStat_bytes_set(long j2, RtcpStreamStat rtcpStreamStat, long j3);

    public static final native long RtcpStreamStat_discard_get(long j2, RtcpStreamStat rtcpStreamStat);

    public static final native void RtcpStreamStat_discard_set(long j2, RtcpStreamStat rtcpStreamStat, long j3);

    public static final native long RtcpStreamStat_dup_get(long j2, RtcpStreamStat rtcpStreamStat);

    public static final native void RtcpStreamStat_dup_set(long j2, RtcpStreamStat rtcpStreamStat, long j3);

    public static final native long RtcpStreamStat_jitterUsec_get(long j2, RtcpStreamStat rtcpStreamStat);

    public static final native void RtcpStreamStat_jitterUsec_set(long j2, RtcpStreamStat rtcpStreamStat, long j3, MathStat mathStat);

    public static final native long RtcpStreamStat_lossPeriodUsec_get(long j2, RtcpStreamStat rtcpStreamStat);

    public static final native void RtcpStreamStat_lossPeriodUsec_set(long j2, RtcpStreamStat rtcpStreamStat, long j3, MathStat mathStat);

    public static final native long RtcpStreamStat_lossType_get(long j2, RtcpStreamStat rtcpStreamStat);

    public static final native void RtcpStreamStat_lossType_set(long j2, RtcpStreamStat rtcpStreamStat, long j3, LossType lossType);

    public static final native long RtcpStreamStat_loss_get(long j2, RtcpStreamStat rtcpStreamStat);

    public static final native void RtcpStreamStat_loss_set(long j2, RtcpStreamStat rtcpStreamStat, long j3);

    public static final native long RtcpStreamStat_pkt_get(long j2, RtcpStreamStat rtcpStreamStat);

    public static final native void RtcpStreamStat_pkt_set(long j2, RtcpStreamStat rtcpStreamStat, long j3);

    public static final native long RtcpStreamStat_reorder_get(long j2, RtcpStreamStat rtcpStreamStat);

    public static final native void RtcpStreamStat_reorder_set(long j2, RtcpStreamStat rtcpStreamStat, long j3);

    public static final native long RtcpStreamStat_updateCount_get(long j2, RtcpStreamStat rtcpStreamStat);

    public static final native void RtcpStreamStat_updateCount_set(long j2, RtcpStreamStat rtcpStreamStat, long j3);

    public static final native long RtcpStreamStat_update_get(long j2, RtcpStreamStat rtcpStreamStat);

    public static final native void RtcpStreamStat_update_set(long j2, RtcpStreamStat rtcpStreamStat, long j3, TimeVal timeVal);

    public static final native long RxMsgEvent_rdata_get(long j2, RxMsgEvent rxMsgEvent);

    public static final native void RxMsgEvent_rdata_set(long j2, RxMsgEvent rxMsgEvent, long j3, SipRxData sipRxData);

    public static final native int SUCCESS_get();

    public static final native long SdpSession_pjSdpSession_get(long j2, SdpSession sdpSession);

    public static final native void SdpSession_pjSdpSession_set(long j2, SdpSession sdpSession, long j3);

    public static final native String SdpSession_wholeSdp_get(long j2, SdpSession sdpSession);

    public static final native void SdpSession_wholeSdp_set(long j2, SdpSession sdpSession, String str);

    public static final native String SendInstantMessageParam_contentType_get(long j2, SendInstantMessageParam sendInstantMessageParam);

    public static final native void SendInstantMessageParam_contentType_set(long j2, SendInstantMessageParam sendInstantMessageParam, String str);

    public static final native String SendInstantMessageParam_content_get(long j2, SendInstantMessageParam sendInstantMessageParam);

    public static final native void SendInstantMessageParam_content_set(long j2, SendInstantMessageParam sendInstantMessageParam, String str);

    public static final native long SendInstantMessageParam_txOption_get(long j2, SendInstantMessageParam sendInstantMessageParam);

    public static final native void SendInstantMessageParam_txOption_set(long j2, SendInstantMessageParam sendInstantMessageParam, long j3, SipTxOption sipTxOption);

    public static final native long SendInstantMessageParam_userData_get(long j2, SendInstantMessageParam sendInstantMessageParam);

    public static final native void SendInstantMessageParam_userData_set(long j2, SendInstantMessageParam sendInstantMessageParam, long j3);

    public static final native boolean SendTypingIndicationParam_isTyping_get(long j2, SendTypingIndicationParam sendTypingIndicationParam);

    public static final native void SendTypingIndicationParam_isTyping_set(long j2, SendTypingIndicationParam sendTypingIndicationParam, boolean z);

    public static final native long SendTypingIndicationParam_txOption_get(long j2, SendTypingIndicationParam sendTypingIndicationParam);

    public static final native void SendTypingIndicationParam_txOption_set(long j2, SendTypingIndicationParam sendTypingIndicationParam, long j3, SipTxOption sipTxOption);

    public static final native long SipEventBody_rxMsg_get(long j2, SipEventBody sipEventBody);

    public static final native void SipEventBody_rxMsg_set(long j2, SipEventBody sipEventBody, long j3, RxMsgEvent rxMsgEvent);

    public static final native long SipEventBody_timer_get(long j2, SipEventBody sipEventBody);

    public static final native void SipEventBody_timer_set(long j2, SipEventBody sipEventBody, long j3, TimerEvent timerEvent);

    public static final native long SipEventBody_tsxState_get(long j2, SipEventBody sipEventBody);

    public static final native void SipEventBody_tsxState_set(long j2, SipEventBody sipEventBody, long j3, TsxStateEvent tsxStateEvent);

    public static final native long SipEventBody_txError_get(long j2, SipEventBody sipEventBody);

    public static final native void SipEventBody_txError_set(long j2, SipEventBody sipEventBody, long j3, TxErrorEvent txErrorEvent);

    public static final native long SipEventBody_txMsg_get(long j2, SipEventBody sipEventBody);

    public static final native void SipEventBody_txMsg_set(long j2, SipEventBody sipEventBody, long j3, TxMsgEvent txMsgEvent);

    public static final native long SipEventBody_user_get(long j2, SipEventBody sipEventBody);

    public static final native void SipEventBody_user_set(long j2, SipEventBody sipEventBody, long j3, UserEvent userEvent);

    public static final native long SipEvent_body_get(long j2, SipEvent sipEvent);

    public static final native void SipEvent_body_set(long j2, SipEvent sipEvent, long j3, SipEventBody sipEventBody);

    public static final native long SipEvent_pjEvent_get(long j2, SipEvent sipEvent);

    public static final native void SipEvent_pjEvent_set(long j2, SipEvent sipEvent, long j3);

    public static final native int SipEvent_type_get(long j2, SipEvent sipEvent);

    public static final native void SipEvent_type_set(long j2, SipEvent sipEvent, int i2);

    public static final native void SipHeaderVector_add(long j2, SipHeaderVector sipHeaderVector, long j3, SipHeader sipHeader);

    public static final native long SipHeaderVector_capacity(long j2, SipHeaderVector sipHeaderVector);

    public static final native void SipHeaderVector_clear(long j2, SipHeaderVector sipHeaderVector);

    public static final native long SipHeaderVector_get(long j2, SipHeaderVector sipHeaderVector, int i2);

    public static final native boolean SipHeaderVector_isEmpty(long j2, SipHeaderVector sipHeaderVector);

    public static final native void SipHeaderVector_reserve(long j2, SipHeaderVector sipHeaderVector, long j3);

    public static final native void SipHeaderVector_set(long j2, SipHeaderVector sipHeaderVector, int i2, long j3, SipHeader sipHeader);

    public static final native long SipHeaderVector_size(long j2, SipHeaderVector sipHeaderVector);

    public static final native String SipHeader_hName_get(long j2, SipHeader sipHeader);

    public static final native void SipHeader_hName_set(long j2, SipHeader sipHeader, String str);

    public static final native String SipHeader_hValue_get(long j2, SipHeader sipHeader);

    public static final native void SipHeader_hValue_set(long j2, SipHeader sipHeader, String str);

    public static final native String SipMediaType_subType_get(long j2, SipMediaType sipMediaType);

    public static final native void SipMediaType_subType_set(long j2, SipMediaType sipMediaType, String str);

    public static final native String SipMediaType_type_get(long j2, SipMediaType sipMediaType);

    public static final native void SipMediaType_type_set(long j2, SipMediaType sipMediaType, String str);

    public static final native void SipMultipartPartVector_add(long j2, SipMultipartPartVector sipMultipartPartVector, long j3, SipMultipartPart sipMultipartPart);

    public static final native long SipMultipartPartVector_capacity(long j2, SipMultipartPartVector sipMultipartPartVector);

    public static final native void SipMultipartPartVector_clear(long j2, SipMultipartPartVector sipMultipartPartVector);

    public static final native long SipMultipartPartVector_get(long j2, SipMultipartPartVector sipMultipartPartVector, int i2);

    public static final native boolean SipMultipartPartVector_isEmpty(long j2, SipMultipartPartVector sipMultipartPartVector);

    public static final native void SipMultipartPartVector_reserve(long j2, SipMultipartPartVector sipMultipartPartVector, long j3);

    public static final native void SipMultipartPartVector_set(long j2, SipMultipartPartVector sipMultipartPartVector, int i2, long j3, SipMultipartPart sipMultipartPart);

    public static final native long SipMultipartPartVector_size(long j2, SipMultipartPartVector sipMultipartPartVector);

    public static final native String SipMultipartPart_body_get(long j2, SipMultipartPart sipMultipartPart);

    public static final native void SipMultipartPart_body_set(long j2, SipMultipartPart sipMultipartPart, String str);

    public static final native long SipMultipartPart_contentType_get(long j2, SipMultipartPart sipMultipartPart);

    public static final native void SipMultipartPart_contentType_set(long j2, SipMultipartPart sipMultipartPart, long j3, SipMediaType sipMediaType);

    public static final native long SipMultipartPart_headers_get(long j2, SipMultipartPart sipMultipartPart);

    public static final native void SipMultipartPart_headers_set(long j2, SipMultipartPart sipMultipartPart, long j3, SipHeaderVector sipHeaderVector);

    public static final native String SipRxData_info_get(long j2, SipRxData sipRxData);

    public static final native void SipRxData_info_set(long j2, SipRxData sipRxData, String str);

    public static final native long SipRxData_pjRxData_get(long j2, SipRxData sipRxData);

    public static final native void SipRxData_pjRxData_set(long j2, SipRxData sipRxData, long j3);

    public static final native String SipRxData_srcAddress_get(long j2, SipRxData sipRxData);

    public static final native void SipRxData_srcAddress_set(long j2, SipRxData sipRxData, String str);

    public static final native String SipRxData_wholeMsg_get(long j2, SipRxData sipRxData);

    public static final native void SipRxData_wholeMsg_set(long j2, SipRxData sipRxData, String str);

    public static final native long SipTransaction_lastTx_get(long j2, SipTransaction sipTransaction);

    public static final native void SipTransaction_lastTx_set(long j2, SipTransaction sipTransaction, long j3, SipTxData sipTxData);

    public static final native String SipTransaction_method_get(long j2, SipTransaction sipTransaction);

    public static final native void SipTransaction_method_set(long j2, SipTransaction sipTransaction, String str);

    public static final native long SipTransaction_pjTransaction_get(long j2, SipTransaction sipTransaction);

    public static final native void SipTransaction_pjTransaction_set(long j2, SipTransaction sipTransaction, long j3);

    public static final native int SipTransaction_role_get(long j2, SipTransaction sipTransaction);

    public static final native void SipTransaction_role_set(long j2, SipTransaction sipTransaction, int i2);

    public static final native int SipTransaction_state_get(long j2, SipTransaction sipTransaction);

    public static final native void SipTransaction_state_set(long j2, SipTransaction sipTransaction, int i2);

    public static final native int SipTransaction_statusCode_get(long j2, SipTransaction sipTransaction);

    public static final native void SipTransaction_statusCode_set(long j2, SipTransaction sipTransaction, int i2);

    public static final native String SipTransaction_statusText_get(long j2, SipTransaction sipTransaction);

    public static final native void SipTransaction_statusText_set(long j2, SipTransaction sipTransaction, String str);

    public static final native String SipTxData_dstAddress_get(long j2, SipTxData sipTxData);

    public static final native void SipTxData_dstAddress_set(long j2, SipTxData sipTxData, String str);

    public static final native String SipTxData_info_get(long j2, SipTxData sipTxData);

    public static final native void SipTxData_info_set(long j2, SipTxData sipTxData, String str);

    public static final native long SipTxData_pjTxData_get(long j2, SipTxData sipTxData);

    public static final native void SipTxData_pjTxData_set(long j2, SipTxData sipTxData, long j3);

    public static final native String SipTxData_wholeMsg_get(long j2, SipTxData sipTxData);

    public static final native void SipTxData_wholeMsg_set(long j2, SipTxData sipTxData, String str);

    public static final native String SipTxOption_contentType_get(long j2, SipTxOption sipTxOption);

    public static final native void SipTxOption_contentType_set(long j2, SipTxOption sipTxOption, String str);

    public static final native long SipTxOption_headers_get(long j2, SipTxOption sipTxOption);

    public static final native void SipTxOption_headers_set(long j2, SipTxOption sipTxOption, long j3, SipHeaderVector sipHeaderVector);

    public static final native boolean SipTxOption_isEmpty(long j2, SipTxOption sipTxOption);

    public static final native String SipTxOption_msgBody_get(long j2, SipTxOption sipTxOption);

    public static final native void SipTxOption_msgBody_set(long j2, SipTxOption sipTxOption, String str);

    public static final native long SipTxOption_multipartContentType_get(long j2, SipTxOption sipTxOption);

    public static final native void SipTxOption_multipartContentType_set(long j2, SipTxOption sipTxOption, long j3, SipMediaType sipMediaType);

    public static final native long SipTxOption_multipartParts_get(long j2, SipTxOption sipTxOption);

    public static final native void SipTxOption_multipartParts_set(long j2, SipTxOption sipTxOption, long j3, SipMultipartPartVector sipMultipartPartVector);

    public static final native String SipTxOption_targetUri_get(long j2, SipTxOption sipTxOption);

    public static final native void SipTxOption_targetUri_set(long j2, SipTxOption sipTxOption, String str);

    public static final native long SrtpCrypto_flags_get(long j2, SrtpCrypto srtpCrypto);

    public static final native void SrtpCrypto_flags_set(long j2, SrtpCrypto srtpCrypto, long j3);

    public static final native String SrtpCrypto_key_get(long j2, SrtpCrypto srtpCrypto);

    public static final native void SrtpCrypto_key_set(long j2, SrtpCrypto srtpCrypto, String str);

    public static final native String SrtpCrypto_name_get(long j2, SrtpCrypto srtpCrypto);

    public static final native void SrtpCrypto_name_set(long j2, SrtpCrypto srtpCrypto, String str);

    public static final native boolean SslCertInfo_isEmpty(long j2, SslCertInfo sslCertInfo);

    public static final native String SslCertInfo_issuerCn_get(long j2, SslCertInfo sslCertInfo);

    public static final native void SslCertInfo_issuerCn_set(long j2, SslCertInfo sslCertInfo, String str);

    public static final native String SslCertInfo_issuerInfo_get(long j2, SslCertInfo sslCertInfo);

    public static final native void SslCertInfo_issuerInfo_set(long j2, SslCertInfo sslCertInfo, String str);

    public static final native String SslCertInfo_raw_get(long j2, SslCertInfo sslCertInfo);

    public static final native void SslCertInfo_raw_set(long j2, SslCertInfo sslCertInfo, String str);

    public static final native long SslCertInfo_serialNo_get(long j2, SslCertInfo sslCertInfo);

    public static final native void SslCertInfo_serialNo_set(long j2, SslCertInfo sslCertInfo, long j3);

    public static final native long SslCertInfo_subjectAltName_get(long j2, SslCertInfo sslCertInfo);

    public static final native void SslCertInfo_subjectAltName_set(long j2, SslCertInfo sslCertInfo, long j3);

    public static final native String SslCertInfo_subjectCn_get(long j2, SslCertInfo sslCertInfo);

    public static final native void SslCertInfo_subjectCn_set(long j2, SslCertInfo sslCertInfo, String str);

    public static final native String SslCertInfo_subjectInfo_get(long j2, SslCertInfo sslCertInfo);

    public static final native void SslCertInfo_subjectInfo_set(long j2, SslCertInfo sslCertInfo, String str);

    public static final native long SslCertInfo_validityEnd_get(long j2, SslCertInfo sslCertInfo);

    public static final native void SslCertInfo_validityEnd_set(long j2, SslCertInfo sslCertInfo, long j3, TimeVal timeVal);

    public static final native boolean SslCertInfo_validityGmt_get(long j2, SslCertInfo sslCertInfo);

    public static final native void SslCertInfo_validityGmt_set(long j2, SslCertInfo sslCertInfo, boolean z);

    public static final native long SslCertInfo_validityStart_get(long j2, SslCertInfo sslCertInfo);

    public static final native void SslCertInfo_validityStart_set(long j2, SslCertInfo sslCertInfo, long j3, TimeVal timeVal);

    public static final native long SslCertInfo_version_get(long j2, SslCertInfo sslCertInfo);

    public static final native void SslCertInfo_version_set(long j2, SslCertInfo sslCertInfo, long j3);

    public static final native String SslCertName_name_get(long j2, SslCertName sslCertName);

    public static final native void SslCertName_name_set(long j2, SslCertName sslCertName, String str);

    public static final native int SslCertName_type_get(long j2, SslCertName sslCertName);

    public static final native void SslCertName_type_set(long j2, SslCertName sslCertName, int i2);

    public static final native long StreamInfo_audCodecParam_get(long j2, StreamInfo streamInfo);

    public static final native void StreamInfo_audCodecParam_set(long j2, StreamInfo streamInfo, long j3, CodecParam codecParam);

    public static final native long StreamInfo_codecClockRate_get(long j2, StreamInfo streamInfo);

    public static final native void StreamInfo_codecClockRate_set(long j2, StreamInfo streamInfo, long j3);

    public static final native String StreamInfo_codecName_get(long j2, StreamInfo streamInfo);

    public static final native void StreamInfo_codecName_set(long j2, StreamInfo streamInfo, String str);

    public static final native int StreamInfo_dir_get(long j2, StreamInfo streamInfo);

    public static final native void StreamInfo_dir_set(long j2, StreamInfo streamInfo, int i2);

    public static final native int StreamInfo_proto_get(long j2, StreamInfo streamInfo);

    public static final native void StreamInfo_proto_set(long j2, StreamInfo streamInfo, int i2);

    public static final native String StreamInfo_remoteRtcpAddress_get(long j2, StreamInfo streamInfo);

    public static final native void StreamInfo_remoteRtcpAddress_set(long j2, StreamInfo streamInfo, String str);

    public static final native String StreamInfo_remoteRtpAddress_get(long j2, StreamInfo streamInfo);

    public static final native void StreamInfo_remoteRtpAddress_set(long j2, StreamInfo streamInfo, String str);

    public static final native long StreamInfo_rxPt_get(long j2, StreamInfo streamInfo);

    public static final native void StreamInfo_rxPt_set(long j2, StreamInfo streamInfo, long j3);

    public static final native long StreamInfo_txPt_get(long j2, StreamInfo streamInfo);

    public static final native void StreamInfo_txPt_set(long j2, StreamInfo streamInfo, long j3);

    public static final native int StreamInfo_type_get(long j2, StreamInfo streamInfo);

    public static final native void StreamInfo_type_set(long j2, StreamInfo streamInfo, int i2);

    public static final native long StreamStat_jbuf_get(long j2, StreamStat streamStat);

    public static final native void StreamStat_jbuf_set(long j2, StreamStat streamStat, long j3, JbufState jbufState);

    public static final native long StreamStat_rtcp_get(long j2, StreamStat streamStat);

    public static final native void StreamStat_rtcp_set(long j2, StreamStat streamStat, long j3, RtcpStat rtcpStat);

    public static final native void StringVector_add(long j2, StringVector stringVector, String str);

    public static final native long StringVector_capacity(long j2, StringVector stringVector);

    public static final native void StringVector_clear(long j2, StringVector stringVector);

    public static final native String StringVector_get(long j2, StringVector stringVector, int i2);

    public static final native boolean StringVector_isEmpty(long j2, StringVector stringVector);

    public static final native void StringVector_reserve(long j2, StringVector stringVector, long j3);

    public static final native void StringVector_set(long j2, StringVector stringVector, int i2, String str);

    public static final native long StringVector_size(long j2, StringVector stringVector);

    public static void SwigDirector_Account_onIncomingCall(Account account, long j2) {
        account.onIncomingCall(new OnIncomingCallParam(j2, false));
    }

    public static void SwigDirector_Account_onIncomingSubscribe(Account account, long j2) {
        account.onIncomingSubscribe(new OnIncomingSubscribeParam(j2, false));
    }

    public static void SwigDirector_Account_onInstantMessage(Account account, long j2) {
        account.onInstantMessage(new OnInstantMessageParam(j2, false));
    }

    public static void SwigDirector_Account_onInstantMessageStatus(Account account, long j2) {
        account.onInstantMessageStatus(new OnInstantMessageStatusParam(j2, false));
    }

    public static void SwigDirector_Account_onMwiInfo(Account account, long j2) {
        account.onMwiInfo(new OnMwiInfoParam(j2, false));
    }

    public static void SwigDirector_Account_onRegStarted(Account account, long j2) {
        account.onRegStarted(new OnRegStartedParam(j2, false));
    }

    public static void SwigDirector_Account_onRegState(Account account, long j2) {
        account.onRegState(new OnRegStateParam(j2, false));
    }

    public static void SwigDirector_Account_onTypingIndication(Account account, long j2) {
        account.onTypingIndication(new OnTypingIndicationParam(j2, false));
    }

    public static boolean SwigDirector_AudioMediaPlayer_onEof(AudioMediaPlayer audioMediaPlayer) {
        return audioMediaPlayer.onEof();
    }

    public static void SwigDirector_Buddy_onBuddyEvSubState(Buddy buddy, long j2) {
        buddy.onBuddyEvSubState(new OnBuddyEvSubStateParam(j2, false));
    }

    public static void SwigDirector_Buddy_onBuddyState(Buddy buddy) {
        buddy.onBuddyState();
    }

    public static void SwigDirector_Call_onCallMediaEvent(Call call, long j2) {
        call.onCallMediaEvent(new OnCallMediaEventParam(j2, false));
    }

    public static void SwigDirector_Call_onCallMediaState(Call call, long j2) {
        call.onCallMediaState(new OnCallMediaStateParam(j2, false));
    }

    public static void SwigDirector_Call_onCallMediaTransportState(Call call, long j2) {
        call.onCallMediaTransportState(new OnCallMediaTransportStateParam(j2, false));
    }

    public static int SwigDirector_Call_onCallRedirected(Call call, long j2) {
        return call.onCallRedirected(new OnCallRedirectedParam(j2, false)).swigValue();
    }

    public static void SwigDirector_Call_onCallReplaceRequest(Call call, long j2) {
        call.onCallReplaceRequest(new OnCallReplaceRequestParam(j2, false));
    }

    public static void SwigDirector_Call_onCallReplaced(Call call, long j2) {
        call.onCallReplaced(new OnCallReplacedParam(j2, false));
    }

    public static void SwigDirector_Call_onCallRxOffer(Call call, long j2) {
        call.onCallRxOffer(new OnCallRxOfferParam(j2, false));
    }

    public static void SwigDirector_Call_onCallSdpCreated(Call call, long j2) {
        call.onCallSdpCreated(new OnCallSdpCreatedParam(j2, false));
    }

    public static void SwigDirector_Call_onCallState(Call call, long j2) {
        call.onCallState(new OnCallStateParam(j2, false));
    }

    public static void SwigDirector_Call_onCallTransferRequest(Call call, long j2) {
        call.onCallTransferRequest(new OnCallTransferRequestParam(j2, false));
    }

    public static void SwigDirector_Call_onCallTransferStatus(Call call, long j2) {
        call.onCallTransferStatus(new OnCallTransferStatusParam(j2, false));
    }

    public static void SwigDirector_Call_onCallTsxState(Call call, long j2) {
        call.onCallTsxState(new OnCallTsxStateParam(j2, false));
    }

    public static void SwigDirector_Call_onCallTxOffer(Call call, long j2) {
        call.onCallTxOffer(new OnCallTxOfferParam(j2, false));
    }

    public static void SwigDirector_Call_onCreateMediaTransport(Call call, long j2) {
        call.onCreateMediaTransport(new OnCreateMediaTransportParam(j2, false));
    }

    public static void SwigDirector_Call_onCreateMediaTransportSrtp(Call call, long j2) {
        call.onCreateMediaTransportSrtp(new OnCreateMediaTransportSrtpParam(j2, false));
    }

    public static void SwigDirector_Call_onDtmfDigit(Call call, long j2) {
        call.onDtmfDigit(new OnDtmfDigitParam(j2, false));
    }

    public static void SwigDirector_Call_onInstantMessage(Call call, long j2) {
        call.onInstantMessage(new OnInstantMessageParam(j2, false));
    }

    public static void SwigDirector_Call_onInstantMessageStatus(Call call, long j2) {
        call.onInstantMessageStatus(new OnInstantMessageStatusParam(j2, false));
    }

    public static void SwigDirector_Call_onStreamCreated(Call call, long j2) {
        call.onStreamCreated(new OnStreamCreatedParam(j2, false));
    }

    public static void SwigDirector_Call_onStreamDestroyed(Call call, long j2) {
        call.onStreamDestroyed(new OnStreamDestroyedParam(j2, false));
    }

    public static void SwigDirector_Call_onTypingIndication(Call call, long j2) {
        call.onTypingIndication(new OnTypingIndicationParam(j2, false));
    }

    public static void SwigDirector_Endpoint_onIpChangeProgress(Endpoint endpoint, long j2) {
        endpoint.onIpChangeProgress(new OnIpChangeProgressParam(j2, false));
    }

    public static void SwigDirector_Endpoint_onNatCheckStunServersComplete(Endpoint endpoint, long j2) {
        endpoint.onNatCheckStunServersComplete(new OnNatCheckStunServersCompleteParam(j2, false));
    }

    public static void SwigDirector_Endpoint_onNatDetectionComplete(Endpoint endpoint, long j2) {
        endpoint.onNatDetectionComplete(new OnNatDetectionCompleteParam(j2, false));
    }

    public static void SwigDirector_Endpoint_onSelectAccount(Endpoint endpoint, long j2) {
        endpoint.onSelectAccount(new OnSelectAccountParam(j2, false));
    }

    public static void SwigDirector_Endpoint_onTimer(Endpoint endpoint, long j2) {
        endpoint.onTimer(new OnTimerParam(j2, false));
    }

    public static void SwigDirector_Endpoint_onTransportState(Endpoint endpoint, long j2) {
        endpoint.onTransportState(new OnTransportStateParam(j2, false));
    }

    public static boolean SwigDirector_FindBuddyMatch_match(FindBuddyMatch findBuddyMatch, String str, long j2) {
        return findBuddyMatch.match(str, new Buddy(j2, false));
    }

    public static void SwigDirector_LogWriter_write(LogWriter logWriter, long j2) {
        logWriter.write(new LogEntry(j2, false));
    }

    public static final native int TimeVal_msec_get(long j2, TimeVal timeVal);

    public static final native void TimeVal_msec_set(long j2, TimeVal timeVal, int i2);

    public static final native int TimeVal_sec_get(long j2, TimeVal timeVal);

    public static final native void TimeVal_sec_set(long j2, TimeVal timeVal, int i2);

    public static final native long TimerEvent_entry_get(long j2, TimerEvent timerEvent);

    public static final native void TimerEvent_entry_set(long j2, TimerEvent timerEvent, long j3);

    public static final native String TlsConfig_CaListFile_get(long j2, TlsConfig tlsConfig);

    public static final native void TlsConfig_CaListFile_set(long j2, TlsConfig tlsConfig, String str);

    public static final native String TlsConfig_certFile_get(long j2, TlsConfig tlsConfig);

    public static final native void TlsConfig_certFile_set(long j2, TlsConfig tlsConfig, String str);

    public static final native long TlsConfig_ciphers_get(long j2, TlsConfig tlsConfig);

    public static final native void TlsConfig_ciphers_set(long j2, TlsConfig tlsConfig, long j3, IntVector intVector);

    public static final native int TlsConfig_method_get(long j2, TlsConfig tlsConfig);

    public static final native void TlsConfig_method_set(long j2, TlsConfig tlsConfig, int i2);

    public static final native long TlsConfig_msecTimeout_get(long j2, TlsConfig tlsConfig);

    public static final native void TlsConfig_msecTimeout_set(long j2, TlsConfig tlsConfig, long j3);

    public static final native String TlsConfig_password_get(long j2, TlsConfig tlsConfig);

    public static final native void TlsConfig_password_set(long j2, TlsConfig tlsConfig, String str);

    public static final native String TlsConfig_privKeyFile_get(long j2, TlsConfig tlsConfig);

    public static final native void TlsConfig_privKeyFile_set(long j2, TlsConfig tlsConfig, String str);

    public static final native long TlsConfig_proto_get(long j2, TlsConfig tlsConfig);

    public static final native void TlsConfig_proto_set(long j2, TlsConfig tlsConfig, long j3);

    public static final native boolean TlsConfig_qosIgnoreError_get(long j2, TlsConfig tlsConfig);

    public static final native void TlsConfig_qosIgnoreError_set(long j2, TlsConfig tlsConfig, boolean z);

    public static final native long TlsConfig_qosParams_get(long j2, TlsConfig tlsConfig);

    public static final native void TlsConfig_qosParams_set(long j2, TlsConfig tlsConfig, long j3, pj_qos_params pj_qos_paramsVar);

    public static final native int TlsConfig_qosType_get(long j2, TlsConfig tlsConfig);

    public static final native void TlsConfig_qosType_set(long j2, TlsConfig tlsConfig, int i2);

    public static final native boolean TlsConfig_requireClientCert_get(long j2, TlsConfig tlsConfig);

    public static final native void TlsConfig_requireClientCert_set(long j2, TlsConfig tlsConfig, boolean z);

    public static final native boolean TlsConfig_verifyClient_get(long j2, TlsConfig tlsConfig);

    public static final native void TlsConfig_verifyClient_set(long j2, TlsConfig tlsConfig, boolean z);

    public static final native boolean TlsConfig_verifyServer_get(long j2, TlsConfig tlsConfig);

    public static final native void TlsConfig_verifyServer_set(long j2, TlsConfig tlsConfig, boolean z);

    public static final native String TlsInfo_cipherName_get(long j2, TlsInfo tlsInfo);

    public static final native void TlsInfo_cipherName_set(long j2, TlsInfo tlsInfo, String str);

    public static final native int TlsInfo_cipher_get(long j2, TlsInfo tlsInfo);

    public static final native void TlsInfo_cipher_set(long j2, TlsInfo tlsInfo, int i2);

    public static final native boolean TlsInfo_established_get(long j2, TlsInfo tlsInfo);

    public static final native void TlsInfo_established_set(long j2, TlsInfo tlsInfo, boolean z);

    public static final native boolean TlsInfo_isEmpty(long j2, TlsInfo tlsInfo);

    public static final native String TlsInfo_localAddr_get(long j2, TlsInfo tlsInfo);

    public static final native void TlsInfo_localAddr_set(long j2, TlsInfo tlsInfo, String str);

    public static final native long TlsInfo_localCertInfo_get(long j2, TlsInfo tlsInfo);

    public static final native void TlsInfo_localCertInfo_set(long j2, TlsInfo tlsInfo, long j3, SslCertInfo sslCertInfo);

    public static final native long TlsInfo_protocol_get(long j2, TlsInfo tlsInfo);

    public static final native void TlsInfo_protocol_set(long j2, TlsInfo tlsInfo, long j3);

    public static final native String TlsInfo_remoteAddr_get(long j2, TlsInfo tlsInfo);

    public static final native void TlsInfo_remoteAddr_set(long j2, TlsInfo tlsInfo, String str);

    public static final native long TlsInfo_remoteCertInfo_get(long j2, TlsInfo tlsInfo);

    public static final native void TlsInfo_remoteCertInfo_set(long j2, TlsInfo tlsInfo, long j3, SslCertInfo sslCertInfo);

    public static final native long TlsInfo_verifyMsgs_get(long j2, TlsInfo tlsInfo);

    public static final native void TlsInfo_verifyMsgs_set(long j2, TlsInfo tlsInfo, long j3, StringVector stringVector);

    public static final native long TlsInfo_verifyStatus_get(long j2, TlsInfo tlsInfo);

    public static final native void TlsInfo_verifyStatus_set(long j2, TlsInfo tlsInfo, long j3);

    public static final native void ToneDescVector_add(long j2, ToneDescVector toneDescVector, long j3, ToneDesc toneDesc);

    public static final native long ToneDescVector_capacity(long j2, ToneDescVector toneDescVector);

    public static final native void ToneDescVector_clear(long j2, ToneDescVector toneDescVector);

    public static final native long ToneDescVector_get(long j2, ToneDescVector toneDescVector, int i2);

    public static final native boolean ToneDescVector_isEmpty(long j2, ToneDescVector toneDescVector);

    public static final native void ToneDescVector_reserve(long j2, ToneDescVector toneDescVector, long j3);

    public static final native void ToneDescVector_set(long j2, ToneDescVector toneDescVector, int i2, long j3, ToneDesc toneDesc);

    public static final native long ToneDescVector_size(long j2, ToneDescVector toneDescVector);

    public static final native long ToneDesc_SWIGUpcast(long j2);

    public static final native String ToneDigitMapDigit_digit_get(long j2, ToneDigitMapDigit toneDigitMapDigit);

    public static final native void ToneDigitMapDigit_digit_set(long j2, ToneDigitMapDigit toneDigitMapDigit, String str);

    public static final native int ToneDigitMapDigit_freq1_get(long j2, ToneDigitMapDigit toneDigitMapDigit);

    public static final native void ToneDigitMapDigit_freq1_set(long j2, ToneDigitMapDigit toneDigitMapDigit, int i2);

    public static final native int ToneDigitMapDigit_freq2_get(long j2, ToneDigitMapDigit toneDigitMapDigit);

    public static final native void ToneDigitMapDigit_freq2_set(long j2, ToneDigitMapDigit toneDigitMapDigit, int i2);

    public static final native void ToneDigitMapVector_add(long j2, ToneDigitMapVector toneDigitMapVector, long j3, ToneDigitMapDigit toneDigitMapDigit);

    public static final native long ToneDigitMapVector_capacity(long j2, ToneDigitMapVector toneDigitMapVector);

    public static final native void ToneDigitMapVector_clear(long j2, ToneDigitMapVector toneDigitMapVector);

    public static final native long ToneDigitMapVector_get(long j2, ToneDigitMapVector toneDigitMapVector, int i2);

    public static final native boolean ToneDigitMapVector_isEmpty(long j2, ToneDigitMapVector toneDigitMapVector);

    public static final native void ToneDigitMapVector_reserve(long j2, ToneDigitMapVector toneDigitMapVector, long j3);

    public static final native void ToneDigitMapVector_set(long j2, ToneDigitMapVector toneDigitMapVector, int i2, long j3, ToneDigitMapDigit toneDigitMapDigit);

    public static final native long ToneDigitMapVector_size(long j2, ToneDigitMapVector toneDigitMapVector);

    public static final native void ToneDigitVector_add(long j2, ToneDigitVector toneDigitVector, long j3, ToneDigit toneDigit);

    public static final native long ToneDigitVector_capacity(long j2, ToneDigitVector toneDigitVector);

    public static final native void ToneDigitVector_clear(long j2, ToneDigitVector toneDigitVector);

    public static final native long ToneDigitVector_get(long j2, ToneDigitVector toneDigitVector, int i2);

    public static final native boolean ToneDigitVector_isEmpty(long j2, ToneDigitVector toneDigitVector);

    public static final native void ToneDigitVector_reserve(long j2, ToneDigitVector toneDigitVector, long j3);

    public static final native void ToneDigitVector_set(long j2, ToneDigitVector toneDigitVector, int i2, long j3, ToneDigit toneDigit);

    public static final native long ToneDigitVector_size(long j2, ToneDigitVector toneDigitVector);

    public static final native long ToneDigit_SWIGUpcast(long j2);

    public static final native long ToneGenerator_SWIGUpcast(long j2);

    public static final native void ToneGenerator_createToneGenerator__SWIG_0(long j2, ToneGenerator toneGenerator, long j3, long j4) throws Exception;

    public static final native void ToneGenerator_createToneGenerator__SWIG_1(long j2, ToneGenerator toneGenerator, long j3) throws Exception;

    public static final native void ToneGenerator_createToneGenerator__SWIG_2(long j2, ToneGenerator toneGenerator) throws Exception;

    public static final native long ToneGenerator_getDigitMap(long j2, ToneGenerator toneGenerator) throws Exception;

    public static final native boolean ToneGenerator_isBusy(long j2, ToneGenerator toneGenerator);

    public static final native void ToneGenerator_playDigits__SWIG_0(long j2, ToneGenerator toneGenerator, long j3, ToneDigitVector toneDigitVector, boolean z) throws Exception;

    public static final native void ToneGenerator_playDigits__SWIG_1(long j2, ToneGenerator toneGenerator, long j3, ToneDigitVector toneDigitVector) throws Exception;

    public static final native void ToneGenerator_play__SWIG_0(long j2, ToneGenerator toneGenerator, long j3, ToneDescVector toneDescVector, boolean z) throws Exception;

    public static final native void ToneGenerator_play__SWIG_1(long j2, ToneGenerator toneGenerator, long j3, ToneDescVector toneDescVector) throws Exception;

    public static final native void ToneGenerator_rewind(long j2, ToneGenerator toneGenerator) throws Exception;

    public static final native void ToneGenerator_setDigitMap(long j2, ToneGenerator toneGenerator, long j3, ToneDigitMapVector toneDigitMapVector) throws Exception;

    public static final native void ToneGenerator_stop(long j2, ToneGenerator toneGenerator) throws Exception;

    public static final native String TransportConfig_boundAddress_get(long j2, TransportConfig transportConfig);

    public static final native void TransportConfig_boundAddress_set(long j2, TransportConfig transportConfig, String str);

    public static final native long TransportConfig_portRange_get(long j2, TransportConfig transportConfig);

    public static final native void TransportConfig_portRange_set(long j2, TransportConfig transportConfig, long j3);

    public static final native long TransportConfig_port_get(long j2, TransportConfig transportConfig);

    public static final native void TransportConfig_port_set(long j2, TransportConfig transportConfig, long j3);

    public static final native String TransportConfig_publicAddress_get(long j2, TransportConfig transportConfig);

    public static final native void TransportConfig_publicAddress_set(long j2, TransportConfig transportConfig, String str);

    public static final native long TransportConfig_qosParams_get(long j2, TransportConfig transportConfig);

    public static final native void TransportConfig_qosParams_set(long j2, TransportConfig transportConfig, long j3, pj_qos_params pj_qos_paramsVar);

    public static final native int TransportConfig_qosType_get(long j2, TransportConfig transportConfig);

    public static final native void TransportConfig_qosType_set(long j2, TransportConfig transportConfig, int i2);

    public static final native long TransportConfig_tlsConfig_get(long j2, TransportConfig transportConfig);

    public static final native void TransportConfig_tlsConfig_set(long j2, TransportConfig transportConfig, long j3, TlsConfig tlsConfig);

    public static final native long TransportInfo_flags_get(long j2, TransportInfo transportInfo);

    public static final native void TransportInfo_flags_set(long j2, TransportInfo transportInfo, long j3);

    public static final native int TransportInfo_id_get(long j2, TransportInfo transportInfo);

    public static final native void TransportInfo_id_set(long j2, TransportInfo transportInfo, int i2);

    public static final native String TransportInfo_info_get(long j2, TransportInfo transportInfo);

    public static final native void TransportInfo_info_set(long j2, TransportInfo transportInfo, String str);

    public static final native String TransportInfo_localAddress_get(long j2, TransportInfo transportInfo);

    public static final native void TransportInfo_localAddress_set(long j2, TransportInfo transportInfo, String str);

    public static final native String TransportInfo_localName_get(long j2, TransportInfo transportInfo);

    public static final native void TransportInfo_localName_set(long j2, TransportInfo transportInfo, String str);

    public static final native String TransportInfo_typeName_get(long j2, TransportInfo transportInfo);

    public static final native void TransportInfo_typeName_set(long j2, TransportInfo transportInfo, String str);

    public static final native int TransportInfo_type_get(long j2, TransportInfo transportInfo);

    public static final native void TransportInfo_type_set(long j2, TransportInfo transportInfo, int i2);

    public static final native long TransportInfo_usageCount_get(long j2, TransportInfo transportInfo);

    public static final native void TransportInfo_usageCount_set(long j2, TransportInfo transportInfo, long j3);

    public static final native long TsxStateEventSrc_data_get(long j2, TsxStateEventSrc tsxStateEventSrc);

    public static final native void TsxStateEventSrc_data_set(long j2, TsxStateEventSrc tsxStateEventSrc, long j3);

    public static final native long TsxStateEventSrc_rdata_get(long j2, TsxStateEventSrc tsxStateEventSrc);

    public static final native void TsxStateEventSrc_rdata_set(long j2, TsxStateEventSrc tsxStateEventSrc, long j3, SipRxData sipRxData);

    public static final native int TsxStateEventSrc_status_get(long j2, TsxStateEventSrc tsxStateEventSrc);

    public static final native void TsxStateEventSrc_status_set(long j2, TsxStateEventSrc tsxStateEventSrc, int i2);

    public static final native long TsxStateEventSrc_tdata_get(long j2, TsxStateEventSrc tsxStateEventSrc);

    public static final native void TsxStateEventSrc_tdata_set(long j2, TsxStateEventSrc tsxStateEventSrc, long j3, SipTxData sipTxData);

    public static final native long TsxStateEventSrc_timer_get(long j2, TsxStateEventSrc tsxStateEventSrc);

    public static final native void TsxStateEventSrc_timer_set(long j2, TsxStateEventSrc tsxStateEventSrc, long j3);

    public static final native int TsxStateEvent_prevState_get(long j2, TsxStateEvent tsxStateEvent);

    public static final native void TsxStateEvent_prevState_set(long j2, TsxStateEvent tsxStateEvent, int i2);

    public static final native long TsxStateEvent_src_get(long j2, TsxStateEvent tsxStateEvent);

    public static final native void TsxStateEvent_src_set(long j2, TsxStateEvent tsxStateEvent, long j3, TsxStateEventSrc tsxStateEventSrc);

    public static final native long TsxStateEvent_tsx_get(long j2, TsxStateEvent tsxStateEvent);

    public static final native void TsxStateEvent_tsx_set(long j2, TsxStateEvent tsxStateEvent, long j3, SipTransaction sipTransaction);

    public static final native int TsxStateEvent_type_get(long j2, TsxStateEvent tsxStateEvent);

    public static final native void TsxStateEvent_type_set(long j2, TsxStateEvent tsxStateEvent, int i2);

    public static final native long TxErrorEvent_tdata_get(long j2, TxErrorEvent txErrorEvent);

    public static final native void TxErrorEvent_tdata_set(long j2, TxErrorEvent txErrorEvent, long j3, SipTxData sipTxData);

    public static final native long TxErrorEvent_tsx_get(long j2, TxErrorEvent txErrorEvent);

    public static final native void TxErrorEvent_tsx_set(long j2, TxErrorEvent txErrorEvent, long j3, SipTransaction sipTransaction);

    public static final native long TxMsgEvent_tdata_get(long j2, TxMsgEvent txMsgEvent);

    public static final native void TxMsgEvent_tdata_set(long j2, TxMsgEvent txMsgEvent, long j3, SipTxData sipTxData);

    public static final native boolean UaConfig_mainThreadOnly_get(long j2, UaConfig uaConfig);

    public static final native void UaConfig_mainThreadOnly_set(long j2, UaConfig uaConfig, boolean z);

    public static final native long UaConfig_maxCalls_get(long j2, UaConfig uaConfig);

    public static final native void UaConfig_maxCalls_set(long j2, UaConfig uaConfig, long j3);

    public static final native boolean UaConfig_mwiUnsolicitedEnabled_get(long j2, UaConfig uaConfig);

    public static final native void UaConfig_mwiUnsolicitedEnabled_set(long j2, UaConfig uaConfig, boolean z);

    public static final native long UaConfig_nameserver_get(long j2, UaConfig uaConfig);

    public static final native void UaConfig_nameserver_set(long j2, UaConfig uaConfig, long j3, StringVector stringVector);

    public static final native int UaConfig_natTypeInSdp_get(long j2, UaConfig uaConfig);

    public static final native void UaConfig_natTypeInSdp_set(long j2, UaConfig uaConfig, int i2);

    public static final native boolean UaConfig_stunIgnoreFailure_get(long j2, UaConfig uaConfig);

    public static final native void UaConfig_stunIgnoreFailure_set(long j2, UaConfig uaConfig, boolean z);

    public static final native long UaConfig_stunServer_get(long j2, UaConfig uaConfig);

    public static final native void UaConfig_stunServer_set(long j2, UaConfig uaConfig, long j3, StringVector stringVector);

    public static final native boolean UaConfig_stunTryIpv6_get(long j2, UaConfig uaConfig);

    public static final native void UaConfig_stunTryIpv6_set(long j2, UaConfig uaConfig, boolean z);

    public static final native long UaConfig_threadCnt_get(long j2, UaConfig uaConfig);

    public static final native void UaConfig_threadCnt_set(long j2, UaConfig uaConfig, long j3);

    public static final native String UaConfig_userAgent_get(long j2, UaConfig uaConfig);

    public static final native void UaConfig_userAgent_set(long j2, UaConfig uaConfig, String str);

    public static final native long UserEvent_user1_get(long j2, UserEvent userEvent);

    public static final native void UserEvent_user1_set(long j2, UserEvent userEvent, long j3);

    public static final native long UserEvent_user2_get(long j2, UserEvent userEvent);

    public static final native void UserEvent_user2_set(long j2, UserEvent userEvent, long j3);

    public static final native long UserEvent_user3_get(long j2, UserEvent userEvent);

    public static final native void UserEvent_user3_set(long j2, UserEvent userEvent, long j3);

    public static final native long UserEvent_user4_get(long j2, UserEvent userEvent);

    public static final native void UserEvent_user4_set(long j2, UserEvent userEvent, long j3);

    public static final native String Version_full_get(long j2, Version version);

    public static final native void Version_full_set(long j2, Version version, String str);

    public static final native int Version_major_get(long j2, Version version);

    public static final native void Version_major_set(long j2, Version version, int i2);

    public static final native int Version_minor_get(long j2, Version version);

    public static final native void Version_minor_set(long j2, Version version, int i2);

    public static final native long Version_numeric_get(long j2, Version version);

    public static final native void Version_numeric_set(long j2, Version version, long j3);

    public static final native int Version_rev_get(long j2, Version version);

    public static final native void Version_rev_set(long j2, Version version, int i2);

    public static final native String Version_suffix_get(long j2, Version version);

    public static final native void Version_suffix_set(long j2, Version version, String str);

    public static final native void delete_Account(long j2);

    public static final native void delete_AccountCallConfig(long j2);

    public static final native void delete_AccountConfig(long j2);

    public static final native void delete_AccountInfo(long j2);

    public static final native void delete_AccountIpChangeConfig(long j2);

    public static final native void delete_AccountMediaConfig(long j2);

    public static final native void delete_AccountMwiConfig(long j2);

    public static final native void delete_AccountNatConfig(long j2);

    public static final native void delete_AccountPresConfig(long j2);

    public static final native void delete_AccountRegConfig(long j2);

    public static final native void delete_AccountSipConfig(long j2);

    public static final native void delete_AudioDevInfo(long j2);

    public static final native void delete_AudioDevInfoVector(long j2);

    public static final native void delete_AudioMedia(long j2);

    public static final native void delete_AudioMediaPlayer(long j2);

    public static final native void delete_AudioMediaPlayerInfo(long j2);

    public static final native void delete_AudioMediaRecorder(long j2);

    public static final native void delete_AudioMediaVector(long j2);

    public static final native void delete_AuthCredInfo(long j2);

    public static final native void delete_AuthCredInfoVector(long j2);

    public static final native void delete_Buddy(long j2);

    public static final native void delete_BuddyConfig(long j2);

    public static final native void delete_BuddyInfo(long j2);

    public static final native void delete_BuddyVector(long j2);

    public static final native void delete_Call(long j2);

    public static final native void delete_CallInfo(long j2);

    public static final native void delete_CallMediaInfo(long j2);

    public static final native void delete_CallMediaInfoVector(long j2);

    public static final native void delete_CallOpParam(long j2);

    public static final native void delete_CallSendRequestParam(long j2);

    public static final native void delete_CallSetting(long j2);

    public static final native void delete_CodecFmtp(long j2);

    public static final native void delete_CodecFmtpVector(long j2);

    public static final native void delete_CodecInfo(long j2);

    public static final native void delete_CodecInfoVector(long j2);

    public static final native void delete_CodecParam(long j2);

    public static final native void delete_CodecParamInfo(long j2);

    public static final native void delete_CodecParamSetting(long j2);

    public static final native void delete_ConfPortInfo(long j2);

    public static final native void delete_Endpoint(long j2);

    public static final native void delete_EpConfig(long j2);

    public static final native void delete_Error(long j2);

    public static final native void delete_FindBuddyMatch(long j2);

    public static final native void delete_IntVector(long j2);

    public static final native void delete_IpChangeParam(long j2);

    public static final native void delete_JbufState(long j2);

    public static final native void delete_LogConfig(long j2);

    public static final native void delete_LogEntry(long j2);

    public static final native void delete_LogWriter(long j2);

    public static final native void delete_LossType(long j2);

    public static final native void delete_MathStat(long j2);

    public static final native void delete_Media(long j2);

    public static final native void delete_MediaConfig(long j2);

    public static final native void delete_MediaEvent(long j2);

    public static final native void delete_MediaEventData(long j2);

    public static final native void delete_MediaFmtChangedEvent(long j2);

    public static final native void delete_MediaFormat(long j2);

    public static final native void delete_MediaFormatAudio(long j2);

    public static final native void delete_MediaFormatVector(long j2);

    public static final native void delete_MediaTransportInfo(long j2);

    public static final native void delete_OnBuddyEvSubStateParam(long j2);

    public static final native void delete_OnCallMediaEventParam(long j2);

    public static final native void delete_OnCallMediaStateParam(long j2);

    public static final native void delete_OnCallMediaTransportStateParam(long j2);

    public static final native void delete_OnCallRedirectedParam(long j2);

    public static final native void delete_OnCallReplaceRequestParam(long j2);

    public static final native void delete_OnCallReplacedParam(long j2);

    public static final native void delete_OnCallRxOfferParam(long j2);

    public static final native void delete_OnCallSdpCreatedParam(long j2);

    public static final native void delete_OnCallStateParam(long j2);

    public static final native void delete_OnCallTransferRequestParam(long j2);

    public static final native void delete_OnCallTransferStatusParam(long j2);

    public static final native void delete_OnCallTsxStateParam(long j2);

    public static final native void delete_OnCallTxOfferParam(long j2);

    public static final native void delete_OnCreateMediaTransportParam(long j2);

    public static final native void delete_OnCreateMediaTransportSrtpParam(long j2);

    public static final native void delete_OnDtmfDigitParam(long j2);

    public static final native void delete_OnIncomingCallParam(long j2);

    public static final native void delete_OnIncomingSubscribeParam(long j2);

    public static final native void delete_OnInstantMessageParam(long j2);

    public static final native void delete_OnInstantMessageStatusParam(long j2);

    public static final native void delete_OnIpChangeProgressParam(long j2);

    public static final native void delete_OnMwiInfoParam(long j2);

    public static final native void delete_OnNatCheckStunServersCompleteParam(long j2);

    public static final native void delete_OnNatDetectionCompleteParam(long j2);

    public static final native void delete_OnRegStartedParam(long j2);

    public static final native void delete_OnRegStateParam(long j2);

    public static final native void delete_OnSelectAccountParam(long j2);

    public static final native void delete_OnStreamCreatedParam(long j2);

    public static final native void delete_OnStreamDestroyedParam(long j2);

    public static final native void delete_OnTimerParam(long j2);

    public static final native void delete_OnTransportStateParam(long j2);

    public static final native void delete_OnTypingIndicationParam(long j2);

    public static final native void delete_PendingJob(long j2);

    public static final native void delete_PresNotifyParam(long j2);

    public static final native void delete_PresenceStatus(long j2);

    public static final native void delete_RegProgressParam(long j2);

    public static final native void delete_RtcpSdes(long j2);

    public static final native void delete_RtcpStat(long j2);

    public static final native void delete_RtcpStreamStat(long j2);

    public static final native void delete_RxMsgEvent(long j2);

    public static final native void delete_SdpSession(long j2);

    public static final native void delete_SendInstantMessageParam(long j2);

    public static final native void delete_SendTypingIndicationParam(long j2);

    public static final native void delete_SipEvent(long j2);

    public static final native void delete_SipEventBody(long j2);

    public static final native void delete_SipHeader(long j2);

    public static final native void delete_SipHeaderVector(long j2);

    public static final native void delete_SipMediaType(long j2);

    public static final native void delete_SipMultipartPart(long j2);

    public static final native void delete_SipMultipartPartVector(long j2);

    public static final native void delete_SipRxData(long j2);

    public static final native void delete_SipTransaction(long j2);

    public static final native void delete_SipTxData(long j2);

    public static final native void delete_SipTxOption(long j2);

    public static final native void delete_SrtpCrypto(long j2);

    public static final native void delete_SslCertInfo(long j2);

    public static final native void delete_SslCertName(long j2);

    public static final native void delete_StreamInfo(long j2);

    public static final native void delete_StreamStat(long j2);

    public static final native void delete_StringVector(long j2);

    public static final native void delete_TimeVal(long j2);

    public static final native void delete_TimerEvent(long j2);

    public static final native void delete_TlsConfig(long j2);

    public static final native void delete_TlsInfo(long j2);

    public static final native void delete_ToneDesc(long j2);

    public static final native void delete_ToneDescVector(long j2);

    public static final native void delete_ToneDigit(long j2);

    public static final native void delete_ToneDigitMapDigit(long j2);

    public static final native void delete_ToneDigitMapVector(long j2);

    public static final native void delete_ToneDigitVector(long j2);

    public static final native void delete_ToneGenerator(long j2);

    public static final native void delete_TransportConfig(long j2);

    public static final native void delete_TransportInfo(long j2);

    public static final native void delete_TsxStateEvent(long j2);

    public static final native void delete_TsxStateEventSrc(long j2);

    public static final native void delete_TxErrorEvent(long j2);

    public static final native void delete_TxMsgEvent(long j2);

    public static final native void delete_UaConfig(long j2);

    public static final native void delete_UserEvent(long j2);

    public static final native void delete_Version(long j2);

    public static final native void delete_pj_qos_params(long j2);

    public static final native void delete_pjmedia_tone_desc(long j2);

    public static final native void delete_pjmedia_tone_digit(long j2);

    public static final native void delete_pjmedia_tone_digit_map(long j2);

    public static final native long new_Account();

    public static final native long new_AccountCallConfig();

    public static final native long new_AccountConfig();

    public static final native long new_AccountInfo();

    public static final native long new_AccountIpChangeConfig();

    public static final native long new_AccountMediaConfig();

    public static final native long new_AccountMwiConfig();

    public static final native long new_AccountNatConfig();

    public static final native long new_AccountPresConfig();

    public static final native long new_AccountRegConfig();

    public static final native long new_AccountSipConfig();

    public static final native long new_AudioDevInfo();

    public static final native long new_AudioDevInfoVector__SWIG_0();

    public static final native long new_AudioDevInfoVector__SWIG_1(long j2);

    public static final native long new_AudioMediaPlayer();

    public static final native long new_AudioMediaPlayerInfo();

    public static final native long new_AudioMediaRecorder();

    public static final native long new_AudioMediaVector__SWIG_0();

    public static final native long new_AudioMediaVector__SWIG_1(long j2);

    public static final native long new_AuthCredInfoVector__SWIG_0();

    public static final native long new_AuthCredInfoVector__SWIG_1(long j2);

    public static final native long new_AuthCredInfo__SWIG_0();

    public static final native long new_AuthCredInfo__SWIG_1(String str, String str2, String str3, int i2, String str4);

    public static final native long new_Buddy();

    public static final native long new_BuddyConfig();

    public static final native long new_BuddyInfo();

    public static final native long new_BuddyVector__SWIG_0();

    public static final native long new_BuddyVector__SWIG_1(long j2);

    public static final native long new_CallInfo();

    public static final native long new_CallMediaInfo();

    public static final native long new_CallMediaInfoVector__SWIG_0();

    public static final native long new_CallMediaInfoVector__SWIG_1(long j2);

    public static final native long new_CallOpParam__SWIG_0(boolean z);

    public static final native long new_CallOpParam__SWIG_1();

    public static final native long new_CallSendRequestParam();

    public static final native long new_CallSetting__SWIG_0(long j2);

    public static final native long new_CallSetting__SWIG_1();

    public static final native long new_Call__SWIG_0(long j2, Account account, int i2);

    public static final native long new_Call__SWIG_1(long j2, Account account);

    public static final native long new_CodecFmtp();

    public static final native long new_CodecFmtpVector__SWIG_0();

    public static final native long new_CodecFmtpVector__SWIG_1(long j2);

    public static final native long new_CodecInfo();

    public static final native long new_CodecInfoVector__SWIG_0();

    public static final native long new_CodecInfoVector__SWIG_1(long j2);

    public static final native long new_CodecParam();

    public static final native long new_CodecParamInfo();

    public static final native long new_CodecParamSetting();

    public static final native long new_ConfPortInfo();

    public static final native long new_Endpoint();

    public static final native long new_EpConfig();

    public static final native long new_Error__SWIG_0();

    public static final native long new_Error__SWIG_1(int i2, String str, String str2, String str3, int i3);

    public static final native long new_FindBuddyMatch();

    public static final native long new_IntVector__SWIG_0();

    public static final native long new_IntVector__SWIG_1(long j2);

    public static final native long new_IpChangeParam();

    public static final native long new_JbufState();

    public static final native long new_LogConfig();

    public static final native long new_LogEntry();

    public static final native long new_LogWriter();

    public static final native long new_LossType();

    public static final native long new_MathStat();

    public static final native long new_MediaConfig();

    public static final native long new_MediaEvent();

    public static final native long new_MediaEventData();

    public static final native long new_MediaFmtChangedEvent();

    public static final native long new_MediaFormat();

    public static final native long new_MediaFormatAudio();

    public static final native long new_MediaFormatVector__SWIG_0();

    public static final native long new_MediaFormatVector__SWIG_1(long j2);

    public static final native long new_MediaTransportInfo();

    public static final native long new_OnBuddyEvSubStateParam();

    public static final native long new_OnCallMediaEventParam();

    public static final native long new_OnCallMediaStateParam();

    public static final native long new_OnCallMediaTransportStateParam();

    public static final native long new_OnCallRedirectedParam();

    public static final native long new_OnCallReplaceRequestParam();

    public static final native long new_OnCallReplacedParam();

    public static final native long new_OnCallRxOfferParam();

    public static final native long new_OnCallSdpCreatedParam();

    public static final native long new_OnCallStateParam();

    public static final native long new_OnCallTransferRequestParam();

    public static final native long new_OnCallTransferStatusParam();

    public static final native long new_OnCallTsxStateParam();

    public static final native long new_OnCallTxOfferParam();

    public static final native long new_OnCreateMediaTransportParam();

    public static final native long new_OnCreateMediaTransportSrtpParam();

    public static final native long new_OnDtmfDigitParam();

    public static final native long new_OnIncomingCallParam();

    public static final native long new_OnIncomingSubscribeParam();

    public static final native long new_OnInstantMessageParam();

    public static final native long new_OnInstantMessageStatusParam();

    public static final native long new_OnIpChangeProgressParam();

    public static final native long new_OnMwiInfoParam();

    public static final native long new_OnNatCheckStunServersCompleteParam();

    public static final native long new_OnNatDetectionCompleteParam();

    public static final native long new_OnRegStartedParam();

    public static final native long new_OnRegStateParam();

    public static final native long new_OnSelectAccountParam();

    public static final native long new_OnStreamCreatedParam();

    public static final native long new_OnStreamDestroyedParam();

    public static final native long new_OnTimerParam();

    public static final native long new_OnTransportStateParam();

    public static final native long new_OnTypingIndicationParam();

    public static final native long new_PresNotifyParam();

    public static final native long new_PresenceStatus();

    public static final native long new_RegProgressParam();

    public static final native long new_RtcpSdes();

    public static final native long new_RtcpStat();

    public static final native long new_RtcpStreamStat();

    public static final native long new_RxMsgEvent();

    public static final native long new_SdpSession();

    public static final native long new_SendInstantMessageParam();

    public static final native long new_SendTypingIndicationParam();

    public static final native long new_SipEvent();

    public static final native long new_SipEventBody();

    public static final native long new_SipHeader();

    public static final native long new_SipHeaderVector__SWIG_0();

    public static final native long new_SipHeaderVector__SWIG_1(long j2);

    public static final native long new_SipMediaType();

    public static final native long new_SipMultipartPart();

    public static final native long new_SipMultipartPartVector__SWIG_0();

    public static final native long new_SipMultipartPartVector__SWIG_1(long j2);

    public static final native long new_SipRxData();

    public static final native long new_SipTransaction();

    public static final native long new_SipTxData();

    public static final native long new_SipTxOption();

    public static final native long new_SrtpCrypto();

    public static final native long new_SslCertInfo();

    public static final native long new_SslCertName();

    public static final native long new_StreamInfo();

    public static final native long new_StreamStat();

    public static final native long new_StringVector__SWIG_0();

    public static final native long new_StringVector__SWIG_1(long j2);

    public static final native long new_TimeVal();

    public static final native long new_TimerEvent();

    public static final native long new_TlsConfig();

    public static final native long new_TlsInfo();

    public static final native long new_ToneDesc();

    public static final native long new_ToneDescVector__SWIG_0();

    public static final native long new_ToneDescVector__SWIG_1(long j2);

    public static final native long new_ToneDigit();

    public static final native long new_ToneDigitMapDigit();

    public static final native long new_ToneDigitMapVector__SWIG_0();

    public static final native long new_ToneDigitMapVector__SWIG_1(long j2);

    public static final native long new_ToneDigitVector__SWIG_0();

    public static final native long new_ToneDigitVector__SWIG_1(long j2);

    public static final native long new_ToneGenerator();

    public static final native long new_TransportConfig();

    public static final native long new_TransportInfo();

    public static final native long new_TsxStateEvent();

    public static final native long new_TsxStateEventSrc();

    public static final native long new_TxErrorEvent();

    public static final native long new_TxMsgEvent();

    public static final native long new_UaConfig();

    public static final native long new_UserEvent();

    public static final native long new_Version();

    public static final native long new_pj_qos_params();

    public static final native long new_pjmedia_tone_desc();

    public static final native long new_pjmedia_tone_digit();

    public static final native long new_pjmedia_tone_digit_map();

    public static final native short pj_qos_params_dscp_val_get(long j2, pj_qos_params pj_qos_paramsVar);

    public static final native void pj_qos_params_dscp_val_set(long j2, pj_qos_params pj_qos_paramsVar, short s);

    public static final native short pj_qos_params_flags_get(long j2, pj_qos_params pj_qos_paramsVar);

    public static final native void pj_qos_params_flags_set(long j2, pj_qos_params pj_qos_paramsVar, short s);

    public static final native short pj_qos_params_so_prio_get(long j2, pj_qos_params pj_qos_paramsVar);

    public static final native void pj_qos_params_so_prio_set(long j2, pj_qos_params pj_qos_paramsVar, short s);

    public static final native int pj_qos_params_wmm_prio_get(long j2, pj_qos_params pj_qos_paramsVar);

    public static final native void pj_qos_params_wmm_prio_set(long j2, pj_qos_params pj_qos_paramsVar, int i2);

    public static final native short pjmedia_tone_desc_flags_get(long j2, pjmedia_tone_desc pjmedia_tone_descVar);

    public static final native void pjmedia_tone_desc_flags_set(long j2, pjmedia_tone_desc pjmedia_tone_descVar, short s);

    public static final native short pjmedia_tone_desc_freq1_get(long j2, pjmedia_tone_desc pjmedia_tone_descVar);

    public static final native void pjmedia_tone_desc_freq1_set(long j2, pjmedia_tone_desc pjmedia_tone_descVar, short s);

    public static final native short pjmedia_tone_desc_freq2_get(long j2, pjmedia_tone_desc pjmedia_tone_descVar);

    public static final native void pjmedia_tone_desc_freq2_set(long j2, pjmedia_tone_desc pjmedia_tone_descVar, short s);

    public static final native short pjmedia_tone_desc_off_msec_get(long j2, pjmedia_tone_desc pjmedia_tone_descVar);

    public static final native void pjmedia_tone_desc_off_msec_set(long j2, pjmedia_tone_desc pjmedia_tone_descVar, short s);

    public static final native short pjmedia_tone_desc_on_msec_get(long j2, pjmedia_tone_desc pjmedia_tone_descVar);

    public static final native void pjmedia_tone_desc_on_msec_set(long j2, pjmedia_tone_desc pjmedia_tone_descVar, short s);

    public static final native short pjmedia_tone_desc_volume_get(long j2, pjmedia_tone_desc pjmedia_tone_descVar);

    public static final native void pjmedia_tone_desc_volume_set(long j2, pjmedia_tone_desc pjmedia_tone_descVar, short s);

    public static final native char pjmedia_tone_digit_digit_get(long j2, pjmedia_tone_digit pjmedia_tone_digitVar);

    public static final native void pjmedia_tone_digit_digit_set(long j2, pjmedia_tone_digit pjmedia_tone_digitVar, char c2);

    public static final native long pjmedia_tone_digit_map_count_get(long j2, pjmedia_tone_digit_map pjmedia_tone_digit_mapVar);

    public static final native void pjmedia_tone_digit_map_count_set(long j2, pjmedia_tone_digit_map pjmedia_tone_digit_mapVar, long j3);

    public static final native short pjmedia_tone_digit_off_msec_get(long j2, pjmedia_tone_digit pjmedia_tone_digitVar);

    public static final native void pjmedia_tone_digit_off_msec_set(long j2, pjmedia_tone_digit pjmedia_tone_digitVar, short s);

    public static final native short pjmedia_tone_digit_on_msec_get(long j2, pjmedia_tone_digit pjmedia_tone_digitVar);

    public static final native void pjmedia_tone_digit_on_msec_set(long j2, pjmedia_tone_digit pjmedia_tone_digitVar, short s);

    public static final native short pjmedia_tone_digit_volume_get(long j2, pjmedia_tone_digit pjmedia_tone_digitVar);

    public static final native void pjmedia_tone_digit_volume_set(long j2, pjmedia_tone_digit pjmedia_tone_digitVar, short s);

    private static final native void swig_module_init();
}
