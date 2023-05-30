package com.jingdong.common.widget.photo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.media.ExifInterface;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.R;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/* loaded from: classes12.dex */
public class PhotoUtils {
    public static final String CLICK_PHOTO_DETAIL_CONFIRM = "photo_detail_confirm";
    public static final String CLICK_PHOTO_DETAIL_DELETE_PIC = "photo_detail_delete";
    public static final String CLICK_PHOTO_DETAIL_DIALOG_NO = "photo_detail_delect_dialog_no";
    public static final String CLICK_PHOTO_DETAIL_DIALOG_YES = "photo_detail_delect_dialog_yes";
    public static final String CLICK_PHOTO_DETAIL_DROP_PIC = "photo_detail_drop";
    public static final String CLICK_PHOTO_DETAIL_PAGE_SELECTED = "photo_detail_page_selected";
    public static final String CLICK_PHOTO_DETAIL_SELECTED_PIC = "photo_detail_selected";
    public static final String CLICK_PHOTO_DETAIL_TOP_RIGHT = "photo_detail_delect_top_right";
    public static final String CLICK_PHOTO_LIST_CANCEL = "photo_list_cancel";
    public static final String CLICK_PHOTO_LIST_CONFIRM = "photo_list_confirm";
    public static final String CLICK_PHOTO_LIST_DELETE = "photo_list_delete";
    public static final String CLICK_PHOTO_LIST_DROP_PIC = "photo_list_drop";
    public static final String CLICK_PHOTO_LIST_PREVIEW_PIC = "photo_list_previewPic";
    public static final String CLICK_PHOTO_LIST_SELECTED_PIC = "photo_list_selected";
    public static final String CLICK_PHOTO_TAKE_PIC = "photo_list_take_pic";
    public static final String PV_PHOTO_DETAIL = "photo_list_detail";
    public static final String PV_PHOTO_LIST = "photo_list";
    public static final int SOURCE_FEEDBACK = 3;
    private static final String TAG = "PhotoUtils";
    private static ArrayList<String> photoFormatLimit = new ArrayList<>();
    private static int photoSizeLimit = 0;
    private static int photoWidthLimit = 0;
    private static int photoHeightLimit = 0;

    public static boolean checkLimit(Context context, String str) {
        String substring;
        ArrayList<String> arrayList;
        try {
            substring = str.substring(str.lastIndexOf(OrderISVUtil.MONEY_DECIMAL) + 1);
            arrayList = photoFormatLimit;
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.e(TAG, e2);
            }
        }
        if (arrayList != null && arrayList.size() > 0 && !photoFormatLimit.contains(substring.toLowerCase())) {
            ToastUtils.shortToast(R.string.uni_photo_check_fail);
            return true;
        } else if (photoSizeLimit > 0 && ((float) getFileSize(new File(str))) / 1048576.0f > photoSizeLimit) {
            try {
                ToastUtils.shortToast(String.format(context.getResources().getString(R.string.uni_photo_size_max), Integer.valueOf(photoSizeLimit)));
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            return true;
        } else {
            if (photoWidthLimit > 0 || photoHeightLimit > 0) {
                ExifInterface exifInterface = new ExifInterface(str);
                float parseFloat = Float.parseFloat(exifInterface.getAttribute("ImageWidth"));
                float parseFloat2 = Float.parseFloat(exifInterface.getAttribute("ImageLength"));
                if (parseFloat == 0.0f && parseFloat2 == 0.0f) {
                    Bitmap decodeFile = BitmapFactory.decodeFile(str);
                    decodeFile.getWidth();
                    decodeFile.getHeight();
                    OKLog.d("MMMMM", "no exif");
                }
            }
            if (OKLog.D) {
                OKLog.d("MMMMM", photoFormatLimit.toString() + "||" + photoSizeLimit + "||" + photoWidthLimit + "||" + photoHeightLimit);
            }
            return false;
        }
    }

    public static long getFileSize(File file) throws Exception {
        return file.length();
    }

