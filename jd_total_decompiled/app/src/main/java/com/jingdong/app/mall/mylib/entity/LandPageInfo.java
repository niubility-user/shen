package com.jingdong.app.mall.mylib.entity;

import com.un.lib.popup.JDTopPopupWindowHelper;

/* loaded from: classes4.dex */
public class LandPageInfo {

    /* loaded from: classes4.dex */
    public enum LandPageEnum {
        landPage0(0, 0, "\u6d88\u606f\u4e2d\u5fc3\u76d2\u5b50"),
        landPage1(1, 1, "M\u9875"),
        landPage2(2, 2, "\u79d2\u6740"),
        landPage3(3, 3, "\u5e97\u94fa\u4e3b\u9875"),
        landPage4(4, 4, "\u5546\u54c1\u8be6\u60c5"),
        landPage5(5, 5, "\u624b\u673a\u4e13\u4eab"),
        landPage6(6, 6, "\u8ba2\u5355\u5217\u8868"),
        landPage7(7, 7, JDTopPopupWindowHelper.BASE_CART),
        landPage8(8, 8, "\u8ba2\u5355\u8be6\u60c5"),
        landPage9(9, 9, "\u8bc4\u4ef7\u6652\u5355"),
        landPage10(10, 10, "\u7269\u6d41\u8ddf\u8e2a"),
        landPage11(11, 11, "\u4f18\u60e0\u5238"),
        landPage12(12, 12, "\u6d88\u606f\u4e2d\u5fc3\u4e8c\u7ea7\u9875\u9762"),
        landPage13(13, 13, "\u901a\u5929\u5854"),
        landPage14(14, 14, "\u865a\u62df\u8ba2\u5355\u843d\u5730\u9875"),
        landPage15(15, 15, "\u4f18\u60e0\u5238\u5f85\u9886\u53d6\u9875\u9762"),
        landPage16(16, 16, "\u641c\u7d22\u9875\u9762"),
        landPage17(17, 17, "\u549a\u549a\u9875\u9762"),
        landPage18(18, 18, "\u65e5\u5386\u9875\u9762"),
        landPage19(19, 19, "\u95ee\u7b54\u56de\u590d"),
        landPage20(20, 20, "openApp\u53d1\u73b0\u7684\u9875\u9762\u7b49"),
        landPage21(21, 21, "\u9886\u5238\u4e2d\u5fc3"),
        landPage22(23, 23, "\u7d27\u6025push"),
        landPage23(24, 24, "\u5238\u4ea4\u6613\u4e2d\u5fc3"),
        landPage24(25, 25, "\u626b\u7801\u767b\u5f55");
        
        public int id;
        public int landPage;
        public String landPageName;

        LandPageEnum(int i2, int i3, String str) {
            this.id = i2;
            this.landPage = i3;
            this.landPageName = str;
        }

        public static int getLandTypeById(int i2) {
            for (LandPageEnum landPageEnum : values()) {
                if (i2 == landPageEnum.id) {
                    return landPageEnum.landPage;
                }
            }
            return -1;
        }

        public static int getNotifcationTypeById(int i2) {
            for (LandPageEnum landPageEnum : values()) {
                if (i2 == landPageEnum.id) {
                    return landPageEnum.landPage;
                }
            }
            return 0;
        }
    }
}
