package com.epam.brest.price;

import com.epam.brest.exceptions.RequestFailureException;
import com.epam.brest.exceptions.RequestInterruptedException;
import com.epam.brest.requesters.Requester;
import com.epam.brest.requesters.RequesterAbstract;

import java.util.List;
import java.util.SortedMap;

public class PriceCalculator extends RequesterAbstract<Double, Long> {

//  private final SortedMap<Double, Long> priceCurve;
    private final Requester<SortedMap<Double, Long>> priceCurveRequester;

    public PriceCalculator(Requester<Double> requester,
                           Requester<SortedMap<Double, Long>> priceCurveRequester) {
        super(requester);
        this.priceCurveRequester = priceCurveRequester;
    }

    @Override
    public Long request() throws RequestFailureException, RequestInterruptedException {
        var value = requester.request();
        var priceCurve = priceCurveRequester.request();
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