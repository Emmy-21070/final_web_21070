package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Schedule {
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    private String isBooked;
    private String time;
    private Date scheduleDay;
    @ManyToOne
    private Producer producer;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIsBooked() {
		return isBooked;
	}
	public void setIsBooked(String isBooked) {
		this.isBooked = isBooked;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Date getScheduleDay() {
		return scheduleDay;
	}
	public void setScheduleDay(Date scheduleDay) {
		this.scheduleDay = scheduleDay;
	}
	public Producer getProducer() {
		return producer;
	}
	public void setProducer(Producer producer) {
		this.producer = producer;
	}
    
    
}
