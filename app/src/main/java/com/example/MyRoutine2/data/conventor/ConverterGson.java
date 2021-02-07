package com.example.MyRoutine2.data.conventor;

import androidx.room.TypeConverter;


import com.example.MyRoutine2.model.ItemEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ConverterGson {

    @TypeConverter
    public String fromCountryLangList(List<ItemEntity> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ItemEntity>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<ItemEntity> toCountryLangList(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ItemEntity>>() {}.getType();
        List<ItemEntity> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }
}
