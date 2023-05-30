package com.jingdong.manto.widget.k;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import com.jingdong.manto.R;

/* loaded from: classes16.dex */
public abstract class a<T> {
    private InterfaceC0701a<T> a;

    /* renamed from: com.jingdong.manto.widget.k.a$a  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    public interface InterfaceC0701a<O> {
        void a(O o);

        void onCancel();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static NumberPicker a(Context context) {
        NumberPicker numberPicker = (NumberPicker) LayoutInflater.from(context).inflate(R.layout.manto_number_picker_txt_14, (ViewGroup) null);
        if (numberPicker != null) {
            numberPicker.setWrapSelectorWheel(false);
            d.b(numberPicker);
        }
        return numberPicker;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static NumberPicker b(Context context) {
        NumberPicker numberPicker = (NumberPicker) LayoutInflater.from(context).inflate(R.layout.manto_number_picker_txt_18, (ViewGroup) null);
        if (numberPicker != null) {
            d.b(numberPicker);
            numberPicker.setWrapSelectorWheel(false);
        }
        return numberPicker;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InterfaceC0701a<T> a() {
        return this.a;
    }

    public void a(InterfaceC0701a<T> interfaceC0701a) {
        this.a = interfaceC0701a;
    }

    public abstract T b();

    public abstract View c();
}
