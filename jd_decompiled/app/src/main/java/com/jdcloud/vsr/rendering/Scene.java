package com.jdcloud.vsr.rendering;

import com.jdcloud.vsr.JDTBitmap;
import com.jdcloud.vsr.JDTObject;
import com.jdcloud.vsr.geometry.AffineMapping;
import com.jdcloud.vsr.imaging.Color;
import com.jdcloud.vsr.shading.ImageShader;

/* loaded from: classes18.dex */
public class Scene extends JDTObject {

    /* loaded from: classes18.dex */
    public static class BitmapLayer extends Layer {
        private JDTBitmap bitmap;

        public JDTBitmap getBitmap() {
            return this.bitmap;
        }

        public AffineMapping getImageTransform() {
            AffineMapping affineMapping = new AffineMapping();
            this.scene.getBitmapLayerImageTransform(this.handle, affineMapping);
            return affineMapping;
        }

        public synchronized void recycleBitmap() {
            this.scene.setBitmapLayerBitmap(this.handle, null);
            JDTBitmap jDTBitmap = this.bitmap;
            this.bitmap = null;
            JDTBitmap.recycle(jDTBitmap);
        }

        public void setBitmap(JDTBitmap jDTBitmap) {
            this.bitmap = jDTBitmap;
            this.scene.setBitmapLayerBitmap(this.handle, jDTBitmap);
        }

        public void setImageTransform(AffineMapping affineMapping) {
            this.scene.setBitmapLayerImageTransform(this.handle, affineMapping.x, affineMapping.y, affineMapping.a11, affineMapping.a12, affineMapping.a21, affineMapping.a22);
        }

        public void setModulationColor(Color color) {
            this.scene.setBitmapLayerModulationColor(this.handle, color.r / 255.0f, color.f7275g / 255.0f, color.b / 255.0f, color.a / 255.0f);
        }

        private BitmapLayer(Scene scene) {
            super(scene);
            this.handle = scene.newBitmapLayer(((JDTObject) scene).handle, this);
        }

        public void getImageTransform(AffineMapping affineMapping) {
            this.scene.getBitmapLayerImageTransform(this.handle, affineMapping);
        }
    }

    /* loaded from: classes18.dex */
    public static class CustomMaskedBitmapLayer extends BitmapLayer {
        public Color getBackgroundColor() {
            Color color = new Color();
            this.scene.getBitmapLayerBgColor(this.handle, color);
            return color;
        }

        public AffineMapping getMaskTransform() {
            AffineMapping affineMapping = new AffineMapping();
            this.scene.getBitmapLayerMaskTransform(this.handle, affineMapping);
            return affineMapping;
        }

        public void rotateMask(float f2) {
            this.scene.rotateBitmapLayerMask(this.handle, f2);
        }

        public void scaleMask(float f2, float f3) {
            this.scene.scaleBitmapLayerMask(this.handle, f2, f3);
        }

        public void setBackgroundColor(Color color) {
            this.scene.setBitmapLayerBgColor(this.handle, color.r / 255.0f, color.f7275g / 255.0f, color.b / 255.0f, color.a / 255.0f);
        }

        public void setMaskPosition(float f2, float f3) {
            this.scene.setBitmapLayerMaskPos(this.handle, f2, f3);
        }

        public void setMaskTransform(AffineMapping affineMapping) {
            this.scene.setBitmapLayerMaskTransform(this.handle, affineMapping.x, affineMapping.y, affineMapping.a11, affineMapping.a12, affineMapping.a21, affineMapping.a22);
        }

        public void skewMask(float f2, float f3) {
            this.scene.skewBitmapLayerMask(this.handle, f2, f3);
        }

        private CustomMaskedBitmapLayer(Scene scene) {
            super();
        }

        public void getMaskTransform(AffineMapping affineMapping) {
            this.scene.getBitmapLayerMaskTransform(this.handle, affineMapping);
        }
    }

    /* loaded from: classes18.dex */
    public static class MaskedBitmapLayer extends CustomMaskedBitmapLayer {
        private JDTBitmap mask;

        public JDTBitmap getMask() {
            return this.mask;
        }

        public void setMask(JDTBitmap jDTBitmap) {
            this.mask = jDTBitmap;
            this.scene.setMaskedBitmapLayerMask(this.handle, jDTBitmap);
        }

        private MaskedBitmapLayer(Scene scene) {
            super();
            this.handle = scene.newMaskedBitmapLayer(((JDTObject) scene).handle, this);
        }
    }

    /* loaded from: classes18.dex */
    public static class SceneLayer extends Layer {
        private Scene subscene;

        public Scene getScene() {
            return this.subscene;
        }

        private SceneLayer(Scene scene, Scene scene2) {
            super(scene);
            this.subscene = scene2;
            this.handle = scene.newSceneLayer(((JDTObject) scene).handle, this, scene2);
        }
    }

    /* loaded from: classes18.dex */
    public static class ShadedBitmapLayer extends BitmapLayer {
        private ImageShader shader;

        public ImageShader getShader() {
            return this.shader;
        }

