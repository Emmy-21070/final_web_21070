package controller;

import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;

import dao.AppointmentDao;
import dao.UserDao;
import domain.Appointment;
import domain.User;

@ManagedBean
@SessionScoped
public class UserController {
	public User user = new User();
	public UserDao userDao = new UserDao();
	public String username = new String("");
	public String password = new String("");
	public String accountType = new String("");
	public String gender = new String("");
	static User loggedInUser;
	
	public List<User> users = new UserDao().findAllUsers();
	public List<User> artists = new UserDao().getUserByRole("artist");
	public List<User> producers = new UserDao().getUserByRole("producer");
	public List<Appointment> appointments = new AppointmentDao().findAllAppointments();
	public String scheduletime = new String("");
	private String searchKey =new String("");
	
	public String login() {
		try {
			User activeUser = new UserDao().getUserByUsername(username);
			System.out.println("USER: "+activeUser.getFirstName());
			loggedInUser = activeUser;
			boolean pswdComparison = BCrypt.checkpw(password, activeUser.getPassword());
			if(pswdComparison && activeUser.getIsActive().equalsIgnoreCase("ACTIVE")) {
				if(activeUser.getRole().equalsIgnoreCase("admin")) {
					return "admin/dashboard.xhtml";
				}else if(activeUser.getRole().equalsIgnoreCase("artist")) {
					return "artist/dashboard.xhtml";
				}else if(activeUser.getRole().equalsIgnoreCase("producer")) {
					return "producer/dashboard.xhtml";
				}
			}else {
				System.out.println("LOGIN FAILED FOR USERNAME: "+activeUser.getIsActive());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return "";
	}
	
	public String createAccount() {
		if(username.isBlank() || username.isEmpty()) {
			// JSFMessagers.addErrorMessage("Failed to register, camp name is required ");
		   System.out.println("Name is empty");
			return "";
		}else if(password.isBlank() || password.isEmpty()) {
			// JSFMessagers.addErrorMessage("Failed to register, camp name is required ");
			   System.out.println("password is empty");
				return "";
		}
		else if(accountType.isBlank() || accountType.isEmpty()) {
			// JSFMessagers.addErrorMessage("Failed to register, camp name is required ");
			   System.out.println("password is empty");
				return "";
		}
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		
		if(accountType.equalsIgnoreCase("admin")) {
			user.setRole("admin");
		}else if(accountType.equalsIgnoreCase("artist")) {
			user.setRole("artist");
		}else if(accountType.equalsIgnoreCase("producer")) {
			user.setRole("producer");
		}
		user.setIsActive("ACTIVE");
		user.setUser_name(username);
		user.setPassword(hashedPassword);
		sendEmail(username);
		userDao.Create(user);
		user= new User();
		return "";
	}
	
	public void sendEmail(String recipientEmail) {
		System.setProperty("https.protocols", "TLSv1.2");
		
		// Sender's email and app password
        final String username = "auca.emmy@gmail.com";
        final String password = "vlei zlor wtgg ybht";

        // Recipient's email
        String toEmail = recipientEmail;

        // Set up SMTP properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Enables STARTTLS
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); // Port for STARTTLS
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");


        props.put("mail.debug", "true");


        // Get the Session object
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object
            Message message = new MimeMessage(session);

            // Set From: header field
            message.setFrom(new InternetAddress(username));

            // Set To: header field
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

            // Set Subject
            message.setSubject("Account Creation");

            // Set Body
            message.setText("Hello! This is a confirmation email of your created account.");

            // Send the message
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}
	
	public String getContextPath() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		return request.getContextPath();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<User> getArtists() {
		return artists;
	}

	public void setArtists(List<User> artists) {
		this.artists = artists;
	}

	public List<User> getProducers() {
		return producers;
	}

	public void setProducers(List<User> producers) {
		this.producers = producers;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public String getScheduletime() {
		return scheduletime;
	}

	public void setScheduletime(String scheduletime) {
		this.scheduletime = scheduletime;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	
	
	

}
