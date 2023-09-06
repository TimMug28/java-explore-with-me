package ru.practicum.statsservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.practicum.stats.HitDto;
import ru.practicum.stats.StatsDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Component
public class StatsClient {

    private final String statsUrl;

    private final RestTemplate restTemplate;

    public StatsClient(@Value("${stats-service.server.url}") String statsServiceUrl) {
        statsUrl = statsServiceUrl;
        restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }

    public void saveEndpointHit(HitDto hitDto) {
        final String url = statsUrl + "/hit";
        restTemplate.postForEntity(url, hitDto, Void.class);
    }

    public List<StatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique, int page, int size) {
        final String url = statsUrl + "/stats?start={start}&end={end}&uris={uris}&unique={unique}";
        ResponseEntity<List<StatsDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<>() {
                },
                Map.of("start", start,
                        "end", end,
                        "uris", uris,
                        "unique", unique)
        );
        return response.getBody();
    }
}

