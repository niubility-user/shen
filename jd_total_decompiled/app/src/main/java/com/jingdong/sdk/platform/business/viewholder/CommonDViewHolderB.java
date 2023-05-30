package com.jingdong.sdk.platform.business.viewholder;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import androidx.annotation.ColorInt;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSON;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jingdong.common.ui.JDBottomDialog;
import com.jingdong.common.unification.title.theme.ThemeTitleHelper;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.aac.navigator.BaseNavigator;
import com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.base.BaseData;
import com.jingdong.sdk.platform.base.BaseEntity;
import com.jingdong.sdk.platform.base.BaseViewHolder;
import com.jingdong.sdk.platform.business.R;
import com.jingdong.sdk.platform.business.entity.CommonDEntity;
import com.jingdong.sdk.platform.business.entity.CommonDPopEntity;
import com.jingdong.sdk.platform.business.entity.CommonDPopInfoEntity;
import com.jingdong.sdk.platform.business.utils.BusinessTools;
import com.jingdong.sdk.platform.business.utils.JumpHelper;
import com.jingdong.sdk.platform.business.views.PdAutoChangeTextSize;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import com.jingdong.sdk.platform.mta.MtaParams;
import com.jingdong.sdk.platform.utils.PlatformEventUtils;
import com.jingdong.sdk.platform.utils.PlatformTools;
import java.util.List;

/* loaded from: classes10.dex */
public class CommonDViewHolderB extends BaseViewHolder {
    private static final int PADDING = DPIUtil.dip2px(12.0f);
    private CommonDEntity commonDEntity;
    private LinearLayout content;
    private ImageView iIcon;
    private ImageView icon;
    private ImageView iconView;
    private boolean isDark;
    private boolean isTenthRevision;
    private JDBottomDialog mLayerDialog;
    private ListView mLayerListView;
    private PdAutoChangeTextSize tvText1;
    private PdAutoChangeTextSize tvText2;
    private PdAutoChangeTextSize tvTitle;

    /* loaded from: classes10.dex */
    static class Holder {
        SimpleDraweeView arrowImg;
        PdAutoChangeTextSize content;
        PdAutoChangeTextSize title;

        Holder() {
        }
    }

    public CommonDViewHolderB(Context context, BaseData baseData, String str, ViewGroup viewGroup) {
        super(context, baseData, str, viewGroup);
    }

    private int calculatePaddingValue(int i2, int i3, int i4) {
        if (i3 <= 0) {
            i3 = i4;
        }
        if (i2 > i3) {
            return DPIUtil.dip2px((i2 - i3) / 4);
        }
        return 0;
    }

