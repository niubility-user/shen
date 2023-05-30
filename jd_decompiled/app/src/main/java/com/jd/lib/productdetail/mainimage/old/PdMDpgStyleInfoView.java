package com.jd.lib.productdetail.mainimage.old;

import android.app.Activity;
import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import com.jd.lib.productdetail.core.entitys.PdDpgListLayerInfo;
import com.jd.lib.productdetail.core.entitys.suitstyle.PDPackColorSizeEntity;
import com.jd.lib.productdetail.core.entitys.suitstyle.PDPackStyleEntity;
import com.jd.lib.productdetail.core.entitys.suitstyle.PDPackStyleWareEntity;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jd.lib.productdetail.mainimage.protocol.b;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.display.JDRoundedBitmapDisplayer;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.entity.productdetail.PDStyleFilterEntity;
import com.jingdong.common.entity.productdetail.PDStylePropertyEntity;
import com.jingdong.common.ui.PDStyleBubbleItemView;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.widget.PDFlowLayout;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMDpgStyleInfoView extends RelativeLayout implements View.OnClickListener {
    public static final /* synthetic */ int H = 0;
    public List<List<PDStyleBubbleItemView>> A;
    public List<PDStylePropertyEntity> B;
    public PDStyleBubbleItemView[] C;
    public TextView[] D;
    public PDPackStyleWareEntity E;
    public c F;
    public String G;

    /* renamed from: g  reason: collision with root package name */
    public Context f5023g;

    /* renamed from: h  reason: collision with root package name */
    public RelativeLayout f5024h;

    /* renamed from: i  reason: collision with root package name */
    public RelativeLayout f5025i;

    /* renamed from: j  reason: collision with root package name */
    public ImageView f5026j;

    /* renamed from: k  reason: collision with root package name */
    public TextView f5027k;

    /* renamed from: l  reason: collision with root package name */
    public TextView f5028l;

    /* renamed from: m  reason: collision with root package name */
    public LinearLayout f5029m;

    /* renamed from: n  reason: collision with root package name */
    public ScrollView f5030n;
    public FrameLayout o;
    public JDDisplayImageOptions p;
    public ArrayList<String> q;
    public int r;
    public ImageView s;
    public TextView t;
    public boolean u;
    public PdMainImagePresenter v;
    public String w;
    public String x;
    public String y;
    public List<PDStyleFilterEntity> z;

    /* loaded from: classes15.dex */
    public class a implements Observer<PdBaseProtocolLiveData.Result<PDPackStyleEntity>> {

        /* renamed from: g  reason: collision with root package name */
        public final /* synthetic */ com.jd.lib.productdetail.mainimage.protocol.b f5031g;

        public a(com.jd.lib.productdetail.mainimage.protocol.b bVar) {
            this.f5031g = bVar;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(PdBaseProtocolLiveData.Result<PDPackStyleEntity> result) {
            List<PDStyleFilterEntity> list;
            PdBaseProtocolLiveData.Result<PDPackStyleEntity> result2 = result;
            if (result2 != null) {
                if (result2.mStatus == PdBaseProtocolLiveData.Result.DataStatus.SUCCESS) {
                    PDPackStyleEntity pDPackStyleEntity = result2.mData;
                    if (pDPackStyleEntity != null) {
                        PdMDpgStyleInfoView.this.E = pDPackStyleEntity.wareInfo;
                    }
                    PdMDpgStyleInfoView pdMDpgStyleInfoView = PdMDpgStyleInfoView.this;
                    PDPackStyleWareEntity pDPackStyleWareEntity = pdMDpgStyleInfoView.E;
                    if (pDPackStyleWareEntity == null) {
                        return;
                    }
                    pdMDpgStyleInfoView.w = pDPackStyleWareEntity.wareId;
                    ArrayList<String> arrayList = pdMDpgStyleInfoView.q;
                    if (arrayList != null) {
                        arrayList.clear();
                    }
                    PdMDpgStyleInfoView pdMDpgStyleInfoView2 = PdMDpgStyleInfoView.this;
                    PDPackStyleWareEntity pDPackStyleWareEntity2 = pdMDpgStyleInfoView2.E;
                    List<String> list2 = pDPackStyleWareEntity2.bigImage;
                    if (list2 != null) {
                        pdMDpgStyleInfoView2.q = (ArrayList) list2;
                    }
                    PDPackColorSizeEntity pDPackColorSizeEntity = pDPackStyleWareEntity2.colorSizeInfo;
                    if (pDPackColorSizeEntity != null && pdMDpgStyleInfoView2.u && (list = pDPackColorSizeEntity.colorSize) != null && !list.isEmpty()) {
                        if (!PdMDpgStyleInfoView.this.z.isEmpty()) {
                            PdMDpgStyleInfoView.this.z.clear();
                        }
                        PdMDpgStyleInfoView.this.z.addAll(pDPackColorSizeEntity.colorSize);
                    }
                    PdMDpgStyleInfoView pdMDpgStyleInfoView3 = PdMDpgStyleInfoView.this;
                    PDPackStyleWareEntity pDPackStyleWareEntity3 = pdMDpgStyleInfoView3.E;
                    String str = pDPackStyleWareEntity3.image;
                    String str2 = pDPackStyleWareEntity3.price;
                    if (!TextUtils.isEmpty(str)) {
                        JDImageUtils.displayImage(str, pdMDpgStyleInfoView3.f5026j, pdMDpgStyleInfoView3.p);
                    }
                    pdMDpgStyleInfoView3.f5028l.setText(pdMDpgStyleInfoView3.getResources().getString(R.string.lib_pd_image_style_sku_800, pdMDpgStyleInfoView3.w));
                    String string = pdMDpgStyleInfoView3.f5023g.getString(R.string.lib_pd_image_no_price);
                    if (!TextUtils.isEmpty(str2)) {
                        try {
                            Double valueOf = Double.valueOf(Double.parseDouble(str2));
                            if (valueOf != null && valueOf.doubleValue() > 0.0d) {
                                string = new DecimalFormat("0.00").format(valueOf);
                            }
                        } catch (Exception e2) {
                            if (Log.D) {
                                Log.d(jpbury.t.f20145j, e2.getMessage());
                            }
                            string = pdMDpgStyleInfoView3.f5023g.getString(R.string.lib_pd_image_no_price);
                        }
                    }
                    String charSequence = PDUtils.getPrice(string, false).toString();
                    Context context = pdMDpgStyleInfoView3.f5023g;
                    int i2 = R.string.lib_pd_image_no_price;
                    if (TextUtils.equals(charSequence, context.getString(i2))) {
                        charSequence = "\u00a5" + pdMDpgStyleInfoView3.f5023g.getString(i2);
                    }
                    SpannableString spannableString = new SpannableString(charSequence);
                    pdMDpgStyleInfoView3.f5027k.setTextColor(ContextCompat.getColor(pdMDpgStyleInfoView3.f5023g, R.color.lib_pd_image_color_FA2C19));
                    try {
                        if (charSequence.startsWith("\u00a5")) {
                            spannableString.setSpan(new AbsoluteSizeSpan(16, true), 0, 1, 18);
                            int indexOf = charSequence.indexOf(OrderISVUtil.MONEY_DECIMAL);
                            if (indexOf > 0) {
                                spannableString.setSpan(new AbsoluteSizeSpan(24, true), 1, indexOf, 18);
                                spannableString.setSpan(new AbsoluteSizeSpan(16, true), indexOf, charSequence.length(), 18);
                            } else if (indexOf < 0 && charSequence.length() > 1) {
                                spannableString.setSpan(new AbsoluteSizeSpan(24, true), 1, charSequence.length(), 18);
                            }
                        }
                    } catch (Exception e3) {
                        if (Log.D) {
                            Log.d("Exception", e3.getMessage());
                        }
                    }
                    pdMDpgStyleInfoView3.f5027k.setText(spannableString);
                    FrameLayout frameLayout = pdMDpgStyleInfoView3.o;
                    if (frameLayout != null) {
                        frameLayout.setVisibility(8);
                    }
                    List<PDStyleFilterEntity> list3 = pdMDpgStyleInfoView3.z;
                    boolean z = pdMDpgStyleInfoView3.u;
                    if (list3 != null && !list3.isEmpty() && z) {
                        if (!pdMDpgStyleInfoView3.B.isEmpty()) {
                            pdMDpgStyleInfoView3.B.clear();
                        }
                        int size = list3.size();
                        for (int i3 = 0; i3 < size; i3++) {
                            PDStyleFilterEntity pDStyleFilterEntity = list3.get(i3);
                            List<PDStylePropertyEntity> list4 = pDStyleFilterEntity.buttons;
                            for (int i4 = 0; i4 < list4.size(); i4++) {
                                PDStylePropertyEntity pDStylePropertyEntity = list4.get(i4);
                                pDStylePropertyEntity.title = pDStyleFilterEntity.title;
                                if (pDStylePropertyEntity.skuList.contains(pdMDpgStyleInfoView3.w)) {
                                    pDStylePropertyEntity.isSelect = true;
                                    pDStylePropertyEntity.status = 1;
                                    pdMDpgStyleInfoView3.B.add(pDStylePropertyEntity);
                                }
                            }
                            if (i3 > pdMDpgStyleInfoView3.B.size() - 1) {
                                PDStylePropertyEntity pDStylePropertyEntity2 = new PDStylePropertyEntity();
                                pDStylePropertyEntity2.status = 0;
                                pDStylePropertyEntity2.isSelect = false;
                                pDStylePropertyEntity2.title = pDStyleFilterEntity.title;
                                pdMDpgStyleInfoView3.B.add(pDStylePropertyEntity2);
                            }
                        }
                        if (size > 1) {
                            for (int i5 = 0; i5 < size; i5++) {
                                List<PDStylePropertyEntity> list5 = list3.get(i5).buttons;
                                for (int i6 = 0; i6 < list5.size(); i6++) {
                                    PDStylePropertyEntity pDStylePropertyEntity3 = list5.get(i6);
                                    ArrayList arrayList2 = new ArrayList(pdMDpgStyleInfoView3.c(i5, false));
                                    arrayList2.retainAll(pDStylePropertyEntity3.skuList);
                                    pDStylePropertyEntity3.isDash = arrayList2.isEmpty();
                                }
                            }
                        }
                        List<PDStylePropertyEntity> list6 = pdMDpgStyleInfoView3.B;
                        if (list6 != null && !list6.isEmpty()) {
                            pdMDpgStyleInfoView3.f5029m.setVisibility(0);
                            int size2 = pdMDpgStyleInfoView3.z.size();
                            pdMDpgStyleInfoView3.C = new PDStyleBubbleItemView[size2];
                            pdMDpgStyleInfoView3.D = new TextView[size2];
                            if (pdMDpgStyleInfoView3.f5029m.getChildCount() != 0) {
                                pdMDpgStyleInfoView3.f5029m.removeAllViews();
                                pdMDpgStyleInfoView3.A.clear();
                            }
                            for (int i7 = 0; i7 < size2; i7++) {
                                PDStyleFilterEntity pDStyleFilterEntity2 = pdMDpgStyleInfoView3.z.get(i7);
                                if (pDStyleFilterEntity2 != null) {
                                    pdMDpgStyleInfoView3.f5029m.addView(pdMDpgStyleInfoView3.a(i7, pDStyleFilterEntity2));
                                }
                            }
                        } else {
                            pdMDpgStyleInfoView3.f5029m.setVisibility(8);
                        }
                    }
                    PdMDpgStyleInfoView pdMDpgStyleInfoView4 = PdMDpgStyleInfoView.this;
                    pdMDpgStyleInfoView4.t.setEnabled(pdMDpgStyleInfoView4.E.cartFlag);
                    PdMDpgStyleInfoView pdMDpgStyleInfoView5 = PdMDpgStyleInfoView.this;
                    if (!pdMDpgStyleInfoView5.E.cartFlag) {
                        Context context2 = pdMDpgStyleInfoView5.f5023g;
                        PDUtils.showToastCenterNormal(context2, context2.getString(R.string.lib_pd_image_style_dpg_nostock));
                    }
                }
                PdBaseProtocolLiveData.Result.DataStatus dataStatus = result2.mStatus;
                if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.SUCCESS || dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FAIL) {
                    this.f5031g.mResult.removeObserver(this);
                    return;
                }
                return;
            }
            this.f5031g.mResult.removeObserver(this);
        }
    }

    /* loaded from: classes15.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PdMDpgStyleInfoView.d(PdMDpgStyleInfoView.this, view);
        }
    }

    /* loaded from: classes15.dex */
    public interface c {
        void a(PdDpgListLayerInfo.DetailBean.ItemsBean itemsBean);
    }

    public PdMDpgStyleInfoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.z = new ArrayList();
        this.A = new ArrayList();
        this.B = new ArrayList();
        this.f5023g = context;
    }

    public static void d(PdMDpgStyleInfoView pdMDpgStyleInfoView, View view) {
        pdMDpgStyleInfoView.getClass();
        if (view == null) {
            return;
        }
        PDStyleBubbleItemView pDStyleBubbleItemView = (PDStyleBubbleItemView) view;
        PDStylePropertyEntity pDStylePropertyEntity = (PDStylePropertyEntity) view.getTag();
        if (pDStylePropertyEntity == null || pDStylePropertyEntity.isDash) {
            return;
        }
        int i2 = pDStylePropertyEntity.position;
        PDStyleBubbleItemView pDStyleBubbleItemView2 = pdMDpgStyleInfoView.C[i2];
        if (pDStyleBubbleItemView2 != null) {
            ((PDStylePropertyEntity) pDStyleBubbleItemView2.getTag()).status = 0;
            pDStyleBubbleItemView2.setItemSelected(false);
        }
        pDStylePropertyEntity.status = 1;
        pDStyleBubbleItemView.setItemSelected(true);
        pdMDpgStyleInfoView.D[pDStylePropertyEntity.position].setVisibility(8);
        pdMDpgStyleInfoView.C[pDStylePropertyEntity.position] = pDStyleBubbleItemView;
        pdMDpgStyleInfoView.B.set(i2, pDStylePropertyEntity);
        String f2 = pdMDpgStyleInfoView.f();
        pdMDpgStyleInfoView.w = f2;
        if (!TextUtils.isEmpty(f2)) {
            FrameLayout frameLayout = pdMDpgStyleInfoView.o;
            if (frameLayout != null) {
                frameLayout.setVisibility(0);
            }
            pdMDpgStyleInfoView.e(false);
        }
        pDStyleBubbleItemView.setItemContent(pDStylePropertyEntity);
        for (int i3 = 0; i3 < pdMDpgStyleInfoView.z.size(); i3++) {
            List<PDStylePropertyEntity> list = pdMDpgStyleInfoView.z.get(i3).buttons;
            List<PDStyleBubbleItemView> list2 = pdMDpgStyleInfoView.A.get(i3);
            if (list != null) {
                for (int i4 = 0; i4 < list.size(); i4++) {
                    PDStylePropertyEntity pDStylePropertyEntity2 = list.get(i4);
                    if (i3 != pDStylePropertyEntity.position && pDStylePropertyEntity2 != null) {
                        ArrayList arrayList = new ArrayList(pdMDpgStyleInfoView.c(pDStylePropertyEntity2.position, false));
                        arrayList.retainAll(pDStylePropertyEntity2.skuList);
                        pDStylePropertyEntity2.isDash = arrayList.isEmpty();
                    }
                    if (pDStylePropertyEntity2 != null && list2 != null && !list2.isEmpty()) {
                        list2.get(i4).setItemDash(pDStylePropertyEntity2);
                    }
                }
            }
        }
    }

    private String f() {
        boolean z = true;
        ArrayList arrayList = null;
        for (int i2 = 0; i2 < this.B.size(); i2++) {
            PDStylePropertyEntity pDStylePropertyEntity = this.B.get(i2);
            if (pDStylePropertyEntity != null) {
                if (pDStylePropertyEntity.status != 0) {
                    List<String> list = pDStylePropertyEntity.skuList;
                    ArrayList arrayList2 = list != null ? new ArrayList(list) : new ArrayList();
                    if (arrayList != null) {
                        arrayList.retainAll(arrayList2);
                    } else {
                        arrayList = arrayList2;
                    }
                } else {
                    z = false;
                }
            }
        }
        if (!z || arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        return (String) arrayList.get(0);
    }

    public final LinearLayout a(int i2, PDStyleFilterEntity pDStyleFilterEntity) {
        Context context;
        int i3;
        Context context2;
        int i4;
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.f5023g).inflate(com.jingdong.common.R.layout.lib_style_item_info, (ViewGroup) null);
        TextView textView = (TextView) linearLayout.findViewById(com.jingdong.common.R.id.detail_style_item_title);
        linearLayout.setPadding(linearLayout.getPaddingLeft(), linearLayout.getPaddingTop() + PDUtils.dip2px(18.0f), linearLayout.getPaddingRight(), linearLayout.getPaddingRight());
        if (this.v.getMainImageParams().isDark) {
            context = this.f5023g;
            i3 = R.color.lib_pd_image_color_1d1b1b;
        } else {
            context = this.f5023g;
            i3 = R.color.lib_pd_image_white;
        }
        linearLayout.setBackgroundColor(ContextCompat.getColor(context, i3));
        textView.setText(pDStyleFilterEntity.title);
        if (this.v.getMainImageParams().isDark) {
            context2 = this.f5023g;
            i4 = R.color.lib_pd_image_color_ececec;
        } else {
            context2 = this.f5023g;
            i4 = R.color.lib_pd_image_color_1d1b1b;
        }
        textView.setTextColor(ContextCompat.getColor(context2, i4));
        TextView textView2 = (TextView) linearLayout.findViewById(com.jingdong.common.R.id.detail_style_item_tip);
        textView2.setText(String.format(getResources().getString(R.string.lib_pd_image_style_unselect_title), pDStyleFilterEntity.title));
        TextView[] textViewArr = this.D;
        textViewArr[i2] = textView2;
        if (textViewArr[i2].getVisibility() == 0) {
            this.D[i2].setVisibility(8);
        }
        PDFlowLayout pDFlowLayout = (PDFlowLayout) linearLayout.getChildAt(1);
        if (pDFlowLayout != null) {
            Context context3 = this.f5023g;
            if (context3 instanceof Activity) {
                pDFlowLayout.setActivity((Activity) context3);
            }
        }
        if (pDFlowLayout != null && pDFlowLayout.getChildCount() != 0) {
            pDFlowLayout.removeAllViews();
        }
        if (pDFlowLayout != null) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, PDUtils.dip2px(31.0f));
            layoutParams.rightMargin = PDUtils.dip2px(9.0f);
            int size = pDStyleFilterEntity.buttons.size();
            ArrayList arrayList = new ArrayList(size);
            for (int i5 = 0; i5 < size; i5++) {
                PDStylePropertyEntity pDStylePropertyEntity = pDStyleFilterEntity.buttons.get(i5);
                PDStyleBubbleItemView b2 = b(pDStylePropertyEntity);
                arrayList.add(b2);
                b2.setItemDash(pDStylePropertyEntity);
                if (pDStylePropertyEntity.isSelect) {
                    b2.setSelected(true);
                    this.C[i2] = b2;
                }
                pDStylePropertyEntity.position = i2;
                b2.setTag(pDStylePropertyEntity);
                pDFlowLayout.addView(b2, layoutParams);
            }
            this.A.add(arrayList);
        }
        return linearLayout;
    }

    public final PDStyleBubbleItemView b(PDStylePropertyEntity pDStylePropertyEntity) {
        PDStyleBubbleItemView pDStyleBubbleItemView = (PDStyleBubbleItemView) LayoutInflater.from(this.f5023g).inflate(com.jingdong.sdk.lib.stylecolorsize.R.layout.lib_pd_style_bubble_item, (ViewGroup) null);
        pDStyleBubbleItemView.initView(0, false);
        pDStyleBubbleItemView.setDarkTheme(this.v.getMainImageParams().isDark);
        pDStyleBubbleItemView.setItemContent(pDStylePropertyEntity);
        pDStyleBubbleItemView.setItemBubble(pDStylePropertyEntity.ktyf);
        pDStyleBubbleItemView.setOnClickListener(new b());
        return pDStyleBubbleItemView;
    }

    public final List<String> c(int i2, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < this.B.size(); i3++) {
            PDStylePropertyEntity pDStylePropertyEntity = this.B.get(i3);
            if ((i2 != i3 || z) && pDStylePropertyEntity != null && pDStylePropertyEntity.status != 0) {
                List<String> list = pDStylePropertyEntity.skuList;
                if (arrayList.isEmpty()) {
                    arrayList.addAll(list);
                } else {
                    arrayList.retainAll(list);
                }
            }
        }
        return arrayList;
    }

    public final void e(boolean z) {
        this.u = z;
        this.E = null;
        com.jd.lib.productdetail.mainimage.protocol.b bVar = new com.jd.lib.productdetail.mainimage.protocol.b();
        b.a aVar = new b.a();
        aVar.a = this.w;
        aVar.b = z;
        if (TextUtils.isEmpty(this.y)) {
            this.y = "matchbuy";
        }
        aVar.f5210c = this.y;
        aVar.d = this.x;
        aVar.f5211e = this.G;
        bVar.a = aVar;
        bVar.request((BaseActivity) getContext());
        bVar.mResult.observe((BaseActivity) getContext(), new a(bVar));
    }

    public void g(PdMainImagePresenter pdMainImagePresenter) {
        this.v = pdMainImagePresenter;
    }

    public void h(c cVar) {
        this.F = cVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:60:? A[RETURN, SYNTHETIC] */
    @Override // android.view.View.OnClickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onClick(android.view.View r7) {
        /*
            r6 = this;
            int r7 = r7.getId()
            int r0 = com.jd.lib.productdetail.mainimage.R.id.pack_style_cancel
            r1 = 0
            if (r0 != r7) goto L12
            com.jd.lib.productdetail.mainimage.old.PdMDpgStyleInfoView$c r7 = r6.F
            if (r7 == 0) goto Ld5
            r7.a(r1)
            goto Ld5
        L12:
            int r0 = com.jd.lib.productdetail.mainimage.R.id.pack_style_icon
            r2 = 0
            r3 = 1
            if (r0 != r7) goto L30
            java.util.ArrayList<java.lang.String> r7 = r6.q
            if (r7 == 0) goto Ld5
            boolean r7 = r7.isEmpty()
            if (r7 != 0) goto Ld5
            int r7 = r6.r
            int r7 = r7 + r3
            r6.r = r7
            android.content.Context r0 = r6.f5023g
            java.util.ArrayList<java.lang.String> r1 = r6.q
            com.jd.lib.productdetail.core.utils.PDBaseDeepLinkHelper.startImageActivity(r0, r2, r7, r1)
            goto Ld5
        L30:
            int r0 = com.jd.lib.productdetail.mainimage.R.id.pack_style_btn
            if (r0 != r7) goto Ld5
            java.util.List<com.jingdong.common.entity.productdetail.PDStylePropertyEntity> r7 = r6.B
            if (r7 == 0) goto L7e
            boolean r7 = r7.isEmpty()
            if (r7 != 0) goto L7e
            java.lang.StringBuffer r7 = new java.lang.StringBuffer
            r7.<init>()
            r0 = 0
        L44:
            java.util.List<com.jingdong.common.entity.productdetail.PDStylePropertyEntity> r4 = r6.B
            int r4 = r4.size()
            if (r0 >= r4) goto L63
            java.util.List<com.jingdong.common.entity.productdetail.PDStylePropertyEntity> r4 = r6.B
            java.lang.Object r4 = r4.get(r0)
            com.jingdong.common.entity.productdetail.PDStylePropertyEntity r4 = (com.jingdong.common.entity.productdetail.PDStylePropertyEntity) r4
            if (r4 == 0) goto L60
            int r5 = r4.status
            if (r5 != 0) goto L60
            java.lang.String r0 = r4.title
            r7.append(r0)
            goto L63
        L60:
            int r0 = r0 + 1
            goto L44
        L63:
            int r0 = r7.length()
            if (r0 == 0) goto L7e
            android.content.Context r0 = r6.f5023g
            int r4 = com.jd.lib.productdetail.mainimage.R.string.lib_pd_image_style_unselect_title
            java.lang.Object[] r5 = new java.lang.Object[r3]
            java.lang.String r7 = r7.toString()
            r5[r2] = r7
            java.lang.String r7 = r0.getString(r4, r5)
            com.jd.lib.productdetail.core.utils.PDUtils.showToastCenterNormal(r0, r7)
            r7 = 0
            goto L7f
        L7e:
            r7 = 1
        L7f:
            if (r7 == 0) goto Lce
            com.jd.lib.productdetail.core.entitys.suitstyle.PDPackStyleWareEntity r7 = r6.E
            if (r7 == 0) goto Lce
            com.jd.lib.productdetail.core.entitys.PdDpgListLayerInfo$DetailBean$ItemsBean r7 = new com.jd.lib.productdetail.core.entitys.PdDpgListLayerInfo$DetailBean$ItemsBean
            r7.<init>()
            r7.isValid = r3
            java.lang.String r0 = r6.w
            r7.skuId = r0
            com.jd.lib.productdetail.core.entitys.suitstyle.PDPackStyleWareEntity r0 = r6.E
            java.lang.String r1 = r0.image
            r7.itemUrl = r1
            java.lang.String r1 = r0.name
            r7.itemName = r1
            java.lang.String r0 = r0.price
            r7.price = r0
            java.lang.String r0 = ""
        La0:
            java.util.List<com.jingdong.common.entity.productdetail.PDStylePropertyEntity> r1 = r6.B
            int r1 = r1.size()
            if (r2 >= r1) goto Lc4
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.util.List<com.jingdong.common.entity.productdetail.PDStylePropertyEntity> r0 = r6.B
            java.lang.Object r0 = r0.get(r2)
            com.jingdong.common.entity.productdetail.PDStylePropertyEntity r0 = (com.jingdong.common.entity.productdetail.PDStylePropertyEntity) r0
            java.lang.String r0 = r0.text
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            int r2 = r2 + 1
            goto La0
        Lc4:
            r7.color = r0
            com.jd.lib.productdetail.mainimage.old.PdMDpgStyleInfoView$c r0 = r6.F
            if (r0 == 0) goto Ld5
            r0.a(r7)
            goto Ld5
        Lce:
            com.jd.lib.productdetail.mainimage.old.PdMDpgStyleInfoView$c r7 = r6.F
            if (r7 == 0) goto Ld5
            r7.a(r1)
        Ld5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.productdetail.mainimage.old.PdMDpgStyleInfoView.onClick(android.view.View):void");
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        JDDisplayImageOptions displayer = new JDDisplayImageOptions().displayer(new JDRoundedBitmapDisplayer(PDUtils.dip2px(4.0f)));
        this.p = displayer;
        displayer.setPlaceholder(17);
        this.f5024h = (RelativeLayout) findViewById(R.id.pack_style_title_layout);
        this.f5025i = (RelativeLayout) findViewById(R.id.pack_style_title_container_layout);
        this.f5030n = (ScrollView) findViewById(R.id.pack_style_scroll);
        this.o = (FrameLayout) findViewById(R.id.pack_style_loading_layout);
        ImageView imageView = (ImageView) findViewById(R.id.pack_style_icon);
        this.f5026j = imageView;
        imageView.setOnClickListener(this);
        TextView textView = (TextView) findViewById(R.id.pack_style_price);
        this.f5027k = textView;
        FontsUtil.changeTextFont(textView);
        this.f5028l = (TextView) findViewById(R.id.pack_style_skuid);
        this.f5029m = (LinearLayout) findViewById(R.id.pack_style_container);
        ImageView imageView2 = (ImageView) findViewById(R.id.pack_style_cancel);
        this.s = imageView2;
        imageView2.setOnClickListener(this);
        TextView textView2 = (TextView) findViewById(R.id.pack_style_btn);
        this.t = textView2;
        textView2.setOnClickListener(this);
    }
}
