package com.jd.lib.productdetail.mainimage.holder.ypsms;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdDrugInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.sdk.platform.floor.isv.DynCommonPopView;
import java.util.ArrayList;

/* loaded from: classes15.dex */
public class PdMYpsmsView extends ConstraintLayout {

    /* renamed from: g  reason: collision with root package name */
    public TextView f4976g;

    /* renamed from: h  reason: collision with root package name */
    public SimpleDraweeView f4977h;

    /* renamed from: i  reason: collision with root package name */
    public View f4978i;

    /* renamed from: j  reason: collision with root package name */
    public View f4979j;

    /* renamed from: k  reason: collision with root package name */
    public TextView f4980k;

    /* renamed from: l  reason: collision with root package name */
    public AppCompatTextView[] f4981l;

    /* renamed from: m  reason: collision with root package name */
    public PdMYpsmsDetailItemView[] f4982m;

    /* renamed from: n  reason: collision with root package name */
    public PdDrugInfo f4983n;
    public ViewGroup o;
    public String p;
    public boolean q;
    public PdMainImagePresenter r;

    /* loaded from: classes15.dex */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            ViewGroup viewGroup;
            if (!PdMYpsmsView.b(PdMYpsmsView.this) || (viewGroup = PdMYpsmsView.this.o) == null || viewGroup.getHeight() <= 0) {
                return;
            }
            int top = PdMYpsmsView.this.f4979j.getTop() - PDUtils.dip2px(3.0f);
            if (PdMYpsmsView.this.f4979j.getVisibility() == 8) {
                top = PdMYpsmsView.this.o.getHeight() - PDUtils.dip2px(3.0f);
            }
            PdMYpsmsDetailItemView[] pdMYpsmsDetailItemViewArr = PdMYpsmsView.this.f4982m;
            if (pdMYpsmsDetailItemViewArr != null && pdMYpsmsDetailItemViewArr[2] != null && pdMYpsmsDetailItemViewArr[2].getVisibility() == 0 && PdMYpsmsView.this.f4982m[2].getBottom() > top) {
                for (PdMYpsmsDetailItemView pdMYpsmsDetailItemView : PdMYpsmsView.this.f4982m) {
                    pdMYpsmsDetailItemView.f4975h.setMaxLines(1);
                }
            }
            AppCompatTextView[] appCompatTextViewArr = PdMYpsmsView.this.f4981l;
            if (appCompatTextViewArr != null && appCompatTextViewArr[2] != null && appCompatTextViewArr[2].getVisibility() == 0 && PdMYpsmsView.this.f4981l[2].getBottom() > top) {
                PdMYpsmsView.this.f4981l[2].setVisibility(8);
                AppCompatTextView[] appCompatTextViewArr2 = PdMYpsmsView.this.f4981l;
                if (appCompatTextViewArr2 != null && appCompatTextViewArr2[1] != null && appCompatTextViewArr2[1].getVisibility() == 0 && PdMYpsmsView.this.f4981l[1].getBottom() > top) {
                    PdMYpsmsView.this.f4981l[1].setVisibility(8);
                }
            }
            PdMYpsmsView.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    }

    /* loaded from: classes15.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PdMYpsmsView.this.r != null) {
                JDJSONObject jDJSONObject = new JDJSONObject();
                jDJSONObject.put("frame_info", (Object) "-100");
                jDJSONObject.put("rankid", (Object) "");
                jDJSONObject.put("touchstone_expids", (Object) "");
                jDJSONObject.put("click_pos", (Object) "-100");
                jDJSONObject.put("type", (Object) WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_YPSMS);
                jDJSONObject.put("QuesNum", (Object) "");
                PdDrugInfo pdDrugInfo = PdMYpsmsView.this.f4983n;
                if (pdDrugInfo != null && pdDrugInfo.tips != null) {
                    jDJSONObject.put("LableNum", (Object) (PdMYpsmsView.this.f4983n.tips.size() + ""));
                } else {
                    jDJSONObject.put("LableNum", (Object) "0");
                }
                if (!TextUtils.isEmpty(PdMYpsmsView.this.f4983n.imageInCell)) {
                    jDJSONObject.put("isPhoto", (Object) "1");
                } else {
                    jDJSONObject.put("isPhoto", (Object) "0");
                }
                PdMYpsmsView.this.r.mtaClick("Productdetail_FunctionEntrance", jDJSONObject.toJSONString());
                PdMYpsmsView.this.a();
            }
        }
    }

    public PdMYpsmsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.q = true;
    }

    public static boolean b(PdMYpsmsView pdMYpsmsView) {
        TextView textView = pdMYpsmsView.f4976g;
        return (textView == null || ViewTreeLifecycleOwner.get(textView) == null || ViewTreeLifecycleOwner.get(pdMYpsmsView.f4976g).getLifecycle() == null || ViewTreeLifecycleOwner.get(pdMYpsmsView.f4976g).getLifecycle().getCurrentState() == null || !ViewTreeLifecycleOwner.get(pdMYpsmsView.f4976g).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) ? false : true;
    }

    private void d(int i2) {
        this.f4977h.setVisibility(i2);
        for (AppCompatTextView appCompatTextView : this.f4981l) {
            appCompatTextView.setVisibility(i2);
        }
    }

    public void a() {
        DynCommonPopView.openPopview(this.o.getContext(), this.r.getMainImageParams().moduleName, this.p, null, this.r.iCommonBasicAbility);
    }

    public void c(PdMainImagePresenter pdMainImagePresenter) {
        this.r = pdMainImagePresenter;
    }

    public void e(String str) {
        this.p = str;
    }

    public void f(PdDrugInfo pdDrugInfo) {
        ArrayList<String> arrayList;
        this.f4983n = pdDrugInfo;
        if (pdDrugInfo != null) {
            for (PdMYpsmsDetailItemView pdMYpsmsDetailItemView : this.f4982m) {
                pdMYpsmsDetailItemView.f4975h.setMaxLines(2);
            }
            String str = pdDrugInfo.title;
            if (TextUtils.isEmpty(str)) {
                this.f4976g.setVisibility(8);
            } else {
                this.f4976g.setVisibility(0);
                this.f4976g.setText(str);
            }
            if (pdDrugInfo.showDetail && this.q) {
                this.f4978i.setVisibility(0);
                this.f4979j.setVisibility(0);
                this.f4978i.setEnabled(true);
                String str2 = pdDrugInfo.jumpButtonText;
                if (TextUtils.isEmpty(str2)) {
                    str2 = getResources().getString(R.string.lib_pd_image_topimage_item_holder_ypsms_btn_ok_text);
                }
                this.f4980k.setText(str2);
            } else {
                this.f4978i.setVisibility(8);
                this.f4979j.setVisibility(8);
                this.f4978i.setEnabled(false);
            }
            if ((TextUtils.isEmpty(pdDrugInfo.imageInCell) || (arrayList = pdDrugInfo.tips) == null || arrayList.size() <= 1) ? false : true) {
                d(0);
                JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
                createSimple.setRoundingParams(RoundingParams.fromCornersRadius(PDUtils.dip2px(8.0f)));
                JDImageLoader.display(pdDrugInfo.imageInCell, this.f4977h, createSimple);
                for (int i2 = 0; i2 < 3; i2++) {
                    if (i2 < pdDrugInfo.tips.size()) {
                        this.f4981l[i2].setVisibility(0);
                        this.f4981l[i2].setText(pdDrugInfo.tips.get(i2));
                    } else {
                        this.f4981l[i2].setVisibility(8);
                    }
                }
            } else {
                d(8);
            }
            ArrayList<PdDrugInfo.DrugDetails> arrayList2 = pdDrugInfo.drugDetails;
            if (arrayList2 != null && arrayList2.size() > 0) {
                for (int i3 = 0; i3 < 3; i3++) {
                    if (i3 < pdDrugInfo.drugDetails.size()) {
                        PdDrugInfo.DrugDetails drugDetails = pdDrugInfo.drugDetails.get(i3);
                        if (!TextUtils.isEmpty(drugDetails.title) && !TextUtils.isEmpty(drugDetails.desc)) {
                            this.f4982m[i3].setVisibility(0);
                            this.f4982m[i3].a(drugDetails);
                        } else {
                            this.f4982m[i3].setVisibility(8);
                        }
                    } else {
                        this.f4982m[i3].setVisibility(8);
                    }
                }
            }
            getViewTreeObserver().addOnGlobalLayoutListener(new a());
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.o = (ViewGroup) findViewById(R.id.lib_pd_holder_topimage_item_ypsms_content);
        this.f4976g = (TextView) findViewById(R.id.lib_pd_holder_topimage_item_ypsms_content_title);
        this.f4977h = (SimpleDraweeView) findViewById(R.id.lib_pd_holder_topimage_item_ypsms_content_pic);
        this.f4981l = new AppCompatTextView[]{(AppCompatTextView) findViewById(R.id.lib_pd_holder_topimage_item_ypsms_content_tips_1), (AppCompatTextView) findViewById(R.id.lib_pd_holder_topimage_item_ypsms_content_tips_2), (AppCompatTextView) findViewById(R.id.lib_pd_holder_topimage_item_ypsms_content_tips_3)};
        this.f4982m = new PdMYpsmsDetailItemView[]{(PdMYpsmsDetailItemView) findViewById(R.id.lib_pd_holder_topimage_item_ypsms_detail_item_1), (PdMYpsmsDetailItemView) findViewById(R.id.lib_pd_holder_topimage_item_ypsms_detail_item_2), (PdMYpsmsDetailItemView) findViewById(R.id.lib_pd_holder_topimage_item_ypsms_detail_item_3)};
        this.f4979j = findViewById(R.id.lib_pd_holder_topimage_item_comment_content_divider);
        this.f4978i = findViewById(R.id.lib_pd_holder_topimage_item_comment_content_btn_ok);
        this.f4980k = (TextView) findViewById(R.id.lib_pd_holder_topimage_item_comment_content_btn_ok_text);
        this.f4978i.setOnClickListener(new b());
    }
}
