package com.jingdong.common.messagepop.template;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.mall.mylib.R;
import com.jingdong.common.messagepop.MessagePopModel;
import com.jingdong.common.utils.DeepDarkUtils;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.wireless.iconfont.widget.IconImageView;

/* loaded from: classes5.dex */
public class ItemVideoPopView implements IPopView {
    @Override // com.jingdong.common.messagepop.template.IPopView
    public View initPopView(Context context, MessagePopModel messagePopModel) {
        return initPopView(context, messagePopModel, null);
    }

    @Override // com.jingdong.common.messagepop.template.IPopView
    public View initPopView(Context context, MessagePopModel messagePopModel, View.OnClickListener onClickListener) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.message_pop_item_video, (ViewGroup) null);
        ((GradientDrawable) ((FrameLayout) inflate.findViewById(R.id.message_pop_bg)).getBackground()).setColor(DeepDarkUtils.getInstance().getDarkColorFromJson(JDDarkUtil.COLOR_FFFFFFF));
        if (!TextUtils.isEmpty(messagePopModel.stationVideoImgPath)) {
            JDImageUtils.displayImage(messagePopModel.stationVideoImgPath, (SimpleDraweeView) inflate.findViewById(R.id.message_pop_left_image));
        }
        if (!TextUtils.isEmpty(messagePopModel.alert)) {
            TextView textView = (TextView) inflate.findViewById(R.id.message_pop_content);
            textView.setText(Html.fromHtml(messagePopModel.alert));
            textView.setTextColor(DeepDarkUtils.getInstance().getDarkColorFromJson(JDDarkUtil.COLOR_262626));
        }
        IconImageView iconImageView = (IconImageView) inflate.findViewById(R.id.message_pop_right_iv);
        iconImageView.setColor(context.getResources().getColor(R.color.message_pop_right_iv_color));
        iconImageView.setColor(DeepDarkUtils.getInstance().getDarkColorFromJson(JDDarkUtil.COLOR_FFFFFFF));
        GradientDrawable gradientDrawable = (GradientDrawable) ((ImageView) inflate.findViewById(R.id.message_pop_right_iv_bg)).getBackground();
        gradientDrawable.setColor(DeepDarkUtils.getInstance().getDarkColorFromJson(JDDarkUtil.COLOR_262626));
        gradientDrawable.setAlpha(77);
        return inflate;
    }
}
