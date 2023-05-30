package com.jingdong.moutaibuy.lib.workflow;

import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.moutaibuy.lib.R;
import java.util.Iterator;

/* loaded from: classes16.dex */
public class c {
    public int a;
    public int b;

    /* renamed from: c  reason: collision with root package name */
    public int f14643c;
    public int d;

    /* renamed from: e  reason: collision with root package name */
    public String f14644e;

    public c() {
        this(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a() {
        Iterator<String> it = a.a.iterator();
        while (it.hasNext()) {
            JDImageUtils.loadImageToDiskCache(it.next(), null);
        }
        JDImageUtils.loadImageToDiskCache("http://m.360buyimg.com/mobilecal/jfs/t1/138465/3/24673/97727/619f2087E0da99579/61aef946d3b830fb.png", null);
    }

    public void b(int i2) {
        this.a = i2;
        this.f14644e = a.a.get(i2);
        switch (i2) {
            case 0:
                this.b = R.string.step_one_tip_text;
                this.d = R.drawable.scan1;
                this.f14643c = R.string.step_one_content;
                return;
            case 1:
                this.b = R.string.step_two_tip_text;
                this.d = R.drawable.scan2;
                this.f14643c = R.string.step_two_content;
                return;
            case 2:
                this.b = R.string.step_three_tip_text;
                this.d = -1;
                this.f14643c = R.string.step_three_content;
                return;
            case 3:
                this.b = R.string.step_four_tip_text;
                this.d = -1;
                this.f14643c = R.string.step_four_content;
                return;
            case 4:
                this.b = R.string.step_five_tip_text;
                this.d = R.drawable.scan5;
                this.f14643c = R.string.step_five_content;
                return;
            case 5:
                this.b = R.string.step_six_tip_text;
                this.d = R.drawable.scan6;
                this.f14643c = R.string.step_six_content;
                return;
            case 6:
                this.b = R.string.step_seven_tip_text;
                this.d = -1;
                this.f14643c = R.string.step_seven_content;
                return;
            case 7:
                this.b = R.string.step_eight_tip_text;
                this.d = R.drawable.scan8;
                this.f14643c = R.string.step_eight_content;
                return;
            default:
                return;
        }
    }

    public c(int i2) {
        b(i2);
    }
}
