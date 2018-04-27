package sample;

import Constants.ConstantClass;
import javafx.animation.AnimationTimer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * class that sends directions to the player once per frame
 */

public class GameLoader {
    private Player player;
    public final AnimationTimer timer;
    private int arrayIndex;
    private JSONArray coordList;
    private direction prevDir = direction.STABLE;

    public GameLoader(Player pl) {
        player = pl;
        arrayIndex = 0;
        coordList = gatherInfoFromJSON();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
    }

    private JSONArray gatherInfoFromJSON() {
        org.json.simple.parser.JSONParser jsonParser = new org.json.simple.parser.JSONParser();

        try (FileReader reader = new FileReader(ConstantClass.JSONFILENAME))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            return  (JSONArray) obj;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void update() {
        if (arrayIndex < coordList.size()) {
            JSONObject coordUnit = (JSONObject) coordList.get(arrayIndex);
            int dirInt = Math.toIntExact((Long) coordUnit.get("dir"));
            direction currentDir = direction.valueOf(dirInt);
            if (prevDir != currentDir) {
                player.changeDir(currentDir);
                prevDir = currentDir;
            }
            arrayIndex++;
        }
    }
}
