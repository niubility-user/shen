package com.facebook.react.modules.camera;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.jdsdk.constant.CartConstant;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

@ReactModule(name = CameraRollManager.NAME)
/* loaded from: classes12.dex */
public class CameraRollManager extends ReactContextBaseJavaModule {
    private static final String ASSET_TYPE_ALL = "All";
    private static final String ASSET_TYPE_PHOTOS = "Photos";
    private static final String ASSET_TYPE_VIDEOS = "Videos";
    private static final String ERROR_UNABLE_TO_FILTER = "E_UNABLE_TO_FILTER";
    private static final String ERROR_UNABLE_TO_LOAD = "E_UNABLE_TO_LOAD";
    private static final String ERROR_UNABLE_TO_LOAD_PERMISSION = "E_UNABLE_TO_LOAD_PERMISSION";
    private static final String ERROR_UNABLE_TO_SAVE = "E_UNABLE_TO_SAVE";
    public static final String NAME = "CameraRollManager";
    private static final String[] PROJECTION = {"_id", "mime_type", "bucket_display_name", "datetaken", "width", "height", PdLVBody.LONGITUDE, PdLVBody.LATITUDE, "_data"};
    private static final String SELECTION_BUCKET = "bucket_display_name = ?";
    private static final String SELECTION_DATE_TAKEN = "datetaken < ?";

    /* loaded from: classes12.dex */
    private static class GetMediaTask extends GuardedAsyncTask<Void, Void> {
        @Nullable
        private final String mAfter;
        private final String mAssetType;
        private final Context mContext;
        private final int mFirst;
        @Nullable
        private final String mGroupName;
        @Nullable
        private final ReadableArray mMimeTypes;
        private final Promise mPromise;

