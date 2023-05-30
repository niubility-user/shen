package com.jd.viewkit.templates.view;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.global.GlobalManage;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.templates.JDViewKitBaseLayout;
import com.jd.viewkit.templates.model.JDViewKitVirtualImageView;
import com.jd.viewkit.templates.model.event.JDViewKitVirtualEvent;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.jd.viewkit.tool.ExpressionParserTool;
import com.jd.viewkit.tool.StringTool;

/* loaded from: classes18.dex */
public class JDViewKitImageView extends JDViewKitBaseLayout implements View.OnClickListener {
    private static final String TAG = "JDViewKitImageView";
    private JDViewKitDataSourceModel dataSourceModel;
    private int heigh;
    private Context mContext;
    private ImageView mImageView;
    private Handler mainHandler;
    private JDViewKitVirtualImageView virtualImageView;
    private int width;

    public JDViewKitImageView(Context context) {
        super(context);
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.mContext = context;
        setWillNotDraw(false);
    }

    private void adaptiveWidthHeigh() {
        int i2;
        int i3;
        int adaptiveType = this.virtualImageView.getAdaptiveType();
        if (adaptiveType > 0) {
            this.width = this.virtualImageView.getWidth();
            this.heigh = this.virtualImageView.getHeigh();
            String adaptiveWidthRefer = this.virtualImageView.getAdaptiveWidthRefer();
            String adaptiveHeightRefer = this.virtualImageView.getAdaptiveHeightRefer();
            if (StringTool.isNotEmpty(adaptiveWidthRefer) && StringTool.isNotEmpty(adaptiveHeightRefer)) {
                int intValueRef = ExpressionParserTool.getIntValueRef(adaptiveWidthRefer, this.dataSourceModel.getDataMap());
                int intValueRef2 = ExpressionParserTool.getIntValueRef(adaptiveHeightRefer, this.dataSourceModel.getDataMap());
                if (intValueRef > 0 && intValueRef > 0) {
                    if (adaptiveType == 1) {
                        double d = this.width;
                        Double.isNaN(d);
                        double d2 = intValueRef;
                        Double.isNaN(d2);
                        double d3 = (d * 1.0d) / d2;
                        double d4 = intValueRef2;
                        Double.isNaN(d4);
                        double d5 = d3 * d4;
                        if (d5 < this.heigh) {
                            this.heigh = (int) d5;
                        }
                    } else if (adaptiveType == 2) {
                        double d6 = this.heigh;
                        Double.isNaN(d6);
                        double d7 = intValueRef2;
                        Double.isNaN(d7);
                        double d8 = intValueRef;
                        Double.isNaN(d8);
                        double d9 = ((d6 * 1.0d) / d7) * d8;
                        if (d9 < this.width) {
                            this.width = (int) d9;
                        }
                    } else if (adaptiveType == 3 && (i2 = this.width) > 0 && (i3 = this.heigh) > 0) {
                        if (intValueRef <= i2 && intValueRef2 <= i3) {
                            this.width = intValueRef;
                            this.heigh = intValueRef2;
                        } else if (intValueRef >= i2) {
                            double d10 = i2;
                            Double.isNaN(d10);
                            double d11 = intValueRef;
                            Double.isNaN(d11);
                            double d12 = (d10 * 1.0d) / d11;
                            double d13 = intValueRef2;
                            Double.isNaN(d13);
                            double d14 = d12 * d13;
                            if (d14 < i3) {
                                this.heigh = (int) d14;
                            }
                        } else if (intValueRef2 >= i3) {
                            double d15 = i3;
                            Double.isNaN(d15);
                            double d16 = intValueRef2;
                            Double.isNaN(d16);
                            double d17 = intValueRef;
                            Double.isNaN(d17);
                            double d18 = ((d15 * 1.0d) / d16) * d17;
                            if (d18 < i2) {
                                this.width = (int) d18;
                            }
                        }
                    }
                }
            }
            updateLayout();
        }
    }

    public JDViewKitDataSourceModel getDataSourceModel() {
        return this.dataSourceModel;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        JDViewKitEventHelper.click(this.virtualImageView, this.dataSourceModel, this, getChannelModel());
    }

