package com.jingdong.remoteimage;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.hybrid.downloader.i;
import com.jd.hybrid.downloader.j;
import com.jd.hybrid.downloader.m.b;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.jdsdk.constant.CartConstant;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class CalorieImageUtil {
    private static final String CALORIE = "calorie";
    private static final String EXT_NAME = "ext_name";
    private static final String GET_IMG_DOWNLOAD_FAILED = "2";
    private static final String GET_IMG_FAILED = "3";
    private static final String GET_IMG_LOCAL_SUCCESS = "1";
    private static final String TAG = "CalorieImageUtil";
    private static final String ZIP_CONFIG_DATA = "zipConfigData";
    private static final String ZIP_URL_PREFIX = "zip_url_prefix";
    private static List<String> extList;
    private static JDJSONArray zipJsonArray;
    private static final List<String> DEFAULT_EXT_LIST = Arrays.asList("png", "jpeg", WareBusinessMagicHeadPicInfoEntity.IMAGE_TYPE_GIF, "bmp", "avif", "webp", "jpg");
    private static final Handler sHandler = new Handler(Looper.getMainLooper());
    private static final Map<String, i> sObserverMap = new HashMap();
    private static final Map<String, List<CalorieImage>> sCallbackMap = new HashMap();

    /* loaded from: classes7.dex */
    private static class CalorieImage {
        public IRemoteImageUriCallback callback;
        public String imageId;
        public String moduleId;
        public boolean success;

        public CalorieImage(String str, String str2, IRemoteImageUriCallback iRemoteImageUriCallback) {
            this.moduleId = str;
            this.imageId = str2;
            this.callback = iRemoteImageUriCallback;
        }
    }

    /* loaded from: classes7.dex */
    public interface IRemoteImageUriCallback {
        void onRemoteImageUriCallback(Uri uri);
    }

    public static String getImageUrlSync(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(":");
        if (split.length < 2) {
            return null;
        }
        return getImageUrlSync(split[0], split[1]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Uri getLocalImageUri(String str, List<String> list) {
        if (list != null && !TextUtils.isEmpty(str)) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                File file = new File(str + OrderISVUtil.MONEY_DECIMAL + list.get(i2));
                if (file.exists()) {
                    return Uri.fromFile(file);
                }
            }
        }
        return null;
    }

    public static void getRemoteImageUriAsync(String str, String str2, IRemoteImageUriCallback iRemoteImageUriCallback) {
        IRemoteImageUriCallback iRemoteImageUriCallback2;
        IRemoteImageUriCallback iRemoteImageUriCallback3;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        Map<String, List<CalorieImage>> map = sCallbackMap;
        if (!map.containsKey(str)) {
            map.put(str, new ArrayList());
        }
        final List<CalorieImage> list = map.get(str);
        if (list != null) {
            list.add(new CalorieImage(str, str2, iRemoteImageUriCallback));
        }
        if (zipJsonArray == null) {
            if (list == null) {
                return;
            }
            for (CalorieImage calorieImage : list) {
                if (calorieImage != null && (iRemoteImageUriCallback3 = calorieImage.callback) != null) {
                    iRemoteImageUriCallback3.onRemoteImageUriCallback(getRemoteXjsUri(calorieImage.moduleId, calorieImage.imageId));
                }
            }
            return;
        }
        JDJSONObject jDJSONObject = null;
        int i2 = 0;
        while (true) {
            if (i2 >= zipJsonArray.size()) {
                break;
            }
            JDJSONObject optJSONObject = zipJsonArray.optJSONObject(i2);
            if (optJSONObject != null && TextUtils.equals(optJSONObject.optString("moduleId"), str)) {
                jDJSONObject = optJSONObject;
                break;
            }
            i2++;
        }
        if (jDJSONObject != null && !TextUtils.isEmpty(jDJSONObject.optString("nameSpace")) && !TextUtils.isEmpty(jDJSONObject.optString("fileId"))) {
            Map<String, i> map2 = sObserverMap;
            if (!map2.containsKey(str)) {
                map2.put(str, new i() { // from class: com.jingdong.remoteimage.CalorieImageUtil.1
                    @Override // com.jd.hybrid.downloader.i
                    public void update(final b bVar) {
                        CalorieImageUtil.runOnUiThread(new Runnable() { // from class: com.jingdong.remoteimage.CalorieImageUtil.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (list == null) {
                                    return;
                                }
                                b bVar2 = bVar;
                                if (bVar2 != null && bVar2.getStatus() == 1) {
                                    for (CalorieImage calorieImage2 : list) {
                                        if (calorieImage2 != null && calorieImage2.callback != null && !TextUtils.isEmpty(calorieImage2.imageId) && !calorieImage2.success) {
                                            String filePath = bVar.getFilePath();
                                            String str3 = "\u4e0b\u8f7d\u6210\u529f\uff0c\u6587\u4ef6\u8def\u5f84\uff1a" + filePath;
                                            Uri localImageUri = CalorieImageUtil.getLocalImageUri(filePath + File.separator + calorieImage2.imageId, CalorieImageUtil.extList != null ? CalorieImageUtil.extList : CalorieImageUtil.DEFAULT_EXT_LIST);
                                            if (localImageUri == null) {
                                                localImageUri = CalorieImageUtil.getRemoteXjsUri(calorieImage2.moduleId, calorieImage2.imageId);
                                                if (!TextUtils.isEmpty(localImageUri.getHost())) {
                                                    calorieImage2.success = true;
                                                }
                                            } else {
                                                calorieImage2.success = true;
                                                MtaService.calorieGetImgExp(calorieImage2.moduleId, calorieImage2.imageId, "1");
                                            }
                                            calorieImage2.callback.onRemoteImageUriCallback(localImageUri);
                                        }
                                    }
                                    return;
                                }
                                for (CalorieImage calorieImage3 : list) {
                                    if (calorieImage3 != null && calorieImage3.callback != null && !calorieImage3.success) {
                                        Uri remoteXjsUri = CalorieImageUtil.getRemoteXjsUri(calorieImage3.moduleId, calorieImage3.imageId);
                                        if (!TextUtils.isEmpty(remoteXjsUri.getHost())) {
                                            calorieImage3.success = true;
                                        }
                                        calorieImage3.callback.onRemoteImageUriCallback(remoteXjsUri);
                                    }
                                }
                            }
                        });
                    }
                });
            }
            j.l().h(jDJSONObject.optString("nameSpace"), jDJSONObject.optString("fileId"), map2.get(str));
        } else if (list != null) {
            for (CalorieImage calorieImage2 : list) {
                if (calorieImage2 != null && (iRemoteImageUriCallback2 = calorieImage2.callback) != null) {
                    iRemoteImageUriCallback2.onRemoteImageUriCallback(getRemoteXjsUri(calorieImage2.moduleId, calorieImage2.imageId));
                }
            }
        }
    }

    public static Uri getRemoteImageUriSync(String str) {
        if (TextUtils.isEmpty(str)) {
            return Uri.EMPTY;
        }
        String[] split = str.split(":");
        if (split.length < 2) {
            return Uri.EMPTY;
        }
        return getRemoteImageUriSync(split[0], split[1]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Uri getRemoteXjsUri(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            String imageUrlById = RemoteImageManager.getImageUrlById(str + CartConstant.KEY_YB_INFO_LINK + str2);
            if (TextUtils.isEmpty(imageUrlById)) {
                MtaService.calorieGetImgExp(str, str2, "3");
                return Uri.EMPTY;
            }
            MtaService.calorieGetImgExp(str, str2, "2");
            return Uri.parse(imageUrlById);
        }
        return Uri.EMPTY;
    }

    private static String getRemoteXjsUrl(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        String imageUrlById = RemoteImageManager.getImageUrlById(str + CartConstant.KEY_YB_INFO_LINK + str2);
        if (TextUtils.isEmpty(imageUrlById)) {
            MtaService.calorieGetImgExp(str, str2, "3");
            return null;
        }
        MtaService.calorieGetImgExp(str, str2, "2");
        return imageUrlById;
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static void loadXCacheConfig(JDJSONObject jDJSONObject) {
        String str;
        String str2;
        String str3;
        int i2;
        String str4 = "project_priority";
        if (jDJSONObject == null) {
            return;
        }
        JDJSONArray optJSONArray = jDJSONObject.optJSONArray(ZIP_CONFIG_DATA);
        zipJsonArray = optJSONArray;
        if (optJSONArray == null) {
            return;
        }
        try {
            extList = JDJSON.parseArray(jDJSONObject.optString(EXT_NAME), String.class);
            String optString = jDJSONObject.optString(ZIP_URL_PREFIX);
            JSONArray jSONArray = new JSONArray();
            boolean isEmpty = zipJsonArray.isEmpty();
            String str5 = CALORIE;
            String str6 = "nameSpace";
            if (isEmpty) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("nameSpace", CALORIE);
                jSONObject.put("files", new JDJSONArray());
                jSONArray.put(jSONObject);
            }
            int i3 = 0;
            while (i3 < zipJsonArray.size()) {
                JDJSONObject optJSONObject = zipJsonArray.optJSONObject(i3);
                if (optJSONObject == null) {
                    str2 = str4;
                    str3 = str5;
                    str = str6;
                    i2 = i3;
                } else {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(str6, optJSONObject.optString(str6));
                    JSONArray jSONArray2 = new JSONArray();
                    JSONObject jSONObject3 = new JSONObject();
                    str = str6;
                    jSONObject3.put(str4, optJSONObject.optInt(str4));
                    JSONArray jSONArray3 = new JSONArray();
                    str2 = str4;
                    JDJSONArray optJSONArray2 = optJSONObject.optJSONArray("policy");
                    str3 = str5;
                    i2 = i3;
                    if (optJSONArray2 != null) {
                        for (int i4 = 0; i4 < optJSONArray2.size(); i4++) {
                            String string = optJSONArray2.getString(i4);
                            if (!TextUtils.isEmpty(string)) {
                                jSONArray3.put(string);
                            }
                        }
                    }
                    jSONObject3.put("demand_classes", jSONArray3);
                    jSONObject3.put("url", optString + optJSONObject.optString("zip_name"));
                    jSONObject3.put("fileId", optJSONObject.optString("fileId"));
                    jSONObject3.put("md5", optJSONObject.optString("file_md5"));
                    jSONObject3.put("app_min", optJSONObject.optString("app_min"));
                    jSONObject3.put("app_max", optJSONObject.optString("app_max"));
                    jSONObject3.put("version_code", optJSONObject.optInt("version_code"));
                    jSONObject3.put("file_type", optJSONObject.optString("file_type"));
                    jSONArray2.put(jSONObject3);
                    jSONObject2.put("files", jSONArray2);
                    jSONArray.put(jSONObject2);
                }
                i3 = i2 + 1;
                str6 = str;
                str4 = str2;
                str5 = str3;
            }
            j.l().p(str5, jSONArray);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void preDownLoadModuleZip(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        j.l().q(str);
    }

    public static void runOnUiThread(Runnable runnable) {
        if (isMainThread()) {
            runnable.run();
        } else {
            sHandler.post(runnable);
        }
    }

    public static String getImageUrlSync(String str, String str2) {
        JDJSONObject jDJSONObject = null;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        if (zipJsonArray == null) {
            return getRemoteXjsUrl(str, str2);
        }
        int i2 = 0;
        while (true) {
            if (i2 >= zipJsonArray.size()) {
                break;
            }
            JDJSONObject optJSONObject = zipJsonArray.optJSONObject(i2);
            if (optJSONObject != null && TextUtils.equals(optJSONObject.optString("moduleId"), str)) {
                jDJSONObject = optJSONObject;
                break;
            }
            i2++;
        }
        if (jDJSONObject != null && !TextUtils.isEmpty(jDJSONObject.optString("nameSpace")) && !TextUtils.isEmpty(jDJSONObject.optString("fileId"))) {
            b k2 = j.l().k(jDJSONObject.optString("nameSpace"), jDJSONObject.optString("fileId"));
            if (k2 == null) {
                return getRemoteXjsUrl(str, str2);
            }
            String str3 = k2.getFilePath() + File.separator + str2;
            List<String> list = extList;
            if (list == null) {
                list = DEFAULT_EXT_LIST;
            }
            Uri localImageUri = getLocalImageUri(str3, list);
            if (localImageUri != null) {
                MtaService.calorieGetImgExp(str, str2, "1");
            }
            return localImageUri != null ? localImageUri.toString() : getRemoteXjsUrl(str, str2);
        }
        return getRemoteXjsUrl(str, str2);
    }

    public static Uri getRemoteImageUriSync(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            if (zipJsonArray == null) {
                return getRemoteXjsUri(str, str2);
            }
            JDJSONObject jDJSONObject = null;
            int i2 = 0;
            while (true) {
                if (i2 >= zipJsonArray.size()) {
                    break;
                }
                JDJSONObject optJSONObject = zipJsonArray.optJSONObject(i2);
                if (optJSONObject != null && TextUtils.equals(optJSONObject.optString("moduleId"), str)) {
                    jDJSONObject = optJSONObject;
                    break;
                }
                i2++;
            }
            if (jDJSONObject != null && !TextUtils.isEmpty(jDJSONObject.optString("nameSpace")) && !TextUtils.isEmpty(jDJSONObject.optString("fileId"))) {
                b k2 = j.l().k(jDJSONObject.optString("nameSpace"), jDJSONObject.optString("fileId"));
                if (k2 == null) {
                    return getRemoteXjsUri(str, str2);
                }
                String str3 = k2.getFilePath() + File.separator + str2;
                List<String> list = extList;
                if (list == null) {
                    list = DEFAULT_EXT_LIST;
                }
                Uri localImageUri = getLocalImageUri(str3, list);
                if (localImageUri != null) {
                    MtaService.calorieGetImgExp(str, str2, "1");
                }
                return localImageUri != null ? localImageUri : getRemoteXjsUri(str, str2);
            }
            return getRemoteXjsUri(str, str2);
        }
        return Uri.EMPTY;
    }
}
