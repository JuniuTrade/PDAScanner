package trade.juniu.pda;

import android.content.Context;

/**
 * @author lyd
 * @date 2019/2/28 0028 13:50
 * @desription
 */
public class PDASingle {

    private static volatile PDASingle instance = null;

    private PDAScanner mScanner;

    private PDASingle(Context context) {
        mScanner = new PDAScanner(context.getApplicationContext());
    }

    private static PDASingle getInstance(Context context) {
        if (instance == null) {
            synchronized (PDASingle.class) {
                if (instance == null) {
                    instance = new PDASingle(context);
                }
            }
        }
        return instance;
    }

    public static PDAScanner getScanner(Context context) {
        return getInstance(context).mScanner;
    }

}
