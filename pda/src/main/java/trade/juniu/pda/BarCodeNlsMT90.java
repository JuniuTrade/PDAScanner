package trade.juniu.pda;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * @author yfl
 * @date
 * @description 新大陆MT90的PDA设备
 */
public class BarCodeNlsMT90 extends Barcode {
    /**
     * 广播需要的标识
     */
    private final String ACTION = "ACTION_BAR_SCANCFG";

    /**
     * 获取广播数据所需要的key
     */
    private final String KEY = "nlscan.action.SCANNER_RESULT";

    private Context mContext;

    public BarCodeNlsMT90(Context context) {
        this.mContext = context;
        //注册广播
        DeviceMT90BroadCastReceiver receiver = new DeviceMT90BroadCastReceiver();
        mContext.registerReceiver(receiver, new IntentFilter(KEY));
        init();
    }

    @Override
    public void init() {
        //设置使用广播接收
        Intent intent = new Intent(ACTION);
        intent.putExtra("EXTRA_SCAN_MODE", 3);
        mContext.sendBroadcast(intent);

    }

    class DeviceMT90BroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (onScanBarcodeListener == null) {
                return;
            }
            String action = intent.getAction();
            if (KEY.equals(action)) {
                //扫描得到的数据
                final String scanResult_1 = intent.getStringExtra("SCAN_BARCODE1");
                final String scanResult_2 = intent.getStringExtra("SCAN_BARCODE2");
                final int barcodeType = intent.getIntExtra("SCAN_BARCODE_TYPE", -1); // -1:unknown
                final String scanStatus = intent.getStringExtra("SCAN_STATE");
                if ("ok".equals(scanStatus)) {
                    //成功
                    onScanBarcodeListener.onScan(scanResult_1);
                } else {
                    //失败如超时等
                    return;
                }

            }
        }
    }
}
