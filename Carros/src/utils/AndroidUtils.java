package utils;

import android.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.util.Log;

public class AndroidUtils {

	public static boolean NetworkConnected(Context c){
		
		boolean conectado;  
	    ConnectivityManager conectivtyManager = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);  
	    if (conectivtyManager.getActiveNetworkInfo() != null  
	            && conectivtyManager.getActiveNetworkInfo().isAvailable()  
	            && conectivtyManager.getActiveNetworkInfo().isConnected()) {  
	        conectado = true;  
	    } else {  
	        conectado = false;  
	    }  
	    return conectado;  
	    
	}
	
	@SuppressWarnings("deprecation")
	public static void AlertDialog(final Context context, final int mensagem){
		try{
			AlertDialog dialog = new AlertDialog.Builder(context).setTitle(context.getString(R.string.appName)).setMessage(mensagem).create();
			dialog.setButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					return;					
				}
			});
			
			dialog.show();
		}
		catch(Exception e){
			Log.e("CARROS",e.getMessage(),e);
		}
	}
	
}