        public void setShader(ImageShader imageShader) {
            this.shader = imageShader;
            this.scene.setShadedBitmapLayerShader(this.handle, imageShader);
        }

        private ShadedBitmapLayer(Scene scene) {
            super();
            this.handle = scene.newShadedBitmapLayer(((JDTObject) scene).handle, this);
        }
    }

    /* loaded from: classes18.dex */
    public static class ShapedBitmapLayer extends CustomMaskedBitmapLayer {
        public float getBorderWidth() {
            return this.scene.getShapedBitmapLayerBorderWidth(this.handle);
        }

        public float getCornerRadius() {
            return this.scene.getShapedBitmapLayerCornerRadius(this.handle);
        }

        public boolean getInPixels() {
            return this.scene.getShapedBitmapLayerInPixelsSwitch(this.handle);
        }

        public float getSlopeWidth() {
            return this.scene.getShapedBitmapLayerSlopeWidth(this.handle);
        }

        public void setBorderWidth(float f2) {
            this.scene.setShapedBitmapLayerBorderWidth(this.handle, f2);
        }

        public void setCornerRadius(float f2) {
            this.scene.setShapedBitmapLayerCornerRadius(this.handle, f2);
        }

        public void setInPixels(boolean z) {
            this.scene.setShapedBitmapLayerInPixelsSwitch(this.handle, z);
        }

        public void setSlopeWidth(float f2) {
            this.scene.setShapedBitmapLayerSlopeWidth(this.handle, f2);
        }

        private ShapedBitmapLayer(Scene scene) {
            super();
            this.handle = scene.newShapedBitmapLayer(((JDTObject) scene).handle, this);
        }
    }

    public Scene() {
        super(newScene());
    }

    private native void deleteLayers();

    /* JADX INFO: Access modifiers changed from: private */
    public native void getBitmapLayerBgColor(long j2, Color color);

    /* JADX INFO: Access modifiers changed from: private */
    public native void getBitmapLayerImageTransform(long j2, AffineMapping affineMapping);

    /* JADX INFO: Access modifiers changed from: private */
    public native void getBitmapLayerMaskTransform(long j2, AffineMapping affineMapping);

    private native void getBitmapLayerModulationColor(long j2, Color color);

    private native Layer getLayerAtPoint(long j2, float f2, float f3);

    private native Layer getLayerByIndex(long j2, int i2);

    private native Layer getLayerByName(long j2, String str);

