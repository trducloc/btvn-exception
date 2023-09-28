package logic;

import entity.Client;


import java.util.InputMismatchException;
import java.util.Scanner;

public class ClientLogic {
    private Client[] clients;
    private int totalClient;
    private ClientLogic clientLogic;


    public ClientLogic(Client[] clients) {
        this.clients = clients;
    }

    public Client[] getClients() {
        return clients;
    }

    public int getTotalClient() {
        return totalClient;
    }

    public void inputClient(){
        System.out.print("Có bao nhiêu khách hàng muốn thêm mới: ");
        int clientNumber;
        do {
            try {
                clientNumber = new Scanner(System.in).nextInt();
                if (clientNumber > 0) {
                    break;
                }
                System.out.print("Số lượng khách phải là một số nguyên dương, vui lòng nhập lại: ");
            } catch(InputMismatchException e){
                System.out.print("Số lượng khách phải là một số nguyên dương, vui lòng nhập lại:");
            }
        } while (true);
        for (int i = 0; i < clientNumber; i++) {
            System.out.println("Nhập thông tin cho khách hàng thứ: " + (i+1));
            Client client = new Client();
            client.InputInfo();
            saveClient(client);
        }
    }
    private void saveClient(Client client) {
        for (int i = 0; i < clients.length; i++) {
            if(clients[i] == null){
                clients[i] = client;
                break;
            }
        }

    }
    public void showClient() {
        for (int i = 0; i < clients.length; i++) {
            if (clients[i] != null) {
                System.out.println(clients[i]);
            }
        }
    }
    public Client findClientById(int clientId) {
        Client result = null;
        for (int i = 0; i < clients.length; i++) {
            if(clients[i] != null && clients[i].getId() == clientId){
                result = clients[i];
                break;
            }
        }
        return result;

    }
    public boolean isEmptyClient(){
        boolean haveDataClient = false;
        for (int i = 0; i < clientLogic.getClients().length; i++) {
            if(clientLogic.getClients()[i] != null){
                haveDataClient = true;
                break;
            }
        }
        return haveDataClient;
    }



}

