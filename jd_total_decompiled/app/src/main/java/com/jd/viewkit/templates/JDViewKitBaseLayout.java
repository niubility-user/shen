package com.jd.viewkit.templates;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.global.GlobalManage;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.helper.JDViewKitFloorModel;
import com.jd.viewkit.helper.JDViewKitViewFilterInterface;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.view.helper.JDViewKitRoundHelper;
import com.jd.viewkit.thirdinterface.model.JDViewKitImageModel;
import com.jd.viewkit.tool.ExpressionParserTool;
import com.jd.viewkit.tool.StringTool;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes18.dex */
public class JDViewKitBaseLayout<T extends JDViewKitVirtualView> extends FrameLayout implements JDViewKitViewFilterInterface {
    private static final String TAG = "JDViewKitBaseLayout";
    public static String offsetKey = "JDViewKitOffsetKey";
    public static String positionKey = "JDViewKitPositionKey";
    public static Set<String> typeSet = new HashSet<String>() { // from class: com.jd.viewkit.templates.JDViewKitBaseLayout.1
        {
            add(JDViewKitVirtualView.viewTypeBanner);
            add(JDViewKitVirtualView.viewTypeDynamicBanner);
            add(JDViewKitVirtualView.viewTypeView);
            add(JDViewKitVirtualView.viewTypeFlatView);
            add(JDViewKitVirtualView.viewTypeScroll);
            add(JDViewKitVirtualView.viewTypeStateful);
            add(JDViewKitVirtualView.viewTypeMultiTab);
            add(JDViewKitVirtualView.viewTypeMultiPlusTab);
            add(JDViewKitVirtualView.viewTypeAnchorNav);
            add(JDViewKitVirtualView.viewTypeBottomNav);
            add(JDViewKitVirtualView.viewTypeTopNav);
        }
    };
    protected ImageView bgImageView;
    protected JDViewKitChannelModel channelModel;
    protected Map<String, Object> dataSourceMap;
    protected List<JDViewKitDataSourceModel> dataSourceModels;
    protected Map<String, JDViewKitVirtualView> dslsMap;
    protected JDViewKitFloorModel floorModel;
    protected boolean isFilter;
    protected Context mContext;
    private Handler mainHandler;
    private JDViewKitRoundHelper roundHelper;
    protected T virtualView;

    public JDViewKitBaseLayout(@NonNull Context context) {
        this(context, null, 0);
    }

    public static boolean getFilterValue(String str, Map<String, Object> map) {
        return ExpressionParserTool.getIntValueRef(str, map) == 1;
    }

    private boolean hasThird(JDViewKitVirtualView jDViewKitVirtualView, Context context) {
        if (jDViewKitVirtualView != null && jDViewKitVirtualView.getServiceModel() != null && jDViewKitVirtualView.getServiceModel().getImageService() != null) {
            try {
                jDViewKitVirtualView.getServiceModel().getImageService().getClass().getMethod("getThirdImageView", Context.class);
            } catch (Throwable th) {
                th.printStackTrace();
                return false;
            }
        }
        return true;
    }

    private boolean isUpdateFloorModel(JDViewKitFloorModel jDViewKitFloorModel) {
        if (jDViewKitFloorModel == null || this.floorModel != jDViewKitFloorModel) {
            return true;
        }
        boolean isForceUpdate = jDViewKitFloorModel.isForceUpdate();
        if (isForceUpdate) {
            jDViewKitFloorModel.setForceUpdate(false);
            return isForceUpdate;
        }
        return isForceUpdate;
    }

    private boolean verifyFloorModel(JDViewKitFloorModel jDViewKitFloorModel) {
        if (jDViewKitFloorModel == null || jDViewKitFloorModel.getDataSourceModelList() == null) {
            return false;
        }
        return jDViewKitFloorModel.getDataSourceModelList() == null || jDViewKitFloorModel.getDataSourceModelList().size() != 0;
    }

