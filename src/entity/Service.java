package entity;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Service implements InputTable {
    private static int AUTO_ID = 100;
    private int id;
    private String name;
    private float price;
    private String unit;

    public Service() {
        this.id = id;
        this.name = name;
        this.price = price;
        this.unit = unit;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public int getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getPrice() {
        return price;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
    public void InputInfo() {
        this.id = AUTO_ID;
        AUTO_ID++;
        System.out.print("Nhập tên dịch vụ: ");
        this.setName(new Scanner(System.in).nextLine());
        System.out.print("Nhập đơn vị tính: ");
        this.setUnit(new Scanner(System.in).nextLine());
        do {
            try {
                System.out.print("Nhập giá cước: ");
                this.setPrice(new Scanner(System.in).nextInt());
            } catch (InputMismatchException e) {
                System.out.println("Giá cước phải là một số lớn hơn 0");
            }
        } while (true);

    }
}
