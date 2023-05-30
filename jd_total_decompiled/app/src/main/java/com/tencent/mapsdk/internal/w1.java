package com.tencent.mapsdk.internal;

import android.graphics.Bitmap;
import com.tencent.map.sdk.utilities.heatmap.Gradient;
import com.tencent.map.sdk.utilities.heatmap.HeatMapTileProvider;
import com.tencent.map.sdk.utilities.visualization.datamodels.WeightedLatLng;
import com.tencent.mapsdk.internal.ba;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Tile;
import com.tencent.tencentmap.mapsdk.maps.model.TileProvider;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes9.dex */
public class w1 extends HeatMapTileProvider {

    /* renamed from: m  reason: collision with root package name */
    private static final boolean f17418m = false;

    /* renamed from: n  reason: collision with root package name */
    private static final int f17419n = 256;
    private static final int o = 1280;
    private static final int p = 5;
    private static final int q = 11;
    private static final int r = 22;
    public static final double s = 1.0d;
    private final HeatMapTileProvider.OnHeatMapReadyListener a;
    private HeatMapTileProvider.HeatTileGenerator b;

    /* renamed from: c  reason: collision with root package name */
    private x5<x1> f17420c;
    private Collection<x1> d;

    /* renamed from: e  reason: collision with root package name */
    private n5 f17421e;

    /* renamed from: f  reason: collision with root package name */
    private int f17422f;

    /* renamed from: g  reason: collision with root package name */
    private Gradient f17423g;

    /* renamed from: h  reason: collision with root package name */
    private int[] f17424h;

    /* renamed from: i  reason: collision with root package name */
    private double[] f17425i;

    /* renamed from: j  reason: collision with root package name */
    private double f17426j;

    /* renamed from: k  reason: collision with root package name */
    private double[] f17427k;

    /* renamed from: l  reason: collision with root package name */
    private boolean f17428l;

    /* loaded from: classes9.dex */
    public class a extends ba.i<Boolean> {
        public a() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Boolean call() {
            if (w1.this.f17428l) {
                return Boolean.FALSE;
            }
            if (w1.this.b != null) {
                w1 w1Var = w1.this;
                w1Var.f17425i = w1Var.b.generateKernel(w1.this.f17422f);
            } else {
                w1 w1Var2 = w1.this;
                int i2 = w1Var2.f17422f;
                double d = w1.this.f17422f;
                Double.isNaN(d);
                w1Var2.f17425i = w1.a(i2, d / 3.0d);
            }
            w1 w1Var3 = w1.this;
            w1Var3.setGradient(w1Var3.f17423g);
            w1 w1Var4 = w1.this;
            w1Var4.b(w1Var4.d);
            w1.this.f17428l = true;
            if (w1.this.a != null) {
                w1.this.a.onHeatMapReady();
            }
            return Boolean.TRUE;
        }
    }

    public w1(HeatMapTileProvider.Builder builder) {
        this.d = c(builder.getData());
        this.f17422f = builder.getRadius();
        this.f17423g = builder.getGradient();
        this.f17426j = builder.getOpacity();
        this.a = builder.getReadyListener();
        this.b = builder.getHeatTileGenerator();
        a();
    }

    public static double a(Collection<x1> collection, n5 n5Var, int i2, int i3) {
        double d = n5Var.a;
        double d2 = n5Var.f16884c;
        double d3 = n5Var.b;
        double d4 = d2 - d;
        double d5 = n5Var.d - d3;
        if (d4 <= d5) {
            d4 = d5;
        }
        double d6 = i3 / (i2 * 2);
        Double.isNaN(d6);
        double d7 = (int) (d6 + 0.5d);
        Double.isNaN(d7);
        double d8 = d7 / d4;
        HashMap hashMap = new HashMap();
        double d9 = 0.0d;
        for (x1 x1Var : collection) {
            int i4 = (int) ((x1Var.a().a - d) * d8);
            int i5 = (int) ((x1Var.a().b - d3) * d8);
            Map map = (Map) hashMap.get(Integer.valueOf(i4));
            if (map == null) {
                map = new HashMap();
                hashMap.put(Integer.valueOf(i4), map);
            }
            Double d10 = (Double) map.get(Integer.valueOf(i5));
            if (d10 == null) {
                d10 = Double.valueOf(0.0d);
            }
            Double valueOf = Double.valueOf(d10.doubleValue() + x1Var.b());
            map.put(Integer.valueOf(i5), valueOf);
            if (valueOf.doubleValue() > d9) {
                d9 = valueOf.doubleValue();
            }
        }
        return d9;
    }

