package com.example.tradm;

public class Offer {
    private String title;
    private String description;
    private int sellerID;
    private int buyerID;
    private String offerStatus;
    private String offerType;
    private int price;
    private Upload upload;

    public Offer() {
        //empty constructor needed
    }

    public Offer(String title, String description, String offerType, int price, Upload upload) {
        this.title = title;
        this.description = description;
        this.buyerID = 0;
        this.sellerID = 1;
        this.offerStatus = "Available";
        this.offerType = offerType;
        this.price = price;
        this.upload = upload;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }

    public void setOfferStatus(String offerStatus) {
        this.offerStatus = offerStatus;
    }

    public int getPrice() {
        return price;
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
