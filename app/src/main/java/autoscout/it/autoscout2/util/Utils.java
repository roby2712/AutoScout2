package autoscout.it.autoscout2.util;

import android.content.Context;

/**
 * Created by Roberto on 13/06/2016.
 */
public class Utils {

    public static int dpAsPx(Context context, int dp) {

        float scale = context.getResources().getDisplayMetrics().density;

        int dpAsPixels = (int) (dp * scale + 0.5f);

        return dpAsPixels;

    }

}
