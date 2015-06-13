package com.example.httpurlconnectiontestapp;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SubActivity extends Activity {
	public URLConnectionAsyncTask URLConnectionTask;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub);
		//Overrideして無理やりActivity内に埋め込む方法。
		//こっちは結果だけ書けばいいです。resultでstringが帰ってきます。
		//こっちの方がわかりやすく、やりやすくなっていいかも？
		URLConnectionTask = new URLConnectionAsyncTask(){
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
		    	TextView TeamName = (TextView) SubActivity.this.findViewById(R.id.SubtextTeamName);
		    	TextView TeamPunishmentNumber = (TextView) SubActivity.this.findViewById(R.id.SubtextTeamPunishmentNumber);
		    	TextView SmokinghistoryPerformanceNumberTeamSum = (TextView) SubActivity.this.findViewById(R.id.SubtextSmokinghistoryPerformanceNumberTeamSum);
		    	TeamName.setText(jsonTeamName);
		    	TeamPunishmentNumber.setText(jsonTeamPunishmentNumber);
		    	SmokinghistoryPerformanceNumberTeamSum.setText(jsonSmokinghistoryPerformanceNumberTeamSum);
		    	//これで、表示されてるはず！
		        return;
		    }
		};
		//executeで非同期処理開始
		URLConnectionTask.execute("http://sleepingdragon.potproject.net/api.php?" +
        			"get=homeselect&UserId=User20150528s4KV2d&TeamId=Team20150528RazMk4");
	}
	
}
