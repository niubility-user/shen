package com.jingdong.common.web;

import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.Keep;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.libs.hybrid.HybridOfflineLoader;
import com.jd.libs.hybrid.offlineload.IPreDownloadParamGetter;
import com.jd.libs.hybrid.offlineload.entity.OfflineFiles;
import com.jd.libs.xconsole.XLog;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.managers.WebWhiteScreenHolder;
import com.jingdong.common.web.util.ChannelPrivacyConfirmUtil;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.net.URLDecoder;
import java.util.concurrent.ConcurrentHashMap;

@Keep
/* loaded from: classes6.dex */
public class WebOfflineLoaderManager {
    private static final String TAG = "WebOfflineLoaderManager";
    private static ConcurrentHashMap<String, HybridOfflineLoader> offlineLoaders;

    public static String createOfflineLoader(String str) {
        return createOfflineLoader(str, null);
    }

    public static Bundle createOfflineLoaderForBundle(String str, Bundle bundle) {
        return createOfflineLoaderForBundle(str, bundle, null);
    }

    public static void deleteOfflineLoader(String str) {
        ConcurrentHashMap<String, HybridOfflineLoader> concurrentHashMap;
        HybridOfflineLoader remove;
        if (TextUtils.isEmpty(str) || (concurrentHashMap = offlineLoaders) == null || concurrentHashMap.isEmpty() || (remove = offlineLoaders.remove(str)) == null) {
            return;
        }
        remove.destroy();
    }

    public static HybridOfflineLoader fetchOfflineLoader(String str) {
        ConcurrentHashMap<String, HybridOfflineLoader> concurrentHashMap;
        if (TextUtils.isEmpty(str) || (concurrentHashMap = offlineLoaders) == null || concurrentHashMap.isEmpty()) {
            return null;
        }
        return offlineLoaders.get(str);
    }

    public static String createOfflineLoader(String str, final IPreDownloadParamGetter.PreDownloadParamGetter preDownloadParamGetter) {
        if (!WebHybridUtils.hybridEnable || TextUtils.isEmpty(str) || WebHybridUtils.degradeOfflineFromQuery(str)) {
            return null;
        }
        if (OKLog.D) {
            OKLog.d("JDHybrid", "create OfflineLoader.");
        }
        HybridOfflineLoader loader = HybridOfflineLoader.getLoader(str, new IPreDownloadParamGetter.PreDownloadParamGetter() { // from class: com.jingdong.common.web.WebOfflineLoaderManager.1
            private static final int STATE_DEFAULT = 0;
            private static final int STATE_DYNAMIC_LOGIN_FAIL = 4;
            private static final int STATE_DYNAMIC_LOGIN_SUCCESS = 3;
            private static final int STATE_DYNAMIC_LOGOUT = 2;

            private int getState(OfflineFiles offlineFiles) {
                if (offlineFiles == null) {
                    return 0;
                }
                if (LoginUserBase.hasLogin()) {
                    return WebLoginHelper.isPreSyncH5LoginSuccess() ? 3 : 4;
                }
                return 2;
            }

            @Override // com.jd.libs.hybrid.offlineload.IPreDownloadParamGetter.PreDownloadParamGetter, com.jd.libs.hybrid.offlineload.IPreDownloadParamGetter
            public String getDownloadUrl(OfflineFiles offlineFiles, String str2) {
                if (!TextUtils.isEmpty(str2) && str2.contains(WebWhiteScreenHolder.X_RENDER)) {
                    if (OKLog.D) {
                        XLog.d(WebOfflineLoaderManager.TAG, null, "\u5546\u57ceApp:\u9884\u6e32\u67d3\u573a\u666f\uff0c\u4e0d\u8fdb\u884c\u9884\u4e0b\u8f7dhtml\u6587\u4ef6", "jdhybrid-dev");
                    }
                    return null;
                } else if (!SwitchQueryFetcher.getSwitchBooleanValue("hybridPreFetchHtml", true)) {
                    if (OKLog.D) {
                        XLog.d(WebOfflineLoaderManager.TAG, null, "\u5546\u57ceApp:\u9884\u4e0b\u8f7dhtml\u7070\u5ea6\u5f00\u5173[\u5173\u95ed]", "jdhybrid-dev");
                    }
                    return null;
                } else if (ChannelPrivacyConfirmUtil.checkIsNeedPrivacyConfirmWithoutDialog(str2)) {
                    if (OKLog.D) {
                        XLog.d(WebOfflineLoaderManager.TAG, null, "\u5546\u57ceApp:\u6b64url\u9700\u8981\u9690\u79c1\u5f39\u7a97\uff0c\u4e0d\u8fdb\u884c\u9884\u4e0b\u8f7dhtml\u6587\u4ef6", "jdhybrid-dev");
                    }
                    return null;
                } else {
                    int state = getState(offlineFiles);
                    if (state == 0) {
                        if (OKLog.D) {
                            XLog.d(WebOfflineLoaderManager.TAG, null, "\u5546\u57ceApp:\u672a\u77e5\u72b6\u6001\uff0c\u4e0d\u8fdb\u884c\u9884\u4e0b\u8f7dhtml\u6587\u4ef6", "jdhybrid-dev");
                        }
                        return null;
                    } else if (state == 4) {
                        if (OKLog.D) {
                            XLog.d(WebOfflineLoaderManager.TAG, null, "\u5546\u57ceApp:App\u5df2\u767b\u5f55\u5374\u672a\u6210\u529f\u9884\u6253\u901a\u767b\u5f55\u6001\uff0c\u4e0d\u8fdb\u884c\u9884\u4e0b\u8f7dhtml\u6587\u4ef6", "jdhybrid-dev");
                        }
                        return null;
                    } else {
                        if (state == 3) {
                            if (OKLog.D) {
                                XLog.d(WebOfflineLoaderManager.TAG, null, "\u5546\u57ceApp:\u53ef\u9884\u4e0b\u8f7dhtml\u6587\u4ef6(app\u5df2\u767b\u5f55)", "jdhybrid-dev");
                            }
                        } else if (state == 2 && OKLog.D) {
                            XLog.d(WebOfflineLoaderManager.TAG, null, "\u5546\u57ceApp:\u53ef\u9884\u4e0b\u8f7dhtml\u6587\u4ef6(app\u672a\u767b\u5f55)", "jdhybrid-dev");
                        }
                        IPreDownloadParamGetter.PreDownloadParamGetter preDownloadParamGetter2 = preDownloadParamGetter;
                        if (preDownloadParamGetter2 != null) {
                            str2 = preDownloadParamGetter2.getDownloadUrl(offlineFiles, str2);
                        }
                        return WebHybridUtils.getMergedUrl(str2);
                    }
                }
            }
        });
        if (loader == null) {
            return null;
        }
        if (offlineLoaders == null) {
            offlineLoaders = new ConcurrentHashMap<>(5);
        }
        String valueOf = String.valueOf(SystemClock.elapsedRealtime());
        offlineLoaders.put(valueOf, loader);
        return valueOf;
    }

