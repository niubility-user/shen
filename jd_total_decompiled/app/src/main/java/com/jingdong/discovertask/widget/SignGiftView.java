package com.jingdong.discovertask.widget;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.common.R;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.discovertask.model.entity.TaskEntity;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* loaded from: classes12.dex */
public class SignGiftView extends LinearLayout {
    public static final int AWARD_TYPE = 4;
    public static final int SIGN_TYPE = 3;
    public static final int STYLE_DIALOG_LARGE = 2;
    public static final int STYLE_DIALOG_SMALL = 1;
    private LayoutInflater inflater;
    private int mPageType;
    private List<View> mSignViews;

    public SignGiftView(Context context) {
        this(context, null);
    }

    private void dayAwardIconStyle(View view, int i2, int i3, boolean z, int i4, boolean z2) {
        View findViewById = view.findViewById(R.id.icon_day_unsigned);
        View findViewById2 = view.findViewById(R.id.icon_ultimate_unsigned);
        if (!z2) {
            if (i2 == 2) {
                if (i3 == 0) {
                    findViewById2.setVisibility(8);
                    findViewById.setVisibility(0);
                    findViewById.setBackgroundResource(R.drawable.icon_day_signed);
                    return;
                }
                findViewById.setVisibility(8);
                findViewById2.setVisibility(0);
                findViewById2.setBackgroundResource(R.drawable.icon_sign_ultimate_award);
            } else if (i3 == 0) {
                findViewById2.setVisibility(8);
                findViewById.setVisibility(0);
                findViewById.setBackgroundResource(R.drawable.icon_day_unsigned);
            } else {
                findViewById.setVisibility(8);
                findViewById2.setVisibility(0);
                findViewById2.setBackgroundResource(R.drawable.icon_unsign_ultimate_award);
            }
        } else if (z && (i2 == 2 || i2 == 3 || i2 == 1)) {
            if (i3 == 0) {
                findViewById2.setVisibility(8);
                findViewById.setVisibility(0);
                findViewById.setBackgroundResource(R.drawable.icon_day_signed);
                return;
            }
            findViewById.setVisibility(8);
            findViewById2.setVisibility(0);
            findViewById2.setBackgroundResource(R.drawable.icon_sign_ultimate_award);
        } else if (i3 == 0) {
            findViewById2.setVisibility(8);
            findViewById.setVisibility(0);
            findViewById.setBackgroundResource(R.drawable.icon_day_unsigned);
        } else {
            findViewById.setVisibility(8);
            findViewById2.setVisibility(0);
            findViewById2.setBackgroundResource(R.drawable.icon_unsign_ultimate_award);
        }
    }

