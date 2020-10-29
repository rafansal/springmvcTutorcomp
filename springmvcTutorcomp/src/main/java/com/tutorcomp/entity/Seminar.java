package com.tutorcomp.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "seminar")
public class Seminar {
	
	public Seminar() {};
	
	public Seminar( String studentName,String tutorName,Date date) {
		this.date = date;
		this.studentName=studentName;
		this.tutorName=tutorName;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private int id;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name="studentId", insertable=false, updatable=false)
	private int studentId;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "studentId")
    private Student student;
	
	@Column(name="tutorId", insertable=false, updatable=false)
	private int tutorId;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "tutorId")
    private Tutor tutor;
	
	@Transient
	private String studentName;
	@Transient
	private String tutorName;
	@Transient
	private String dateString;
	
	public Seminar getDto() {
		Seminar dto = new Seminar();
		dto.setStudentId(this.getStudentId());
		dto.setTutorId(this.getTutorId());
		dto.setId(this.getId());
		dto.setDate(this.getDate());
		dto.setStudent(this.getStudent());
		dto.setTutor(this.getTutor());
		dto.setStudentName(this.getStudentName());
		dto.setTutorName(this.getTutorName());		
		dto.setDateString(this.getDateString());
		return dto;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getTutorId() {
		return tutorId;
	}
	public void setTutorId(int tutorId) {
		this.tutorId = tutorId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Tutor getTutor() {
		return tutor;
	}
	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getTutorName() {
		return tutorName;
	}
	public void setTutorName(String tutorName) {
		this.tutorName = tutorName;
	}
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}	
}
