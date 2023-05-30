package com.jingdong.sdk.platform.floor.isv.store;

import android.content.Context;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jingdong.sdk.platform.utils.PlatformTools;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes10.dex */
public class OptionStore {
    static final String ISV_NAME = "isvoptions";
    ArrayList<String> mOptions = new ArrayList<>();
    String moduleName;

    public OptionStore(String str) {
        this.moduleName = str;
    }

    public ArrayList<String> getOptions() {
        return this.mOptions;
    }

    void loadFromApp(Context context) throws IOException {
        String readLine;
        if (context == null) {
            return;
        }
        String str = "isvoptions/" + this.moduleName;
        String[] list = context.getAssets().list(str);
        if (list != null) {
            for (String str2 : list) {
                InputStream open = context.getAssets().open(str + "/" + str2);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(open));
                do {
                    readLine = bufferedReader.readLine();
                    if (!TextUtils.isEmpty(readLine)) {
                        this.mOptions.add(readLine);
                    }
                } while (readLine != null);
                open.close();
            }
        }
    }

    public void preLoad(Context context) {
        try {
            if (PlatformTools.D) {
                loadFromApp(context);
            }
            Iterator<String> it = this.mOptions.iterator();
            while (it.hasNext()) {
                try {
                    context.getApplicationContext().getClassLoader().loadClass(it.next());
                } catch (ClassNotFoundException e2) {
                    PlatformTools.catchExceptionAndReportToBugly(e2);
                }
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    public void registOption(String str) {
        this.mOptions.add(str);
    }

    public void registOptionJsonString(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        JDJSONArray parseArray = JDJSON.parseArray(str);
        for (int i2 = 0; i2 < parseArray.size(); i2++) {
            String string = parseArray.getString(i2);
            if (!this.mOptions.contains(string)) {
                this.mOptions.add(string);
            }
        }
    }
}
