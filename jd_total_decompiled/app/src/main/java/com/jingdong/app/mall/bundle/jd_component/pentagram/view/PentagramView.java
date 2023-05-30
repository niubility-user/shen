package com.jingdong.app.mall.bundle.jd_component.pentagram.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.productdetail.core.views.VerticalImageSpan;
import com.jdcn.fido.constant.BasicInformation;
import com.jingdong.app.mall.bundle.jd_component.R;
import com.jingdong.app.mall.bundle.jd_component.pentagram.entity.CompareEntity;
import com.jingdong.app.mall.bundle.jd_component.pentagram.entity.NullDataUi;
import com.jingdong.app.mall.bundle.jd_component.pentagram.entity.ProductForRadar;
import com.jingdong.app.mall.bundle.jd_component.pentagram.entity.RadarColorData;
import com.jingdong.app.mall.bundle.jd_component.pentagram.entity.RadarColorDataItem;
import com.jingdong.app.mall.bundle.jd_component.pentagram.entity.RadarColorObject;
import com.jingdong.app.mall.bundle.jd_component.pentagram.entity.RadarEntity;
import com.jingdong.app.mall.bundle.jd_component.pentagram.entity.RadarFloor;
import com.jingdong.app.mall.bundle.jd_component.pentagram.entity.RaderViewData;
import com.jingdong.app.mall.bundle.jd_component.pentagram.entity.RootBg;
import com.jingdong.app.mall.bundle.jd_component.pentagram.entity.ScoreEntity;
import com.jingdong.app.mall.bundle.jd_component.pentagram.entity.ScoreEntityUi;
import com.jingdong.app.mall.bundle.jd_component.pentagram.entity.TitleEntity;
import com.jingdong.app.mall.bundle.jd_component.pentagram.entity.TitleEntityUi;
import com.jingdong.app.mall.bundle.jd_component.pentagram.utils.PentagramViewUtils;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.common.baseRecycleAdapter.BaseQuickAdapter;
import com.jingdong.common.baseRecycleAdapter.BaseViewHolder;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public class PentagramView extends BaseView<RadarFloor> {
    private TextView compareText;
    private LinearLayout compareWrapper;
    private RelativeLayout dataWrapper;
    private TextView insufficientView;
    private GridLayoutManager layoutManager;
    public Listener listener;
    private ProductAdapter mAdapter;
    private TextView noCompareText;
    private RecyclerView productList;
    private RadarFloor radarFloor;
    private RadarView radarView;
    private RelativeLayout root;
    private TextView score;
    private TextView scoreText;
    private TextView scoreTitle;
    private TextView subTitle;
    private TextView title;

    /* loaded from: classes2.dex */
    public interface Listener {
        void getComparison();

        void onProductNameClick(ProductForRadar productForRadar, int i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class ProductAdapter extends BaseQuickAdapter<ProductForRadar, BaseViewHolder> {
        private Listener listener;
        Listener listenerAdapter;

        public ProductAdapter(int i2) {
            super(i2);
            this.listenerAdapter = new Listener() { // from class: com.jingdong.app.mall.bundle.jd_component.pentagram.view.PentagramView.ProductAdapter.1
                @Override // com.jingdong.app.mall.bundle.jd_component.pentagram.view.PentagramView.Listener
                public void getComparison() {
                    if (ProductAdapter.this.listener != null) {
                        ProductAdapter.this.listener.getComparison();
                    }
                }

                @Override // com.jingdong.app.mall.bundle.jd_component.pentagram.view.PentagramView.Listener
                public void onProductNameClick(ProductForRadar productForRadar, int i3) {
                    if (ProductAdapter.this.listener != null) {
                        ProductAdapter.this.listener.onProductNameClick(productForRadar, i3);
                    }
                }
            };
        }

        public void setListener(Listener listener) {
            this.listener = listener;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jingdong.common.baseRecycleAdapter.BaseQuickAdapter
        public void convert(BaseViewHolder baseViewHolder, ProductForRadar productForRadar) {
            ProductItemView productItemView = (ProductItemView) baseViewHolder.getView(R.id.product_view);
            productItemView.setListener(this.listenerAdapter);
            productItemView.bindData(productForRadar, baseViewHolder.getAdapterPosition());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.jingdong.common.baseRecycleAdapter.BaseQuickAdapter
        public BaseViewHolder createBaseViewHolder(View view) {
            return new BaseViewHolder(view);
        }
    }

    public PentagramView(@NonNull Context context) {
        super(context);
    }

    public void bindBg() {
        if (this.root == null) {
            return;
        }
        RootBg rootBg = this.radarFloor.getVisualData().rootBg;
        if (rootBg == null) {
            this.root.setBackgroundResource(PentagramViewUtils.isDarkConfig() ? R.drawable.lib_so_pentagram_wrapper_dark_bg : R.drawable.lib_so_pentagram_wrapper_bg);
        } else if (PentagramViewUtils.isDarkConfig()) {
            setBg(rootBg.viewDarkBg, R.drawable.lib_so_pentagram_wrapper_dark_bg);
        } else {
            setBg(rootBg.viewBg, R.drawable.lib_so_pentagram_wrapper_bg);
        }
    }

    public void bindCompareData() {
        if (this.compareWrapper == null || this.compareText == null || this.noCompareText == null) {
            return;
        }
        final CompareEntity compareEntity = this.radarFloor.getRadarEntity().getCompareEntity();
        if (compareEntity != null) {
            String str = compareEntity.buttonType;
            str.hashCode();
            char c2 = '\uffff';
            switch (str.hashCode()) {
                case 48:
                    if (str.equals("0")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 49:
                    if (str.equals("1")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 50:
                    if (str.equals("2")) {
                        c2 = 2;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    this.compareText.setVisibility(8);
                    this.noCompareText.setVisibility(8);
                    return;
                case 1:
                    this.compareText.setVisibility(8);
                    if (!TextUtils.isEmpty(compareEntity.compareText)) {
                        this.noCompareText.setVisibility(0);
                        if (!TextUtils.isEmpty(compareEntity.compareIcon)) {
                            JDImageUtils.loadImage(compareEntity.compareIcon, new JDImageLoadingListener() { // from class: com.jingdong.app.mall.bundle.jd_component.pentagram.view.PentagramView.3
                                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                                public void onLoadingCancelled(String str2, View view) {
                                }

                                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                                public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                                    SpannableString spannableString = new SpannableString("  " + compareEntity.compareText);
                                    BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
                                    bitmapDrawable.setBounds(0, 0, DPIUtil.dip2px(10.0f), DPIUtil.dip2px(10.0f));
                                    spannableString.setSpan(new VerticalImageSpan(bitmapDrawable), 0, 1, 17);
                                    PentagramView.this.noCompareText.setText(spannableString);
                                }

                                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                                public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                                    PentagramView.this.noCompareText.setText(compareEntity.compareText);
                                }

                                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                                public void onLoadingStarted(String str2, View view) {
                                }
                            });
                            return;
                        } else {
                            this.noCompareText.setText(compareEntity.compareText);
                            return;
                        }
                    }
                    this.noCompareText.setVisibility(8);
                    return;
                case 2:
                    this.compareText.setText(compareEntity.compareText);
                    this.compareText.setVisibility(TextUtils.isEmpty(compareEntity.compareText) ? 8 : 0);
                    this.noCompareText.setVisibility(8);
                    return;
                default:
                    return;
            }
        }
        this.compareText.setVisibility(8);
        this.noCompareText.setVisibility(8);
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.pentagram.view.BaseView
    protected void bindEvent() {
        TextView textView = this.compareText;
        if (textView != null) {
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.jd_component.pentagram.view.PentagramView.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Listener listener = PentagramView.this.listener;
                    if (listener != null) {
                        listener.getComparison();
                    }
                }
            });
        }
    }

    public void bindNameWrapper() {
        if (this.productList == null) {
            return;
        }
        List<RaderViewData> userScoreTagList = this.radarFloor.getRadarEntity().getUserScoreTagList();
        List<RadarColorDataItem> list = this.radarFloor.getVisualData().radarColorDataList;
        if (userScoreTagList != null && userScoreTagList.size() > 0) {
            if (list != null && list.size() > 0) {
                ArrayList arrayList = new ArrayList();
                int min = Math.min(userScoreTagList.size(), 3);
                for (int i2 = 0; i2 < min; i2++) {
                    ProductForRadar productForRadar = new ProductForRadar();
                    productForRadar.name = userScoreTagList.get(i2).name;
                    productForRadar.colors = list.get(i2).colors;
                    productForRadar.darkColors = list.get(i2).darkColors;
                    productForRadar.nameColor = list.get(i2).nameColor;
                    productForRadar.nameDarkColor = list.get(i2).nameDarkColor;
                    productForRadar.corners = list.get(i2).corners;
                    productForRadar.wareJumpUrl = userScoreTagList.get(i2).wareJumpUrl;
                    arrayList.add(productForRadar);
                }
                if (arrayList.size() > 0) {
                    this.productList.setVisibility(0);
                    this.mAdapter.setNewData(arrayList);
                    return;
                }
                this.productList.setVisibility(8);
                return;
            }
            this.productList.setVisibility(8);
            return;
        }
        this.productList.setVisibility(8);
    }

    public void bindNullData() {
        if (this.root == null) {
            return;
        }
        String nullData = this.radarFloor.getRadarEntity().getNullData();
        NullDataUi nullDataUi = this.radarFloor.getVisualData().nullDataUi;
        if (nullDataUi == null) {
            this.insufficientView.setVisibility(8);
            return;
        }
        if (this.root.getLayoutParams() != null) {
            ViewGroup.LayoutParams layoutParams = this.root.getLayoutParams();
            layoutParams.height = DPIUtil.dip2px(-2.0f);
            this.root.setLayoutParams(layoutParams);
        }
        this.dataWrapper.setVisibility(8);
        this.insufficientView.setVisibility(0);
        this.insufficientView.setText(nullData);
        if (PentagramViewUtils.isDarkConfig()) {
            this.root.setBackgroundResource(R.drawable.lib_so_pentagram_wrapper_dark_bg);
            this.insufficientView.setTextColor(Color.parseColor(!TextUtils.isEmpty(nullDataUi.textDarkColor) ? nullDataUi.textDarkColor : "#CCFFFFFF"));
            return;
        }
        this.root.setBackgroundResource(R.drawable.lib_so_pentagram_wrapper_bg);
        this.insufficientView.setTextColor(Color.parseColor(!TextUtils.isEmpty(nullDataUi.textColor) ? nullDataUi.textColor : "#CC1A1A1A"));
    }

    public void bindRadarView() {
        if (this.radarView == null) {
            return;
        }
        List<RaderViewData> userScoreTagList = this.radarFloor.getRadarEntity().getUserScoreTagList();
        List<RadarColorDataItem> list = this.radarFloor.getVisualData().radarColorDataList;
        List<RadarColorData> list2 = this.radarFloor.getVisualData().radarBgColor;
        List<String> list3 = this.radarFloor.getVisualData().scaleList;
        if (userScoreTagList != null && userScoreTagList.size() > 0) {
            if (list != null && list.size() > 0) {
                if (list2 != null && list2.size() > 0) {
                    int i2 = 6;
                    if (list3 == null || list3.size() != 6) {
                        list3 = new ArrayList<>(Arrays.asList("4", "4.2", "4.4", "4.6", "4.8", BasicInformation.SDK_VERSION));
                    }
                    RadarColorObject radarColorObject = new RadarColorObject();
                    radarColorObject.radarBgColor = list2;
                    radarColorObject.radarColorDataList = list;
                    Paint paint = new Paint();
                    paint.setTextSize(DPIUtil.sp2px(getContext(), 9.0f));
                    int size = userScoreTagList.get(0).scoreList.size();
                    String str = "";
                    int i3 = 0;
                    float f2 = 0.0f;
                    float f3 = 0.0f;
                    while (i3 < size) {
                        str = userScoreTagList.get(0).scoreList.get(i3).getTagName();
                        if (!TextUtils.isEmpty(str)) {
                            f2 = Math.max(f2, paint.measureText(str.substring(0, Math.min(str.length(), i2))));
                        }
                        StringBuilder sb = new StringBuilder();
                        for (int i4 = 0; i4 < userScoreTagList.size(); i4++) {
                            if (userScoreTagList.get(i4).scoreList != null && userScoreTagList.get(i4).scoreList.size() == userScoreTagList.get(0).scoreList.size()) {
                                if (i4 != 0) {
                                    sb.append("/");
                                }
                                sb.append(userScoreTagList.get(i4).scoreList.get(i3).getScore());
                            }
                        }
                        f3 = Math.max(f3, paint.measureText(sb.toString()));
                        i3++;
                        i2 = 6;
                    }
                    double d = size;
                    Double.isNaN(d);
                    float f4 = (float) (6.283185307179586d / d);
                    double max = Math.max(f2, f3);
                    double sin = Math.sin(f4 / 2.0f);
                    Double.isNaN(max);
                    float f5 = (float) ((max * sin) / 2.0d);
                    int i5 = 0;
                    float f6 = 0.0f;
                    while (i5 < size) {
                        double dip2px = DPIUtil.dip2px(43.0f) + f5;
                        float f7 = f4;
                        double d2 = i5 * f4;
                        Double.isNaN(d2);
                        double d3 = 3.141592653589793d - d2;
                        double sin2 = Math.sin(d3);
                        Double.isNaN(dip2px);
                        float f8 = (float) (dip2px * sin2);
                        if (f8 < 0.0f) {
                            double dip2px2 = DPIUtil.dip2px(43.0f) + f5;
                            double sin3 = Math.sin(d3);
                            Double.isNaN(dip2px2);
                            f8 = ((float) (dip2px2 * sin3)) - f2;
                        }
                        f6 = Math.max(f6, Math.abs(f8));
                        i5++;
                        f4 = f7;
                    }
                    paint.getTextBounds(str, 0, str.length(), new Rect());
                    final float abs = (f6 * 1.1f) + (Math.abs(f3 - r3.width()) / 2.0f);
                    this.radarView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.jingdong.app.mall.bundle.jd_component.pentagram.view.PentagramView.4
                        @Override // android.view.ViewTreeObserver.OnPreDrawListener
                        public boolean onPreDraw() {
                            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) PentagramView.this.radarView.getLayoutParams();
                            layoutParams.width = (int) (abs * 2.0f);
                            PentagramView.this.radarView.setLayoutParams(layoutParams);
                            if (PentagramView.this.productList != null) {
                                PentagramView.this.radarView.getViewTreeObserver().removeOnPreDrawListener(this);
                                int appWidth = (DPIUtil.getAppWidth(PentagramView.this.getContext() instanceof Activity ? (Activity) PentagramView.this.getContext() : null) - DPIUtil.dip2px(60.0f)) - ((int) (abs * 2.0f));
                                PentagramView.this.layoutManager.findViewByPosition(0);
                                ViewGroup.LayoutParams layoutParams2 = PentagramView.this.productList.getLayoutParams();
                                layoutParams2.width = appWidth;
                                PentagramView.this.productList.setLayoutParams(layoutParams2);
                                PentagramView.this.productList.requestLayout();
                                return true;
                            }
                            return true;
                        }
                    });
                    this.radarView.setDataList(userScoreTagList, radarColorObject, list3);
                    this.radarView.setCircleCount(this.radarFloor.getVisualData().circleCount);
                    this.radarView.setVisibility(0);
                    return;
                }
                this.radarView.setVisibility(8);
                return;
            }
            this.radarView.setVisibility(8);
            return;
        }
        this.radarView.setVisibility(8);
    }

    public void bindScore() {
        if (this.scoreTitle == null || this.scoreText == null || this.score == null) {
            return;
        }
        if (this.radarFloor.getRadarEntity().getScoreEntity() != null && this.radarFloor.getVisualData().scoreEntityUi != null) {
            ScoreEntity scoreEntity = this.radarFloor.getRadarEntity().getScoreEntity();
            ScoreEntityUi scoreEntityUi = this.radarFloor.getVisualData().scoreEntityUi;
            if (!TextUtils.isEmpty(scoreEntity.getScoreTitle()) && !TextUtils.isEmpty(scoreEntity.getScore()) && !TextUtils.isEmpty(scoreEntity.getScoreText())) {
                this.scoreTitle.setTextSize(10.0f);
                this.scoreTitle.setText(scoreEntity.getScoreTitle());
                this.scoreTitle.setVisibility(0);
                this.score.setTextSize(18.0f);
                FontsUtil.changeTextFont(this.score, 4099);
                this.score.setText(scoreEntity.getScore());
                this.score.setVisibility(0);
                this.scoreText.setTextSize(10.0f);
                FontsUtil.changeTextFont(this.scoreText, 4099);
                this.scoreText.setText(scoreEntity.getScoreText());
                this.scoreText.setVisibility(0);
                boolean isDarkConfig = PentagramViewUtils.isDarkConfig();
                String str = JDDarkUtil.COLOR_CCCCCC;
                if (isDarkConfig) {
                    TextView textView = this.scoreTitle;
                    if (!TextUtils.isEmpty(scoreEntityUi.getTitleDarkColor())) {
                        str = scoreEntityUi.getTitleDarkColor();
                    }
                    textView.setTextColor(Color.parseColor(str));
                    this.score.setTextColor(Color.parseColor(!TextUtils.isEmpty(scoreEntityUi.getScoreDarkColor()) ? scoreEntityUi.getScoreDarkColor() : "#B32D00"));
                    this.scoreText.setTextColor(Color.parseColor(TextUtils.isEmpty(scoreEntityUi.getTextDarkColor()) ? "#B32D00" : scoreEntityUi.getTextDarkColor()));
                    return;
                }
                TextView textView2 = this.scoreTitle;
                if (!TextUtils.isEmpty(scoreEntityUi.getTitleColor())) {
                    str = scoreEntityUi.getTitleColor();
                }
                textView2.setTextColor(Color.parseColor(str));
                this.score.setTextColor(Color.parseColor(!TextUtils.isEmpty(scoreEntityUi.getScoreColor()) ? scoreEntityUi.getScoreColor() : "#B32D00"));
                this.scoreText.setTextColor(Color.parseColor(TextUtils.isEmpty(scoreEntityUi.getTextColor()) ? "#B32D00" : scoreEntityUi.getTextColor()));
                return;
            }
            this.scoreTitle.setVisibility(8);
            this.score.setVisibility(8);
            this.scoreText.setVisibility(8);
            return;
        }
        this.score.setVisibility(8);
        this.scoreText.setVisibility(8);
        this.scoreTitle.setVisibility(8);
    }

    public void bindTitle() {
        if (this.title == null || this.subTitle == null) {
            return;
        }
        if (this.radarFloor.getRadarEntity().getTitleEntity() != null && this.radarFloor.getVisualData().titleEntityUi != null) {
            TitleEntity titleEntity = this.radarFloor.getRadarEntity().getTitleEntity();
            TitleEntityUi titleEntityUi = this.radarFloor.getVisualData().titleEntityUi;
            if (!TextUtils.isEmpty(titleEntity.getMainTitle()) && !TextUtils.isEmpty(titleEntity.getSubTitle())) {
                if (PentagramViewUtils.isDarkConfig()) {
                    this.title.setTextColor(Color.parseColor(TextUtils.isEmpty(titleEntityUi.getTitleDarkColor()) ? "#FFCA80" : titleEntityUi.getTitleDarkColor()));
                    this.subTitle.setTextColor(Color.parseColor(TextUtils.isEmpty(titleEntityUi.getSubTitleDarkColor()) ? "#8C4600" : titleEntityUi.getSubTitleDarkColor()));
                } else {
                    this.title.setTextColor(Color.parseColor(TextUtils.isEmpty(titleEntityUi.getTitleColor()) ? "#FFCA80" : titleEntityUi.getTitleColor()));
                    this.subTitle.setTextColor(Color.parseColor(TextUtils.isEmpty(titleEntityUi.getSubTitleColor()) ? "#8C4600" : titleEntityUi.getSubTitleColor()));
                }
                this.title.setTextSize(10.0f);
                FontsUtil.changeTextFont(this.title, 4099);
                this.title.setText(titleEntity.getMainTitle());
                this.title.setVisibility(0);
                this.subTitle.setTextSize(10.0f);
                FontsUtil.changeTextFont(this.subTitle, 4099);
                this.subTitle.setText(titleEntity.getSubTitle());
                this.subTitle.setVisibility(0);
                return;
            }
            this.title.setVisibility(8);
            this.subTitle.setVisibility(8);
            return;
        }
        this.title.setVisibility(8);
        this.subTitle.setVisibility(8);
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.pentagram.view.BaseView
    protected void initView() {
        this.root = (RelativeLayout) findViewById(R.id.lib_so_pentagram_wrapper);
        this.dataWrapper = (RelativeLayout) findViewById(R.id.lib_so_pentagram_data_wrapper);
        this.title = (TextView) findViewById(R.id.lib_so_pentagram_main_title);
        this.subTitle = (TextView) findViewById(R.id.lib_so_pentagram_main_text);
        this.scoreTitle = (TextView) findViewById(R.id.lib_so_pentagram_score_title);
        this.score = (TextView) findViewById(R.id.lib_so_pentagram_score);
        this.scoreText = (TextView) findViewById(R.id.lib_so_pentagram_score_text);
        this.productList = (RecyclerView) findViewById(R.id.lib_so_color_name_wrapper);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1) { // from class: com.jingdong.app.mall.bundle.jd_component.pentagram.view.PentagramView.1
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        };
        this.layoutManager = gridLayoutManager;
        this.productList.setLayoutManager(gridLayoutManager);
        ProductAdapter productAdapter = new ProductAdapter(R.layout.lib_so_product_view);
        this.mAdapter = productAdapter;
        productAdapter.setListener(this.listener);
        this.productList.setAdapter(this.mAdapter);
        this.compareWrapper = (LinearLayout) findViewById(R.id.lib_so_pentagram_compare_wrapper);
        this.compareText = (TextView) findViewById(R.id.lib_so_pentagram_compare);
        this.noCompareText = (TextView) findViewById(R.id.lib_so_pentagram_no_compare);
        this.radarView = (RadarView) findViewById(R.id.lib_so_pentagram_radar_view);
        this.insufficientView = (TextView) findViewById(R.id.lib_so_pentagram_data_null);
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.pentagram.view.BaseView
    protected int layoutId() {
        return R.layout.lib_so_product_pentagram_view;
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.pentagram.view.BaseView
    public void reset(Object obj) {
    }

    public void setBg(final String str, final int i2) {
        if (!TextUtils.isEmpty(str)) {
            JDImageUtils.loadImage(str, new JDSimpleImageLoadingListener() { // from class: com.jingdong.app.mall.bundle.jd_component.pentagram.view.PentagramView.2
                @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                    if (TextUtils.equals(str, str2)) {
                        PentagramView.this.root.setBackground(new BitmapDrawable(bitmap));
                    } else {
                        PentagramView.this.root.setBackgroundResource(i2);
                    }
                }

                @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                    PentagramView.this.root.setBackgroundResource(i2);
                }
            });
        } else {
            this.root.setBackgroundResource(i2);
        }
    }

    public void setListener(Listener listener) {
        this.listener = listener;
        ProductAdapter productAdapter = this.mAdapter;
        if (productAdapter != null) {
            productAdapter.setListener(listener);
        }
    }

    public PentagramView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.pentagram.view.BaseView
    public void bind(RadarFloor radarFloor) {
        this.radarFloor = radarFloor;
        RadarEntity radarEntity = radarFloor.getRadarEntity();
        if (radarEntity.valid()) {
            RelativeLayout relativeLayout = this.root;
            if (relativeLayout != null && relativeLayout.getLayoutParams() != null) {
                ViewGroup.LayoutParams layoutParams = this.root.getLayoutParams();
                layoutParams.height = DPIUtil.dip2px(205.0f);
                this.root.setLayoutParams(layoutParams);
            }
            this.insufficientView.setVisibility(8);
            this.dataWrapper.setVisibility(0);
            bindBg();
            bindTitle();
            bindScore();
            bindNameWrapper();
            bindCompareData();
            bindRadarView();
        } else if (radarEntity.showPlaceHolder()) {
            bindNullData();
        }
    }

    public PentagramView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
