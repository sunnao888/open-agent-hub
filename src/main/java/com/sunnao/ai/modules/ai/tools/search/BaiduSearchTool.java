package com.sunnao.ai.modules.ai.tools.search;

import com.alibaba.cloud.ai.toolcalling.baidusearch.BaiduSearchService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

public class BaiduSearchTool {

    private final BaiduSearchService baiduSearchService;

    public BaiduSearchTool(BaiduSearchService baiduSearchService) {
        this.baiduSearchService = baiduSearchService;
    }

    @Tool(description = "百度联网搜索工具", name = "BaiduSearchTool")
    public BaiduSearchService.Response search(@ToolParam(description = "搜索关键字") String query) {
        BaiduSearchService.Request request = new BaiduSearchService.Request(query, 10);
        return baiduSearchService.apply(request);
    }
}
