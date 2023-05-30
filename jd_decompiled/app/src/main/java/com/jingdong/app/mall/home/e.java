package com.jingdong.app.mall.home;

import com.jingdong.common.utils.text.OnTextScaleModeChangeListener;
import com.jingdong.common.utils.text.TextScaleModeHelper;
import com.jingdong.jdsdk.JdSdk;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes4.dex */
public class e implements OnTextScaleModeChangeListener {

    /* renamed from: g  reason: collision with root package name */
    private ArrayList<b> f9132g;

    /* loaded from: classes4.dex */
    public interface b {
        int getTextSize();

        void onTextScaleModeChanged(int i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class c {
        private static final e a = new e();
    }

    public static e b() {
        return c.a;
    }

    public void a(b bVar) {
        if (bVar == null || this.f9132g.contains(bVar)) {
            return;
        }
        this.f9132g.add(bVar);
    }

    public int c(int i2) {
        return ((int) TextScaleModeHelper.INSTANCE.getInstance().getScaleSize(JdSdk.getInstance().getApplicationContext(), i2 / 2)) * 2;
    }

    public void d() {
        this.f9132g.clear();
    }

    @Override // com.jingdong.common.utils.text.OnTextScaleModeChangeListener
    public void onTextScaleModeChanged() {
        b next;
        Iterator<b> it = this.f9132g.iterator();
        while (it.hasNext() && (next = it.next()) != null) {
            next.onTextScaleModeChanged(c(next.getTextSize()));
        }
    }

    private e() {
        this.f9132g = new ArrayList<>();
    }
}
