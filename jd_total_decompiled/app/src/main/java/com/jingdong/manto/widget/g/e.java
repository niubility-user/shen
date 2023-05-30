package com.jingdong.manto.widget.g;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.manto.R;
import com.jingdong.manto.launch.LaunchParam;
import com.jingdong.manto.pkg.db.entity.PkgHistoryEntity;
import com.jingdong.manto.widget.g.f;
import java.util.List;

/* loaded from: classes16.dex */
public class e {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements f.c {
        a() {
        }

        @Override // com.jingdong.manto.widget.g.f.c
        public void a(PkgHistoryEntity pkgHistoryEntity, int i2) {
            LaunchParam launchParam = new LaunchParam();
            launchParam.appId = pkgHistoryEntity.appId;
            launchParam.debugType = pkgHistoryEntity.type;
            com.jingdong.a.o(launchParam);
        }
    }

    public static void a(Activity activity, View view, List<PkgHistoryEntity> list) {
        if (view == null || list == null) {
            return;
        }
        f fVar = new f(activity);
        LayoutInflater.from(activity).inflate(R.layout.manto_nav_drop_list_layout, (ViewGroup) null).setBackgroundResource(R.drawable.manto_nav_list_window_back);
        fVar.a(list);
        fVar.a(new a());
        fVar.showAsDropDown(view);
    }
}
