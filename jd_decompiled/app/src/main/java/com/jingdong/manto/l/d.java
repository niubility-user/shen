package com.jingdong.manto.l;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.LruCache;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

/* loaded from: classes15.dex */
public class d implements l {
    final LruCache<String, Reference<Bitmap>> a = new LruCache<>(31457280);

    @Override // com.jingdong.manto.l.l
    public final Bitmap a(String str) {
        Reference<Bitmap> reference;
        if (TextUtils.isEmpty(str) || (reference = this.a.get(str)) == null) {
            return null;
        }
        Bitmap bitmap = reference.get();
        if (bitmap == null || bitmap.isRecycled()) {
            this.a.remove(str);
            return null;
        }
        return bitmap;
    }

    @Override // com.jingdong.manto.l.l
    public final void a(Bitmap bitmap) {
        if (bitmap != null) {
            bitmap.isRecycled();
        }
    }

    @Override // com.jingdong.manto.l.l
    public final void a(String str, Bitmap bitmap) {
        if (TextUtils.isEmpty(str) || bitmap == null) {
            return;
        }
        this.a.put(str, new SoftReference(bitmap));
    }
}
