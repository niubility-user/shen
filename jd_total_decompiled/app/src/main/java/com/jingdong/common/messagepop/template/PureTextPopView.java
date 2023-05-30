package com.jingdong.common.messagepop.template;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.mall.mylib.R;
import com.jingdong.common.messagepop.MessagePopModel;
import com.jingdong.common.utils.DeepDarkUtils;

/* loaded from: classes5.dex */
public class PureTextPopView implements IPopView {
    @Override // com.jingdong.common.messagepop.template.IPopView
    public View initPopView(Context context, MessagePopModel messagePopModel) {
        return initPopView(context, messagePopModel, null);
    }

    @Override // com.jingdong.common.messagepop.template.IPopView
    public View initPopView(Context context, MessagePopModel messagePopModel, View.OnClickListener onClickListener) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.message_pop_item_text_only, (ViewGroup) null);
        ((GradientDrawable) ((FrameLayout) inflate.findViewById(R.id.message_pop_bg)).getBackground()).setColor(DeepDarkUtils.getInstance().getDarkColorFromJson(JDDarkUtil.COLOR_FFFFFFF));
        if (!TextUtils.isEmpty(messagePopModel.alert)) {
            TextView textView = (TextView) inflate.findViewById(R.id.message_pop_content);
            textView.setText(Html.fromHtml(messagePopModel.alert));
            textView.setTextColor(DeepDarkUtils.getInstance().getDarkColorFromJson(JDDarkUtil.COLOR_262626));
        }
        return inflate;
    }
}
