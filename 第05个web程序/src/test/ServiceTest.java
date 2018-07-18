package test;

import service.Service;
import service.impl.ServiceImpl;

public class ServiceTest {

    public static void main(String[] args) {
        Service service = new ServiceImpl();
        System.out.println(service.verifyUser("name", "1234", "1234", "1234"));
    }
}
