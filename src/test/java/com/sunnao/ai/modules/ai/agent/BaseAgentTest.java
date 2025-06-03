package com.sunnao.ai.modules.ai.agent;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BaseAgentTest {

    @Resource
    private  OpenManus4J openManus4J;

    @Test
    void run() {
        openManus4J.run("""
                请帮我写一篇关于人工智能在教育领域应用的文章，要求包括以下内容：
                1. 人工智能在教育中的定义和作用
                2. 人工智能如何个性化学习体验
                3. 人工智能在教育评估中的应用
                4. 人工智能在教育管理中的潜力
                5. 人工智能在教育中的挑战和未来展望
                请确保文章结构清晰，逻辑严谨，并提供相关的案例或数据支持。
                文章完成后，帮我保存到本地。
                之后，根据文章内容，生成一份PPT大纲，包含每个部分的标题和要点。
                """);
    }
}