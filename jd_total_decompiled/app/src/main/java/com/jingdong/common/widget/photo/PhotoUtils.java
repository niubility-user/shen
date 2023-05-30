package com.jingdong.common.widget.photo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.media.ExifInterface;
import android.provider.MediaStore;
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
    */
    public static ArrayList<String> getPhotos(Context context, String str) {
        String[] strArr = {"_data", "datetaken", "_id"};
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            try {
                cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, strArr, str == null ? null : "bucket_id=?", str == null ? null : new String[]{str}, "_id DESC");
                if (cursor != null) {
                    int columnIndexOrThrow = cursor.getColumnIndexOrThrow("_data");
                    while (cursor.moveToNext()) {
                        String string = cursor.getString(columnIndexOrThrow);
                        if (string != null && !string.endsWith(".gif") && new File(string).exists()) {
                            arrayList.add(string);
                        }
                    }
                }
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Exception e2) {
                        e = e2;
                    }
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Exception e3) {
                        if (OKLog.E) {
                            OKLog.e(TAG, e3);
                        }
                    }
                }
                throw th;
            }
        } catch (Exception e4) {
            if (OKLog.E) {
                OKLog.e(TAG, e4);
            }
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e5) {
                    e = e5;
                }
            }
        }
        return arrayList;
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
