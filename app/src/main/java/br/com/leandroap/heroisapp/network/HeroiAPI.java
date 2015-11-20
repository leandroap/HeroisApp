package br.com.leandroap.heroisapp.network;

import java.util.List;

import br.com.leandroap.heroisapp.model.Heroi;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by leandro on 03/10/15.
 */
public interface HeroiAPI {

    @GET("/superherois/superherois.json")
    public void getData(Callback<List<Heroi>> response);
}
