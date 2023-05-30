package com.jdpay.widget.recycler.indexer;

import com.jdpay.lib.Bean;

/* loaded from: classes18.dex */
public interface JPIndexableBean extends Bean {
    public static final int TYPE_DATA = 1;
    public static final int TYPE_INDEX = 2;

    char getIndexChar();

    int getType();
}