    public static boolean viewIsFilter(View view) {
        if (view == null || !(view instanceof JDViewKitViewFilterInterface)) {
            return false;
        }
        return ((JDViewKitViewFilterInterface) view).isViewFilter();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        T t = this.virtualView;
        if (t != null && t.getBorderWidth() > 0 && StringTool.isNotEmpty(this.virtualView.getBorderColor())) {
            canvas.save();
            super.dispatchDraw(canvas);
            if (this.roundHelper == null) {
                this.roundHelper = new JDViewKitRoundHelper();
            }
            this.roundHelper.setRound(this.virtualView.getTopLeftRadius(), this.virtualView.getTopRightRadius(), this.virtualView.getBottomRightRadius(), this.virtualView.getBottomLeftRadius());
            this.roundHelper.setRect(new RectF(0.0f, 0.0f, getWidth(), getHeight()));
            this.roundHelper.setStrokeColor(this.virtualView.getBorderColorInt());
            this.roundHelper.setStrokeWidth(GlobalManage.getInstance().getRealPx(this.virtualView.getBorderWidth(), getChannelModel()));
            this.roundHelper.darwStroke(canvas);
            canvas.restore();
            return;
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        T t = this.virtualView;
        if (t != null && t.hasRadius()) {
            if (this.roundHelper == null) {
                this.roundHelper = new JDViewKitRoundHelper();
            }
            this.roundHelper.setRound(this.virtualView.getTopLeftRadius(), this.virtualView.getTopRightRadius(), this.virtualView.getBottomRightRadius(), this.virtualView.getBottomLeftRadius());
            this.roundHelper.setRect(new RectF(0.0f, 0.0f, getWidth(), getHeight()));
            canvas.clipPath(this.roundHelper.getClipPathPath());
            super.draw(canvas);
            return;
        }
        super.draw(canvas);
    }

    public ImageView getBgImageView() {
        return this.bgImageView;
    }

    public JDViewKitChannelModel getChannelModel() {
        return this.channelModel;
    }

    public Map<String, Object> getDataSourceMap() {
        return this.dataSourceMap;
    }

    public List<JDViewKitDataSourceModel> getDataSourceModels() {
        return this.dataSourceModels;
    }

    public Map<String, JDViewKitVirtualView> getDslsMap() {
        return this.dslsMap;
    }

    public int getHeighRealPx() {
        if (this.virtualView != null) {
            return GlobalManage.getInstance().getRealPx(this.virtualView.getHeigh(), getChannelModel());
        }
        return 0;
    }

    public int[] getOffset() {
        Map<String, Object> map = this.dataSourceMap;
        if (map != null) {
            Object obj = map.get(offsetKey);
            Object obj2 = this.dataSourceMap.get(positionKey);
            if (obj == null || !(obj instanceof Integer) || obj2 == null || !(obj2 instanceof Integer)) {
                return null;
            }
            return new int[]{((Integer) obj).intValue(), ((Integer) obj2).intValue()};
        }
        return null;
    }

    public int getViewWidth() {
        if (getChannelModel() != null) {
            return getChannelModel().getViewWidth();
        }
        return 0;
    }

    public T getVirtualView() {
        return this.virtualView;
    }

    public int getWidthRealPx() {
        if (this.virtualView != null) {
            return GlobalManage.getInstance().getRealPx(this.virtualView.getWidth(), getChannelModel());
        }
        return 0;
    }

    public void isShow(boolean z) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (z) {
            setVisibility(0);
            layoutParams.height = GlobalManage.getInstance().getRealPx(this.virtualView.getHeigh(), getChannelModel());
        } else {
            setVisibility(8);
            layoutParams.height = 0;
        }
        requestLayout();
    }

    @Override // com.jd.viewkit.helper.JDViewKitViewFilterInterface
    public boolean isViewFilter() {
        return this.isFilter;
    }

