package com.jd.libs.hybrid.offlineload.loader;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.Keep;
import com.google.common.net.HttpHeaders;
import com.jd.libs.hybrid.base.GlobalParamProvider;
import com.jd.libs.hybrid.base.HybridBase;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.base.util.StringUtils;
import com.jd.libs.hybrid.offlineload.entity.OfflineFiles;
import com.jd.libs.hybrid.offlineload.utils.FileUtils;
import com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils;
import com.jd.libs.hybrid.offlineload.utils.OfflineFileHelper;
import com.jd.libs.xwin.http.StreamRequest;
import com.jd.libs.xwin.http.a;
import com.jingdong.common.web.managers.WebPerfManager;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes16.dex */
public class HtmlLoader {
    @Keep
    public static void preloadHtmlFile(final String str, final OfflineFiles offlineFiles) {
        if (TextUtils.isEmpty(str) || HybridSettings.getAppContext() == null || offlineFiles == null) {
            return;
        }
        if (offlineFiles.isCanPreloadHtml()) {
            String setting = HybridBase.getInstance().getSetting("userAgent");
            String str2 = null;
            if (TextUtils.isEmpty(setting)) {
                GlobalParamProvider.IGlobalParamProvider iGlobalParamProvider = GlobalParamProvider.sGlobalParamProvider;
                setting = iGlobalParamProvider != null ? iGlobalParamProvider.getUserAgent(str) : null;
            }
            final String str3 = setting;
            final String cookieString = HybridBase.getInstance().getCookieString(str);
            Log.d("HtmlLoader", "Preload html is enable, try to download html = " + str + ", ua = " + str3 + ", cookie = " + cookieString);
            StringBuilder sb = new StringBuilder();
            sb.append(OfflineFileHelper.HYBRID_OFFLINE_FILE_TEMP_DIR);
            sb.append(File.separator);
            sb.append(System.currentTimeMillis());
            String sb2 = sb.toString();
            final String dir = FileUtils.getDir(HybridSettings.getAppContext(), sb2);
            if (TextUtils.isEmpty(dir)) {
                Log.e("HtmlLoader", "Cannot pre-download html cause the save path is null!");
                OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_CODE, "preloadHtml", (String) null, str, "Cannot pre-download html cause the save path is null!");
                return;
            } else if (TextUtils.isEmpty(str3)) {
                if (Log.isDebug()) {
                    Log.xLogEForDev("HtmlLoader", "\u5f00\u542f\u4e86\u9884\u4e0b\u8f7dhtml\u529f\u80fd\uff0c\u4f46\u672a\u83b7\u53d6\u5230UserAgent\u4fe1\u606f\uff0c\u65e0\u6cd5\u9884\u4e0b\u8f7d\u3002");
                }
                Log.e("HtmlLoader", "Cannot pre-download html cause ua is null!");
                OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_CODE, "preloadHtml-ua", offlineFiles.getAppId(), str, "Cannot pre-download html cause ua is null!");
                return;
            } else {
                a.InterfaceC0173a interfaceC0173a = new a.InterfaceC0173a() { // from class: com.jd.libs.hybrid.offlineload.loader.HtmlLoader.1
                    @Override // com.jd.libs.xwin.http.a.InterfaceC0173a
                    public void onError(int i2, Map<String, List<String>> map, String str4) {
                        Log.d("Download html error, code " + i2 + "  path:" + str4);
                        if (Log.isDebug()) {
                            Log.xLogE("HtmlLoader", "\u9879\u76ee(id:" + offlineFiles.getAppId() + ")\u7684HTML\u9884\u4e0b\u8f7d\u5931\u8d25\uff0c\u539f\u56e0\uff1ahttp code=" + i2 + ", msg=" + str4);
                        }
                        offlineFiles.onPreloadEnd(false, null);
                        String str5 = "html\u9884\u4e0b\u8f7d\u5931\u8d25, code=" + i2 + ", msg=" + str4;
                        if (i2 == 400 || i2 == 431) {
                            try {
                                str5 = str5 + String.format(Locale.getDefault(), ", UA Size=%d, Cookie Size=%d, UA=%s, Cookie=%s", Integer.valueOf(StringUtils.getByteSize(str3)), Integer.valueOf(StringUtils.getByteSize(cookieString)), str3, cookieString);
                            } catch (Throwable unused) {
                            }
                        }
                        OfflineExceptionUtils.reportDownloadError(i2, OfflineExceptionUtils.ERR_MSG_NET, "preloadHtml-onError", offlineFiles.getAppId(), str, str5);
                    }

                    @Override // com.jd.libs.xwin.http.a.InterfaceC0173a
                    public void onProgress(int i2) {
                    }

                    @Override // com.jd.libs.xwin.http.a.InterfaceC0173a
                    public void onStart() {
                        com.jd.libs.xdog.b.k("prefetch", WebPerfManager.HTML_PRE_DOWNLOAD_START, String.valueOf(System.currentTimeMillis()));
                        offlineFiles.onPreloadHtmlUrl(str, false);
                        Log.d(String.format("Start to download html (%s) into dir (%s)", str, dir));
                        if (Log.isDebug()) {
                            Log.xLogDForDev("HtmlLoader", "Html\u9884\u4e0b\u8f7d\u5f00\u59cb\uff0c\u4e0b\u8f7dUrl: " + str + ", cookie= " + cookieString);
                        }
                    }

                    @Override // com.jd.libs.xwin.http.a.InterfaceC0173a
                    public void onSusses(int i2, Map<String, List<String>> map, String str4) {
                        List<String> list;
                        Log.d("Download html success, code " + i2 + "  path:" + str4);
                        if (i2 == 200) {
                            com.jd.libs.xdog.b.k("prefetch", WebPerfManager.HTML_PRE_DOWNLOAD_END, String.valueOf(System.currentTimeMillis()));
                            if (Log.isDebug()) {
                                Log.xLogD("HtmlLoader", "\u9879\u76ee(id:" + offlineFiles.getAppId() + ")\u7684HTML\u9884\u4e0b\u8f7d\u6210\u529f\u3002");
                            }
                            if (map != null && (list = map.get(HttpHeaders.SET_COOKIE)) != null && !list.isEmpty()) {
                                Log.d("HtmlLoader", "Set-Cookie value: " + list.toString());
                                HybridBase.getInstance().saveCookieString(str, list);
                            }
                            offlineFiles.onPreloadEnd(true, str4);
                            return;
                        }
                        if (Log.isDebug()) {
                            Log.xLogE("HtmlLoader", "\u9879\u76ee(id:" + offlineFiles.getAppId() + ")\u7684HTML\u9884\u4e0b\u8f7d\u5931\u8d25\uff0c\u539f\u56e0\uff1ahttp code=" + i2);
                        }
                        offlineFiles.onPreloadEnd(false, null);
                        String str5 = "html\u9884\u4e0b\u8f7d\u5931\u8d25, code=" + i2;
                        if (i2 == 400 || i2 == 431) {
                            try {
                                str5 = str5 + String.format(Locale.getDefault(), ", UA Size=%d, Cookie Size=%d, UA=%s, Cookie=%s", Integer.valueOf(StringUtils.getByteSize(str3)), Integer.valueOf(StringUtils.getByteSize(cookieString)), str3, cookieString);
                            } catch (Throwable unused) {
                            }
                        }
                        OfflineExceptionUtils.reportDownloadError(i2, OfflineExceptionUtils.ERR_MSG_NET, "preloadHtml-onError", offlineFiles.getAppId(), str, str5);
                    }
                };
                try {
                    str2 = Uri.parse(str).getLastPathSegment();
                } catch (Exception e2) {
                    Log.e("HtmlLoader", e2);
                }
                String randomFileName = TextUtils.isEmpty(str2) ? FileUtils.getRandomFileName() : str2;
                HashMap<String, String> hashMap = new HashMap<>(1);
                hashMap.put("Request-From", "jdhybrid-htmlDownload");
                if (Log.isDebug()) {
                    Log.xLogD("HtmlLoader", "\u9879\u76ee(id:" + offlineFiles.getAppId() + ")\u5f00\u542f\u4e86HTML\u9884\u4e0b\u8f7d\u529f\u80fd\uff0c\u5f00\u59cb\u4e0b\u8f7d\u3002");
                }
                if ("1".equals(HybridBase.getInstance().getSetting(HybridBase.SWITCH_DOWNLOAD_ADAPTER))) {
                    HybridBase.getInstance().downloadFile(str, null, hashMap, true, true, sb2, randomFileName, interfaceC0173a);
                    return;
                }
                com.jd.libs.xwin.http.a aVar = new com.jd.libs.xwin.http.a(str);
                aVar.c(dir + File.separator + randomFileName);
                aVar.setUserAgent(str3);
                if (!TextUtils.isEmpty(cookieString)) {
                    aVar.setCookies(cookieString);
                }
                aVar.setHeader(hashMap);
                aVar.b(interfaceC0173a);
                com.jd.libs.xwin.http.c.b(aVar, true);
                return;
            }
        }
        Log.d("HtmlLoader", "Preload html is disable.");
    }

    @Keep
    public static void preloadHtmlStream(final String str, final OfflineFiles offlineFiles) {
        if (TextUtils.isEmpty(str) || HybridSettings.getAppContext() == null || offlineFiles == null) {
            return;
        }
        if (offlineFiles.isCanPreloadHtml()) {
            String setting = HybridBase.getInstance().getSetting("userAgent");
            if (TextUtils.isEmpty(setting)) {
                GlobalParamProvider.IGlobalParamProvider iGlobalParamProvider = GlobalParamProvider.sGlobalParamProvider;
                setting = iGlobalParamProvider != null ? iGlobalParamProvider.getUserAgent(str) : null;
            }
            final String cookieString = HybridBase.getInstance().getCookieString(str);
            Log.d("HtmlLoader", "Preload html is enable, try to download html = " + str + ", ua = " + setting + ", cookie = " + cookieString);
            if (TextUtils.isEmpty(setting)) {
                if (Log.isDebug()) {
                    Log.xLogEForDev("HtmlLoader", "\u5f00\u542f\u4e86\u9884\u4e0b\u8f7dhtml\u529f\u80fd\uff0c\u4f46\u672a\u83b7\u53d6\u5230UserAgent\u4fe1\u606f\uff0c\u65e0\u6cd5\u9884\u4e0b\u8f7d\u3002");
                }
                Log.e("HtmlLoader", "Cannot pre-download html cause ua is null!");
                OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_CODE, "preloadHtml-ua", offlineFiles.getAppId(), str, "Cannot pre-download html cause ua is null!");
                return;
            }
            final String str2 = setting;
            StreamRequest.StreamListener streamListener = new StreamRequest.StreamListener() { // from class: com.jd.libs.hybrid.offlineload.loader.HtmlLoader.2
                @Override // com.jd.libs.xwin.http.StreamRequest.StreamListener
                public void onConnect(int i2, Map<String, List<String>> map, InputStream inputStream) {
                    List<String> list;
                    Log.d("Connected html, code " + i2 + ", start to pre-read.");
                    if (i2 == 200) {
                        com.jd.libs.xdog.b.k("prefetch", WebPerfManager.HTML_PRE_DOWNLOAD_END, String.valueOf(System.currentTimeMillis()));
                        if (Log.isDebug()) {
                            Log.xLogD("HtmlLoader", "\u9879\u76ee(id:" + offlineFiles.getAppId() + ")\u7684HTML\u9884\u4e0b\u8f7d\u8fde\u63a5\u6210\u529f\uff0c\u5f00\u59cb\u9884\u8bfb\u3002");
                        }
                        if (map != null && (list = map.get(HttpHeaders.SET_COOKIE)) != null && !list.isEmpty()) {
                            Log.d("HtmlLoader", "Set-Cookie value: " + list.toString());
                            HybridBase.getInstance().saveCookieString(str, list);
                        }
                        offlineFiles.onPreloadConnect(true, inputStream);
                        return;
                    }
                    if (Log.isDebug()) {
                        Log.xLogE("HtmlLoader", "\u9879\u76ee(id:" + offlineFiles.getAppId() + ")\u7684HTML\u9884\u4e0b\u8f7d\u5931\u8d25\uff0c\u539f\u56e0\uff1ahttp code=" + i2);
                    }
                    offlineFiles.onPreloadConnect(false, null);
                    String str3 = "html\u9884\u4e0b\u8f7d\u5931\u8d25, code=" + i2;
                    if (i2 == 400 || i2 == 431) {
                        try {
                            str3 = str3 + String.format(Locale.getDefault(), ", UA Size=%d, Cookie Size=%d, UA=%s, Cookie=%s", Integer.valueOf(StringUtils.getByteSize(str2)), Integer.valueOf(StringUtils.getByteSize(cookieString)), str2, cookieString);
                        } catch (Throwable unused) {
                        }
                    }
                    OfflineExceptionUtils.reportDownloadError(i2, OfflineExceptionUtils.ERR_MSG_NET, "preloadHtmlStream-onConnect", offlineFiles.getAppId(), str, str3);
                }

                @Override // com.jd.libs.xwin.http.StreamRequest.StreamListener
                public void onError(int i2, Map<String, List<String>> map, String str3) {
                    Log.d("Download html error, code " + i2 + "  path:" + str3);
                    if (Log.isDebug()) {
                        Log.xLogE("HtmlLoader", "\u9879\u76ee(id:" + offlineFiles.getAppId() + ")\u7684HTML\u9884\u4e0b\u8f7d\u5931\u8d25\uff0c\u539f\u56e0\uff1ahttp code=" + i2 + ", msg=" + str3);
                    }
                    offlineFiles.onPreloadConnect(false, null);
                    String str4 = "html\u9884\u4e0b\u8f7d\u5931\u8d25, code=" + i2 + ", msg=" + str3;
                    if (i2 == 400 || i2 == 431) {
                        try {
                            str4 = str4 + String.format(Locale.getDefault(), ", UA Size=%d, Cookie Size=%d, UA=%s, Cookie=%s", Integer.valueOf(StringUtils.getByteSize(str2)), Integer.valueOf(StringUtils.getByteSize(cookieString)), str2, cookieString);
                        } catch (Throwable unused) {
                        }
                    }
                    OfflineExceptionUtils.reportDownloadError(i2, OfflineExceptionUtils.ERR_MSG_NET, "preloadHtmlStream-onError", offlineFiles.getAppId(), str, str4);
                }

                @Override // com.jd.libs.xwin.http.StreamRequest.StreamListener
                public void onStart() {
                    com.jd.libs.xdog.b.k("prefetch", WebPerfManager.HTML_PRE_DOWNLOAD_START, String.valueOf(System.currentTimeMillis()));
                    offlineFiles.onPreloadHtmlUrl(str, true);
                    Log.d(String.format("Start to download html stream (%s)", str));
                    if (Log.isDebug()) {
                        Log.xLogDForDev("HtmlLoader", "Html\u9884\u4e0b\u8f7d\u5f00\u59cb\uff0c\u4e0b\u8f7dUrl: " + str + ", cookie= " + cookieString);
                    }
                }
            };
            HashMap<String, String> hashMap = new HashMap<>(1);
            hashMap.put("Request-From", "jdhybrid-htmlDownload");
            if (Log.isDebug()) {
                Log.xLogD("HtmlLoader", "\u9879\u76ee(id:" + offlineFiles.getAppId() + ")\u5f00\u542f\u4e86HTML\u9884\u4e0b\u8f7d\u529f\u80fd\uff0c\u5f00\u59cb\u4e0b\u8f7d\u3002");
            }
            if ("1".equals(HybridBase.getInstance().getSetting(HybridBase.SWITCH_DOWNLOAD_ADAPTER))) {
                HybridBase.getInstance().downloadStream(str, null, hashMap, true, true, streamListener);
                return;
            }
            StreamRequest streamRequest = new StreamRequest(str);
            streamRequest.setUserAgent(setting);
            if (!TextUtils.isEmpty(cookieString)) {
                streamRequest.setCookies(cookieString);
            }
            streamRequest.setHeader(hashMap);
            streamRequest.setListener(streamListener);
            com.jd.libs.xwin.http.c.b(streamRequest, true);
            return;
        }
        Log.d("HtmlLoader", "Preload html is disable.");
    }
}
