package uk.tw.energy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.tw.energy.domain.Usage;
import uk.tw.energy.service.AccountService;
import uk.tw.energy.service.ElectricityUsageService;

import java.util.Optional;

@RestController
@RequestMapping("/usage")
public class ElectricityUsageController {

    private final AccountService accountService;
    private final ElectricityUsageService electricityUsageService;


    public ElectricityUsageController(AccountService accountService, ElectricityUsageService electricityUsageService) {
        this.accountService = accountService;
        this.electricityUsageService = electricityUsageService;
    }

    @GetMapping("/last-week/{smartMeterId}")
    public ResponseEntity<Usage> getUsageCostOfLastWeek(@PathVariable String smartMeterId) {
        Optional<String> planId = Optional.ofNullable(this.accountService.getPricePlanIdForSmartMeterId(smartMeterId));
        if (!planId.isPresent()) {
            return ResponseEntity.notFound().build();
            //TODO: Not found since we are repeating this code many times and be handled centrally
        }
        return ResponseEntity.ok(electricityUsageService.getUsageCostOfLastWeek(smartMeterId, planId.get()));
    }

}
