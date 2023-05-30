package com.jingdong.sdk.platform.floor.isv;

import android.view.View;
import java.util.Map;

/* loaded from: classes10.dex */
public interface IBaseCooperateExt {
    void adapterDarkModel(boolean z);

    void adapterVersion(int i2);

    Object getBaseConfig();

    void jump(int i2, String str);

    void mtaClick(String str, Map<String, Object> map);

    void mtaExplore(String str, Map<String, Object> map);

    void refresh();

    void showDialog(View view, float f2);

    void showToast(String str);
}
