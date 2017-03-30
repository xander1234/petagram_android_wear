package co.com.pets.restApi.model;

/**
 * Created by xander on 20/03/2017.
 */

public class LikeUserResponse {

    private String id;
    private String token;
    private String id_usuario_instagram;
    private String id_foto_instagram;


    public LikeUserResponse(String id, String token, String id_usuario_instagram, String id_foto_instagram) {
        this.id = id;
        this.token = token;
        this.id_usuario_instagram = id_usuario_instagram;
        this.id_foto_instagram = id_foto_instagram;
    }

    public LikeUserResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId_usuario_instagram() {
        return id_usuario_instagram;
    }

    public void setId_usuario_instagram(String id_usuario_instagram) {
        this.id_usuario_instagram = id_usuario_instagram;
    }

    public String getId_foto_instagram() {
        return id_foto_instagram;
    }

    public void setId_foto_instagram(String id_foto_instagram) {
        this.id_foto_instagram = id_foto_instagram;
    }
}
