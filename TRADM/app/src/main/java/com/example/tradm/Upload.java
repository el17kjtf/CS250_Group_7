package com.example.tradm;

public class Upload {
    private String ImageUrl;

    public Upload(){
        //empty for firebase
    }

    public Upload(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getImageUrl() {
        return ImageUrl;
    }
}
