package com.example.httpurlconnectiontestapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.os.AsyncTask;
/*
 * AsyncTask<�^1, �^2,�^3>
   * �@�@�߂�ǂ��������ǂ����Ɛݒ肵�Ȃ��ƃG���[���N���܂�
 *   �^1		execute()�̈����̌^
 *          doInBackground()�̈����̌^
 *          �������ɂ��Ȃ��Ƃ����Ȃ��悤�ł�
 *
 *   �^2 �c onProgressUpdate()�̈����̌^
 *
 *   �^3 �c doInBackground()�̖߂�l�̌^
 *         onPostExecute()�̈����̌^
 *
 *   �� ���ꂼ��s�v�ȏꍇ�́AVoid��ݒ肷��Ηǂ�
 */
public class URLConnectionAsyncTask extends AsyncTask<String, Void, String> {

	//�R���X�g���N�^
	//Activity���g�����߂����ŕR�Â����܂�
	//useActivity�ϐ��Ɏg�p����Activity��ݒ�
	public URLConnectionAsyncTask(){

	}
	//������Override���Ďg�p�ł��郁�]�b�g�ꗗ
	//onPreExecute() :
	//this.execute()��������Ǝ��s�����
	//���C���X���b�h�Ŏ��s�A��ԍŏ��ɍs����
	//doInBackground() :
	//onPreExecute()�̌�Ɏ��s�A�Ɨ������X���b�h�ōs����
	//doInBackground()�́A�ϒ������łȂ���΂Ȃ�Ȃ��悤�ł�
	//onProgressUpdate()�@:
	//doInBackground()�ƕ��񂵂ă��C���X���b�h�Ŏ��s�A
	//�񓯊������̐i�s�󋵂��v���O���X�o�[�ŕ\�����������ȂǂɎg��
	//onPostExecute():
	//doInBackground()�̌�Ɏ��s�A���C���X���b�h�ōs����
	//doInBackground()�ł̕Ԃ�l�������ɓ���
	//
	
	//�������񓯊��ŏ�������镔���B
	//���ꂪ��둤�Ō����Ȃ��Ƃ���Ŏ��s�������Ă��Ƃł�
	@Override
	protected String doInBackground(String... arg) {
		//HttpURLConnection���g�p���Đڑ����s���܂��B
		HttpURLConnection connection = null;
        StringBuilder src = new StringBuilder();
        try {
        	URL url = new URL(arg[0]);
            connection = (HttpURLConnection) url.openConnection();
            //����!!!
            //AndroidManifest.xml�ŃC���^�[�l�b�g�ڑ��̋������Ȃ��Ƃ�����RuntimeException���������܂��I
            //<uses-permission android:name="android.permission.INTERNET" />
            //���AAndroidManifest.xml�̍Ō�ɋL�q���Ă��������I
            connection.connect();
            InputStream is = connection.getInputStream();
            //�擾�����e�L�X�g�f�[�^���Asrc�ϐ��ɓ���Ă����܂�
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
        	//�ڑ����I��
            connection.disconnect();
        }
        //String�^�ɕϊ�
        String strsrc = new String(src);
		return strsrc;
	}
	
	//���̃��]�b�g�́AdoInBackground���I�������ɌĂяo����܂��B
	//�܂�A�C���^�[�l�b�g�ڑ����s���A�ڑ����������A�l������Ɏ擾�ŗ����炱�ꂪ���s����܂��B 
	//�����̕����̐U�镑����ύX���������́Aextend���g���ČʂɊg������΂����ł�
    @Override
    protected void onPostExecute(String result) {
    	return;
    }

}
