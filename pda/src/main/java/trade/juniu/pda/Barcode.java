package trade.juniu.pda;

import android.os.Handler;

/**
 * @author lyd
 * @date 2019/2/28 0028 10:29
 * @desription
 */
public abstract class Barcode {

    protected Handler mHandler = null;

    public abstract void init();

    public void setHandler(Handler handler) {
        this.mHandler = handler;
    }
}
