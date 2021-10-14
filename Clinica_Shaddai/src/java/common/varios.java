package common;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ottoniel
 */


public class varios {

    //Documentacion para enviar correos 
    //https://www.campusmvp.es/recursos/post/como-enviar-correo-electronico-con-java-a-traves-de-gmail.aspx
    //DOCUMENTACION PARA ENVIAR CORREOS 
    //https://www.youtube.com/watch?v=R4a2bJ5r4N8

    //crear una regla en el firewall del puerto 587 
    public boolean  enviarCorreos(String distino, String usuario){
        String remitente = "clinicashaddaivn@gmail.com";
        String clave = "VnShaddai";
        String destino = "ottomansketa@gmail.com";
        
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", clave);
        
        Session session = Session.getDefaultInstance(props);
        MimeMessage mensaje = new MimeMessage(session);
        
        try {
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
            mensaje.setSubject("Registro de usuario - CLINICA SHADDAI", "text/html");
            mensaje.setText("<h2>Bienvidido a la Clicina Shaddai </h2> <br /> "
                    + "<br />"
                    + "Tus Credenciales son:"
                    + "USUARIO: " + usuario 
                    + "CONTRASEÑA: " + usuario 
                    + "<br />"                    
                    + "<a href='https://google.com'>Regresar a Citio</a>"
                    + "<br />");
            
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, clave);
            transport.sendMessage(mensaje, mensaje.getAllRecipients());
            transport.close();
            return true;
            
        } catch (Exception e) {
            System.out.println("error " +  e.getLocalizedMessage() + " otro " + e.getMessage() );
            e.printStackTrace();
            return false;
        }
        
    }
    
}
