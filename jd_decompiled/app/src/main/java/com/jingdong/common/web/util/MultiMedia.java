package com.jingdong.common.web.util;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.jingdong.common.web.uibinder.IWebUiBinder;
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
    /* JADX WARN: Code restructure failed: missing block: B:103:0x009c, code lost:
        r0 = e;
     */
    /* JADX WARN: Removed duplicated region for block: B:112:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x007d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0067 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void getMediaFile(com.jingdong.common.web.uibinder.IWebUiBinder r16, com.jingdong.common.web.entity.JsInputEntity r17) {
        /*
            Method dump skipped, instructions count: 261
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.util.MultiMedia.getMediaFile(com.jingdong.common.web.uibinder.IWebUiBinder, com.jingdong.common.web.entity.JsInputEntity):void");
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
