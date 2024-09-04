package com.tapfoods.models;

public class User {

	    private int userId;
	    private String username;
	    private String email;
	    private String password;
	    private String address;
	    private String phono;
	    

	    // Default constructor
	    public User() {
	    }

	    // Parameterized constructor
	    public User(int userId, String username, String email, String password, String address, String phono) {
	        this.userId = userId;
	        this.username = username;
	        this.email = email;
	        this.password = password;
	        this.address = address;
	        this.phono = phono;
	        
	    }

	    public User(String username, String email, String password, String address, String phono) {
			super();
			this.username = username;
			this.email = email;
			this.password = password;
			this.address = address;
			this.phono = phono;
		}

		// Getter and Setter methods
	    public int getUserId() {
	        return userId;
	    }

	    public void setUserId(int userId) {
	        this.userId = userId;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	    public String getPhono() {
	        return phono;
	    }

	    public void setPhono(String phono) {
	        this.phono = phono;
	    }

		@Override
		public String toString() {
			return "User [userId=" + userId + ", username=" + username + ", email=" + email + ", password=" + password
					+ ", address=" + address + ", phono=" + phono + "]";
		}

	   

	   

	   
	}


