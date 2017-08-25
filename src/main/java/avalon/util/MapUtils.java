package avalon.util;

import avalon.model.dungeons.DungeonMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapUtils {

    public static DungeonMap gen(int num) {
        List<DungeonMap> mapNodes = new ArrayList<>();
        List<DungeonMap> freeNodes = new ArrayList<>();
        Random rand = new Random();

        for (int i=0; i<num; i++) {
            DungeonMap m = new DungeonMap();
            mapNodes.add(m);
            freeNodes.add(m);
        }

        DungeonMap head = freeNodes.remove(0);
        DungeonMap tail = freeNodes.remove(0);

        while(freeNodes.size() > 0) {
            int numChildren = rand.nextInt(1) + 1;      // random number between 1 and 1
            if (freeNodes.size() < numChildren) {
                numChildren = freeNodes.size();
            }
            List<DungeonMap> childList = new ArrayList<>();
            for (int i=0; i<numChildren; i++) {
                childList.add(freeNodes.remove(0));
            }

        }

        return head;
    }

}