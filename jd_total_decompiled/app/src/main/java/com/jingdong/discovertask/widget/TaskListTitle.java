package com.jingdong.discovertask.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jingdong.common.R;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes12.dex */
public class TaskListTitle extends ConstraintLayout {
    private View course;
    private Context mContext;
    private TextView title;

    public TaskListTitle(Context context) {
        this(context, null);
    }

    private void initView(Context context) {
        this.mContext = context;
        LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.layout_task_list_title, this);
        this.title = (TextView) findViewById(R.id.title);
        this.course = findViewById(R.id.course);
    }

    public void dealDarkMode() {
        if (DeepDarkChangeManager.getInstance().getUIMode() == 1) {
            TextView textView = this.title;
            if (textView != null) {
                textView.setTextColor(-1);
                return;
            }
            return;
        }
        TextView textView2 = this.title;
        if (textView2 != null) {
            textView2.setTextColor(Color.parseColor("#FF000000"));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        View view = this.course;
        if (view != null) {
            int measuredWidth = view.getMeasuredWidth();
            int width = DPIUtil.getWidth(this.mContext);
            this.title.getLayoutParams().width = (width - (measuredWidth * 2)) - DPIUtil.dip2px(20.0f);
        }
    }

    public TaskListTitle(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TaskListTitle(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        initView(context);
    }
}
