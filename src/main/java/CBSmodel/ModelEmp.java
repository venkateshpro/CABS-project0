package CBSmodel;

import java.util.Objects;

public class ModelEmp {

	private Integer id;
	private String name;
	private String email;
	private Boolean manager;
	private Boolean active;
	private String dept;
	
	
	public ModelEmp(Integer id, String name, String email, String dept) {
		this.manager = false;
		this.id = id;
		this.name = name;
		this.email = email;
		this.active = true;
		this.setDept(dept);
	}
	
	public ModelEmp(Integer id, String name, String email, Boolean manager, String dept) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.manager = manager;
		this.active = true;
		this.setDept(dept);
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public Boolean getManager() {
		return manager;
	}

	public Boolean getActive() {
		return active;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setManager(Boolean manager) {
		this.manager = manager;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelEmp other = (ModelEmp) obj;
		return Objects.equals(id, other.id);
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
	

}

