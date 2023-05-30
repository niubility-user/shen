package com.jingdong.app.mall.home.floor.model.entity;

import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.common.i.s;
import com.jingdong.app.mall.home.n.h.c;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.h;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class BubbleDynamicEntity extends FloorEntity {
    public static final int DEF_LR_SKU_RADIUS = 20;
    public static final int DEF_MID_SKU_RADIUS = 18;
    private long countdownEnd;
    private long countdownStart;
    private long countdownTimestamp;
    private long flipInterval;
    public int floorHeight;
    public int[] lSkuSize;
    public int[] lTextSize;
    public f leftElement;
    public int[] midBottomTextSize;
    public f midElement;
    public int[] midSkuSize;
    private int midTopMode;
    public int[] midTopTextSize;
    public int middleWidth;
    public int[] rSkuSize;
    public int[] rTextSize;
    public f rightElement;
    private String textAfter;
    private String textBefore;
    private String[] timeColors;
    public int lrSkuRadius = 20;
    public int midSkuRadius = 18;

    private void addExpoJson() {
        f fVar = this.leftElement;
        if (fVar != null) {
            addExpoJson(fVar.l());
        }
        f fVar2 = this.midElement;
        if (fVar2 != null) {
            addExpoJson(fVar2.l());
        }
        f fVar3 = this.rightElement;
        if (fVar3 != null) {
            addExpoJson(fVar3.l());
        }
    }

    private boolean checkDataValid() {
        return (this.leftElement == null || this.midElement == null || this.rightElement == null) ? false : true;
    }

    private boolean checkSizeValid() {
        int[] iArr = this.lSkuSize;
        boolean z = iArr != null && iArr.length == 4 && iArr[0] > 0 && iArr[1] > 0;
        int[] iArr2 = this.rSkuSize;
        boolean z2 = z & (iArr2 != null && iArr2.length == 4 && iArr2[0] > 0 && iArr2[1] > 0);
        int[] iArr3 = this.midSkuSize;
        boolean z3 = z2 & (iArr3 == null || (iArr3.length == 4 && iArr3[0] > 0 && iArr3[1] > 0));
        int[] iArr4 = this.lTextSize;
        boolean z4 = z3 & (iArr4 != null && iArr4.length == 3);
        int[] iArr5 = this.rTextSize;
        boolean z5 = z4 & (iArr5 != null && iArr5.length == 3);
        int[] iArr6 = this.midTopTextSize;
        boolean z6 = z5 & (iArr6 != null && iArr6.length == 2);
        int[] iArr7 = this.midBottomTextSize;
        return z6 & (iArr7 != null && iArr7.length == 2);
    }

    public long getEndTime() {
        return this.countdownTimestamp;
    }

    public long getEndTimeRemain() {
        return (this.countdownEnd * 1000) - (SystemClock.elapsedRealtime() - s.f9357c);
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public ArrayList<String> getExpoData() {
        if (isValid()) {
            this.mExposData.clear();
            this.mExposData.add(this.leftElement.j());
            this.mExposData.add(this.midElement.j());
            this.mExposData.add(this.rightElement.j());
            return this.mExposData;
        }
        return super.getExpoData();
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public ArrayList<b> getExpoJson() {
        return this.mJsonExposData;
    }

    public long getFlipInterval() {
        return this.flipInterval;
    }

    public int getLrSkuRadius() {
        return this.lrSkuRadius;
    }

    public int getMidSkuRadius() {
        return this.midSkuRadius;
    }

    public int getMidTopMode() {
        return this.midTopMode;
    }

    public int[] getMidTopTextColors() {
        return m.o(this.midElement.getJsonString("topTextColor"), -16777216);
    }

    public int getMidTopTimeOvalBgColor() {
        return 0;
    }

    public int getMidTopTimePointColor() {
        String[] strArr = this.timeColors;
        if (strArr == null || strArr.length <= 1) {
            return -16777216;
        }
        return m.i(strArr[1], -16777216);
    }

    public int getMidTopTimeTextColor() {
        String[] strArr = this.timeColors;
        if (strArr == null || strArr.length <= 0) {
            return -16777216;
        }
        return m.i(strArr[0], -16777216);
    }

    public long getStartTimeRemain() {
        return (this.countdownStart * 1000) - (SystemClock.elapsedRealtime() - s.f9357c);
    }

    public String getTextAfter() {
        return this.textAfter;
    }

    public String getTextBefore() {
        return this.textBefore;
    }

    @Override // com.jingdong.app.mall.home.floor.model.entity.FloorEntity
    public boolean isValid() {
        return checkSizeValid() && checkDataValid();
    }

    public void parseData(h hVar) {
        addExpoJson();
        long jsonLong = hVar.getJsonLong("aInterval", 30L) * 100;
        this.flipInterval = jsonLong;
        if (jsonLong < 500) {
            jsonLong = 3000;
        }
        this.flipInterval = jsonLong;
        f fVar = this.midElement;
        if (fVar != null) {
            this.midTopMode = fVar.getJsonInt("textMode");
            String jsonString = this.midElement.getJsonString("topText");
            boolean z = this.midTopMode == 1;
            this.textBefore = z ? jsonString : this.midElement.getJsonString("textBefore");
            if (!z) {
                jsonString = this.midElement.getJsonString("textAfter");
            }
            this.textAfter = jsonString;
            if (this.midTopMode == 2) {
                this.countdownStart = c.i(this.midElement.getJsonString("countdownStart"));
                this.countdownEnd = c.i(this.midElement.getJsonString("countdownEnd"));
                this.countdownTimestamp = c.i(this.midElement.getJsonString("countdownTimestamp"));
                String jsonString2 = this.midElement.getJsonString("countdownAllColor", "");
                this.timeColors = TextUtils.isEmpty(jsonString2) ? null : jsonString2.split(ContainerUtils.FIELD_DELIMITER);
            }
        }
    }

    public void resetData() {
        this.leftElement = null;
        this.midElement = null;
        this.rightElement = null;
    }

    public void resetSizeParams() {
        this.lSkuSize = null;
        this.lTextSize = null;
        this.midSkuSize = null;
        this.rSkuSize = null;
        this.rTextSize = null;
        this.midTopTextSize = null;
        this.midBottomTextSize = null;
        this.middleWidth = 0;
        this.lrSkuRadius = 20;
        this.midSkuRadius = 18;
    }

    private void addExpoJson(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mJsonExposData.add(b.c(str));
    }
}
