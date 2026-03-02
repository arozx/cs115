/**
 * Represents a participant with their health data.
 */
public class Participant implements Comparable<Participant> {
    private String participantId;
    private String dateOfDataCollection;
    private String timeOfDataCollection;
    private int heartRate;

    /**
     * Constructs a new Participant with the given details.
     *
     * @param participantId        The ID of the participant.
     * @param dateOfDataCollection The date the data was collected.
     * @param timeOfDataCollection The time the data was collected.
     * @param heartRate            The computed heart rate.
     */
    public Participant(String participantId, String dateOfDataCollection, String timeOfDataCollection, int heartRate) {
        this.participantId = participantId;
        this.dateOfDataCollection = dateOfDataCollection;
        this.timeOfDataCollection = timeOfDataCollection;
        this.heartRate = heartRate;
    }

    /**
     * Gets the participant ID.
     *
     * @return The participant ID.
     */
    public String getParticipantId() {
        return participantId;
    }

    /**
     * Sets the participant ID.
     *
     * @param participantId The participant ID to set.
     */
    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    /**
     * Gets the date of data collection.
     *
     * @return The date of data collection.
     */
    public String getDateOfDataCollection() {
        return dateOfDataCollection;
    }

    /**
     * Sets the date of data collection.
     *
     * @param dateOfDataCollection The date to set.
     */
    public void setDateOfDataCollection(String dateOfDataCollection) {
        this.dateOfDataCollection = dateOfDataCollection;
    }

    /**
     * Gets the time of data collection.
     *
     * @return The time of data collection.
     */
    public String getTimeOfDataCollection() {
        return timeOfDataCollection;
    }

    /**
     * Sets the time of data collection.
     *
     * @param timeOfDataCollection The time to set.
     */
    public void setTimeOfDataCollection(String timeOfDataCollection) {
        this.timeOfDataCollection = timeOfDataCollection;
    }

    /**
     * Gets the heart rate.
     *
     * @return The heart rate.
     */
    public int getHeartRate() {
        return heartRate;
    }

    /**
     * Sets the heart rate.
     *
     * @param heartRate The heart rate to set.
     */
    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    /**
     * Formats the participant object as a string.
     * 
     * @return The formatted string representation of the participant.
     */
    @Override
    public String toString() {
        return String.format("%-15s%-15s%-15s%d", participantId.toUpperCase(), dateOfDataCollection,
                timeOfDataCollection, heartRate);
    }

    /**
     * Compares this participant with another based on heart rate.
     *
     * @param other The other participant to compare with.
     * @return A negative integer, zero, or a positive integer as this object is
     *         less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Participant other) {
        return Integer.compare(this.heartRate, other.heartRate);
    }
}
