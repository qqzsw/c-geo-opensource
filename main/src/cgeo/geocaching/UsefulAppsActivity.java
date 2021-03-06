package cgeo.geocaching;

import butterknife.InjectView;

import cgeo.geocaching.activity.AbstractActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UsefulAppsActivity extends AbstractActivity {

    @InjectView(R.id.parent) protected LinearLayout parentLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.useful_apps);

        addApp(R.string.helper_calendar_title, R.string.helper_calendar_description, R.drawable.cgeo, "cgeo.calendar");
        addApp(R.string.helper_locus_title, R.string.helper_locus_description, R.drawable.helper_locus, "menion.android.locus");
        addApp(R.string.helper_gpsstatus_title, R.string.helper_gpsstatus_description, R.drawable.helper_gpsstatus, "com.eclipsim.gpsstatus2");
        addApp(R.string.helper_bluetoothgps_title, R.string.helper_bluetoothgps_description, R.drawable.helper_bluetoothgps, "googoo.android.btgps");
        addApp(R.string.helper_barcode_title, R.string.helper_barcode_description, R.drawable.helper_barcode, "com.google.zxing.client.android");
    }

    private void installFromMarket(String marketId) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pname:" + marketId)));
        } catch (Exception e) {
            // market not available in standard emulator
        }

        finish();
    }

    private void addApp(final int titleId, final int descriptionId, final int imageId, final String marketUrl) {
        final View appLayout = getLayoutInflater().inflate(R.layout.useful_apps_item, null);
        ((TextView) appLayout.findViewById(R.id.title)).setText(res.getString(titleId));
        ((ImageView) appLayout.findViewById(R.id.image)).setImageDrawable(res.getDrawable(imageId));
        ((TextView) appLayout.findViewById(R.id.description)).setText(res.getString(descriptionId));
        appLayout.findViewById(R.id.app_layout).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                installFromMarket(marketUrl);
            }
        });
        parentLayout.addView(appLayout);
    }

}
