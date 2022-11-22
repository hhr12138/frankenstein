package common.choice.frankenstein.service;

import common.choice.frankenstein.entity.Content;
import common.choice.frankenstein.handler.AbstractFrankHandler;
import common.choice.frankenstein.handler.FrankHandler;
import common.choice.frankenstein.strategy.ContentIdStrategy;
import common.choice.frankenstein.strategy.impl.RandomAdStrategy;
import common.choice.frankenstein.strategy.impl.RandomNativeStrategy;
import common.entity.valhalla.vo.RestResponse;
import common.log.scholar_of_yore.service.LogTemplate;
import common.storage.king.service.FrankClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommonService {
    @Resource
    private FrankHandler frankHandler;
    @Resource
    private FrankClient frankClient;
    @Resource
    private LogTemplate logTemplate;

    public RestResponse<List<Content>> getNativeContent(String requestId, int size){
        List<Content> result = null;
        try{
           ContentIdStrategy strategy = new RandomNativeStrategy(frankClient,requestId,size,logTemplate);
           result = frankHandler.getContents(requestId,strategy);
        } catch (Exception e){
            return RestResponse.fail(e.getMessage());
        }
        return RestResponse.success(result);
    }

    //和上面差不多, 懒得写了
    public RestResponse<List<Content>> getAdContent(String requestId, int size){
        List<Content> result = null;
        try{
            ContentIdStrategy strategy = new RandomAdStrategy();
            result = frankHandler.getContents(requestId,strategy);
        } catch (Exception e){
            return RestResponse.fail(e.getMessage());
        }
        return RestResponse.success(result);
    }

    public RestResponse<List<Content>> getHotContent(String requestId, int size) {

    }
}
