package common.choice.frankenstein.cache.impl;
import common.choice.frankenstein.cache.IdCache;
import common.choice.frankenstein.configuration.AppConfiguration;
import common.entity.valhalla.vo.RestResponse;
import common.storage.king.service.FrankClient;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;

@Component
public class LocalNativeCache extends IdCache{
    @Resource
    private FrankClient frankClient;
    public List<Long> getIds() throws Exception {
        RestResponse<List<Long>> resp = frankClient.getNativeIds(AppConfiguration.PSM, cacheCnt);
        if(!resp.isSuccess()){
            throw new Exception("[localAdCache] getIds error: rpc error err="+resp.getMsg());
        }
        return resp.getData();
    }
}
