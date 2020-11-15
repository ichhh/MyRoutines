package com.chernov.ivan.myroutines.data;

import androidx.room.TypeConverter;

import com.chernov.ivan.myroutines.model.ProgramItemEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ConverterGson {

    @TypeConverter
    public String fromCountryLangList(List<ProgramItemEntity> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ProgramItemEntity>>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<ProgramItemEntity> toCountryLangList(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ProgramItemEntity>>() {}.getType();
        List<ProgramItemEntity> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }
}
