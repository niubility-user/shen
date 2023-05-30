package com.jingdong.common.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes12.dex */
public class JDReverseDefaultToast extends Toast {
    public JDReverseDefaultToast(Context context) {
        super(context);
    }

    public static JDReverseDefaultToast makeText(Context context, int i2, int i3) throws Resources.NotFoundException {
        return makeText(context, context.getResources().getText(i2), i3);
    }

    public static JDReverseDefaultToast makeText(Context context, CharSequence charSequence, int i2) {
        TextView textView;
        LinearLayout linearLayout;
        JDReverseDefaultToast jDReverseDefaultToast = new JDReverseDefaultToast(context);
        try {
            View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(context.getResources().getIdentifier("transient_notification", "layout", "android"), (ViewGroup) null);
            textView = (TextView) inflate.findViewById(context.getResources().getIdentifier("message", "id", "android"));
            linearLayout = inflate;
        } catch (Exception unused) {
            LinearLayout linearLayout2 = new LinearLayout(context);
            RoundRectShape roundRectShape = new RoundRectShape(new float[]{9.0f, 9.0f, 9.0f, 9.0f, 9.0f, 9.0f, 9.0f, 9.0f}, null, null);
            ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f}, null, null));
            shapeDrawable.getPaint().setColor(-16777216);
            ShapeDrawable shapeDrawable2 = new ShapeDrawable(roundRectShape);
            shapeDrawable2.setPadding(0, 0, 1, 2);
            shapeDrawable2.getPaint().setColor(-6250336);
            linearLayout2.setBackgroundDrawable(new LayerDrawable(new Drawable[]{shapeDrawable2, shapeDrawable}));
            textView = new TextView(context);
            textView.setTextColor(-1);
            int widthByDesignValue720 = DPIUtil.getWidthByDesignValue720(20);
            linearLayout2.setPadding(widthByDesignValue720, widthByDesignValue720, widthByDesignValue720, widthByDesignValue720);
            linearLayout2.addView(textView);
            linearLayout = linearLayout2;
        }
        textView.setText(charSequence);
        textView.setRotation(180.0f);
        int yOffset = jDReverseDefaultToast.getYOffset();
        jDReverseDefaultToast.setView(linearLayout);
        jDReverseDefaultToast.setDuration(i2);
        jDReverseDefaultToast.setGravity(48, 0, yOffset);
        return jDReverseDefaultToast;
    }
}
