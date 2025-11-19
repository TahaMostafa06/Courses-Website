package com.github.tahamostafa06.backend.database.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public abstract class JsonDatabase<RecordType extends Record> {
    private String filePath;
    protected Map<String, RecordType> records;
    protected TypeToken<?> deserializationTypeToken;

    protected JsonDatabase(String filePath, TypeToken<Map<String, RecordType>> typeToken) throws IOException {
        this.filePath = filePath;
        this.deserializationTypeToken = typeToken;
        load();
    }

    protected void load() throws IOException {
        var jsonReader = new JsonReader(new FileReader(this.filePath));
        var gson = new Gson();
        this.records = gson.fromJson(jsonReader, deserializationTypeToken.getType());
        jsonReader.close();
    }

    public RecordType getRecord(String key) {
        return records.get(key);
    }

    public void addRecord(RecordType record) {
        this.records.put(generateNewId(record), record);
    }

    public abstract String generateNewId(RecordType record);

    public String getIdByRecord(RecordType record) {
        for (var entry : this.records.entrySet()) {
            if (entry.getValue().equals(record))
                return entry.getKey();
        }
        return null;
    }

    public void saveToFile() throws IOException {
        var gson = new GsonBuilder().setPrettyPrinting().create();
        var fileWriter = new FileWriter(this.filePath);
        gson.toJson(this.records, fileWriter);
        fileWriter.close();
    }

}