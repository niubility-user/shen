package com.jingdong.common.web.util;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.web.entity.JsInputEntity;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes12.dex */
public class MultiMedia {
    public static final int MULTI_MEDIA_PIC = 16;
    public static final int MULTI_MEDIA_PIC_TAKE_PHOTO = 19;
    private static final String TAG = "MultiMedia";
    private static final int TAKE_PHOTO_TYPE = 1;
    private static final int UPLOAD_TO_H5 = 1;
    private static final int UPLOAD_TO_SERVER = 0;
    private static Builder builder;
    private static WebFileUploader webFileUploader;

    /* loaded from: classes12.dex */
    public static class Builder {
        boolean callBackBatch;
        int callBackDataType;
        String compress;
        String jsCallBackName;
        int minLength;

        Builder(String str, String str2, int i2, int i3, boolean z) {
            this.jsCallBackName = str;
            this.compress = str2;
            this.minLength = i2;
            this.callBackDataType = i3;
            this.callBackBatch = z;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(24:1|(1:3)|4|(3:59|60|(18:62|7|(1:58)(2:11|12)|(3:47|48|(15:50|51|(3:39|40|(7:42|43|17|18|19|20|(6:27|(1:29)|30|(1:32)(1:35)|33|34)(2:24|25)))|16|17|18|19|20|(1:22)|27|(0)|30|(0)(0)|33|34))|14|(0)|16|17|18|19|20|(0)|27|(0)|30|(0)(0)|33|34))|6|7|(1:9)|58|(0)|14|(0)|16|17|18|19|20|(0)|27|(0)|30|(0)(0)|33|34|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x009c, code lost:
        r0 = e;
     */
    /* JADX WARN: Removed duplicated region for block: B:179:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x007d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0067 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void getMediaFile(IWebUiBinder iWebUiBinder, JsInputEntity jsInputEntity) {
        String str;
        int i2;
        int i3;
        int i4;
        int i5;
        String str2;
        boolean z;
        if (OKLog.D) {
            OKLog.d(TAG, "MediaUtils -->> " + jsInputEntity);
        }
        Object paramValue = jsInputEntity.getParamValue("compress");
        Object paramValue2 = jsInputEntity.getParamValue("callBackDataType");
        Object paramValue3 = jsInputEntity.getParamValue("interfaceType");
        Object paramValue4 = jsInputEntity.getParamValue("mediaMinLength");
        Object paramValue5 = jsInputEntity.getParamValue("callBackBatch");
        String str3 = "0.8";
        if (paramValue != null) {
            try {
            } catch (Exception e2) {
                e = e2;
                i2 = 0;
                i3 = 0;
                i4 = 0;
                e.printStackTrace();
                i5 = i4;
                str2 = str3;
                z = true;
                builder = new Builder(jsInputEntity.callBackName, str2, i5, i2, z);
                if (i2 != 0) {
                }
                WebUnifiedMtaUtil.sendAlbumMta(iWebUiBinder, "album_normal", jsInputEntity.params);
                if (i3 == 1) {
                }
                MediaUtils.jumpToGetPhoto(iWebUiBinder.getBaseActivity(), r7, !r7 ? 19 : 16);
            }
            if (!TextUtils.isEmpty((String) paramValue)) {
                str = (String) paramValue;
                str3 = str;
                i2 = (paramValue2 != null || TextUtils.isEmpty((String) paramValue2)) ? 0 : Integer.parseInt((String) paramValue2);
                if (paramValue3 != null) {
                    try {
                    } catch (Exception e3) {
                        e = e3;
                        i3 = 0;
                        i4 = 0;
                        e.printStackTrace();
                        i5 = i4;
                        str2 = str3;
                        z = true;
                        builder = new Builder(jsInputEntity.callBackName, str2, i5, i2, z);
                        if (i2 != 0) {
                        }
                        WebUnifiedMtaUtil.sendAlbumMta(iWebUiBinder, "album_normal", jsInputEntity.params);
                        if (i3 == 1) {
                        }
                        MediaUtils.jumpToGetPhoto(iWebUiBinder.getBaseActivity(), r7, !r7 ? 19 : 16);
                    }
                    if (!TextUtils.isEmpty((String) paramValue3)) {
                        i3 = Integer.parseInt((String) paramValue3);
                        if (paramValue4 != null) {
                            try {
                            } catch (Exception e4) {
                                e = e4;
                                i4 = 0;
                                e.printStackTrace();
                                i5 = i4;
                                str2 = str3;
                                z = true;
                                builder = new Builder(jsInputEntity.callBackName, str2, i5, i2, z);
                                if (i2 != 0) {
                                }
                                WebUnifiedMtaUtil.sendAlbumMta(iWebUiBinder, "album_normal", jsInputEntity.params);
                                if (i3 == 1) {
                                }
                                MediaUtils.jumpToGetPhoto(iWebUiBinder.getBaseActivity(), r7, !r7 ? 19 : 16);
                            }
                            if (!TextUtils.isEmpty((String) paramValue4)) {
                                i4 = Integer.parseInt((String) paramValue4);
                                z = !"1".equals(paramValue5);
                                i5 = i4;
                                str2 = str3;
                                builder = new Builder(jsInputEntity.callBackName, str2, i5, i2, z);
                                if (i2 != 0 && "".equals(LoginUserBase.getUserPin())) {
                                    WebUtils.sendJSONStr2M(iWebUiBinder, jsInputEntity.callBackName, WebUtils.stringfyJSonData("-2", "\u7528\u6237\u672a\u767b\u5f55", "fail"));
                                    WebUnifiedMtaUtil.sendAlbumMta(iWebUiBinder, "album_exception", "\u7528\u6237\u672a\u767b\u5f55: " + jsInputEntity.params);
                                    return;
                                }
                                WebUnifiedMtaUtil.sendAlbumMta(iWebUiBinder, "album_normal", jsInputEntity.params);
                                boolean z2 = i3 == 1;
                                MediaUtils.jumpToGetPhoto(iWebUiBinder.getBaseActivity(), z2, !z2 ? 19 : 16);
                            }
                        }
                        i4 = 0;
                        z = !"1".equals(paramValue5);
                        i5 = i4;
                        str2 = str3;
                        builder = new Builder(jsInputEntity.callBackName, str2, i5, i2, z);
                        if (i2 != 0) {
                        }
                        WebUnifiedMtaUtil.sendAlbumMta(iWebUiBinder, "album_normal", jsInputEntity.params);
                        if (i3 == 1) {
                        }
                        MediaUtils.jumpToGetPhoto(iWebUiBinder.getBaseActivity(), z2, !z2 ? 19 : 16);
                    }
                }
                i3 = 0;
                if (paramValue4 != null) {
                }
                i4 = 0;
                z = !"1".equals(paramValue5);
                i5 = i4;
                str2 = str3;
                builder = new Builder(jsInputEntity.callBackName, str2, i5, i2, z);
                if (i2 != 0) {
                }
                WebUnifiedMtaUtil.sendAlbumMta(iWebUiBinder, "album_normal", jsInputEntity.params);
                if (i3 == 1) {
                }
                MediaUtils.jumpToGetPhoto(iWebUiBinder.getBaseActivity(), z2, !z2 ? 19 : 16);
            }
        }
        str = "0.8f";
        str3 = str;
        if (paramValue2 != null) {
        }
        if (paramValue3 != null) {
        }
        i3 = 0;
        if (paramValue4 != null) {
        }
        i4 = 0;
        z = !"1".equals(paramValue5);
        i5 = i4;
        str2 = str3;
        builder = new Builder(jsInputEntity.callBackName, str2, i5, i2, z);
        if (i2 != 0) {
        }
        WebUnifiedMtaUtil.sendAlbumMta(iWebUiBinder, "album_normal", jsInputEntity.params);
        if (i3 == 1) {
        }
        MediaUtils.jumpToGetPhoto(iWebUiBinder.getBaseActivity(), z2, !z2 ? 19 : 16);
    }

    public static void onCancel() {
        if (builder != null) {
            builder = null;
        }
        WebFileUploader webFileUploader2 = webFileUploader;
        if (webFileUploader2 != null) {
            webFileUploader2.cancel();
            webFileUploader = null;
        }
    }

    public static void processResultIntent(final IWebUiBinder iWebUiBinder, Intent intent, int i2) {
        final List<String> processPhotoSelectResult;
        Builder builder2 = builder;
        if (builder2 == null) {
            return;
        }
        final String str = builder2.jsCallBackName;
        final int i3 = builder2.callBackDataType;
        final String str2 = builder2.compress;
        final int i4 = builder2.minLength;
        final boolean z = builder2.callBackBatch;
        List<String> list = null;
        if (i2 == 19) {
            String processPhotoCaptureResult = MediaUtils.processPhotoCaptureResult(intent);
            if (!TextUtils.isEmpty(processPhotoCaptureResult)) {
                list = Collections.singletonList(processPhotoCaptureResult);
            }
        } else if (i2 == 16) {
            processPhotoSelectResult = MediaUtils.processPhotoSelectResult(intent);
            if (processPhotoSelectResult == null && !processPhotoSelectResult.isEmpty()) {
                new Thread(new Runnable() { // from class: com.jingdong.common.web.util.MultiMedia.1
                    @Override // java.lang.Runnable
                    public void run() {
                        int i5 = i3;
                        if (i5 == 0) {
                            if (MultiMedia.webFileUploader == null) {
                                WebFileUploader unused = MultiMedia.webFileUploader = new WebFileUploader();
                            }
                            MediaUploadUtils.uploadPics(iWebUiBinder, str2, str, MultiMedia.webFileUploader, i4, processPhotoSelectResult, Boolean.valueOf(z));
                        } else if (i5 == 1) {
                            Bitmap[] bitmapArr = new Bitmap[processPhotoSelectResult.size()];
                            String[] strArr = new String[processPhotoSelectResult.size()];
                            int i6 = 0;
                            try {
                                try {
                                    int parseFloat = (Float.parseFloat(str2) <= 0.0f || Float.parseFloat(str2) > 1.0f) ? 80 : (int) (Float.parseFloat(str2) * 100.0f);
                                    for (int i7 = 0; i7 < processPhotoSelectResult.size(); i7++) {
                                        if (!TextUtils.isEmpty((CharSequence) processPhotoSelectResult.get(i7))) {
                                            bitmapArr[i7] = BitmapFactory.decodeFile((String) processPhotoSelectResult.get(i7));
                                            strArr[i7] = MediaUtils.bitmap2Base64Str(bitmapArr[i7], parseFloat);
                                        }
                                    }
                                    WebUtils.sendJSONStr2M(iWebUiBinder, str, WebUtils.stringfyJSonData("0", Arrays.toString(strArr), ""));
                                    while (i6 < processPhotoSelectResult.size()) {
                                        if (!bitmapArr[i6].isRecycled()) {
                                            bitmapArr[i6].recycle();
                                        }
                                        i6++;
                                    }
                                } catch (Exception e2) {
                                    WebUtils.sendJSONStr2M(iWebUiBinder, str, WebUtils.stringfyJSonData("-1", "\u56fe\u7247\u8f6c\u6362\u5931\u8d25", ""));
                                    WebUnifiedMtaUtil.sendAlbumMta(iWebUiBinder, "album_exception", "\u4e8c\u8fdb\u5236\u56fe\u7247\u8f6c\u6362\u5931\u8d25: " + e2.toString());
                                    e2.printStackTrace();
                                    while (i6 < processPhotoSelectResult.size()) {
                                        if (!bitmapArr[i6].isRecycled()) {
                                            bitmapArr[i6].recycle();
                                        }
                                        i6++;
                                    }
                                }
                            } catch (Throwable th) {
                                while (i6 < processPhotoSelectResult.size()) {
                                    if (!bitmapArr[i6].isRecycled()) {
                                        bitmapArr[i6].recycle();
                                    }
                                    i6++;
                                }
                                throw th;
                            }
                        }
                    }
                }).start();
                return;
            } else {
                WebUtils.sendJSONStr2M(iWebUiBinder, str, WebUtils.stringfyJSonData("-1", "notSelectPic", "fail"));
            }
        }
        processPhotoSelectResult = list;
        if (processPhotoSelectResult == null) {
        }
        WebUtils.sendJSONStr2M(iWebUiBinder, str, WebUtils.stringfyJSonData("-1", "notSelectPic", "fail"));
    }
}
