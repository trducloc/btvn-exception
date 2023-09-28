package logic;

import entity.Service;
import entity.Client;
import entity.Bill;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private ServiceLogic serviceLogic;
    private ClientLogic clientLogic;
    private BillLogic billLogic;

    public Menu() {
        Client[] clients = new Client[100];
        clientLogic = new ClientLogic(clients);
        Service[] services = new Service[100];
        serviceLogic = new ServiceLogic(services);
        Bill[] bills = new Bill[100];
        billLogic = new BillLogic(clientLogic,serviceLogic, bills);

    }

    public void show() {
        while (true) {

                showMenu();
                int functionChoice = chooseFunction();
                switch (functionChoice) {
                    case 1:
                        clientLogic.inputClient();
                        break;
                    case 2:
                        clientLogic.showClient();
                        break;
                    case 3:
                        serviceLogic.inputService();

                        break;
                    case 4:
                        serviceLogic.showService();
                        break;
                    case 5:
                        billLogic.showBill();
                        break;
                    case 6:
                        menuSort();
                        break;
                    case 7:
                        billLogic.pay();
                        break;
                    case 8:
                        return;
                }
        }
    }

    private void menuSort() {
        System.out.println("_______________Các lựa chọn sắp xếp_______________");
        System.out.println("1. Theo Họ tên khách hàng.");
        System.out.println("2. Theo Số lượng sử dụng (giảm dần)");
        System.out.println("3. Trở lại menu chính.");
        int x = 0;
        System.out.print("Bạn chọn: ");
        do {
            try {
                x = new Scanner(System.in).nextInt();
                if (x == 1 || x == 2) {
                    break;
                }
                System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại: ");
            } catch (InputMismatchException e) {
                System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại: ");
            }
        } while(true);
        switch (x) {
            case 1:
                billLogic.sortBySaleName();
                break;
            case 2:
                billLogic.sortByQuantity();
                break;
        }
    }

    private static int chooseFunction() {
        System.out.print("Xin mời nhập lựa chọn: ");
        int functionChoice = 0;
            do {
                try {
                    functionChoice = new Scanner(System.in).nextInt();
                    if (functionChoice >= 1 && functionChoice <= 8) {
                        break;
                    }
                    System.out.print("Chức năng không hợp lệ, vui lòng lựa chọn (từ 1-8): ");
                } catch (InputMismatchException e) {
                    System.out.print("Chức năng không hợp lệ, vui lòng lựa chọn (từ 1-8): ");
                }
            } while(true);
            return functionChoice;
        }


    public void showMenu() {
            System.out.println("-----Menu-----");
            System.out.println("1. Nhập danh sách khách hàng mới.");
            System.out.println("2. In ra danh sách khách hàng.");
            System.out.println("3. Nhập danh sách dịch vụ.");
            System.out.println("4. In ra danh sách dịch vụ.");
            System.out.println("5. In danh sách hóa đơn cho mỗi khách hàng.");
            System.out.println("6. Sắp xếp danh sách hóa đơn.");
            System.out.println("7. Lập bảng kê số tiền phải trả cho mỗi khách hàng.");
            System.out.println("8. Thoát");
    }
}


