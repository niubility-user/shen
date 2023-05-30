package com.jd.manto.map;

import android.graphics.Bitmap;
import java.util.List;

/* loaded from: classes17.dex */
final class Beans {

    /* loaded from: classes17.dex */
    static final class Anchor {
        public float x;
        public float y;

        public Anchor(float f2, float f3) {
            this.x = f2;
            this.y = f3;
        }
    }

    /* loaded from: classes17.dex */
    static final class CallOut {
        public int anchorX;
        public int anchorY;
        public int bgColor;
        public int borderColor;
        public int borderRadius;
        public int borderWidth;
        public int color;
        public String content;
        public int display;
        public int fontSize;
        public int padding;
        public String textAlign;
    }

    /* loaded from: classes17.dex */
    static final class CircleInfo {
        public int fillColor;
        public double latitude;
        public double longitude;
        public int radius;
        public int strokeColor;
        public float strokeWidth;
    }

    /* loaded from: classes17.dex */
    static final class ControlInfo {
        public boolean clickable;
        public String data;
        public Bitmap icon;
        public String iconPath;
        public int id;
        public Position position;
    }

    /* loaded from: classes17.dex */
    static final class CoverInfo {
        public String iconPath;
        public double latitude;
        public double longitude;
        public float rotate;

        public CoverInfo(double d, double d2, String str, float f2) {
            this.latitude = d;
            this.longitude = d2;
            this.iconPath = str;
            this.rotate = f2;
        }
    }

    /* loaded from: classes17.dex */
    static final class Label {
        public float anchorX;
        public float anchorY;
        public int bgColor;
        public int borderColor;
        public int borderRadius;
        public int borderWidth;
        public int color;
        public String content;
        public int fontSize;
        public int padding;
        public String textAlign;
    }

    /* loaded from: classes17.dex */
    static final class LineInfo {
        public String arrowIconPath;
        public boolean arrowLine;
        public int borderColor;
        public int borderWidth;
        public int color;
        public boolean dottedLine;
        public List<Point> points;
        public int width;
    }

    /* loaded from: classes17.dex */
    static final class MapInfo {
        public double centerLatitude;
        public double centerLongitude;
        public List<CircleInfo> circleInfos;
        public List<ControlInfo> controlInfos;
        public List<CoverInfo> coverInfos;
        public boolean enable3D;
        public boolean enableOverlooking;
        public boolean enableRotate;
        public boolean enableSatellite;
        public boolean enableScroll;
        public boolean enableTraffic;
        public boolean enableZoom;
        public List<Point> includePoints;
        public int mapId;
        public List<MarkInfo> markers;
        public List<LineInfo> polylines;
        public int scale;
        public boolean showCompass;
        public boolean showLocation;
        public String theme;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public static final class MarkInfo {
        public float alpha;
        public Anchor anchor;
        public String aria_label;
        public CallOut callOut;
        public String data;
        public float height;
        public String iconPath;
        public int id;
        public Label label;
        public double latitude;
        public double longitude;
        public float rotate;
        public String title;
        public float width;
        public int zIndex;
    }

    /* loaded from: classes17.dex */
    static final class Point {
        public double latitude;
        public double longitude;

        public Point(double d, double d2) {
            this.latitude = d;
            this.longitude = d2;
        }

        public final boolean equals(Object obj) {
            if (obj instanceof Point) {
                Point point2 = (Point) obj;
                return this.latitude == point2.latitude && this.longitude == point2.longitude;
            }
            return false;
        }
    }

    /* loaded from: classes17.dex */
    static final class Position {
        public int height;
        public int left;
        public int top;
        public int width;

        public Position(int i2, int i3, int i4, int i5) {
            this.left = i2;
            this.top = i3;
            this.width = i4;
            this.height = i5;
        }
    }

    /* loaded from: classes17.dex */
    static final class TranslateMarkerInfo {
        public int duration;
        public double latitude;
        public double longitude;
        public double oldlatitude;
        public double oldlongitude;
        public float rotate;
    }

    /* loaded from: classes17.dex */
    static final class UpdateMapInfo {
        public boolean updateCoordinate = false;
        public boolean updateZoom = false;
        public boolean updateScroll = false;
        public boolean updateShowCompass = false;
        public boolean updateRotate = false;
        public boolean update3D = false;
        public boolean updateSatellite = false;
        public boolean updateTraffic = false;
        public boolean updateTheme = false;
        public boolean updateLoc = false;
        public boolean updateMarkers = false;
        public boolean updateCtrls = false;
        public boolean updateCircles = false;
        public boolean updateLine = false;
        public boolean updatePoints = false;
    }

    Beans() {
    }
}
