package com.jingdong.manto.widget.input.z;

import android.content.Context;
import android.text.TextWatcher;
import android.view.View;
import com.jingdong.manto.q.n;

/* loaded from: classes16.dex */
public interface d {

    /* loaded from: classes16.dex */
    public interface a {
        void a();
    }

    /* loaded from: classes16.dex */
    public interface b {
        boolean a(int i2);
    }

    /* loaded from: classes16.dex */
    public interface c {
        void a();
    }

    int a(int i2);

    void a(View.OnFocusChangeListener onFocusChangeListener);

    void a(n nVar);

    void a(a aVar);

    void a(c cVar);

    boolean a();

    void addTextChangedListener(TextWatcher textWatcher);

    void b();

    void b(View.OnFocusChangeListener onFocusChangeListener);

    void b(n nVar);

    void c();

    void d();

    void destroy();

    boolean e();

    Context getContext();

    int getInputId();

    char getLastKeyPressed();

    CharSequence getText();

    View getView();

    void setFixed(boolean z);

    void setInputId(int i2);
}
