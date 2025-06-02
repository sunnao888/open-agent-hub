package com.sunnao.ai.modules.ai.service;

import com.sunnao.ai.modules.ai.agent.OpenManus4J;
import com.sunnao.ai.modules.ai.apps.LoveApp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final LoveApp loveApp;
    private final OpenManus4J openManus4J;

    public void love(String prompt) {
        Flux<String> response = loveApp.stream("1", prompt);

        response.subscribe(System.out::print);
    }

    public void manus(String prompt) {
        String response = openManus4J.run(prompt);
        System.out.println(response);
    }
}
