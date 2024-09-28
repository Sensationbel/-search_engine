package by.bulaukin.search_engine.services.index.listener;

import by.bulaukin.search_engine.services.index.JsoupResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jsoup.Connection;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class CreatedPagesAspect {

    private final ApplicationEventPublisher publisher;

    @SneakyThrows
    @Around("@annotation(Publisher)")
    public Object addPublish(ProceedingJoinPoint point) {
        JsoupResponse target = (JsoupResponse) point.getTarget();
        Object proceed = point.proceed();
        publisher.publishEvent(new CreatedPagesEventHolder(target,
                (Connection.Response) proceed));
        return proceed;

    }

}
