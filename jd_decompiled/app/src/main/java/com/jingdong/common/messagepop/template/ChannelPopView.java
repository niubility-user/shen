package com.jingdong.common.messagepop.template;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.mylib.R;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.messagepop.MessagePopModel;
import com.jingdong.common.utils.JDImageUtils;

/* loaded from: classes5.dex */
public class ChannelPopView implements IPopView {
    @Override // com.jingdong.common.messagepop.template.IPopView
    public View initPopView(Context context, MessagePopModel messagePopModel) {
        return initPopView(context, messagePopModel, null);
    }

    @Override // com.jingdong.common.messagepop.template.IPopView
    public View initPopView(Context context, MessagePopModel messagePopModel, View.OnClickListener onClickListener) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.message_pop_item1, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.message_pop_title);
        TextView textView2 = (TextView) inflate.findViewById(R.id.message_pop_content);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.message_pop_btn);
        TextView textView3 = (TextView) inflate.findViewById(R.id.message_pop_type_name);
        TextView textView4 = (TextView) inflate.findViewById(R.id.message_pop_content2);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) inflate.findViewById(R.id.message_pop_left_image);
        int i2 = messagePopModel.notifyTemplateId;
        if (i2 == 3) {
            imageView.setVisibility(0);
            simpleDraweeView.setVisibility(8);
            textView4.setVisibility(8);
        } else if (i2 == 4) {
            simpleDraweeView.setVisibility(8);
            imageView.setVisibility(8);
            textView.setVisibility(8);
            textView2.setVisibility(8);
            textView4.setText(messagePopModel.alert);
            textView4.setVisibility(0);
        } else if (i2 == 5) {
            textView2.setTextSize(1, 13.0f);
            simpleDraweeView.setVisibility(0);
            imageView.setVisibility(0);
            textView4.setVisibility(8);
            JDImageUtils.displayImage(messagePopModel.sImagePath, simpleDraweeView, new JDDisplayImageOptions().setPlaceholder(17));
        }
        textView3.setText(TextUtils.isEmpty(messagePopModel.channelTitle) ? "\u6d88\u606f\u901a\u77e5" : messagePopModel.channelTitle);
        textView.setText(messagePopModel.title);
        textView2.setText(messagePopModel.alert);
        String str = messagePopModel.icon;
        JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
        int i3 = R.drawable.jd_buy_icon;
        JDImageUtils.displayImage(str, (SimpleDraweeView) inflate.findViewById(R.id.message_pop_type_icon), jDDisplayImageOptions.showImageOnLoading(i3).showImageOnFail(i3).showImageForEmptyUri(i3));
        if (onClickListener != null) {
            imageView.setOnClickListener(onClickListener);
        }
        return inflate;
    }
}
