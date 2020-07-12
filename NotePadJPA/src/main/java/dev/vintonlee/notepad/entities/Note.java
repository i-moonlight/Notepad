package dev.vintonlee.notepad.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Note {

//	+------------+--------------+------+-----+-------------------+-----------------------------+
//	| Field      | Type         | Null | Key | Default           | Extra                       |
//	+------------+--------------+------+-----+-------------------+-----------------------------+
//	| id         | int(11)      | NO   | PRI | NULL              | auto_increment              |
//	| title      | varchar(200) | NO   |     | NULL              |                             |
//	| text       | text         | YES  |     | NULL              |                             |
//	| starred    | tinyint(4)   | YES  |     | 0                 |                             |
//	| created_at | timestamp    | YES  |     | CURRENT_TIMESTAMP |                             |
//	| updated_at | timestamp    | YES  |     | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
//	| user_id    | int(11)      | NO   | MUL | NULL              |                             |
//	+------------+--------------+------+-----+-------------------+-----------------------------+

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;

	private String text;

	private Boolean starred;

	@CreationTimestamp
	@Column(name = "created_at")
	private Date createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;

	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;

	@JsonIgnore
	@OneToMany(mappedBy = "note")
	private List<Image> images;

	@Override
	public String toString() {
		return "Note [id=" + id + ", title=" + title + ", text=" + text + ", starred=" + starred + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + ", user=" + user + "]";
	}

}
