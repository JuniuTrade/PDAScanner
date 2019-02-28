package trade.juniu.pda;

import android.os.Handler;

/**
 * @author lyd
 * @date 2019/2/27 0027 17:53
 * @desription
 */
public interface IScanner {

    /**
     * 初始化
     */
    void init();

    /**
     * 设置handler
     *
     * @param handler
     */
    void setHandler(Handler handler);
}