    private int getColorWithTheme(int i2, int i3) {
        return this.isDark ? i2 : ContextCompat.getColor(this.mContext, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ListView getLayerListView(CommonDPopEntity commonDPopEntity) {
        if (commonDPopEntity == null) {
            return null;
        }
        initLayerListView();
        setLayerLvAdapter(commonDPopEntity);
        return this.mLayerListView;
    }

    private void handleIcon() {
        if (!TextUtils.isEmpty(this.commonDEntity.icon)) {
            this.icon.setVisibility(0);
            ViewGroup.LayoutParams layoutParams = this.icon.getLayoutParams();
            layoutParams.width = -2;
            if (this.commonDEntity.iconH > 0) {
                layoutParams.height = DPIUtil.dip2px(r5 / 2);
            } else {
                layoutParams.height = -2;
            }
            this.icon.setLayoutParams(layoutParams);
            JDImageUtils.displayImage(this.commonDEntity.icon, this.icon);
            ImageView imageView = this.icon;
            CommonDEntity commonDEntity = this.commonDEntity;
            setImageDark(imageView, commonDEntity != null && commonDEntity.darkModel && commonDEntity.commonDarkModel, ContextCompat.getColor(getContext(), R.color.pd_color_ececec));
        } else {
            this.icon.setVisibility(8);
        }
        if (!TextUtils.isEmpty(this.commonDEntity.iIcon)) {
            this.iIcon.setVisibility(0);
            ViewGroup.LayoutParams layoutParams2 = this.iIcon.getLayoutParams();
            layoutParams2.height = -2;
            layoutParams2.width = DPIUtil.dip2px(11.0f);
            this.iIcon.setLayoutParams(layoutParams2);
            JDImageUtils.displayImage(this.commonDEntity.iIcon, this.iIcon);
            ImageView imageView2 = this.iIcon;
            CommonDEntity commonDEntity2 = this.commonDEntity;
            setImageDark(imageView2, commonDEntity2 != null && commonDEntity2.darkModel, ContextCompat.getColor(this.mContext, R.color.pd_color_ececec));
        } else {
            this.iIcon.setVisibility(8);
        }
        if (!TextUtils.isEmpty(this.commonDEntity.tailIcon)) {
            this.iconView.setVisibility(0);
            ViewGroup.LayoutParams layoutParams3 = this.iconView.getLayoutParams();
            layoutParams3.width = -2;
            if (this.commonDEntity.tailIconH > 0) {
                layoutParams3.height = DPIUtil.dip2px(r2 / 2);
            } else {
                layoutParams3.height = DPIUtil.dip2px(12.0f);
            }
            this.iconView.setLayoutParams(layoutParams3);
            JDImageUtils.displayImage(this.commonDEntity.tailIcon, this.iconView);
            ImageView imageView3 = this.iconView;
            CommonDEntity commonDEntity3 = this.commonDEntity;
            setImageDark(imageView3, commonDEntity3 != null && commonDEntity3.darkModel, ContextCompat.getColor(this.mContext, R.color.pd_color_ececec));
            return;
        }
        this.iconView.setVisibility(8);
    }

    private void handleIfWireWrapping1() {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.iIcon.getLayoutParams();
        ViewGroup.LayoutParams layoutParams2 = this.mRootView.getLayoutParams();
        if (this.commonDEntity.wireWrapping1) {
            handleWireWrapping1(layoutParams, layoutParams2);
        } else {
            handleNotWireWrapping1(layoutParams, layoutParams2);
        }
    }

    private void handleNotWireWrapping1(RelativeLayout.LayoutParams layoutParams, ViewGroup.LayoutParams layoutParams2) {
        if (this.commonDEntity.height > 0) {
            layoutParams2.height = DPIUtil.dip2px(r0 / 2);
        } else {
            layoutParams2.height = (int) this.mContext.getResources().getDimension(R.dimen.platform_floor_height);
        }
        this.mRootView.setLayoutParams(layoutParams2);
        ViewGroup viewGroup = this.mRootView;
        if (viewGroup instanceof LinearLayout) {
            ((LinearLayout) viewGroup).setGravity(16);
        }
        this.content.setGravity(16);
        this.tvText1.setSingleLine();
        layoutParams.addRule(15);
        this.iIcon.setLayoutParams(layoutParams);
        if (this.isTenthRevision) {
            this.mRootView.setPadding(DPIUtil.dip2px(10.0f), 0, DPIUtil.dip2px(10.0f), 0);
            if (TextUtils.isEmpty(this.commonDEntity.title) && (this.tvTitle.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.tvTitle.getLayoutParams();
                layoutParams3.setMargins(0, 0, PDUtils.dip2px(16.0f), 0);
                this.tvTitle.setLayoutParams(layoutParams3);
            }
            if (TextUtils.isEmpty(this.commonDEntity.text2) && (this.tvText2.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
                LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) this.tvText2.getLayoutParams();
                layoutParams4.setMargins(PDUtils.dip2px(16.0f), 0, PDUtils.dip2px(8.0f), 0);
                this.tvText2.setLayoutParams(layoutParams4);
            }
        } else {
            this.mRootView.setPadding(DPIUtil.dip2px(18.0f), 0, DPIUtil.dip2px(18.0f), 0);
        }
        this.icon.setPadding(0, 0, 0, 0);
        this.tvTitle.setPadding(0, 0, 0, 0);
        if (TextUtils.isEmpty(this.commonDEntity.iIcon)) {
            this.tvText1.setPadding(0, 0, 0, 0);
        } else {
            this.tvText1.setPadding(0, 0, DPIUtil.dip2px(13.0f), 0);
        }
        layoutParams.setMargins(0, 0, 0, 0);
        ((LinearLayout.LayoutParams) this.iconView.getLayoutParams()).setMargins(DPIUtil.dip2px(5.0f), 0, 0, 0);
    }

    private void handleText() {
        Context context;
        int i2;
        Context context2;
        int i3;
        if (!TextUtils.isEmpty(this.commonDEntity.title)) {
            this.tvTitle.setVisibility(0);
            this.tvTitle.setText(this.commonDEntity.title);
            if (this.isTenthRevision) {
                context2 = this.mContext;
                i3 = R.color.pd_color_848383;
            } else {
                context2 = this.mContext;
                i3 = R.color.pd_color_ececec;
            }
            int color = ContextCompat.getColor(context2, i3);
            CommonDEntity commonDEntity = this.commonDEntity;
            if (!commonDEntity.darkModel) {
                color = PlatformTools.getColorValue(commonDEntity.titleC, getResources().getColor(R.color.platform_color_262626));
            }
            this.tvTitle.setTextColor(color);
            this.tvTitle.setTextSize(1, PlatformTools.getTextSizeValue(this.commonDEntity.titleS, this.isTenthRevision ? 14 : 13));
            BusinessTools.setTextBold(this.tvTitle, this.commonDEntity.titleB, false);
        } else {
            this.tvTitle.setVisibility(8);
        }
        if (!TextUtils.isEmpty(this.commonDEntity.text1)) {
            this.tvText1.setVisibility(0);
            this.tvText1.setText(this.commonDEntity.text1);
            if (this.isTenthRevision) {
                context = this.mContext;
                i2 = R.color.pd_color_848383;
            } else {
                context = this.mContext;
                i2 = R.color.pd_color_ececec;
            }
            int color2 = ContextCompat.getColor(context, i2);
            CommonDEntity commonDEntity2 = this.commonDEntity;
            if (!commonDEntity2.darkModel) {
                color2 = PlatformTools.getColorValue(commonDEntity2.text1C, getResources().getColor(R.color.platform_color_262626));
            }
            this.tvText1.setTextColor(color2);
            this.tvText1.setTextSize(1, PlatformTools.getTextSizeValue(this.commonDEntity.text1S, this.isTenthRevision ? 14 : 13));
            PdAutoChangeTextSize pdAutoChangeTextSize = this.tvText1;
            CommonDEntity commonDEntity3 = this.commonDEntity;
            BusinessTools.setTextBold(pdAutoChangeTextSize, commonDEntity3.text1B, commonDEntity3.jdfont);
        } else {
            this.tvText1.setVisibility(8);
        }
        if (!TextUtils.isEmpty(this.commonDEntity.text2)) {
            this.tvText2.setVisibility(0);
            this.tvText2.setText(this.commonDEntity.text2);
            CommonDEntity commonDEntity4 = this.commonDEntity;
            this.tvText2.setTextColor(commonDEntity4.darkModel ? ContextCompat.getColor(this.mContext, R.color.pd_color_848383) : PlatformTools.getColorValue(commonDEntity4.text2C, getResources().getColor(R.color.platform_color_848484)));
            this.tvText2.setTextSize(1, PlatformTools.getTextSizeValue(this.commonDEntity.text2S, this.isTenthRevision ? 14 : 13));
            BusinessTools.setTextBold(this.tvText2, this.commonDEntity.text2B, false);
            return;
        }
        this.tvText2.setVisibility(8);
    }

    private void handleWireWrapping1(RelativeLayout.LayoutParams layoutParams, ViewGroup.LayoutParams layoutParams2) {
        int i2;
        int i3;
        int i4;
        layoutParams2.height = -2;
        this.mRootView.setLayoutParams(layoutParams2);
        ViewGroup viewGroup = this.mRootView;
        if (viewGroup instanceof LinearLayout) {
            ((LinearLayout) viewGroup).setGravity(48);
        }
        this.content.setGravity(48);
        this.tvText1.setSingleLine(false);
        layoutParams.addRule(10);
        this.iIcon.setLayoutParams(layoutParams);
        CommonDEntity commonDEntity = this.commonDEntity;
        int i5 = commonDEntity.height;
        if (i5 <= 0) {
            i5 = 100;
        }
        commonDEntity.height = i5;
        int i6 = commonDEntity.iconH;
        commonDEntity.iconH = i6 > 0 ? i6 : 48;
        if (TextUtils.isEmpty(commonDEntity.icon)) {
            i2 = 0;
        } else {
            CommonDEntity commonDEntity2 = this.commonDEntity;
            i2 = DPIUtil.dip2px((commonDEntity2.height - commonDEntity2.iconH) / 4);
        }
        if (TextUtils.isEmpty(this.commonDEntity.title)) {
            i3 = 0;
        } else {
            CommonDEntity commonDEntity3 = this.commonDEntity;
            i3 = calculatePaddingValue(commonDEntity3.height, commonDEntity3.titleS, 24);
        }
        if (TextUtils.isEmpty(this.commonDEntity.text1)) {
            i4 = 0;
        } else {
            CommonDEntity commonDEntity4 = this.commonDEntity;
            i4 = calculatePaddingValue(commonDEntity4.height, commonDEntity4.text1S, 24);
        }
        int min = Math.min(i2, i3);
        if (min <= 0) {
            min = Math.max(i2, i3);
        }
        int min2 = Math.min(min, i4);
        if (min2 <= 0) {
            min2 = Math.max(min2, i4);
        }
        if (this.isTenthRevision) {
            this.mRootView.setPadding(DPIUtil.dip2px(10.0f), min2, DPIUtil.dip2px(10.0f), min2);
            if (TextUtils.isEmpty(this.commonDEntity.title) && (this.tvTitle.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.tvTitle.getLayoutParams();
                layoutParams3.setMargins(0, 0, PDUtils.dip2px(16.0f), 0);
                this.tvTitle.setLayoutParams(layoutParams3);
            }
            if (TextUtils.isEmpty(this.commonDEntity.text2) && (this.tvText2.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
                LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) this.tvText2.getLayoutParams();
                layoutParams4.setMargins(PDUtils.dip2px(16.0f), 0, PDUtils.dip2px(8.0f), 0);
                this.tvText2.setLayoutParams(layoutParams4);
            }
            this.tvTitle.setLineSpacing(PDUtils.dip2px(8.0f), 1.0f);
            this.tvText1.setLineSpacing(PDUtils.dip2px(8.0f), 1.0f);
            this.tvText2.setLineSpacing(PDUtils.dip2px(8.0f), 1.0f);
        } else {
            this.mRootView.setPadding(DPIUtil.dip2px(18.0f), min2, DPIUtil.dip2px(18.0f), min2);
        }
        this.icon.setPadding(0, i2 - min2, 0, 0);
        this.tvTitle.setPadding(0, i3 - min2, 0, 0);
        if (TextUtils.isEmpty(this.commonDEntity.iIcon)) {
            this.tvText1.setPadding(0, i4 - min2, 0, 0);
        } else {
            this.tvText1.setPadding(0, i4 - min2, DPIUtil.dip2px(13.0f), 0);
        }
        int i7 = i4 - min2;
        layoutParams.setMargins(0, DPIUtil.dip2px(2.0f) + i7, 0, 0);
        ((LinearLayout.LayoutParams) this.iconView.getLayoutParams()).setMargins(DPIUtil.dip2px(5.0f), i7 + DPIUtil.dip2px(2.0f), 0, 0);
        if (OKLog.D) {
            OKLog.d("commonD", "paddingIcon = " + i2 + " paddingTitle=" + i3 + " paddingText1=" + i4);
        }
    }

    private void initLayerListView() {
        Context context;
        int i2;
        if (this.mLayerListView == null) {
            this.mLayerListView = new ListView(this.mContext);
            this.mLayerListView.setBackgroundColor(ContextCompat.getColor(this.mContext, this.isDark ? R.color.pd_color_1d1b1b : R.color.pd_white));
            this.mLayerListView.setCacheColorHint(PlatformTools.parseColor("#00000000"));
            this.mLayerListView.setSelector(R.color.platform_color_transparent);
            if (this.isDark) {
                context = this.mContext;
                i2 = R.drawable.pd_drawable_555353;
            } else {
                context = this.mContext;
                i2 = R.drawable.pd_drawable_e5e5e5;
            }
            this.mLayerListView.setDivider(ContextCompat.getDrawable(context, i2));
            this.mLayerListView.setDividerHeight(1);
            this.mLayerListView.setVerticalScrollBarEnabled(false);
            this.mLayerListView.setPadding(DPIUtil.dip2px(10.0f), 0, DPIUtil.dip2px(10.0f), 0);
            this.mLayerListView.setFooterDividersEnabled(false);
        }
    }

    private void setImageDark(ImageView imageView, boolean z, @ColorInt int i2) {
        GenericDraweeHierarchy hierarchy;
        if (!(imageView instanceof SimpleDraweeView) || (hierarchy = ((SimpleDraweeView) imageView).getHierarchy()) == null) {
            return;
        }
        hierarchy.setActualImageColorFilter(new PorterDuffColorFilter(i2, z ? PorterDuff.Mode.SRC_IN : PorterDuff.Mode.DST));
    }

    private void setLayerLvAdapter(final CommonDPopEntity commonDPopEntity) {
        final List<CommonDPopInfoEntity> list = commonDPopEntity.popInfo;
        if (list == null || list.isEmpty()) {
            return;
        }
        this.mLayerListView.setAdapter((ListAdapter) new BaseAdapter() { // from class: com.jingdong.sdk.platform.business.viewholder.CommonDViewHolderB.4
            @Override // android.widget.Adapter
            public int getCount() {
                return list.size();
            }

            @Override // android.widget.Adapter
            public long getItemId(int i2) {
                return 0L;
            }

            @Override // android.widget.Adapter
            public View getView(int i2, View view, ViewGroup viewGroup) {
                Holder holder;
                int colorValue;
                int colorValue2;
                if (view == null) {
                    view = ImageUtil.inflate(((LifecycleBaseViewHolder) CommonDViewHolderB.this).mContext, R.layout.lib_platform_floor_d_layer_item, (ViewGroup) null);
                    holder = new Holder();
                    holder.arrowImg = (SimpleDraweeView) view.findViewById(R.id.common_d_layer_item_arrow_img);
                    holder.title = (PdAutoChangeTextSize) view.findViewById(R.id.common_d_layer_item_short_name);
                    holder.content = (PdAutoChangeTextSize) view.findViewById(R.id.common_d_layer_item_name);
                    view.setTag(holder);
                } else {
                    holder = (Holder) view.getTag();
                }
                final CommonDPopInfoEntity item = getItem(i2);
                if (item != null) {
                    if (!TextUtils.isEmpty(item.subTitle)) {
                        holder.title.setVisibility(0);
                        holder.title.setText(item.subTitle);
                        if (CommonDViewHolderB.this.isDark) {
                            colorValue2 = ContextCompat.getColor(((LifecycleBaseViewHolder) CommonDViewHolderB.this).mContext, R.color.pd_color_ececec);
                        } else {
                            colorValue2 = PlatformTools.getColorValue(commonDPopEntity.subTitleC, CommonDViewHolderB.this.getResources().getColor(R.color.platform_color_2E2D2D));
                        }
                        holder.title.setTextColor(colorValue2);
                    } else {
                        holder.title.setVisibility(8);
                    }
                    if (!TextUtils.isEmpty(item.subDes)) {
                        holder.content.setVisibility(0);
                        holder.content.setText(item.subDes);
                        if (CommonDViewHolderB.this.isDark) {
                            colorValue = ContextCompat.getColor(((LifecycleBaseViewHolder) CommonDViewHolderB.this).mContext, R.color.pd_color_848383);
                        } else {
                            colorValue = PlatformTools.getColorValue(commonDPopEntity.subDesC, CommonDViewHolderB.this.getResources().getColor(R.color.platform_color_848484));
                        }
                        holder.content.setTextColor(colorValue);
                    } else {
                        holder.content.setVisibility(8);
                    }
                    holder.arrowImg.setVisibility(4);
                    if (!TextUtils.isEmpty(item.subTitle) && !TextUtils.isEmpty(item.subUrl)) {
                        int i3 = CommonDViewHolderB.this.isDark ? R.drawable.platform_right_arrow_dark : R.drawable.platform_arrow_r;
                        holder.arrowImg.setVisibility(0);
                        holder.arrowImg.setImageResource(i3);
                    }
                    if (!TextUtils.isEmpty(item.subUrl)) {
                        view.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.sdk.platform.business.viewholder.CommonDViewHolderB.4.1
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view2) {
                                JumpHelper.gotoMWithUrl(((LifecycleBaseViewHolder) CommonDViewHolderB.this).mContext, item.subUrl);
                            }
                        });
                    }
                }
                view.setPadding(0, CommonDViewHolderB.PADDING, 0, CommonDViewHolderB.PADDING);
                return view;
            }

            @Override // android.widget.Adapter
            public CommonDPopInfoEntity getItem(int i2) {
                if (i2 < 0 || i2 >= list.size()) {
                    return null;
                }
                return (CommonDPopInfoEntity) list.get(i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCommonDLayerDialog(ListView listView, CommonDPopEntity commonDPopEntity) {
        if (this.mLayerDialog == null) {
            this.mLayerDialog = new JDBottomDialog(this.mContext);
        }
        this.mLayerDialog.setTitleTextColor(R.color.platform_color_81838e);
        this.mLayerDialog.addContentWithTitleAndHeight(commonDPopEntity.ppTitle, listView, commonDPopEntity.btn, false, true);
        this.mLayerDialog.setPosBtnClickListener(new View.OnClickListener() { // from class: com.jingdong.sdk.platform.business.viewholder.CommonDViewHolderB.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CommonDViewHolderB.this.mLayerDialog == null || !CommonDViewHolderB.this.mLayerDialog.isShowing()) {
                    return;
                }
                CommonDViewHolderB.this.mLayerDialog.dismiss();
            }
        });
        this.mLayerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.jingdong.sdk.platform.business.viewholder.CommonDViewHolderB.3
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                PlatformEventUtils.sendDialogHideStatus(((BaseViewHolder) CommonDViewHolderB.this).mFloorData.mManageKey);
            }
        });
        if (BusinessTools.getImageInfoEntity()) {
            this.mLayerDialog.changeToTheme(ThemeTitleHelper.getTitleBg(this.mContext, "DETAILPOPLAYER", this.isDark), Integer.valueOf(ThemeTitleHelper.getTitleTextColor(this.mContext, "DETAILPOPLAYER", this.isDark)), getResources().getDrawable(R.drawable.lib_pd_dialog_hf_close));
        } else {
            this.mLayerDialog.toDefaultTheme();
            this.mLayerDialog.setTitleTextColor(PlatformTools.getColorValue(commonDPopEntity.ppTitleC, getResources().getColor(R.color.platform_color_2E2D2D)));
        }
        this.mLayerDialog.setDarkMode(this.isDark);
        this.mLayerDialog.show();
        PlatformEventUtils.sendDialogShowStatus(this.mFloorData.mManageKey);
    }

    @Override // com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder
    protected BaseNavigator createNavigator() {
        return null;
    }

    @Override // com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder
    public Class getViewModelClass() {
        return null;
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected void initView() {
        this.content = (LinearLayout) findViewById(R.id.platform_floor_d_content);
        this.icon = (SimpleDraweeView) findViewById(R.id.platform_floor_d_icon);
        this.tvTitle = (PdAutoChangeTextSize) findViewById(R.id.platform_floor_d_title);
        this.tvText1 = (PdAutoChangeTextSize) findViewById(R.id.platform_floor_d_text1);
        this.iIcon = (SimpleDraweeView) findViewById(R.id.platform_floor_d_i_icon);
        this.tvText2 = (PdAutoChangeTextSize) findViewById(R.id.platform_floor_d_text2);
        this.iconView = (SimpleDraweeView) findViewById(R.id.platform_floor_d_arrow);
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected void onCreatedView() {
        this.mLayoutId = R.layout.lib_platform_floor_d_b;
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected void onDestroyView() {
        this.tvTitle = null;
        this.tvText1 = null;
        this.iIcon = null;
        this.tvText2 = null;
        this.iconView = null;
        this.icon = null;
        this.mLayerListView = null;
        this.mLayerDialog = null;
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected void setBackGroundColor() {
        if (!canSetBg() || this.mRootView == null || isLine()) {
            return;
        }
        V v = this.mFloorEntity;
        int i2 = 0;
        if (v != 0) {
            String str = v.backgroundColor;
            if (!TextUtils.isEmpty(str)) {
                try {
                    i2 = Color.parseColor(str);
                } catch (Exception e2) {
                    PlatformTools.catchException(e2);
                }
            }
        }
        if (i2 == 0) {
            V v2 = this.mFloorEntity;
            if (v2 == 0 || v2.canUseDefaultBgColor) {
                int backGroundColor = getBackGroundColor();
                if (backGroundColor == 0) {
                    backGroundColor = Color.parseColor(JDDarkUtil.COLOR_FFFFFFF);
                }
                if (getBaseEntity() instanceof BaseTemplateEntity) {
                    ViewCompat.setBackground(this.mRootView, BusinessTools.getFloorBgDrawable((BaseTemplateEntity) getBaseEntity(), backGroundColor));
                    return;
                }
                this.mRootView.setBackgroundColor(backGroundColor);
            }
        } else if (getBaseEntity() instanceof BaseTemplateEntity) {
            ViewCompat.setBackground(this.mRootView, BusinessTools.getFloorBgDrawable((BaseTemplateEntity) getBaseEntity(), i2));
        } else {
            this.mRootView.setBackgroundColor(i2);
        }
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    public void showData(BaseEntity baseEntity) {
        Object obj = baseEntity.mData;
        if (obj != null) {
            CommonDEntity commonDEntity = (CommonDEntity) JDJSON.parseObject(obj.toString(), CommonDEntity.class);
            this.commonDEntity = commonDEntity;
            boolean z = (TextUtils.isEmpty(commonDEntity.text1) && TextUtils.isEmpty(this.commonDEntity.title)) ? false : true;
            CommonDEntity commonDEntity2 = this.commonDEntity;
            if (commonDEntity2 != null && z) {
                this.isDark = commonDEntity2.darkModel;
                this.isTenthRevision = commonDEntity2.tenthRevision;
                handleIfWireWrapping1();
                handleText();
                handleIcon();
                this.mRootView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.sdk.platform.business.viewholder.CommonDViewHolderB.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (!TextUtils.isEmpty(CommonDViewHolderB.this.commonDEntity.jumpUrl)) {
                            JumpHelper.jump(((LifecycleBaseViewHolder) CommonDViewHolderB.this).mContext, CommonDViewHolderB.this.commonDEntity.jumpUrl, CommonDViewHolderB.this.commonDEntity.jumpType);
                        } else if (CommonDViewHolderB.this.commonDEntity.jumpType == 3 && CommonDViewHolderB.this.commonDEntity.pop != null) {
                            CommonDViewHolderB commonDViewHolderB = CommonDViewHolderB.this;
                            commonDViewHolderB.showCommonDLayerDialog(commonDViewHolderB.getLayerListView(commonDViewHolderB.commonDEntity.pop), CommonDViewHolderB.this.commonDEntity.pop);
                        }
                        if (CommonDViewHolderB.this.commonDEntity.jumpType > 0) {
                            PlatformEventUtils.sendMtaEvent(CommonDViewHolderB.this.getBaseData().mManageKey, new MtaParams(CommonDViewHolderB.this.commonDEntity.buried + "_bpCommonFloor", CommonDViewHolderB.this.getBaseEntity().getMtaParam()));
                        }
                    }
                });
                if (this.commonDEntity.darkModel) {
                    int color = ContextCompat.getColor(this.mContext, R.color.pd_color_1d1b1b);
                    if (getBaseEntity() instanceof BaseTemplateEntity) {
                        ViewCompat.setBackground(this.mRootView, BusinessTools.getFloorBgDrawable((BaseTemplateEntity) getBaseEntity(), color, this.commonDEntity.darkModel, this.isTenthRevision));
                        return;
                    } else {
                        this.mRootView.setBackgroundColor(color);
                        return;
                    }
                }
                return;
            }
            hideViewHolder();
        }
    }
}
