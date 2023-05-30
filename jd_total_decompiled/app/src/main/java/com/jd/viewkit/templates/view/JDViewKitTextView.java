package com.jd.viewkit.templates.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import com.jd.dynamic.DYConstants;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.global.GlobalManage;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.helper.JDViewKitViewFilterInterface;
import com.jd.viewkit.templates.JDViewKitBaseLayout;
import com.jd.viewkit.templates.model.JDViewKitVirtualTextView;
import com.jd.viewkit.templates.model.event.JDViewKitVirtualEvent;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.jd.viewkit.templates.view.helper.JDViewKitRoundHelper;
import com.jd.viewkit.tool.ColorTool;
import com.jd.viewkit.tool.ExpressionParserTool;
import com.jd.viewkit.tool.StringTool;

/* loaded from: classes18.dex */
public class JDViewKitTextView extends AppCompatTextView implements View.OnClickListener, JDViewKitViewFilterInterface {
    private static final String TAG = "JDViewKitTextView";
    protected JDViewKitChannelModel channelModel;
    protected JDViewKitDataSourceModel dataSourceModel;
    protected boolean isFilter;
    protected Context mContext;
    private JDViewKitRoundHelper roundHelper;
    private JDViewKitVirtualTextView virtualTextView;

    public JDViewKitTextView(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override // android.view.View
    protected void dispatchDraw(Canvas canvas) {
        JDViewKitVirtualTextView jDViewKitVirtualTextView = this.virtualTextView;
        if (jDViewKitVirtualTextView != null && jDViewKitVirtualTextView.getBorderWidth() > 0 && StringTool.isNotEmpty(this.virtualTextView.getBorderColor())) {
            String str = "dispatchDraw: " + this;
            canvas.saveLayer(new RectF(0.0f, 0.0f, getWidth(), getHeight()), null, 31);
            super.dispatchDraw(canvas);
            if (this.roundHelper == null) {
                this.roundHelper = new JDViewKitRoundHelper();
            }
            this.roundHelper.setRound(this.virtualTextView.getTopLeftRadius(), this.virtualTextView.getTopRightRadius(), this.virtualTextView.getBottomRightRadius(), this.virtualTextView.getBottomLeftRadius());
            this.roundHelper.setRect(new RectF(0.0f, 0.0f, getWidth(), getHeight()));
            this.roundHelper.setStrokeColor(this.virtualTextView.getBorderColorInt());
            this.roundHelper.setStrokeWidth(GlobalManage.getInstance().getRealPx(this.virtualTextView.getBorderWidth(), getChannelModel()));
            this.roundHelper.darwStroke(canvas);
            canvas.restore();
            return;
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        JDViewKitVirtualTextView jDViewKitVirtualTextView = this.virtualTextView;
        if (jDViewKitVirtualTextView != null && jDViewKitVirtualTextView.hasRadius()) {
            String str = "draw: " + this;
            canvas.save();
            if (this.roundHelper == null) {
                this.roundHelper = new JDViewKitRoundHelper();
            }
            this.roundHelper.setRound(this.virtualTextView.getTopLeftRadius(), this.virtualTextView.getTopRightRadius(), this.virtualTextView.getBottomRightRadius(), this.virtualTextView.getBottomLeftRadius());
            this.roundHelper.setRect(new RectF(0.0f, 0.0f, getWidth(), getHeight()));
            canvas.clipPath(this.roundHelper.getClipPathPath());
            super.draw(canvas);
            canvas.restore();
            return;
        }
        super.draw(canvas);
    }

    public JDViewKitChannelModel getChannelModel() {
        return this.channelModel;
    }

    public JDViewKitDataSourceModel getDataSourceModel() {
        return this.dataSourceModel;
    }

    public float getStringHeight(String str, JDViewKitVirtualTextView jDViewKitVirtualTextView) {
        if (StringTool.isEmpty(str) || jDViewKitVirtualTextView == null) {
            return 0.0f;
        }
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(GlobalManage.getInstance().getRealPx(jDViewKitVirtualTextView.getFontSize(), getChannelModel()));
        if (this.virtualTextView.getBold() == 1) {
            textPaint.setFakeBoldText(true);
        }
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        return fontMetrics.descent - fontMetrics.ascent;
    }

    public float getStringWidth(String str, JDViewKitVirtualTextView jDViewKitVirtualTextView) {
        if (StringTool.isEmpty(str) || jDViewKitVirtualTextView == null) {
            return 0.0f;
        }
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(GlobalManage.getInstance().getRealPx(jDViewKitVirtualTextView.getFontSize(), getChannelModel()));
        if (this.virtualTextView.getBold() == 1) {
            textPaint.setFakeBoldText(true);
        }
        return textPaint.measureText(str);
    }

    @Override // com.jd.viewkit.helper.JDViewKitViewFilterInterface
    public boolean isViewFilter() {
        return this.isFilter;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        JDViewKitEventHelper.click(this.virtualTextView, this.dataSourceModel, this, getChannelModel());
    }

    public void setChannelModel(JDViewKitChannelModel jDViewKitChannelModel) {
        this.channelModel = jDViewKitChannelModel;
    }

    public void setDataSourceModel(JDViewKitDataSourceModel jDViewKitDataSourceModel, boolean z) {
        this.dataSourceModel = jDViewKitDataSourceModel;
        if (this.virtualTextView != null && jDViewKitDataSourceModel != null && jDViewKitDataSourceModel.getDataMap() != null) {
            boolean filterValue = JDViewKitBaseLayout.getFilterValue(this.virtualTextView.isFilter(), jDViewKitDataSourceModel.getDataMap());
            this.isFilter = filterValue;
            if (filterValue) {
                return;
            }
        }
        if (z) {
            updateLayout();
        }
        if (jDViewKitDataSourceModel == null) {
            setText("");
            setVisibility(8);
            return;
        }
        String stringValueRef = ExpressionParserTool.getStringValueRef(this.virtualTextView.getValueRefer(), jDViewKitDataSourceModel.getDataMap());
        if (StringTool.isEmpty(stringValueRef)) {
            setText("");
            setVisibility(8);
            return;
        }
        setVisibility(0);
        String textDecoration = this.virtualTextView.getTextDecoration();
        if (StringTool.isNotEmpty(textDecoration)) {
            if (textDecoration.toLowerCase().equals("linethrough")) {
                SpannableString spannableString = new SpannableString(stringValueRef);
                spannableString.setSpan(new StrikethroughSpan(), 0, stringValueRef.length(), 17);
                setText(spannableString);
                return;
            } else if (textDecoration.toLowerCase().equals("underline")) {
                SpannableString spannableString2 = new SpannableString(stringValueRef);
                spannableString2.setSpan(new UnderlineSpan(), 0, stringValueRef.length(), 17);
                setText(spannableString2);
                return;
            } else {
                setText(stringValueRef);
                return;
            }
        }
        setText(stringValueRef);
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:76:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void setVirtualTextView(JDViewKitVirtualTextView jDViewKitVirtualTextView) {
        this.virtualTextView = jDViewKitVirtualTextView;
        if (getLayoutParams() == null) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(GlobalManage.getInstance().getRealPx(jDViewKitVirtualTextView.getWidth(), getChannelModel()), GlobalManage.getInstance().getRealPx(jDViewKitVirtualTextView.getHeigh(), getChannelModel()));
            layoutParams.topMargin = GlobalManage.getInstance().getRealPx(jDViewKitVirtualTextView.getOrgY(), getChannelModel());
            layoutParams.leftMargin = GlobalManage.getInstance().getRealPx(jDViewKitVirtualTextView.getOrgX(), getChannelModel());
            setLayoutParams(layoutParams);
        } else {
            updateLayout();
        }
        if (jDViewKitVirtualTextView.getFontSize() > 0) {
            setTextSize(0, GlobalManage.getInstance().getRealPx(jDViewKitVirtualTextView.getFontSize(), getChannelModel()));
        }
        if (!StringTool.isEmpty(jDViewKitVirtualTextView.getColor())) {
            setTextColor(ColorTool.parseColor(jDViewKitVirtualTextView.getColor(), -16777216));
        }
        if (jDViewKitVirtualTextView.getLines() > 0) {
            setLines(jDViewKitVirtualTextView.getLines());
            setMaxLines(jDViewKitVirtualTextView.getLines());
        }
        setEllipsize(TextUtils.TruncateAt.END);
        int i2 = 1;
        if (jDViewKitVirtualTextView.getBold() == 1) {
            getPaint().setFakeBoldText(true);
        }
        if (jDViewKitVirtualTextView.getBold() == 1 && jDViewKitVirtualTextView.getItalic() == 1) {
            setTypeface(null, 3);
        } else if (jDViewKitVirtualTextView.getBold() == 1) {
            setTypeface(null, 1);
        } else if (jDViewKitVirtualTextView.getItalic() == 1) {
            setTypeface(null, 2);
        }
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 17 && jDViewKitVirtualTextView.getTextAlign() != null && !jDViewKitVirtualTextView.getTextAlign().equals("left")) {
            if (!jDViewKitVirtualTextView.getTextAlign().equals(DYConstants.DY_CENTER)) {
                if (jDViewKitVirtualTextView.getTextAlign().equals("right")) {
                    i2 = 5;
                }
            }
            int i4 = 48;
            if (jDViewKitVirtualTextView.getVerticalAlign() != null && !jDViewKitVirtualTextView.getVerticalAlign().equals("top")) {
                if (!jDViewKitVirtualTextView.getVerticalAlign().equals(DYConstants.DY_CENTER)) {
                    i4 = 16;
                } else if (jDViewKitVirtualTextView.getVerticalAlign().equals("bottom")) {
                    i4 = 80;
                }
            }
            setGravity(i2 | i4);
            if (i3 < 16) {
                if (StringTool.isNotEmpty(jDViewKitVirtualTextView.getBackgroundColor()) || (StringTool.isNotEmpty(jDViewKitVirtualTextView.getBorderColor()) && jDViewKitVirtualTextView.getBorderWidth() != 0)) {
                    GradientDrawable gradientDrawable = new GradientDrawable();
                    if (StringTool.isNotEmpty(jDViewKitVirtualTextView.getBackgroundColor())) {
                        gradientDrawable.setColor(jDViewKitVirtualTextView.getBackgroundColorInt());
                    }
                    setBackground(gradientDrawable);
                }
            } else if (StringTool.isNotEmpty(jDViewKitVirtualTextView.getBackgroundColor())) {
                setBackgroundColor(jDViewKitVirtualTextView.getBackgroundColorInt());
            }
            if (jDViewKitVirtualTextView.getVirtualEventByType(JDViewKitVirtualEvent.typeClick) != null) {
                setOnClickListener(this);
            }
            if (StringTool.isEmpty(jDViewKitVirtualTextView.getHidden())) {
                setVisibility(8);
                return;
            }
            return;
        }
        i2 = 3;
        int i42 = 48;
        if (jDViewKitVirtualTextView.getVerticalAlign() != null) {
            if (!jDViewKitVirtualTextView.getVerticalAlign().equals(DYConstants.DY_CENTER)) {
            }
        }
        setGravity(i2 | i42);
        if (i3 < 16) {
        }
        if (jDViewKitVirtualTextView.getVirtualEventByType(JDViewKitVirtualEvent.typeClick) != null) {
        }
        if (StringTool.isEmpty(jDViewKitVirtualTextView.getHidden())) {
        }
    }

    public void updateLayout() {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        marginLayoutParams.width = GlobalManage.getInstance().getRealPx(this.virtualTextView.getWidth(), getChannelModel());
        marginLayoutParams.height = GlobalManage.getInstance().getRealPx(this.virtualTextView.getHeigh(), getChannelModel());
        marginLayoutParams.topMargin = GlobalManage.getInstance().getRealPx(this.virtualTextView.getOrgY(), getChannelModel());
        marginLayoutParams.leftMargin = GlobalManage.getInstance().getRealPx(this.virtualTextView.getOrgX(), getChannelModel());
        if (this.virtualTextView.getFontSize() > 0) {
            setTextSize(0, GlobalManage.getInstance().getRealPx(this.virtualTextView.getFontSize(), getChannelModel()));
        }
        requestLayout();
    }

    public JDViewKitTextView(Context context, JDViewKitChannelModel jDViewKitChannelModel) {
        super(context);
        this.mContext = context;
        this.channelModel = jDViewKitChannelModel;
    }

    public JDViewKitTextView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public JDViewKitTextView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
