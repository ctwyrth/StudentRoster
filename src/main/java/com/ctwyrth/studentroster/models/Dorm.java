package com.ctwyrth.studentroster.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

//import com.ctwyrth.studentroster.models.Student;

@Entity
@Table(name="dorms")
public class Dorm {
    // -------------------variables-------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Name can not be null or blanked.")
    @Size(min=3, max=20, message="Dorm names must be between 3 and 20 characters.")
    private String name;
    
    @OneToMany(mappedBy="dorm", fetch = FetchType.LAZY)
    private List<Student> students;

    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;

    // -------------------constructors (include an empty one)-------------------
    public Dorm() {}
    
    public Dorm(String name) {
    	this.name = name;
    }

    // -------------------methods-------------------
    // These tie to the mandatory variables above:
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
    public void removeStudent(Student student) {
    	for (int i = 0; i < this.students.size(); i++) {
    		if (this.students.get(i) == student) {
    			this.students.remove(i);
    		}
    	}
    }
    
    public void displayDorm() {
    	System.out.println(this.getId());
    	System.out.println(this.getName());
    	System.out.println(this.getStudents());
    }
    
    // -------------------getters & setters-------------------
    public Long getId() {
    	return id;
    }
    public void setId(Long id) {
    	this.id = id;
    }
    
    public String getName() {
    	return name;
    }
    public void setName(String name) {
    	this.name = name;
    }
    
    public List<Student> getStudents() {
    	return students;
    }
    public void setStudents(List<Student> students) {
    	this.students = students;
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

}