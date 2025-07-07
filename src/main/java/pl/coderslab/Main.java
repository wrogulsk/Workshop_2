package pl.coderslab;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import java.util.Arrays;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        User firstUser = new User(1, "Zbyszek Zamachowski", "zbychu@wp.pl", "TruDneHasLo");
        User secondUser = new User(2, "Janek Kowalewski", "kowal@op.pl", "NieDoZlamaniaHaslo");
        User thirdUser = new User(3, "Maciej Maciejewski", "maciejo@onet.pl", "TruDneHasLoBaRDzo");
        User fourthUser = new User(4, "Tomasz Chciwoski", "chcij123@wp.pl", "HAsLONIedOZdaRCIa");
        UserDao userDao = new UserDao();
//        userDao.create(firstUser);
//        userDao.create(secondUser);
//        userDao.create(thirdUser);
//        userDao.create(fourthUser);
//        userDao.update(secondUser);
//        userDao.findAll();
//        userDao.read(3);
//        User userToEdit = new User();
        userDao.delete(3);
//userDao.delete(4);
//userDao.findAll();
//        userToEdit = userDao.read(6);
//        userDao.update(userToEdit);
//        User fourthUser = new User(1, "Zbyszek Zamachowski", "zbychu@wp.pl", "TruDneHasLo");
//        userDao.create(fourthUser);
//        User userToEdit = new User();
//        userToEdit = userDao.read(7);
//        userDao.update(userToEdit);
        //userDao.findAll();
        //System.out.println(Arrays.toString(userDao.findAll()));

    }
}
