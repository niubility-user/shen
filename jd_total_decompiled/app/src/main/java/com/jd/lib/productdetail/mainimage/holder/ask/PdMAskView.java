package com.jd.lib.productdetail.mainimage.holder.ask;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdAnswerInfo;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentInfo;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdQuestionInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareImageQaEntity;
import com.jd.lib.productdetail.core.utils.OpenAppUtils;
import com.jd.lib.productdetail.core.utils.PDBaseDeepLinkHelper;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMAskView extends RelativeLayout {
    public static final /* synthetic */ int B = 0;
    public PdMainImagePresenter A;

    /* renamed from: g */
    public a f4665g;

    /* renamed from: h */
    public AppCompatTextView f4666h;

    /* renamed from: i */
    public PdMAskItemView f4667i;

    /* renamed from: j */
    public PdMAskItemView f4668j;

    /* renamed from: k */
    public View f4669k;

    /* renamed from: l */
    public LinearLayout f4670l;

    /* renamed from: m */
    public WareBusinessUnitMainImageEntity f4671m;

    /* renamed from: n */
    public TextView f4672n;
    public Context o;
    public PdCommentInfo p;
    public SimpleDraweeView q;
    public int r;
    public RelativeLayout s;
    public boolean t;
    public ArrayList<PdQuestionInfo> u;
    public WareImageQaEntity v;
    public String w;
    public int x;
    public int y;
    public int z;

    /* loaded from: classes15.dex */
    public interface a {
    }

    public PdMAskView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.t = false;
        this.w = "openapp.jdmobile://virtual?params={\"category\":\"jump\",\"des\":\"commune\",\"subDes\":\"medicineList\",\"skuId\":\"%1$s\",\"categoryId1\":\"%2$s\",\"categoryId2\":\"%3$s\",\"categoryId3\":\"%4$s\",\"source\":\"shx\"}";
        this.o = context;
    }

    public /* synthetic */ void b(View view) {
        e();
    }

    public final void a() {
        this.s.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.mainimage.holder.ask.a
            {
                PdMAskView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PdMAskView.this.b(view);
            }
        });
    }

    public void c(PdCommentInfo pdCommentInfo, WareImageQaEntity wareImageQaEntity) {
        List<PdQuestionInfo> list;
        ArrayList<PdQuestionInfo> arrayList;
        PdMainImagePresenter pdMainImagePresenter;
        List<PdAnswerInfo> list2;
        this.p = pdCommentInfo;
        this.v = wareImageQaEntity;
        this.u = new ArrayList<>();
        PdMainImagePresenter pdMainImagePresenter2 = this.A;
        if (pdMainImagePresenter2 != null) {
            pdMainImagePresenter2.mAskQuesInfos = new ArrayList<>();
        }
        this.t = false;
        WareImageQaEntity wareImageQaEntity2 = this.v;
        if (wareImageQaEntity2 != null && TextUtils.isEmpty(wareImageQaEntity2.title)) {
            this.v.title = this.o.getString(R.string.lib_pd_image_doc_answer_your_question);
        }
        if (pdCommentInfo != null && (list = pdCommentInfo.questionList) != null && list.size() != 0) {
            for (int i2 = 0; i2 < pdCommentInfo.questionList.size(); i2++) {
                PdQuestionInfo pdQuestionInfo = pdCommentInfo.questionList.get(i2);
                if (pdQuestionInfo != null && !TextUtils.isEmpty(pdQuestionInfo.content) && (list2 = pdQuestionInfo.answerList) != null && list2.size() > 0 && pdQuestionInfo.answerList.get(0) != null && !TextUtils.isEmpty(pdQuestionInfo.answerList.get(0).content)) {
                    PdQuestionInfo pdQuestionInfo2 = new PdQuestionInfo();
                    pdQuestionInfo2.content = pdQuestionInfo.content;
                    pdQuestionInfo2.questionIcon = pdQuestionInfo.questionIcon;
                    pdQuestionInfo2.answerList = pdQuestionInfo.answerList;
                    this.t = TextUtils.equals(pdQuestionInfo.answerList.get(0).systemId, "21");
                    if (this.u.size() < 2) {
                        this.u.add(pdQuestionInfo2);
                    }
                }
            }
            int i3 = R2.attr.additionBottom;
            if (getContext() != null && (getContext() instanceof Activity) && (pdMainImagePresenter = this.A) != null) {
                i3 = PDUtils.px2dp(pdMainImagePresenter.appImageWidth);
            }
            if (this.u.size() == 2) {
                if (i3 > 340) {
                    this.f4668j.setVisibility(0);
                    this.f4667i.a(this.u.get(0), wareImageQaEntity);
                    this.f4668j.a(this.u.get(1), wareImageQaEntity);
                    PdMainImagePresenter pdMainImagePresenter3 = this.A;
                    if (pdMainImagePresenter3 != null && pdMainImagePresenter3.mAskQuesInfos != null) {
                        pdMainImagePresenter3.mAskQuesInfos = this.u;
                    }
                } else {
                    this.f4667i.setVisibility(0);
                    this.f4668j.setVisibility(8);
                    this.f4667i.a(this.u.get(0), wareImageQaEntity);
                    PdMainImagePresenter pdMainImagePresenter4 = this.A;
                    if (pdMainImagePresenter4 != null && (arrayList = pdMainImagePresenter4.mAskQuesInfos) != null) {
                        arrayList.add(this.u.get(0));
                    }
                }
                this.f4667i.setVisibility(0);
                d(false);
                return;
            }
            this.f4667i.setVisibility(8);
            this.f4668j.setVisibility(8);
            d(true);
            return;
        }
        this.f4667i.setVisibility(8);
        this.f4668j.setVisibility(8);
        d(true);
    }

    public final void d(boolean z) {
        WareImageQaEntity wareImageQaEntity = this.v;
        if (wareImageQaEntity != null) {
            this.f4672n.setText(wareImageQaEntity.buttonText);
            this.f4666h.setText(this.v.title);
        }
        if (z) {
            this.q.setVisibility(0);
            WareImageQaEntity wareImageQaEntity2 = this.v;
            if (wareImageQaEntity2 != null) {
                JDImageUtils.displayImage(wareImageQaEntity2.defaultImage, this.q);
            }
        } else {
            this.q.setVisibility(8);
        }
        if (this.r != 1) {
            WareImageQaEntity wareImageQaEntity3 = this.v;
            if (wareImageQaEntity3 == null || TextUtils.isEmpty(wareImageQaEntity3.buttonText)) {
                this.f4670l.setVisibility(4);
                this.f4669k.setVisibility(4);
            } else if (z) {
                this.f4670l.setVisibility(8);
                this.f4669k.setVisibility(8);
            } else {
                this.f4670l.setVisibility(0);
                this.f4669k.setVisibility(0);
            }
        } else if (z) {
            this.f4670l.setVisibility(8);
            this.f4669k.setVisibility(8);
        } else {
            this.f4670l.setVisibility(4);
            this.f4669k.setVisibility(4);
        }
        this.f4666h.setVisibility(0);
    }

    public void e() {
        WareImageQaEntity wareImageQaEntity;
        PdMAskViewHolder pdMAskViewHolder;
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity;
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity;
        Context context;
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity;
        if (!PDUtils.repeatClick() || (wareImageQaEntity = this.v) == null || TextUtils.isEmpty(wareImageQaEntity.buttonText)) {
            return;
        }
        SimpleDraweeView simpleDraweeView = this.q;
        if (simpleDraweeView != null && simpleDraweeView.getVisibility() == 8) {
            PdCommentInfo pdCommentInfo = this.p;
            if (pdCommentInfo != null && (wareBusinessUnitMainImageEntity = this.f4671m) != null && (context = this.o) != null && (extMapEntity = wareBusinessUnitMainImageEntity.extMap) != null) {
                if (this.t) {
                    WareBusinessUnitMainImageEntity.ExtMapEntity.CategoryEntity categoryEntity = extMapEntity.category;
                    if (categoryEntity != null) {
                        this.x = categoryEntity.fstId;
                        this.y = categoryEntity.sndId;
                        this.z = categoryEntity.thdId;
                    }
                    OpenAppUtils.openAppForInner(this.o, String.format(this.w, extMapEntity.skuId, Integer.valueOf(this.x), Integer.valueOf(this.y), Integer.valueOf(this.z)));
                } else {
                    PDBaseDeepLinkHelper.startBuyAskWithRouter(context, extMapEntity.skuId, pdCommentInfo.biData);
                }
            }
        } else {
            WareImageQaEntity wareImageQaEntity2 = this.v;
            if (wareImageQaEntity2 != null && !TextUtils.isEmpty(wareImageQaEntity2.jumpUrl)) {
                OpenAppUtils.openAppForInner(this.o, this.v.jumpUrl);
            }
        }
        a aVar = this.f4665g;
        if (aVar == null || (wareBusinessMagicHeadPicInfoEntity = (pdMAskViewHolder = ((b) aVar).a).r) == null) {
            return;
        }
        pdMAskViewHolder.l(wareBusinessMagicHeadPicInfoEntity.anchorType, -100);
    }

    public void g(int i2) {
        if (this.f4669k == null || this.f4670l == null || this.f4667i == null || this.f4668j == null) {
            return;
        }
        this.r = i2;
        if (i2 == 1) {
            this.s.setClickable(false);
        } else {
            this.s.setClickable(true);
        }
        this.f4666h.setClickable(false);
        this.f4670l.setClickable(false);
        this.f4667i.setClickable(false);
        this.f4668j.setClickable(false);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f4666h = (AppCompatTextView) findViewById(R.id.lib_pd_holder_topimage_item_ask_content_title);
        this.s = (RelativeLayout) findViewById(R.id.lib_pd_top_image_ask_item_rl);
        this.f4667i = (PdMAskItemView) findViewById(R.id.lib_pd_holder_topimage_item_ask_item_1);
        this.f4668j = (PdMAskItemView) findViewById(R.id.lib_pd_holder_topimage_item_ask_item_2);
        this.f4669k = findViewById(R.id.lib_pd_holder_topimage_item_ask_content_divider);
        this.f4670l = (LinearLayout) findViewById(R.id.lib_pd_holder_topimage_item_ask_content_btn_ok);
        this.f4672n = (TextView) findViewById(R.id.lib_pd_topimage_item_holder_ask_btn_ok_text);
        this.q = (SimpleDraweeView) findViewById(R.id.lib_pd_ask_default_image);
        a();
    }
}
