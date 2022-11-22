package common.choice.frankenstein.strategy;

import java.util.List;

//喵的, 纯纯的滥用设计模式, 这个该扔到king那里的, 在这里每个不同的策略就是掉king的不同方法....完全没必要, 不过写都写了...
public interface ContentIdStrategy {
    List<Long> getContentIds() throws Exception;
}
