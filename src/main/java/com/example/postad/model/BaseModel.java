package com.example.postad.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@MappedSuperclass
@Getter
@Setter
public class BaseModel extends BaseIdModel {

	private static final long serialVersionUID = 1L;

	@Column(name = "updated_date", nullable = false)
	private LocalDateTime updatedDate;

	@Column(name = "created_date", nullable = false)
	private LocalDateTime createdDate;

	@PrePersist
	public void prePersist() {
		this.createdDate = LocalDateTime.now(ZoneOffset.of("+03:00"));
		this.updatedDate = LocalDateTime.now(ZoneOffset.of("+03:00"));
	}

	@PreUpdate
	public void preUpdate() {
		this.updatedDate = LocalDateTime.now(ZoneOffset.of("+03:00"));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((updatedDate == null) ? 0 : updatedDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseModel other = (BaseModel) obj;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (updatedDate == null) {
			if (other.updatedDate != null)
				return false;
		} else if (!updatedDate.equals(other.updatedDate))
			return false;
		return true;
	}

}