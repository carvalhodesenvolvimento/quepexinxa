package com.quepexinxa.carlos.quepexinxa;

import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    public static List<ItemPromocao> fromJsonToList(String json){
        List<ItemPromocao> listaPromocao = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(json);

            for (int i=0; i < array.length(); i++){
                ItemPromocao itemPromocao = new ItemPromocao(array.getJSONObject(i));
                listaPromocao.add(itemPromocao);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listaPromocao;
    }
}