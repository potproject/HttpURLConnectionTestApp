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
		//Override���Ė������Activity���ɖ��ߍ��ޕ��@�B
		//�������͌��ʂ��������΂����ł��Bresult��string���A���Ă��܂��B
		//�������̕����킩��₷���A���₷���Ȃ��Ă��������H
		URLConnectionTask = new URLConnectionAsyncTask(){
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
		    	TextView TeamName = (TextView) SubActivity.this.findViewById(R.id.SubtextTeamName);
		    	TextView TeamPunishmentNumber = (TextView) SubActivity.this.findViewById(R.id.SubtextTeamPunishmentNumber);
		    	TextView SmokinghistoryPerformanceNumberTeamSum = (TextView) SubActivity.this.findViewById(R.id.SubtextSmokinghistoryPerformanceNumberTeamSum);
		    	TeamName.setText(jsonTeamName);
		    	TeamPunishmentNumber.setText(jsonTeamPunishmentNumber);
		    	SmokinghistoryPerformanceNumberTeamSum.setText(jsonSmokinghistoryPerformanceNumberTeamSum);
		    	//����ŁA�\������Ă�͂��I
		        return;
		    }
		};
		//execute�Ŕ񓯊������J�n
		URLConnectionTask.execute("http://sleepingdragon.potproject.net/api.php?" +
        			"get=homeselect&UserId=User20150528s4KV2d&TeamId=Team20150528RazMk4");
	}
	
}
