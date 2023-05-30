package com.jingdong.common.jdreactFramework.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.JdWebViewFunctionUtil;
import com.jingdong.common.widget.photo.AlbumListActivity;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class ReactMediaUtil {
    public static final int REQUEST_CODE_FILECHOOSER = 12346;
    public static final int REQUEST_CODE_IMAGE = 12345;
    public static final int REQUEST_CODE_VIDEO = 12347;
    static final String TAG = "ReactMediaUtil";
    private static SelectFileListener mSelectFileListener;
    private static Uri videoUri;

    /* loaded from: classes5.dex */
    public interface SelectFileListener {
        void onSelected(List<String> list);
    }

    /* loaded from: classes5.dex */
    public interface UploadFileListener {
        void onFail();

        void onSuccess();
    }

    public static void dealActivityResult(Context context, int i2, int i3, Intent intent) {
        Uri data;
        if (intent == null) {
            return;
        }
        String str = TAG;
        OKLog.d(str, "dealActivityResult:" + intent);
        if (i2 == 12345) {
            ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra(AlbumListActivity.KEY_RESULT_PHOTOS);
            if (stringArrayListExtra != null && stringArrayListExtra.size() >= 1) {
                OKLog.d(str, "dealActivityResult,getImagePath:" + stringArrayListExtra);
                SelectFileListener selectFileListener = mSelectFileListener;
                if (selectFileListener != null) {
                    selectFileListener.onSelected(stringArrayListExtra);
                }
            }
        } else if (i2 == 12346 && (data = intent.getData()) != null) {
            String pathFromUri = JdWebViewFunctionUtil.getPathFromUri(context, data);
            OKLog.d(str, "dealActivityResult,getFilePath:" + pathFromUri);
            ArrayList arrayList = new ArrayList();
            arrayList.add(pathFromUri);
            SelectFileListener selectFileListener2 = mSelectFileListener;
            if (selectFileListener2 != null) {
                selectFileListener2.onSelected(arrayList);
            }
        }
        mSelectFileListener = null;
    }

    public static void gotoFileChooser(Context context, String str, SelectFileListener selectFileListener) {
        if (context instanceof Activity) {
            mSelectFileListener = selectFileListener;
            Intent selectFileIntent = ImageUtil.getSelectFileIntent();
            selectFileIntent.setType(str);
            ((Activity) context).startActivityForResult(selectFileIntent, 12346);
        }
    }

    public static void selectImage(Context context, int i2, ArrayList<String> arrayList, SelectFileListener selectFileListener) {
        if (i2 <= 0 || i2 > 9 || !(context instanceof Activity)) {
            return;
        }
        mSelectFileListener = selectFileListener;
        Intent intent = new Intent(context, AlbumListActivity.class);
        intent.putExtra(AlbumListActivity.KEY_MAX_COUNT, i2);
        if (arrayList != null && arrayList.size() > 0) {
            intent.putStringArrayListExtra(AlbumListActivity.KEY_RESULT_PHOTOS, arrayList);
        }
        ((Activity) context).startActivityForResult(intent, 12345);
    }

    public static void selectImage(Context context, int i2, SelectFileListener selectFileListener) {
        selectImage(context, i2, null, selectFileListener);
    }
}
