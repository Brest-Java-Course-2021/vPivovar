package com.epam.brest.price;

import com.epam.brest.requesters.Requester;
import com.epam.brest.requesters.RequesterAbstract;

import java.util.List;
import java.util.SortedMap;

public class PriceCalculator extends RequesterAbstract<Double, Long> {

    private final SortedMap<Double, Long> priceCurve;

    public PriceCalculator(Requester<Double> requester,
                           SortedMap<Double, Long> priceCurve) {
        super(requester);
        this.priceCurve = priceCurve;
    }

    @Override
    public Long request() {
        var value = requester.request();
        var price = 0L;
        var it = priceCurve.entrySet().iterator();
        var curve = List.copyOf(priceCurve.entrySet());
        for (var i = 0; i < curve.size() - 1; i++) {
            var current = curve.get(i);
            var next = curve.get(i + 1);
            if (value > next.getKey()) {
                price += Math.round((next.getKey() - current.getKey()) * current.getValue());
            } else {
                price += Math.round((value - current.getKey()) * current.getValue());
                break;
            }
        }

        var last = curve.get(curve.size() - 1);
        if (value > last.getKey()) {
            price += Math.round((value - last.getKey()) * last.getValue());
        }
        return price;
    }
}