        private GetMediaTask(ReactContext reactContext, int i2, @Nullable String str, @Nullable String str2, @Nullable ReadableArray readableArray, String str3, Promise promise) {
            super(reactContext);
            this.mContext = reactContext;
            this.mFirst = i2;
            this.mAfter = str;
            this.mGroupName = str2;
            this.mMimeTypes = readableArray;
            this.mPromise = promise;
            this.mAssetType = str3;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.react.bridge.GuardedAsyncTask
        public void doInBackgroundGuarded(Void... voidArr) {
            StringBuilder sb = new StringBuilder("1");
            ArrayList arrayList = new ArrayList();
            if (!TextUtils.isEmpty(this.mAfter)) {
                sb.append(" AND datetaken < ?");
                arrayList.add(this.mAfter);
            }
            if (!TextUtils.isEmpty(this.mGroupName)) {
                sb.append(" AND bucket_display_name = ?");
                arrayList.add(this.mGroupName);
            }
            if (this.mAssetType.equals(CameraRollManager.ASSET_TYPE_PHOTOS)) {
                sb.append(" AND media_type = 1");
            } else if (this.mAssetType.equals(CameraRollManager.ASSET_TYPE_VIDEOS)) {
                sb.append(" AND media_type = 3");
            } else if (this.mAssetType.equals(CameraRollManager.ASSET_TYPE_ALL)) {
                sb.append(" AND media_type IN (3,1)");
            } else {
                this.mPromise.reject(CameraRollManager.ERROR_UNABLE_TO_FILTER, "Invalid filter option: '" + this.mAssetType + "'. Expected one of '" + CameraRollManager.ASSET_TYPE_PHOTOS + "', '" + CameraRollManager.ASSET_TYPE_VIDEOS + "' or '" + CameraRollManager.ASSET_TYPE_ALL + "'.");
                return;
            }
            ReadableArray readableArray = this.mMimeTypes;
            if (readableArray != null && readableArray.size() > 0) {
                sb.append(" AND mime_type IN (");
                for (int i2 = 0; i2 < this.mMimeTypes.size(); i2++) {
                    sb.append("?,");
                    arrayList.add(this.mMimeTypes.getString(i2));
                }
                sb.replace(sb.length() - 1, sb.length(), ")");
            }
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            ContentResolver contentResolver = this.mContext.getContentResolver();
            try {
                Cursor query = contentResolver.query(MediaStore.Files.getContentUri("external"), CameraRollManager.PROJECTION, sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]), "datetaken DESC, date_modified DESC LIMIT " + (this.mFirst + 1));
                if (query != null) {
                    CameraRollManager.putEdges(contentResolver, query, writableNativeMap, this.mFirst);
                    CameraRollManager.putPageInfo(query, writableNativeMap, this.mFirst);
                    query.close();
                    this.mPromise.resolve(writableNativeMap);
                } else {
                    this.mPromise.reject(CameraRollManager.ERROR_UNABLE_TO_LOAD, "Could not get media");
                }
            } catch (SecurityException e2) {
                this.mPromise.reject(CameraRollManager.ERROR_UNABLE_TO_LOAD_PERMISSION, "Could not get media: need permission", e2);
            }
        }
    }

    /* loaded from: classes12.dex */
    private static class SaveToCameraRoll extends GuardedAsyncTask<Void, Void> {
        private final Context mContext;
        private final Promise mPromise;
        private final Uri mUri;
        private Uri uri;

        public SaveToCameraRoll(ReactContext reactContext, Uri uri, Promise promise) {
            super(reactContext);
            this.mContext = reactContext;
            this.mUri = uri;
            this.mPromise = promise;
        }

        public void write10Before() {
            FileChannel fileChannel;
            File externalFilesDir;
            String str;
            File file = new File(this.mUri.getPath());
            FileChannel fileChannel2 = null;
            try {
                try {
                    externalFilesDir = this.mContext.getExternalFilesDir(Environment.DIRECTORY_DCIM);
                    externalFilesDir.mkdirs();
                } catch (Throwable th) {
                    th = th;
                    fileChannel = null;
                }
                if (!externalFilesDir.isDirectory()) {
                    this.mPromise.reject(CameraRollManager.ERROR_UNABLE_TO_LOAD, "External media storage directory not available");
                    return;
                }
                File file2 = new File(externalFilesDir, file.getName());
                String name = file.getName();
                if (name.indexOf(46) >= 0) {
                    String substring = name.substring(0, name.lastIndexOf(46));
                    str = name.substring(name.lastIndexOf(46));
                    name = substring;
                } else {
                    str = "";
                }
                int i2 = 0;
                while (!file2.createNewFile()) {
                    file2 = new File(externalFilesDir, name + CartConstant.KEY_YB_INFO_LINK + i2 + str);
                    i2++;
                }
                FileChannel channel = new FileInputStream(file).getChannel();
                try {
                    fileChannel = new FileOutputStream(file2).getChannel();
                } catch (Throwable th2) {
                    th = th2;
                    fileChannel = null;
                }
                try {
                    fileChannel.transferFrom(channel, 0L, channel.size());
                    channel.close();
                    fileChannel.close();
                    MediaScannerConnection.scanFile(this.mContext, new String[]{file2.getAbsolutePath()}, null, new MediaScannerConnection.OnScanCompletedListener() { // from class: com.facebook.react.modules.camera.CameraRollManager.SaveToCameraRoll.1
                        @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                        public void onScanCompleted(String str2, Uri uri) {
                            if (uri != null) {
                                SaveToCameraRoll.this.mPromise.resolve(uri.toString());
                            } else {
                                SaveToCameraRoll.this.mPromise.reject(CameraRollManager.ERROR_UNABLE_TO_SAVE, "Could not add image to gallery");
                            }
                        }
                    });
                    if (channel != null && channel.isOpen()) {
                        try {
                            channel.close();
                        } catch (IOException e2) {
                            FLog.e(ReactConstants.TAG, "Could not close input channel", e2);
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileChannel2 = channel;
                    try {
                        this.mPromise.reject(th);
                        if (fileChannel2 != null && fileChannel2.isOpen()) {
                            try {
                                fileChannel2.close();
                            } catch (IOException e3) {
                                FLog.e(ReactConstants.TAG, "Could not close input channel", e3);
                            }
                        }
                        if (fileChannel == null || !fileChannel.isOpen()) {
                            return;
                        }
                        fileChannel.close();
                    } catch (Throwable th4) {
                        if (fileChannel2 != null && fileChannel2.isOpen()) {
                            try {
                                fileChannel2.close();
                            } catch (IOException e4) {
                                FLog.e(ReactConstants.TAG, "Could not close input channel", e4);
                            }
                        }
                        if (fileChannel != null && fileChannel.isOpen()) {
                            try {
                                fileChannel.close();
                            } catch (IOException e5) {
                                FLog.e(ReactConstants.TAG, "Could not close output channel", e5);
                            }
                        }
                        throw th4;
                    }
                }
                if (fileChannel == null || !fileChannel.isOpen()) {
                    return;
                }
                fileChannel.close();
            } catch (IOException e6) {
                FLog.e(ReactConstants.TAG, "Could not close output channel", e6);
            }
        }

        public void write11After(InputStream inputStream, String str, String str2) {
            ContentValues contentValues = new ContentValues();
            ContentResolver contentResolver = this.mContext.getContentResolver();
            contentValues.put("_display_name", str);
            contentValues.put("mime_type", str2);
            if (Build.VERSION.SDK_INT >= 29) {
                contentValues.put("relative_path", Environment.DIRECTORY_DCIM);
            } else {
                contentValues.put("_data", this.mContext.getExternalFilesDir(Environment.DIRECTORY_DCIM) + str);
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                Uri insert = this.mContext.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                if (insert != null) {
                    OutputStream openOutputStream = contentResolver.openOutputStream(insert);
                    if (openOutputStream != null) {
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(openOutputStream);
                        byte[] bArr = new byte[1024];
                        for (int read = bufferedInputStream.read(bArr); read >= 0; read = bufferedInputStream.read(bArr)) {
                            bufferedOutputStream.write(bArr, 0, read);
                            bufferedOutputStream.flush();
                        }
                        bufferedOutputStream.close();
                    }
                    bufferedInputStream.close();
                }
            } catch (Throwable th) {
                FLog.e(ReactConstants.TAG, "Write file to DCIM fail", th);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.react.bridge.GuardedAsyncTask
        public void doInBackgroundGuarded(Void... voidArr) {
            FileInputStream fileInputStream;
            File file = new File(this.mUri.toString());
            if (Build.VERSION.SDK_INT >= 29) {
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (FileNotFoundException e2) {
                    FLog.e(ReactConstants.TAG, "File Not Found Exception", e2);
                    fileInputStream = null;
                }
                if (fileInputStream != null) {
                    write11After(fileInputStream, file.getName(), "");
                    return;
                }
                return;
            }
            write10Before();
        }
    }

    public CameraRollManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private static void putBasicNodeInfo(Cursor cursor, WritableMap writableMap, int i2, int i3, int i4) {
        writableMap.putString("type", cursor.getString(i2));
        writableMap.putString("group_name", cursor.getString(i3));
        double d = cursor.getLong(i4);
        Double.isNaN(d);
        writableMap.putDouble(VerifyTracker.KEY_TIMESTAMP, d / 1000.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void putEdges(ContentResolver contentResolver, Cursor cursor, WritableMap writableMap, int i2) {
        WritableNativeArray writableNativeArray;
        int i3;
        WritableNativeArray writableNativeArray2 = new WritableNativeArray();
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex("_id");
        int columnIndex2 = cursor.getColumnIndex("mime_type");
        int columnIndex3 = cursor.getColumnIndex("bucket_display_name");
        int columnIndex4 = cursor.getColumnIndex("datetaken");
        int columnIndex5 = cursor.getColumnIndex("width");
        int columnIndex6 = cursor.getColumnIndex("height");
        int columnIndex7 = cursor.getColumnIndex(PdLVBody.LONGITUDE);
        int columnIndex8 = cursor.getColumnIndex(PdLVBody.LATITUDE);
        int columnIndex9 = cursor.getColumnIndex("_data");
        int i4 = i2;
        int i5 = 0;
        while (i5 < i4 && !cursor.isAfterLast()) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            WritableNativeMap writableNativeMap2 = new WritableNativeMap();
            WritableNativeArray writableNativeArray3 = writableNativeArray2;
            int i6 = columnIndex;
            int i7 = i5;
            int i8 = columnIndex;
            int i9 = columnIndex8;
            if (putImageInfo(contentResolver, cursor, writableNativeMap2, i6, columnIndex5, columnIndex6, columnIndex9)) {
                putBasicNodeInfo(cursor, writableNativeMap2, columnIndex2, columnIndex3, columnIndex4);
                putLocationInfo(cursor, writableNativeMap2, columnIndex7, i9);
                writableNativeMap.putMap("node", writableNativeMap2);
                writableNativeArray = writableNativeArray3;
                writableNativeArray.pushMap(writableNativeMap);
                i3 = i7;
            } else {
                writableNativeArray = writableNativeArray3;
                i3 = i7 - 1;
            }
            cursor.moveToNext();
            i5 = i3 + 1;
            i4 = i2;
            writableNativeArray2 = writableNativeArray;
            columnIndex8 = i9;
            columnIndex = i8;
        }
        writableMap.putArray("edges", writableNativeArray2);
    }

    private static boolean putImageInfo(ContentResolver contentResolver, Cursor cursor, WritableMap writableMap, int i2, int i3, int i4, int i5) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        Uri parse = Uri.parse("file://" + cursor.getString(i5));
        writableNativeMap.putString("uri", parse.toString());
        float f2 = (float) cursor.getInt(i3);
        float f3 = (float) cursor.getInt(i4);
        String guessContentTypeFromName = URLConnection.guessContentTypeFromName(parse.toString());
        if (guessContentTypeFromName != null && guessContentTypeFromName.startsWith("video")) {
            try {
                AssetFileDescriptor openAssetFileDescriptor = contentResolver.openAssetFileDescriptor(parse, "r");
                MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                mediaMetadataRetriever.setDataSource(openAssetFileDescriptor.getFileDescriptor());
                if (f2 <= 0.0f || f3 <= 0.0f) {
                    try {
                        f2 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
                        f3 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
                    } catch (NumberFormatException e2) {
                        FLog.e(ReactConstants.TAG, "Number format exception occurred while trying to fetch video metadata for " + parse.toString(), e2);
                        mediaMetadataRetriever.release();
                        openAssetFileDescriptor.close();
                        return false;
                    }
                }
                writableNativeMap.putInt("playableDuration", Integer.parseInt(mediaMetadataRetriever.extractMetadata(9)) / 1000);
                mediaMetadataRetriever.release();
                openAssetFileDescriptor.close();
            } catch (Exception e3) {
                FLog.e(ReactConstants.TAG, "Could not get video metadata for " + parse.toString(), e3);
                return false;
            }
        }
        if (f2 <= 0.0f || f3 <= 0.0f) {
            try {
                AssetFileDescriptor openAssetFileDescriptor2 = contentResolver.openAssetFileDescriptor(parse, "r");
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFileDescriptor(openAssetFileDescriptor2.getFileDescriptor(), null, options);
                f2 = options.outWidth;
                f3 = options.outHeight;
                openAssetFileDescriptor2.close();
            } catch (IOException e4) {
                FLog.e(ReactConstants.TAG, "Could not get width/height for " + parse.toString(), e4);
                return false;
            }
        }
        writableNativeMap.putDouble("width", f2);
        writableNativeMap.putDouble("height", f3);
        writableMap.putMap("image", writableNativeMap);
        return true;
    }

    private static void putLocationInfo(Cursor cursor, WritableMap writableMap, int i2, int i3) {
        double d = cursor.getDouble(i2);
        double d2 = cursor.getDouble(i3);
        if (d > 0.0d || d2 > 0.0d) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putDouble(PdLVBody.LONGITUDE, d);
            writableNativeMap.putDouble(PdLVBody.LATITUDE, d2);
            writableMap.putMap(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID, writableNativeMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void putPageInfo(Cursor cursor, WritableMap writableMap, int i2) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putBoolean("has_next_page", i2 < cursor.getCount());
        if (i2 < cursor.getCount()) {
            cursor.moveToPosition(i2 - 1);
            writableNativeMap.putString("end_cursor", cursor.getString(cursor.getColumnIndex("datetaken")));
        }
        writableMap.putMap("page_info", writableNativeMap);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void getPhotos(ReadableMap readableMap, Promise promise) {
        int i2 = readableMap.getInt("first");
        String string = readableMap.hasKey("after") ? readableMap.getString("after") : null;
        String string2 = readableMap.hasKey("groupName") ? readableMap.getString("groupName") : null;
        String string3 = readableMap.hasKey("assetType") ? readableMap.getString("assetType") : ASSET_TYPE_PHOTOS;
        ReadableArray array = readableMap.hasKey("mimeTypes") ? readableMap.getArray("mimeTypes") : null;
        if (!readableMap.hasKey("groupTypes")) {
            new GetMediaTask(getReactApplicationContext(), i2, string, string2, array, string3, promise).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return;
        }
        throw new JSApplicationIllegalArgumentException("groupTypes is not supported on Android");
    }

    @ReactMethod
    public void saveToCameraRoll(String str, String str2, Promise promise) {
        new SaveToCameraRoll(getReactApplicationContext(), Uri.parse(str), promise).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }
}
