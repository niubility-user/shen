package com.jingdong.common.messagepop.template;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.mall.mylib.R;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.messagepop.MessagePopModel;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.utils.DeepDarkUtils;
import com.jingdong.common.utils.JDImageUtils;

/* loaded from: classes5.dex */
public class TemplateFifteenPopView implements IPopView {
    @Override // com.jingdong.common.messagepop.template.IPopView
    public View initPopView(Context context, MessagePopModel messagePopModel) {
        return initPopView(context, messagePopModel, null);
    }

    @Override // com.jingdong.common.messagepop.template.IPopView
    public View initPopView(Context context, MessagePopModel messagePopModel, View.OnClickListener onClickListener) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.message_pop_item_template_fifteen, (ViewGroup) null);
        ((GradientDrawable) ((FrameLayout) inflate.findViewById(R.id.message_pop_bg)).getBackground()).setColor(DeepDarkUtils.getInstance().getDarkColorFromJson(JDDarkUtil.COLOR_FFFFFFF));
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) inflate.findViewById(R.id.message_pop_left_image);
        try {
            JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
            int i2 = R.drawable.jd_buy_icon;
            JDImageUtils.displayImage(messagePopModel.stationSImgPath, simpleDraweeView, jDDisplayImageOptions.showImageOnLoading(i2).showImageOnFail(i2).showImageForEmptyUri(i2));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!TextUtils.isEmpty(messagePopModel.title)) {
            TextView textView = (TextView) inflate.findViewById(R.id.message_pop_title);
            textView.setText(messagePopModel.title);
            textView.setTextColor(DeepDarkUtils.getInstance().getDarkColorFromJson(JDDarkUtil.COLOR_0000000));
        }
        if (!TextUtils.isEmpty(messagePopModel.btnText)) {
            TextView textView2 = (TextView) inflate.findViewById(R.id.message_pop_right_bt);
            textView2.setText(messagePopModel.btnText);
            textView2.setBackground(context.getResources().getDrawable(DeepDarkChangeManager.getInstance().getUIMode() == 0 ? R.drawable.message_pop_lotter_bt_bg : R.drawable.message_pop_lotter_bt_bg_dark));
        }
        return inflate;
    }
}
