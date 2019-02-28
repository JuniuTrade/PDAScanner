package trade.juniu.pdascanner;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import javax.security.auth.login.LoginException;

import trade.juniu.pda.OnScanBarcodeListener;
import trade.juniu.pda.PDAConfig;
import trade.juniu.pda.PDAScanner;
import trade.juniu.pda.PDASingle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.text);
        PDAScanner scanner = PDASingle.getScanner(this);
        scanner.setOnScanBarcodeListener(new OnScanBarcodeListener() {
            @Override
            public void onScan(String barcode) {
                Toast.makeText(getApplicationContext(), barcode, Toast.LENGTH_LONG);
                Log.e("lyd", " barcode " + barcode);
                textView.setText(barcode);
            }
        });
    }
}
