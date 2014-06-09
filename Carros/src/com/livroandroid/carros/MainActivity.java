package com.livroandroid.carros;

import modelo.Carro;
import utils.AndroidUtils;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity{
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.appActionSettings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements OnClickListener{
            
    	private Button btEsportivo;
    	private Button btClassico;
    	private Button btLuxo;
    	private Button btSobre;
    	
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        	
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            // Layout do dashboard            
                        
            btEsportivo = (Button) rootView.findViewById(R.id.btEsportivos);
            btClassico = (Button) rootView.findViewById(R.id.btClassicos);
            btLuxo = (Button) rootView.findViewById(R.id.btLuxo);
            
            btLuxo.setOnClickListener((OnClickListener) this);
	        btClassico.setOnClickListener((OnClickListener) this);
	        btEsportivo.setOnClickListener((OnClickListener) this);
            
            
            
            btSobre = (Button) rootView.findViewById(R.id.btSobre);
            btSobre.setOnClickListener(this);
            
            return rootView;
        }

		@Override
		public void onClick(View v) {			
			
			
			if(v == btSobre){
				startActivity(new Intent(getActivity(), TelaSobre.class));	    		
	    	}
			
			else {
				if(AndroidUtils.NetworkConnected(getActivity())){
	
					Intent comando = new Intent(getActivity(), TelaListaCarros.class);
					
			    	if(v == btEsportivo){
			    		comando.putExtra(Carro.TIPO, Carro.TIPO_ESPORTIVOS);
			    		startActivity(comando);
			    	}
			    	else if(v == btClassico){
			    		comando.putExtra(Carro.TIPO, Carro.TIPO_CLASSICO);
			    		startActivity(comando);
			    	}
			    	else if(v == btLuxo){
			    		comando.putExtra(Carro.TIPO, Carro.TIPO_LUXO);
			    		startActivity(comando);
			    	}
		    	
				}
				
				else{
	            	Toast toast = Toast.makeText(getActivity(), "Internet Indisponivel",Toast.LENGTH_SHORT);
	    	        toast.show();
	            }
			}
			
		}	
    }
}