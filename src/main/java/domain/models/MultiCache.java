package domain.models;

import java.util.HashMap;
import java.util.TreeMap;

public class MultiCache {

    private TreeMap<Integer, Cache> levelToCacheMap = new TreeMap<>();
    private Integer levels;

    public MultiCache(HashMap<Integer, Integer> levelToCapacityMap){
        this.levels = levelToCapacityMap.size();
        for(Integer i : levelToCapacityMap.keySet()){
            initCache(i, levelToCapacityMap.get(i));
        }
    }

    public TreeMap<Integer, Cache> getCaches() {
        return levelToCacheMap;
    }

    private void initCache(Integer level, Integer capacity){
        Cache cache = new Cache(capacity, level);
        levelToCacheMap.put(level, cache);
    }
}
