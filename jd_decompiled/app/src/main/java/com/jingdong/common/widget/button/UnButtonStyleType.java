package com.jingdong.common.widget.button;

/* loaded from: classes12.dex */
public enum UnButtonStyleType {
    A(1),
    A_S(2),
    B(3),
    X_B(4),
    X_B_S(5),
    X_A(6),
    X_A_S(7),
    D_A(8),
    D_A_S(9),
    E(10),
    E_S(11),
    A_A(12),
    A_A_S(13),
    M_01(14),
    M_02(15),
    M_03(16),
    M_04(17),
    M_05(18),
    DIALOG_RED(19),
    DIALOG_WHITE(20),
    YELLOW(21),
    E_X(22),
    X_A_X(23);
    
    private int type;

    UnButtonStyleType(int i2) {
        this.type = i2;
    }

    public int getType() {
        return this.type;
    }
}
