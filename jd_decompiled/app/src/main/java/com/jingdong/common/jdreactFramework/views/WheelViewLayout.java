package com.jingdong.common.jdreactFramework.views;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.jingdong.common.jdreactFramework.utils.WheelView;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class WheelViewLayout extends LinearLayout {
    public static final int UPDATE = 1001;
    Handler mHandle;

    /* loaded from: classes5.dex */
    class Wheel {
        public int position;
        public WheelView view;

        Wheel() {
        }
    }

    public WheelViewLayout(Context context) {
        this(context, null);
    }

    public void refreshWheelView(WheelView[] wheelViewArr, int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < wheelViewArr.length; i2++) {
            Wheel wheel = new Wheel();
            wheel.position = iArr[i2];
            wheel.view = wheelViewArr[i2];
            arrayList.add(wheel);
        }
        this.mHandle.sendMessage(this.mHandle.obtainMessage(1001, arrayList));
    }

    public WheelViewLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.mHandle = new Handler() { // from class: com.jingdong.common.jdreactFramework.views.WheelViewLayout.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what != 1001) {
                    return;
                }
                ArrayList arrayList = (ArrayList) message.obj;
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    Wheel wheel = (Wheel) arrayList.get(i2);
                    wheel.view.invalidate();
                    wheel.view.setDefault(wheel.position);
                }
            }
        };
    }

    public WheelViewLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mHandle = new Handler() { // from class: com.jingdong.common.jdreactFramework.views.WheelViewLayout.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what != 1001) {
                    return;
                }
                ArrayList arrayList = (ArrayList) message.obj;
                for (int i22 = 0; i22 < arrayList.size(); i22++) {
                    Wheel wheel = (Wheel) arrayList.get(i22);
                    wheel.view.invalidate();
                    wheel.view.setDefault(wheel.position);
                }
            }
        };
    }

    public WheelViewLayout(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2);
        this.mHandle = new Handler() { // from class: com.jingdong.common.jdreactFramework.views.WheelViewLayout.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what != 1001) {
                    return;
                }
                ArrayList arrayList = (ArrayList) message.obj;
                for (int i22 = 0; i22 < arrayList.size(); i22++) {
                    Wheel wheel = (Wheel) arrayList.get(i22);
                    wheel.view.invalidate();
                    wheel.view.setDefault(wheel.position);
                }
            }
        };
    }
}
