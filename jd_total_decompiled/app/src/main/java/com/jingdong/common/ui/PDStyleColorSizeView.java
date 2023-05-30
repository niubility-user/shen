package com.jingdong.common.ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.common.DeepLinkUtil;
import com.jingdong.common.PdColorSizeUtil;
import com.jingdong.common.entity.PdStyleImageDetail;
import com.jingdong.common.entity.productdetail.PDSopSkuInfoEntity;
import com.jingdong.common.entity.productdetail.PDStyleColorSizeEntity;
import com.jingdong.common.entity.productdetail.PDStyleEntity;
import com.jingdong.common.entity.productdetail.PDStyleFilterEntity;
import com.jingdong.common.entity.productdetail.PDStylePropertyEntity;
import com.jingdong.common.entity.productdetail.PdSelectEntity;
import com.jingdong.common.entity.productdetail.PdSizeGuide;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.widget.PDFlowLayout;
import com.jingdong.sdk.lib.stylecolorsize.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class PDStyleColorSizeView extends LinearLayout {
    private boolean bybtPbFlag;
    private boolean isDarkTheme;
    private boolean isElder;
    private boolean isNewStyle;
    private Activity mActivity;
    private PdAutoChangeTextSize mBuyStyleExplain;
    private String mBuyStyleExplainUrl;
    private Context mContext;
    private View mFilterContainer;
    private LinearLayout mFilterLayout;
    private List<PDStyleFilterEntity> mFilterList;
    private List<List<PDStyleBubbleItemView>> mFilterTextViews;
    private PdAutoChangeTextSize[] mFilterTipViews;
    public int mFinalX;
    private boolean mIsJX;
    private int mItemBgDrawable;
    private StyleSizeItemClickListenerImpl mOnListener;
    private PdSelectEntity mPdSelectEntity;
    private PDStyleColorSizeEntity mPdStyleColorSizeEntity;
    private PDStyleBubbleItemView[] mSelectItemViews;
    private List<PDStylePropertyEntity> mSelectedProperty;
    private List<PDSopSkuInfoEntity> mServerSopSkuList;
    private String mSkuId;
    private List<String> mSopSkuList;
    private ColorStateList mTextColor;
    private int padding;

    /* loaded from: classes6.dex */
    public interface OnStyleSizeItemClickListener {
        void onBuyStyleExplainClickListener(PDStylePropertyEntity pDStylePropertyEntity);

        void onDashViewClick(View view, PDStylePropertyEntity pDStylePropertyEntity);

        void onOpenImgDetailClickListener(PdStyleImageDetail pdStyleImageDetail);

        void onSelectText(PdSelectEntity pdSelectEntity);

        void onSelectViewClick(View view, String str, PDStylePropertyEntity pDStylePropertyEntity, String str2);

        void onSizeHelperClickListener(PDStyleFilterEntity pDStyleFilterEntity);

        void onSizeHelperClickListener(String str);

        void onSkuIdLongClick(View view);
    }

    /* loaded from: classes6.dex */
    public static class StyleSizeItemClickListenerImpl implements OnStyleSizeItemClickListener {
        @Override // com.jingdong.common.ui.PDStyleColorSizeView.OnStyleSizeItemClickListener
        public void onBuyStyleExplainClickListener(PDStylePropertyEntity pDStylePropertyEntity) {
        }

        @Override // com.jingdong.common.ui.PDStyleColorSizeView.OnStyleSizeItemClickListener
        public void onDashViewClick(View view, PDStylePropertyEntity pDStylePropertyEntity) {
        }

        protected void onMtaParam(PDStylePropertyEntity pDStylePropertyEntity) {
        }

        @Override // com.jingdong.common.ui.PDStyleColorSizeView.OnStyleSizeItemClickListener
        public void onOpenImgDetailClickListener(PdStyleImageDetail pdStyleImageDetail) {
        }

        @Override // com.jingdong.common.ui.PDStyleColorSizeView.OnStyleSizeItemClickListener
        public void onSelectText(PdSelectEntity pdSelectEntity) {
        }

        @Override // com.jingdong.common.ui.PDStyleColorSizeView.OnStyleSizeItemClickListener
        public void onSelectViewClick(View view, String str, PDStylePropertyEntity pDStylePropertyEntity, String str2) {
        }

        @Override // com.jingdong.common.ui.PDStyleColorSizeView.OnStyleSizeItemClickListener
        public void onSizeHelperClickListener(PDStyleFilterEntity pDStyleFilterEntity) {
        }

        @Override // com.jingdong.common.ui.PDStyleColorSizeView.OnStyleSizeItemClickListener
        public void onSizeHelperClickListener(String str) {
        }

        public void onSizeHelperExpo(PDStyleFilterEntity pDStyleFilterEntity) {
        }

        @Override // com.jingdong.common.ui.PDStyleColorSizeView.OnStyleSizeItemClickListener
        public void onSkuIdLongClick(View view) {
        }
    }

    public PDStyleColorSizeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.padding = 0;
        this.mFilterList = new ArrayList();
        this.mFilterTextViews = new ArrayList();
        this.mSelectedProperty = new ArrayList();
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean b(View view) {
        StyleSizeItemClickListenerImpl styleSizeItemClickListenerImpl = this.mOnListener;
        if (styleSizeItemClickListenerImpl != null) {
            styleSizeItemClickListenerImpl.onSkuIdLongClick(view);
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean d(View view) {
        StyleSizeItemClickListenerImpl styleSizeItemClickListenerImpl = this.mOnListener;
        if (styleSizeItemClickListenerImpl != null) {
            styleSizeItemClickListenerImpl.onSkuIdLongClick(view);
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int dip2px(int i2) {
        return PdColorSizeUtil.dip2px(i2);
    }

    private void filterSelectText(PDStyleFilterEntity pDStyleFilterEntity) {
        if (this.mOnListener == null || this.mPdSelectEntity == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        PdSelectEntity pdSelectEntity = this.mPdSelectEntity;
        if (pdSelectEntity.selectLength == pdSelectEntity.selectItems.size()) {
            PdSelectEntity pdSelectEntity2 = this.mPdSelectEntity;
            pdSelectEntity2.status = 1;
            Iterator<Map.Entry<Integer, PDStylePropertyEntity>> it = pdSelectEntity2.selectItems.entrySet().iterator();
            sb.append("\u5df2\u9009\uff1a");
            while (it.hasNext()) {
                sb.append(it.next().getValue().text);
                sb.append("\uff0c");
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
        } else {
            PdSelectEntity pdSelectEntity3 = this.mPdSelectEntity;
            pdSelectEntity3.status = 0;
            Iterator<Map.Entry<Integer, PDStylePropertyEntity>> it2 = pdSelectEntity3.selectItems.entrySet().iterator();
            sb.append("\u8bf7\u9009\u62e9");
            while (it2.hasNext()) {
                PDStylePropertyEntity value = it2.next().getValue();
                if (value.status == 0) {
                    sb.append(value.title);
                    sb.append("\u3001");
                }
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        this.mPdSelectEntity.selectText = sb.toString();
        this.mOnListener.onSelectText(this.mPdSelectEntity);
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x016d, code lost:
        if (r17 > 0) goto L51;
     */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0205 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private LinearLayout getBuyStyleRowView(int i2, PDStyleFilterEntity pDStyleFilterEntity) {
        int i3;
        PDStyleBubbleItemView propertyItemView;
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.lib_style_item_contract_info, (ViewGroup) null);
        PdAutoChangeTextSize pdAutoChangeTextSize = (PdAutoChangeTextSize) linearLayout.findViewById(R.id.detail_style_item_title);
        this.mBuyStyleExplain = (PdAutoChangeTextSize) linearLayout.findViewById(R.id.detail_style_item_explain);
        pdAutoChangeTextSize.setText(pDStyleFilterEntity.title);
        TextView textView = (TextView) linearLayout.findViewById(R.id.detail_style_item_sku_id);
        textView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.jingdong.common.ui.a
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return PDStyleColorSizeView.this.b(view);
            }
        });
        boolean z = true;
        if (this.isElder) {
            if (textView != null) {
                textView.setVisibility(8);
            }
        } else if (i2 == 0 && this.isNewStyle && pDStyleFilterEntity != null && pDStyleFilterEntity.isSpecialCategory) {
            textView.setText(getResources().getString(R.string.pd_style_sku_id, this.mSkuId));
            textView.setTextColor(ContextCompat.getColor(this.mContext, this.isDarkTheme ? R.color.pd_color_848383 : R.color.gray_8c8c8c));
            if (this.bybtPbFlag) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
            }
        } else {
            textView.setVisibility(8);
        }
        int dimension = (int) getResources().getDimension(R.dimen.pd_txt_size_small);
        Drawable drawable = getResources().getDrawable(this.isDarkTheme ? R.drawable.lib_pd_style_explain_dark : R.drawable.lib_pd_style_explain);
        drawable.setBounds(0, 0, dimension, dimension);
        this.mBuyStyleExplain.setCompoundDrawables(null, null, drawable, null);
        PdAutoChangeTextSize pdAutoChangeTextSize2 = (PdAutoChangeTextSize) linearLayout.findViewById(R.id.detail_style_item_tip);
        pdAutoChangeTextSize2.setText(String.format(getResources().getString(R.string.pd_style_unselect_title), pDStyleFilterEntity.title));
        Context context = this.mContext;
        int i4 = R.color.pd_red;
        pdAutoChangeTextSize2.setTextColor(ContextCompat.getColor(context, i4));
        PdAutoChangeTextSize[] pdAutoChangeTextSizeArr = this.mFilterTipViews;
        pdAutoChangeTextSizeArr[i2] = pdAutoChangeTextSize2;
        if (pdAutoChangeTextSizeArr[i2].getVisibility() == 0) {
            this.mFilterTipViews[i2].setVisibility(8);
        }
        pdAutoChangeTextSize.setTextColor(ContextCompat.getColor(this.mContext, this.isDarkTheme ? R.color.pd_color_ececec : R.color.gray_262626));
        Context context2 = this.mContext;
        if (this.isDarkTheme) {
            i4 = R.color.pd_color_ff3826;
        }
        int color = ContextCompat.getColor(context2, i4);
        pdAutoChangeTextSize2.setTextColor(color);
        this.mBuyStyleExplain.setTextColor(color);
        PDFlowLayout pDFlowLayout = (PDFlowLayout) linearLayout.getChildAt(1);
        pDFlowLayout.setSpace(dip2px(12), dip2px(4));
        pDFlowLayout.setPadW(this.padding);
        if (pDFlowLayout != null && pDFlowLayout.getChildCount() != 0) {
            pDFlowLayout.removeAllViews();
        }
        pDFlowLayout.setActivity(this.mActivity);
        this.mBuyStyleExplainUrl = null;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, dip2px(38));
        int size = pDStyleFilterEntity.buttons.size();
        ArrayList arrayList = new ArrayList(size);
        int i5 = 0;
        while (i5 < size) {
            PDStylePropertyEntity pDStylePropertyEntity = pDStyleFilterEntity.buttons.get(i5);
            if (pDStylePropertyEntity != null && pDStylePropertyEntity.isContainsSopSku) {
                if (this.isElder) {
                    i3 = 2;
                } else {
                    if (pDStyleFilterEntity.isSpecialCategory && this.isNewStyle) {
                        i3 = 1;
                        propertyItemView = getPropertyItemView(pDStylePropertyEntity, i3);
                        pDStylePropertyEntity.hasService = pDStyleFilterEntity.hasService;
                        if (propertyItemView == null) {
                            arrayList.add(propertyItemView);
                            propertyItemView.setItemDash(pDStylePropertyEntity);
                            if (pDStylePropertyEntity.isSelect) {
                                propertyItemView.setItemSelected(z);
                                this.mSelectItemViews[i2] = propertyItemView;
                                if (pDStylePropertyEntity.hasBubble && !TextUtils.isEmpty(pDStylePropertyEntity.serviceInfr)) {
                                    this.mFilterTipViews[i2].setVisibility(0);
                                    this.mFilterTipViews[i2].setText(pDStylePropertyEntity.serviceInfr);
                                    this.mFilterTipViews[i2].setTextColor(ContextCompat.getColor(this.mContext, R.color.gray_8c8c8c));
                                }
                                if (pDStylePropertyEntity.hasBubble && !TextUtils.isEmpty(pDStylePropertyEntity.serviceText) && !TextUtils.isEmpty(pDStylePropertyEntity.serviceInfrUrl) && (!this.isNewStyle || pDStyleFilterEntity == null || !pDStyleFilterEntity.isSpecialCategory)) {
                                    this.mBuyStyleExplain.setText(pDStylePropertyEntity.serviceText);
                                    this.mBuyStyleExplainUrl = pDStylePropertyEntity.serviceInfrUrl;
                                    this.mBuyStyleExplain.setVisibility(0);
                                    this.mBuyStyleExplain.setTag(pDStylePropertyEntity);
                                } else {
                                    this.mBuyStyleExplain.setVisibility(8);
                                }
                            }
                            pDStylePropertyEntity.position = i2;
                            pDStylePropertyEntity.mtaSkuId = this.mSkuId;
                            propertyItemView.setTag(pDStylePropertyEntity);
                            pDFlowLayout.addView(propertyItemView, layoutParams);
                        }
                    }
                    i3 = 0;
                    propertyItemView = getPropertyItemView(pDStylePropertyEntity, i3);
                    pDStylePropertyEntity.hasService = pDStyleFilterEntity.hasService;
                    if (propertyItemView == null) {
                    }
                }
            }
            i5++;
            z = true;
        }
        this.mBuyStyleExplain.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.PDStyleColorSizeView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PDStyleColorSizeView.this.mOnListener != null) {
                    Object tag = view.getTag();
                    if (tag instanceof PDStylePropertyEntity) {
                        PDStylePropertyEntity pDStylePropertyEntity2 = (PDStylePropertyEntity) tag;
                        pDStylePropertyEntity2.skuId = PDStyleColorSizeView.this.mSkuId;
                        PDStyleColorSizeView.this.mOnListener.onBuyStyleExplainClickListener(pDStylePropertyEntity2);
                    }
                }
                if (TextUtils.isEmpty(PDStyleColorSizeView.this.mBuyStyleExplainUrl)) {
                    return;
                }
                DeepLinkUtil.gotoMWithUrl(PDStyleColorSizeView.this.mContext, PDStyleColorSizeView.this.mBuyStyleExplainUrl);
            }
        });
        this.mFilterTextViews.add(arrayList);
        return linearLayout;
    }

    private String getCurRowTypeForPoint(int i2) {
        List<PDStyleFilterEntity> list = this.mFilterList;
        if (list != null) {
            return list.get(i2).title;
        }
        return null;
    }

    private String getFinalFilterSkuid() {
        boolean z = true;
        ArrayList arrayList = null;
        for (int i2 = 0; i2 < this.mSelectedProperty.size(); i2++) {
            PDStylePropertyEntity pDStylePropertyEntity = this.mSelectedProperty.get(i2);
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

    private PDStyleBubbleItemView getPropertyItemView(PDStylePropertyEntity pDStylePropertyEntity, int i2) {
        PDStyleBubbleItemView pDStyleBubbleItemView = (PDStyleBubbleItemView) LayoutInflater.from(this.mContext).inflate(R.layout.lib_pd_style_bubble_item, (ViewGroup) null);
        pDStyleBubbleItemView.initView(i2, this.isElder);
        pDStyleBubbleItemView.setDarkTheme(this.isDarkTheme);
        pDStyleBubbleItemView.setItemContent(pDStylePropertyEntity);
        pDStyleBubbleItemView.setOnOpenImgClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.PDStyleColorSizeView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view.getTag() instanceof String) {
                    PdStyleImageDetail pdStyleImageDetail = new PdStyleImageDetail();
                    pdStyleImageDetail.url = (String) view.getTag();
                    PDStyleColorSizeView.this.mOnListener.onOpenImgDetailClickListener(pdStyleImageDetail);
                }
            }
        });
        pDStyleBubbleItemView.setItemBubble(pDStylePropertyEntity.ktyf);
        pDStyleBubbleItemView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.PDStyleColorSizeView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PDStyleColorSizeView.this.onItemClick(view);
            }
        });
        return pDStyleBubbleItemView;
    }

    private List<String> getRetainList(int i2, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < this.mSelectedProperty.size(); i3++) {
            PDStylePropertyEntity pDStylePropertyEntity = this.mSelectedProperty.get(i3);
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

    private List<String> getRetainSopSku(List<String> list) {
        List<String> list2 = this.mSopSkuList;
        if (list2 == null || list2.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(this.mSopSkuList);
        arrayList.retainAll(list);
        return arrayList;
    }

    private void getSopSkuList(List<PDSopSkuInfoEntity> list) {
        if (list != null && !list.isEmpty()) {
            List<String> list2 = this.mSopSkuList;
            if (list2 == null) {
                this.mSopSkuList = new ArrayList();
            } else {
                list2.clear();
            }
            boolean z = false;
            for (PDSopSkuInfoEntity pDSopSkuInfoEntity : list) {
                if (pDSopSkuInfoEntity != null && pDSopSkuInfoEntity.isSop) {
                    if (TextUtils.equals(pDSopSkuInfoEntity.skuId, this.mSkuId)) {
                        z = true;
                    }
                    this.mSopSkuList.add(pDSopSkuInfoEntity.skuId);
                }
            }
            if (z) {
                return;
            }
            this.mSopSkuList.clear();
            return;
        }
        this.mSopSkuList = null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:83:0x01ec, code lost:
        if (r19 > 0) goto L84;
     */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01ff  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private LinearLayout getStyleRowView(int i2, PDStyleFilterEntity pDStyleFilterEntity) {
        int i3;
        PDStyleBubbleItemView propertyItemView;
        int i4;
        String str;
        LinearLayout.LayoutParams layoutParams;
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.lib_style_item_info, (ViewGroup) null);
        PdAutoChangeTextSize pdAutoChangeTextSize = (PdAutoChangeTextSize) linearLayout.findViewById(R.id.detail_style_item_title);
        PdAutoChangeTextSize pdAutoChangeTextSize2 = (PdAutoChangeTextSize) linearLayout.findViewById(R.id.detail_style_item_explain);
        TextView textView = (TextView) linearLayout.findViewById(R.id.detail_style_item_sku_id);
        textView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.jingdong.common.ui.b
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return PDStyleColorSizeView.this.d(view);
            }
        });
        if (this.isElder) {
            if (textView != null) {
                textView.setVisibility(8);
            }
        } else if (i2 == 0 && this.isNewStyle && pDStyleFilterEntity != null && pDStyleFilterEntity.isSpecialCategory) {
            textView.setText(getResources().getString(R.string.pd_style_sku_id, this.mSkuId));
            textView.setTextColor(ContextCompat.getColor(this.mContext, this.isDarkTheme ? R.color.pd_color_848383 : R.color.gray_8c8c8c));
            if (this.bybtPbFlag) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
            }
        } else {
            textView.setVisibility(8);
        }
        if (this.isNewStyle && pDStyleFilterEntity != null && pDStyleFilterEntity.isSpecialCategory) {
            pdAutoChangeTextSize2.setVisibility(8);
            pdAutoChangeTextSize2.setCompoundDrawables(null, null, null, null);
        } else {
            PdSizeGuide pdSizeGuide = pDStyleFilterEntity.titleExtMap;
            if (pdSizeGuide != null) {
                setSizeGuideText(pdSizeGuide, pdAutoChangeTextSize2, pDStyleFilterEntity);
            } else {
                PdSizeGuide pdSizeGuide2 = pDStyleFilterEntity.arTitleExtMap;
                if (pdSizeGuide2 != null) {
                    setSizeGuideText(pdSizeGuide2, pdAutoChangeTextSize2, pDStyleFilterEntity);
                }
            }
        }
        PdAutoChangeTextSize pdAutoChangeTextSize3 = (PdAutoChangeTextSize) linearLayout.findViewById(R.id.detail_style_item_tip);
        PdSizeGuide pdSizeGuide3 = pDStyleFilterEntity.titleExtMap;
        if (pdSizeGuide3 == null || TextUtils.isEmpty(pdSizeGuide3.backwordTitle)) {
            pdAutoChangeTextSize3.setText(String.format(getResources().getString(R.string.pd_style_unselect_title), pDStyleFilterEntity.title));
        } else {
            pdAutoChangeTextSize3.setText(String.format(getResources().getString(R.string.pd_style_unselect_title), pDStyleFilterEntity.titleExtMap.backwordTitle));
        }
        PdAutoChangeTextSize[] pdAutoChangeTextSizeArr = this.mFilterTipViews;
        pdAutoChangeTextSizeArr[i2] = pdAutoChangeTextSize3;
        if (pdAutoChangeTextSizeArr[i2].getVisibility() == 0) {
            this.mFilterTipViews[i2].setVisibility(8);
        }
        pdAutoChangeTextSize.setTextColor(ContextCompat.getColor(this.mContext, this.isDarkTheme ? R.color.pd_color_ececec : R.color.gray_262626));
        pdAutoChangeTextSize3.setTextColor(ContextCompat.getColor(this.mContext, this.isDarkTheme ? R.color.pd_color_ff3826 : R.color.pd_red));
        PDFlowLayout pDFlowLayout = (PDFlowLayout) linearLayout.getChildAt(1);
        pDFlowLayout.setActivity(this.mActivity);
        int i5 = this.mFinalX;
        if (i5 != 0) {
            pDFlowLayout.setFinalX(i5);
        }
        pDFlowLayout.setPadW(this.padding);
        pDFlowLayout.setonScrollChanged(new PDFlowLayout.onScrollChanged() { // from class: com.jingdong.common.ui.PDStyleColorSizeView.1
            @Override // com.jingdong.common.widget.PDFlowLayout.onScrollChanged
            public void onScrollChanged(int i6) {
                PDStyleColorSizeView.this.mFinalX = i6;
            }
        });
        int i6 = 2;
        if (this.isElder) {
            pDFlowLayout.setmCanOverRowSize(false);
            pDFlowLayout.setMaxRows(-1);
            int dip2px = dip2px(12);
            pDFlowLayout.setSpace(dip2px, dip2px);
        } else if (this.isNewStyle && pDStyleFilterEntity.isSpecialCategory) {
            pDFlowLayout.setmCanOverRowSize(true);
            pDFlowLayout.setMaxRows(2);
            int dip2px2 = dip2px(12);
            pDFlowLayout.setSpace(dip2px2 / 2, dip2px2);
        } else {
            pDFlowLayout.setmCanOverRowSize(false);
            pDFlowLayout.setMaxRows(-1);
            int dip2px3 = dip2px(12);
            pDFlowLayout.setSpace(dip2px3, dip2px3);
        }
        if (pDFlowLayout != null && pDFlowLayout.getChildCount() != 0) {
            pDFlowLayout.removeAllViews();
        }
        int size = pDStyleFilterEntity.buttons.size();
        if (this.isNewStyle && pDStyleFilterEntity.isSpecialCategory) {
            pdAutoChangeTextSize.setText(pDStyleFilterEntity.title + "(" + size + ")");
        } else {
            pdAutoChangeTextSize.setText(pDStyleFilterEntity.title);
        }
        ArrayList arrayList = new ArrayList(size);
        int dip2px4 = dip2px(110);
        int dip2px5 = dip2px(30);
        int i7 = 0;
        while (i7 < size) {
            PDStylePropertyEntity pDStylePropertyEntity = pDStyleFilterEntity.buttons.get(i7);
            if (pDStylePropertyEntity.isContainsSopSku) {
                if (this.isElder) {
                    i3 = !TextUtils.isEmpty(pDStylePropertyEntity.selectImg) ? 2 : 0;
                } else {
                    if (pDStyleFilterEntity.isSpecialCategory && this.isNewStyle) {
                        i3 = 1;
                        propertyItemView = getPropertyItemView(pDStylePropertyEntity, i3);
                        if (propertyItemView != null) {
                            new LinearLayout.LayoutParams(-2, dip2px5);
                            boolean equals = TextUtils.equals(pDStylePropertyEntity.stock, "0");
                            String str2 = "";
                            if (i3 == i6) {
                                i4 = size;
                                layoutParams = new LinearLayout.LayoutParams(-1, -2);
                                if (pDStylePropertyEntity.skuImgList != null) {
                                    str2 = pDStylePropertyEntity.selectImg;
                                    str = "";
                                } else {
                                    str = "";
                                }
                            } else {
                                i4 = size;
                                str = pDStylePropertyEntity.iconInfo;
                                if (pDStyleFilterEntity.isSpecialCategory && this.isNewStyle) {
                                    if (pDStyleFilterEntity.isNewLogic && !TextUtils.isEmpty(pDStylePropertyEntity.btnImage)) {
                                        str2 = pDStylePropertyEntity.btnImage;
                                    } else if (pDStylePropertyEntity.skuImgList != null) {
                                        str2 = pDStylePropertyEntity.selectImg;
                                    }
                                    layoutParams = new LinearLayout.LayoutParams(dip2px4, -2);
                                } else {
                                    layoutParams = new LinearLayout.LayoutParams(-2, dip2px5);
                                }
                            }
                            propertyItemView.setItemImage(str2, str, equals);
                            arrayList.add(propertyItemView);
                            propertyItemView.setItemDash(pDStylePropertyEntity);
                            if (pDStylePropertyEntity.isSelect) {
                                propertyItemView.setSelected(true);
                                this.mSelectItemViews[i2] = propertyItemView;
                            }
                            pDStylePropertyEntity.position = i2;
                            pDStylePropertyEntity.mtaSkuId = this.mSkuId;
                            propertyItemView.setTag(pDStylePropertyEntity);
                            pDFlowLayout.addView(propertyItemView, layoutParams);
                            i7++;
                            size = i4;
                            i6 = 2;
                        }
                    }
                    i3 = 0;
                    propertyItemView = getPropertyItemView(pDStylePropertyEntity, i3);
                    if (propertyItemView != null) {
                    }
                }
            }
            i4 = size;
            i7++;
            size = i4;
            i6 = 2;
        }
        if (this.isNewStyle && pDStyleFilterEntity.isSpecialCategory && this.mSelectItemViews[i2] == null) {
            this.mFilterTipViews[i2].setVisibility(0);
        }
        this.mFilterTextViews.add(arrayList);
        return linearLayout;
    }

    private void handlePropertyData(String str, PDStylePropertyEntity pDStylePropertyEntity, PdSizeGuide pdSizeGuide) {
        List<String> list;
        if (pDStylePropertyEntity == null || (list = pDStylePropertyEntity.skuList) == null) {
            return;
        }
        List<String> retainSopSku = getRetainSopSku(list);
        if (retainSopSku != null) {
            pDStylePropertyEntity.isContainsSopSku = !retainSopSku.isEmpty();
            pDStylePropertyEntity.skuList = retainSopSku;
        }
        if (pdSizeGuide != null && !TextUtils.isEmpty(pdSizeGuide.backwordTitle)) {
            pDStylePropertyEntity.title = pdSizeGuide.backwordTitle;
        } else {
            pDStylePropertyEntity.title = str;
        }
        if (pDStylePropertyEntity.skuList.contains(this.mSkuId)) {
            pDStylePropertyEntity.isSelect = true;
            pDStylePropertyEntity.status = 1;
            this.mSelectedProperty.add(pDStylePropertyEntity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onItemClick(View view) {
        if (view == null) {
            return;
        }
        PDStyleBubbleItemView pDStyleBubbleItemView = (PDStyleBubbleItemView) view;
        if (pDStyleBubbleItemView.getTag() instanceof PDStylePropertyEntity) {
            PDStylePropertyEntity pDStylePropertyEntity = (PDStylePropertyEntity) pDStyleBubbleItemView.getTag();
            pDStyleBubbleItemView.setContentDescription(pDStylePropertyEntity.title + pDStylePropertyEntity.text);
        }
        PDStylePropertyEntity pDStylePropertyEntity2 = (PDStylePropertyEntity) view.getTag();
        if (pDStylePropertyEntity2 != null) {
            if (!pDStylePropertyEntity2.isDash) {
                int i2 = pDStylePropertyEntity2.position;
                PDStyleBubbleItemView pDStyleBubbleItemView2 = this.mSelectItemViews[i2];
                PDStyleFilterEntity pDStyleFilterEntity = this.mFilterList.get(i2);
                if (pDStyleBubbleItemView2 != null) {
                    if (pDStyleBubbleItemView2 != pDStyleBubbleItemView) {
                        pDStyleBubbleItemView2.setContentDescription(null);
                        selectItemDif(pDStyleBubbleItemView, pDStylePropertyEntity2, pDStyleBubbleItemView2);
                    } else {
                        PdSelectEntity pdSelectEntity = this.mPdSelectEntity;
                        if (pdSelectEntity != null) {
                            if (pDStylePropertyEntity2.status == 0) {
                                pdSelectEntity.selectLength++;
                                pDStylePropertyEntity2.status = 1;
                            } else {
                                pdSelectEntity.selectLength--;
                                pDStylePropertyEntity2.status = 0;
                            }
                            pdSelectEntity.selectItems.put(Integer.valueOf(pDStylePropertyEntity2.position), pDStylePropertyEntity2);
                        }
                        pDStyleBubbleItemView.setItemSelected(!pDStyleBubbleItemView.isSelected());
                    }
                    filterSelectText(pDStyleFilterEntity);
                } else {
                    selectItemDif(pDStyleBubbleItemView, pDStylePropertyEntity2, pDStyleBubbleItemView2);
                }
                boolean isSelected = pDStyleBubbleItemView.isSelected();
                this.mFilterTipViews[pDStylePropertyEntity2.position].setVisibility(isSelected ? 8 : 0);
                if (pDStylePropertyEntity2.hasService && isSelected && !TextUtils.isEmpty(pDStylePropertyEntity2.serviceInfr)) {
                    this.mFilterTipViews[pDStylePropertyEntity2.position].setVisibility(0);
                    this.mFilterTipViews[pDStylePropertyEntity2.position].setText(pDStylePropertyEntity2.serviceInfr);
                    this.mFilterTipViews[pDStylePropertyEntity2.position].setTextColor(ContextCompat.getColor(this.mContext, R.color.gray_8c8c8c));
                }
                this.mSelectItemViews[pDStylePropertyEntity2.position] = pDStyleBubbleItemView;
                pDStylePropertyEntity2.status = isSelected ? 1 : 0;
                this.mSelectedProperty.set(i2, pDStylePropertyEntity2);
                if (isSelected) {
                    String finalFilterSkuid = getFinalFilterSkuid();
                    if (pDStylePropertyEntity2.hasService) {
                        this.mBuyStyleExplainUrl = pDStylePropertyEntity2.serviceInfrUrl;
                        if (this.mBuyStyleExplain != null) {
                            this.mBuyStyleExplain.setVisibility((!pDStylePropertyEntity2.hasBubble || TextUtils.isEmpty(pDStylePropertyEntity2.serviceText) || TextUtils.isEmpty(pDStylePropertyEntity2.serviceInfrUrl)) ? false : true ? 0 : 8);
                            this.mBuyStyleExplain.setText(pDStylePropertyEntity2.serviceText);
                        }
                    }
                    if (!TextUtils.isEmpty(finalFilterSkuid) && this.mOnListener != null) {
                        this.mOnListener.onSelectViewClick(view, finalFilterSkuid, pDStylePropertyEntity2, getCurRowTypeForPoint(pDStylePropertyEntity2.position));
                    }
                    pDStylePropertyEntity2.mtaSkuId = finalFilterSkuid;
                } else {
                    if (pDStylePropertyEntity2.hasService) {
                        this.mFilterTipViews[pDStylePropertyEntity2.position].setText(String.format(getResources().getString(R.string.pd_style_unselect_title), getCurRowTypeForPoint(pDStylePropertyEntity2.position)));
                        this.mFilterTipViews[pDStylePropertyEntity2.position].setTextColor(ContextCompat.getColor(this.mContext, R.color.pd_red));
                        PdAutoChangeTextSize pdAutoChangeTextSize = this.mBuyStyleExplain;
                        if (pdAutoChangeTextSize != null) {
                            pdAutoChangeTextSize.setVisibility(8);
                        }
                    }
                    restSelectFilter(pDStylePropertyEntity2);
                }
                pDStyleBubbleItemView.setItemContent(pDStylePropertyEntity2);
                restDashProperty(pDStylePropertyEntity2, false);
            }
            StyleSizeItemClickListenerImpl styleSizeItemClickListenerImpl = this.mOnListener;
            if (styleSizeItemClickListenerImpl != null) {
                styleSizeItemClickListenerImpl.onMtaParam(pDStylePropertyEntity2);
            }
        }
    }

    private void processStyleFilterData(List<PDStyleFilterEntity> list) {
        ArrayList arrayList;
        List<String> list2;
        Map<String, String> map;
        if (!this.mSelectedProperty.isEmpty()) {
            this.mSelectedProperty.clear();
        }
        int size = list.size();
        this.mPdSelectEntity = new PdSelectEntity();
        for (int i2 = 0; i2 < size; i2++) {
            PDStyleFilterEntity pDStyleFilterEntity = list.get(i2);
            List<PDStylePropertyEntity> list3 = pDStyleFilterEntity.buttons;
            for (int i3 = 0; i3 < list3.size(); i3++) {
                handlePropertyData(pDStyleFilterEntity.title, list3.get(i3), pDStyleFilterEntity.titleExtMap);
            }
            if (i2 > this.mSelectedProperty.size() - 1) {
                PDStylePropertyEntity pDStylePropertyEntity = new PDStylePropertyEntity();
                pDStylePropertyEntity.status = 0;
                pDStylePropertyEntity.isSelect = false;
                PdSizeGuide pdSizeGuide = pDStyleFilterEntity.titleExtMap;
                if (pdSizeGuide != null && !TextUtils.isEmpty(pdSizeGuide.backwordTitle)) {
                    pDStylePropertyEntity.title = pDStyleFilterEntity.titleExtMap.backwordTitle;
                } else {
                    pDStylePropertyEntity.title = pDStyleFilterEntity.title;
                }
                this.mSelectedProperty.add(pDStylePropertyEntity);
            }
        }
        for (int i4 = 0; i4 < list.size(); i4++) {
            PDStyleFilterEntity pDStyleFilterEntity2 = list.get(i4);
            List<PDStylePropertyEntity> list4 = pDStyleFilterEntity2.buttons;
            boolean z = false;
            for (int i5 = 0; i5 < list4.size(); i5++) {
                PDStylePropertyEntity pDStylePropertyEntity2 = list4.get(i5);
                if (!this.isNewStyle || !pDStyleFilterEntity2.isSpecialCategory || (map = pDStylePropertyEntity2.skuImgList) == null || map.isEmpty()) {
                    arrayList = null;
                } else {
                    arrayList = new ArrayList(pDStylePropertyEntity2.skuImgList.size());
                    arrayList.addAll(pDStylePropertyEntity2.skuImgList.keySet());
                }
                ArrayList arrayList2 = new ArrayList(getRetainList(i4, false));
                arrayList2.retainAll(pDStylePropertyEntity2.skuList);
                if (arrayList != null && this.isNewStyle) {
                    arrayList2.retainAll(arrayList);
                    if (arrayList2.isEmpty()) {
                        pDStylePropertyEntity2.selectImg = pDStylePropertyEntity2.imageUrl;
                    } else {
                        pDStylePropertyEntity2.selectImg = pDStylePropertyEntity2.skuImgList.get(arrayList2.get(0));
                    }
                }
                if (TextUtils.equals("1", pDStyleFilterEntity2.userBybtType)) {
                    pDStylePropertyEntity2.iconInfo = pDStylePropertyEntity2.bybtFlagImg;
                } else if (TextUtils.equals("2", pDStyleFilterEntity2.userBybtType) && pDStylePropertyEntity2.skuBybtImgList != null) {
                    if (!arrayList2.isEmpty()) {
                        pDStylePropertyEntity2.iconInfo = pDStylePropertyEntity2.skuBybtImgList.get(arrayList2.get(0));
                    } else if (size == 1 && (list2 = pDStylePropertyEntity2.skuList) != null && !list2.isEmpty()) {
                        pDStylePropertyEntity2.iconInfo = pDStylePropertyEntity2.skuBybtImgList.get(pDStylePropertyEntity2.skuList.get(0));
                    }
                }
                if (size > 1) {
                    pDStylePropertyEntity2.isDash = arrayList2.isEmpty();
                }
                if (pDStylePropertyEntity2.isSelect) {
                    PdSelectEntity pdSelectEntity = this.mPdSelectEntity;
                    pdSelectEntity.selectLength++;
                    pdSelectEntity.selectItems.put(Integer.valueOf(i4), pDStylePropertyEntity2);
                    z = true;
                }
            }
            if (!z) {
                PDStylePropertyEntity pDStylePropertyEntity3 = new PDStylePropertyEntity();
                pDStylePropertyEntity3.status = 0;
                pDStylePropertyEntity3.title = pDStyleFilterEntity2.title;
                this.mPdSelectEntity.selectItems.put(Integer.valueOf(i4), pDStylePropertyEntity3);
            }
        }
        filterSelectText(null);
    }

    private void restDashProperty(PDStylePropertyEntity pDStylePropertyEntity, boolean z) {
        for (int i2 = 0; i2 < this.mFilterList.size(); i2++) {
            List<PDStylePropertyEntity> list = this.mFilterList.get(i2).buttons;
            List<PDStyleBubbleItemView> list2 = this.mFilterTextViews.get(i2);
            if (list != null) {
                int i3 = -1;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    PDStylePropertyEntity pDStylePropertyEntity2 = list.get(i4);
                    if (pDStylePropertyEntity2 != null && pDStylePropertyEntity2.isContainsSopSku) {
                        i3++;
                        if (pDStylePropertyEntity2 != null) {
                            ArrayList arrayList = new ArrayList(getRetainList(pDStylePropertyEntity2.position, z));
                            if (arrayList.isEmpty()) {
                                pDStylePropertyEntity2.isDash = false;
                            } else {
                                arrayList.retainAll(pDStylePropertyEntity2.skuList);
                                pDStylePropertyEntity2.isDash = arrayList.isEmpty();
                            }
                        }
                        if (list2 != null && !list2.isEmpty()) {
                            list2.get(i3).setItemDash(pDStylePropertyEntity2);
                        }
                    }
                }
            }
        }
    }

    private void restSelectFilter(PDStylePropertyEntity pDStylePropertyEntity) {
        List<PDStylePropertyEntity> list = this.mFilterList.get(pDStylePropertyEntity.position).buttons;
        for (int i2 = 0; i2 < list.size(); i2++) {
            PDStylePropertyEntity pDStylePropertyEntity2 = list.get(i2);
            pDStylePropertyEntity2.isDash = false;
            pDStylePropertyEntity2.isSelect = false;
        }
    }

    private void selectItemDif(PDStyleBubbleItemView pDStyleBubbleItemView, PDStylePropertyEntity pDStylePropertyEntity, PDStyleBubbleItemView pDStyleBubbleItemView2) {
        PDStylePropertyEntity pDStylePropertyEntity2;
        PdSelectEntity pdSelectEntity = this.mPdSelectEntity;
        if (pdSelectEntity != null) {
            if (pDStylePropertyEntity.status == 1) {
                pdSelectEntity.selectLength--;
                pDStylePropertyEntity.status = 0;
            } else {
                if (pdSelectEntity.selectLength < pdSelectEntity.selectItems.size() && (pDStylePropertyEntity2 = this.mPdSelectEntity.selectItems.get(Integer.valueOf(pDStylePropertyEntity.position))) != null && pDStylePropertyEntity2.status == 0) {
                    this.mPdSelectEntity.selectLength++;
                }
                pDStylePropertyEntity.status = 1;
                this.mPdSelectEntity.selectItems.put(Integer.valueOf(pDStylePropertyEntity.position), pDStylePropertyEntity);
            }
        }
        if (pDStyleBubbleItemView2 != null) {
            ((PDStylePropertyEntity) pDStyleBubbleItemView2.getTag()).status = 0;
            pDStyleBubbleItemView2.setItemSelected(false);
        }
        pDStyleBubbleItemView.setItemSelected(true);
    }

    private void setSizeGuideText(final PdSizeGuide pdSizeGuide, final PdAutoChangeTextSize pdAutoChangeTextSize, final PDStyleFilterEntity pDStyleFilterEntity) {
        pdAutoChangeTextSize.setVisibility(8);
        if (pdSizeGuide == null || TextUtils.isEmpty(pdSizeGuide.entranceCw) || TextUtils.isEmpty(pdSizeGuide.jumpPic) || TextUtils.isEmpty(pdSizeGuide.jumpUrl)) {
            return;
        }
        pdAutoChangeTextSize.setVisibility(0);
        StyleSizeItemClickListenerImpl styleSizeItemClickListenerImpl = this.mOnListener;
        if (styleSizeItemClickListenerImpl != null) {
            styleSizeItemClickListenerImpl.onSizeHelperExpo(pDStyleFilterEntity);
        }
        pdAutoChangeTextSize.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.PDStyleColorSizeView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PDStyleColorSizeView.this.mOnListener != null) {
                    PDStyleColorSizeView.this.mOnListener.onSizeHelperClickListener(pDStyleFilterEntity);
                    PDStyleColorSizeView.this.mOnListener.onSizeHelperClickListener(pdSizeGuide.jumpUrl);
                }
                if (pdSizeGuide.jumpType == 2) {
                    DeepLinkUtil.openAppForInner(PDStyleColorSizeView.this.mContext, pdSizeGuide.jumpUrl);
                } else {
                    DeepLinkUtil.gotoMWithUrl(PDStyleColorSizeView.this.mContext, pdSizeGuide.jumpUrl);
                }
            }
        });
        pdAutoChangeTextSize.setText(pdSizeGuide.entranceCw);
        pdAutoChangeTextSize.setTextColor(ContextCompat.getColor(this.mContext, this.isDarkTheme ? R.color.pd_color_848383 : R.color.pd_style_gray));
        JDImageUtils.loadImage(pdSizeGuide.jumpPic, new JDSimpleImageLoadingListener() { // from class: com.jingdong.common.ui.PDStyleColorSizeView.4
            @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
                bitmapDrawable.setBounds(0, 0, PDStyleColorSizeView.this.dip2px(4), PDStyleColorSizeView.this.dip2px(14));
                pdAutoChangeTextSize.setCompoundDrawables(null, null, bitmapDrawable, null);
            }
        });
    }

    public void bindData2View(PDStyleColorSizeEntity pDStyleColorSizeEntity, boolean z) {
        bindData2View(pDStyleColorSizeEntity, z, false, false);
    }

    public void changeStyle(boolean z) {
        this.mIsJX = z;
        this.mTextColor = getResources().getColorStateList(z ? R.color.lib_style_jx_text_selector : R.color.lib_style_text_selector);
        this.mItemBgDrawable = z ? R.drawable.lib_pd_style_button_x : R.drawable.lib_pd_style_button_new;
    }

    public List<PDStyleFilterEntity> getFilterList() {
        List<PDStyleFilterEntity> list = this.mFilterList;
        if (list != null) {
            return list;
        }
        return null;
    }

    public List<PDStylePropertyEntity> getStyleProperty() {
        return this.mSelectedProperty;
    }

    protected void initView() {
        this.mFilterLayout = (LinearLayout) findViewById(R.id.detail_style_filter_layout);
        this.mFilterContainer = findViewById(R.id.detail_style_filter_layout_container);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    public void setActivity(Activity activity) {
        this.mActivity = activity;
    }

    public void setDarkTheme(boolean z) {
        this.isDarkTheme = z;
    }

    public void setFilterList(List<PDStyleFilterEntity> list) {
        if (list != null) {
            processStyleFilterData(list);
            this.mFilterList = list;
        }
    }

    public void setIsJX(boolean z) {
        this.mIsJX = z;
    }

    public void setOnStyleSizeItemClickListener(StyleSizeItemClickListenerImpl styleSizeItemClickListenerImpl) {
        if (styleSizeItemClickListenerImpl != null) {
            this.mOnListener = styleSizeItemClickListenerImpl;
        }
    }

    public void setPadW(int i2) {
        this.padding = PDUtils.dip2px(i2);
    }

    public void setSkuId(String str, boolean z) {
        this.mSkuId = str;
        this.isNewStyle = z;
    }

    public void setSopSkuList(List<PDSopSkuInfoEntity> list, boolean z) {
        if (z) {
            this.mServerSopSkuList = list;
            getSopSkuList(list);
        }
    }

    public void showAllFilter(PDStyleEntity pDStyleEntity) {
        List<PDStyleFilterEntity> list;
        if (pDStyleEntity == null || (list = pDStyleEntity.colorSize) == null || list.isEmpty()) {
            return;
        }
        if (!this.mFilterList.isEmpty()) {
            this.mFilterList.clear();
        }
        processStyleFilterData(list);
        this.mFilterList.addAll(list);
        List<PDStylePropertyEntity> list2 = this.mSelectedProperty;
        if (list2 != null && !list2.isEmpty()) {
            setVisibility(0);
            showFilterView();
            return;
        }
        setVisibility(8);
    }

    public void showFilterView() {
        LinearLayout styleRowView;
        int size = this.mFilterList.size();
        this.mSelectItemViews = new PDStyleBubbleItemView[size];
        this.mFilterTipViews = new PdAutoChangeTextSize[size];
        if (this.mFilterLayout.getChildCount() != 0) {
            this.mFilterLayout.removeAllViews();
            this.mFilterTextViews.clear();
        }
        for (int i2 = 0; i2 < size; i2++) {
            PDStyleFilterEntity pDStyleFilterEntity = this.mFilterList.get(i2);
            if (pDStyleFilterEntity != null) {
                if (pDStyleFilterEntity.hasService) {
                    styleRowView = getBuyStyleRowView(i2, pDStyleFilterEntity);
                } else {
                    styleRowView = getStyleRowView(i2, pDStyleFilterEntity);
                }
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                if (i2 != size - 1) {
                    layoutParams.bottomMargin = dip2px(18);
                    styleRowView.setLayoutParams(layoutParams);
                } else {
                    layoutParams.bottomMargin = dip2px(3);
                    styleRowView.setLayoutParams(layoutParams);
                }
                this.mFilterLayout.addView(styleRowView);
            }
        }
    }

    public void bindData2View(PDStyleColorSizeEntity pDStyleColorSizeEntity, boolean z, boolean z2) {
        bindData2View(pDStyleColorSizeEntity, z, z2, false);
    }

    public void bindData2View(PDStyleColorSizeEntity pDStyleColorSizeEntity, boolean z, boolean z2, boolean z3) {
        DisplayMetrics displayMetrics;
        this.isElder = z3;
        this.bybtPbFlag = false;
        if (pDStyleColorSizeEntity == null || TextUtils.isEmpty(pDStyleColorSizeEntity.skuId)) {
            return;
        }
        Resources resources = getResources();
        if (resources != null && (displayMetrics = resources.getDisplayMetrics()) != null) {
            PdColorSizeUtil.setmDensity(displayMetrics.density);
        }
        setSkuId(pDStyleColorSizeEntity.skuId, z2);
        this.mPdStyleColorSizeEntity = pDStyleColorSizeEntity;
        setSopSkuList(pDStyleColorSizeEntity.sopSkuList, z);
        changeStyle(pDStyleColorSizeEntity.isJX);
        showAllFilter(pDStyleColorSizeEntity.styleEntity);
    }

    public void bindData2View(PDStyleColorSizeEntity pDStyleColorSizeEntity, boolean z, boolean z2, boolean z3, Bundle bundle) {
        DisplayMetrics displayMetrics;
        this.isElder = z3;
        this.bybtPbFlag = false;
        if (pDStyleColorSizeEntity == null || TextUtils.isEmpty(pDStyleColorSizeEntity.skuId)) {
            return;
        }
        Resources resources = getResources();
        if (resources != null && (displayMetrics = resources.getDisplayMetrics()) != null) {
            PdColorSizeUtil.setmDensity(displayMetrics.density);
        }
        if (bundle != null) {
            this.bybtPbFlag = bundle.getBoolean("bybtPbFlag");
        }
        setSkuId(pDStyleColorSizeEntity.skuId, z2);
        this.mPdStyleColorSizeEntity = pDStyleColorSizeEntity;
        setSopSkuList(pDStyleColorSizeEntity.sopSkuList, z);
        changeStyle(pDStyleColorSizeEntity.isJX);
        showAllFilter(pDStyleColorSizeEntity.styleEntity);
    }
}
