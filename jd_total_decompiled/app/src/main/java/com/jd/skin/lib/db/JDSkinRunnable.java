package com.jd.skin.lib.db;

import com.jd.skin.lib.Utils.FileUtils;
import com.jd.skin.lib.bean.ResourceItems;
import java.io.File;
import java.util.List;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDSkinRunnable implements Runnable {
    private ResultCallback mCallback;
    private List<File> mDeletPics;
    private Map<String, ResourceItems> mResourceItemsMa;

    public JDSkinRunnable(Map<String, ResourceItems> map, List<File> list, ResultCallback resultCallback) {
        this.mCallback = resultCallback;
        this.mDeletPics = list;
        this.mResourceItemsMa = map;
    }

    @Override // java.lang.Runnable
    public void run() {
        JDSkinDBController.deleteDatas();
        FileUtils.deletePics(this.mDeletPics);
        boolean insertDatas = JDSkinDBController.insertDatas(this.mResourceItemsMa);
        ResultCallback resultCallback = this.mCallback;
        if (resultCallback != null) {
            resultCallback.result(insertDatas, null);
        }
    }
}
