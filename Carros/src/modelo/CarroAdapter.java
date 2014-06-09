package modelo;

import java.util.List;

import utils.DownloadImagemUtil;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CarroAdapter extends BaseAdapter {

	private LayoutInflater inflate;
	private final List<Carro> carros;
	private final Activity context;
	private DownloadImagemUtil downloader;
	
	public CarroAdapter(Activity context, List<Carro> carros){
		this.context = context;
		this.carros = carros;
		this.inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		downloader = new DownloadImagemUtil(context);
	}

	@Override
	public int getCount() {
		return carros!=null ? carros.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return carros != null ? carros.get(position): null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder = null;
		
		if(view == null){
			// nao existe no cache
			holder = new ViewHolder();
			int layout = com.livroandroid.carros.R.layout.carro_item;
			view = inflate.inflate(layout, null);
			view.setTag(holder);
			holder.txtNome = (TextView) view.findViewById(com.livroandroid.carros.R.id.TextView_Nome);
			holder.imgFoto = (ImageView) view.findViewById(com.livroandroid.carros.R.id.img);
			holder.progress = (ProgressBar) view.findViewById(com.livroandroid.carros.R.id.progress);
		}
		else{
			// ja existe no cache
			holder = (ViewHolder) view.getTag();
		}
		
		holder.imgFoto.setImageBitmap(null);
		Carro c = carros.get(position);
		holder.txtNome.setText(c.nome);
		downloader.download(context, c.urlFoto,holder.imgFoto, holder.progress);
		return view;
		
		
	}
	
	public static class ViewHolder{
		TextView txtNome;
		ImageView imgFoto;
		ProgressBar progress;
	}
}
