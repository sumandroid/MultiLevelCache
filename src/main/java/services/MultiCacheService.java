package services;

public interface MultiCacheService {

    String read(String key);

    String write(String token, String token1);
}
