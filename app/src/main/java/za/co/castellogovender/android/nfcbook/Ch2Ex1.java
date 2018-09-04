package za.co.castellogovender.android.nfcbook;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Ch2Ex1 extends AppCompatActivity {

    private NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch2_ex1);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

    }

    @Override
    protected void onNewIntent(Intent intent) {

        Toast.makeText(this, "NFC intent received", Toast.LENGTH_SHORT).show();

        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = new Intent(this, MainActivity.class).addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        IntentFilter[] intentFilter = new IntentFilter[] { };

        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter, null);
    }

    @Override
    protected void onPause() {
        super.onPause();

        nfcAdapter.disableForegroundDispatch(this);
    }

}
