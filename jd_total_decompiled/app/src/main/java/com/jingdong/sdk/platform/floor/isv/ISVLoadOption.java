package com.jingdong.sdk.platform.floor.isv;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.temp.FloorTemplate;
import com.jingdong.sdk.platform.floor.BaseFloor;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes10.dex */
public class ISVLoadOption extends BaseLoadFloorOption {
    public static List<Class<?>> optionClasses = new ArrayList();
    HashMap<String, Class<? extends BaseFloor>> map;
    public List<BaseLoadFloorOption> subOptions = new ArrayList();

    /* loaded from: classes10.dex */
    public interface ICallBack {
        void onLoop(BaseLoadFloorOption baseLoadFloorOption);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b(BaseLoadFloorOption baseLoadFloorOption) {
        this.map.putAll(baseLoadFloorOption.getFloorClass());
    }

    public static void loadOptions(Context context) {
        try {
            InputStream open = context.getAssets().open("cooper.json");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(open));
            char[] cArr = new char[open.available()];
            bufferedReader.read(cArr);
            List parseArray = JDJSON.parseArray(new String(cArr), String.class);
            optionClasses.clear();
            Iterator it = parseArray.iterator();
            while (it.hasNext()) {
                optionClasses.add(context.getClassLoader().loadClass((String) it.next()));
            }
            open.close();
        } catch (IOException | ClassNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    private void loopOption(ICallBack iCallBack) {
        for (BaseLoadFloorOption baseLoadFloorOption : this.subOptions) {
            if (iCallBack != null) {
                iCallBack.onLoop(baseLoadFloorOption);
            }
        }
    }

    @Override // com.jingdong.sdk.platform.floor.isv.BaseLoadFloorOption
    public boolean buildTemplate(BaseTemplateEntity baseTemplateEntity) {
        if (!(baseTemplateEntity instanceof FloorTemplate) || TextUtils.isEmpty(baseTemplateEntity.mId)) {
            return true;
        }
        boolean z = false;
        Iterator<BaseLoadFloorOption> it = this.subOptions.iterator();
        while (it.hasNext()) {
            z |= it.next().buildTemplate(baseTemplateEntity);
        }
        return z;
    }

    @Override // com.jingdong.sdk.platform.floor.isv.BaseLoadFloorOption
    public IBaseView createView(ViewGroup viewGroup, String str) {
        for (BaseLoadFloorOption baseLoadFloorOption : this.subOptions) {
            List<String> registViews = baseLoadFloorOption.getRegistViews();
            if (registViews != null) {
                Iterator<String> it = registViews.iterator();
                while (it.hasNext()) {
                    if (TextUtils.equals(it.next(), str)) {
                        return baseLoadFloorOption.createView(viewGroup, str);
                    }
                }
            }
        }
        return null;
    }

    public void generateInstance() {
        if (this.subOptions.isEmpty()) {
            try {
                Iterator<Class<?>> it = optionClasses.iterator();
                while (it.hasNext()) {
                    Object newInstance = it.next().newInstance();
                    if (newInstance instanceof BaseLoadFloorOption) {
                        this.subOptions.add((BaseLoadFloorOption) newInstance);
                    }
                }
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (InstantiationException e3) {
                e3.printStackTrace();
            }
        }
    }

    @Override // com.jingdong.sdk.platform.floor.isv.BaseLoadFloorOption
    public HashMap<String, Class<? extends BaseFloor>> getFloorClass() {
        if (this.map == null) {
            this.map = new HashMap<>();
        }
        loopOption(new ICallBack() { // from class: com.jingdong.sdk.platform.floor.isv.d
            @Override // com.jingdong.sdk.platform.floor.isv.ISVLoadOption.ICallBack
            public final void onLoop(BaseLoadFloorOption baseLoadFloorOption) {
                ISVLoadOption.this.b(baseLoadFloorOption);
            }
        });
        return this.map;
    }

    @Override // com.jingdong.sdk.platform.floor.isv.BaseLoadFloorOption
    public List<String> getRegistViews() {
        final ArrayList arrayList = new ArrayList();
        loopOption(new ICallBack() { // from class: com.jingdong.sdk.platform.floor.isv.c
            @Override // com.jingdong.sdk.platform.floor.isv.ISVLoadOption.ICallBack
            public final void onLoop(BaseLoadFloorOption baseLoadFloorOption) {
                arrayList.addAll(baseLoadFloorOption.getRegistViews());
            }
        });
        return arrayList;
    }

    @Override // com.jingdong.sdk.platform.floor.isv.BaseLoadFloorOption
    public void onMainViewScrolled(final Activity activity, final int i2) {
        loopOption(new ICallBack() { // from class: com.jingdong.sdk.platform.floor.isv.a
            @Override // com.jingdong.sdk.platform.floor.isv.ISVLoadOption.ICallBack
            public final void onLoop(BaseLoadFloorOption baseLoadFloorOption) {
                baseLoadFloorOption.onMainViewScrolled(activity, i2);
            }
        });
    }

    @Override // com.jingdong.sdk.platform.floor.isv.BaseLoadFloorOption
    public void onStartBuildTemplate() {
        generateInstance();
        loopOption(new ICallBack() { // from class: com.jingdong.sdk.platform.floor.isv.b
            @Override // com.jingdong.sdk.platform.floor.isv.ISVLoadOption.ICallBack
            public final void onLoop(BaseLoadFloorOption baseLoadFloorOption) {
                baseLoadFloorOption.onStartBuildTemplate();
            }
        });
    }
}
