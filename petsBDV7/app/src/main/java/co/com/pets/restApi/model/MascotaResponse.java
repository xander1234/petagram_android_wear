package co.com.pets.restApi.model;



import java.util.ArrayList;

import co.com.pets.pojo.MascotaRestApi;

/**
 * Created by xander on 27/02/2017.
 */

public class MascotaResponse {

    private ArrayList<MascotaRestApi> mascotas;

    public ArrayList<MascotaRestApi> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<MascotaRestApi> mascotas) {
        this.mascotas = mascotas;
    }
}
