package com.jingdong.common.widget.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.common.R;
import com.jingdong.jdsdk.utils.DPIUtil;
import java.util.List;

/* loaded from: classes12.dex */
public class CustomTagList extends LinearLayout {
    private boolean haveBottomDiv;
    private View.OnClickListener onClickListener;
    private float tagPaddingLeft;

    /* loaded from: classes12.dex */
    public static class TagEntity {
        public String id;
        public String name;
    }

    public CustomTagList(Context context) {
        super(context);
        this.haveBottomDiv = false;
        this.tagPaddingLeft = 0.0f;
    }

    private void addRow(LinearLayout linearLayout) {
        linearLayout.setPadding((int) this.tagPaddingLeft, 0, 0, 0);
        addView(linearLayout);
        addView(new View(getContext()), new LinearLayout.LayoutParams(-1, DPIUtil.dip2px(15.0f)));
    }

    private TextView getTextView() {
        TextView textView = new TextView(getContext());
        textView.setTextSize(1, 13.0f);
        textView.setTextColor(Color.parseColor("#7f7f7f"));
        textView.setBackgroundResource(R.drawable.inventory_tag_bg);
        textView.setSingleLine();
        return textView;
    }

    public void setData(List<? extends TagEntity> list, int i2) {
        List<? extends TagEntity> list2 = list;
        if (list2 != null && list.size() > 0) {
            removeAllViews();
            TextPaint paint = getTextView().getPaint();
            int width = DPIUtil.getWidth() - DPIUtil.dip2px(16.5f);
            int dip2px = DPIUtil.dip2px(5.5f);
            int dip2px2 = DPIUtil.dip2px(13.0f) + dip2px;
            int i3 = width - dip2px2;
            float f2 = 0.0f;
            LinearLayout linearLayout = new LinearLayout(getContext());
            addRow(linearLayout);
            int i4 = 0;
            while (i4 < list.size() && i4 != i2) {
                TagEntity tagEntity = list2.get(i4);
                String str = tagEntity.name;
                if (!TextUtils.isEmpty(str)) {
                    String str2 = "#" + str + "#";
                    float measureText = paint.measureText(str2);
                    float f3 = i3;
                    if (measureText > f3) {
                        str2 = str2.substring(0, paint.breakText(str2, true, f3, null));
                    }
                    float f4 = measureText + dip2px2;
                    float f5 = f2 + f4;
                    if (f5 >= width) {
                        linearLayout = new LinearLayout(getContext());
                        addRow(linearLayout);
                    } else {
                        f4 = f5;
                    }
                    TextView textView = getTextView();
                    textView.setText(str2);
                    textView.setTag(tagEntity);
                    textView.setOnClickListener(this.onClickListener);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.setMargins(0, 0, dip2px, 0);
                    textView.setLayoutParams(layoutParams);
                    linearLayout.addView(textView);
                    if ((i4 == list.size() - 1 || i4 == 2) && this.haveBottomDiv) {
                        View view = new View(getContext());
                        view.setBackgroundColor(Color.parseColor("#F4F4F4"));
                        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, DPIUtil.dip2px(5.0f));
                        layoutParams2.setMargins(0, DPIUtil.dip2px(10.0f), 0, 0);
                        addView(view, layoutParams2);
                    }
                    f2 = f4;
                }
                i4++;
                list2 = list;
            }
            return;
        }
        setVisibility(8);
    }

    public void setOnItemClick(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public CustomTagList(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.haveBottomDiv = false;
        this.tagPaddingLeft = 0.0f;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CustomTagList);
        this.tagPaddingLeft = obtainStyledAttributes.getDimension(R.styleable.CustomTagList_paddingLeft, 0.0f);
        this.haveBottomDiv = obtainStyledAttributes.getBoolean(R.styleable.CustomTagList_haveBottomDiv, false);
        obtainStyledAttributes.recycle();
    }
}
