package dev.vintonlee.notepad.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class User {
//	+------------+--------------+------+-----+-------------------+-----------------------------+
//	| Field      | Type         | Null | Key | Default           | Extra                       |
//	+------------+--------------+------+-----+-------------------+-----------------------------+
//	| id         | int(11)      | NO   | PRI | NULL              | auto_increment              |
//	| username   | varchar(65)  | NO   |     | NULL              |                             |
//	| email      | varchar(255) | NO   |     | NULL              |                             |
//	| password   | varchar(280) | NO   |     | NULL              |                             |
//	| created_at | timestamp    | NO   |     | CURRENT_TIMESTAMP |                             |
//	| updated_at | timestamp    | NO   |     | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
//	| role       | varchar(45)  | YES  |     | NULL              |                             |
//	| enabled    | tinyint(4)   | NO   |     | 1                 |                             |
//	| first_name | varchar(80)  | YES  |     | NULL              |                             |
//	| last_name  | varchar(80)  | YES  |     | NULL              |                             |
//	+------------+--------------+------+-----+-------------------+-----------------------------+

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;

	private String email;

	private String password;

	@CreationTimestamp
	@Column(name = "created_at")
	private Date createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;

	private String role;

	private Boolean enabled;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Note> notes;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Image> images;

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", role=" + role + ", enabled=" + enabled
				+ ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
