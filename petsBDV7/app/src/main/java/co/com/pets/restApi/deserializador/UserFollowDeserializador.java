package co.com.pets.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import co.com.pets.pojo.UserFollow;
import co.com.pets.restApi.JsonKeys;
import co.com.pets.restApi.model.UserFollowResponse;

/**
 * Created by xander on 29/03/2017.
 */

public class UserFollowDeserializador implements JsonDeserializer<UserFollowResponse> {

    @Override
    public UserFollowResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gsonFollow = new Gson();
        UserFollowResponse userFollowResponse = gsonFollow.fromJson(json, UserFollowResponse.class);
        JsonObject followObj = json.getAsJsonObject();
        JsonObject followDataObj = followObj.getAsJsonObject(JsonKeys.MEDIA_RESPONSE_ARRAY);
        String outgoing_status = followDataObj.get(JsonKeys.MEDIA_USER_OUTGOING_STATUS).getAsString();

        UserFollow userFollow = new UserFollow();
        userFollow.setSalida(outgoing_status);

        userFollowResponse.setUserFollow(userFollow);

        return userFollowResponse;
    }

}
