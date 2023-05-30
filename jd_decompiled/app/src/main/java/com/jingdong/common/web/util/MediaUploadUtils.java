package com.jingdong.common.web.util;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jd.jdsdk.security.DesCbcCrypto;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.unification.uniutil.CRCUtils;
import com.jingdong.common.utils.DeviceInfoUtil;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.common.utils.security.JDKeyStore;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.util.WebFileUploader;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;
import tv.danmaku.ijk.media.example.widget.media.FFmpegInvoke;
import tv.danmaku.ijk.media.ext.report.ReportConstant;

/* loaded from: classes12.dex */
public class MediaUploadUtils {
    private static final String AUCODE = "66e303d6030ac04839082240eeb76b60";
    public static final String DOMAINNAME = "m.360buyimg.com/webview/";
    private static final String IMGURL = "https://upload-appimg.jd.com/appImageUpload.action";
    private static final String TAG = "MediaUploadUtils";
    private static final String VIDEOCOMPRESSPATH = "/storage/emulated/0/jd/video/webviewCompressVideo.mp4";
    private static final String uploadPinEncodeKey = JDKeyStore.getInstance().generateKey(JDKeyStore.KEY_TYPE_3DES);

    /* JADX INFO: Access modifiers changed from: private */
    public static void getVideoUploadUrl(final IWebUiBinder iWebUiBinder, final WebFileUploader webFileUploader, final String str, final String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            long length = new File(str).length();
            jSONObject.put("fileSize", length + "");
            if (OKLog.D) {
                OKLog.d(TAG, "fileSize " + length);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        HttpGroup httpGroupaAsynPool = HttpGroupUtils.getHttpGroupaAsynPool();
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("videoUploadUrl");
        httpSetting.setAttempts(1);
        httpSetting.setPost(false);
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setJsonParams(jSONObject);
        httpSetting.setListener(new HttpGroup.CustomOnAllListener() { // from class: com.jingdong.common.web.util.MediaUploadUtils.4
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JDJSONObject jSONObject2 = httpResponse.getFastJsonObject().getJSONObject("data");
                jSONObject2.getString("videoId");
                String string = jSONObject2.getString("uploadUrl");
                String string2 = jSONObject2.getString(ReportConstant.CommonInfo.PLAY_URL);
                if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                    if (OKLog.D) {
                        OKLog.d(MediaUploadUtils.TAG, "uploadUrl " + string + " playUrl " + string2 + " path " + str);
                    }
                    MediaUploadUtils.startUploadVideo(webFileUploader, iWebUiBinder, str2, str, string, string2);
                    return;
                }
                WebUtils.sendJSONStr2M(iWebUiBinder, str2, WebUtils.stringfyJSonData("-1", "getUploadUrlFail", "fail"));
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                WebUtils.sendJSONStr2M(iWebUiBinder, str2, WebUtils.stringfyJSonData("-1", "getUploadUrlFail", "fail"));
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        httpGroupaAsynPool.add(httpSetting);
    }

    private static String initKeyCode(long j2, byte[] bArr) {
        if (j2 == -1) {
            return null;
        }
        return Long.toHexString(j2) + "" + bArr.length + CartConstant.KEY_YB_INFO_LINK;
    }

    private static WebFileUploader.UploadRequest initUploadImageTask(IWebUiBinder iWebUiBinder, WebFileUploader webFileUploader, String str, int i2, String str2) {
        byte[] compressByParameter = BitmapUtils.compressByParameter(str, i2, str2);
        String initKeyCode = initKeyCode(CRCUtils.checksumBytes(compressByParameter), compressByParameter);
        if (compressByParameter != null && !TextUtils.isEmpty(initKeyCode)) {
            return WebFileUploader.UploadRequest.Builder.newInstance().addHeader("aucode", AUCODE).addHeader("client", "Android").addHeader("keycode", initKeyCode).addHeader("type", "0").addHeader("appid", "0").addHeader("clientIp", "127.0.0.1").addHeader("ipPort", DeviceInfoUtil.getIpAddress()).addHeader("uploadPin", DesCbcCrypto.encrypt(LoginUserBase.getUserPin(), uploadPinEncodeKey, (byte[]) null)).byteSource(compressByParameter).url(IMGURL).build();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\u56fe\u7247\u4e0a\u4f20\u521d\u59cb\u5316\u5931\u8d25,request\u751f\u6210\u5931\u8d25: ");
        sb.append(compressByParameter == null);
        sb.append(TextUtils.isEmpty(initKeyCode));
        WebUnifiedMtaUtil.sendAlbumMta(iWebUiBinder, "album_exception", sb.toString());
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void startUploadVideo(WebFileUploader webFileUploader, final IWebUiBinder iWebUiBinder, final String str, String str2, String str3, final String str4) {
        if (webFileUploader == null) {
            return;
        }
        webFileUploader.uploadVideo(str3, str2, new WebFileUploader.UniformListener() { // from class: com.jingdong.common.web.util.MediaUploadUtils.5
            @Override // com.jingdong.common.web.util.WebFileUploader.UniformListener
            public void onEnd(String str5, String[] strArr, String str6) {
                if (OKLog.D) {
                    OKLog.d(MediaUploadUtils.TAG, "uploadVideo end");
                }
                WebUtils.sendJSONStr2M(IWebUiBinder.this, str, WebUtils.stringfyJSonData(str5, str4, str6));
                if (new File(MediaUploadUtils.VIDEOCOMPRESSPATH).exists()) {
                    new File(MediaUploadUtils.VIDEOCOMPRESSPATH).delete();
                }
            }

            @Override // com.jingdong.common.web.util.WebFileUploader.UniformListener
            public void onError(String str5, String[] strArr, String str6) {
                if (OKLog.D) {
                    OKLog.d(MediaUploadUtils.TAG, "uploadVideo error");
                }
                WebUtils.sendJSONStr2M(IWebUiBinder.this, str, WebUtils.stringfyJSonData(str5, strArr, str6));
                if (new File(MediaUploadUtils.VIDEOCOMPRESSPATH).exists()) {
                    new File(MediaUploadUtils.VIDEOCOMPRESSPATH).delete();
                }
            }

            @Override // com.jingdong.common.web.util.WebFileUploader.UniformListener
            public void onStart() {
                if (OKLog.D) {
                    OKLog.d(MediaUploadUtils.TAG, "uploadVideo start ");
                }
                WebUtils.sendJSONStr2M(IWebUiBinder.this, str, WebUtils.stringfyJSonData("1", "", ""));
            }
        });
    }

    public static void uploadPics(final IWebUiBinder iWebUiBinder, String str, final String str2, WebFileUploader webFileUploader, int i2, final List<String> list, Boolean bool) {
        if (iWebUiBinder == null || webFileUploader == null) {
            return;
        }
        if (list != null && !list.isEmpty()) {
            if (OKLog.D) {
                OKLog.e(TAG, "uploadPics start");
            }
            WebUtils.sendJSONStr2M(iWebUiBinder, str2, WebUtils.stringfyJSonData("1", "", ""));
            for (int i3 = 0; i3 < list.size(); i3++) {
                String str3 = (list.get(i3) == null || TextUtils.isEmpty(list.get(i3))) ? DYConstants.DY_FALSE : list.get(i3);
                if (!DYConstants.DY_FALSE.equals(str3)) {
                    if (bool.booleanValue()) {
                        webFileUploader.addTask(initUploadImageTask(iWebUiBinder, webFileUploader, str3, i2, str));
                    } else {
                        webFileUploader.uploadPicWithSingleCallBack(initUploadImageTask(iWebUiBinder, webFileUploader, str3, i2, str), i3, new WebFileUploader.SingleListener() { // from class: com.jingdong.common.web.util.MediaUploadUtils.1
                            @Override // com.jingdong.common.web.util.WebFileUploader.SingleListener
                            public void onEnd(String str4, String str5, String str6, int i4) {
                                if (OKLog.D) {
                                    OKLog.e(MediaUploadUtils.TAG, "uploadPics end " + str5);
                                }
                                WebUtils.sendJSONStr2M(IWebUiBinder.this, str2, WebUtils.stringfyJSonData(str4, str5, i4, list.size()));
                            }

                            @Override // com.jingdong.common.web.util.WebFileUploader.SingleListener
                            public void onError(String str4, String str5, String str6, int i4) {
                                WebUtils.sendJSONStr2M(IWebUiBinder.this, str2, WebUtils.stringfyJSonData(str4, str5, i4, list.size()));
                                WebUnifiedMtaUtil.sendAlbumMta(IWebUiBinder.this, "album_exception", "\u4e0a\u4f20\u5931\u8d25: " + str5 + LangUtils.SINGLE_SPACE + i4);
                            }
                        });
                    }
                }
            }
        }
        if (bool.booleanValue()) {
            webFileUploader.uploadPicWithUniformCallBack(new WebFileUploader.UniformListener() { // from class: com.jingdong.common.web.util.MediaUploadUtils.2
                @Override // com.jingdong.common.web.util.WebFileUploader.UniformListener
                public void onEnd(String str4, String[] strArr, String str5) {
                    if (OKLog.D) {
                        OKLog.e(MediaUploadUtils.TAG, "uploadPics end " + Arrays.toString(strArr));
                    }
                    try {
                        WebUtils.sendJSONStr2M(IWebUiBinder.this, str2, WebUtils.stringfyJSonData(str4, Arrays.toString(strArr), str5));
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

                @Override // com.jingdong.common.web.util.WebFileUploader.UniformListener
                public void onError(String str4, String[] strArr, String str5) {
                    if (OKLog.D) {
                        OKLog.e(MediaUploadUtils.TAG, "uploadPics error");
                    }
                    WebUtils.sendJSONStr2M(IWebUiBinder.this, str2, WebUtils.stringfyJSonData(str4, Arrays.toString(strArr), str5));
                    WebUnifiedMtaUtil.sendAlbumMta(IWebUiBinder.this, "album_exception", "\u4e0a\u4f20\u5931\u8d25: " + Arrays.toString(strArr));
                }

                @Override // com.jingdong.common.web.util.WebFileUploader.UniformListener
                public void onStart() {
                }
            });
        }
    }

    public static void uploadVideo(final IWebUiBinder iWebUiBinder, final WebFileUploader webFileUploader, final String str, final String str2) {
        if (TextUtils.isEmpty(str) || webFileUploader == null || iWebUiBinder == null || !new File(str).exists()) {
            return;
        }
        if (new File(str).length() > 3145728 && iWebUiBinder.getBaseActivity() != null) {
            if (new File(VIDEOCOMPRESSPATH).exists()) {
                new File(VIDEOCOMPRESSPATH).delete();
            }
            FFmpegInvoke.loadLibrariesOnce(iWebUiBinder.getBaseActivity());
            FFmpegInvoke.exec(FFmpegInvoke.buildCmd(str, VIDEOCOMPRESSPATH, 30, "480k"), new FFmpegInvoke.OnExecListener() { // from class: com.jingdong.common.web.util.MediaUploadUtils.3
                @Override // tv.danmaku.ijk.media.example.widget.media.FFmpegInvoke.OnExecListener
                public void onExecuted(int i2) {
                    if (OKLog.D) {
                        OKLog.d(MediaUploadUtils.TAG, "onExecuted " + i2);
                    }
                    if (!new File(MediaUploadUtils.VIDEOCOMPRESSPATH).exists() || i2 != 0) {
                        MediaUploadUtils.getVideoUploadUrl(IWebUiBinder.this, webFileUploader, str, str2);
                    } else {
                        MediaUploadUtils.getVideoUploadUrl(IWebUiBinder.this, webFileUploader, MediaUploadUtils.VIDEOCOMPRESSPATH, str2);
                    }
                }
            });
            return;
        }
        getVideoUploadUrl(iWebUiBinder, webFileUploader, str, str2);
    }
}
