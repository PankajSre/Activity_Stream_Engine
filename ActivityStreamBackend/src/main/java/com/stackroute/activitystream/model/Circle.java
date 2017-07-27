package com.stackroute.activitystream.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Circle extends StatusCode implements Serializable{

	private static final long serialVersionUID = -1147732076765756355L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int circleId;
	private String circleName;
	private String createdBy;
	private boolean status;
	private Date creationDate;
	
	public Circle() {
	}

	public int getCircleId() {
		return circleId;
	}

	public void setCircleId(int circleId) {
		this.circleId = circleId;
	}

	public String getCircleName() {
		return circleName;
	}

	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "Circle [circleId=" + circleId + ", circleName=" + circleName + ", createdBy=" + createdBy + ", status="
				+ status + ", creationDate=" + creationDate + "]";
	}
	
}
