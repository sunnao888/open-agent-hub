package com.sunnao.ai.modules.ai.agent;

import lombok.extern.slf4j.Slf4j;

/**
 * 实现ReAct模式的agent
 * 简单来说就是赋予agent先思考再执行的能力
 *
 * @author sunnao
 * @since 2025-06-02
 */
@Slf4j
public abstract class ReActAgent extends BaseAgent {

    /**
     * 处理当前状态并决定下一步行动
     *
     * @return 是否需要继续执行
     */
    public abstract boolean think();

    /**
     * 执行决定的行动
     *
     * @return 行动执行的结果
     */
    public abstract String act();


    @Override
    public String step() {
        try {
            // 先思考
            boolean shouldAct = think();
            if (!shouldAct) {
                return "思考完成，无需行动";
            }
            // 再行动
            return act();
        } catch (Exception e) {
            log.error("步骤执行失败", e);
            return "步骤执行出错: " + e.getMessage();
        }
    }
}
