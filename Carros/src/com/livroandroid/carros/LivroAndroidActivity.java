package com.livroandroid.carros;

import utils.AndroidUtils;
import utils.Transacao;
import utils.TransacaoTask;
import android.app.Activity;

public class LivroAndroidActivity extends Activity {
	
	protected void alert(int mensagem){
		AndroidUtils.AlertDialog(this, mensagem);
	}
	
	public void startTransacao(Transacao transacao){
		boolean redeOk = AndroidUtils.NetworkConnected(this);
		if(redeOk){
			TransacaoTask task = new TransacaoTask(this, transacao, R.string.Aguarde);
			task.execute();
		}
		else{
			AndroidUtils.AlertDialog(this, R.string.ErroConexao);
		}
	}
}
