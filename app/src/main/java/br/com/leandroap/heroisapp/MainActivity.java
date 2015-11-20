package br.com.leandroap.heroisapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.leandroap.heroisapp.adapter.ListaHeroiAdapter;
import br.com.leandroap.heroisapp.model.Heroi;
import br.com.leandroap.heroisapp.network.HeroiAPI;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private List<Heroi> herois;
    private ListView lvHerois;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvHerois = (ListView)findViewById(R.id.lvHerois);

        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://www.heiderlopes.com.br")
                .build();

        HeroiAPI heroiAPI = restAdapter.create(HeroiAPI.class);

        heroiAPI.getData(new Callback<List<Heroi>>() {
            @Override
            public void success(List<Heroi> herois, Response response) {
                ListaHeroiAdapter adapter = new ListaHeroiAdapter(getApplicationContext(),
                        R.layout.row_item, herois);
                lvHerois.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), R.string.toast_erro_listar_herois,
                        Toast.LENGTH_LONG).show();
            }
        });
    }


}
