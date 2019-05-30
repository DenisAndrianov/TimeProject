package models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    private User owner;

    @ManyToOne
    private User sign;

    @NotBlank
    private String note;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStart;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeEnd;

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", owner=" + owner +
                ", sign=" + sign +
                ", note='" + note + '\'' +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                '}';
    }

    public Offer() {
    }

    public Offer(User owner, String note, Date timeStart, Date timeEnd) {
        this.owner = owner;
        this.sign = sign;
        this.note = note;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getSign() {
        return sign;
    }

    public void setSign(User sign) {
        this.sign = sign;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }
}
