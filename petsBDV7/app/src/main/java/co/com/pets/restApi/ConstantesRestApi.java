package co.com.pets.restApi;

/**
 * Created by xander on 27/02/2017.
 */

public final class ConstantesRestApi {

    //ppal acount: favoritepets1    -> id: 4765269386
    //secundary acount: monkeypets1 -> id: 4768556731
    //Search user:
    // https://api.instagram.com/v1/users/search?q=favoritepets1&access_token=4765269386.7f7c9e1.6ae212374a78417f92a54c2dedb2072c

    //https://api.instagram.com/v1/
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com"+ VERSION;
    public static final String ACCESS_TOKEN = "4765269386.7f7c9e1.6ae212374a78417f92a54c2dedb2072c";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
//    public static final String KEY_GET_RECENT_MEDIA_USER = "users/self/media/recent/";
    public static final String KEY_GET_RECENT_MEDIA_USER = "users/4765269386/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    public static final String KEY_GET_RECENT_MEDIA_USER_SECUNDARIO = "users/4768556731/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER_SECUNDARIO = KEY_GET_RECENT_MEDIA_USER_SECUNDARIO + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    public static String ACOUNT_INSTAGRAM = "favoritepets1";

    //////Firebase
    public static final String ROOT_URL_FIREBASE = "https://secret-beyond-94805.herokuapp.com/";

    //Registro usuario
    public static final String KEY_POST_ID_TOKEN_REGISTRO_USUARIO = "registrar-usuario/";

    //Registro Like
    public static final String KEY_POST_ID_TOKEN_LIKE_TIMELINE = "like_timeline/";

    //GEt device Registro Like
    public static final String KEY_POST_ID_TOKEN_LIKE_TIMELINE_ACOUNT = "like-instagram/{id}/{userinstagram}/";




    //EndPoint para saber si se sigue o no a otro usuario, y para hacer follow o no, dependiendo de parámetros
    public static final String KEY_POST_RELATIONSHIP = "users/{user-id}/relationship"; //EnPoint para hacer like o no a otro usuario
    //EndPoint para saber si se sigue a un usuario
    public static final String KEY_GET_USER_FOLLOW = KEY_POST_RELATIONSHIP+"?"+KEY_ACCESS_TOKEN+ "="+ACCESS_TOKEN;








}
