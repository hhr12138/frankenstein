package common.choice.frankenstein.strategy.impl;

import common.choice.frankenstein.strategy.ContentIdStrategy;

import java.util.ArrayList;
import java.util.List;

public class RandomAdStrategy implements ContentIdStrategy {
    @Override
    public List<Long> getContentIds() throws Exception {
        return new ArrayList<>();
    }
}
