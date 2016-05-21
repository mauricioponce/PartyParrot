package parrot.party.partyparrot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.github.lzyzsd.randomcolor.RandomColor;

import in.excogitation.lib.sensey.Sensey;
import in.excogitation.lib.sensey.ShakeDetector;
import io.github.sporklibrary.Spork;
import io.github.sporklibrary.annotations.BindLayout;
import io.github.sporklibrary.annotations.BindView;


/**
 * The party parrot that changes color when you shake the phone built with libraries.
 *
 * http://cultofthepartyparrot.com/
 *
 * http://sporklibrary.github.io
 * https://github.com/nisrulz/Sensey
 * https://github.com/lzyzsd/AndroidRandomColor
 * https://github.com/koral--/android-gif-drawable/tree/master/src/main/java/pl/droidsonroids/gif
 */
@BindLayout(R.layout.activity_parrot)
public class ParrotActivity extends AppCompatActivity {

    final RandomColor randomColor = new RandomColor();

    @BindView(R.id.party_layout)
    RelativeLayout partyLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Spork.bind(this);

        startListener();
    }

    private void startListener() {
        Sensey.getInstance().init(getApplicationContext());

        Sensey.getInstance().startShakeDetection(new ShakeDetector.ShakeListener() {
            @Override
            public void onShakeDetected() {
                //we do the sharlem shake
                partyLayout.setBackgroundColor(randomColor.randomColor());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        stopListener();
    }

    private void stopListener() {
        Sensey.getInstance().stopShakeDetection();
    }
}
