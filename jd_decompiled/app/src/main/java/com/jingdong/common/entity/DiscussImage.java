package com.jingdong.common.entity;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.text.TextUtils;
import java.io.File;

/* loaded from: classes5.dex */
public class DiscussImage {
    static final long LIMIT_IMAGE_SIZE = 524288;
    private static final String TAG = "DiscussImage";
    private Uri path;
    private BitmapDrawable picture;
    public int rotate;

    public static DiscussImage createDiscussImage(Context context, Uri uri) {
        return createDiscussImage(context, uri, true);
    }

    private static int getFileSampleSize(String str) {
        if (TextUtils.isEmpty(str)) {
            return 4;
        }
        File file = new File(str);
        if (file.isFile() && file.exists()) {
            return (int) (file.length() / LIMIT_IMAGE_SIZE);
        }
        return 4;
    }

    public Uri getPath() {
        return this.path;
    }

    public BitmapDrawable getPicture() {
        return this.picture;
    }

    public void setPath(Uri uri) {
        this.path = uri;
    }

    public void setPicture(BitmapDrawable bitmapDrawable) {
        this.picture = bitmapDrawable;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0127 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0140 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:111:0x011b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0114 A[Catch: all -> 0x0157, TRY_LEAVE, TryCatch #11 {all -> 0x0157, blocks: (B:53:0x0110, B:55:0x0114, B:69:0x0135, B:71:0x0139), top: B:108:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0139 A[Catch: all -> 0x0157, TRY_LEAVE, TryCatch #11 {all -> 0x0157, blocks: (B:53:0x0110, B:55:0x0114, B:69:0x0135, B:71:0x0139), top: B:108:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x014c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r13v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r13v13 */
    /* JADX WARN: Type inference failed for: r13v15 */
    /* JADX WARN: Type inference failed for: r13v16 */
    /* JADX WARN: Type inference failed for: r13v3 */
    /* JADX WARN: Type inference failed for: r1v0, types: [android.content.ContentResolver, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v0, types: [boolean, java.io.InputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.jingdong.common.entity.DiscussImage createDiscussImage(android.content.Context r11, android.net.Uri r12, boolean r13) {
        /*
            Method dump skipped, instructions count: 369
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.entity.DiscussImage.createDiscussImage(android.content.Context, android.net.Uri, boolean):com.jingdong.common.entity.DiscussImage");
    }
}
