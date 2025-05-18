package com.sunnao.ai.modules.ai.third.openai.model;

import lombok.Data;

import java.util.List;

@Data
public class ModelListResponse {
    private String object;
    private List<OpenaiModel> data;
}
