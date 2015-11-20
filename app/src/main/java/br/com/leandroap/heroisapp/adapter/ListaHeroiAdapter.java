package br.com.leandroap.heroisapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.leandroap.heroisapp.R;
import br.com.leandroap.heroisapp.model.Heroi;

/**
 * Created by leandro on 03/10/15.
 */
public class ListaHeroiAdapter extends ArrayAdapter<Heroi> {

    private String URLImagens = "http://heiderlopes.com.br/superherois/imagens/";
    private Context context;
    private int resource;
    private List<Heroi> herois;

    public ListaHeroiAdapter(Context context, int resource, List<Heroi> herois) {
        super(context, resource, herois);
        this.context = context;
        this.resource = resource;
        this.herois = herois;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(resource, parent, false);
        TextView tvNomeHeroi = (TextView)view.findViewById(R.id.tvNomeHeroi);
        tvNomeHeroi.setText(herois.get(position).getNome());
        ImageView ivHeroi = (ImageView)view.findViewById(R.id.ivHeroi);
        Picasso.with(getContext())
                .load(URLImagens + herois.get(position).getImagem())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.ic_action_error)
                .into(ivHeroi);
        return view;
    }
}