    public static Bundle createOfflineLoaderForBundle(String str, Bundle bundle, IPreDownloadParamGetter.PreDownloadParamGetter preDownloadParamGetter) {
        String createOfflineLoader;
        if (!TextUtils.isEmpty(str) && WebHybridUtils.hybridEnable && (createOfflineLoader = createOfflineLoader(str, preDownloadParamGetter)) != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean(MBaseKeyNames.KEY_USE_HYBRID, true);
            bundle.putString(MBaseKeyNames.KEY_OFFLINE_ID, createOfflineLoader);
        }
        return bundle;
    }

    public static Bundle createOfflineLoaderForBundle(Bundle bundle) {
        return createOfflineLoaderForBundle(bundle, (IPreDownloadParamGetter.PreDownloadParamGetter) null);
    }

    public static Bundle createOfflineLoaderForBundle(Bundle bundle, IPreDownloadParamGetter.PreDownloadParamGetter preDownloadParamGetter) {
        URLParamMap map;
        if (bundle != null && WebHybridUtils.hybridEnable) {
            String string = bundle.getString("url");
            if (TextUtils.isEmpty(string)) {
                Serializable serializable = bundle.getSerializable("urlParamMap");
                if ((serializable instanceof SerializableContainer) && (map = ((SerializableContainer) serializable).getMap()) != null) {
                    try {
                        string = URLDecoder.decode(map.get((Object) RemoteMessageConst.TO), "utf-8");
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        string = null;
                    }
                }
            }
            String createOfflineLoader = createOfflineLoader(string, preDownloadParamGetter);
            if (createOfflineLoader != null) {
                bundle.putBoolean(MBaseKeyNames.KEY_USE_HYBRID, true);
                bundle.putString(MBaseKeyNames.KEY_OFFLINE_ID, createOfflineLoader);
            }
        }
        return bundle;
    }
}
