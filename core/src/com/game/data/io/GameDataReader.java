package com.game.data.io;


import com.game.data.GameRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Basic Reader that is extended by every other reader
 * Provides functionality for automated file reading and constructing using GSON
 *
 * @author Youri Dudock
 */
public abstract class GameDataReader<T> {

    // extension of the data files
    private final String FILE_EXTENSION = ".json";

    /**
     * @return name of the file that must be read
     */
    public abstract String getFileName();

    /**
     * @return the class of T that is being read (required for GSON)
     */
    public abstract Class getClassType();

    /**
     * Reads a file and constructs it using GSON
     *
     * @return a list with the constructed data of type T
     */
    public List<T> read() {
        // setup a JSON Reader
        JsonReader reader = null;

        try {
            // try reading the file in JSON and init the JSON reader from it
            reader = new JsonReader(new FileReader(GameRepository.DATA_PATH + getSubFolder() + getFileName() + FILE_EXTENSION));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // get the type of T for GSON
        Type typeOfT = TypeToken.getParameterized(List.class, getClassType()).getType();

        // if reader non null, then return constructed List<T> from reader with GSON
        return reader == null ? null : new Gson().fromJson(reader, typeOfT);
    }


    /**
     * Can be overwritten to provide an extra sub folder in the common data folder
     *
     * @return name of the sub folder
     */
    protected String getSubFolder() {
        return "";
    }


}
