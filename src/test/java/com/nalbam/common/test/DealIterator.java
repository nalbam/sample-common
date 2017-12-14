package com.nalbam.common.test;

public class DealIterator implements Iterator {

    String[] deals;
    int position = 0;

    public DealIterator(final String[] deals) {
        this.deals = deals;
    }

    @Override
    public boolean hashNext() {
        return this.deals != null && this.deals.length > this.position;
    }

    @Override
    public Object next() {
        return this.deals[this.position++];
    }

}
