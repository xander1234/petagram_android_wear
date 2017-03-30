package co.com.pets.restApi;


import co.com.pets.restApi.model.MascotaResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by xander on 27/02/2017.
 */

public interface EndpointsApi {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
   Call<MascotaResponse> getRecentMedia();

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER_SECUNDARIO)
    Call<MascotaResponse> getRecentMediaWafleSandbox();

}
