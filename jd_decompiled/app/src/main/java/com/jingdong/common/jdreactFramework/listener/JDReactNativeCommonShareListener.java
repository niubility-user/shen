package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.entity.ShareImageInfo;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.utils.ShareUtil;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JDReactNativeCommonShareListener implements NativeCommonShareListener, JDFlutterCall {
    private static final String COMMON_SHARE_KEY_EVENT_NAME = "eventName";
    private static final String COMMON_SHARE_KEY_ICON_URL = "iconUrl";
    private static final String COMMON_SHARE_KEY_SUMMARY = "summary";
    private static final String COMMON_SHARE_KEY_TITLE = "title";
    private static final String COMMON_SHARE_KEY_URL = "url";
    private static final String CUSTOM_SHARE_KEY_CHANNEL = "channelInfo";
    private static final String CUSTOM_SHARE_KEY_EVENT_NAME = "eventName";
    private static final String CUSTOM_SHARE_KEY_ICON_URL = "iconUrl";
    private static final String CUSTOM_SHARE_KEY_SUMMARY = "summary";
    private static final String CUSTOM_SHARE_KEY_TIMELINE_TITLE = "timelineTitle";
    private static final String CUSTOM_SHARE_KEY_TITLE = "title";
    private static final String CUSTOM_SHARE_KEY_URL = "url";
    private static final String MINI_PROGRAME_SHARE_KEY_MPICONURL = "mpIconUrl";
    private static final String MINI_PROGRAME_SHARE_KEY_MPID = "mpId";
    private static final String MINI_PROGRAME_SHARE_KEY_MPPATH = "mpPath";
    private static final String MINI_PROGRAME_SHARE_KEY_TYPE = "miniProgramType";
    public static final String SHARECHANNEL = "com.jd.jdflutter/share";
    private static final String SHAREINFO_KEY_DIRECT_PATH = "directPath";
    private static final String SHAREINFO_KEY_DIRECT_URL = "directUrl";
    private static final String SHAREINFO_KEY_LOGO_URL = "logoUrl";
    private static final String SHAREINFO_KEY_PRODUCT_DESC = "productDesc";
    private static final String SHAREINFO_KEY_PRODUCT_PATH = "productPath";
    private static final String SHAREINFO_KEY_PRODUCT_TITLE = "productTitle";
    private static final String SHAREINFO_KEY_PRODUCT_URL = "productUrl";
    private static final String SHAREINFO_KEY_SLOGAN = "slogan";
    private static final String SHAREINFP_KEY_KEY_SHARE_JSON = "keyShareJsonStr";
    private static final String SHARE_CONTENT = "content";
    private static final String SHARE_IMG_URL = "shareImgUrl";
    private static final String SHARE_TITLE = "title";
    private static final String SHARE_TYPE = "shareType";
    private static final String SHARE_URL = "shareUrl";
    private static final String TAG = "JDReactNativeCommonShareListener";
    private String[] channelShare = {"Wxfriends", "Wxmoments", "Sinaweibo", "QQfriends", "QQzone", "Moreshare", "CopyURL", "QRCode", ShareUtil.S_JDFamily};

    /* loaded from: classes5.dex */
    private static class ShareCallbackListener implements ShareUtil.CallbackListener {
        private JDCallback mErrorCb;
        private JDCallback mSuccessCb;

        public ShareCallbackListener(JDCallback jDCallback, JDCallback jDCallback2) {
            this.mSuccessCb = jDCallback;
            this.mErrorCb = jDCallback2;
        }

        @Override // com.jingdong.common.utils.ShareUtil.CallbackListener
        public void onCancel() {
            JDCallback jDCallback = this.mSuccessCb;
            if (jDCallback != null) {
                jDCallback.invoke(new Object[0]);
                this.mSuccessCb = null;
            }
        }

        @Override // com.jingdong.common.utils.ShareUtil.CallbackListener
        public void onComplete(Object obj) {
            JDCallback jDCallback = this.mSuccessCb;
            if (jDCallback != null) {
                jDCallback.invoke(new Object[0]);
                this.mSuccessCb = null;
            }
        }

        @Override // com.jingdong.common.utils.ShareUtil.CallbackListener
        public void onError(String str) {
            JDCallback jDCallback = this.mErrorCb;
            if (jDCallback != null) {
                jDCallback.invoke(new Object[0]);
                this.mErrorCb = null;
            }
        }
    }

    private void performMantoShareAction(ShareInfo shareInfo) {
        Intent intent;
        try {
            Activity currentMyActivity = AbstractJDReactInitialHelper.getCurrentMyActivity();
            if (currentMyActivity == null || (intent = currentMyActivity.getIntent()) == null || !intent.getBooleanExtra("fromManto", false)) {
                return;
            }
            String stringExtra = intent.getStringExtra("mantoShareAction");
            if (TextUtils.isEmpty(stringExtra)) {
                return;
            }
            shareInfo.setJdMpTaskAction(stringExtra);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void setKeyShareJSON(ShareInfo shareInfo, HashMap hashMap) {
        if (shareInfo == null || hashMap == null || !hashMap.containsKey(SHAREINFP_KEY_KEY_SHARE_JSON)) {
            return;
        }
        String str = (String) hashMap.get(SHAREINFP_KEY_KEY_SHARE_JSON);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        shareInfo.setKeyShareJsonStr(str);
    }

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, final JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if (str.equals("sendWxShare")) {
            sendWxShare(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCommonShareListener.4
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCommonShareListener.5
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("showShareDialog")) {
            showShareDialog(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCommonShareListener.6
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCommonShareListener.7
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        } else if (str.equals("showCustomShareDialog")) {
            showCustomShareDialog(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCommonShareListener.8
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCommonShareListener.9
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        }
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeCommonShareListener
    public void sendWxShare(HashMap hashMap, final JDCallback jDCallback, final JDCallback jDCallback2) {
        String str;
        String str2;
        String str3;
        if (hashMap != null && jDCallback != null && jDCallback2 != null) {
            try {
                String str4 = hashMap.containsKey("title") ? (String) hashMap.get("title") : "";
                String str5 = hashMap.containsKey("content") ? (String) hashMap.get("content") : "";
                String str6 = hashMap.containsKey("shareUrl") ? (String) hashMap.get("shareUrl") : "";
                String str7 = hashMap.containsKey(SHARE_IMG_URL) ? (String) hashMap.get(SHARE_IMG_URL) : "";
                String str8 = hashMap.containsKey(SHARE_TYPE) ? (String) hashMap.get(SHARE_TYPE) : "";
                String str9 = hashMap.containsKey(MINI_PROGRAME_SHARE_KEY_MPID) ? (String) hashMap.get(MINI_PROGRAME_SHARE_KEY_MPID) : "";
                String str10 = hashMap.containsKey(MINI_PROGRAME_SHARE_KEY_MPPATH) ? (String) hashMap.get(MINI_PROGRAME_SHARE_KEY_MPPATH) : "";
                String str11 = hashMap.containsKey(MINI_PROGRAME_SHARE_KEY_MPICONURL) ? (String) hashMap.get(MINI_PROGRAME_SHARE_KEY_MPICONURL) : "";
                String str12 = hashMap.containsKey("logoUrl") ? (String) hashMap.get("logoUrl") : "";
                String str13 = hashMap.containsKey(SHAREINFO_KEY_SLOGAN) ? (String) hashMap.get(SHAREINFO_KEY_SLOGAN) : "";
                String str14 = hashMap.containsKey(SHAREINFO_KEY_PRODUCT_URL) ? (String) hashMap.get(SHAREINFO_KEY_PRODUCT_URL) : "";
                String str15 = hashMap.containsKey(SHAREINFO_KEY_PRODUCT_PATH) ? (String) hashMap.get(SHAREINFO_KEY_PRODUCT_PATH) : "";
                String str16 = hashMap.containsKey(SHAREINFO_KEY_PRODUCT_TITLE) ? (String) hashMap.get(SHAREINFO_KEY_PRODUCT_TITLE) : "";
                String str17 = hashMap.containsKey(SHAREINFO_KEY_PRODUCT_DESC) ? (String) hashMap.get(SHAREINFO_KEY_PRODUCT_DESC) : "";
                String str18 = hashMap.containsKey(SHAREINFO_KEY_DIRECT_URL) ? (String) hashMap.get(SHAREINFO_KEY_DIRECT_URL) : "";
                String str19 = hashMap.containsKey(SHAREINFO_KEY_DIRECT_PATH) ? (String) hashMap.get(SHAREINFO_KEY_DIRECT_PATH) : "";
                String str20 = hashMap.containsKey(MINI_PROGRAME_SHARE_KEY_TYPE) ? (String) hashMap.get(MINI_PROGRAME_SHARE_KEY_TYPE) : "";
                int i2 = 0;
                try {
                    i2 = Integer.parseInt(str8);
                } catch (Exception e2) {
                    JLog.e(TAG, e2.toString());
                }
                ShareInfo shareInfo = new ShareInfo();
                if (!TextUtils.isEmpty(str4)) {
                    shareInfo.setTitle(str4);
                }
                if (!TextUtils.isEmpty(str5)) {
                    shareInfo.setSummary(str5);
                    shareInfo.setWxcontent(str5);
                    shareInfo.setWxMomentsContent(str5);
                }
                if (!TextUtils.isEmpty(str6)) {
                    shareInfo.setUrl(str6);
                }
                if (TextUtils.isEmpty(str7)) {
                    str = str6;
                } else {
                    str = str6;
                    String str21 = str7;
                    shareInfo.setIconUrl(str21);
                    str7 = str21;
                }
                try {
                    shareInfo.setChannels(this.channelShare[i2]);
                    if (!TextUtils.isEmpty(str9)) {
                        shareInfo.setMpId(str9);
                    }
                    try {
                        if (!TextUtils.isEmpty(str20)) {
                            shareInfo.setMpType(Integer.parseInt(str20));
                        }
                        str3 = str9;
                        str2 = str20;
                    } catch (Exception e3) {
                        str2 = str20;
                        str3 = str9;
                        JLog.e(TAG, "" + e3.getMessage());
                    }
                    if (!TextUtils.isEmpty(str10)) {
                        shareInfo.setMpPath(str10);
                    }
                    if (!TextUtils.isEmpty(str11)) {
                        shareInfo.setMpIconUrl(str11);
                    }
                    ShareImageInfo shareImageInfo = new ShareImageInfo();
                    if (!TextUtils.isEmpty(str12)) {
                        shareImageInfo.logoUrl = str12;
                    }
                    if (!TextUtils.isEmpty(str13)) {
                        shareImageInfo.slogan = str13;
                    }
                    if (!TextUtils.isEmpty(str14)) {
                        shareImageInfo.productUrl = str14;
                    }
                    if (!TextUtils.isEmpty(str15)) {
                        shareImageInfo.productPath = str15;
                    }
                    if (!TextUtils.isEmpty(str16)) {
                        shareImageInfo.productTitle = str16;
                    }
                    if (!TextUtils.isEmpty(str17)) {
                        shareImageInfo.productDesc = str17;
                    }
                    if (!TextUtils.isEmpty(str18)) {
                        shareImageInfo.directUrl = str18;
                    }
                    if (!TextUtils.isEmpty(str19)) {
                        shareImageInfo.directPath = str19;
                    }
                    if (!TextUtils.isEmpty(shareImageInfo.productUrl) || !TextUtils.isEmpty(shareImageInfo.productPath) || !TextUtils.isEmpty(shareImageInfo.directUrl) || !TextUtils.isEmpty(shareImageInfo.directPath)) {
                        shareInfo.setShareImageInfo(shareImageInfo);
                    }
                    setKeyShareJSON(shareInfo, hashMap);
                    performMantoShareAction(shareInfo);
                    JLog.d(TAG, "sendWxShare method. title is : " + str4 + " summary is : " + str5 + " url is : " + str + " iconurl is : " + str7 + " channel is : " + this.channelShare[i2] + " mpId is : " + str3 + " mpPath is : " + str10 + " mpIconUrl is : " + str11 + " miniProgramType " + str2);
                    final BaseActivity baseActivity = (BaseActivity) AbstractJDReactInitialHelper.getCurrentMyActivity();
                    if (baseActivity == null) {
                        return;
                    }
                    ShareUtil.sendShare(baseActivity, shareInfo, new ShareUtil.CallbackListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCommonShareListener.1
                        @Override // com.jingdong.common.utils.ShareUtil.CallbackListener
                        public void onCancel() {
                            JDCallback jDCallback3 = jDCallback;
                            if (jDCallback3 != null) {
                                jDCallback3.invoke("1");
                            }
                            BaseActivity baseActivity2 = baseActivity;
                            if (baseActivity2 != null) {
                                baseActivity2.onShareCancel();
                            }
                        }

                        @Override // com.jingdong.common.utils.ShareUtil.CallbackListener
                        public void onComplete(Object obj) {
                            JDCallback jDCallback3 = jDCallback;
                            if (jDCallback3 != null) {
                                jDCallback3.invoke("0");
                            }
                            BaseActivity baseActivity2 = baseActivity;
                            if (baseActivity2 != null) {
                                baseActivity2.onShareComplete("");
                            }
                        }

                        @Override // com.jingdong.common.utils.ShareUtil.CallbackListener
                        public void onError(String str22) {
                            JDCallback jDCallback3 = jDCallback2;
                            if (jDCallback3 != null) {
                                jDCallback3.invoke(str22);
                            }
                            BaseActivity baseActivity2 = baseActivity;
                            if (baseActivity2 != null) {
                                baseActivity2.onShareError("");
                            }
                        }
                    });
                    return;
                } catch (Exception e4) {
                    e = e4;
                    JLog.e(TAG, e.toString());
                    return;
                }
            } catch (Exception e5) {
                e = e5;
                JLog.e(TAG, e.toString());
                return;
            }
        }
        JLog.e(TAG, "parameters are invalid!!");
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeCommonShareListener
    public void showCustomShareDialog(HashMap hashMap, final JDCallback jDCallback, final JDCallback jDCallback2) {
        if (hashMap != null && jDCallback != null && jDCallback2 != null) {
            try {
                String str = hashMap.containsKey("url") ? (String) hashMap.get("url") : "";
                String str2 = hashMap.containsKey("title") ? (String) hashMap.get("title") : "";
                String str3 = hashMap.containsKey("summary") ? (String) hashMap.get("summary") : "";
                String str4 = hashMap.containsKey("iconUrl") ? (String) hashMap.get("iconUrl") : "";
                String str5 = hashMap.containsKey("eventName") ? (String) hashMap.get("eventName") : "";
                String str6 = hashMap.containsKey(CUSTOM_SHARE_KEY_CHANNEL) ? (String) hashMap.get(CUSTOM_SHARE_KEY_CHANNEL) : "";
                String str7 = hashMap.containsKey(CUSTOM_SHARE_KEY_TIMELINE_TITLE) ? (String) hashMap.get(CUSTOM_SHARE_KEY_TIMELINE_TITLE) : "";
                String str8 = hashMap.containsKey(MINI_PROGRAME_SHARE_KEY_MPID) ? (String) hashMap.get(MINI_PROGRAME_SHARE_KEY_MPID) : "";
                String str9 = hashMap.containsKey(MINI_PROGRAME_SHARE_KEY_MPPATH) ? (String) hashMap.get(MINI_PROGRAME_SHARE_KEY_MPPATH) : "";
                String str10 = hashMap.containsKey(MINI_PROGRAME_SHARE_KEY_MPICONURL) ? (String) hashMap.get(MINI_PROGRAME_SHARE_KEY_MPICONURL) : "";
                String str11 = hashMap.containsKey("logoUrl") ? (String) hashMap.get("logoUrl") : "";
                String str12 = hashMap.containsKey(SHAREINFO_KEY_SLOGAN) ? (String) hashMap.get(SHAREINFO_KEY_SLOGAN) : "";
                String str13 = hashMap.containsKey(SHAREINFO_KEY_PRODUCT_URL) ? (String) hashMap.get(SHAREINFO_KEY_PRODUCT_URL) : "";
                String str14 = hashMap.containsKey(SHAREINFO_KEY_PRODUCT_PATH) ? (String) hashMap.get(SHAREINFO_KEY_PRODUCT_PATH) : "";
                String str15 = hashMap.containsKey(SHAREINFO_KEY_PRODUCT_TITLE) ? (String) hashMap.get(SHAREINFO_KEY_PRODUCT_TITLE) : "";
                String str16 = hashMap.containsKey(SHAREINFO_KEY_PRODUCT_DESC) ? (String) hashMap.get(SHAREINFO_KEY_PRODUCT_DESC) : "";
                String str17 = hashMap.containsKey(SHAREINFO_KEY_DIRECT_URL) ? (String) hashMap.get(SHAREINFO_KEY_DIRECT_URL) : "";
                String str18 = hashMap.containsKey(SHAREINFO_KEY_DIRECT_PATH) ? (String) hashMap.get(SHAREINFO_KEY_DIRECT_PATH) : "";
                String str19 = hashMap.containsKey(MINI_PROGRAME_SHARE_KEY_TYPE) ? (String) hashMap.get(MINI_PROGRAME_SHARE_KEY_TYPE) : "";
                String str20 = TAG;
                String str21 = str14;
                StringBuilder sb = new StringBuilder();
                String str22 = str13;
                sb.append("url = ");
                sb.append(str);
                sb.append(", title = ");
                sb.append(str2);
                sb.append(", summary = ");
                sb.append(str3);
                sb.append(", iconUrl = ");
                sb.append(str4);
                sb.append(", eventName =");
                sb.append(str5);
                sb.append(", channels =");
                sb.append(str6);
                sb.append(" mpId is : ");
                sb.append(str8);
                sb.append(" mpPath is : ");
                sb.append(str9);
                sb.append(" mpIconUrl is : ");
                sb.append(str10);
                sb.append(" timelineTitle is : ");
                sb.append(str7);
                sb.append(" miniProgramType ");
                sb.append(str19);
                JLog.d(str20, sb.toString());
                ShareInfo shareInfo = new ShareInfo(TextUtils.isEmpty(str) ? "" : str, TextUtils.isEmpty(str2) ? "" : str2, TextUtils.isEmpty(str3) ? "" : str3, TextUtils.isEmpty(str4) ? "" : str4, TextUtils.isEmpty(str5) ? "" : str5);
                shareInfo.setChannels(str6);
                if (!TextUtils.isEmpty(str8)) {
                    shareInfo.setMpId(str8);
                }
                if (!TextUtils.isEmpty(str9)) {
                    shareInfo.setMpPath(str9);
                }
                if (!TextUtils.isEmpty(str10)) {
                    shareInfo.setMpIconUrl(str10);
                }
                if (!TextUtils.isEmpty(str7)) {
                    shareInfo.setTitleTimeline(str7);
                }
                ShareImageInfo shareImageInfo = new ShareImageInfo();
                if (!TextUtils.isEmpty(str11)) {
                    shareImageInfo.logoUrl = str11;
                }
                if (!TextUtils.isEmpty(str12)) {
                    shareImageInfo.slogan = str12;
                }
                if (!TextUtils.isEmpty(str22)) {
                    shareImageInfo.productUrl = str22;
                }
                if (!TextUtils.isEmpty(str21)) {
                    shareImageInfo.productPath = str21;
                }
                if (!TextUtils.isEmpty(str15)) {
                    shareImageInfo.productTitle = str15;
                }
                if (!TextUtils.isEmpty(str16)) {
                    shareImageInfo.productDesc = str16;
                }
                if (!TextUtils.isEmpty(str17)) {
                    shareImageInfo.directUrl = str17;
                }
                if (!TextUtils.isEmpty(str18)) {
                    shareImageInfo.directPath = str18;
                }
                try {
                    if (!TextUtils.isEmpty(str19)) {
                        shareInfo.setMpType(Integer.parseInt(str19));
                    }
                } catch (Exception e2) {
                    JLog.e(TAG, "" + e2.getMessage());
                }
                if (!TextUtils.isEmpty(shareImageInfo.productUrl) || !TextUtils.isEmpty(shareImageInfo.productPath) || !TextUtils.isEmpty(shareImageInfo.directUrl) || !TextUtils.isEmpty(shareImageInfo.directPath)) {
                    shareInfo.setShareImageInfo(shareImageInfo);
                }
                setKeyShareJSON(shareInfo, hashMap);
                try {
                    performMantoShareAction(shareInfo);
                    final BaseActivity baseActivity = (BaseActivity) AbstractJDReactInitialHelper.getCurrentMyActivity();
                    ShareUtil.CallbackListener callbackListener = new ShareUtil.CallbackListener() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCommonShareListener.3
                        @Override // com.jingdong.common.utils.ShareUtil.CallbackListener
                        public void onCancel() {
                            JDCallback jDCallback3 = jDCallback;
                            if (jDCallback3 != null) {
                                jDCallback3.invoke(new Object[0]);
                            }
                            BaseActivity baseActivity2 = baseActivity;
                            if (baseActivity2 != null) {
                                baseActivity2.onShareCancel();
                            }
                        }

                        @Override // com.jingdong.common.utils.ShareUtil.CallbackListener
                        public void onComplete(Object obj) {
                            JDCallback jDCallback3 = jDCallback;
                            if (jDCallback3 != null) {
                                jDCallback3.invoke(new Object[0]);
                            }
                            BaseActivity baseActivity2 = baseActivity;
                            if (baseActivity2 != null) {
                                baseActivity2.onShareComplete("");
                            }
                        }

                        @Override // com.jingdong.common.utils.ShareUtil.CallbackListener
                        public void onError(String str23) {
                            JDCallback jDCallback3 = jDCallback2;
                            if (jDCallback3 != null) {
                                jDCallback3.invoke(new Object[0]);
                            }
                            BaseActivity baseActivity2 = baseActivity;
                            if (baseActivity2 != null) {
                                baseActivity2.onShareError("");
                            }
                        }
                    };
                    if (baseActivity != null) {
                        ShareUtil.showShareDialog(baseActivity, shareInfo, callbackListener);
                        return;
                    }
                    return;
                } catch (Exception e3) {
                    e = e3;
                    e.printStackTrace();
                    return;
                }
            } catch (Exception e4) {
                e = e4;
                e.printStackTrace();
                return;
            }
        }
        JLog.e(TAG, "parameters are invalid!!");
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeCommonShareListener
    public void showShareDialog(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        if (hashMap != null && jDCallback != null && jDCallback2 != null) {
            try {
                String str = hashMap.containsKey("url") ? (String) hashMap.get("url") : "";
                String str2 = hashMap.containsKey("title") ? (String) hashMap.get("title") : "";
                String str3 = hashMap.containsKey("summary") ? (String) hashMap.get("summary") : "";
                String str4 = hashMap.containsKey("iconUrl") ? (String) hashMap.get("iconUrl") : "";
                String str5 = hashMap.containsKey("eventName") ? (String) hashMap.get("eventName") : "";
                String str6 = hashMap.containsKey(MINI_PROGRAME_SHARE_KEY_MPID) ? (String) hashMap.get(MINI_PROGRAME_SHARE_KEY_MPID) : "";
                String str7 = hashMap.containsKey(MINI_PROGRAME_SHARE_KEY_MPPATH) ? (String) hashMap.get(MINI_PROGRAME_SHARE_KEY_MPPATH) : "";
                String str8 = hashMap.containsKey(MINI_PROGRAME_SHARE_KEY_MPICONURL) ? (String) hashMap.get(MINI_PROGRAME_SHARE_KEY_MPICONURL) : "";
                String str9 = hashMap.containsKey("logoUrl") ? (String) hashMap.get("logoUrl") : "";
                String str10 = hashMap.containsKey(SHAREINFO_KEY_SLOGAN) ? (String) hashMap.get(SHAREINFO_KEY_SLOGAN) : "";
                String str11 = hashMap.containsKey(SHAREINFO_KEY_PRODUCT_URL) ? (String) hashMap.get(SHAREINFO_KEY_PRODUCT_URL) : "";
                String str12 = hashMap.containsKey(SHAREINFO_KEY_PRODUCT_PATH) ? (String) hashMap.get(SHAREINFO_KEY_PRODUCT_PATH) : "";
                String str13 = hashMap.containsKey(SHAREINFO_KEY_PRODUCT_TITLE) ? (String) hashMap.get(SHAREINFO_KEY_PRODUCT_TITLE) : "";
                String str14 = hashMap.containsKey(SHAREINFO_KEY_PRODUCT_DESC) ? (String) hashMap.get(SHAREINFO_KEY_PRODUCT_DESC) : "";
                String str15 = hashMap.containsKey(SHAREINFO_KEY_DIRECT_URL) ? (String) hashMap.get(SHAREINFO_KEY_DIRECT_URL) : "";
                String str16 = hashMap.containsKey(SHAREINFO_KEY_DIRECT_PATH) ? (String) hashMap.get(SHAREINFO_KEY_DIRECT_PATH) : "";
                String str17 = hashMap.containsKey(MINI_PROGRAME_SHARE_KEY_TYPE) ? (String) hashMap.get(MINI_PROGRAME_SHARE_KEY_TYPE) : "";
                String str18 = TAG;
                String str19 = str13;
                StringBuilder sb = new StringBuilder();
                String str20 = str12;
                sb.append("url = ");
                sb.append(str);
                sb.append(", title = ");
                sb.append(str2);
                sb.append(", summary = ");
                sb.append(str3);
                sb.append(", iconUrl = ");
                sb.append(str4);
                sb.append(", eventName =");
                sb.append(str5);
                sb.append(" mpId is : ");
                sb.append(str6);
                sb.append(" mpPath is : ");
                sb.append(str7);
                sb.append(" mpIconUrl is : ");
                sb.append(str8);
                sb.append(" miniProgramType ");
                sb.append(str17);
                JLog.d(str18, sb.toString());
                ShareInfo shareInfo = new ShareInfo(TextUtils.isEmpty(str) ? "" : str, TextUtils.isEmpty(str2) ? "" : str2, TextUtils.isEmpty(str3) ? "" : str3, TextUtils.isEmpty(str4) ? "" : str4, TextUtils.isEmpty(str5) ? "" : str5);
                if (!TextUtils.isEmpty(str6)) {
                    shareInfo.setMpId(str6);
                }
                try {
                    if (!TextUtils.isEmpty(str17)) {
                        shareInfo.setMpType(Integer.parseInt(str17));
                    }
                } catch (Exception e2) {
                    JLog.e(TAG, "" + e2.getMessage());
                }
                if (!TextUtils.isEmpty(str7)) {
                    shareInfo.setMpPath(str7);
                }
                if (!TextUtils.isEmpty(str8)) {
                    shareInfo.setMpIconUrl(str8);
                }
                ShareImageInfo shareImageInfo = new ShareImageInfo();
                if (!TextUtils.isEmpty(str9)) {
                    shareImageInfo.logoUrl = str9;
                }
                if (!TextUtils.isEmpty(str10)) {
                    shareImageInfo.slogan = str10;
                }
                if (!TextUtils.isEmpty(str11)) {
                    shareImageInfo.productUrl = str11;
                }
                if (!TextUtils.isEmpty(str20)) {
                    shareImageInfo.productPath = str20;
                }
                if (!TextUtils.isEmpty(str19)) {
                    shareImageInfo.productTitle = str19;
                }
                if (!TextUtils.isEmpty(str14)) {
                    shareImageInfo.productDesc = str14;
                }
                if (!TextUtils.isEmpty(str15)) {
                    shareImageInfo.directUrl = str15;
                }
                if (!TextUtils.isEmpty(str16)) {
                    shareImageInfo.directPath = str16;
                }
                if (!TextUtils.isEmpty(shareImageInfo.productUrl) || !TextUtils.isEmpty(shareImageInfo.productPath) || !TextUtils.isEmpty(shareImageInfo.directUrl) || !TextUtils.isEmpty(shareImageInfo.directPath)) {
                    shareInfo.setShareImageInfo(shareImageInfo);
                }
                setKeyShareJSON(shareInfo, hashMap);
                try {
                    performMantoShareAction(shareInfo);
                    final BaseActivity baseActivity = (BaseActivity) AbstractJDReactInitialHelper.getCurrentMyActivity();
                    ShareCallbackListener shareCallbackListener = new ShareCallbackListener(jDCallback, jDCallback2) { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeCommonShareListener.2
                        @Override // com.jingdong.common.jdreactFramework.listener.JDReactNativeCommonShareListener.ShareCallbackListener, com.jingdong.common.utils.ShareUtil.CallbackListener
                        public void onCancel() {
                            super.onCancel();
                            BaseActivity baseActivity2 = baseActivity;
                            if (baseActivity2 != null) {
                                baseActivity2.onShareCancel();
                            }
                        }

                        @Override // com.jingdong.common.jdreactFramework.listener.JDReactNativeCommonShareListener.ShareCallbackListener, com.jingdong.common.utils.ShareUtil.CallbackListener
                        public void onComplete(Object obj) {
                            super.onComplete(obj);
                            BaseActivity baseActivity2 = baseActivity;
                            if (baseActivity2 != null) {
                                baseActivity2.onShareComplete("");
                            }
                        }

                        @Override // com.jingdong.common.jdreactFramework.listener.JDReactNativeCommonShareListener.ShareCallbackListener, com.jingdong.common.utils.ShareUtil.CallbackListener
                        public void onError(String str21) {
                            super.onError(str21);
                            BaseActivity baseActivity2 = baseActivity;
                            if (baseActivity2 != null) {
                                baseActivity2.onShareError("");
                            }
                        }
                    };
                    if (baseActivity != null) {
                        ShareUtil.showShareDialog(baseActivity, shareInfo, shareCallbackListener);
                        return;
                    }
                    return;
                } catch (Exception e3) {
                    e = e3;
                    JLog.e(TAG, e.toString());
                    return;
                }
            } catch (Exception e4) {
                e = e4;
                JLog.e(TAG, e.toString());
                return;
            }
        }
        JLog.e(TAG, "parameters are invalid!!");
    }
}
