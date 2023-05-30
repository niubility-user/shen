package com.jingdong.common.unification.video.controller;

/* loaded from: classes6.dex */
public class ItemPlayerController {
    private static ItemPlayerController controller;
    private boolean abandonAudioFocus = true;

    private ItemPlayerController() {
    }

    public static ItemPlayerController getController() {
        ItemPlayerController itemPlayerController = controller;
        if (itemPlayerController != null) {
            return itemPlayerController;
        }
        synchronized (ItemPlayerController.class) {
            if (controller == null) {
                controller = new ItemPlayerController();
            }
        }
        return controller;
    }

    public boolean isAbandonAudioFocus() {
        return this.abandonAudioFocus;
    }

    public void setAbandonAudioFocus(boolean z) {
        this.abandonAudioFocus = z;
    }
}
