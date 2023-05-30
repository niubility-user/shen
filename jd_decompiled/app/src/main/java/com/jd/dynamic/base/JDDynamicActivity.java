package com.jd.dynamic.base;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.Keep;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.JSONException;
import org.json.JSONObject;

@Keep
/* loaded from: classes13.dex */
public class JDDynamicActivity extends DynamicBaseActivity {
    private FrameLayout container;

    private JSONObject getJSONDataFromAsset(String str) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open(str)));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    bufferedReader.close();
                    return new JSONObject(sb.toString());
                }
                sb.append(readLine);
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        } catch (JSONException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    @Override // com.jd.dynamic.base.DynamicBaseActivity
    protected View getContentView() {
        FrameLayout frameLayout = new FrameLayout(this);
        this.container = frameLayout;
        return frameLayout;
    }

    @Override // com.jd.dynamic.base.DynamicBaseActivity
    protected FrameLayout getDynamicContainer() {
        return this.container;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.dynamic.base.DynamicBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }
}
