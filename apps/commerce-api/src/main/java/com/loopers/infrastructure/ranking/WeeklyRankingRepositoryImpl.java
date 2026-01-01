package com.loopers.infrastructure.ranking;

import com.loopers.domain.ranking.WeeklyRankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeeklyRankingRepositoryImpl implements WeeklyRankingRepository {
    private final WeeklyRankingJpaRepository weeklyRankingJpaRepository;
}
