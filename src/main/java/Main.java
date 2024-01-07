import org.example.Generator;
import org.example.Player;
import org.example.Serializer;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Player> players = Generator.generatePlayers(10000);
        String filePath ="player.json";
        Serializer<Player> serializer= new Serializer<>();
        serializer.serialize(players, filePath);
    }
}
