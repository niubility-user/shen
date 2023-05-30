package com.jingdong.common.messagepop.template;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.mall.mylib.R;
import com.jingdong.common.messagepop.MessagePopModel;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.remoteimage.RemoteImageManager;
import com.jingdong.wireless.iconfont.widget.IconImageView;

/* loaded from: classes5.dex */
public class TemplateImgTextPopView implements IPopView {
    private int templateId;

    public TemplateImgTextPopView(int i2) {
        this.templateId = i2;
    }

    @Override // com.jingdong.common.messagepop.template.IPopView
    public View initPopView(Context context, MessagePopModel messagePopModel) {
        return initPopView(context, messagePopModel, null);
    }

    @Override // com.jingdong.common.messagepop.template.IPopView
    public View initPopView(Context context, MessagePopModel messagePopModel, View.OnClickListener onClickListener) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.message_pop_item_bg_img, (ViewGroup) null);
        View findViewById = inflate.findViewById(R.id.messagePopBg);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) inflate.findViewById(R.id.messagePopBgIv);
        SimpleDraweeView simpleDraweeView2 = (SimpleDraweeView) inflate.findViewById(R.id.messagePopIv);
        TextView textView = (TextView) inflate.findViewById(R.id.messagePopTv);
        View findViewById2 = inflate.findViewById(R.id.messagePopRightRoundBg);
        IconImageView iconImageView = (IconImageView) inflate.findViewById(R.id.messagePopRightRoundIv);
        TextView textView2 = (TextView) inflate.findViewById(R.id.messagePopRightBtn);
        ((GradientDrawable) findViewById.getBackground()).setColor(Color.parseColor(JDDarkUtil.COLOR_FFFFFFF));
        if (!TextUtils.isEmpty(messagePopModel.stationSImgPath)) {
            JDImageUtils.displayImage(messagePopModel.stationSImgPath, simpleDraweeView2);
        }
        if (!TextUtils.isEmpty(messagePopModel.alert)) {
            textView.setText(Html.fromHtml(messagePopModel.alert));
        }
        switch (this.templateId) {
            case 19:
                simpleDraweeView.setVisibility(0);
                findViewById2.setVisibility(0);
                iconImageView.setVisibility(0);
                textView2.setVisibility(8);
                JDImageUtils.displayImage(RemoteImageManager.getImageUrlById("114_3181"), simpleDraweeView);
                break;
            case 20:
                simpleDraweeView.setVisibility(0);
                findViewById2.setVisibility(8);
                iconImageView.setVisibility(8);
                textView2.setVisibility(0);
                JDImageUtils.displayImage(RemoteImageManager.getImageUrlById("114_3181"), simpleDraweeView);
                if (!TextUtils.isEmpty(messagePopModel.btnText) && messagePopModel.btnText.length() >= 3 && messagePopModel.btnText.length() <= 4) {
                    textView2.setText(messagePopModel.btnText);
                    break;
                } else {
                    textView2.setText("\u53bb\u770b\u770b");
                    break;
                }
                break;
            case 21:
                simpleDraweeView.setVisibility(8);
                findViewById2.setVisibility(8);
                iconImageView.setVisibility(8);
                textView2.setVisibility(0);
                if (!TextUtils.isEmpty(messagePopModel.btnText) && messagePopModel.btnText.length() >= 3 && messagePopModel.btnText.length() <= 4) {
                    textView2.setText(messagePopModel.btnText);
                    break;
                } else {
                    textView2.setText("\u53bb\u770b\u770b");
                    break;
                }
        }
        return inflate;
    }
}
