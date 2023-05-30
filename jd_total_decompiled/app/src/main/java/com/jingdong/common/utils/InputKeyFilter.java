package com.jingdong.common.utils;

import android.text.method.NumberKeyListener;

/* loaded from: classes6.dex */
public class InputKeyFilter extends NumberKeyListener {
    @Override // android.text.method.NumberKeyListener
    protected char[] getAcceptedChars() {
        return new char[]{'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'l', 'k', 'j', 'h', 'g', 'f', 'd', 's', 'a', 'z', 'x', 'c', 'v', 'b', 'n', 'm', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '_', '\n'};
    }

    @Override // android.text.method.KeyListener
    public int getInputType() {
        return 0;
    }
}
