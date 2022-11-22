package common.choice.frankenstein.cache;

import java.util.List;

public interface ListCache<T> {
    List<T> randomGet(int count);
}
