package common.choice.frankenstein.handler.impl;

import common.choice.frankenstein.handler.AbstractFrankHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonHandler extends AbstractFrankHandler {

    @Override
    public Map<Long, Long> getStyle(String requestId, List<Long> contents) {
        Map<Long, Long> result = new HashMap<>();
        for(Long id: contents){
            result.put(id, 1L);
        }
        return result;
    }

    @Override
    public Map<Long, List<Long>> getComponents(String requestId, List<Long> contents) {
        Map<Long,List<Long>> result = new HashMap<>();
        for(Long id: contents){
            result.put(id,new ArrayList<>());
        }
        return result;
    }
}
