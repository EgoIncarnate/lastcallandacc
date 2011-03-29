package e.e;

import java.net.URLEncoder;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class v extends Activity implements c {
	private String u,t,i;
	private int m;
	private TextView z;
	private static String q = " has a caption rating of ";
	private static String[] r = { "poor", "fair", "average", "good",
			"very good" };
	private Boolean f = false;

	@Override
	public void b(String s) {
		if (!f) {
			if (m == R.id.item1) {
			String o = t + q + s;
			
			z.setText(o);
			z.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED);
			z.sendAccessibilityEvent(AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED);
			f = true;
			}
			else
			{
				z = ((TextView) findViewById(R.id.t));
				
				z.setText(t + s);
				
				z.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED);
				z.sendAccessibilityEvent(AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED);
			}
		}
	}

	private a x;

	public void onCreate(Bundle b) {
		super.onCreate(b);
		t = getIntent().getExtras().getString("t");
		u = getIntent().getExtras().getString("u");
		i = getIntent().getExtras().getString("i");
		m = getIntent().getExtras().getInt("m");

		if (m == R.id.item1) {
			setContentView(R.layout.v);

			z = ((TextView) findViewById(R.id.t));
			z.setText(t + q + "(loading)");
			z.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED);

			Button p = (Button) findViewById(R.id.p);
			p.setOnClickListener(new View.OnClickListener() {
				public void onClick(View arg0) {
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(u)));
				}
			});

			String g = "Rate the captions in this video ";
			int h[] = { R.id.r1, R.id.r2, R.id.r3, R.id.r4, R.id.r5 };
			for (int i = 0; i < h.length; i++) {
				p = (Button) findViewById(h[i]);
				p.setText(g + r[i]);

				p.setOnClickListener(new View.OnClickListener() {
					public void onClick(View arg0) {
						e(arg0.getTag().toString());
					}
				});
			}
			d();
		}
		else
		{
			setContentView(R.layout.l);
			l();
		}
	}

	//private String w = "http://dmasus:8088/";
	private String w = "http://eserver.lastcallgio.appspot.com/";

	private void e(String rank) {
		if (x != null)
			x.cancel(true);
		String getURL = w + "r?q=";
		getURL += URLEncoder.encode(i);
		getURL += "?rank=" + rank;

		HttpUriRequest uri = new HttpPost(getURL);

		x = new a(uri, this);
		x.execute(null);
	}
	
	private void l() {
		if (x != null)
			x.cancel(true);
		String getURL = w + "c?q=";

		// String getURL =
		// "http://gdata.youtube.com/feeds/mobile/channels?v=2&caption=true&alt=json&lr=en&q="
		// String getURL =
		// "http://gdata.youtube.com/feeds/mobile/channels?v=2&caption=true&lr=en&fields=entry[link/@rel='http://gdata.youtube.com/schemas/2007%23mobile']&alt=json&q="
		getURL += URLEncoder.encode(i);

		HttpUriRequest uri = new HttpGet(getURL);

		x = new a(uri, this);
		x.execute(null);
		
	}
	

	private void d() {
		if (x != null)
			x.cancel(true);
		String getURL = w + "r?q=";

		// String getURL =
		// "http://gdata.youtube.com/feeds/mobile/channels?v=2&caption=true&alt=json&q="
		// String getURL =
		// "http://gdata.youtube.com/feeds/mobile/channels?v=2&caption=true&fields=entry[link/@rel='http://gdata.youtube.com/schemas/2007%23mobile']&alt=json&q="
		getURL += URLEncoder.encode(i);

		HttpUriRequest uri = new HttpGet(getURL);

		x = new a(uri, this);
		x.execute(null);
	}

}