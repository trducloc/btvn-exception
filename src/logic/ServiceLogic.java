package logic;

import entity.Service;

import java.util.InputMismatchException;
import java.util.Scanner;


public class ServiceLogic {
    private Service[] services;
    private int totalService;
    private ServiceLogic serviceLogic;

    public ServiceLogic(Service[] services) {
        this.services = services;
    }

    public Service[] getServices() {
        return services;
    }

    public void inputService() {
        int serviceNumber;

        System.out.print("Có bao nhiêu dịch vụ muốn thêm mới: ");
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
        for (int i = 0; i < serviceNumber; i++) {
            System.out.println("Nhập thông tin cho dịch vụ thứ : " + (i + 1));
            Service service = new Service();
            service.InputInfo();
            saveService(service);
        }
        totalService += serviceNumber;
    }
    private void saveService(Service service) {
        for (int j = 0; j < services.length; j++) {
            if(services[j] == null){
                services[j] = service;
                break;
            }
        }
    }
    public void showService() {
        for (int i = 0; i < services.length; i++) {
            if(services[i] != null) {
                System.out.println(services[i]);
            }
        }
    }
    public Service findServiceById(int serviceId) {
        Service result = null;
        for (int i = 0; i < services.length; i++) {
            if(services[i] != null && services[i].getId() == serviceId){
                result = services[i];
                break;
            }
        }
        return result;

    }
    public boolean isEmptyService(){
        boolean haveDataService = false;
        for (int i = 0; i < serviceLogic.getServices().length; i++) {
            if(serviceLogic.getServices()[i] != null){
                haveDataService = true;
                break;
            }
        }
        return haveDataService;
    }
}
