package com.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import com.coremedia.iso.Hex;
import com.coremedia.iso.IsoTypeWriter;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Logger;
import jd.wjweblogin.d.c;

@Descriptor(tags = {19, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, R2.anim.lib_cashier_sdk_fragment_right_out, R2.anim.lib_cashier_sdk_pop_in_animation_bottom, R2.anim.lib_cashier_sdk_pop_out_animation_bottom, R2.anim.live_pop_rotate_amin, 140, R2.anim.manto_push_right_in, R2.anim.manto_translate_dialog_in, R2.anim.manto_translate_dialog_out, 144, R2.anim.message_center_dialog_out, R2.anim.miaosha_dropdown_in, R2.anim.miaosha_dropdown_out, R2.anim.mtrl_bottom_sheet_slide_in, R2.anim.mtrl_bottom_sheet_slide_out, 150, 151, 152, 153, 154, 155, R2.anim.out_to_bottom_slow, R2.anim.out_to_right, R2.anim.pickerview_dialog_scale_in, R2.anim.pickerview_dialog_scale_out, 160, 161, 162, R2.anim.pop_center_out, R2.anim.pop_in, R2.anim.pop_left_bottom_in, R2.anim.pop_left_bottom_out, R2.anim.pop_left_top_in, R2.anim.pop_left_top_out, 169, 170, R2.anim.pop_right_bottom_out, R2.anim.pop_right_top_in, R2.anim.pop_right_top_out, R2.anim.popdown_anim_feedback, R2.anim.popup_anim_feedback, R2.anim.popup_center_enter, 177, 178, R2.anim.popwin_anim_alpha_out, 180, R2.anim.push_right_out, R2.anim.settlement_dialog_bottom_enter, R2.anim.settlement_dialog_bottom_exit, R2.anim.settlement_dialog_right_enter, R2.anim.settlement_dialog_right_exit, R2.anim.slide_down, R2.anim.slide_in_from_bottom, R2.anim.slide_in_from_bottom_medium_time, R2.anim.slide_in_from_left, R2.anim.slide_in_from_top, R2.anim.slide_in_from_top_medium_time, 192, R2.anim.slide_out_from_left, R2.anim.slide_out_to_bottom, R2.anim.slide_out_to_bottom_medium_time, R2.anim.slide_out_to_right, R2.anim.slide_out_to_top, R2.anim.slide_out_to_top_medium_time, R2.anim.slide_out_top_dongdong, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253})
/* loaded from: classes12.dex */
public class ExtensionDescriptor extends BaseDescriptor {
    private static Logger log = Logger.getLogger(ExtensionDescriptor.class.getName());
    ByteBuffer data;

    static int[] allTags() {
        int[] iArr = new int[R2.anim.mtrl_bottom_sheet_slide_in];
        for (int i2 = 106; i2 < 254; i2++) {
            int i3 = i2 + c.f20058m;
            log.finest("pos:" + i3);
            iArr[i3] = i2;
        }
        return iArr;
    }

    @Override // com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    int getContentSize() {
        return this.data.remaining();
    }

    @Override // com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public void parseDetail(ByteBuffer byteBuffer) throws IOException {
        this.data = byteBuffer.slice();
        byteBuffer.position(byteBuffer.position() + this.data.remaining());
    }

    @Override // com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public ByteBuffer serialize() {
        ByteBuffer allocate = ByteBuffer.allocate(getSize());
        IsoTypeWriter.writeUInt8(allocate, this.tag);
        writeSize(allocate, getContentSize());
        allocate.put(this.data.duplicate());
        return allocate;
    }

    @Override // com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor
    public String toString() {
        return "ExtensionDescriptortag=" + this.tag + ",bytes=" + Hex.encodeHex(this.data.array()) + '}';
    }
}
