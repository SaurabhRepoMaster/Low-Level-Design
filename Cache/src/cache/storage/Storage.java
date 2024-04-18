package cache.storage;

import cache.exceptions.StorageFullException;

public interface Storage<Key,Value> {

    public void add(Key key, Value value) throws StorageFullException;

    void remove(Key key);

    public Value get(Key key);

}
