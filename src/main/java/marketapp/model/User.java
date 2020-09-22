package marketapp.model;

public class User {

	private int id;
	private String name;
	private String password;
	private Role role; // Role object
	private String image;

	public User() {
		super();
		this.id = 0;
		this.name = "";
		this.password = "";
		this.role = new Role(); 						// Role object
		this.image = "https://i.imgur.com/u3p3o3R.png";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", role=" + role + ", image=" + image
				+ "]";
	}

}
