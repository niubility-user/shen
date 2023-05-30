package com.tencent.map.lib.models;

import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.jd.dynamic.DYConstants;
import java.util.ArrayList;
import java.util.List;

@Keep
/* loaded from: classes9.dex */
public final class IndoorCellInfo {
    private List<String> areaIds = new ArrayList();
    private Style style;

    /* loaded from: classes9.dex */
    public static class Style {
        private int color;

        public Style(int i2) {
            this.color = i2;
        }

        public int getColor() {
            return this.color;
        }

        public void setColor(int i2) {
            this.color = i2;
        }

        public String toString() {
            return "Style{color=" + Integer.toHexString(this.color) + '}';
        }
    }

    public IndoorCellInfo(@NonNull Style style) {
        this.style = style;
    }

    public void addAreaId(@NonNull String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.areaIds.add(str);
    }

    @NonNull
    public List<String> getAreaIds() {
        return this.areaIds;
    }

    public Style getStyle() {
        return this.style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("IndoorCellInfo{areaIds=");
        sb.append(this.areaIds);
        sb.append(", style=");
        Style style = this.style;
        sb.append(style != null ? style.toString() : DYConstants.DY_NULL_STR);
        sb.append('}');
        return sb.toString();
    }
}
