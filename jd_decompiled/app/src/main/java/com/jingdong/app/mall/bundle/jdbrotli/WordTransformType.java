package com.jingdong.app.mall.bundle.jdbrotli;

/* loaded from: classes2.dex */
public class WordTransformType {
    static final int IDENTITY = 0;
    static final int OMIT_FIRST_1 = 12;
    static final int OMIT_FIRST_2 = 13;
    static final int OMIT_FIRST_3 = 14;
    static final int OMIT_FIRST_4 = 15;
    static final int OMIT_FIRST_5 = 16;
    static final int OMIT_FIRST_6 = 17;
    static final int OMIT_FIRST_7 = 18;
    static final int OMIT_FIRST_8 = 19;
    static final int OMIT_FIRST_9 = 20;
    static final int OMIT_LAST_1 = 1;
    static final int OMIT_LAST_2 = 2;
    static final int OMIT_LAST_3 = 3;
    static final int OMIT_LAST_4 = 4;
    static final int OMIT_LAST_5 = 5;
    static final int OMIT_LAST_6 = 6;
    static final int OMIT_LAST_7 = 7;
    static final int OMIT_LAST_8 = 8;
    static final int OMIT_LAST_9 = 9;
    static final int UPPERCASE_ALL = 11;
    static final int UPPERCASE_FIRST = 10;

    WordTransformType() {
    }

    static int getOmitFirst(int i2) {
        if (i2 >= 12) {
            return (i2 - 12) + 1;
        }
        return 0;
    }

    static int getOmitLast(int i2) {
        if (i2 <= 9) {
            return (i2 - 1) + 1;
        }
        return 0;
    }
}
