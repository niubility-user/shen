package com.jingdong.common.sample.jshop;

import android.os.Bundle;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.sample.jshop.ui.JshopTitle;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes6.dex */
public class JshopPromotionRule extends MyActivity {
    private static final boolean DBG = false;
    private static final String TAG = "JshopPromotionRule";
    private TextView mContentText;
    private JshopTitle mJsTitle;
    public SourceEntity mSource;
    private TextView mTitleText;

    private void initView() {
        this.mTitleText = (TextView) findViewById(R.id.jshop_rule_title_text);
        this.mContentText = (TextView) findViewById(R.id.jshop_rule_text);
        this.mTitleText.setText(getIntent().getStringExtra("name"));
        this.mContentText.setText(getIntent().getStringExtra("ruleDetail"));
        JshopTitle jshopTitle = (JshopTitle) findViewById(R.id.rule_title);
        this.mJsTitle = jshopTitle;
        jshopTitle.setTitleText(R.string.jshop_promotion_rule);
        this.mJsTitle.setLeftSplitLineVisibility(8);
        this.mJsTitle.setOnTitleClickListener(new JshopTitle.TitleClickListener() { // from class: com.jingdong.common.sample.jshop.JshopPromotionRule.1
            @Override // com.jingdong.common.sample.jshop.ui.JshopTitle.TitleClickListener
            public void onLeftClicked() {
                JshopPromotionRule.this.finish();
            }

            @Override // com.jingdong.common.sample.jshop.ui.JshopTitle.TitleClickListener
            public void onRightClicked() {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        if (getIntent() != null) {
            SourceEntity sourceEntity = (SourceEntity) getIntent().getSerializableExtra("source");
            if (sourceEntity != null) {
                this.mSource = sourceEntity;
                if (Log.D) {
                    System.out.println(sourceEntity);
                }
            } else if (Log.D) {
                System.err.println("JshopPromotionRule SourceEntity = null");
            }
        }
        setContentView(R.layout.jshop_promotion_rule);
        initView();
    }
}
