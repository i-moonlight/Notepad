package dev.vintonlee.notepad.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Image {

//	+------------+--------------+------+-----+-------------------+-----------------------------+
//	| Field      | Type         | Null | Key | Default           | Extra                       |
//	+------------+--------------+------+-----+-------------------+-----------------------------+
//	| id         | int(11)      | NO   | PRI | NULL              | auto_increment              |
//	| url_link   | varchar(245) | YES  |     | NULL              |                             |
//	| created_at | datetime     | YES  |     | CURRENT_TIMESTAMP |                             |
//	| updated_at | datetime     | YES  |     | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
//	| note_id    | int(11)      | YES  | MUL | NULL              |                             |
//	| user_id    | int(11)      | YES  | MUL | NULL              |                             |
//	+------------+--------------+------+-----+-------------------+-----------------------------+

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "url_link")
	private String urlLink;

	@CreationTimestamp
	@Column(name = "created_at")
	private Date createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@ManyToOne()
	@JoinColumn(name = "note_id")
	private Note note;

	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;

	public Image() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrlLink() {
		return urlLink;
	}

	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", urlLink=" + urlLink + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ "]";
	}

}
