package com.jd.lib.flexcube.pool;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.flexcube.R;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes15.dex */
public class WidgetPool extends ViewModel {
    private Map<String, b> a = new HashMap();

    public static View a(String str, Context context) {
        if (context instanceof FragmentActivity) {
            return ((WidgetPool) ViewModelProviders.of((FragmentActivity) context).get(WidgetPool.class)).c(str, context);
        }
        return d(str, context);
    }

    private View b(String str) {
        b bVar = this.a.get(str);
        if (bVar == null) {
            return null;
        }
        return bVar.c();
    }

    public static View d(String str, Context context) {
        com.jd.lib.flexcube.iwidget.a.a a = a.b().a(str);
        if (a == null) {
            return null;
        }
        return a.getView(context);
    }

    public static void e(View view) {
        if (view.getContext() instanceof FragmentActivity) {
            ((WidgetPool) ViewModelProviders.of((FragmentActivity) view.getContext()).get(WidgetPool.class)).f(view);
        }
    }

    public View c(String str, Context context) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        View b = b(str);
        if (b != null) {
            return b;
        }
        com.jd.lib.flexcube.iwidget.a.a a = a.b().a(str);
        if (a == null) {
            return null;
        }
        return a.getView(context);
    }

    public void f(View view) {
        String valueOf = String.valueOf(view.getTag(R.id.type));
        com.jd.lib.flexcube.iwidget.a.a a = a.b().a(valueOf);
        if (a == null || !a.useCache()) {
            return;
        }
        b bVar = this.a.get(valueOf);
        if (bVar == null) {
            bVar = new b();
            this.a.put(valueOf, bVar);
        }
        bVar.a(view);
    }

    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        Iterator<b> it = this.a.values().iterator();
        while (it.hasNext()) {
            it.next().b();
        }
        this.a.clear();
    }
}
