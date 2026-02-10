package model;

public class Train {
    private int trainId;
    private String trainName;
    private String source;
    private String destination;
    private int totalSeats;
    private int availableSeats;
    private double fare;
    private String travelDate;

    public Train(int trainId, String trainName, String source, String destination,
                 int totalSeats, int availableSeats, double fare, String travelDate) {
        this.trainId = trainId;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.fare = fare;
        this.travelDate = travelDate;
    }

    public int getTrainId() { return trainId; }
    public int getAvailableSeats() { return availableSeats; }

    @Override
    public String toString() {
        return trainId + " - " + trainName + " | " + source + " -> " + destination +
                " | Date: " + travelDate + " | Seats: " + availableSeats + " | Fare: " + fare;
    }
}
