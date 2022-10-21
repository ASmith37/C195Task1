package com.example.c195task1.model;

import java.time.LocalDateTime;

/** Implements the appointment class
 */
public class Appointment {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int customerId;
    private int userId;
    private int contactId;
    private String contact;

    /** Constructor for the appointment class
     * @param appointmentId a unique appointment ID
     * @param title a string of the appointment's title
     * @param description a string description of the appointment
     * @param location the location of the appointment
     * @param type a string describing the type of purpose of the appointment
     * @param start the start time of the appointment, in local time
     * @param end the end time of the appointment, in local time
     * @param createDate the date the appointment was created
     * @param createdBy the user who created the appointment
     * @param lastUpdate the datetime of most recent update of the appointment
     * @param lastUpdatedBy the user who last modified the appointment
     * @param customerId the ID of the customer for whom this appointment exists
     * @param userId a user ID of the program's user
     * @param contactId the contact at the business for whom the appointment is relevant
     * @param contact the contact name that goes with that ID
     */
    public Appointment(int appointmentId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, int customerId, int userId, int contactId, String contact) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
        this.contact = contact;
    }

    /** Gets the ID of the appointment
     * @return the integer ID
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /** Gets the title of the appointment
     * @return string containing title
     */
    public String getTitle() {
        return title;
    }

    /** Gets the description of the appointment
     * @return the string of the description
     */
    public String getDescription() {
        return description;
    }

    /** Gets the location of the appointment
     * @return a string contining the location
     */
    public String getLocation() {
        return location;
    }

    /** Gets the type of the appointment
     * @return a string describing the type of appointment
     */
    public String getType() {
        return type;
    }

    /** Gets the start time of the appointment
     * @return a LocalDateTime of the appointment's start, in the user's time
     */
    public LocalDateTime getStart() {
        return start;
    }
    /** Gets the end time of the appointment
     * @return a LocalDateTime of the appointment's end, in the user's time
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /** Gets the customer ID associated with the appointment
     * @return integer of customer ID
     */
    public int getCustomerId() {
        return customerId;
    }

    /** Gets the user ID contained in the appointment
     * @return user ID
     */
    public int getUserId() {
        return userId;
    }

    /** Gets the contact ID associated with the appointment
     * @return contact ID
     */
    public int getContactId() {
        return contactId;
    }

    /** Get the contact name associated with the appointment
     * @return contact as string
     */
    public String getContact() {
        return contact;
    }
}
