package com.bezkoder.springjwt.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.request.LoginRequest;
import com.bezkoder.springjwt.payload.request.SignupRequest;
import com.bezkoder.springjwt.payload.response.JwtResponse;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.jwt.JwtUtils;
import com.bezkoder.springjwt.security.services.UserDetailsImpl;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 userDetails.getDNI(),
												 userDetails.getDate(),
												 roles));
	}
	

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()), signUpRequest.getDNI(),null, null, null, null, signUpRequest.getDate());
		TO= signUpRequest.getEmail();
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					//.orElseThrow(() -> new RuntimeException("Error:(no hay role) Role is not found."));
			roles.add(userRole);
	}else  {
		strRoles.forEach(role -> {
			switch (role) {
			case "admin":
				Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(adminRole);

				break;
			default:
				Role userRole = roleRepository.findByName(ERole.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);
			}
		});
		}

		user.setRoles(roles);
		userRepository.save(user);
		sendEmail();
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	static final String FROM ="detectivecrashatos@gmail.com";
	static final String FROMNAME ="atoscarcrash";
	String TO = "";
	static final String SMTP_USERNAME ="detectivecrashatos@gmail.com";
	static final String SMTP_PASSWORD ="atos2021";
	static final String CONFIGSET ="ConfigSet";
	static final String HOST ="smtp.gmail.com";
	static final int PORT =587;
	static final String SUBJECT ="Código de confirmación";
	String BODY =
			"<p> Gracias por registrarte en la aplicación de <b>AtosCarCrash</b>, ahora podrás utilizar la sección de alquileres y editar tu perfil como usuario. <br><br>\n"
			+ "<h3>\n"
			+ "  ¿Cómo alquilo un vehículo?\n"
			+ "</h3>\n"
			+ "\n"
			+ "<p>\n"
			+ "  2. Me voy a la sección de <b>Alquiler</b>.\n"
			+ "  <br>\n"
			+ "  <br>\n"
			+ "  3. Escojo una <b>fecha y hora de recogida</b> del vehículo.\n"
			+ "  <br>\n"
			+ "  <br>\n"
			+ "  4. Escojo una <b>fecha y hora dedevolución</b> del vehículo.\n"
			+ "    <br>\n"
			+ "    <br>\n"
			+ "  5. Por último, elijo la  <b>ciudad de alquiler</b> en la que recogeré mi vehículo.\n"
			+ "  <br>\n"
			+ "  <br>\n"
			+ "  6. Ya estoy listo para disfrutar de mi vehículo gracias a <b>AtosCarCrash</b>.\n"
			+ "  \n"
			+ "<p>\n"
			+ "\n"
			+ "\n"
			+ "</p>";
	
	public void  sendEmail(){
		
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.stmp.port", PORT);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth","true");
		Session session = Session.getDefaultInstance(props);
		MimeMessage msg = new MimeMessage(session);
		Transport transport ;
		try {
			transport = session.getTransport();
			msg.setFrom(new InternetAddress(FROM, FROMNAME));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
			msg.setSubject(SUBJECT);
			msg.setContent(BODY, "text/html");
			msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
			transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
		}
		catch(Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
	}
	
}
