package com.jingdong.common.messagepop.template;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.mall.mylib.R;
import com.jingdong.common.messagepop.MessagePopModel;
import com.jingdong.common.utils.DeepDarkUtils;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.wireless.iconfont.widget.IconImageView;

/* loaded from: classes5.dex */
public class ItemLivePopView implements IPopView {
    @Override // com.jingdong.common.messagepop.template.IPopView
    public View initPopView(Context context, MessagePopModel messagePopModel) {
        return initPopView(context, messagePopModel, null);
    }

    @Override // com.jingdong.common.messagepop.template.IPopView
    public View initPopView(Context context, MessagePopModel messagePopModel, View.OnClickListener onClickListener) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.message_pop_item_live, (ViewGroup) null);
        ((GradientDrawable) ((FrameLayout) inflate.findViewById(R.id.message_pop_bg)).getBackground()).setColor(DeepDarkUtils.getInstance().getDarkColorFromJson(JDDarkUtil.COLOR_FFFFFFF));
        Animation loadAnimation = AnimationUtils.loadAnimation(context, R.anim.live_pop_rotate_amin);
        loadAnimation.setInterpolator(new LinearInterpolator());
        ((ImageView) inflate.findViewById(R.id.live_guanghuan_iv)).startAnimation(loadAnimation);
        ((GradientDrawable) ((FrameLayout) inflate.findViewById(R.id.live_icon_bg)).getBackground()).setColor(DeepDarkUtils.getInstance().getDarkColorFromJson(JDDarkUtil.COLOR_FFFFFFF));
        ((SimpleDraweeView) inflate.findViewById(R.id.live_icon)).setController(Fresco.newDraweeControllerBuilder().setUri(new Uri.Builder().scheme(UriUtil.LOCAL_RESOURCE_SCHEME).path(String.valueOf(R.drawable.search_live_tag)).build()).setAutoPlayAnimations(true).build());
        if (!TextUtils.isEmpty(messagePopModel.icon)) {
            JDImageUtils.displayImage(messagePopModel.icon, (SimpleDraweeView) inflate.findViewById(R.id.message_pop_icon));
        }
        if (!TextUtils.isEmpty(messagePopModel.channelTitle)) {
            TextView textView = (TextView) inflate.findViewById(R.id.message_pop_title);
            textView.setText(messagePopModel.channelTitle);
            textView.setTextColor(DeepDarkUtils.getInstance().getDarkColorFromJson(JDDarkUtil.COLOR_262626));
        }
        if (!TextUtils.isEmpty(messagePopModel.alert)) {
            TextView textView2 = (TextView) inflate.findViewById(R.id.message_pop_content);
            textView2.setText(Html.fromHtml(messagePopModel.alert));
            textView2.setTextColor(DeepDarkUtils.getInstance().getDarkColorFromJson(JDDarkUtil.COLOR_262626));
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
