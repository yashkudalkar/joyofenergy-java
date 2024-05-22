package uk.tw.energy.service;

import org.springframework.stereotype.Service;
import uk.tw.energy.domain.ElectricityReading;
import uk.tw.energy.domain.PricePlan;
import uk.tw.energy.domain.Usage;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ElectricityUsageService {

    private final List<PricePlan> pricePlans;
    private final MeterReadingService meterReadingService;
    private final PricePlanService pricePlanService;

    public ElectricityUsageService(List<PricePlan> pricePlans, MeterReadingService meterReadingService, PricePlanService pricePlanService) {
        this.pricePlans = pricePlans;
        this.meterReadingService = meterReadingService;
        this.pricePlanService = pricePlanService;
    }

    public Usage getUsageCostOfLastWeek(String smartMeterId, String planName) {
        //TODO: NOT FOUND HANDLING
        Optional<PricePlan> smartMetersPricePlan = pricePlans.stream().filter(pricePlan -> pricePlan.getPlanName().equals(planName)).findFirst();

        Instant endTime = Instant.now();
        Instant startTime = endTime.minus(Duration.ofDays(7));

        List<ElectricityReading> lastWeekElectricityReadings = meterReadingService.getReadings(smartMeterId)
                .orElse(List.of()).stream()
                .filter(reading -> reading.time().isAfter(startTime) && reading.time().isBefore(endTime))
                .collect(Collectors.toList());


        BigDecimal totalCost = pricePlanService.calculateCost(lastWeekElectricityReadings, smartMetersPricePlan.get());
        return new Usage(smartMeterId, startTime, endTime, totalCost, lastWeekElectricityReadings);
    }
}
