package com.jingdong.common.unification.router.builder;

import android.os.Bundle;
import android.os.Parcelable;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.builder.RouterEntry;
import java.io.Serializable;

/* loaded from: classes6.dex */
public class RouterEntry<T extends RouterEntry> {
    public static final String EXTRA_INTENTFLAG = "intentFlag";
    public static final String EXTRA_REQUESTCODE = "requestCode";
    public CallBackListener callBackListener;
    public Object mExtraData;
    public int intentFlag = -1;
    public int requestCode = -1;
    public Bundle extraBundle = new Bundle();

    public T callBackListener(CallBackListener callBackListener) {
        this.callBackListener = callBackListener;
        return this;
    }

    public T extraData(Object obj) {
        this.mExtraData = obj;
        return this;
    }

    public T extraObject(String str, Object obj) {
        if (obj instanceof Serializable) {
            this.extraBundle.putSerializable(str, (Serializable) obj);
        } else if (obj instanceof Parcelable) {
            this.extraBundle.putParcelable(str, (Parcelable) obj);
        }
        return this;
    }

    public T intentFlag(int i2) {
        this.intentFlag = i2;
        return this;
    }

    public Bundle preAllData() {
        int i2 = this.intentFlag;
        if (i2 != -1) {
            this.extraBundle.putInt(EXTRA_INTENTFLAG, i2);
        }
        int i3 = this.requestCode;
        if (i3 != -1) {
            this.extraBundle.putInt("requestCode", i3);
        }
        return this.extraBundle;
    }

    public T requestCode(int i2) {
        this.requestCode = i2;
        return this;
    }
}
