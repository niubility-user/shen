package com.jingdong.manto.widget.input;

import android.os.Looper;
import android.text.Editable;
import android.text.SpanWatcher;
import android.text.Spannable;
import android.widget.EditText;
import com.jingdong.manto.sdk.thread.MantoHandler;
import com.jingdong.manto.utils.MantoLog;

/* loaded from: classes16.dex */
public final class w {
    public com.jingdong.manto.widget.input.z.a b;

    /* renamed from: c */
    final Runnable f14493c = new a();
    final MantoHandler a = new MantoHandler(Looper.getMainLooper());

    /* loaded from: classes16.dex */
    public class a implements Runnable {
        a() {
            w.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (w.this.b != null) {
                MantoLog.d("input", String.format(" onComposingDismissed ", new Object[0]));
                w.this.b.a();
            }
        }
    }

    /* loaded from: classes16.dex */
    public class b implements SpanWatcher {
        b() {
            w.this = r1;
        }

        @Override // android.text.SpanWatcher
        public final void onSpanAdded(Spannable spannable, Object obj, int i2, int i3) {
            if (InputUtil.isInstanceOfComposingText(obj)) {
                MantoLog.d("input", String.format(" onSpanAdded %s", spannable));
            }
        }

        @Override // android.text.SpanWatcher
        public final void onSpanChanged(Spannable spannable, Object obj, int i2, int i3, int i4, int i5) {
            if (InputUtil.isInstanceOfComposingText(obj)) {
                MantoLog.d("input", String.format(" onSpanChanged %s", spannable));
            }
        }

        @Override // android.text.SpanWatcher
        public final void onSpanRemoved(Spannable spannable, Object obj, int i2, int i3) {
            if (InputUtil.isInstanceOfComposingText(obj)) {
                MantoLog.d("input", String.format(" onSpanRemoved %s", spannable));
                w wVar = w.this;
                wVar.a.b(wVar.f14493c);
                w wVar2 = w.this;
                wVar2.a.a(wVar2.f14493c, 100L);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class c extends com.jingdong.manto.ui.e {
        c() {
            w.this = r1;
        }

        @Override // com.jingdong.manto.ui.e, android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            w wVar = w.this;
            wVar.a.b(wVar.f14493c);
        }
    }

    public w(EditText editText) {
    }

    public final Editable a(Editable editable) {
        editable.setSpan(new b(), 0, editable.length(), 18);
        editable.setSpan(new c(), 0, editable.length(), 18);
        return editable;
    }
}
