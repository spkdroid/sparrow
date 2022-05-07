package com.crypto.sparrow;

public class CoinData {

    private String coinName;
    private String coinPicture;
    private String coinCode;
    private String coinFullName;
    private String coinAlgorithm;

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getCoinPicture() {
        return "https://www.cryptocompare.com/" + coinPicture;
    }

    public void setCoinPicture(String coinPicture) {
        this.coinPicture = coinPicture;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public String getCoinFullName() {
        return coinFullName;
    }

    public void setCoinFullName(String coinFullName) {
        this.coinFullName = coinFullName;
    }

    public String getCoinAlgorithm() {
        return coinAlgorithm;
    }

    public void setCoinAlgorithm(String coinAlgorithm) {
        this.coinAlgorithm = coinAlgorithm;
    }
}