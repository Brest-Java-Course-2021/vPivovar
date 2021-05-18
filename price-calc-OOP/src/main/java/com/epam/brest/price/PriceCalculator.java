package com.epam.brest.price;

import com.epam.brest.requesters.Requester;

import java.util.List;
import java.util.SortedMap;

public class PriceCalculator implements Requester<Long> {

    private final SortedMap<Double, Long> priceCurve;
    private final Requester<Double> requester;

    public PriceCalculator(SortedMap<Double, Long> priceCurve,
                           Requester<Double> requester) {
        this.priceCurve = priceCurve;
        this.requester = requester;
    }

    @Override
    public Long request() {
        var aDouble = requester.request();
        var result = 0L;
        var it = priceCurve.entrySet().iterator();
        var curve = List.copyOf(priceCurve.entrySet());
        for (var i = 0; i < curve.size() - 1; i++) {
            var current = curve.get(i);
            var next = curve.get(i + 1);
            if (aDouble > next.getKey()) {
                result += Math.round((next.getKey() - current.getKey()) * current.getValue());
            } else {
                result += Math.round((aDouble - current.getKey()) * current.getValue());
                break;
            }
        }

        var last = curve.get(curve.size() - 1);
        if (aDouble > last.getKey()) {
            result += Math.round((aDouble - last.getKey()) * last.getValue());
        }
        return result;
    }
}