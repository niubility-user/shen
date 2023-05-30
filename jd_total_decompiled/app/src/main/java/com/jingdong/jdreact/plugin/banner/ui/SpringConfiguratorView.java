package com.jingdong.jdreact.plugin.banner.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import com.jingdong.jdreact.plugin.banner.OrigamiValueConverter;
import com.jingdong.jdreact.plugin.banner.Spring;
import com.jingdong.jdreact.plugin.banner.SpringConfig;
import com.jingdong.jdreact.plugin.banner.SpringConfigRegistry;
import com.jingdong.jdreact.plugin.banner.SpringListener;
import com.jingdong.jdreact.plugin.banner.SpringSystem;
import com.jingdong.sdk.platform.business.personal.R2;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes13.dex */
public class SpringConfiguratorView extends FrameLayout {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.#");
    private static final float MAX_FRICTION = 50.0f;
    private static final int MAX_SEEKBAR_VAL = 100000;
    private static final float MAX_TENSION = 200.0f;
    private static final float MIN_FRICTION = 0.0f;
    private static final float MIN_TENSION = 0.0f;
    private TextView mFrictionLabel;
    private SeekBar mFrictionSeekBar;
    private final float mRevealPx;
    private final Spring mRevealerSpring;
    private SpringConfig mSelectedSpringConfig;
    private final List<SpringConfig> mSpringConfigs;
    private Spinner mSpringSelectorSpinner;
    private final float mStashPx;
    private TextView mTensionLabel;
    private SeekBar mTensionSeekBar;
    private final int mTextColor;
    private final SpinnerAdapter spinnerAdapter;
    private final SpringConfigRegistry springConfigRegistry;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public class OnNubTouchListener implements View.OnTouchListener {
        private OnNubTouchListener() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                SpringConfiguratorView.this.togglePosition();
                return true;
            }
            return true;
        }
    }

    /* loaded from: classes13.dex */
    private class RevealerSpringListener implements SpringListener {
        private RevealerSpringListener() {
        }

        @Override // com.jingdong.jdreact.plugin.banner.SpringListener
        public void onSpringActivate(Spring spring) {
        }

        @Override // com.jingdong.jdreact.plugin.banner.SpringListener
        public void onSpringAtRest(Spring spring) {
        }

        @Override // com.jingdong.jdreact.plugin.banner.SpringListener
        public void onSpringEndStateChange(Spring spring) {
        }

        @Override // com.jingdong.jdreact.plugin.banner.SpringListener
        public void onSpringUpdate(Spring spring) {
            float currentValue = (float) spring.getCurrentValue();
            float f2 = SpringConfiguratorView.this.mRevealPx;
            SpringConfiguratorView.this.setTranslationY((currentValue * (SpringConfiguratorView.this.mStashPx - f2)) + f2);
        }
    }

    /* loaded from: classes13.dex */
    private class SeekbarListener implements SeekBar.OnSeekBarChangeListener {
        private SeekbarListener() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
            if (seekBar == SpringConfiguratorView.this.mTensionSeekBar) {
                double d = ((i2 * 200.0f) / 100000.0f) + 0.0f;
                SpringConfiguratorView.this.mSelectedSpringConfig.tension = OrigamiValueConverter.tensionFromOrigamiValue(d);
                String format = SpringConfiguratorView.DECIMAL_FORMAT.format(d);
                SpringConfiguratorView.this.mTensionLabel.setText("T:" + format);
            }
            if (seekBar == SpringConfiguratorView.this.mFrictionSeekBar) {
                SpringConfig springConfig = SpringConfiguratorView.this.mSelectedSpringConfig;
                double d2 = ((i2 * SpringConfiguratorView.MAX_FRICTION) / 100000.0f) + 0.0f;
                springConfig.friction = OrigamiValueConverter.frictionFromOrigamiValue(d2);
                String format2 = SpringConfiguratorView.DECIMAL_FORMAT.format(d2);
                SpringConfiguratorView.this.mFrictionLabel.setText("F:" + format2);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public class SpinnerAdapter extends BaseAdapter {
        private final Context mContext;
        private final List<String> mStrings = new ArrayList();

        public SpinnerAdapter(Context context) {
            this.mContext = context;
        }

        public void add(String str) {
            this.mStrings.add(str);
            notifyDataSetChanged();
        }

        public void clear() {
            this.mStrings.clear();
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.mStrings.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            return this.mStrings.get(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            TextView textView;
            if (view == null) {
                textView = new TextView(this.mContext);
                textView.setLayoutParams(new AbsListView.LayoutParams(-1, -1));
                int dpToPx = Util.dpToPx(12.0f, SpringConfiguratorView.this.getResources());
                textView.setPadding(dpToPx, dpToPx, dpToPx, dpToPx);
                textView.setTextColor(SpringConfiguratorView.this.mTextColor);
            } else {
                textView = (TextView) view;
            }
            textView.setText(this.mStrings.get(i2));
            return textView;
        }
    }

    /* loaded from: classes13.dex */
    private class SpringSelectedListener implements AdapterView.OnItemSelectedListener {
        private SpringSelectedListener() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j2) {
            SpringConfiguratorView springConfiguratorView = SpringConfiguratorView.this;
            springConfiguratorView.mSelectedSpringConfig = (SpringConfig) springConfiguratorView.mSpringConfigs.get(i2);
            SpringConfiguratorView springConfiguratorView2 = SpringConfiguratorView.this;
            springConfiguratorView2.updateSeekBarsForSpringConfig(springConfiguratorView2.mSelectedSpringConfig);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public SpringConfiguratorView(Context context) {
        this(context, null);
    }

    private View generateHierarchy(Context context) {
        Resources resources = getResources();
        int dpToPx = Util.dpToPx(5.0f, resources);
        int dpToPx2 = Util.dpToPx(10.0f, resources);
        int dpToPx3 = Util.dpToPx(20.0f, resources);
        TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(0, -2, 1.0f);
        layoutParams.setMargins(0, 0, dpToPx, 0);
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(Util.createLayoutParams(-1, Util.dpToPx(300.0f, resources)));
        FrameLayout frameLayout2 = new FrameLayout(context);
        FrameLayout.LayoutParams createMatchParams = Util.createMatchParams();
        createMatchParams.setMargins(0, dpToPx3, 0, 0);
        frameLayout2.setLayoutParams(createMatchParams);
        frameLayout2.setBackgroundColor(Color.argb(100, 0, 0, 0));
        frameLayout.addView(frameLayout2);
        this.mSpringSelectorSpinner = new Spinner(context, 0);
        FrameLayout.LayoutParams createMatchWrapParams = Util.createMatchWrapParams();
        createMatchWrapParams.gravity = 48;
        createMatchWrapParams.setMargins(dpToPx2, dpToPx2, dpToPx2, 0);
        this.mSpringSelectorSpinner.setLayoutParams(createMatchWrapParams);
        frameLayout2.addView(this.mSpringSelectorSpinner);
        LinearLayout linearLayout = new LinearLayout(context);
        FrameLayout.LayoutParams createMatchWrapParams2 = Util.createMatchWrapParams();
        createMatchWrapParams2.setMargins(0, 0, 0, Util.dpToPx(80.0f, resources));
        createMatchWrapParams2.gravity = 80;
        linearLayout.setLayoutParams(createMatchWrapParams2);
        linearLayout.setOrientation(1);
        frameLayout2.addView(linearLayout);
        LinearLayout linearLayout2 = new LinearLayout(context);
        FrameLayout.LayoutParams createMatchWrapParams3 = Util.createMatchWrapParams();
        createMatchWrapParams3.setMargins(dpToPx2, dpToPx2, dpToPx2, dpToPx3);
        linearLayout2.setPadding(dpToPx2, dpToPx2, dpToPx2, dpToPx2);
        linearLayout2.setLayoutParams(createMatchWrapParams3);
        linearLayout2.setOrientation(0);
        linearLayout.addView(linearLayout2);
        SeekBar seekBar = new SeekBar(context);
        this.mTensionSeekBar = seekBar;
        seekBar.setLayoutParams(layoutParams);
        linearLayout2.addView(this.mTensionSeekBar);
        TextView textView = new TextView(getContext());
        this.mTensionLabel = textView;
        textView.setTextColor(this.mTextColor);
        FrameLayout.LayoutParams createLayoutParams = Util.createLayoutParams(Util.dpToPx(MAX_FRICTION, resources), -1);
        this.mTensionLabel.setGravity(19);
        this.mTensionLabel.setLayoutParams(createLayoutParams);
        this.mTensionLabel.setMaxLines(1);
        linearLayout2.addView(this.mTensionLabel);
        LinearLayout linearLayout3 = new LinearLayout(context);
        FrameLayout.LayoutParams createMatchWrapParams4 = Util.createMatchWrapParams();
        createMatchWrapParams4.setMargins(dpToPx2, dpToPx2, dpToPx2, dpToPx3);
        linearLayout3.setPadding(dpToPx2, dpToPx2, dpToPx2, dpToPx2);
        linearLayout3.setLayoutParams(createMatchWrapParams4);
        linearLayout3.setOrientation(0);
        linearLayout.addView(linearLayout3);
        SeekBar seekBar2 = new SeekBar(context);
        this.mFrictionSeekBar = seekBar2;
        seekBar2.setLayoutParams(layoutParams);
        linearLayout3.addView(this.mFrictionSeekBar);
        TextView textView2 = new TextView(getContext());
        this.mFrictionLabel = textView2;
        textView2.setTextColor(this.mTextColor);
        FrameLayout.LayoutParams createLayoutParams2 = Util.createLayoutParams(Util.dpToPx(MAX_FRICTION, resources), -1);
        this.mFrictionLabel.setGravity(19);
        this.mFrictionLabel.setLayoutParams(createLayoutParams2);
        this.mFrictionLabel.setMaxLines(1);
        linearLayout3.addView(this.mFrictionLabel);
        View view = new View(context);
        FrameLayout.LayoutParams createLayoutParams3 = Util.createLayoutParams(Util.dpToPx(60.0f, resources), Util.dpToPx(40.0f, resources));
        createLayoutParams3.gravity = 49;
        view.setLayoutParams(createLayoutParams3);
        view.setOnTouchListener(new OnNubTouchListener());
        view.setBackgroundColor(Color.argb(255, 0, (int) R2.anim.pop_in, 209));
        frameLayout.addView(view);
        return frameLayout;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void togglePosition() {
        this.mRevealerSpring.setEndValue(this.mRevealerSpring.getEndValue() == 1.0d ? 0.0d : 1.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSeekBarsForSpringConfig(SpringConfig springConfig) {
        int round = Math.round(((((float) OrigamiValueConverter.origamiValueFromTension(springConfig.tension)) - 0.0f) * 100000.0f) / 200.0f);
        int round2 = Math.round(((((float) OrigamiValueConverter.origamiValueFromFriction(springConfig.friction)) - 0.0f) * 100000.0f) / MAX_FRICTION);
        this.mTensionSeekBar.setProgress(round);
        this.mFrictionSeekBar.setProgress(round2);
    }

    public void destroy() {
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup != null) {
            viewGroup.removeView(this);
        }
        this.mRevealerSpring.destroy();
    }

    public void refreshSpringConfigurations() {
        Map<SpringConfig, String> allSpringConfig = this.springConfigRegistry.getAllSpringConfig();
        this.spinnerAdapter.clear();
        this.mSpringConfigs.clear();
        for (Map.Entry<SpringConfig, String> entry : allSpringConfig.entrySet()) {
            if (entry.getKey() != SpringConfig.defaultConfig) {
                this.mSpringConfigs.add(entry.getKey());
                this.spinnerAdapter.add(entry.getValue());
            }
        }
        this.mSpringConfigs.add(SpringConfig.defaultConfig);
        this.spinnerAdapter.add(allSpringConfig.get(SpringConfig.defaultConfig));
        this.spinnerAdapter.notifyDataSetChanged();
        if (this.mSpringConfigs.size() > 0) {
            this.mSpringSelectorSpinner.setSelection(0);
        }
    }

    public SpringConfiguratorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @TargetApi(11)
    public SpringConfiguratorView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mSpringConfigs = new ArrayList();
        this.mTextColor = Color.argb(255, 225, 225, 225);
        SpringSystem create = SpringSystem.create();
        this.springConfigRegistry = SpringConfigRegistry.getInstance();
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(context);
        this.spinnerAdapter = spinnerAdapter;
        Resources resources = getResources();
        this.mRevealPx = Util.dpToPx(40.0f, resources);
        float dpToPx = Util.dpToPx(280.0f, resources);
        this.mStashPx = dpToPx;
        Spring createSpring = create.createSpring();
        this.mRevealerSpring = createSpring;
        createSpring.setCurrentValue(1.0d).setEndValue(1.0d).addListener(new RevealerSpringListener());
        addView(generateHierarchy(context));
        SeekbarListener seekbarListener = new SeekbarListener();
        this.mTensionSeekBar.setMax(MAX_SEEKBAR_VAL);
        this.mTensionSeekBar.setOnSeekBarChangeListener(seekbarListener);
        this.mFrictionSeekBar.setMax(MAX_SEEKBAR_VAL);
        this.mFrictionSeekBar.setOnSeekBarChangeListener(seekbarListener);
        this.mSpringSelectorSpinner.setAdapter((android.widget.SpinnerAdapter) spinnerAdapter);
        this.mSpringSelectorSpinner.setOnItemSelectedListener(new SpringSelectedListener());
        refreshSpringConfigurations();
        setTranslationY(dpToPx);
    }
}
