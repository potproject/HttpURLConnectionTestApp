package com.example.httpurlconnectiontestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener{
	public URLConnectionAsyncTask URLConnectionTask;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//URLに接続するサンプル。ですが、
		//Android3.0以降からNetworkOnMainThreadExceptionとなり、MainThreadでは使用できなくなっているようです。
		//つまり、このやり方では現在使用することが出来ません。
		//この問題を解決するには、別にスレッドを作って動かさなくてはなりません。
		//		
		//MainThreadは、AndroidでUI(画面表示)のために使用される処理です。TextViewに文字を設定することなどが行えます。
		//onCreateメゾットでは、MainThreadしか使用できないため、ネットワークの接続などは他のスレッドを作って行ってくださいということです。
		//スレッドとは並列処理の単位のことです。スレッドの概念は難しいので、わからなければ頑張って調べてください。
		/*HttpURLConnection connection = null;
        StringBuilder src = new StringBuilder();
        try {
        	URL url = new URL("http://sleepingdragon.potproject.net/api.php?" +
        			"get=homeselect&UserId=User20150528s4KV2d&TeamId=Team20150528RazMk4");
            
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream is = connection.getInputStream();
            while (true) {
                byte[] line = new byte[1024];
                int size = is.read(line);
                if (size <= 0)
                    break;
                src.append(new String(line, "UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            connection.disconnect();
        }
        String uresponse = new String(src);
        Log.d("test",uresponse);
        */
		
		//なので、別スレッドとして、AsyncTaskをExtendしたメゾットを作り、呼び出しましょう
		//ここからの処理は、URLConnectionAsyncTaskクラスで行います。
		//URLConnectionAsyncTask.javaを参照してください。
		// URLConnectionAsyncTaskの生成
		URLConnectionTask = new URLConnectionExtend(this);
		//executeで非同期処理開始
		URLConnectionTask.execute("http://sleepingdragon.potproject.net/api.php?" +
        			"get=homeselect&UserId=User20150528s4KV2d&TeamId=Team20150528RazMk4");
		// クリックイベントを取得したいボタン
		findViewById(R.id.button).setOnClickListener(this);

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
	    switch (view.getId()) {
        case R.id.button:
        	Intent intent = new Intent(MainActivity.this, SubActivity.class);
        	startActivity(intent);
	    }
	}
}
