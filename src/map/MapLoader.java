package map;
import core.GameConfig;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class MapLoader {

    public static TileMap initializeMap(){
        int[][] tempMap=new int[GameConfig.WORLDCOLNUMBER][GameConfig.WORLDLINENUMBER];
        TileMap map = new TileMap(GameConfig.WORLDCOLNUMBER,GameConfig.WORLDLINENUMBER);
        try {
            InputStream is =map.getClass().getResourceAsStream("/map/map.txt");
            if (is == null) throw new IllegalStateException("Map not found: /world/map.txt");
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            for (int y = 0; y< GameConfig.WORLDLINENUMBER; y++){
                String line=br.readLine();
                String[] numbers=line.split(" ");
                for (int x = 0; x<GameConfig.WORLDCOLNUMBER; x++){
                    String number=numbers[x];
                    tempMap[x][y]=Integer.parseInt(number);
                }
            }
            map.setMap(tempMap);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return map;
    }

}
