package co.com.pets.receivesTouch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import co.com.pets.pojo.UserFollow;
import co.com.pets.restApi.ConstantesRestApi;
import co.com.pets.restApi.Endpoints;
import co.com.pets.restApi.adapter.RestApiAdapter;
import co.com.pets.restApi.model.UserFollowResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xander on 28/03/2017.
 */
public class GiveTouchUser extends BroadcastReceiver {

    private static final String USER_RECEPTOR = "monkeypets1";
    private static final String USER_EMISOR = "favoritepets1";
    private static final String USER1 = "-KKI-JIngAMiEh3zOogs";  //usuario inicial
    private static final String USER2 = "-KgN7JhBfi5DsemdKC80";
    private static final String ID_RECEPTOR = USER1;

    @Override
    public void onReceive(Context context, Intent intent) {
        String ACTION_KEY = "TOUCH_USER";
        String accion = intent.getAction();

        Log.d("RECIBO", accion);

        if (ACTION_KEY.equals(accion)){
            touchUser(context,USER1);
            Toast.makeText(context, "Diste un toque a " + USER_RECEPTOR, Toast.LENGTH_SHORT).show();
        }
    }


    public void touchUser(final Context context, final String userId){

        final String FOLLOW_USER = "follow";
        final String UNFOLLOW_USER = "unfollow";

        Log.d("TOQUE_USER", "true");

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecentFollow = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        final Endpoints endpointApi = (Endpoints) restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecentFollow);
        Call<UserFollowResponse> userFollowResponseCall = endpointApi.followUserQuestion(userId);

        userFollowResponseCall.enqueue(new Callback<UserFollowResponse>() {
            @Override
            public void onResponse(Call<UserFollowResponse> call, Response<UserFollowResponse> response) {

                UserFollowResponse userFollowResponse =response.body();
                UserFollow userFollow = userFollowResponse.getUserFollow();

                String follow_user = FOLLOW_USER;

                if(FOLLOW_USER.equals(userFollow.getSalida())){
                    follow_user = UNFOLLOW_USER;
                }

                Call<UserFollowResponse> userFollowResponseCall1 = endpointApi.setUserFollow(userId,follow_user,ConstantesRestApi.ACCESS_TOKEN);

                userFollowResponseCall1.enqueue(new Callback<UserFollowResponse>() {
                    @Override
                    public void onResponse(Call<UserFollowResponse> call, Response<UserFollowResponse> response) {

                        UserFollowResponse userFollowResponse1 = response.body();
                        UserFollow userFollow1 = userFollowResponse1.getUserFollow();

                        String text = "";
                        if(FOLLOW_USER.equals(userFollow1.getSalida())){
                            text = text +"Siguiendo Usuario";
                        }else{
                            text = text +"Dejar de seguir al usuario";
                        }

                        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<UserFollowResponse> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onFailure(Call<UserFollowResponse> call, Throwable t) {

            }
        });

    }

}
