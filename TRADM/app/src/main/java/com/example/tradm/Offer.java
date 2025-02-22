package com.example.tradm;

public class Offer {
    private String title;
    private String description;
    private int sellerID;
    private int buyerID;
    private OfferStat offerStatus;
    private String offerType;
    private int price;
    private Upload upload;

    enum OfferStat{
        Sold, Available, Pending
    }

    public Offer() {
        //empty constructor needed
    }

    public Offer(String title, String description, OfferStat offerStatus, String offerType, int price, Upload upload) {
        this.title = title;
        this.description = description;
        this.offerStatus = offerStatus;
        this.offerType = offerType;
        this.price = price;
        this.upload = upload;
    }

    public Offer(String title, String description, int buyerID, int sellerID, OfferStat offerStatus, String offerType, int price, Upload upload) {
        this.title = title;
        this.description = description;
        this.buyerID = buyerID;
        this.sellerID = sellerID;
        this.offerStatus = offerStatus;
        this.offerType = offerType;
        this.price = price;
        this.upload = upload;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }

    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }

    public void setOfferStatus(OfferStat offerStatus) {
        this.offerStatus = offerStatus;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setUpload(Upload upload) {
        this.upload = upload;
    }

    public int getBuyerID() {
        return buyerID;
    }

    public int getPrice() {
        return price;
    }

    public int getSellerID() {
        return sellerID;
    }

    public OfferStat getOfferStatus() {
        return offerStatus;
    }

    public String getOfferType() {
        return offerType;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Upload getUpload() {
        return upload;
    }
}
