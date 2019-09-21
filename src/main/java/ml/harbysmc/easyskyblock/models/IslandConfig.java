package ml.harbysmc.easyskyblock.models;

import lombok.Data;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class IslandConfig implements ConfigurationSerializable {
    private List<String> friends;
    private int index;
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;

    public IslandConfig () {

    }

    public IslandConfig(Map<String, Object> map) {
        friends = (List<String>) map.get("friends");
        index = (int) map.get("index");
        x = (double) map.get("x");
        y = (double) map.get("y");
        z = (double) map.get("z");
        yaw = (float) (double) map.get("yaw");
        pitch = (float) (double) map.get("pitch");
    }

    @Override
    public Map<String, Object> serialize() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("friends", friends);
        map.put("index", index);
        map.put("x", x);
        map.put("y", y);
        map.put("z", z);
        map.put("yaw", yaw);
        map.put("pitch", pitch);
        return map;
    }
}
