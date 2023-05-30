package tv.danmaku.ijk.media.alpha.mix;

import android.os.Build;
import java.util.ArrayList;
import java.util.Comparator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class FrameSet {
    private int index;
    private ArrayList<Frame> list = new ArrayList<>();

    public FrameSet(JSONObject jSONObject) {
        this.index = 0;
        try {
            this.index = jSONObject.getInt("i");
            JSONArray jSONArray = jSONObject.getJSONArray("obj");
            int length = jSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                this.list.add(new Frame(this.index, jSONArray.getJSONObject(i2)));
            }
            if (Build.VERSION.SDK_INT >= 24) {
                this.list.sort(new Comparator() { // from class: tv.danmaku.ijk.media.alpha.mix.a
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        return FrameSet.a((Frame) obj, (Frame) obj2);
                    }
                });
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int a(Frame frame, Frame frame2) {
        if (frame.getZ() == frame2.getZ()) {
            return 0;
        }
        return frame.getZ() > frame2.getZ() ? 1 : -1;
    }

    public int getIndex() {
        return this.index;
    }

    public ArrayList<Frame> getList() {
        return this.list;
    }
}
