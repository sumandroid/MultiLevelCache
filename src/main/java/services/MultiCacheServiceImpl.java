package services;

import domain.models.Cache;
import domain.models.MultiCache;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class MultiCacheServiceImpl implements MultiCacheService {

    private MultiCache multiCache;
    private TreeMap<Integer, Cache> levelToCacheMap;

    public MultiCacheServiceImpl(MultiCache multiCache){
        this.multiCache = multiCache;
        this.levelToCacheMap = multiCache.getCaches();
    }

    @Override
    public String read(String key) {
        long startTime = System.currentTimeMillis();
        Set<Cache> missedCaches = new HashSet<>();
        String result = null;
        for(Integer level : levelToCacheMap.keySet()){
            Cache cache = levelToCacheMap.get(level);
            result = cache.read(key);
            if(result == null){
                missedCaches.add(cache);
            }else{
                break;
            }
        }
        for(Cache cache : missedCaches){
            cache.write(key, result);
        }
        long endTime = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(result + "\n");
        stringBuilder.append("time taken to read is: " + (endTime - startTime) + " milliSeconds");
        return stringBuilder.toString();
    }

    @Override
    public String write(String key, String value) {
        long startTime = System.currentTimeMillis();
        for(Integer level : levelToCacheMap.keySet()){
            Cache cache = levelToCacheMap.get(level);
            String result = cache.write(key, value);
            if(result == null) break;
        }
        long endTime = System.currentTimeMillis();
        return ("write took " + (endTime - startTime)) + " milliSeconds";
    }
}
