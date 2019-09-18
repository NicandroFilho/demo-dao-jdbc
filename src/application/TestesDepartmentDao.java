package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;

import java.util.Scanner;

public class TestesDepartmentDao {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();


        DB.closeConnection();
        sc.close();
    }
}
