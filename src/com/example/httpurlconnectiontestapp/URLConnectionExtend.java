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
    	//��������Ajson�`���Ŏ擾�������̂��p�[�X(���)���A�K�؂Ɏ��o���܂�
        // JSONObject �ɕϊ����܂�
        JSONObject json;
        String jsonTeamName="";
        String jsonTeamPunishmentNumber="";
        String jsonSmokinghistoryPerformanceNumberTeamSum="";
        //try/catch���Ȃ��Ƒʖڂ��ۂ�
		try {
			//JSONObject�^�ɕϊ�
			json = new JSONObject(result);
			// TeamName���擾
	        jsonTeamName = json.getString("TeamName");
	        // TeamPunishmentNumber���擾
	        jsonTeamPunishmentNumber = json.getString("TeamPunishmentNumber");
	        // SmokinghistoryPerformanceNumberTeamSum���擾
	        jsonSmokinghistoryPerformanceNumberTeamSum = json.getString("SmokinghistoryPerformanceNumberTeamSum");
		} catch (JSONException e) {
			e.printStackTrace();
		}
        // �擾�������ʂ��e�L�X�g�r���[�ɓ�����
    	TextView TeamName = (TextView) useActivity.findViewById(R.id.textTeamName);
    	TextView TeamPunishmentNumber = (TextView) useActivity.findViewById(R.id.textTeamPunishmentNumber);
    	TextView SmokinghistoryPerformanceNumberTeamSum = (TextView) useActivity.findViewById(R.id.textSmokinghistoryPerformanceNumberTeamSum);
    	TeamName.setText(jsonTeamName);
    	TeamPunishmentNumber.setText(jsonTeamPunishmentNumber);
    	SmokinghistoryPerformanceNumberTeamSum.setText(jsonSmokinghistoryPerformanceNumberTeamSum);
    	//����ŁA�\������Ă�͂��I
        return;
    }

}
