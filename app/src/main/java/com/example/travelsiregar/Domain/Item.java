package com.example.travelsiregar.Domain;

import java.io.Serializable;

public class Item implements Serializable {
    private String title;
    private String address;
    private String description;
    private String pic;
    private String duration;
    private String timeTour;
    private String dateTour;
    private String tourGuideName;
    private String tourGuidePhone;
    private String tourGuidePic;
    private int price;
    private int bed;
    private String distance;
    private double score;

    public Item() {
    }

    // Getter & Setter untuk title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Method tambahan agar bisa pakai getName() di adapter
    public String getName() {
        return title;
    }

    // Getter & Setter untuk address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Getter & Setter untuk description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter & Setter untuk pic
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    // Getter & Setter untuk duration
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    // Getter & Setter untuk timeTour
    public String getTimeTour() {
        return timeTour;
    }

    public void setTimeTour(String timeTour) {
        this.timeTour = timeTour;
    }

    // Getter & Setter untuk dateTour
    public String getDateTour() {
        return dateTour;
    }

    public void setDateTour(String dateTour) {
        this.dateTour = dateTour;
    }

    // Getter & Setter untuk tourGuideName
    public String getTourGuideName() {
        return tourGuideName;
    }

    public void setTourGuideName(String tourGuideName) {
        this.tourGuideName = tourGuideName;
    }

    // Getter & Setter untuk tourGuidePhone
    public String getTourGuidePhone() {
        return tourGuidePhone;
    }

    public void setTourGuidePhone(String tourGuidePhone) {
        this.tourGuidePhone = tourGuidePhone;
    }

    // Getter & Setter untuk tourGuidePic
    public String getTourGuidePic() {
        return tourGuidePic;
    }

    public void setTourGuidePic(String tourGuidePic) {
        this.tourGuidePic = tourGuidePic;
    }

    // Getter & Setter untuk price
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // Getter & Setter untuk bed
    public int getBed() {
        return bed;
    }

    public void setBed(int bed) {
        this.bed = bed;
    }

    // Getter & Setter untuk distance
    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    // Getter & Setter untuk score
    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
    public Item(String title, String description) {
        this.title = title;
        this.description = description;
    }

}