    public void setDataSourceModel(JDViewKitDataSourceModel jDViewKitDataSourceModel, boolean z) {
        this.dataSourceModel = jDViewKitDataSourceModel;
        if (this.virtualImageView != null && jDViewKitDataSourceModel != null && jDViewKitDataSourceModel.getDataMap() != null) {
            boolean filterValue = JDViewKitBaseLayout.getFilterValue(this.virtualImageView.isFilter(), jDViewKitDataSourceModel.getDataMap());
            this.isFilter = filterValue;
            if (filterValue) {
                return;
            }
        }
        if (this.virtualImageView == null) {
            return;
        }
        adaptiveWidthHeigh();
        if (z) {
            updateLayout();
        }
        String valueRefer = this.virtualImageView.getValueRefer();
        if (jDViewKitDataSourceModel != null && !StringTool.isEmpty(valueRefer) && valueRefer.length() >= 2) {
            ExpressionParserTool.getUrlStringValueRef(this.virtualImageView.getValueRefer(), jDViewKitDataSourceModel.getDataMap());
            this.mainHandler.post(new Runnable
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0059: INVOKE 
                  (wrap: android.os.Handler : 0x0052: IGET (r2v0 'this' com.jd.viewkit.templates.view.JDViewKitImageView A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:10) com.jd.viewkit.templates.view.JDViewKitImageView.mainHandler android.os.Handler)
                  (wrap: java.lang.Runnable : 0x0056: CONSTRUCTOR 
                  (r2v0 'this' com.jd.viewkit.templates.view.JDViewKitImageView A[IMMUTABLE_TYPE, THIS])
                  (r3 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                 A[MD:(com.jd.viewkit.templates.view.JDViewKitImageView, java.lang.String):void (m), WRAPPED] call: com.jd.viewkit.templates.view.JDViewKitImageView.2.<init>(com.jd.viewkit.templates.view.JDViewKitImageView, java.lang.String):void type: CONSTRUCTOR)
                 type: VIRTUAL call: android.os.Handler.post(java.lang.Runnable):boolean A[MD:(java.lang.Runnable):boolean (c)] (LINE:10) in method: com.jd.viewkit.templates.view.JDViewKitImageView.setDataSourceModel(com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel, boolean):void, file: classes18.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                Caused by: java.lang.NullPointerException
                */
            /*
                this = this;
                r2.dataSourceModel = r3
                com.jd.viewkit.templates.model.JDViewKitVirtualImageView r0 = r2.virtualImageView
                if (r0 == 0) goto L21
                if (r3 == 0) goto L21
                java.util.Map r0 = r3.getDataMap()
                if (r0 == 0) goto L21
                com.jd.viewkit.templates.model.JDViewKitVirtualImageView r0 = r2.virtualImageView
                java.lang.String r0 = r0.isFilter()
                java.util.Map r1 = r3.getDataMap()
                boolean r0 = com.jd.viewkit.templates.JDViewKitBaseLayout.getFilterValue(r0, r1)
                r2.isFilter = r0
                if (r0 == 0) goto L21
                return
            L21:
                com.jd.viewkit.templates.model.JDViewKitVirtualImageView r0 = r2.virtualImageView
                if (r0 != 0) goto L26
                return
            L26:
                r2.adaptiveWidthHeigh()
                if (r4 == 0) goto L2e
                r2.updateLayout()
            L2e:
                com.jd.viewkit.templates.model.JDViewKitVirtualImageView r4 = r2.virtualImageView
                java.lang.String r4 = r4.getValueRefer()
                if (r3 == 0) goto L5d
                boolean r0 = com.jd.viewkit.tool.StringTool.isEmpty(r4)
                if (r0 != 0) goto L5d
                int r4 = r4.length()
                r0 = 2
                if (r4 >= r0) goto L44
                goto L5d
            L44:
                com.jd.viewkit.templates.model.JDViewKitVirtualImageView r4 = r2.virtualImageView
                java.lang.String r4 = r4.getValueRefer()
                java.util.Map r3 = r3.getDataMap()
                java.lang.String r3 = com.jd.viewkit.tool.ExpressionParserTool.getUrlStringValueRef(r4, r3)
                android.os.Handler r4 = r2.mainHandler
                com.jd.viewkit.templates.view.JDViewKitImageView$2 r0 = new com.jd.viewkit.templates.view.JDViewKitImageView$2
                r0.<init>()
                r4.post(r0)
                return
            L5d:
                android.os.Handler r3 = r2.mainHandler
                com.jd.viewkit.templates.view.JDViewKitImageView$1 r4 = new com.jd.viewkit.templates.view.JDViewKitImageView$1
                r4.<init>()
                r3.post(r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.viewkit.templates.view.JDViewKitImageView.setDataSourceModel(com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel, boolean):void");
        }

        public void setJDViewKitVirtualImageView(JDViewKitVirtualImageView jDViewKitVirtualImageView) {
            this.virtualView = jDViewKitVirtualImageView;
            this.virtualImageView = jDViewKitVirtualImageView;
            if (jDViewKitVirtualImageView == null) {
                return;
            }
            this.width = jDViewKitVirtualImageView.getWidth();
            this.heigh = jDViewKitVirtualImageView.getHeigh();
            try {
                this.mImageView = this.virtualImageView.getServiceModel().getImageService().getThirdImageView(this.mContext);
            } catch (Throwable unused) {
            }
            if (this.mImageView == null) {
                this.mImageView = new ImageView(this.mContext);
            }
            this.mImageView.setScaleType(jDViewKitVirtualImageView.getScaleType());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(GlobalManage.getInstance().getRealPx(jDViewKitVirtualImageView.getWidth(), getChannelModel()), GlobalManage.getInstance().getRealPx(jDViewKitVirtualImageView.getHeigh(), getChannelModel()));
            layoutParams.topMargin = GlobalManage.getInstance().getRealPx(jDViewKitVirtualImageView.getOrgY(), getChannelModel());
            layoutParams.leftMargin = GlobalManage.getInstance().getRealPx(jDViewKitVirtualImageView.getOrgX(), getChannelModel());
            setLayoutParams(layoutParams);
            if (!StringTool.isEmpty(jDViewKitVirtualImageView.getBackgroundColor())) {
                setBackgroundColor(jDViewKitVirtualImageView.getBackgroundColorInt());
            }
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(GlobalManage.getInstance().getRealPx(jDViewKitVirtualImageView.getWidth(), getChannelModel()), GlobalManage.getInstance().getRealPx(jDViewKitVirtualImageView.getHeigh(), getChannelModel()));
            layoutParams2.topMargin = 0;
            layoutParams2.leftMargin = 0;
            this.mImageView.setLayoutParams(layoutParams2);
            addView(this.mImageView);
            if (Build.VERSION.SDK_INT >= 16) {
                if (StringTool.isNotEmpty(jDViewKitVirtualImageView.getBackgroundColor())) {
                    GradientDrawable gradientDrawable = new GradientDrawable();
                    if (StringTool.isNotEmpty(jDViewKitVirtualImageView.getBackgroundColor())) {
                        gradientDrawable.setColor(jDViewKitVirtualImageView.getBackgroundColorInt());
                    }
                    setBackground(gradientDrawable);
                }
            } else if (StringTool.isNotEmpty(jDViewKitVirtualImageView.getBackgroundColor())) {
                setBackgroundColor(jDViewKitVirtualImageView.getBackgroundColorInt());
            }
            if (jDViewKitVirtualImageView.getVirtualEventByType(JDViewKitVirtualEvent.typeClick) != null) {
                setOnClickListener(this);
            }
            if (StringTool.isEmpty(jDViewKitVirtualImageView.getHidden())) {
                return;
            }
            setVisibility(8);
        }

        @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
        public void updateLayout() {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
            marginLayoutParams.width = GlobalManage.getInstance().getRealPx(this.width, getChannelModel());
            marginLayoutParams.height = GlobalManage.getInstance().getRealPx(this.heigh, getChannelModel());
            marginLayoutParams.topMargin = GlobalManage.getInstance().getRealPx(this.virtualImageView.getOrgY(), getChannelModel());
            marginLayoutParams.leftMargin = GlobalManage.getInstance().getRealPx(this.virtualImageView.getOrgX(), getChannelModel());
            requestLayout();
            ImageView imageView = this.mImageView;
            if (imageView == null) {
                return;
            }
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
            marginLayoutParams2.width = GlobalManage.getInstance().getRealPx(this.width, getChannelModel());
            marginLayoutParams2.height = GlobalManage.getInstance().getRealPx(this.heigh, getChannelModel());
            marginLayoutParams2.topMargin = 0;
            marginLayoutParams2.leftMargin = 0;
            this.mImageView.requestLayout();
        }

        public JDViewKitImageView(Context context, JDViewKitChannelModel jDViewKitChannelModel) {
            this(context);
            this.channelModel = jDViewKitChannelModel;
        }
    }
