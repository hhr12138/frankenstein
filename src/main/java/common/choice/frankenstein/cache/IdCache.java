package common.choice.frankenstein.cache;

import common.choice.frankenstein.configuration.AppConfiguration;
import common.log.scholar_of_yore.service.LogTemplate;
import common.storage.king.service.FrankClient;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class IdCache implements ListCache<Long> {
    @Resource
    LogTemplate logTemplate;
    protected List<Long> cache;
    public static final int MAX_RETRY_CNT = 3;
    protected int maxRetryCnt = MAX_RETRY_CNT;
    public static final int CACHE_CNT = (int)1e6;
    protected int cacheCnt = CACHE_CNT;
    @PostConstruct
    public void init() throws Exception {
        List<Long> ids = getIds();
        CopyOnWriteArrayList tran = new CopyOnWriteArrayList(ids);
        this.cache = tran;
    }

    @Override
    public List<Long> randomGet(int count) {
        List<Long> c = cache;
        int start = (int)(Math.random()*c.size());
        List<Long> ans = new ArrayList<>();
        if(start+count>c.size()){
            int cnt = start+count-c.size();
            ans.addAll(c.subList(0,cnt));
            count -= cnt;
        }
        ans.addAll(c.subList(start,start+count));
        return ans;
    }

    public abstract List<Long> getIds() throws Exception;

    @Scheduled(cron = "0 1 * * * *")
    protected void updateCache(){
        updateCache(0);
    }
    private synchronized void updateCache(int deep){
        if(deep >= maxRetryCnt) return;
        try {
            List<Long> ids = getIds();
           this.cache = new CopyOnWriteArrayList<>(ids);
        } catch (Exception e) {
            logTemplate.error("-1", AppConfiguration.PSM,"Id cache update error: retry cnt="+deep+"err="+e.getMessage());
            updateCache(deep+1);
        }
    }
}
