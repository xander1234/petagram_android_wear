package co.com.pets.restApi.deserializador;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import co.com.pets.pojo.MascotaRestApi;
import co.com.pets.restApi.JsonKeys;
import co.com.pets.restApi.model.MascotaResponse;

/**
 * Created by xander on 28/02/2017.
 */

public class MascotaDeserializador implements JsonDeserializer<MascotaResponse>{


    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();
        MascotaResponse mascotaResponse =  gson.fromJson(json, MascotaResponse.class);
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        mascotaResponse.setMascotas(deserializarMascotaDeJson(mascotaResponseData));

        return mascotaResponse;
    }


    private ArrayList<MascotaRestApi> deserializarMascotaDeJson(JsonArray mascotaResponseData){
        ArrayList<MascotaRestApi> mascotaRestApi = new ArrayList<>();

        for (int i = 0; i< mascotaResponseData.size(); i++){

            JsonObject mascotaResponseDataObject =  mascotaResponseData.get(i).getAsJsonObject();

            JsonObject userJson = mascotaResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id = userJson.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto = userJson.get(JsonKeys.USER_FULLNAME).getAsString();


            JsonObject imageJson = mascotaResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdResolutionJson = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String urlFoto = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson = mascotaResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            MascotaRestApi mascotaActual = new MascotaRestApi();
            mascotaActual.setId(id);
            mascotaActual.setNombreCompleto(nombreCompleto);
            mascotaActual.setUrlFoto(urlFoto);
            mascotaActual.setLikes(likes);

            mascotaRestApi.add(mascotaActual);

        }

        return mascotaRestApi;
    }
}
