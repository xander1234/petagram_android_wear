package co.com.pets.pojo;

/**
 * Created by xander on 04/03/2017.
 */

public class MascotaRestApi {

    private String id;
    private String nombreCompleto;
    private String urlFoto;
    private int likes = 0;

    public MascotaRestApi(String urlFoto, String nombreCompleto, String email, int likes) {
        this.urlFoto = urlFoto;
        this.nombreCompleto = nombreCompleto;
        this.likes = likes;
    }

    public MascotaRestApi() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
