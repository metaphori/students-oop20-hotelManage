package controller.Client;
import model.client.*;
import model.file.*;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class ControllerClientImpl implements ControllerClient {

    private final List<Client> list = new LinkedList<>();
    private List<String> lists = new LinkedList<>();
    private final URL clientUrl;
    private final String nameFile = "Clients.txt";
    private MyFile myfile;
    
    public ControllerClientImpl() {
        clientUrl = ClassLoader.getSystemResource(nameFile);
        myfile = new MyFileImpl(clientUrl);
        lists = myfile.fileReader();
        for (var i: lists) {
            list.add(new ClientImpl(i));
        }
    }
 
    @Override
    public List<String> getAllClient() {
        return lists;
    }

    @Override
    public void insertClient(final String name, final String surname, final String id) {
        lists.add(name + "." + surname + "." + id);
        list.add(new ClientImpl(name, surname, id));
        myfile.fileWriter(name + "." + surname + "." + id);
    }

    @Override
    public String getClient(final String id) {
        for (var i: list) {
            if ((i.getId()).equals(id)) {
                String s = i.getName() + "." + i.getSurname() + "." + id;
                return s;
            }
        }
        return null;
    }

    @Override
    public Boolean searchClient(String id) {
        if (this.getClient(id) != null) {
            return true;
        }
        return false;
    }

}
