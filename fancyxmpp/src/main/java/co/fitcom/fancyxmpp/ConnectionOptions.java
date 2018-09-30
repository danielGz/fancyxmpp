package co.fitcom.fancyxmpp;

import java.util.Collection;

/**
 * Created by triniwiz on 6/22/18
 */
public class ConnectionOptions {
    private String username;
    private String password;
    private String domain;
    private String host;
    private Collection<String> sasl;
    private int port = 5222;
    private boolean compression = false;
    private String resource;

    public void setResource(String resource) {
        this.resource = resource;
    }

    public void setCompression(boolean compression) {
        this.compression = compression;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setSasl(Collection<String> sasl) {
        this.sasl = sasl;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<String> getSasl() {
        return sasl;
    }

    public int getPort() {
        return port;
    }

    public String getDomain() {
        return domain;
    }

    public String getHost() {
        return host;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean getCompression(){
        return  compression;
    }

    public String getResource() {
        return resource;
    }
}
