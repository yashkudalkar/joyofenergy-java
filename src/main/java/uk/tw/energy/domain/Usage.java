package uk.tw.energy.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record Usage(
        String smartMeterId,
        Instant startTime,
        Instant endTime,
        BigDecimal totalCost,
        List<ElectricityReading> readings
) {
}
