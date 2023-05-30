package com.jd.lib.productdetail.mainimage.holder.ask;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatTextView;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdAnswerInfo;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdQuestionInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareImageQaEntity;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.utils.JDImageUtils;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMAskItemView extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    public AppCompatTextView f4659g;

    /* renamed from: h  reason: collision with root package name */
    public SimpleDraweeView f4660h;

    /* renamed from: i  reason: collision with root package name */
    public SimpleDraweeView f4661i;

    /* renamed from: j  reason: collision with root package name */
    public AppCompatTextView f4662j;

    /* renamed from: k  reason: collision with root package name */
    public SimpleDraweeView f4663k;

    /* renamed from: l  reason: collision with root package name */
    public AppCompatTextView f4664l;

    public PdMAskItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(PdQuestionInfo pdQuestionInfo, WareImageQaEntity wareImageQaEntity) {
        PdAnswerInfo pdAnswerInfo;
        if (pdQuestionInfo == null) {
            setVisibility(8);
            return;
        }
        this.f4659g.setText(pdQuestionInfo.content);
        List<PdAnswerInfo> list = pdQuestionInfo.answerList;
        if (list == null || list.isEmpty() || (pdAnswerInfo = list.get(0)) == null) {
            return;
        }
        JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
        jDDisplayImageOptions.setRoundingParams(new RoundingParams().setRoundAsCircle(true));
        int i2 = R.drawable.lib_pd_mainimage_holder_item_comment_user_pic_default_bg;
        jDDisplayImageOptions.showImageOnFail(i2);
        jDDisplayImageOptions.showImageForEmptyUri(i2);
        jDDisplayImageOptions.showImageOnLoading(i2);
        JDImageLoader.display(pdAnswerInfo.userImg, this.f4660h, jDDisplayImageOptions);
        if (wareImageQaEntity != null) {
            JDDisplayImageOptions jDDisplayImageOptions2 = new JDDisplayImageOptions();
            int i3 = com.jd.lib.productdetail.core.R.drawable.lib_pd_core_ask_icon;
            jDDisplayImageOptions2.showImageOnFail(i3);
            jDDisplayImageOptions2.showImageForEmptyUri(i3);
            jDDisplayImageOptions2.showImageOnLoading(i3);
            JDImageLoader.display(wareImageQaEntity.questionIcon, this.f4661i, jDDisplayImageOptions2);
        }
        this.f4662j.setText(pdAnswerInfo.nickName);
        this.f4664l.setText(pdAnswerInfo.content);
        JDImageUtils.displayImage(pdAnswerInfo.typeIcon, this.f4663k);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f4659g = (AppCompatTextView) findViewById(R.id.lib_pd_holder_topimage_item_ask_question_tv);
        this.f4660h = (SimpleDraweeView) findViewById(R.id.lib_pd_holder_topimage_item_ask_item_icon);
        this.f4662j = (AppCompatTextView) findViewById(R.id.lib_pd_holder_topimage_item_ask_item_user_name_tv);
        this.f4663k = (SimpleDraweeView) findViewById(R.id.lib_pd_holder_topimage_item_ask_item_user_img);
        this.f4664l = (AppCompatTextView) findViewById(R.id.lib_pd_holder_topimage_item_ask_item_detail_tv);
        this.f4661i = (SimpleDraweeView) findViewById(R.id.lib_pd_ask_icon);
    }
}
