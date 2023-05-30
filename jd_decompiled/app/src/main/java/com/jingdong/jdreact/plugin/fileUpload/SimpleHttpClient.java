package com.jingdong.jdreact.plugin.fileUpload;

import android.content.Context;
import android.os.Build;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes13.dex */
public class SimpleHttpClient {
    private static String Enter = "\r\n";
    public static int POOL_SIZE = 8;
    private static final String TAG = "SimpleHttpClient";
    private static ExecutorService sExecutorService = Executors.newFixedThreadPool(8);

    /* loaded from: classes13.dex */
    public interface HttpCallback<T> {
        void onError(T t);

        void onSuccess(T t);
    }

    public static int checkSelfPermission(Context context, String str) {
        return context.checkCallingOrSelfPermission(str);
    }

    public static void doGet(final String str, final String str2, final String str3, final String str4, final String str5, final HttpCallback<String> httpCallback) {
        sExecutorService.submit(new Runnable() { // from class: com.jingdong.jdreact.plugin.fileUpload.SimpleHttpClient.1
            /* JADX WARN: Code restructure failed: missing block: B:49:0x01e8, code lost:
                if (r2 != null) goto L50;
             */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    Method dump skipped, instructions count: 761
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdreact.plugin.fileUpload.SimpleHttpClient.AnonymousClass1.run():void");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean hasGrantedPermission(Context context) {
        return Build.VERSION.SDK_INT <= 28 || checkSelfPermission(context, "android.permission.READ_MEDIA_IMAGES") == 0;
    }
}
