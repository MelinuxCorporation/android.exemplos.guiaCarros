package utils;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class TransacaoTask extends AsyncTask<Void, Void, Boolean>{

	private static final String TAG="Livro Android";
	private Context context;
	private Transacao Transacao;
	private ProgressDialog progresso;
	private Throwable exceptionErro;
	private int aguardeMsg;
	
	
	public TransacaoTask(Context c, Transacao t, int mensagem){
		Transacao = t;
		context = c;
		aguardeMsg = mensagem;		
	}
	
	@Override
	protected void onPreExecute(){
		// Inicia a janela de Aguarde
		abrirProgress();
	}
	
	
	@Override
	protected Boolean doInBackground(Void... params) {
		try{
			Transacao.executar();
		}
		
		catch (Throwable e){
			Log.e(TAG,e.getMessage(),e);
			this.exceptionErro = e;
			return false;
		}
		
		finally{
			try{
				fecharProgress();
			}
			catch (Exception e){
				Log.e(TAG,e.getMessage(),e);
			}
		}
		return true;
	}
	
	@Override
	protected void onPostExecute(Boolean ok){
		if(ok){
			Transacao.atualizarView();
		}
		else{
			AndroidUtils.AlertDialog(context, 1);//"Erro: "+exceptionErro.getMessage());
		}		
	}
	
	public void abrirProgress(){
		try{
			if(progresso!=null){
				progresso =ProgressDialog.show(context, "", context.getString(aguardeMsg));
			}
		}
		catch (Throwable e){
			Log.e(TAG, e.getMessage(),e);
		}
	}
	
	public void fecharProgress(){
		try{
			if(progresso!=null){
				progresso.dismiss();
			}
		}
		catch(Throwable e){
			Log.e(TAG,e.getMessage(),e);
		}
	}
}