package com.jingdong.common.videoplayer;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jingdong.cleanmvp.engine.HttpGroupUtil;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.UnLog;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import org.json.JSONException;

/* loaded from: classes6.dex */
public class VideoDataUitls {
    private static final String TAG = "VideoDataUitls";
    private static long startTime;

    public static void getVideoPath(BaseActivity baseActivity, String str, final IVideoResultLister iVideoResultLister) {
        if (iVideoResultLister == null) {
            return;
        }
        if (baseActivity != null && !TextUtils.isEmpty(str)) {
            startTime = 0L;
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setHost(Configuration.getCommentHost());
            httpSetting.setFunctionId("getVideoPlayAddress");
            httpSetting.putJsonParam("videoId", str);
            httpSetting.setEffect(0);
            httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.videoplayer.VideoDataUitls.1
                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    IVideoResultLister.this.onExTime(System.currentTimeMillis() - VideoDataUitls.startTime);
                    if (httpResponse == null) {
                        IVideoResultLister.this.onVideoPath("");
                        return;
                    }
                    JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                    if (jSONObject == null) {
                        IVideoResultLister.this.onVideoPath("");
                        return;
                    }
                    try {
                        IVideoResultLister.this.onVideoPath(jSONObject.getString("playAddress"));
                    } catch (JSONException e2) {
                        UnLog.e(VideoDataUitls.TAG, e2.toString());
                        IVideoResultLister.this.onVideoPath("");
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    IVideoResultLister.this.onVideoPath("");
                    IVideoResultLister.this.onExTime(System.currentTimeMillis() - VideoDataUitls.startTime);
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                public void onProgress(int i2, int i3) {
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                public void onStart() {
                    long unused = VideoDataUitls.startTime = System.currentTimeMillis();
                }
            });
            new HttpGroupUtil().getHttpGroupaAsynPool(baseActivity, null).add(httpSetting);
            return;
        }
        iVideoResultLister.onVideoPath("");
        iVideoResultLister.onExTime(System.currentTimeMillis() - startTime);
    }

    public static void getVideoUrl(String str, final IVideoResultLister iVideoResultLister) {
        if (iVideoResultLister == null) {
            return;
        }
        startTime = 0L;
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("videoCommentIos");
        httpSetting.setHost(Configuration.getCommentHost());
        httpSetting.putJsonParam("sku", str);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.videoplayer.VideoDataUitls.2
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                String str2;
                IVideoResultLister.this.onExTime(System.currentTimeMillis() - VideoDataUitls.startTime);
                JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                String str3 = null;
                try {
                } catch (Exception e2) {
                    UnLog.e(VideoDataUitls.TAG, e2.toString());
                    str2 = str3;
                }
                if ("0".equals(jSONObject.getString("code"))) {
                    str3 = jSONObject.getString("videoInfos");
                    str2 = str3.replace("\\", "");
                    if (TextUtils.isEmpty(str2)) {
                        return;
                    }
                    VideoInfo videoInfo = (VideoInfo) JDJSON.parseObject(str2, VideoInfo.class);
                    if (videoInfo != null && !TextUtils.isEmpty(videoInfo.main_url)) {
                        IVideoResultLister.this.onVideoPath(videoInfo.main_url.trim());
                    } else {
                        IVideoResultLister.this.onVideoPath("");
                    }
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                IVideoResultLister.this.onVideoPath("");
                IVideoResultLister.this.onExTime(System.currentTimeMillis() - VideoDataUitls.startTime);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
                long unused = VideoDataUitls.startTime = System.currentTimeMillis();
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }
}
