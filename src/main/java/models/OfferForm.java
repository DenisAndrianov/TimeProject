package models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;


public class OfferForm {


    private User owner;
    @NotBlank
    private String note;
    @NotBlank
    private String timeStart;
    @NotBlank
    private String timeEnd;





    public OfferForm(User owner, String note, String timeStart, String timeEnd) {
        this.owner = owner;
        this.note = note;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public OfferForm() {
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Override
    public String toString() {
        return "OfferForm{" +
                "owner=" + owner +
                ", note='" + note + '\'' +
                ", timeStart='" + timeStart + '\'' +
                ", timeEnd='" + timeEnd + '\'' +
                '}';
    }
}
