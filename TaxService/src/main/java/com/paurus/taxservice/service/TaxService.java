package com.paurus.taxservice.service;

import com.paurus.taxservice.model.TaxRequest;
import com.paurus.taxservice.model.TaxResponse;
import com.paurus.taxservice.model.TraderTaxRule;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TaxService {

    private final Map<Long, TraderTaxRule> rules = new HashMap<>();

    public TaxService() {
        rules.put(1L, new TraderTaxRule(TraderTaxRule.Type.GENERAL, TraderTaxRule.Mode.RATE, 0.10));
        rules.put(2L, new TraderTaxRule(TraderTaxRule.Type.GENERAL, TraderTaxRule.Mode.AMOUNT, 2));
        rules.put(3L, new TraderTaxRule(TraderTaxRule.Type.WINNINGS, TraderTaxRule.Mode.RATE, 0.10));
        rules.put(4L, new TraderTaxRule(TraderTaxRule.Type.WINNINGS, TraderTaxRule.Mode.AMOUNT, 1));
    }

    public TaxResponse calculateTax(TaxRequest req) {
        double beforeTax = req.playedAmount * req.odd;
        double winnings = beforeTax - req.playedAmount;

        TraderTaxRule rule = rules.get(req.traderId);
        if (rule == null) throw new RuntimeException("Unknown trader ID");

        double taxBase = switch (rule.type) {
            case GENERAL -> beforeTax;
            case WINNINGS -> winnings;
        };

        double taxAmount = switch (rule.mode) {
            case RATE -> taxBase * rule.value;
            case AMOUNT -> rule.value;
        };

        double afterTax = beforeTax - taxAmount;

        TaxResponse res = new TaxResponse();
        res.possibleReturnAmountBefTax = beforeTax;
        res.taxAmount = taxAmount;
        res.taxRate = rule.mode == TraderTaxRule.Mode.RATE ? rule.value : 0;
        res.possibleReturnAmountAfterTax = afterTax;
        res.possibleReturnAmount = afterTax;
        return res;
    }
}

