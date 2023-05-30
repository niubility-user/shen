package com.airbnb.lottie.model;

import androidx.annotation.RestrictTo;
import com.airbnb.lottie.model.content.ShapeGroup;
import java.util.List;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class FontCharacter {
    private final char character;
    private final String fontFamily;
    private final List<ShapeGroup> shapes;
    private final double size;
    private final String style;
    private final double width;

    public FontCharacter(List<ShapeGroup> list, char c2, double d, double d2, String str, String str2) {
        this.shapes = list;
        this.character = c2;
        this.size = d;
        this.width = d2;
        this.style = str;
        this.fontFamily = str2;
    }

    public static int hashFor(char c2, String str, String str2) {
        return ((((0 + c2) * 31) + str.hashCode()) * 31) + str2.hashCode();
    }

    public List<ShapeGroup> getShapes() {
        return this.shapes;
    }

    double getSize() {
        return this.size;
    }

    String getStyle() {
        return this.style;
    }

    public double getWidth() {
        return this.width;
    }

    public int hashCode() {
        return hashFor(this.character, this.fontFamily, this.style);
    }
}
