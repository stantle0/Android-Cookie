package com.cookie.asynctask;

import android.os.AsyncTask;
import android.os.Handler;

public class IncrementadorAsyncTask extends AsyncTask<Void, Void, Void> {

	private Handler handler;

	
	public IncrementadorAsyncTask(Handler handler) {
		this.handler = handler;
	}


	@Override
	protected Void doInBackground(Void... params) {
		
		int flag = 0;
		while (flag < 10) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			publishProgress();
		}
		
		return null;
	}
	
	@Override
	protected void onProgressUpdate(Void... values) {
		handler.sendEmptyMessage(ResultadoMessage.UM_SEGUNDO_PASSADO);
	}
	

}
