package com.tdam2013.grupo13.messaging;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class WebMessageServiceWrapper {
	
	private Context context;
	
	
	public WebMessageServiceWrapper(Context context){
		this.context = context;
	}
	
	public void registerUser(String userName, String password){
		String[] params = { userName, password};
		UserRegisterAsyncTask task = new UserRegisterAsyncTask(); 
		task.execute(params);
	}
	
	class UserRegisterAsyncTask extends AsyncTask<String, Integer, Boolean>{

		ProgressDialog pDialog;
		
		@Override
        protected void onPreExecute(){
			super.onPreExecute();
			
			pDialog = new ProgressDialog(context);
			pDialog.setMessage("Procesando");
			pDialog.show();
        }
    	
    	@Override
		protected Boolean doInBackground(String... params) {
    		WebMessageClient client = new WebMessageClientRegister(context);
			String[] result = client.execute(params);
			return result[0].equals("success");
		}
    	
    	@Override
        protected void onPostExecute(Boolean result){
            super.onPostExecute(result);
            
            pDialog.dismiss();
            
            if(result){
            	Toast.makeText(context, "Usuario registrado", Toast.LENGTH_SHORT).show();
            }else{
            	Toast.makeText(context, "Usuario NO registrado", Toast.LENGTH_SHORT).show();
            }
        }
	}
	
	public void sendMessage(String userName, String password, String to, String msg){
		String[] params = { userName, password, to, msg};
		MessageSenderAsyncTask task = new MessageSenderAsyncTask(); 
		task.execute(params);
	}
	
	class MessageSenderAsyncTask extends AsyncTask<String, Integer, Boolean>{

		ProgressDialog pDialog;
		
		@Override
        protected void onPreExecute(){
			super.onPreExecute();
			
			pDialog = new ProgressDialog(context);
			pDialog.setMessage("Enviando mensaje");
			pDialog.show();
        }
    	
    	@Override
		protected Boolean doInBackground(String... params) {
    		WebMessageClient client = new WebMessageClientSender(context);
			String[]  result = client.execute(params);
			return null;
		}
    	
    	@Override
        protected void onPostExecute(Boolean result){
            super.onPostExecute(result);
            
            pDialog.dismiss();
            
            if(result){
            	Toast.makeText(context, "Mensaje enviado", Toast.LENGTH_SHORT).show();
            }else{
            	Toast.makeText(context, "Mensaje no enviado", Toast.LENGTH_SHORT).show();
            }
        }
	}
}
