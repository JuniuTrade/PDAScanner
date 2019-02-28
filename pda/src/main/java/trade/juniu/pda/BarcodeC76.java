package trade.juniu.pda;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Message;
import android.text.TextUtils;

import com.barcode.BarcodeUtility;

/**
 * @author lyd
 * @date 2019/2/28 0028 10:39
 * @desription
 */
public class BarcodeC76 extends Barcode {

    /**
     * 广播需要的标识
     */
    private final String ACTION = "trade.juniu.pda.c76";

    /**
     * 获取广播数据所需要的key
     */
    private final String KEY = "C76";

    private Context mContext;

    public BarcodeC76(Context context) {
        this.mContext = context;
        //注册广播
        DeviceC76BroadCastReceiver receiver = new DeviceC76BroadCastReceiver();
        mContext.registerReceiver(receiver, new IntentFilter(ACTION));
        init();
    }

    @Override
    public void init() {
        BarcodeUtility barcodeUtility = BarcodeUtility.getInstance();
        barcodeUtility.setOutputMode(mContext, 2);//设置广播接收数据
        barcodeUtility.setScanResultBroadcast(mContext, ACTION, KEY);//设置接收数据的广播
        barcodeUtility.open(mContext, BarcodeUtility.ModuleType.BARCODE_2D);//打开2D
        barcodeUtility.setReleaseScan(mContext, true);//设置松开扫描按键，不停止扫描
        barcodeUtility.setScanFailureBroadcast(mContext, false);//扫描失败也发送广播
        barcodeUtility.enableContinuousScan(mContext, false);//关闭键盘助手连续扫描
        barcodeUtility.enablePlayFailureSound(mContext, false);//关闭键盘助手 扫描失败的声音
        barcodeUtility.enablePlaySuccessSound(mContext, true);//关闭键盘助手 扫描成功的声音
        barcodeUtility.enableEnter(mContext, false);//关闭回车
    }

    class DeviceC76BroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (mHandler == null) {
                return;
            }
            String action = intent.getAction();
            if (ACTION.equals(action)) {
                //扫描得到的数据
                String reason = intent.getStringExtra(KEY);
                if (TextUtils.isEmpty(reason)) {
                    return;
                }
                Message m = Message.obtain(mHandler, PDAConfig.BARCODE_READ);
                m.obj = reason;
                mHandler.sendMessage(m);
            }
        }
    }
}
