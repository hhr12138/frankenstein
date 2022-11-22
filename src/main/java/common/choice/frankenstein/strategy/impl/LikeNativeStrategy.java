package common.choice.frankenstein.strategy.impl;

import common.choice.frankenstein.configuration.AppConfiguration;
import common.choice.frankenstein.strategy.ContentIdStrategy;
import common.entity.valhalla.vo.RestResponse;
import common.log.scholar_of_yore.service.LogTemplate;
import common.storage.king.service.FrankClient;

import java.util.List;

public class LikeNativeStrategy implements ContentIdStrategy {
    private LogTemplate logTemplate;
    private FrankClient frankClient;
    private String requestId;
    private int size;
    private String psm = AppConfiguration.PSM;
    @Override
    public List<Long> getContentIds() throws Exception {
        RestResponse<List<Long>> resp = frankClient.getNativeIdsOrderByLike(requestId,size);
        if(resp.isSuccess()){
            return resp.getData();
        }
        logTemplate.error(requestId, psm,"[LikeNativeStrategy] getContentIds: rpc error");
        throw new Exception("[LikeNativeStrategy] getContentIds: rpc error");
    }
}
