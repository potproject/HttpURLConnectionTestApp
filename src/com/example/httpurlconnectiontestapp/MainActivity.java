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
		//URL�ɐڑ�����T���v���B�ł����A
		//Android3.0�ȍ~����NetworkOnMainThreadException�ƂȂ�AMainThread�ł͎g�p�ł��Ȃ��Ȃ��Ă���悤�ł��B
		//�܂�A���̂����ł͌��ݎg�p���邱�Ƃ��o���܂���B
		//���̖�����������ɂ́A�ʂɃX���b�h������ē������Ȃ��Ă͂Ȃ�܂���B
		//		
		//MainThread�́AAndroid��UI(��ʕ\��)�̂��߂Ɏg�p����鏈���ł��BTextView�ɕ�����ݒ肷�邱�ƂȂǂ��s���܂��B
		//onCreate���]�b�g�ł́AMainThread�����g�p�ł��Ȃ����߁A�l�b�g���[�N�̐ڑ��Ȃǂ͑��̃X���b�h������čs���Ă��������Ƃ������Ƃł��B
		//�X���b�h�Ƃ͕��񏈗��̒P�ʂ̂��Ƃł��B�X���b�h�̊T�O�͓���̂ŁA�킩��Ȃ���Ί撣���Ē��ׂĂ��������B
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
		
		//�Ȃ̂ŁA�ʃX���b�h�Ƃ��āAAsyncTask��Extend�������]�b�g�����A�Ăяo���܂��傤
		//��������̏����́AURLConnectionAsyncTask�N���X�ōs���܂��B
		//URLConnectionAsyncTask.java���Q�Ƃ��Ă��������B
		// URLConnectionAsyncTask�̐���
		URLConnectionTask = new URLConnectionExtend(this);
		//execute�Ŕ񓯊������J�n
		URLConnectionTask.execute("http://sleepingdragon.potproject.net/api.php?" +
        			"get=homeselect&UserId=User20150528s4KV2d&TeamId=Team20150528RazMk4");
		// �N���b�N�C�x���g���擾�������{�^��
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
