package logic;

import entity.Client;
import entity.Bill;
import entity.BillDetail;
import entity.Service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BillLogic {
    private final ClientLogic clientLogic;
    private final ServiceLogic serviceLogic;
    private Bill[] bills;
    private BillDetail[] billDetails;

    public BillLogic(ClientLogic clientLogic, ServiceLogic serviceLogic, Bill[] bills) {
        this.clientLogic = clientLogic;
        this.serviceLogic = serviceLogic;
        this.bills = bills;
    }


    public void list() {
        if (!clientLogic.isEmptyClient()) {
            System.out.println("Chưa có thông tin khách hàng, vui lòng nhập trước.");
            return;
        }
        System.out.println("Cần in danh sách hóa đơn cho bao nhiêu khách hàng: ");
        int clientNumber;
        do {
            try {
                clientNumber = new Scanner(System.in).nextInt();
                if (clientNumber > 0 && clientNumber <= clientLogic.getTotalClient()) {
                    break;
                }
                System.out.println("Số lượng khách hàng cần in hóa đơn phải là một số nguyên dương và nhỏ hơn tổng số lượng khách hàng của cửa hàng.");
            }catch(InputMismatchException e){
                System.out.println("Số lượng khách hàng cần in hóa đơn phải là một số nguyên dương");
            }
        } while (true);
        for (int i = 0; i < clientNumber; i++) {
            System.out.println("Nhập thông tin cho khách hàng thứ: " + (i + 1));
            Client client = inputClientForList();
            BillDetail[] details = inputBillDetail();
            Bill bill = new Bill(client, details);
            saveList(bill);
        }
        showBill();
    }

    private BillDetail[] inputBillDetail() {
        if (!serviceLogic.isEmptyService()) {
            System.out.println("Chưa có thông tin dịch vụ, vui lòng nhập trước.");
            return new BillDetail[0];
        }
        int serviceNumber;
        System.out.println("Khách hàng này sử dụng bao nhiêu dịch vụ: ");
        do {
            try{
            serviceNumber = new Scanner(System.in).nextInt();
                if (serviceNumber > 0) {
                    break;
                }
                System.out.println("Số lượng dịch vụ phải là một số nguyên dương, vui lòng nhập lại: ");
            }catch(InputMismatchException e){
                System.out.println("Số lượng dịch vụ phải là một số nguyên dương, vui lòng nhập lại: ");
            }
        } while(true);

        BillDetail[] details = new BillDetail[serviceNumber];
        int count = 0;
        for (int i = 0; i < serviceNumber; i++) {
            System.out.println("Nhập thông tin cho dịch vụ thứ: " + (i + 1));
            System.out.println("Nhập mã dịch vụ: ");
            int serviceId;
            Service service = null;
            do {
                try {
                    serviceId = new Scanner(System.in).nextInt();
                    for (int k = 0; k < serviceLogic.getServices().length; k++) {
                        if (serviceLogic.getServices()[k] != null && serviceLogic.getServices()[k].getId() == serviceId) {
                            service = serviceLogic.getServices()[k];
                            break;
                        }
                    }
                    if (service != null) {
                        break;
                    }
                    System.out.println("Không có dịch vụ mang mã " + serviceId + ",vui lòng nhập lại: ");
                } catch (InputMismatchException e) {
                    System.out.println("Mã dịch vụ phải là một nguyên có 3 chữ số, vui lòng nhập lại:");
                }
            } while (true);

            System.out.println("Dịch vụ mang mã  " + service.getId() + " được sử dụng số lượng bao nhiêu: ");
            int quantity;
            do {
                try {
                    quantity = new Scanner(System.in).nextInt();
                    if (quantity > 0) {
                        break;
                    }
                    System.out.println("Số lượng sử dụng phải là một số nguyên dương, vui lòng nhập lại: ");
                }catch(InputMismatchException e){
                    System.out.println("Số lượng sử dụng phải là một số nguyên dương, vui lòng nhập lại: ");
                }
            } while (true);
            BillDetail detail = new BillDetail(service, quantity);
            details[count] = detail;
            count++;
        }
        return details;
    }

    public void pay(){
        for (int i = 0; i < bills.length; i++) {
            double pay = 0;
            Bill serviceNumbers = bills[i];
            BillDetail[] listService = serviceNumbers.getBillDetails();
            for (int j = 0; j < listService.length; j++) {
                pay += listService[j].getService().getPrice() * listService[j].getQuantity();
            }
            System.out.println("Số tiền phải trả cho mỗi khách hàng " + bills[i].getClient().getName() + " là " + pay);
        }
    }



    public void sortBySaleName(){
        if(!isEmpty()){
            System.out.println("Chưa có thông tin khách hàng, vui lòng nhập trước khi thực hiện.");
            return;
        }
        for (int i = 0; i < bills.length -1; i++) {
            if(bills[i] == null){
                continue;
            }
            for (int j = i +1 ; j < bills.length; j++) {
                if(bills[j] == null){
                    continue;
                }
                if(bills[i].getClient().getName().trim().compareToIgnoreCase(bills[j].getClient().getName().trim()) > 0){
                    Bill temp = bills[i];
                    bills[i] = bills[j];
                    bills[j] = temp;
                }
            }
        }
        showBill();
    }

    public void sortByQuantity() {
        if (!isEmptyQuantity()) {
            System.out.println("Chưa có thông tin dịch vụ sử dụng, vui lòng nhập trước khi thực hiện");
            return;
        }
        for (int i = 0; i < bills.length - 1; i++) {
            if (bills[i] == null) {
                continue;
            }
            for (int j = i+1; j < bills.length; j++) {
                if (bills[j] == null) {
                    continue;
                }
                if (bills[i].getTotalQuantity() < bills[j].getTotalQuantity()){
                    Bill temp = bills[i];
                    bills[i] = bills[j];
                    bills[j] = temp;
                }
            }
        }
        showBill();
    }




    private Client inputClientForList() {
        System.out.println("Nhập mã khách hàng : ");
        int clientId;
        Client client;
        do{
            clientId = new Scanner(System.in).nextInt();
            client = clientLogic.findClientById(clientId);
            if(client != null){
                break;
            }
            System.out.print("Không tồn tại khách hàng mang mã " + clientId + ", vui lòng nhập lại: ");
        }while (true);
        return client;
    }


    private void saveList(Bill bill) {
        for (int i = 0; i < bills.length; i++) {
            if(bills[i] == null ){
                bills[i] = bill;
                break;
            }
        }
    }
    public void showBill(){
        for (int i = 0; i < bills.length; i++) {
            if(bills[i] != null){
                System.out.println(bills[i]);
            }
        }
    }
    private boolean isEmpty(){
        boolean haveData = false;
        for (int i = 0; i < bills[i].getClient().getName().length(); i++) {
            if(clientLogic.getClients()[i] != null){
                haveData = true;
                break;
            }
        }
        return haveData;
    }
    private boolean isEmptyQuantity() {
        boolean haveData = false;
        for (int i = 0; i < billDetails[i].getQuantity(); i++) {
            if(serviceLogic.getServices()[i] != null){
                haveData = true;
                break;
            }
        }
        return haveData;
    }
    }


