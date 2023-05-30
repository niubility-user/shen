package com.jingdong.app.mall.safemode;

import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class c implements FilenameFilter {

    /* renamed from: g  reason: collision with root package name */
    List<File> f11611g;

    public c() {
        File parentFile = JdSdk.getInstance().getApplication().getFilesDir().getParentFile();
        File file = new File(parentFile.getAbsolutePath(), "code_cache");
        File file2 = new File(parentFile.getAbsolutePath(), "lib");
        File file3 = new File(JdSdk.getInstance().getApplication().getCacheDir() + "/safe_mode_fixing_flag" + CartConstant.KEY_YB_INFO_LINK + PackageInfoUtil.getVersionCode() + ".dat");
        File file4 = new File(JdSdk.getInstance().getApplication().getCacheDir() + "/safe_mode_cache" + CartConstant.KEY_YB_INFO_LINK + PackageInfoUtil.getVersionCode() + ".dat");
        ArrayList arrayList = new ArrayList(4);
        this.f11611g = arrayList;
        arrayList.add(file);
        this.f11611g.add(file2);
        this.f11611g.add(file3);
        this.f11611g.add(file4);
    }

    @Override // java.io.FilenameFilter
    public boolean accept(File file, String str) {
        List<File> list = this.f11611g;
        if (list == null || !list.contains(new File(file, str))) {
            return new File(file, str).exists();
        }
        return false;
    }
}
