package de.bitcoinclient.bcutils.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.bitcoinclient.bcutils.BCUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class ConfigManager {
    private final JsonObject object;
    private final File config;

    public ConfigManager(String path, String fileName) throws IOException {
        if(!new File(new File(" ").getPath().replace(" ","") + path).exists()) {
            new File(new File(" ").getPath().replace(" ","") + path).mkdirs();
        }

        config = new File(new File(" ").getPath().replace(" ","") + path, fileName+".json");

        if(!config.exists()) {
            FileWriter writer = new FileWriter(config);
            writer.write("{}");
            writer.flush();
            writer.close();
        }

        object = JsonParser.parseReader(new FileReader(config)).getAsJsonObject();
    }

    public ConfigManager(String fileName) throws IOException {
        if(!new File(new File(" ").getPath().replace(" ","") + "plugins/"+ BCUtils.getBCUtils().getDescription().getName()).exists()) {
            new File(new File(" ").getPath().replace(" ","") + "plugins/"+ BCUtils.getBCUtils().getDescription().getName()).mkdirs();
        }

        config = new File(new File(" ").getPath().replace(" ","") + "plugins", fileName+".json");

        if(!config.exists()) {
            FileWriter writer = new FileWriter(config);
            writer.write("{}");
            writer.flush();
            writer.close();
        }
        object = JsonParser.parseReader(new FileReader(config)).getAsJsonObject();
    }

    public void save() {
        try {
            FileWriter writer = new FileWriter(config);
            writer.write(object.toString()
                    .replace("{","{\n")
                    .replace("}","\n}")
                    .replace(",",",\n"));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            BCUtils.getPluginLogger().sendError(e.getMessage());
        }

    }

    public void add2Config(String id, String value, boolean isForced) {
        if(isForced) {
            object.addProperty(id, value);
            save();
        }
        else if(!object.has(id)) {
            object.addProperty(id, value);
            save();
        }
    }

    public void add2Config(String id, int value, boolean isForced) {
        if(isForced) {
            object.addProperty(id, value);
            save();
        }
        else if(!object.has(id)) {
            object.addProperty(id, value);
            save();
        }
    }

    public void add2Config(String id, boolean value, boolean isForced) {
        if(isForced) {
            object.addProperty(id, value);
            save();
        }
        else if(!object.has(id)) {
            object.addProperty(id, value);
            save();
        }
    }

    public void add2Config(String id, double value, boolean isForced) {
        if(isForced) {
            object.addProperty(id, value);
            save();
        }
        else if(!object.has(id)) {
            object.addProperty(id, value);
            save();
        }
    }

    public void add2Config(String id, long value, boolean isForced) {
        if(isForced) {
            object.addProperty(id, value);
            save();
        }
        else if(!object.has(id)) {
            object.addProperty(id, value);
            save();
        }
    }

    public void add2Config(String id, UUID value, boolean isForced) {
        if(isForced) {
            object.addProperty(id, value.toString());
            save();
        }
        else if(!object.has(id)) {
            object.addProperty(id, value.toString());
            save();
        }
    }

    public void removeFromConfig(String id, boolean value, boolean isForced) {
        object.remove(id);
        save();
    }

    public String getValueAsString(String id) {
        if(object.has(id)) {
            return object.get(id).getAsString();
        }
        return "";
    }

    public int getValueAsInt(String id) {
        if(object.has(id)) {
            return object.get(id).getAsInt();
        }
        return -1;
    }

    public boolean getValueAsBoolean(String id) {
        if(object.has(id)) {
            return object.get(id).getAsBoolean();
        }
        return false;
    }

    public double getValueAsDouble(String id) {
        if(object.has(id)) {
            return object.get(id).getAsDouble();
        }
        return -1;
    }

    public long getValueAsLong(String id) {
        if(object.has(id)) {
            return object.get(id).getAsLong();
        }
        return -1;
    }

    public boolean hasInConfig(String id) {
        return object.has(id);
    }

    public JsonObject getObject() {
        return object;
    }
}
