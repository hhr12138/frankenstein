package common.choice.frankenstein.strategy.impl;

import common.choice.frankenstein.configuration.AppConfiguration;
import common.choice.frankenstein.strategy.ContentIdStrategy;
import common.entity.valhalla.vo.RestResponse;
import common.log.scholar_of_yore.service.LogTemplate;
import common.storage.king.service.FrankClient;

import java.util.List;

public class RandomNativeStrategy implements ContentIdStrategy {
    private FrankClient frankClient;
    private String requestId;
    private int size;
    private String psm = AppConfiguration.PSM;
    private LogTemplate logTemplate;

    public RandomNativeStrategy(FrankClient frankClient, String requestId, int size, LogTemplate logTemplate) {
        this.frankClient = frankClient;
        this.requestId = requestId;
        this.size = size;
        this.logTemplate = logTemplate;
    }

    @Override
    public List<Long> getContentIds() throws Exception {
        RestResponse<List<Long>> resp = frankClient.getNativeIds(requestId, size);
        if(resp.isSuccess()) return resp.getData();
        logTemplate.error(requestId, psm,"[RandomNativeStrategy] getContentIds: rpc error");
        throw new Exception("[RandomNativeStrategy] getContentIds: rpc error");
    }
}
