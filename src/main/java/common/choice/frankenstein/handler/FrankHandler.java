package common.choice.frankenstein.handler;

import common.choice.frankenstein.entity.Content;
import common.choice.frankenstein.strategy.ContentIdStrategy;

import java.util.List;

public interface FrankHandler {
    List<Content> getContents(String reqeustId, ContentIdStrategy strategy) throws Exception;
}
