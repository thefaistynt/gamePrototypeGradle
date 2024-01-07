import org.example.Deserializer;
import org.example.Player;

import java.io.IOException;
import java.util.List;

public class Deserialization {
    public static void main(String[] args) throws IOException {
        Deserializer<Player> deserializer = new Deserializer<>();
        String filePath = "player.json";
        List<Player> deserializedObjects = deserializer.deserialize(filePath, Player.class);
        for (Player obj : deserializedObjects) {
            System.out.println(obj.getBackpack().slots[0][1].toString());
        }
    }
}
