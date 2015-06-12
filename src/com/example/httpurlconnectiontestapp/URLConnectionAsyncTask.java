package com.example.httpurlconnectiontestapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;
/*
 * AsyncTask<型1, 型2,型3>
   * 　　めんどくさいけどちゃんと設定しないとエラーが起きます
 *   型1		execute()の引数の型
 *          doInBackground()の引数の型
 *          ※同じにしないといけないようです
 *
 *   型2 … onProgressUpdate()の引数の型
 *
 *   型3 … doInBackground()の戻り値の型
 *         onPostExecute()の引数の型
 *
 *   ※ それぞれ不要な場合は、Voidを設定すれば良い
 */
public class URLConnectionAsyncTask extends AsyncTask<String, Void, String> {
	private Activity useActivity;
	//コンストラクタ
	//Activityを使うためここで紐づけします
	//useActivity変数に使用するActivityを設定
	public URLConnectionAsyncTask(Activity activity){
		this.useActivity = activity;
	}
	//ここでOverrideして使用できるメゾット一覧
	//onPreExecute() :
	//this.execute()が送られると実行される
	//メインスレッドで実行、一番最初に行われる
	//doInBackground() :
	//onPreExecute()の後に実行、独立したスレッドで行われる
	//doInBackground()は、可変長引数でなければならないようです
	//onProgressUpdate()　:
	//doInBackground()と並列してメインスレッドで実行、
	//非同期処理の進行状況をプログレスバーで表示したい時などに使う
	//onPostExecute():
	//doInBackground()の後に実行、メインスレッドで行われる
	//doInBackground()での返り値が引数に入る
	//
	
	//ここが非同期で処理される部分。
	//これが後ろ側で見えないところで実行されるってことです
	@Override
	protected String doInBackground(String... arg) {
		//HttpURLConnectionを使用して接続を行います。
		HttpURLConnection connection = null;
        StringBuilder src = new StringBuilder();
        try {
        	URL url = new URL(arg[0]);
            connection = (HttpURLConnection) url.openConnection();
            //注意!!!
            //AndroidManifest.xmlでインターネット接続の許可をしないとここでRuntimeExceptionが発生します！
            //<uses-permission android:name="android.permission.INTERNET" />
            //を、AndroidManifest.xmlの最後に記述してください！
            connection.connect();
            InputStream is = connection.getInputStream();
            //取得したテキストデータを、src変数に入れていきます
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
        	//接続を終了
            connection.disconnect();
        }
        //String型に変換
        String strsrc = new String(src);
		return strsrc;
	}
	
	//このメゾットは、doInBackgroundが終わった後に呼び出されます。
	//つまり、インターネット接続を行い、接続が完了し、値が正常に取得で来たらこれが実行されます。 
	//ここの部分の振る舞いを変更したい時は、extendを使って個別に拡張すればいいです
    @Override
    protected void onPostExecute(String result) {
    	//ここから、json形式で取得したものをパース(解析)し、適切に取り出します
        // JSONObject に変換します
        JSONObject json;
        String jsonTeamName="";
        String jsonTeamPunishmentNumber="";
        String jsonSmokinghistoryPerformanceNumberTeamSum="";
        //try/catchしないと駄目っぽい
		try {
			//JSONObject型に変換
			json = new JSONObject(result);
			// TeamNameを取得
	        jsonTeamName = json.getString("TeamName");
	        // TeamPunishmentNumberを取得
	        jsonTeamPunishmentNumber = json.getString("TeamPunishmentNumber");
	        // SmokinghistoryPerformanceNumberTeamSumを取得
	        jsonSmokinghistoryPerformanceNumberTeamSum = json.getString("SmokinghistoryPerformanceNumberTeamSum");
		} catch (JSONException e) {
			e.printStackTrace();
		}
        // 取得した結果をテキストビューに入れるよ
    	TextView TeamName = (TextView) useActivity.findViewById(R.id.textTeamName);
    	TextView TeamPunishmentNumber = (TextView) useActivity.findViewById(R.id.textTeamPunishmentNumber);
    	TextView SmokinghistoryPerformanceNumberTeamSum = (TextView) useActivity.findViewById(R.id.textSmokinghistoryPerformanceNumberTeamSum);
    	TeamName.setText(jsonTeamName);
    	TeamPunishmentNumber.setText(jsonTeamPunishmentNumber);
    	SmokinghistoryPerformanceNumberTeamSum.setText(jsonSmokinghistoryPerformanceNumberTeamSum);
    	//これで、表示されてるはず！
        return;
    }

}
