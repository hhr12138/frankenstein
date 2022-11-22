package common.choice.frankenstein.controller;

import common.choice.frankenstein.entity.Content;
import common.choice.frankenstein.service.CommonService;
import common.entity.valhalla.vo.RestResponse;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CommonController {
    @Resource
    private CommonService service;
    public RestResponse<List<Content>> getNativeContent(String requestId, int size){
        return service.getNativeContent(requestId,size);
    }

    public RestResponse<List<Content>> getHotContent(String requestId, int size){
        return service.getHotContent(requestId, size);
    }
    //简单的CRUD, 懒得写了
    public RestResponse<List<Content>> getContentByTag(){
        return null;
    }
    public RestResponse<List<Content>> getContentByAuthorId(){
        return null;
    }
    public RestResponse<List<Content>> getContentByReadTime(){
        return null;
    }

    public RestResponse<List<Content>> getAdContent(String requestId, int size){
        return service.getAdContent(requestId,size);
    }
}
