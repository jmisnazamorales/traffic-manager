package co.edu.uniandes.traffic.manager.domain;

import java.time.LocalDateTime;

public class Trip {
    private String vendor;
    private LocalDateTime pickUpTime;
    private LocalDateTime dropOffTime;
    private Integer passengerCount;
    private Double tripDistance;
    private Double pickUpLonPickUp;
    private Double pickUpLatPickUp;
    private Integer rateCode;
    private Integer storeFlag;
    private Double pickUpLonDropOff;
    private Double pickUpLatDropOff;
    private String paymentType;
    private Double fareAmount;
    private Double sureCharge;
    private Double mtaTax;
    private Double tipAmount;
    private Double tollsAmount;
    private Double totalAmount;


    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public LocalDateTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalDateTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public LocalDateTime getDropOffTime() {
        return dropOffTime;
    }

    public void setDropOffTime(LocalDateTime dropOffTime) {
        this.dropOffTime = dropOffTime;
    }

    public Integer getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(Integer passengerCount) {
        this.passengerCount = passengerCount;
    }

    public Double getTripDistance() {
        return tripDistance;
    }

    public void setTripDistance(Double tripDistance) {
        this.tripDistance = tripDistance;
    }

    public Double getPickUpLonPickUp() {
        return pickUpLonPickUp;
    }

    public void setPickUpLonPickUp(Double pickUpLonPickUp) {
        this.pickUpLonPickUp = pickUpLonPickUp;
    }

    public Double getPickUpLatPickUp() {
        return pickUpLatPickUp;
    }

    public void setPickUpLatPickUp(Double pickUpLatPickUp) {
        this.pickUpLatPickUp = pickUpLatPickUp;
    }

    public Integer getRateCode() {
        return rateCode;
    }

    public void setRateCode(Integer rateCode) {
        this.rateCode = rateCode;
    }

    public Integer getStoreFlag() {
        return storeFlag;
    }

    public void setStoreFlag(Integer storeFlag) {
        this.storeFlag = storeFlag;
    }

    public Double getPickUpLonDropOff() {
        return pickUpLonDropOff;
    }

    public void setPickUpLonDropOff(Double pickUpLonDropOff) {
        this.pickUpLonDropOff = pickUpLonDropOff;
    }

    public Double getPickUpLatDropOff() {
        return pickUpLatDropOff;
    }

    public void setPickUpLatDropOff(Double pickUpLatDropOff) {
        this.pickUpLatDropOff = pickUpLatDropOff;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Double getFareAmount() {
        return fareAmount;
    }

    public void setFareAmount(Double fareAmount) {
        this.fareAmount = fareAmount;
    }

    public Double getSureCharge() {
        return sureCharge;
    }

    public void setSureCharge(Double sureCharge) {
        this.sureCharge = sureCharge;
    }

    public Double getMtaTax() {
        return mtaTax;
    }

    public void setMtaTax(Double mtaTax) {
        this.mtaTax = mtaTax;
    }

    public Double getTipAmount() {
        return tipAmount;
    }

    public void setTipAmount(Double tipAmount) {
        this.tipAmount = tipAmount;
    }

    public Double getTollsAmount() {
        return tollsAmount;
    }

    public void setTollsAmount(Double tollsAmount) {
        this.tollsAmount = tollsAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