    public static Bitmap a(double[][] dArr, int[] iArr, double d) {
        int i2 = iArr[iArr.length - 1];
        double length = (double) (iArr.length - 1);
        Double.isNaN(length);
        double d2 = length / d;
        int length2 = dArr.length;
        int[] iArr2 = new int[length2 * length2];
        for (int i3 = 0; i3 < length2; i3++) {
            for (int i4 = 0; i4 < length2; i4++) {
                double d3 = dArr[i4][i3];
                int i5 = (i3 * length2) + i4;
                int i6 = (int) (d3 * d2);
                if (d3 == 0.0d) {
                    iArr2[i5] = 0;
                } else if (i6 < iArr.length) {
                    iArr2[i5] = iArr[i6];
                } else {
                    iArr2[i5] = i2;
                }
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(length2, length2, Bitmap.Config.ARGB_8888);
        createBitmap.setPixels(iArr2, 0, length2, 0, 0, length2, length2);
        return createBitmap;
    }

    public static n5 a(Collection<x1> collection) {
        Iterator<x1> it = collection.iterator();
        x1 next = it.next();
        double d = next.a().a;
        double d2 = next.a().a;
        double d3 = d;
        double d4 = d2;
        double d5 = next.a().b;
        double d6 = next.a().b;
        while (it.hasNext()) {
            x1 next2 = it.next();
            double d7 = next2.a().a;
            double d8 = next2.a().b;
            if (d7 < d3) {
                d3 = d7;
            }
            if (d7 > d4) {
                d4 = d7;
            }
            if (d8 < d5) {
                d5 = d8;
            }
            if (d8 > d6) {
                d6 = d8;
            }
        }
        return new n5(d3, d4, d5, d6);
    }

    private static Tile a(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return new Tile(256, 256, byteArrayOutputStream.toByteArray());
    }

    private void a() {
        if (this.d != null) {
            ba.a((ba.i) new a()).b((ba.d.b) Boolean.FALSE);
        }
    }

    private double[] a(int i2) {
        int i3;
        double[] dArr = new double[22];
        int i4 = 5;
        while (true) {
            if (i4 >= 11) {
                break;
            }
            dArr[i4] = a(this.d, this.f17421e, i2, (int) (Math.pow(2.0d, (double) (i4 - 3)) * 1280.0d));
            if (i4 == 5) {
                for (int i5 = 0; i5 < i4; i5++) {
                    dArr[i5] = dArr[i4];
                }
            }
            i4++;
        }
        for (i3 = 11; i3 < 22; i3++) {
            dArr[i3] = dArr[10];
        }
        return dArr;
    }

    public static double[] a(int i2, double d) {
        double[] dArr = new double[(i2 * 2) + 1];
        for (int i3 = -i2; i3 <= i2; i3++) {
            double d2 = (-i3) * i3;
            Double.isNaN(d2);
            dArr[i3 + i2] = Math.exp(d2 / ((2.0d * d) * d));
        }
        return dArr;
    }

    public static double[][] a(double[][] dArr, double[] dArr2) {
        double length = dArr2.length;
        Double.isNaN(length);
        int floor = (int) Math.floor(length / 2.0d);
        int length2 = dArr.length;
        int i2 = length2 - (floor * 2);
        int i3 = 1;
        int i4 = (floor + i2) - 1;
        double[][] dArr3 = (double[][]) Array.newInstance(double.class, length2, length2);
        int i5 = 0;
        while (true) {
            double d = 0.0d;
            if (i5 >= length2) {
                break;
            }
            int i6 = 0;
            while (i6 < length2) {
                double d2 = dArr[i5][i6];
                if (d2 != d) {
                    int i7 = i5 + floor;
                    if (i4 < i7) {
                        i7 = i4;
                    }
                    int i8 = i7 + 1;
                    int i9 = i5 - floor;
                    for (int i10 = floor > i9 ? floor : i9; i10 < i8; i10++) {
                        double[] dArr4 = dArr3[i10];
                        dArr4[i6] = dArr4[i6] + (dArr2[i10 - i9] * d2);
                    }
                }
                i6++;
                d = 0.0d;
            }
            i5++;
        }
        double[][] dArr5 = (double[][]) Array.newInstance(double.class, i2, i2);
        int i11 = floor;
        while (i11 < i4 + 1) {
            int i12 = 0;
            while (i12 < length2) {
                double d3 = dArr3[i11][i12];
                if (d3 != 0.0d) {
                    int i13 = i12 + floor;
                    if (i4 < i13) {
                        i13 = i4;
                    }
                    int i14 = i13 + i3;
                    int i15 = i12 - floor;
                    for (int i16 = floor > i15 ? floor : i15; i16 < i14; i16++) {
                        double[] dArr6 = dArr5[i11 - floor];
                        int i17 = i16 - floor;
                        dArr6[i17] = dArr6[i17] + (dArr2[i16 - i15] * d3);
                    }
                }
                i12++;
                i3 = 1;
            }
            i11++;
            i3 = 1;
        }
        return dArr5;
    }

    private static <T extends WeightedLatLng> Collection<x1> c(Collection<T> collection) {
        ArrayList arrayList = new ArrayList();
        for (T t : collection) {
            arrayList.add(new x1(t.getPoint(), t.getIntensity()));
        }
        return arrayList;
    }

    private static Collection<x1> wrapData(Collection<LatLng> collection) {
        ArrayList arrayList = new ArrayList();
        Iterator<LatLng> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(new x1(it.next()));
        }
        return arrayList;
    }

    public void b(Collection<x1> collection) {
        this.d = collection;
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("No input points.");
        }
        n5 a2 = a(this.d);
        this.f17421e = a2;
        this.f17420c = new x5<>(a2);
        Iterator<x1> it = this.d.iterator();
        while (it.hasNext()) {
            this.f17420c.a((x5<x1>) it.next());
        }
        this.f17427k = a(this.f17422f);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.TileProvider
    public Tile getTile(int i2, int i3, int i4) {
        double d;
        if (!this.f17428l) {
            ma.g("TileOverlay", "\u70ed\u529b\u56fe\u672a\u521d\u59cb\u5316\u5b8c\u6210\uff0c\u8fd4\u56de\u7a7a\u74e6\u5757");
            return TileProvider.NO_TILE;
        }
        double pow = 1.0d / Math.pow(2.0d, i4);
        int i5 = this.f17422f;
        double d2 = i5;
        Double.isNaN(d2);
        double d3 = (d2 * pow) / 256.0d;
        double d4 = (i5 * 2) + 256;
        Double.isNaN(d4);
        double d5 = ((2.0d * d3) + pow) / d4;
        double d6 = i2;
        Double.isNaN(d6);
        double d7 = (d6 * pow) - d3;
        double d8 = i2 + 1;
        Double.isNaN(d8);
        double d9 = (d8 * pow) + d3;
        double d10 = i3;
        Double.isNaN(d10);
        double d11 = (d10 * pow) - d3;
        double d12 = i3 + 1;
        Double.isNaN(d12);
        double d13 = (d12 * pow) + d3;
        Collection<x1> arrayList = new ArrayList<>();
        if (d7 < 0.0d) {
            arrayList = this.f17420c.a(new n5(d7 + 1.0d, 1.0d, d11, d13));
            d = -1.0d;
        } else if (d9 > 1.0d) {
            arrayList = this.f17420c.a(new n5(0.0d, d9 - 1.0d, d11, d13));
            d = 1.0d;
        } else {
            d = 0.0d;
        }
        n5 n5Var = new n5(d7, d9, d11, d13);
        n5 n5Var2 = this.f17421e;
        if (!n5Var.b(new n5(n5Var2.a - d3, n5Var2.f16884c + d3, n5Var2.b - d3, n5Var2.d + d3))) {
            ma.g("TileOverlay", "\u70ed\u529b\u56fe\u8d85\u51fa\u6709\u6548\u8fb9\u754c\uff0c\u8fd4\u56de\u7a7a\u74e6\u5757-" + i2 + ":" + i3 + ":" + i4);
            return TileProvider.NO_TILE;
        }
        Collection<x1> a2 = this.f17420c.a(n5Var);
        if (a2.isEmpty()) {
            ma.g("TileOverlay", "\u70ed\u529b\u56fe\u6ca1\u6709\u70ed\u529b\u6570\u636e\uff0c\u8fd4\u56de\u7a7a\u74e6\u5757-" + i2 + ":" + i3 + ":" + i4);
            return TileProvider.NO_TILE;
        }
        int i6 = (this.f17422f * 2) + 256;
        double[][] dArr = (double[][]) Array.newInstance(double.class, i6, i6);
        for (x1 x1Var : a2) {
            o5 a3 = x1Var.a();
            int i7 = (int) ((a3.b - d11) / d5);
            double[] dArr2 = dArr[(int) ((a3.a - d7) / d5)];
            dArr2[i7] = dArr2[i7] + x1Var.b();
        }
        for (x1 x1Var2 : arrayList) {
            o5 a4 = x1Var2.a();
            int i8 = (int) ((a4.b - d11) / d5);
            double[] dArr3 = dArr[(int) (((a4.a + d) - d7) / d5)];
            dArr3[i8] = dArr3[i8] + x1Var2.b();
        }
        return a(a(a(dArr, this.f17425i), this.f17424h, this.f17427k[i4]));
    }

    @Override // com.tencent.map.sdk.utilities.heatmap.HeatMapTileProvider
    public void setData(Collection<LatLng> collection) {
        b(wrapData(collection));
    }

    @Override // com.tencent.map.sdk.utilities.heatmap.HeatMapTileProvider
    public void setGradient(Gradient gradient) {
        this.f17423g = gradient;
        HeatMapTileProvider.HeatTileGenerator heatTileGenerator = this.b;
        if (heatTileGenerator != null) {
            this.f17424h = heatTileGenerator.generateColorMap(this.f17426j);
        } else {
            this.f17424h = gradient.generateColorMap(this.f17426j);
        }
    }

    @Override // com.tencent.map.sdk.utilities.heatmap.HeatMapTileProvider
    public void setHeatTileGenerator(HeatMapTileProvider.HeatTileGenerator heatTileGenerator) {
        this.b = heatTileGenerator;
        if (heatTileGenerator != null) {
            this.f17425i = heatTileGenerator.generateKernel(this.f17422f);
            this.f17424h = this.b.generateColorMap(this.f17426j);
        }
    }

    @Override // com.tencent.map.sdk.utilities.heatmap.HeatMapTileProvider
    public void setOpacity(double d) {
        this.f17426j = d;
        setGradient(this.f17423g);
    }

    @Override // com.tencent.map.sdk.utilities.heatmap.HeatMapTileProvider
    public void setRadius(int i2) {
        if (i2 <= 0) {
            return;
        }
        this.f17422f = i2;
        HeatMapTileProvider.HeatTileGenerator heatTileGenerator = this.b;
        if (heatTileGenerator != null) {
            this.f17425i = heatTileGenerator.generateKernel(i2);
        } else {
            double d = i2;
            Double.isNaN(d);
            this.f17425i = a(i2, d / 3.0d);
        }
        this.f17427k = a(this.f17422f);
    }

    @Override // com.tencent.map.sdk.utilities.heatmap.HeatMapTileProvider
    public <T extends WeightedLatLng> void setWeightedData(Collection<T> collection) {
        b(c(collection));
    }
}
