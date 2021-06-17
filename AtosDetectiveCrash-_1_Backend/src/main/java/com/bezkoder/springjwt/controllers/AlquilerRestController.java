package com.bezkoder.springjwt.controllers;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.Alquiler;
import com.bezkoder.springjwt.security.services.IAlquilerService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class AlquilerRestController {
	
	@Autowired
	private IAlquilerService alquilerService;
	
	@Autowired
    private alquilerRepository alquilerRepository;
	
	@GetMapping("/alquiler")
	public List<Alquiler> index(){
		return alquilerService.findAll();
	}
	
	@GetMapping("/alquiler/{id}")
	public Alquiler show(@PathVariable Long id){
		return alquilerService.findById(id);
	}
	
	@GetMapping("/alquileres/{cod_ref}")
    public List<Alquiler>buscaPorCodigoRef(@PathVariable String cod_ref){
        return alquilerRepository.buscaPorCodigoRef(cod_ref);
    }
	
	@PostMapping("/alquiler")
	@ResponseStatus(HttpStatus.CREATED)
	public Alquiler create(@RequestBody Alquiler alquiler){
		TO=alquiler.getEmail();
		System.out.println(alquiler.getEmail());
		BODY="";
		BODY+="<h1>Confirmación de reserva</h1>";
		BODY+="<p><h3>Hola "+alquiler.getEmail()+", tu código de reserva es <b><h2>"+alquiler.getCodRefrencia()+"</h2></b>"
				+ "<br>Tendrás que ir a recoger el vehículo a "+alquiler.getCiudadAlquiler();
		if(!alquiler.getCiudadAlquiler().equals(alquiler.getCiudadAlquilerDevolucion())) {
			BODY+="y devolverlo en "+alquiler.getCiudadAlquilerDevolucion()+"</h3></p>";
		} else {
			BODY+="</h3></p>";
		}
		sendEmail();
		return alquilerService.save(alquiler);
	}
	
	@PostMapping("/alquiler/email")
	@ResponseStatus(HttpStatus.CREATED)
	public void _sendEmail(){
		sendEmail();
	}
	
		
	@PutMapping("/alquiler/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Alquiler update(@RequestBody Alquiler alquiler,@PathVariable Long id){
		Alquiler alquilerActual=alquilerService.findById(id);
		alquilerActual.setId_coche(alquiler.getId_coche());
		alquilerActual.setId_cliente(alquiler.getId_cliente());
		alquilerActual.setFecha_recogida(alquiler.getFecha_recogida());
		alquilerActual.setFecha_devolucion(alquiler.getFecha_devolucion());
		alquilerActual.setHora_recogida(alquiler.getHora_recogida());
		alquilerActual.setCiudadAlquiler(alquiler.getCiudadAlquiler());
		alquilerActual.setCiudadAlquilerDevolucion(alquiler.getCiudadAlquilerDevolucion());
		alquilerActual.setNum_puertas(alquiler.getNum_puertas());
		alquilerActual.setCodRefrencia(alquiler.getCodRefrencia());
		alquilerActual.setEmail(alquiler.getEmail());
		alquilerActual.setHora_devolucion(alquiler.getHora_devolucion());
		alquilerActual.setMatricula_coche(alquiler.getMatricula_coche());
		alquilerActual.setGama(alquiler.getGama());
		return alquilerService.save(alquilerActual);
	}
	
	@DeleteMapping("/alquiler/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		alquilerService.delete(id);
	}
	
	static final String FROM ="detectivecrashatos@gmail.com";
	static final String FROMNAME ="atoscarcrash";
	String TO = "";
	static final String SMTP_USERNAME ="detectivecrashatos@gmail.com";
	static final String SMTP_PASSWORD ="atos2021";
	static final String CONFIGSET ="ConfigSet";
	static final String HOST ="smtp.gmail.com";
	static final int PORT =587;
	static final String SUBJECT ="Confirmacion de reserva ";
	String BODY = String.join(System.getProperty("line.separator"),
			"Reserva confirmada \n");
	
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
