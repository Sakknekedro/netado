package sakknekedro.netado;

import java.text.DecimalFormat;

import android.net.TrafficStats;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	//TODO: some way to save traffic amounts between reboots
	
	/** Total bytes of outgoing traffic since last reboot */
	private long upTraffic;
	/** Total bytes of incoming traffic since last reboot */
	private long dnTraffic;
	/** Total GBs of outgoing and incoming traffic since last reboot */
	private Double totalTraffic;
	/** How much 1GB of traffic costs */
	private Double unitPrice = 150.0;
	/** How much the traffic costs since reboot */
	private Double price;
	private String sTotalTraffic,sPrice;
	private TextView trafficView, priceView;
	private DecimalFormat df;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		df = new DecimalFormat("##.##");
		
		upTraffic = TrafficStats.getTotalTxBytes();
		dnTraffic = TrafficStats.getTotalRxBytes();
		totalTraffic = (upTraffic + dnTraffic) / Math.pow(1024.0,3);
		sTotalTraffic = df.format(totalTraffic);
		price = (totalTraffic * unitPrice);
		sPrice = df.format(price);

		trafficView = (TextView) findViewById(R.id.traffic_amount);
		priceView = (TextView) findViewById(R.id.price_amount);

		trafficView.setText(sTotalTraffic);
		priceView.setText(sPrice);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
