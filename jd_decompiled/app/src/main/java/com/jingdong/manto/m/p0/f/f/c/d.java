package com.jingdong.manto.m.p0.f.f.c;

import android.media.AudioRecord;

/* loaded from: classes15.dex */
final class d {
    private final int a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(c cVar) {
        this.a = AudioRecord.getMinBufferSize(cVar.e(), cVar.a(), cVar.d());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a() {
        return this.a;
    }
}