    private native int getLayerCount(long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public native String getLayerName(long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public native float getLayerOrientation(long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean getLayerPhantomFlag(long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public native void getLayerTransform(long j2, AffineMapping affineMapping);

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean getLayerVisibility(long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public native float getLayerX(long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public native float getLayerY(long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public native float getShapedBitmapLayerBorderWidth(long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public native float getShapedBitmapLayerCornerRadius(long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean getShapedBitmapLayerInPixelsSwitch(long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public native float getShapedBitmapLayerSlopeWidth(long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public native long newBitmapLayer(long j2, BitmapLayer bitmapLayer);

    /* JADX INFO: Access modifiers changed from: private */
    public native long newMaskedBitmapLayer(long j2, MaskedBitmapLayer maskedBitmapLayer);

    private static native long newScene();

    /* JADX INFO: Access modifiers changed from: private */
    public native long newSceneLayer(long j2, SceneLayer sceneLayer, Scene scene);

    /* JADX INFO: Access modifiers changed from: private */
    public native long newShadedBitmapLayer(long j2, ShadedBitmapLayer shadedBitmapLayer);

    /* JADX INFO: Access modifiers changed from: private */
    public native long newShapedBitmapLayer(long j2, ShapedBitmapLayer shapedBitmapLayer);

    /* JADX INFO: Access modifiers changed from: private */
    public native void rotateBitmapLayerMask(long j2, float f2);

    /* JADX INFO: Access modifiers changed from: private */
    public native void rotateLayer(long j2, float f2, float f3, float f4);

    /* JADX INFO: Access modifiers changed from: private */
    public native void scaleBitmapLayerMask(long j2, float f2, float f3);

    /* JADX INFO: Access modifiers changed from: private */
    public native void scaleLayer(long j2, float f2, float f3, float f4);

    /* JADX INFO: Access modifiers changed from: private */
    public native void setBitmapLayerBgColor(long j2, float f2, float f3, float f4, float f5);

    /* JADX INFO: Access modifiers changed from: private */
    public native void setBitmapLayerBitmap(long j2, JDTBitmap jDTBitmap);

    /* JADX INFO: Access modifiers changed from: private */
    public native void setBitmapLayerImageTransform(long j2, float f2, float f3, float f4, float f5, float f6, float f7);

    /* JADX INFO: Access modifiers changed from: private */
    public native void setBitmapLayerMaskPos(long j2, float f2, float f3);

    /* JADX INFO: Access modifiers changed from: private */
    public native void setBitmapLayerMaskTransform(long j2, float f2, float f3, float f4, float f5, float f6, float f7);

    /* JADX INFO: Access modifiers changed from: private */
    public native void setBitmapLayerModulationColor(long j2, float f2, float f3, float f4, float f5);

    /* JADX INFO: Access modifiers changed from: private */
    public native void setLayerCenterPos(long j2, float f2, float f3);

    /* JADX INFO: Access modifiers changed from: private */
    public native void setLayerName(long j2, String str);

    /* JADX INFO: Access modifiers changed from: private */
    public native void setLayerPhantomFlag(long j2, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public native void setLayerTransform(long j2, float f2, float f3, float f4, float f5, float f6, float f7);

    /* JADX INFO: Access modifiers changed from: private */
    public native void setLayerVisibility(long j2, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public native void setLayerX(long j2, float f2);

    /* JADX INFO: Access modifiers changed from: private */
    public native void setLayerY(long j2, float f2);

    /* JADX INFO: Access modifiers changed from: private */
    public native void setMaskedBitmapLayerMask(long j2, JDTBitmap jDTBitmap);

    /* JADX INFO: Access modifiers changed from: private */
    public native void setShadedBitmapLayerShader(long j2, ImageShader imageShader);

    /* JADX INFO: Access modifiers changed from: private */
    public native void setShapedBitmapLayerBorderWidth(long j2, float f2);

    /* JADX INFO: Access modifiers changed from: private */
    public native void setShapedBitmapLayerCornerRadius(long j2, float f2);

    /* JADX INFO: Access modifiers changed from: private */
    public native void setShapedBitmapLayerInPixelsSwitch(long j2, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public native void setShapedBitmapLayerSlopeWidth(long j2, float f2);

    /* JADX INFO: Access modifiers changed from: private */
    public native void skewBitmapLayerMask(long j2, float f2, float f3);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jdcloud.vsr.JDTObject
    public void finalize() throws Throwable {
        deleteLayers();
        super.finalize();
    }

    public Layer getLayer(int i2) {
        return getLayerByIndex(this.handle, i2);
    }

    public int getLayerCount() {
        return getLayerCount(this.handle);
    }

    public BitmapLayer newBitmapLayer() {
        return new BitmapLayer();
    }

    public MaskedBitmapLayer newMaskedBitmapLayer() {
        return new MaskedBitmapLayer();
    }

    public SceneLayer newSceneLayer(Scene scene) {
        return new SceneLayer(scene);
    }

    public ShadedBitmapLayer newShadedBitmapLayer() {
        return new ShadedBitmapLayer();
    }

    public ShapedBitmapLayer newShapedBitmapLayer() {
        return new ShapedBitmapLayer();
    }

    /* loaded from: classes18.dex */
    public static class Layer {
        protected long handle;
        protected Scene scene;

        protected Layer(Scene scene) {
            this.scene = scene;
        }

        public boolean equals(Object obj) {
            return (obj instanceof Layer) && this.handle == ((Layer) obj).handle;
        }

        public String getName() {
            return this.scene.getLayerName(this.handle);
        }

        public float getOrientation() {
            return this.scene.getLayerOrientation(this.handle);
        }

        public boolean getPhantom() {
            return this.scene.getLayerPhantomFlag(this.handle);
        }

        public AffineMapping getTransform() {
            AffineMapping affineMapping = new AffineMapping();
            this.scene.getLayerTransform(this.handle, affineMapping);
            return affineMapping;
        }

        public boolean getVisibility() {
            return this.scene.getLayerVisibility(this.handle);
        }

        public float getX() {
            return this.scene.getLayerX(this.handle);
        }

        public float getY() {
            return this.scene.getLayerY(this.handle);
        }

        public void hide() {
            setVisibility(false);
        }

        public void rotate(float f2) {
            this.scene.rotateLayer(this.handle, f2, 0.0f, 0.0f);
        }

        public void scale(float f2) {
            this.scene.scaleLayer(this.handle, f2, 0.0f, 0.0f);
        }

        public void setCenterPosition(float f2, float f3) {
            this.scene.setLayerCenterPos(this.handle, f2, f3);
        }

        public void setName(String str) {
            this.scene.setLayerName(this.handle, str);
        }

        public void setPhantom(boolean z) {
            this.scene.setLayerPhantomFlag(this.handle, z);
        }

        public void setPosition(float f2, float f3) {
            this.scene.setLayerX(this.handle, f2);
            this.scene.setLayerY(this.handle, f3);
        }

        public void setTransform(AffineMapping affineMapping) {
            this.scene.setLayerTransform(this.handle, affineMapping.x, affineMapping.y, affineMapping.a11, affineMapping.a12, affineMapping.a21, affineMapping.a22);
        }

        public void setVisibility(boolean z) {
            this.scene.setLayerVisibility(this.handle, z);
        }

        public void setX(float f2) {
            this.scene.setLayerX(this.handle, f2);
        }

        public void setY(float f2) {
            this.scene.setLayerY(this.handle, f2);
        }

        public void show() {
            setVisibility(true);
        }

        public void getTransform(AffineMapping affineMapping) {
            this.scene.getLayerTransform(this.handle, affineMapping);
        }
    }

    public Layer getLayer(String str) {
        return getLayerByName(this.handle, str);
    }

    public Layer getLayer(float f2, float f3) {
        return getLayerAtPoint(this.handle, f2, f3);
    }
}
