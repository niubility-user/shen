package com.jd.viewkit.templates.view.jdviewkitrichtextview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.core.internal.view.SupportMenu;
import com.jd.dynamic.DYConstants;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.global.GlobalManage;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.templates.JDViewKitBaseLayout;
import com.jd.viewkit.templates.model.JDViewKitVirtualTextView;
import com.jd.viewkit.templates.model.jdviewkitvirtualrichtextview.JDViewKitVirtualRichTextImgView;
import com.jd.viewkit.templates.model.jdviewkitvirtualrichtextview.JDViewKitVirtualRichTextTagView;
import com.jd.viewkit.templates.model.jdviewkitvirtualrichtextview.JDViewKitVirtualRichTextView;
import com.jd.viewkit.templates.view.JDViewKitTextView;
import com.jd.viewkit.thirdinterface.JDViewKitImageService;
import com.jd.viewkit.tool.ColorTool;
import com.jd.viewkit.tool.ExpressionParserTool;
import com.jd.viewkit.tool.NumberTool;
import com.jd.viewkit.tool.StringTool;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitRichTextView extends JDViewKitTextView implements View.OnClickListener {
    private Handler mHandler;
    private SpannableString mSpannableString;
    private Map<Integer, Object> spannedMap;
    private List<JDViewKitRichTextSpansModel> spansModelList;
    private JDViewKitVirtualRichTextView virtualRichTextView;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public class JDViewKitRichTextBottomSpan extends SuperscriptSpan {
        JDViewKitRichTextBottomSpan() {
        }

        @Override // android.text.style.SuperscriptSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            textPaint.baselineShift -= 3;
        }

        @Override // android.text.style.SuperscriptSpan, android.text.style.MetricAffectingSpan
        public void updateMeasureState(TextPaint textPaint) {
            textPaint.baselineShift -= 3;
        }
    }

    public JDViewKitRichTextView(@NonNull Context context) {
        super(context);
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public static Bitmap tag2Bitmap() {
        return null;
    }

    public JDViewKitVirtualRichTextView getVirtualRichTextView() {
        return this.virtualRichTextView;
    }

    @Override // com.jd.viewkit.templates.view.JDViewKitTextView
    public void setDataSourceModel(JDViewKitDataSourceModel jDViewKitDataSourceModel, boolean z) {
        Drawable drawable;
        int i2;
        this.dataSourceModel = jDViewKitDataSourceModel;
        if (this.virtualRichTextView != null && jDViewKitDataSourceModel != null && jDViewKitDataSourceModel.getDataMap() != null) {
            boolean filterValue = JDViewKitBaseLayout.getFilterValue(this.virtualRichTextView.isFilter(), this.dataSourceModel.getDataMap());
            this.isFilter = filterValue;
            if (filterValue) {
                return;
            }
        }
        this.spannedMap = new HashMap();
        if (z) {
            updateLayout();
        }
        if (this.dataSourceModel == null) {
            this.mSpannableString = null;
            setText("");
            setVisibility(8);
        } else if (this.virtualRichTextView != null) {
            this.spansModelList = new ArrayList();
            StringBuffer stringBuffer = new StringBuffer("");
            int size = this.virtualRichTextView.getRichTextViewList().size();
            int valueOfInt = NumberTool.valueOfInt(this.virtualRichTextView.getItemSpace());
            for (int i3 = 0; i3 < size; i3++) {
                if (ExpressionParserTool.getIntValueRef(this.virtualRichTextView.getRichTextViewList().get(i3).isFilter(), this.dataSourceModel.getDataMap()) != 1) {
                    JDViewKitRichTextSpansModel jDViewKitRichTextSpansModel = new JDViewKitRichTextSpansModel(this.virtualRichTextView.getRichTextViewList().get(i3), this.dataSourceModel);
                    String text = jDViewKitRichTextSpansModel.getText();
                    if (StringTool.isNotEmpty(text)) {
                        if (!jDViewKitRichTextSpansModel.isTag() && !jDViewKitRichTextSpansModel.isImg()) {
                            stringBuffer.append(text);
                        } else {
                            stringBuffer.append("#");
                        }
                        this.spansModelList.add(jDViewKitRichTextSpansModel);
                        if (valueOfInt > 0 && i3 < size - 1) {
                            stringBuffer.append("#");
                            this.spansModelList.add(new JDViewKitRichTextSpansModel(true));
                        }
                    }
                }
            }
            if (stringBuffer.toString().length() > 0) {
                setVisibility(0);
                this.mSpannableString = new SpannableString(stringBuffer.toString());
                float textSize = super.getTextSize();
                final int i4 = 0;
                for (JDViewKitRichTextSpansModel jDViewKitRichTextSpansModel2 : this.spansModelList) {
                    if (jDViewKitRichTextSpansModel2.isTag()) {
                        Bitmap tagBitmap = jDViewKitRichTextSpansModel2.getTagBitmap();
                        if (tagBitmap != null) {
                            SpannableString spannableString = this.mSpannableString;
                            VerticalImageSpan verticalImageSpan = new VerticalImageSpan(this.mContext, tagBitmap);
                            int i5 = i4 + 1;
                            spannableString.setSpan(verticalImageSpan, i4, i5, 17);
                            i4 = i5;
                        }
                    } else {
                        if (jDViewKitRichTextSpansModel2.isImg()) {
                            final int realPx = GlobalManage.getInstance().getRealPx(jDViewKitRichTextSpansModel2.virtualTextView.getWidth(), getChannelModel());
                            final int realPx2 = GlobalManage.getInstance().getRealPx(jDViewKitRichTextSpansModel2.virtualTextView.getHeigh(), getChannelModel());
                            String text2 = jDViewKitRichTextSpansModel2.getText();
                            if (!TextUtils.isEmpty(text2)) {
                                try {
                                    this.virtualRichTextView.getServiceModel().getImageService().downloadImage(getContext(), text2, realPx, realPx2, new JDViewKitImageService.DownloadImageListener() { // from class: com.jd.viewkit.templates.view.jdviewkitrichtextview.JDViewKitRichTextView.1
                                        @Override // com.jd.viewkit.thirdinterface.JDViewKitImageService.DownloadImageListener
                                        public void onEnd(String str, final Bitmap bitmap) {
                                            if (JDViewKitRichTextView.this.mSpannableString == null || JDViewKitRichTextView.this.spannedMap.get(Integer.valueOf(i4)) == null) {
                                                return;
                                            }
                                            JDViewKitRichTextView.this.mHandler.post(new Runnable() { // from class: com.jd.viewkit.templates.view.jdviewkitrichtextview.JDViewKitRichTextView.1.1
                                                @Override // java.lang.Runnable
                                                public void run() {
                                                    Object obj;
                                                    if (JDViewKitRichTextView.this.mSpannableString == null || bitmap == null || JDViewKitRichTextView.this.spannedMap == null || (obj = JDViewKitRichTextView.this.spannedMap.get(Integer.valueOf(i4))) == null) {
                                                        return;
                                                    }
                                                    JDViewKitRichTextView.this.spannedMap.remove(Integer.valueOf(i4));
                                                    JDViewKitRichTextView.this.mSpannableString.removeSpan(obj);
                                                    SpannableString spannableString2 = JDViewKitRichTextView.this.mSpannableString;
                                                    Context context = JDViewKitRichTextView.this.getContext();
                                                    Bitmap bitmap2 = bitmap;
                                                    AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                                                    VerticalImageSpan verticalImageSpan2 = new VerticalImageSpan(context, bitmap2, realPx, realPx2);
                                                    int i6 = i4;
                                                    spannableString2.setSpan(verticalImageSpan2, i6, i6 + 1, 33);
                                                    JDViewKitRichTextView jDViewKitRichTextView = JDViewKitRichTextView.this;
                                                    jDViewKitRichTextView.setText(jDViewKitRichTextView.mSpannableString);
                                                }
                                            });
                                        }

                                        @Override // com.jd.viewkit.thirdinterface.JDViewKitImageService.DownloadImageListener
                                        public void onError(String str) {
                                        }
                                    });
                                } catch (Throwable unused) {
                                }
                            }
                            try {
                                drawable = this.virtualRichTextView.getServiceModel().getImageService().getPlaceholderDrawable(getContext(), 1);
                            } catch (Throwable unused2) {
                                drawable = null;
                            }
                            if (drawable == null) {
                                drawable = new ColorDrawable(0);
                            }
                            VerticalImageSpan verticalImageSpan2 = new VerticalImageSpan(drawable, realPx, realPx2);
                            this.spannedMap.put(Integer.valueOf(i4), verticalImageSpan2);
                            i2 = i4 + 1;
                            this.mSpannableString.setSpan(verticalImageSpan2, i4, i2, 33);
                        } else if (!jDViewKitRichTextSpansModel2.isSpace()) {
                            String text3 = jDViewKitRichTextSpansModel2.getText();
                            if (StringTool.isNotEmpty(text3)) {
                                i2 = text3.length() + i4;
                                this.mSpannableString.setSpan(jDViewKitRichTextSpansModel2.getRelativeSizeSpan(textSize), i4, i2, 17);
                                this.mSpannableString.setSpan(new JDViewKitRichTextBottomSpan(), i4, i2, 17);
                                if (!StringTool.isEmpty(jDViewKitRichTextSpansModel2.virtualTextView.getColor())) {
                                    this.mSpannableString.setSpan(new ForegroundColorSpan(ColorTool.parseColor(jDViewKitRichTextSpansModel2.virtualTextView.getColor(), -16777216)), i4, i2, 17);
                                }
                                if (jDViewKitRichTextSpansModel2.virtualTextView.getBold() == 1) {
                                    this.mSpannableString.setSpan(new StyleSpan(1), i4, i2, 17);
                                }
                                this.mSpannableString.setSpan(new BackgroundColorSpan(jDViewKitRichTextSpansModel2.getVirtualTextView().getBackgroundColorInt()), i4, i2, 17);
                                String textDecoration = jDViewKitRichTextSpansModel2.getTextDecoration();
                                if (StringTool.isNotEmpty(textDecoration)) {
                                    if (textDecoration.toLowerCase().equals("linethrough")) {
                                        this.mSpannableString.setSpan(new StrikethroughSpan(), i4, i2, 17);
                                    } else if (textDecoration.toLowerCase().equals("underline")) {
                                        this.mSpannableString.setSpan(new UnderlineSpan(), i4, i2, 17);
                                    }
                                }
                            }
                        } else {
                            int i6 = i4 + 1;
                            this.mSpannableString.setSpan(new VerticalImageSpan(new ColorDrawable(0), valueOfInt, 1), i4, i6, 17);
                            i4 = i6;
                        }
                        i4 = i2;
                    }
                }
                setText(this.mSpannableString);
                return;
            }
            this.mSpannableString = null;
            setText("");
            setVisibility(8);
        }
    }

    public void setVirtualRichTextView(JDViewKitVirtualRichTextView jDViewKitVirtualRichTextView) {
        this.virtualRichTextView = jDViewKitVirtualRichTextView;
        super.setVirtualTextView(jDViewKitVirtualRichTextView);
        if (jDViewKitVirtualRichTextView == null || jDViewKitVirtualRichTextView.getHorizontalAlign() == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (jDViewKitVirtualRichTextView.getHorizontalAlign().equals("left")) {
                setTextAlignment(2);
            } else if (jDViewKitVirtualRichTextView.getHorizontalAlign().equals(DYConstants.DY_CENTER)) {
                setTextAlignment(4);
            } else if (jDViewKitVirtualRichTextView.getHorizontalAlign().equals("right")) {
                setTextAlignment(3);
            }
        } else if (jDViewKitVirtualRichTextView.getHorizontalAlign().equals("left")) {
            setGravity((getGravity() & 112) | 3);
        } else if (jDViewKitVirtualRichTextView.getHorizontalAlign().equals(DYConstants.DY_CENTER)) {
            setGravity((getGravity() & 112) | 1);
        } else if (jDViewKitVirtualRichTextView.getHorizontalAlign().equals("right")) {
            setGravity((getGravity() & 112) | 5);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public class JDViewKitRichTextSpansModel {
        public JDViewKitDataSourceModel dataSourceModel;
        public boolean isItemSpace;
        public JDViewKitVirtualTextView virtualTextView;

        public JDViewKitRichTextSpansModel(JDViewKitVirtualTextView jDViewKitVirtualTextView, JDViewKitDataSourceModel jDViewKitDataSourceModel) {
            this.virtualTextView = jDViewKitVirtualTextView;
            this.dataSourceModel = jDViewKitDataSourceModel;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getText() {
            JDViewKitVirtualTextView jDViewKitVirtualTextView = this.virtualTextView;
            if (jDViewKitVirtualTextView != null) {
                if (this.dataSourceModel != null) {
                    return ExpressionParserTool.getStringValueRef(jDViewKitVirtualTextView.getValueRefer(), this.dataSourceModel.getDataMap());
                }
                return ExpressionParserTool.getStringValueRef(jDViewKitVirtualTextView.getValueRefer(), null);
            }
            return null;
        }

        public JDViewKitDataSourceModel getDataSourceModel() {
            return this.dataSourceModel;
        }

        public RelativeSizeSpan getRelativeSizeSpan(float f2) {
            if (isTag() || this.virtualTextView == null || f2 == 0.0f) {
                return null;
            }
            float realPx = GlobalManage.getInstance().getRealPx(this.virtualTextView.getFontSize(), JDViewKitRichTextView.this.getChannelModel());
            if (realPx > 0.0f) {
                return new RelativeSizeSpan(realPx / f2);
            }
            return null;
        }

        public Bitmap getRoundedCornerBitmap(Bitmap bitmap, float[] fArr) {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint();
            Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            RectF rectF = new RectF(rect);
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(SupportMenu.CATEGORY_MASK);
            Path path = new Path();
            path.addRoundRect(rectF, fArr, Path.Direction.CCW);
            canvas.drawPath(path, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
            return createBitmap;
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x00ec  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0107  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0116  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x013f  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x01dd  */
        /* JADX WARN: Removed duplicated region for block: B:46:0x0249  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x027e  */
        /* JADX WARN: Removed duplicated region for block: B:53:0x02ab  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x02de  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public android.graphics.Bitmap getTagBitmap() {
            /*
                Method dump skipped, instructions count: 828
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.viewkit.templates.view.jdviewkitrichtextview.JDViewKitRichTextView.JDViewKitRichTextSpansModel.getTagBitmap():android.graphics.Bitmap");
        }

        public String getTextDecoration() {
            JDViewKitVirtualTextView jDViewKitVirtualTextView = this.virtualTextView;
            if (jDViewKitVirtualTextView == null || !StringTool.isNotEmpty(jDViewKitVirtualTextView.getTextDecoration())) {
                return null;
            }
            return this.virtualTextView.getTextDecoration();
        }

        public JDViewKitVirtualTextView getVirtualTextView() {
            return this.virtualTextView;
        }

        public boolean isImg() {
            JDViewKitVirtualTextView jDViewKitVirtualTextView = this.virtualTextView;
            return jDViewKitVirtualTextView != null && (jDViewKitVirtualTextView instanceof JDViewKitVirtualRichTextImgView);
        }

        public boolean isSpace() {
            return this.virtualTextView == null && this.dataSourceModel == null && this.isItemSpace;
        }

        public boolean isTag() {
            JDViewKitVirtualTextView jDViewKitVirtualTextView = this.virtualTextView;
            return jDViewKitVirtualTextView != null && (jDViewKitVirtualTextView instanceof JDViewKitVirtualRichTextTagView);
        }

        public void setDataSourceModel(JDViewKitDataSourceModel jDViewKitDataSourceModel) {
            this.dataSourceModel = jDViewKitDataSourceModel;
        }

        public void setVirtualTextView(JDViewKitVirtualTextView jDViewKitVirtualTextView) {
            this.virtualTextView = jDViewKitVirtualTextView;
        }

        public JDViewKitRichTextSpansModel(boolean z) {
            this.isItemSpace = z;
        }
    }

    public JDViewKitRichTextView(@NonNull Context context, @NonNull JDViewKitChannelModel jDViewKitChannelModel) {
        super(context, jDViewKitChannelModel);
        this.mHandler = new Handler(Looper.getMainLooper());
    }
}
