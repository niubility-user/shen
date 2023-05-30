package com.jd.skin.lib.controller;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.jd.skin.lib.JDSkinSDK;
import com.jd.skin.lib.Utils.ConstancesUtils;
import com.jd.skin.lib.Utils.FileUtils;
import com.jd.skin.lib.Utils.JDSkinSharedPreferencesUtils;
import com.jd.skin.lib.bean.ResourceItems;
import com.jd.skin.lib.db.JDSkinDBController;
import com.jd.skin.lib.db.JDSkinRunnable;
import com.jd.skin.lib.db.ResultCallback;
import com.jd.skin.lib.inter.OnDownloadPicListener;
import com.jd.skin.lib.inter.OnSkinElementsChangeListener;
import com.jd.skin.lib.manager.DownloadManager;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes18.dex */
public class JDSkinDataController implements OnDownloadPicListener {
    private static JDSkinDataController mJDSkinDataController = new JDSkinDataController();
    private String mSkinId;
    private String mUpdateTime;
    private List<OnSkinElementsChangeListener> skinElementsChangeListeners;
    private int downloadCount = 0;
    private int needDownloadCount = 0;
    Map<String, ResourceItems> resourceItemsMap = new HashMap();
    private Handler mainHandler = new Handler(Looper.getMainLooper());
    private List<File> mDeletPic = new CopyOnWriteArrayList();
    private List<ResourceItems> mNeedsDownResList = new CopyOnWriteArrayList();
    private ExecutorService mSingleThreadExecutor = Executors.newSingleThreadExecutor();

    private JDSkinDataController() {
    }

