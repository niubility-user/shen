package com.jingdong.moutaibuy.lib.view;

import android.content.Context;
import android.util.AttributeSet;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.jingdong.moutaibuy.lib.i.d;
import java.util.Map;

/* loaded from: classes16.dex */
public class MouTaiScanView extends ScanView {
    private Map<DecodeHintType, Object> t;

    public MouTaiScanView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // com.jingdong.moutaibuy.lib.view.ScanView
    protected void j() {
        MultiFormatReader multiFormatReader = new MultiFormatReader();
        this.q = multiFormatReader;
        d dVar = this.p;
        if (dVar == d.ONE_DIMENSION) {
            multiFormatReader.setHints(com.jingdong.moutaibuy.lib.d.a.b);
        } else if (dVar == d.TWO_DIMENSION) {
            multiFormatReader.setHints(com.jingdong.moutaibuy.lib.d.a.f14586c);
        } else if (dVar == d.ONLY_QR_CODE) {
            multiFormatReader.setHints(com.jingdong.moutaibuy.lib.d.a.d);
        } else if (dVar == d.ONLY_CODE_128) {
            multiFormatReader.setHints(com.jingdong.moutaibuy.lib.d.a.f14587e);
        } else if (dVar == d.ONLY_EAN_13) {
            multiFormatReader.setHints(com.jingdong.moutaibuy.lib.d.a.f14588f);
        } else if (dVar == d.HIGH_FREQUENCY) {
            multiFormatReader.setHints(com.jingdong.moutaibuy.lib.d.a.f14589g);
        } else if (dVar == d.CUSTOM) {
            multiFormatReader.setHints(this.t);
        } else {
            multiFormatReader.setHints(com.jingdong.moutaibuy.lib.d.a.a);
        }
    }

    public void s(d dVar, Map<DecodeHintType, Object> map) {
        this.p = dVar;
        this.t = map;
        if (dVar == d.CUSTOM && (map == null || map.isEmpty())) {
            throw new RuntimeException("type \u4e3a Type.CUSTOM \u65f6 hintMap \u4e0d\u80fd\u4e3a\u7a7a");
        }
        j();
    }

    public MouTaiScanView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
