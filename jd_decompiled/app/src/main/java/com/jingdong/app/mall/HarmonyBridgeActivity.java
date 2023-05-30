package com.jingdong.app.mall;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.entity.cart.NewGiftPoolItem;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.ArrayList;

/* loaded from: classes19.dex */
public class HarmonyBridgeActivity extends MyActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 100 && i3 == -1) {
            try {
                ArrayList parcelableArrayList = intent.getExtras().getParcelableArrayList("pageData");
                if (parcelableArrayList != null) {
                    String jSONString = JDJSON.toJSONString(parcelableArrayList);
                    Intent intent2 = new Intent();
                    intent2.putExtra("pageData", jSONString);
                    setResult(-1, intent2);
                }
            } catch (Exception e2) {
                ExceptionReporter.reportExceptionToBugly(e2);
            }
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle extras = getIntent().getExtras();
        String string = extras.getString("pageData");
        if (!TextUtils.isEmpty(string)) {
            extras.putParcelableArrayList("pageData", new ArrayList<>(JDJSON.parseArray(string, NewGiftPoolItem.class)));
        }
        try {
            DeepLinkCommonHelper.startActivityForResult(this, DeepLinkCommonHelper.HOST_GIFT_POOL_ACTIVITY, extras, 100);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