    public static String getImageContentDescription(String str, boolean z) {
        return (z ? "\u89c6\u9891\u62cd\u6444\u65f6\u95f4\uff0c" : "\u7167\u7247\u62cd\u6444\u65f6\u95f4\uff0c") + getTakeDate(str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0066, code lost:
        if (com.jingdong.sdk.oklog.OKLog.E == false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0068, code lost:
        com.jingdong.sdk.oklog.OKLog.e(com.jingdong.common.widget.photo.PhotoUtils.TAG, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0083, code lost:
        if (com.jingdong.sdk.oklog.OKLog.E == false) goto L43;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<java.lang.String> getPhotos(android.content.Context r10, java.lang.String r11) {
        /*
            r0 = 3
            java.lang.String[] r3 = new java.lang.String[r0]
            r0 = 0
            java.lang.String r7 = "_data"
            r3[r0] = r7
            java.lang.String r1 = "datetaken"
            r2 = 1
            r3[r2] = r1
            r1 = 2
            java.lang.String r4 = "_id"
            r3[r1] = r4
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r9 = 0
            android.content.ContentResolver r1 = r10.getContentResolver()     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
            android.net.Uri r10 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
            if (r11 != 0) goto L22
            r4 = r9
            goto L24
        L22:
            java.lang.String r4 = "bucket_id=?"
        L24:
            if (r11 != 0) goto L28
            r5 = r9
            goto L2d
        L28:
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
            r2[r0] = r11     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
            r5 = r2
        L2d:
            java.lang.String r6 = "_id DESC"
            r2 = r10
            android.database.Cursor r9 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
            if (r9 == 0) goto L5d
            int r10 = r9.getColumnIndexOrThrow(r7)     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
        L3a:
            boolean r11 = r9.moveToNext()     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
            if (r11 == 0) goto L5d
            java.lang.String r11 = r9.getString(r10)     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
            if (r11 == 0) goto L3a
            java.lang.String r0 = ".gif"
            boolean r0 = r11.endsWith(r0)     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
            if (r0 != 0) goto L3a
            java.io.File r0 = new java.io.File     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
            r0.<init>(r11)     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
            boolean r0 = r0.exists()     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
            if (r0 == 0) goto L3a
            r8.add(r11)     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L70
            goto L3a
        L5d:
            if (r9 == 0) goto L86
            r9.close()     // Catch: java.lang.Exception -> L63
            goto L86
        L63:
            r10 = move-exception
            boolean r11 = com.jingdong.sdk.oklog.OKLog.E
            if (r11 == 0) goto L86
        L68:
            java.lang.String r11 = com.jingdong.common.widget.photo.PhotoUtils.TAG
            com.jingdong.sdk.oklog.OKLog.e(r11, r10)
            goto L86
        L6e:
            r10 = move-exception
            goto L87
        L70:
            r10 = move-exception
            boolean r11 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L6e
            if (r11 == 0) goto L7a
            java.lang.String r11 = com.jingdong.common.widget.photo.PhotoUtils.TAG     // Catch: java.lang.Throwable -> L6e
            com.jingdong.sdk.oklog.OKLog.e(r11, r10)     // Catch: java.lang.Throwable -> L6e
        L7a:
            if (r9 == 0) goto L86
            r9.close()     // Catch: java.lang.Exception -> L80
            goto L86
        L80:
            r10 = move-exception
            boolean r11 = com.jingdong.sdk.oklog.OKLog.E
            if (r11 == 0) goto L86
            goto L68
        L86:
            return r8
        L87:
            if (r9 == 0) goto L97
            r9.close()     // Catch: java.lang.Exception -> L8d
            goto L97
        L8d:
            r11 = move-exception
            boolean r0 = com.jingdong.sdk.oklog.OKLog.E
            if (r0 == 0) goto L97
            java.lang.String r0 = com.jingdong.common.widget.photo.PhotoUtils.TAG
            com.jingdong.sdk.oklog.OKLog.e(r0, r11)
        L97:
            goto L99
        L98:
            throw r10
        L99:
            goto L98
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.widget.photo.PhotoUtils.getPhotos(android.content.Context, java.lang.String):java.util.ArrayList");
    }

    public static String getTakeDate(String str) {
        ExifInterface exifInterface;
        try {
            exifInterface = new ExifInterface(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            exifInterface = null;
        }
        String attribute = exifInterface != null ? exifInterface.getAttribute("DateTime") : "";
        if (TextUtils.isEmpty(attribute)) {
            return timeStamp2Date(new File(str).lastModified(), "");
        }
        return timeStamp2Date(attribute, "");
    }

    public static void initPhotoLimitParams(String str) {
        if (TextUtils.isEmpty(str)) {
            photoFormatLimit = new ArrayList<>();
            photoHeightLimit = 0;
            photoWidthLimit = 0;
            photoSizeLimit = 0;
            return;
        }
        String[] split = str.split(DYConstants.DY_REGEX_VERTICAL_LINE);
        if (split.length != 3) {
            return;
        }
        photoFormatLimit = new ArrayList<>();
        photoHeightLimit = 0;
        photoWidthLimit = 0;
        photoSizeLimit = 0;
        for (String str2 : split[0].split(DYConstants.DY_REGEX_COMMA)) {
            photoFormatLimit.add(str2);
        }
        photoSizeLimit = Integer.parseInt(split[1]);
        String[] split2 = split[2].split(DYConstants.DY_REGEX_COMMA);
        if (split2.length == 2) {
            photoWidthLimit = Integer.parseInt(split2[0]);
            photoHeightLimit = Integer.parseInt(split2[1]);
            return;
        }
        photoWidthLimit = 0;
        photoHeightLimit = 0;
    }

    public static boolean isCameraCanUse() {
        Camera camera;
        boolean z;
        try {
            camera = Camera.open();
            z = true;
        } catch (Exception unused) {
            camera = null;
            z = false;
        }
        boolean z2 = camera != null ? z : false;
        if (z2) {
            camera.release();
        }
        return z2;
    }

    public static void sendComonData(Context context, String str, int i2) {
        try {
            if (i2 == 1) {
                sendStoryCommonData(context, str);
            } else if (i2 != 3) {
            } else {
                sendFeedbackCommonData(context, str);
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public static void sendFeedbackCommonData(Context context, String str) {
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1987225691:
                if (str.equals(CLICK_PHOTO_DETAIL_DIALOG_NO)) {
                    c2 = 0;
                    break;
                }
                break;
            case -1474443901:
                if (str.equals(CLICK_PHOTO_DETAIL_DIALOG_YES)) {
                    c2 = 1;
                    break;
                }
                break;
            case 1083513407:
                if (str.equals(CLICK_PHOTO_DETAIL_TOP_RIGHT)) {
                    c2 = 2;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                JDMtaUtils.sendCommonData(context, "MyJD_Feedback_DeletePhotoNO", "", "", PhotoListActivity.class, "", "", "", "MyJD_Feedback", "");
                return;
            case 1:
                JDMtaUtils.sendCommonData(context, "MyJD_Feedback_DeletePhotoYES", "", "", PhotoListActivity.class, "", "", "", "MyJD_Feedback", "");
                return;
            case 2:
                JDMtaUtils.sendCommonData(context, "MyJD_Feedback_DeletePhoto", "", "", PhotoListActivity.class, "", "", "", "MyJD_Feedback", "");
                return;
            default:
                return;
        }
    }

    public static void sendPagePV(Context context, int i2, String str) {
        if (i2 != 1) {
            return;
        }
        sendStoryPagePV(context, str);
    }

    public static void sendStoryCommonData(Context context, String str) {
        str.hashCode();
        if (str.equals(CLICK_PHOTO_LIST_PREVIEW_PIC)) {
            JDMtaUtils.sendCommonData(context, "StoryEdit_PreviewPic", "", "", PhotoListActivity.class, "", "", "", "Story_Photo", "");
        } else if (str.equals(CLICK_PHOTO_TAKE_PIC)) {
            JDMtaUtils.sendCommonData(context, "TellStory_Shot", "", "", PhotoListActivity.class, "", "", "", "Story_Photo", "");
        }
    }

    private static void sendStoryPagePV(Context context, String str) {
        str.hashCode();
        if (str.equals(PV_PHOTO_DETAIL)) {
            JDMtaUtils.sendPagePv(context, "PhotoDetailActivity", "", "Story_PreviewPic", "");
        } else if (str.equals(PV_PHOTO_LIST)) {
            JDMtaUtils.sendPagePv(context, "PhotoListActivity", "", "Story_Photo", "");
        }
    }

    public static String timeStamp2Date(long j2, String str) {
        if (j2 <= 0) {
            return "";
        }
        if (TextUtils.isEmpty(str)) {
            str = "yyyy\u5e74MM\u6708dd\u65e5 HH:mm:ss";
        }
        try {
            return new SimpleDateFormat(str).format(new Date(j2));
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    @SuppressLint({"SimpleDateFormat"})
    public static String timeStamp2Date(String str, String str2) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").parse(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            date = null;
        }
        if (date == null) {
            return "";
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = "yyyy\u5e74MM\u6708dd\u65e5 HH:mm:ss";
        }
        try {
            return new SimpleDateFormat(str2).format(date);
        } catch (Exception e3) {
            e3.printStackTrace();
            return "";
        }
    }
}