    public void setBgColorAndBorder(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            if (StringTool.isNotEmpty(this.virtualView.getBackgroundColor())) {
                GradientDrawable gradientDrawable = new GradientDrawable();
                if (StringTool.isNotEmpty(this.virtualView.getBackgroundColor())) {
                    gradientDrawable.setColor(this.virtualView.getBackgroundColorInt());
                }
                view.setBackground(gradientDrawable);
            }
        } else if (StringTool.isNotEmpty(this.virtualView.getBackgroundColor())) {
            view.setBackgroundColor(this.virtualView.getBackgroundColorInt());
        }
    }

    public void setBgImageView(ImageView imageView) {
        this.bgImageView = imageView;
    }

    public void setChannelModel(JDViewKitChannelModel jDViewKitChannelModel) {
        this.channelModel = jDViewKitChannelModel;
    }

    public void setDataSourceMap(Map<String, Object> map, boolean z) {
        T t;
        this.dataSourceMap = map;
        T t2 = this.virtualView;
        if (t2 != null && map != null) {
            boolean filterValue = getFilterValue(t2.isFilter(), map);
            this.isFilter = filterValue;
            if (filterValue) {
                return;
            }
        }
        if (z) {
            updateLayout();
        }
        if (this.bgImageView == null || (t = this.virtualView) == null || StringTool.isEmpty(t.getBackgroundImage())) {
            return;
        }
        final String urlStringValueRef = ExpressionParserTool.getUrlStringValueRef(this.virtualView.getBackgroundImage(), map);
        if (StringTool.isEmpty(urlStringValueRef)) {
            this.bgImageView.setVisibility(8);
            return;
        }
        this.bgImageView.setVisibility(0);
        this.mainHandler.post(new Runnable() { // from class: com.jd.viewkit.templates.JDViewKitBaseLayout.2
            {
                JDViewKitBaseLayout.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    JDViewKitBaseLayout.this.virtualView.getServiceModel().getImageService().fillImageView(JDViewKitBaseLayout.this.bgImageView, new JDViewKitImageModel(urlStringValueRef, false, 0, JDViewKitBaseLayout.this.virtualView.getBorderWidth(), JDViewKitBaseLayout.this.virtualView.getBorderColor(), JDViewKitBaseLayout.this.virtualView.getBgScaleType(), ImageView.ScaleType.FIT_CENTER));
                } catch (Throwable unused) {
                }
            }
        });
    }

    public void setDataSourceModels(List<JDViewKitDataSourceModel> list, boolean z) {
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() > 0) {
            for (JDViewKitDataSourceModel jDViewKitDataSourceModel : list) {
                if (getDslsMap().get(jDViewKitDataSourceModel.getDslId()) != null) {
                    arrayList.add(jDViewKitDataSourceModel);
                }
            }
        }
        this.dataSourceModels = arrayList;
        isShow(arrayList.size() > 0);
        isShow(hasThird(this.virtualView, this.mContext));
    }

    public void setDataSourceSByVirtualView(JDViewKitDataSourceModel jDViewKitDataSourceModel, boolean z) {
        if (StringTool.isBegin(this.virtualView.getValueRefer(), "$")) {
            String substring = this.virtualView.getValueRefer().substring(1);
            List<Map<String, Object>> list = null;
            if (jDViewKitDataSourceModel.getDataMap() != null && jDViewKitDataSourceModel.getDataMap().get(substring) != null) {
                list = (List) jDViewKitDataSourceModel.getDataMap().get(substring);
            }
            ArrayList arrayList = new ArrayList();
            if (list != null) {
                for (Map<String, Object> map : list) {
                    JDViewKitDataSourceModel jDViewKitDataSourceModel2 = new JDViewKitDataSourceModel(map);
                    jDViewKitDataSourceModel2.setViewListener(jDViewKitDataSourceModel.getViewListener());
                    jDViewKitDataSourceModel2.setFloorAcrossListener(jDViewKitDataSourceModel.getFloorAcrossListener());
                    jDViewKitDataSourceModel2.setDataMap(map);
                    jDViewKitDataSourceModel2.setTimeStame(jDViewKitDataSourceModel.timeStame);
                    jDViewKitDataSourceModel2.setParamsModel(jDViewKitDataSourceModel.getParamsModel());
                    arrayList.add(jDViewKitDataSourceModel2);
                }
            }
            setDataSourceMap(jDViewKitDataSourceModel.getDataMap(), z);
            setDataSourceModels(arrayList, z);
        }
    }

    public void setDslsMap(Map<String, JDViewKitVirtualView> map) {
        this.dslsMap = map;
    }

    public void setFloorModel(JDViewKitFloorModel jDViewKitFloorModel) {
        if (jDViewKitFloorModel == null) {
            return;
        }
        if (!verifyFloorModel(jDViewKitFloorModel)) {
            isShow(false);
        } else if (isUpdateFloorModel(jDViewKitFloorModel)) {
            this.floorModel = jDViewKitFloorModel;
            setDataSourceMap(jDViewKitFloorModel.getDataSourceMap(), false);
            setDataSourceModels(jDViewKitFloorModel.getDataSourceModelList(), false);
            if (getChannelModel() != null) {
                getChannelModel().setJdViewKitFloorModel(this.floorModel);
            }
            if (jDViewKitFloorModel.refreshType <= 0 || jDViewKitFloorModel.getFloorAcrossListener() == null) {
                return;
            }
            jDViewKitFloorModel.getFloorAcrossListener().updateView(this, jDViewKitFloorModel.refreshType);
        }
    }

    public void setFloorModelByLayout(JDViewKitFloorModel jDViewKitFloorModel) {
        if (!verifyFloorModel(jDViewKitFloorModel)) {
            isShow(false);
            return;
        }
        setDataSourceMap(jDViewKitFloorModel.getDataSourceMap(), true);
        setDataSourceModels(jDViewKitFloorModel.getDataSourceModelList(), true);
        this.floorModel = jDViewKitFloorModel;
    }

    public void setOffset(int i2, int i3) {
        Map<String, Object> map = this.dataSourceMap;
        if (map != null) {
            map.put(offsetKey, Integer.valueOf(i2));
            this.dataSourceMap.put(positionKey, Integer.valueOf(i3));
        }
    }

    public void setViewWidth(int i2) {
        if (getChannelModel() != null) {
            if (getVirtualView() != null) {
                getChannelModel().setRootWidth(getVirtualView().getWidth());
            }
            getChannelModel().setViewWidth(i2);
        }
    }

    public void setVirtualView(T t) {
        this.virtualView = t;
        setDslsMap(t.getDslMap());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(GlobalManage.getInstance().getRealPx(t.getWidth(), getChannelModel()), GlobalManage.getInstance().getRealPx(t.getHeigh(), getChannelModel()));
        layoutParams.topMargin = GlobalManage.getInstance().getRealPx(t.getOrgY(), getChannelModel());
        layoutParams.leftMargin = GlobalManage.getInstance().getRealPx(t.getOrgX(), getChannelModel());
        setLayoutParams(layoutParams);
        setBgColorAndBorder(this);
        if (t.getBackgroundImage() != null && this.bgImageView == null) {
            ImageView bgImageView = getBgImageView(t, this.mContext);
            this.bgImageView = bgImageView;
            if (bgImageView != null) {
                addView(bgImageView, 0);
            }
        }
        postInvalidate();
    }

    public void updateLayout() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.width = GlobalManage.getInstance().getRealPx(this.virtualView.getWidth(), getChannelModel());
        layoutParams.height = GlobalManage.getInstance().getRealPx(this.virtualView.getHeigh(), getChannelModel());
        if (getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
            marginLayoutParams.topMargin = GlobalManage.getInstance().getRealPx(this.virtualView.getOrgY(), getChannelModel());
            marginLayoutParams.leftMargin = GlobalManage.getInstance().getRealPx(this.virtualView.getOrgX(), getChannelModel());
        }
        requestLayout();
        ImageView imageView = this.bgImageView;
        if (imageView == null) {
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
        marginLayoutParams2.width = GlobalManage.getInstance().getRealPx(this.virtualView.getWidth(), getChannelModel());
        marginLayoutParams2.height = GlobalManage.getInstance().getRealPx(this.virtualView.getHeigh(), getChannelModel());
        marginLayoutParams2.topMargin = 0;
        marginLayoutParams2.leftMargin = 0;
        this.bgImageView.requestLayout();
    }

    public JDViewKitBaseLayout(@NonNull Context context, @NonNull JDViewKitChannelModel jDViewKitChannelModel) {
        this(context);
        this.channelModel = jDViewKitChannelModel;
    }

    protected ImageView getBgImageView(JDViewKitVirtualView jDViewKitVirtualView, Context context) {
        ImageView thirdImageView;
        if (jDViewKitVirtualView != null) {
            try {
                if (jDViewKitVirtualView.getServiceModel() == null || jDViewKitVirtualView.getServiceModel().getImageService() == null || (thirdImageView = jDViewKitVirtualView.getServiceModel().getImageService().getThirdImageView(context)) == null) {
                    return null;
                }
                thirdImageView.setScaleType(jDViewKitVirtualView.getBgScaleType());
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(GlobalManage.getInstance().getRealPx(jDViewKitVirtualView.getWidth(), getChannelModel()), GlobalManage.getInstance().getRealPx(jDViewKitVirtualView.getHeigh(), getChannelModel()));
                layoutParams.topMargin = 0;
                layoutParams.leftMargin = 0;
                thirdImageView.setLayoutParams(layoutParams);
                return thirdImageView;
            } catch (Throwable unused) {
                isShow(false);
            }
        }
        return null;
    }

    public JDViewKitBaseLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public JDViewKitBaseLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2) {
        super(context, attributeSet, i2);
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.isFilter = false;
        this.mContext = context;
    }
}
