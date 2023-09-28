package entity;

import constant.ClientType;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Client implements InputTable {
    private static int AUTO_ID = 10000;
    private int id;
    private String name;
    private String address;
    private String phone;
    private ClientType clientType;

    public Client() {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.clientType = clientType;
    }





    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", clientType=" + clientType.value +
                '}';
    }

    public void InputInfo(){
        this.id = AUTO_ID;
        AUTO_ID++;
        System.out.print("Nhập họ tên khách hàng: ");
        this.setName(new Scanner(System.in).nextLine());
        System.out.print("Nhập địa chỉ: ");
        this.setAddress(new Scanner(System.in).nextLine());
        System.out.print("Nhập SDT: ");
        this.setPhone(new Scanner(System.in).nextLine());
        System.out.println("Chọn loại khách hàng, chọn 1 trong các loại dưới đây: ");
        System.out.println("1. Cá nhân");
        System.out.println("2. Đại diện đơn vị hành chính");
        System.out.println("3. Đại diện đơn vị kinh doanh");
        int type = 0;
        System.out.print("Lựa chọn của bạn: ");
        do{
            try {
                type = new Scanner(System.in).nextInt();
                if (type >= 1 && type <= 3) {
                    break;
                }
                System.out.println("Lựa chọn không hợp lệ, chọn lại: ");
            }catch (InputMismatchException e){
                System.out.println("Lựa chọn không hợp lệ, chọn lại: ");
            }
        }while(true);
        switch(type){
            case 1:
                this.setClientType(ClientType.INDIVIDUAL);
                break;
            case 2:
                this.setClientType(ClientType.ADMINISTRATIVE_REPRESENTATIVE);
                break;
            case 3:
                this.setClientType(ClientType.BUSSINESS_REPRESENTATIVE);
                break;

        }
    }
}