    public static JDSkinDataController getInstance() {
        return mJDSkinDataController;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyUpdateUI() {
        if (OKLog.D) {
            OKLog.d("JDSkinSDK", "JDSkinSDK---->--2222");
        }
        Handler handler = this.mainHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.jd.skin.lib.controller.JDSkinDataController.2
                @Override // java.lang.Runnable
                public void run() {
                    if (JDSkinDataController.this.skinElementsChangeListeners != null && !JDSkinDataController.this.skinElementsChangeListeners.isEmpty()) {
                        if (OKLog.D) {
                            OKLog.d("JDSkinSDK", "JDSkinSDK---->--6666");
                        }
                        for (OnSkinElementsChangeListener onSkinElementsChangeListener : JDSkinDataController.this.skinElementsChangeListeners) {
                            if (onSkinElementsChangeListener != null) {
                                if (OKLog.D) {
                                    OKLog.d("JDSkinSDK", "JDSkinSDK---->--4444");
                                }
                                onSkinElementsChangeListener.skinChange();
                            }
                        }
                    } else if (OKLog.D) {
                        OKLog.d("JDSkinSDK", "JDSkinSDK---->--5555");
                    }
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x015d A[Catch: JSONException -> 0x01eb, TryCatch #0 {JSONException -> 0x01eb, blocks: (B:3:0x0006, B:5:0x001b, B:8:0x0023, B:10:0x0029, B:13:0x00c3, B:15:0x00c9, B:17:0x00cf, B:19:0x00e4, B:25:0x0113, B:27:0x0119, B:29:0x011f, B:31:0x0134, B:34:0x0140, B:39:0x0164, B:41:0x016a, B:43:0x0170, B:45:0x0185, B:49:0x0192, B:54:0x01b7, B:55:0x01bc, B:50:0x019b, B:52:0x01b0, B:35:0x0148, B:37:0x015d, B:20:0x00f6, B:22:0x010b, B:56:0x01c5, B:61:0x01e8, B:57:0x01c9, B:59:0x01cd, B:60:0x01e5), top: B:66:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01b0 A[Catch: JSONException -> 0x01eb, TryCatch #0 {JSONException -> 0x01eb, blocks: (B:3:0x0006, B:5:0x001b, B:8:0x0023, B:10:0x0029, B:13:0x00c3, B:15:0x00c9, B:17:0x00cf, B:19:0x00e4, B:25:0x0113, B:27:0x0119, B:29:0x011f, B:31:0x0134, B:34:0x0140, B:39:0x0164, B:41:0x016a, B:43:0x0170, B:45:0x0185, B:49:0x0192, B:54:0x01b7, B:55:0x01bc, B:50:0x019b, B:52:0x01b0, B:35:0x0148, B:37:0x015d, B:20:0x00f6, B:22:0x010b, B:56:0x01c5, B:61:0x01e8, B:57:0x01c9, B:59:0x01cd, B:60:0x01e5), top: B:66:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01b7 A[Catch: JSONException -> 0x01eb, TryCatch #0 {JSONException -> 0x01eb, blocks: (B:3:0x0006, B:5:0x001b, B:8:0x0023, B:10:0x0029, B:13:0x00c3, B:15:0x00c9, B:17:0x00cf, B:19:0x00e4, B:25:0x0113, B:27:0x0119, B:29:0x011f, B:31:0x0134, B:34:0x0140, B:39:0x0164, B:41:0x016a, B:43:0x0170, B:45:0x0185, B:49:0x0192, B:54:0x01b7, B:55:0x01bc, B:50:0x019b, B:52:0x01b0, B:35:0x0148, B:37:0x015d, B:20:0x00f6, B:22:0x010b, B:56:0x01c5, B:61:0x01e8, B:57:0x01c9, B:59:0x01cd, B:60:0x01e5), top: B:66:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01bc A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.Map<java.lang.String, com.jd.skin.lib.bean.ResourceItems> parseData(java.lang.String r15) {
        /*
            Method dump skipped, instructions count: 498
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.skin.lib.controller.JDSkinDataController.parseData(java.lang.String):java.util.Map");
    }

    private synchronized void saveDataInDatabase() {
        this.mSingleThreadExecutor.execute(new JDSkinRunnable(this.resourceItemsMap, this.mDeletPic, new ResultCallback() { // from class: com.jd.skin.lib.controller.JDSkinDataController.1
            @Override // com.jd.skin.lib.db.ResultCallback
            public void result(boolean z, ResourceItems resourceItems) {
                if (OKLog.D) {
                    OKLog.d("JDSkinSDK", "JDSkinSDK---->--" + z);
                }
                if (z) {
                    if (OKLog.D) {
                        OKLog.d("JDSkinSDK", "JDSkinSDK---->--0000");
                    }
                    JDSkinDataController.this.saveSkinUpdateTime();
                    JDSkinSDK.getInstance().clearLruCache();
                    if (JDSkinDataController.this.mNeedsDownResList != null && JDSkinDataController.this.mNeedsDownResList.size() > 0) {
                        JDSkinDataController.this.mNeedsDownResList.clear();
                    }
                    Map<String, ResourceItems> map = JDSkinDataController.this.resourceItemsMap;
                    if (map != null && map.size() > 0) {
                        JDSkinDataController.this.resourceItemsMap.clear();
                    }
                    if (OKLog.D) {
                        OKLog.d("JDSkinSDK", "JDSkinSDK---->--1111");
                    }
                    JDSkinDataController.this.notifyUpdateUI();
                }
            }
        }));
    }

    public ExecutorService getDataBaseDealExecutorService() {
        return this.mSingleThreadExecutor;
    }

    public Handler getMainHandler() {
        return this.mainHandler;
    }

    @Override // com.jd.skin.lib.inter.OnDownloadPicListener
    public void onDownloadComplet(boolean z, String str, String str2, String str3, String str4) {
        synchronized (JDSkinDataController.class) {
            if (z) {
                this.downloadCount++;
                if (this.resourceItemsMap.containsKey(str)) {
                    if (str4 != null && "bgImage".equals(str4)) {
                        this.resourceItemsMap.get(str).setLocalBgImage(str3);
                    } else if (str4 != null && "bgImageSelected".equals(str4)) {
                        this.resourceItemsMap.get(str).setLocalgImageSelected(str3);
                    } else if (str4 != null && "resource".equals(str4)) {
                        this.resourceItemsMap.get(str).setLocalResource(str3);
                    }
                    if (OKLog.D) {
                        OKLog.d("JDSkinSDK", "JDSkinSDK---->" + this.needDownloadCount + "--" + this.downloadCount);
                    }
                    if (this.needDownloadCount == this.downloadCount) {
                        saveDataInDatabase();
                    }
                }
            }
        }
    }

    public void readyDown() {
        List<ResourceItems> list = this.mNeedsDownResList;
        if (list != null && list.size() > 0) {
            for (ResourceItems resourceItems : this.mNeedsDownResList) {
                String code = resourceItems.getCode();
                String bgImage = resourceItems.getBgImage();
                String bgImageSelected = resourceItems.getBgImageSelected();
                String localBgImage = resourceItems.getLocalBgImage();
                String resource = resourceItems.getResource();
                if (!TextUtils.isEmpty(localBgImage)) {
                    File file = new File(localBgImage);
                    if (bgImage != null && bgImage.startsWith("http") && !file.exists()) {
                        List<File> readLocalFile = FileUtils.readLocalFile(code + "bgImage");
                        if (readLocalFile != null) {
                            this.mDeletPic.addAll(readLocalFile);
                        }
                        this.needDownloadCount++;
                        DownloadManager.getInstance().downloadSingle(code, resourceItems.getBgImage(), resourceItems.getLocalBgImage(), this, "bgImage", resourceItems.getBgImageMd5(), false);
                    }
                }
                String localgImageSelected = resourceItems.getLocalgImageSelected();
                if (!TextUtils.isEmpty(localgImageSelected)) {
                    File file2 = new File(localgImageSelected);
                    if (bgImageSelected != null && bgImageSelected.startsWith("http") && !file2.exists()) {
                        List<File> readLocalFile2 = FileUtils.readLocalFile(code + "bgImageSelected");
                        if (readLocalFile2 != null) {
                            this.mDeletPic.addAll(readLocalFile2);
                        }
                        this.needDownloadCount++;
                        DownloadManager.getInstance().downloadSingle(code, resourceItems.getBgImageSelected(), resourceItems.getLocalgImageSelected(), this, "bgImageSelected", resourceItems.getBgImageSelectedMd5(), false);
                    }
                }
                String localResource = resourceItems.getLocalResource();
                if (!TextUtils.isEmpty(localResource)) {
                    File file3 = new File(localResource);
                    if (resource != null && resource.startsWith("http") && !file3.exists()) {
                        List<File> readLocalFile3 = FileUtils.readLocalFile(code + "resource");
                        if (readLocalFile3 != null) {
                            this.mDeletPic.addAll(readLocalFile3);
                        }
                        this.needDownloadCount++;
                        DownloadManager.getInstance().downloadSingle(code, resourceItems.getResource(), resourceItems.getLocalResource(), this, "resource", resourceItems.getResourceMd5(), true);
                    }
                }
            }
            return;
        }
        saveDataInDatabase();
    }

    public void removeSkinElementsChangeListener(OnSkinElementsChangeListener onSkinElementsChangeListener) {
        List<OnSkinElementsChangeListener> list = this.skinElementsChangeListeners;
        if (list == null || !list.contains(onSkinElementsChangeListener)) {
            return;
        }
        this.skinElementsChangeListeners.remove(onSkinElementsChangeListener);
    }

    public void reset() {
        JDSkinSDK.getInstance().clearLruCache();
        JDSkinDBController.deleteDatas();
        notifyUpdateUI();
    }

    public void saveData(boolean z, String str) {
        if (!TextUtils.isEmpty(str) && z) {
            this.mDeletPic.clear();
            this.needDownloadCount = 0;
            this.downloadCount = 0;
            this.mNeedsDownResList.clear();
            this.resourceItemsMap.clear();
            parseData(str);
        }
    }

    public void saveSkinId() {
        String str = this.mSkinId;
        if (str == null || "".equals(str)) {
            return;
        }
        JDSkinSharedPreferencesUtils.putString(JDSkinSDK.getInstance().getContext(), ConstancesUtils.SP_CURRENT_SKIN_ID, this.mSkinId);
    }

    public void saveSkinUpdateTime() {
        String str = this.mUpdateTime;
        if (str == null || "".equals(str)) {
            return;
        }
        JDSkinSharedPreferencesUtils.putString(JDSkinSDK.getInstance().getContext(), ConstancesUtils.SP_CURRENT_SKIN_UPDATETIME, this.mUpdateTime);
    }

    public void setOnSkinElementsChangeListener(OnSkinElementsChangeListener onSkinElementsChangeListener) {
        if (this.skinElementsChangeListeners == null) {
            this.skinElementsChangeListeners = new ArrayList();
        }
        this.skinElementsChangeListeners.add(onSkinElementsChangeListener);
    }
}
