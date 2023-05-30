package com.jingdong.app.mall.home.shakeandshow;

import android.text.TextUtils;
import com.jingdong.app.mall.home.o.a.o;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.FileUtils;
import com.jingdong.jdsdk.network.toolbox.FileGuider;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import java.io.File;

/* loaded from: classes4.dex */
public class h {
    private static String a = "";

    /* loaded from: classes4.dex */
    class a implements HttpGroup.OnCommonListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f10863g;

        a(String str) {
            this.f10863g = str;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            FileService.Directory directory;
            if (httpResponse == null || TextUtils.isEmpty(h.a) || !this.f10863g.contains(h.a)) {
                return;
            }
            File saveFile = httpResponse.getSaveFile();
            if (o.b(saveFile) && (directory = FileService.getDirectory(1)) != null) {
                String str = directory.getPath() + this.f10863g;
                FileUtils.saveToFile(str, saveFile.getAbsolutePath());
                com.jingdong.app.mall.home.o.a.f.A0("home_shake_loading_img", str);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    private static void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        new File(str).deleteOnExit();
        com.jingdong.app.mall.home.o.a.f.A0("home_shake_loading_img", "");
        a = "";
    }

    public static void c(String str) {
        String O = com.jingdong.app.mall.home.o.a.f.O("home_shake_loading_img", "");
        if (TextUtils.isEmpty(str)) {
            b(O);
            return;
        }
        String p = com.jingdong.app.mall.home.o.a.f.p(str);
        String md5 = Md5Encrypt.md5(p);
        String str2 = File.separator + "shake_loading_img_" + md5;
        if (O.contains(str2) && new File(O).exists()) {
            a = md5;
            return;
        }
        b(O);
        a = md5;
        FileGuider fileGuider = new FileGuider();
        fileGuider.setSpace(1);
        fileGuider.setImmutable(false);
        fileGuider.setFileName(str2);
        HttpSetting a2 = com.jingdong.app.mall.home.base.a.a.a(p);
        a2.setSavePath(fileGuider);
        a2.setLocalFileCache(true);
        a2.setOnTouchEvent(true);
        a2.setType(5000);
        a2.setListener(new a(str2));
        HttpGroupUtils.getHttpGroupaAsynPool().add(a2);
    }

    public static String d() {
        String O = com.jingdong.app.mall.home.o.a.f.O("home_shake_loading_img", "");
        if (!TextUtils.isEmpty(O) && !TextUtils.isEmpty(a) && O.contains(a)) {
            String a2 = com.jingdong.app.mall.home.m.a.a(new File(O));
            if (!TextUtils.isEmpty(a2)) {
                return a2;
            }
        }
        b(O);
        return "";
    }
}
