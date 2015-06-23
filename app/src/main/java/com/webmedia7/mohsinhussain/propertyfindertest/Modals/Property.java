package com.webmedia7.mohsinhussain.propertyfindertest.Modals;

/**
 * Created by mohsinhussain on 6/15/15.
 */
public class Property {
    int id;
    int catId;
    String title;
    String type;
    String thumbnailURL;
    String thumbnailBigUrl;
    int imageCount;
    String price;
    String currency;
    String feature;
    String location;
    String area;
    String poa;
    int bathrooms;
    int bedrooms;
    boolean visited;
    String [] amenities;
    long lat;
    long lang;

    public Property(){

    }

    public Property(int id, int catId, String title, String type, String thumbnailURL, String thumbnailBigUrl,
                    int imageCount, String price, String currency, String feature, String location,
                    String area,String poa, int bathrooms, int bedrooms, boolean visited, String [] amenities,
                    long lat, long lang)
    {
        this.id = id;
        this.catId = catId;
        this.title = title;
        this.type = type;
        this.thumbnailURL = thumbnailURL;
        this.thumbnailBigUrl = thumbnailBigUrl;
        this.imageCount = imageCount;
        this.price = price;
        this.currency = currency;
        this.feature = feature;
        this.location = location;
        this.area = area;
        this.poa = poa;
        this.bathrooms = bathrooms;
        this.bedrooms = bedrooms;
        this.visited = visited;
        this.amenities = amenities;
        this.lat = lat;
        this.id = id;
        this.lang = lang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public String getThumbnailBigUrl() {
        return thumbnailBigUrl;
    }

    public void setThumbnailBigUrl(String thumbnailBigUrl) {
        this.thumbnailBigUrl = thumbnailBigUrl;
    }

    public int getImageCount() {
        return imageCount;
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPoa() {
        return poa;
    }

    public void setPoa(String poa) {
        this.poa = poa;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String[] getAmenities() {
        return amenities;
    }

    public void setAmenities(String[] amenities) {
        this.amenities = amenities;
    }

    public long getLat() {
        return lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public long getLang() {
        return lang;
    }

    public void setLang(long lang) {
        this.lang = lang;
    }
}
