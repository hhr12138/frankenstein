package common.choice.frankenstein.handler;
import common.choice.frankenstein.configuration.AppConfiguration;
import common.choice.frankenstein.entity.Content;
import common.choice.frankenstein.strategy.ContentIdStrategy;
import common.log.scholar_of_yore.service.LogTemplate;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractFrankHandler implements FrankHandler{
    @Resource
    protected LogTemplate logTemplate;
    protected String psm = AppConfiguration.PSM;
    public abstract Map<Long,Long> getStyle(String requestId, List<Long> contents);
    public abstract Map<Long, List<Long>> getComponents(String requestId, List<Long> contents);
    public List<Content> getContents(String requestId, ContentIdStrategy strategy) throws Exception {
        List<Long> contentIds = strategy.getContentIds();
        Map<Long,Long> style = getStyle(requestId, contentIds);
        Map<Long, List<Long>> components = getComponents(requestId, contentIds);
        List<Content> result = new ArrayList<>();
        for(Long id: contentIds){
            Content content = new Content();
            content.setId(id);
            if(!style.containsKey(id)){
                logTemplate.warn(requestId, psm, "[getContents]: can not find style, rollback to basic style, contentId="+id);
                style.put(id,1L);
            }
            if(!components.containsKey(id)){
                components.put(id,new ArrayList<>());
            }
            content.setStyleId(style.get(id));
            content.setComponentIds(components.get(id));
            result.add(content);
        }
        return result;
    }
}
