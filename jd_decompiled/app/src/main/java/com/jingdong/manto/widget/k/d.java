package com.jingdong.manto.widget.k;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import com.jingdong.manto.R;
import com.jingdong.manto.utils.MantoDensityUtils;
import java.lang.reflect.Field;

/* loaded from: classes16.dex */
public class d {
    public static void a(Context context, NumberPicker numberPicker) {
        Resources resources;
        int i2;
        if (numberPicker == null) {
            return;
        }
        if (com.jingdong.manto.k.a.b().a() == 1) {
            resources = numberPicker.getContext().getResources();
            i2 = R.color.manto_dark_text_weight;
        } else {
            resources = numberPicker.getContext().getResources();
            i2 = R.color.manto_black;
        }
        a(numberPicker, resources.getColor(i2));
    }

    public static void a(NumberPicker numberPicker) {
        if (numberPicker != null) {
            try {
                Field declaredField = NumberPicker.class.getDeclaredField("mInputText");
                declaredField.setAccessible(true);
                EditText editText = (EditText) declaredField.get(numberPicker);
                if (editText != null) {
                    editText.setClickable(false);
                    editText.setFocusable(false);
                    editText.setFocusableInTouchMode(false);
                }
            } catch (Exception unused) {
            }
        }
    }

    public static void a(NumberPicker numberPicker, int i2) {
        if (numberPicker == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            numberPicker.setTextColor(i2);
            return;
        }
        int childCount = numberPicker.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = numberPicker.getChildAt(i3);
            if (childAt instanceof EditText) {
                try {
                    ((EditText) childAt).setTextColor(i2);
                    numberPicker.invalidate();
                    Field declaredField = numberPicker.getClass().getDeclaredField("mSelectorWheelPaint");
                    boolean isAccessible = declaredField.isAccessible();
                    declaredField.setAccessible(true);
                    ((Paint) declaredField.get(numberPicker)).setColor(i2);
                    declaredField.setAccessible(isAccessible);
                    numberPicker.invalidate();
                } catch (Exception unused) {
                }
            }
        }
    }

    public static void a(NumberPicker numberPicker, Drawable drawable) {
        if (numberPicker != null) {
            try {
                Field declaredField = NumberPicker.class.getDeclaredField("mSelectionDivider");
                declaredField.setAccessible(true);
                declaredField.set(numberPicker, drawable);
            } catch (Exception unused) {
            }
        }
    }

    public static void b(NumberPicker numberPicker) {
        if (numberPicker == null) {
            return;
        }
        ColorDrawable colorDrawable = new ColorDrawable(numberPicker.getContext().getResources().getColor(R.color.manto_picker_selection_divider));
        b(numberPicker, MantoDensityUtils.dip2pixel(numberPicker.getContext(), 1));
        a(numberPicker, colorDrawable);
        a(numberPicker);
        c(numberPicker);
        d(numberPicker);
    }

    public static void b(NumberPicker numberPicker, int i2) {
        if (numberPicker != null) {
            try {
                if (Build.VERSION.SDK_INT < 29) {
                    Field declaredField = NumberPicker.class.getDeclaredField("mSelectionDividerHeight");
                    declaredField.setAccessible(true);
                    declaredField.set(numberPicker, Integer.valueOf(i2));
                } else {
                    numberPicker.setSelectionDividerHeight(i2);
                }
            } catch (Exception unused) {
            }
        }
    }

    public static void c(NumberPicker numberPicker) {
        if (numberPicker != null) {
            try {
                Field declaredField = NumberPicker.class.getDeclaredField("mInputText");
                declaredField.setAccessible(true);
                EditText editText = (EditText) declaredField.get(numberPicker);
                if (editText != null) {
                    editText.setFilters(new InputFilter[0]);
                }
            } catch (Exception unused) {
            }
        }
    }

    public static void d(NumberPicker numberPicker) {
        if (numberPicker != null) {
            try {
                Field declaredField = NumberPicker.class.getDeclaredField("mSetSelectionCommand");
                declaredField.setAccessible(true);
                Runnable runnable = (Runnable) declaredField.get(numberPicker);
                if (runnable != null) {
                    numberPicker.removeCallbacks(runnable);
                }
            } catch (Exception unused) {
            }
        }
    }
}
