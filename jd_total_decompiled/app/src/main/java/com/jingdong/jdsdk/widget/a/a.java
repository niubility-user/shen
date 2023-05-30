package com.jingdong.jdsdk.widget.a;

import android.app.Application;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/* loaded from: classes14.dex */
public class a extends Toast {
    private TextView a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(Application application) {
        super(application);
    }

    private static TextView a(ViewGroup viewGroup) {
        TextView a;
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt instanceof TextView) {
                return (TextView) childAt;
            }
            if ((childAt instanceof ViewGroup) && (a = a((ViewGroup) childAt)) != null) {
                return a;
            }
        }
        return null;
    }

    private static TextView b(View view) {
        TextView a;
        if (view instanceof TextView) {
            return (TextView) view;
        }
        if (view.findViewById(16908299) instanceof TextView) {
            return (TextView) view.findViewById(16908299);
        }
        if (!(view instanceof ViewGroup) || (a = a((ViewGroup) view)) == null) {
            throw new IllegalArgumentException("The layout must contain a TextView");
        }
        return a;
    }

    @Override // android.widget.Toast
    public void setText(CharSequence charSequence) {
        this.a.setText(charSequence);
    }

    @Override // android.widget.Toast
    public void setView(View view) {
        super.setView(view);
        this.a = b(view);
    }
}
