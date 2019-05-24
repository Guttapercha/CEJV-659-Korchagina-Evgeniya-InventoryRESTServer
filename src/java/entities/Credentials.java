package entities;

import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Credentials {
    
    private String email;
    private String password;
    
    public Credentials() {
        
    }

    public Credentials(String email, String password) {
        this.email = email;
        this.password = password;    
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
    
}

