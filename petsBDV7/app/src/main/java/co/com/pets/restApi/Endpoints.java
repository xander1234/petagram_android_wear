package co.com.pets.restApi;


import co.com.pets.restApi.model.LikeUserResponse;
import co.com.pets.restApi.model.UserFollowResponse;
import co.com.pets.restApi.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by xander on 18/03/2017.
 */

public interface Endpoints {


//Para Fireabase

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_TOKEN_REGISTRO_USUARIO)
    Call<UsuarioResponse> registrarTokenID(@Field("token") String token, @Field("id_usuario_instagram") String id_usuario_instagram);

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_TOKEN_LIKE_TIMELINE)
    Call<LikeUserResponse> registrarTokenIDLikeTimeline(@Field("token") String token, @Field("id_usuario_instagram") String id_usuario_instagram, @Field("id_foto_instagram") String id_foto_instagram );


    @GET(ConstantesRestApi.KEY_POST_ID_TOKEN_LIKE_TIMELINE_ACOUNT)
    Call<UsuarioResponse> likeImgInstagramAcount(@Path("id") String id, @Path("userinstagram") String userinstagram);


    @GET(ConstantesRestApi.KEY_POST_RELATIONSHIP)
    Call<UserFollowResponse> followUserQuestion(@Path("user-id") String userId);

    //Para seguir o no a un usuario:
    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_GET_USER_FOLLOW)
    Call<UserFollowResponse> setUserFollow(@Path("user-id") String userID, @Field("action") String accion, @Field(ConstantesRestApi.KEY_ACCESS_TOKEN) String tokenAccess);

}
