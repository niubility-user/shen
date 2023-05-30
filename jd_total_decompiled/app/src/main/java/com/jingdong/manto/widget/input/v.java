package com.jingdong.manto.widget.input;

import com.jingdong.manto.utils.MantoLog;
import java.lang.ref.WeakReference;

/* loaded from: classes16.dex */
public abstract class v extends j {
    @Override // com.jingdong.manto.widget.input.j
    void a(y yVar) {
        MantoLog.e("TextAreaInvokeHandler", "removeInputImpl: editText = " + yVar.toString());
        if (yVar == null) {
            MantoLog.w("TextAreaInvokeHandler", "removeInputImpl failed, EditText is null");
            return;
        }
        int i2 = yVar.f14508k;
        yVar.b(this.f14463f);
        WeakReference<com.jingdong.manto.q.n> weakReference = this.f14470m;
        com.jingdong.manto.q.n nVar = weakReference == null ? null : weakReference.get();
        if (nVar == null) {
            MantoLog.w("TextAreaInvokeHandler", String.format(String.format("removeInputImpl(viewId : %s) failed, pageView is null", Integer.valueOf(i2)), new Object[0]));
            return;
        }
        com.jingdong.manto.q.d n2 = nVar.n();
        if (n2 == null) {
            MantoLog.w("TextAreaInvokeHandler", String.format(String.format("removeInputImpl(viewId : %s) failed, CustomViewContainer is null", Integer.valueOf(i2)), new Object[0]));
        } else {
            MantoLog.i("TextAreaInvokeHandler", String.format("removeInputImpl(viewId : %s) success = %s", Integer.valueOf(i2), Boolean.valueOf(n2.f(i2))));
        }
    }

    @Override // com.jingdong.manto.widget.input.j
    boolean a(y yVar, com.jingdong.manto.widget.input.a0.f fVar) {
        String format;
        com.jingdong.manto.q.n nVar;
        MantoLog.e("TextAreaInvokeHandler", "addInputImpl: param = " + fVar);
        if (yVar == null) {
            format = "addInputImpl failed, EditText is null";
        } else {
            int i2 = fVar.H;
            WeakReference<com.jingdong.manto.q.n> weakReference = this.f14470m;
            if (weakReference == null || (nVar = weakReference.get()) == null) {
                format = String.format("addInputImpl(viewId : %s) failed, pageView is null", Integer.valueOf(i2));
            } else {
                com.jingdong.manto.q.d n2 = nVar.n();
                if (n2 != null) {
                    Boolean bool = fVar.t;
                    int i3 = (bool == null || !bool.booleanValue()) ? 0 : 4;
                    int i4 = fVar.L;
                    float[] fArr = {fVar.f14404f.intValue(), fVar.f14403e.intValue(), fVar.f14402c.intValue(), fVar.d.intValue(), 0.0f};
                    Boolean bool2 = fVar.x;
                    boolean b = n2.b(yVar, i2, i4, fArr, i3, bool2 != null && bool2.booleanValue(), 0);
                    MantoLog.i("TextAreaInvokeHandler", String.format(String.format("addInputImpl(viewId : %s), success = %b ", Integer.valueOf(i2), Boolean.valueOf(b)), new Object[0]));
                    return b;
                }
                format = String.format("addInputImpl(viewId : %s) failed, CustomViewContainer is null", Integer.valueOf(i2));
            }
        }
        MantoLog.w("TextAreaInvokeHandler", format);
        return false;
    }

    @Override // com.jingdong.manto.widget.input.j
    boolean b(y yVar, com.jingdong.manto.widget.input.a0.f fVar) {
        String format;
        com.jingdong.manto.q.n nVar;
        MantoLog.e("TextAreaInvokeHandler", "updateInputPosition: param = " + fVar);
        if (yVar == null) {
            format = "updateInputPosition failed, EditText is null";
        } else {
            int i2 = yVar.f14508k;
            WeakReference<com.jingdong.manto.q.n> weakReference = this.f14470m;
            if (weakReference == null || (nVar = weakReference.get()) == null) {
                format = String.format("updateInputPosition(viewId : %s) failed, pageView is null", Integer.valueOf(i2));
            } else {
                com.jingdong.manto.q.d n2 = nVar.n();
                if (n2 != null) {
                    Boolean bool = fVar.t;
                    boolean a = n2.a(i2, new float[]{fVar.f14404f.intValue(), fVar.f14403e.intValue(), fVar.f14402c.intValue(), fVar.d.intValue(), 0.0f}, (bool == null || !bool.booleanValue()) ? 0 : 4, fVar.x, 0);
                    MantoLog.i("TextAreaInvokeHandler", String.format(String.format("updateInputPosition(viewId : %s), success = %b ", Integer.valueOf(i2), Boolean.valueOf(a)), new Object[0]));
                    return a;
                }
                format = String.format("updateInputPosition(viewId : %s) failed, CustomViewContainer is null", Integer.valueOf(i2));
            }
        }
        MantoLog.w("TextAreaInvokeHandler", format);
        return false;
    }
}
