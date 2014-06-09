package com.livroandroid.carros;
import java.util.ArrayList;

import modelo.Carro;
import modelo.CarroAdapter;
import service.CarroService;
import utils.Transacao;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class TelaListaCarros  extends LivroAndroidActivity implements OnItemClickListener, Transacao{
	
	public static final String TAG = "livroAndroid";
	private ListView listView;
	private ArrayList<Carro> carros = null;
	private String tipo;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.carros);
		
		ListView lista = (ListView) findViewById(R.id.ListView_Carros);
		lista.setOnItemClickListener(this);
		tipo = getIntent().getStringExtra("tipo");
		carros = new ArrayList<Carro>();
		
		startTransacao(this);
	}
	
	
	@Override
	public void executar(){
		CarroService Service = new CarroService();
		Service.getRequest(this, tipo);
	}
	
	public void getCarros(ArrayList<Carro> ListaCarros){
		Log.d("PROJETO","CHEGUEI AKI NO GET CARROS");
		if(ListaCarros==null){
			Toast toast = Toast.makeText(this, "Não foi possivel realizar consulta!",Toast.LENGTH_SHORT);
	        toast.show();
		}
		else{
			//for(Carro c : ListaCarros){
			//	Log.i(TAG, "Carro: "+ c.nome);
			//}
			Toast toast = Toast.makeText(this, ListaCarros.size()+ " Veiculos Encontrados!",Toast.LENGTH_SHORT);
	        toast.show();
		}
		
		if(ListaCarros!=null){
			carros.addAll(ListaCarros);	
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
		Carro c = (Carro) parent.getAdapter().getItem(position);
		Toast.makeText(this, "Carro: "+c.nome,Toast.LENGTH_SHORT).show();
	}

	@Override
	public void atualizarView() {
		Log.d("PROJETO","ATUALIZANDO A VIEW.. OS OS CARROS: "+carros.toString());
		if(carros != null){
			listView.setAdapter(new CarroAdapter(this, carros));
		}   
	}
}