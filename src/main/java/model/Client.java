package model;

/**
 * @author Borza Diana-Cristina
 * Clasa ce defineste un obiect de tipul Client care contine atributele specifice tabelei din baza de date specifice unui client
 * si care contine constructori si metode de set si get
 */
public class Client {
    private int id;
    private String clientName;
    private String emailAddress;
    private String gender;
    private String phoneNumber;

    public Client(){

    }

    public Client(int id, String name, String emailAddress, String gender, String phoneNumber) {
        this.id = id;
        this.clientName = name;
        this.emailAddress = emailAddress;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public Client(String name, String emailAddress, String gender, String phoneNumber) {
        this.clientName = name;
        this.emailAddress = emailAddress;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + clientName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", gender=" + gender +
                '}';
    }
}