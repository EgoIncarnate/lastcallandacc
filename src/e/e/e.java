package e.e;

import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class e extends ListActivity implements c {
	private ListView l;
	private AndroidHttpClient c;
	private a x;
	@Override
	public void b(String in) {
		try {
			JSONObject j = new JSONObject(in);
			JSONArray ar = j.getJSONObject("feed").getJSONArray("entry");
			// JSONArray ar = fd.names();
			ids = new ArrayList<String>();
			t = new ArrayList<String>();
			u = new ArrayList<String>();
			for (int i = 0; i < ar.length(); i++) {
				String id = ar.getJSONObject(i).getJSONObject("id")
					.getString("$t");
				String item = ar.getJSONObject(i).getJSONObject("title")
						.getString("$t");
				String url = ar.getJSONObject(i).getJSONArray("link")
						.getJSONObject(0).getString("href");
				// Log.i("item ",item);
				t.add(item);
				u.add(url);
				ids.add(id);
			}		
			l.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, t));
			}
			catch(Exception e) {}
			EditText t = (EditText)findViewById(R.id.s);
			t.setText(s);
			t.selectAll();
		
	}

	String s;
	public int m = R.id.item1;
	ArrayList<String> u,t,ids;
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.e);

		l = getListView();
	
		findViewById(R.id.b).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				d();
			}
		});
		((EditText)findViewById(R.id.s)).selectAll();
	}
	
	private void d() {
		if(x != null) x.cancel(true);
		String getURL;
		String ytf = "http://gdata.youtube.com/feeds/mobile/";
		if(m == R.id.item1) {
			 getURL = ytf + "videos?v=2&caption=true&fields=entry[link/@rel='http://gdata.youtube.com/schemas/2007%23mobile']&alt=json&q=";			
		}
		else {
			 getURL = ytf + "channels?v=2&max-results=50&caption=true&alt=json&q=";			
		}
		
		//String getURL = "http://gdata.youtube.com/feeds/mobile/channels?v=2&caption=true&alt=json&q="
		//String getURL = "http://gdata.youtube.com/feeds/mobile/channels?v=2&caption=true&fields=entry[link/@rel='http://gdata.youtube.com/schemas/2007%23mobile']&alt=json&q="
		s = ((EditText)findViewById(R.id.s)).getText().toString();
		((EditText)findViewById(R.id.s)).setText("Searching");
		getURL += URLEncoder.encode(s);

		HttpUriRequest uri =new HttpGet(getURL);		
		uri.addHeader("GData-Version", "2");
		uri.addHeader("X-GData-Client", "lastcallgio");
		uri.addHeader(
				"X-GData-Key",
				"key=AI39si713Z3Uu69_ClmRzM3igF2dR3hUkf0RgLPPoDWcErcX2-DWOAZv9wtT8rfAwC7NMY4f54_wF8k6QmTatByEotgLmwyfbg");
		
		x = new a(uri, this);
		x.execute(null);
	}

	public boolean onCreateOptionsMenu(Menu n) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.e, n);
		return true;
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int p, long n) {
		Intent i = new Intent(this, v.class);
		i.putExtra("i", ids.get(p));
		i.putExtra("u", u.get(p));
		i.putExtra("t", t.get(p));
		i.putExtra("m", m);
		startActivity(i);
	}

	public boolean onOptionsItemSelected(MenuItem i) {
		m = i.getItemId();
		l.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, new String[] {} ));
		return true;
	}
}

//