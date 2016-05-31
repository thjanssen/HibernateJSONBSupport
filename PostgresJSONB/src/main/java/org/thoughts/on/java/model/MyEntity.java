package org.thoughts.on.java.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import org.hibernate.annotations.Type;

@Entity
public class MyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column
	@Type(type = "MyJsonType")
	private MyJson jsonProperty;
	
	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public MyJson getJsonProperty() {
		return jsonProperty;
	}

	public void setJsonProperty(MyJson jsonProperty) {
		this.jsonProperty = jsonProperty;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof MyEntity)) {
			return false;
		}
		MyEntity other = (MyEntity) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (jsonProperty != null)
			result += "jsonProperty: " + jsonProperty.toString();
		return result;
	}
}