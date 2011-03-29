package e.e;

import java.net.URLEncoder;


import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

public class a extends AsyncTask<Void, Void, String> {

	HttpUriRequest m;
	String o;
	c b;
	
	public a(HttpUriRequest m, c b ) {
		this.m = m;
		this.b = b;
	}

	protected String doInBackground(Void... voids) {
		o = "";
		HttpClient client = new DefaultHttpClient();
		try {
			HttpResponse responseGet = client.execute(m);
			HttpEntity resEntityGet = responseGet.getEntity();
			if (resEntityGet != null) {
				o = EntityUtils.toString(resEntityGet);
			}
		} catch (Exception e) {
			Log.i("why oh why",e.toString());
		}
		return o;
	}

	protected void onPostExecute(String r) {
		b.b(r);
	}

}