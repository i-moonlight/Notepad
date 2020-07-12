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

import lombok.Data;

@Entity
@Data
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

	@Override
	public String toString() {
		return "Image [id=" + id + ", urlLink=" + urlLink + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ "]";
	}

}
