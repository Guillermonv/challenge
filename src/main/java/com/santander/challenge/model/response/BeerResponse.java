package com.santander.challenge.model.response;

/**
 * @author guillermovarelli
 */
public class BeerResponse {
    Integer beerQty;
    Integer sixPackQty;

    public Integer getBeerQty() {
        return beerQty;
    }

    public void setBeerQty(Integer beerQty) {
        this.beerQty = beerQty;
    }

    public Integer getSixPackQty() {
        return sixPackQty;
    }

    public void setSixPackQty(Integer sixPackQty) {
        this.sixPackQty = sixPackQty;
    }
}
