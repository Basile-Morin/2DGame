package world;
import core.GameConfig;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class MapLoader {

    public TileMap initializeMap(){
        int[][] tempMap=new int[GameConfig.worldColNumber][GameConfig.worldLineNumber];
        TileMap map = new TileMap(GameConfig.worldColNumber,GameConfig.worldLineNumber);
        try {
            InputStream is =getClass().getResourceAsStream("/world/map.txt");
            if (is == null) throw new IllegalStateException("Map not found: /world/map.txt");
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            for (int y = 0; y< GameConfig.worldLineNumber; y++){
                String line=br.readLine();
                String[] numbers=line.split(" ");
                for (int x=0;x<GameConfig.worldColNumber;x++){
                    String number=numbers[x];
                    tempMap[x][y]=Integer.parseInt(number);
                }
            }
            map.setMap(tempMap);
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return map;
    }

}
