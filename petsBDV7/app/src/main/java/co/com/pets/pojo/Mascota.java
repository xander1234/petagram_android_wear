package co.com.pets.pojo;

/**
 * Created by xander on 22/01/2017.
 */


public class Mascota {

    private int id;
    private int id_mascota;
    private int foto;
    private String nombre;
    private  int likes;


    public Mascota(int id_mascota, int foto,String nombre, int likes) {
        this.foto = foto;
        this.nombre = nombre;
        this.likes = likes;
        this.id_mascota = id_mascota;
    }


    public Mascota(int foto,String nombre, int likes) {
        this.foto = foto;
        this.nombre = nombre;
        this.likes = likes;
    }

    public Mascota(int foto,int likes) {
        this.foto = foto;
        this.likes = likes;
    }
    public Mascota() {

    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
