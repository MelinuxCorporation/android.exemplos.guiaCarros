package service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import modelo.Carro;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import com.livroandroid.carros.TelaListaCarros;
import utils.HttpHelper;
import utils.XMLUtils;
import android.os.AsyncTask;

public class CarroService{

	private final String URL = "http://www.livroandroid.com.br/livro/carros/carros_{tipo}.xml";
	private TelaListaCarros Context;
	
	public void getRequest(TelaListaCarros context, String tipo){
		Context = context;
		Processo Task = new Processo(this);
		Task.execute(tipo,URL);
	}
	
	public void getResponse(ArrayList<Carro> ListaCarros){
		Context.getCarros(ListaCarros);
	}
	
	
	private class Processo extends AsyncTask<String, Void, ArrayList<Carro>>{
		
		private CarroService Servico;
		
		public Processo(CarroService c){
			this.Servico = c;
		}

		@Override
		protected ArrayList<Carro> doInBackground(String... params) {
			ArrayList<Carro> Carros = null;
			String Tipo = params[0];
			String Url = params[1].replace("{tipo}", Tipo);
			String Xml;
			
			try {
				Xml = HttpHelper.doGet(Url, "UTF-8");
				Carros = parseXML(Xml);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return Carros;
		}
		
		@Override
		public void onPostExecute(ArrayList<Carro> ListaCarros){
			this.Servico.getResponse(ListaCarros);
		}
		
		private ArrayList<Carro> parseXML(String xml){
			ArrayList<Carro> carros = new ArrayList<Carro>();
			Element root = XMLUtils.getRoot(xml, "UTF-8");
			List<Node> nodeCarros = XMLUtils.getChildren(root, "carro");
			
			for(Node node : nodeCarros){
				Carro c = new Carro();
				c.nome = XMLUtils.getText(node, "nome");
				c.desc = XMLUtils.getText(node, "desc");
				c.urlFoto = XMLUtils.getText(node, "url_foto");
				c.urlInfo = XMLUtils.getText(node, "url_info");
				
				//Log.d("CARRO","Carro "+c.nome);
				carros.add(c);
			}
			return carros;
		}
	}
}


