package bll;

import bll.validators.EmailValidator;
import bll.validators.PhoneNumberValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Borza Diana-Cristina
 * Clasa care contine metodele specifice clientului
 */
public class ClientBLL {
    private List<Validator<Client>> validatorList;
    private ClientDAO client;

    /**
     * Constructorul clasei
     */
    public ClientBLL(){
        validatorList = new ArrayList<>();
        validatorList.add(new EmailValidator());
        validatorList.add(new PhoneNumberValidator());
        client = new ClientDAO();
    }
    /**
     * Metoda ce returneaza un Client cu id-ul specificat
     * @param id id-ul clientului cautat
     * @return Clientul cu id-ul cautat
     */
    public Client findClientWithSpecifiedID(int id){
        Client foundClient = client.findById(id);
        if(foundClient == null)
            throw new NoSuchElementException("The client with the id "+id+" was not found!");
        return foundClient;
    }
    /**
     * Metoda care sterge din baza de date clientul cu un id specificat
     * @param id id-ul clientului pe care dorim sa il stergem
     */
    public int deleteClientWithSpecifiedID(int id){
        int isDeleted = client.delete(id);
        if(!(isDeleted>0))
            try {
                throw new Exception("The client with id "+id+" was not deleted!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        return isDeleted;
    }

    /**
     * Metoda care editeaza un client existent cu id-ul id
      * @param client noile atribute ale clientului
     * @param id id-ul clientului cautat
     */
    public int editClientWithSpecifiedId(Client client,int id){
        int isEdited = this.client.update(client,id);
        if(!(isEdited>0))
            try {
                throw new Exception("The client with id "+id+" was not edited!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        return isEdited;
    }

    /**
     * Metoda care insereaza in baza de date un client
     * @param client clientul pe care dorim sa il inseram
     */
    public int insertClient(Client client){
        int var = this.client.insert(client);
        if(!(var>0))
            try {
                throw new Exception("The client was not inserted!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        return var;
    }

    /**
     * Metoda care returneaza o lista cu toti clientii aflati in baza de date
     * @return
     */
    public List<Object> getClientsList(){
        List<Object> clients = client.findAll();
        if(clients.size()==0 || clients==null)
            throw new NoSuchElementException("The list of clients is empty!");
        return clients;
    }

    /**
     * Metoda care returneaza o lista cu numele tuturor clientilor existenti in baza de date
     * @return
     */
    public List<String> getClientsName(){
        List<String>  clients = client.getAllClientsName();
        if(clients.size()==0 || clients==null)
            throw new NoSuchElementException("The list of clients is empty!");
        return clients;
    }


}
