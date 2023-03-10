package gui.fx.app.restclient.dto;

public class UserDto {

	private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String roleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

    @Override
    public String toString() {
        return
                "id=" + id +
                ", email= " + email +
                ", firstName= " + firstName +
                ", lastName= " + lastName +
                ", username= " + username +
                ", roleName= " + roleName;
    }
}
