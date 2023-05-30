package com.jdjr.risk.device.b;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes18.dex */
public class o {
    public static AtomicBoolean a = new AtomicBoolean(false);
    public static float[] b = new float[3];

    /* renamed from: c  reason: collision with root package name */
    public static float[] f7329c = new float[3];
    public static float[] d = new float[3];

    /* renamed from: e  reason: collision with root package name */
    public static float[] f7330e = new float[3];

    /* renamed from: f  reason: collision with root package name */
    public static float[] f7331f = new float[3];

    /* renamed from: g  reason: collision with root package name */
    public static float[] f7332g = new float[3];

    /* renamed from: h  reason: collision with root package name */
    public static float[] f7333h = new float[3];

    /* renamed from: i  reason: collision with root package name */
    private Timer f7334i;

    /* renamed from: j  reason: collision with root package name */
    private Context f7335j;

    /* renamed from: k  reason: collision with root package name */
    private int f7336k = 1;

    /* renamed from: l  reason: collision with root package name */
    private SensorEventListener f7337l = new SensorEventListener() { // from class: com.jdjr.risk.device.b.o.2
        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i2) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            int type = sensorEvent.sensor.getType();
            if (type == 1) {
                float[] fArr = o.b;
                float[] fArr2 = sensorEvent.values;
                fArr[0] = fArr2[0];
                fArr[1] = fArr2[1];
                fArr[2] = fArr2[2];
            } else if (type == 2) {
                float[] fArr3 = o.f7333h;
                float[] fArr4 = sensorEvent.values;
                fArr3[0] = fArr4[0];
                fArr3[1] = fArr4[1];
                fArr3[2] = fArr4[2];
            } else if (type == 3) {
                float[] fArr5 = o.d;
                float[] fArr6 = sensorEvent.values;
                fArr5[0] = fArr6[0];
                fArr5[1] = fArr6[1];
                fArr5[2] = fArr6[2];
            } else if (type == 4) {
                float[] fArr7 = o.f7330e;
                float[] fArr8 = sensorEvent.values;
                fArr7[0] = fArr8[0];
                fArr7[1] = fArr8[1];
                fArr7[2] = fArr8[2];
            } else if (type == 6) {
                o.f7332g[0] = sensorEvent.values[0];
            } else if (type == 9) {
                float[] fArr9 = o.f7331f;
                float[] fArr10 = sensorEvent.values;
                fArr9[0] = fArr10[0];
                fArr9[1] = fArr10[1];
                fArr9[2] = fArr10[2];
            } else if (type != 10) {
            } else {
                float[] fArr11 = o.f7329c;
                float[] fArr12 = sensorEvent.values;
                fArr11[0] = fArr12[0];
                fArr11[1] = fArr12[1];
                fArr11[2] = fArr12[2];
            }
        }
    };

    public o(Context context) {
        if (context != null) {
            this.f7335j = context.getApplicationContext();
        }
    }

    private void b() {
        SensorManager sensorManager;
        Context context = this.f7335j;
        if (context == null || (sensorManager = (SensorManager) context.getSystemService("sensor")) == null) {
            return;
        }
        Sensor defaultSensor = sensorManager.getDefaultSensor(1);
        if (defaultSensor != null) {
            sensorManager.registerListener(this.f7337l, defaultSensor, this.f7336k);
        }
        Sensor defaultSensor2 = sensorManager.getDefaultSensor(10);
        if (defaultSensor2 != null) {
            sensorManager.registerListener(this.f7337l, defaultSensor2, this.f7336k);
        }
        Sensor defaultSensor3 = sensorManager.getDefaultSensor(3);
        if (defaultSensor3 != null) {
            sensorManager.registerListener(this.f7337l, defaultSensor3, this.f7336k);
        }
        Sensor defaultSensor4 = sensorManager.getDefaultSensor(4);
        if (defaultSensor4 != null) {
            sensorManager.registerListener(this.f7337l, defaultSensor4, this.f7336k);
        }
        Sensor defaultSensor5 = sensorManager.getDefaultSensor(9);
        if (defaultSensor5 != null) {
            sensorManager.registerListener(this.f7337l, defaultSensor5, this.f7336k);
        }
        Sensor defaultSensor6 = sensorManager.getDefaultSensor(6);
        if (defaultSensor6 != null) {
            sensorManager.registerListener(this.f7337l, defaultSensor6, this.f7336k);
        }
        Sensor defaultSensor7 = sensorManager.getDefaultSensor(2);
        if (defaultSensor7 != null) {
            sensorManager.registerListener(this.f7337l, defaultSensor7, this.f7336k);
        }
    }

    public void a() {
        SensorManager sensorManager;
        SensorEventListener sensorEventListener;
        Context context = this.f7335j;
        if (context == null || (sensorManager = (SensorManager) context.getSystemService("sensor")) == null || (sensorEventListener = this.f7337l) == null) {
            return;
        }
        sensorManager.unregisterListener(sensorEventListener);
    }

    public void a(final String str, final String str2, final int i2, final int i3, final String str3) {
        if (this.f7335j != null) {
            com.jdjr.risk.biometric.core.e.a.get();
            final String str4 = System.currentTimeMillis() + "";
            b();
            Timer timer = new Timer();
            this.f7334i = timer;
            timer.schedule(new TimerTask
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0045: INVOKE 
                  (r10v0 'timer' java.util.Timer)
                  (wrap: java.util.TimerTask : 0x003d: CONSTRUCTOR 
                  (r16v0 'this' com.jdjr.risk.device.b.o A[IMMUTABLE_TYPE, THIS])
                  (r2 I:android.os.Bundle A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r20v0 'i3' int A[DONT_INLINE])
                  (r19v0 'i2' int A[DONT_INLINE])
                  (r17v0 'str' java.lang.String A[DONT_INLINE])
                  (r18v0 'str2' java.lang.String A[DONT_INLINE])
                  (r21v0 'str3' java.lang.String A[DONT_INLINE])
                  (r8v0 'str4' java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                 A[MD:(com.jdjr.risk.device.b.o, android.os.Bundle, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void (m), WRAPPED] call: com.jdjr.risk.device.b.o.1.<init>(com.jdjr.risk.device.b.o, android.os.Bundle, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void type: CONSTRUCTOR)
                  (200 long)
                  (r19v0 'i2' int)
                 type: VIRTUAL call: java.util.Timer.schedule(java.util.TimerTask, long, long):void A[MD:(java.util.TimerTask, long, long):void (c)] in method: com.jdjr.risk.device.b.o.a(java.lang.String, java.lang.String, int, int, java.lang.String):void, file: classes18.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                Caused by: java.lang.NullPointerException
                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                	... 23 more
                */
            /*
                this = this;
                r9 = r16
                android.content.Context r0 = r9.f7335j
                if (r0 == 0) goto L48
                java.lang.ThreadLocal<android.os.Bundle> r0 = com.jdjr.risk.biometric.core.e.a
                java.lang.Object r0 = r0.get()
                r2 = r0
                android.os.Bundle r2 = (android.os.Bundle) r2
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                long r3 = java.lang.System.currentTimeMillis()
                r0.append(r3)
                java.lang.String r1 = ""
                r0.append(r1)
                java.lang.String r8 = r0.toString()
                r16.b()
                java.util.Timer r10 = new java.util.Timer
                r10.<init>()
                r9.f7334i = r10
                com.jdjr.risk.device.b.o$1 r11 = new com.jdjr.risk.device.b.o$1
                r0 = r11
                r1 = r16
                r3 = r20
                r4 = r19
                r5 = r17
                r6 = r18
                r7 = r21
                r0.<init>()
                r12 = 200(0xc8, double:9.9E-322)
                r0 = r19
                long r14 = (long) r0
                r10.schedule(r11, r12, r14)
            L48:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jdjr.risk.device.b.o.a(java.lang.String, java.lang.String, int, int, java.lang.String):void");
        }
    }
