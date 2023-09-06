package ru.practicum.stats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatsRepository extends JpaRepository<Stats, Long> {

    @Query("select new ru.practicum.stats.StatsDto(s.app,s.uri,count(s.uri)) " +
            "from Stats as s " +
            "where s.timestamp between :start and :end " +
            "group by s.app, s.uri " +
            "order by count(s.uri) desc")
    List<StatsDto> getListAllStats(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("select new ru.practicum.stats.StatsDto(s.app,s.uri,count(distinct(s.uri))) " +
            "from Stats as s " +
            "where s.timestamp between :start and :end " +
            "group by s.app, s.uri, s.ip " +
            "order by count(s.uri) desc")
    List<StatsDto> getListOfUniqueStatistics(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

        @Query("select new ru.practicum.stats.StatsDto(s.app,s.uri,count(s.uri)) " +
            "from Stats as s " +
            "where s.uri in :uris and s.timestamp between :start and :end " +
            "group by s.app, s.uri " +
            "order by count(s.uri) desc")
    List<StatsDto> getStatisticsByUrlsAndTimes(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end,
                                               @Param("uris") List<String> uris);

    @Query("select new ru.practicum.stats.StatsDto(s.app,s.uri,count(distinct(s.uri))) " +
            "from Stats as s " +
            "where s.uri in :uris and s.timestamp between :start and :end " +
            "group by s.app, s.uri, s.ip " +
            "order by count(s.uri) desc")
    List<StatsDto> getUniqueStatisticsByUrisAndTimes(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end,
                                                     @Param("uris") List<String> uris);
}