    private void init(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0 */
    /* JADX WARN: Type inference failed for: r11v1, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r11v4 */
    private void initView(TaskEntity.DiscBaseTaskEntity discBaseTaskEntity) {
        TaskEntity.DiscSignTaskDetailEntity discSignTaskDetailEntity;
        List<TaskEntity.DiscTaskItemInfoEntity> list;
        TextView textView;
        TextView textView2;
        View view;
        int i2;
        TextView textView3;
        int i3;
        if (discBaseTaskEntity == null || (discSignTaskDetailEntity = discBaseTaskEntity.signDetail) == null || (list = discSignTaskDetailEntity.discTaskItemInfos) == null || list.isEmpty()) {
            return;
        }
        this.mSignViews.clear();
        int i4 = -1;
        ?? r11 = 0;
        int i5 = 0;
        int i6 = -1;
        boolean z = false;
        while (i5 < list.size()) {
            TaskEntity.DiscTaskItemInfoEntity discTaskItemInfoEntity = list.get(i5);
            View inflate = this.inflater.inflate(R.layout.discover_sign_gift, this, (boolean) r11);
            if (i5 == 0) {
                inflate.findViewById(R.id.line_left).setVisibility(8);
            }
            if (i5 == list.size() - 1) {
                inflate.findViewById(R.id.line_right).setVisibility(8);
            }
            TextView textView4 = (TextView) inflate.findViewById(R.id.award_amount);
            TextView textView5 = (TextView) inflate.findViewById(R.id.award_count);
            View findViewById = inflate.findViewById(R.id.icon_arrow);
            if (TextUtils.isEmpty(discTaskItemInfoEntity.itemDesc)) {
                textView4.setVisibility(4);
                findViewById.setVisibility(4);
                if (discTaskItemInfoEntity.jPeas != 0) {
                    textView5.setText(MqttTopic.SINGLE_LEVEL_WILDCARD + discTaskItemInfoEntity.jPeas);
                    textView5.setVisibility(r11);
                } else {
                    textView5.setVisibility(4);
                }
            } else {
                textView5.setVisibility(4);
                textView4.setVisibility(r11);
                findViewById.setVisibility(r11);
                textView4.setText(discTaskItemInfoEntity.itemDesc);
            }
            View findViewById2 = inflate.findViewById(R.id.icon_day_unsigned);
            View findViewById3 = inflate.findViewById(R.id.icon_ultimate_unsigned);
            findViewById2.setVisibility(4);
            findViewById3.setVisibility(4);
            int i7 = discTaskItemInfoEntity.rewardLevel;
            if (i7 == 0) {
                findViewById2.setVisibility(r11);
                findViewById3.setVisibility(4);
            } else if (i7 == 1) {
                findViewById2.setVisibility(4);
                findViewById3.setVisibility(r11);
            } else {
                findViewById2.setVisibility(4);
                findViewById3.setVisibility(r11);
            }
            TextView textView6 = (TextView) inflate.findViewById(R.id.text_day);
            TextView textView7 = (TextView) inflate.findViewById(R.id.text_today);
            if (this.mPageType == 4) {
                if (DeepDarkChangeManager.getInstance().getUIMode() == 1) {
                    textView6.setTextColor(Color.parseColor("#8C8C8C"));
                } else if (i6 == i4) {
                    textView6.setTextColor(Color.parseColor("#353535"));
                } else {
                    textView6.setTextColor(Color.parseColor(JDDarkUtil.COLOR_CCCCCC));
                }
                textView = textView7;
                i2 = 1;
                textView2 = textView6;
                view = inflate;
                dayAwardIconStyle(inflate, discTaskItemInfoEntity.rewardStatus, discTaskItemInfoEntity.rewardLevel, discBaseTaskEntity.curSignState == 1, i6, discTaskItemInfoEntity.curStep == 1);
            } else {
                textView = textView7;
                textView2 = textView6;
                view = inflate;
                i2 = 1;
            }
            if (discTaskItemInfoEntity.curStep == i2) {
                if (discTaskItemInfoEntity.rewardStatus == 2) {
                    i3 = 0;
                    z = true;
                } else {
                    i3 = 0;
                    z = false;
                }
                textView.setVisibility(i3);
                textView3 = textView2;
                textView3.setVisibility(8);
                i6 = i5;
            } else {
                textView3 = textView2;
                textView.setVisibility(8);
                textView3.setVisibility(0);
            }
            textView3.setText("\u7b2c" + discTaskItemInfoEntity.order + "\u5929");
            View view2 = view;
            this.mSignViews.add(view2);
            addView(view2);
            i5++;
            i4 = -1;
            r11 = 0;
        }
        if (this.mPageType == 3) {
            signToday(i6, z);
        }
    }

    private void modifyStyle(int i2, View view) {
        TextView textView = (TextView) view.findViewById(R.id.award_amount);
        TextView textView2 = (TextView) view.findViewById(R.id.text_day);
        TextView textView3 = (TextView) view.findViewById(R.id.text_today);
        View findViewById = view.findViewById(R.id.icon_day_unsigned);
        View findViewById2 = view.findViewById(R.id.icon_ultimate_unsigned);
        View findViewById3 = view.findViewById(R.id.line_left);
        View findViewById4 = view.findViewById(R.id.line_right);
        if (1 == i2) {
            textView.setTextSize(2, 8.0f);
            ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
            layoutParams.width = DPIUtil.dip2px(15.0f);
            layoutParams.height = DPIUtil.dip2px(16.5f);
            findViewById.setLayoutParams(layoutParams);
            ViewGroup.LayoutParams layoutParams2 = findViewById2.getLayoutParams();
            layoutParams2.width = DPIUtil.dip2px(17.0f);
            layoutParams2.height = DPIUtil.dip2px(18.5f);
            findViewById2.setLayoutParams(layoutParams2);
            textView2.setTextSize(2, 8.0f);
            ViewGroup.LayoutParams layoutParams3 = textView3.getLayoutParams();
            layoutParams3.width = DPIUtil.dip2px(25.0f);
            layoutParams3.height = DPIUtil.dip2px(11.0f);
            textView3.setTextSize(2, 8.0f);
            ViewGroup.LayoutParams layoutParams4 = findViewById3.getLayoutParams();
            layoutParams4.width = DPIUtil.dip2px(6.0f);
            findViewById3.setLayoutParams(layoutParams4);
            ViewGroup.LayoutParams layoutParams5 = findViewById4.getLayoutParams();
            layoutParams5.width = DPIUtil.dip2px(6.0f);
            findViewById4.setLayoutParams(layoutParams5);
            return;
        }
        textView.setTextSize(2, 10.0f);
        ViewGroup.LayoutParams layoutParams6 = findViewById.getLayoutParams();
        layoutParams6.width = DPIUtil.dip2px(25.0f);
        layoutParams6.height = DPIUtil.dip2px(25.0f);
        findViewById.setLayoutParams(layoutParams6);
        ViewGroup.LayoutParams layoutParams7 = findViewById2.getLayoutParams();
        layoutParams7.width = DPIUtil.dip2px(21.0f);
        layoutParams7.height = DPIUtil.dip2px(24.0f);
        findViewById2.setLayoutParams(layoutParams7);
        textView2.setTextSize(2, 10.0f);
        ViewGroup.LayoutParams layoutParams8 = textView3.getLayoutParams();
        layoutParams8.width = DPIUtil.dip2px(30.0f);
        layoutParams8.height = DPIUtil.dip2px(13.0f);
        textView3.setTextSize(2, 10.0f);
        ViewGroup.LayoutParams layoutParams9 = findViewById3.getLayoutParams();
        layoutParams9.width = DPIUtil.dip2px(10.0f);
        findViewById3.setLayoutParams(layoutParams9);
        ViewGroup.LayoutParams layoutParams10 = findViewById4.getLayoutParams();
        layoutParams10.width = DPIUtil.dip2px(10.0f);
        findViewById4.setLayoutParams(layoutParams10);
    }

    private void signToday(int i2, boolean z) {
        if (this.mSignViews != null && i2 >= 0 && i2 <= r5.size() - 1) {
            for (int i3 = 0; i3 < this.mSignViews.size(); i3++) {
                View view = this.mSignViews.get(i3);
                View findViewById = view.findViewById(R.id.icon_day_unsigned);
                View findViewById2 = view.findViewById(R.id.icon_ultimate_unsigned);
                if (i3 <= i2) {
                    findViewById.setVisibility(8);
                    findViewById2.setVisibility(8);
                }
            }
        }
    }

    public SignGiftView initWithData(TaskEntity.DiscBaseTaskEntity discBaseTaskEntity, int i2) {
        if (discBaseTaskEntity != null && discBaseTaskEntity.signDetail != null) {
            this.mPageType = i2;
            initView(discBaseTaskEntity);
        }
        return this;
    }

    public SignGiftView modifyWithStyle(int i2) {
        if (this.mSignViews != null) {
            for (int i3 = 0; i3 < this.mSignViews.size(); i3++) {
                modifyStyle(i2, this.mSignViews.get(i3));
            }
            invalidate();
            requestLayout();
            return this;
        }
        return null;
    }

    public SignGiftView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SignGiftView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mSignViews = new ArrayList();
        this.mPageType = 3;
        init(context);
    }
}
