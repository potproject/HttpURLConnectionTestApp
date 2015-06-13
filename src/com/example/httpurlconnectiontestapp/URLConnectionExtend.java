package com.example.httpurlconnectiontestapp;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.widget.TextView;

public class URLConnectionExtend extends URLConnectionAsyncTask{
	private Activity useActivity;
	public URLConnectionExtend(Activity activity) {
		this.useActivity = activity;
		// TODO Auto-generated constructor stub
	}
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
