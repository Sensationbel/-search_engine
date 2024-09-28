package by.bulaukin.search_engine.controllers;

import by.bulaukin.search_engine.dto.statistics.ResultResponse;
import by.bulaukin.search_engine.dto.statistics.StatisticsResponse;
import by.bulaukin.search_engine.services.StatisticsService;
import by.bulaukin.search_engine.services.index.IndexationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final StatisticsService statisticsService;
    private final IndexationService indexationService;


    @GetMapping("/statistics")
    public ResponseEntity<StatisticsResponse> statistics() {
        return ResponseEntity.ok(statisticsService.getStatistics());
    }

    @GetMapping("/startIndexing")
    public ResponseEntity<ResultResponse> startIndexing() {
        log.info("ApiController.startIndexing started into" + Thread.currentThread().getName());
        indexationService.startPars();
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setResult(true);
        log.info("ApiController.startIndexing finished into " + Thread.currentThread().getName());
        return ResponseEntity.ok(resultResponse);
    }
}
