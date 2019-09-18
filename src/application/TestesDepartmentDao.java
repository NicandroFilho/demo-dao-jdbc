package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;
import java.util.Scanner;

public class TestesDepartmentDao {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("TESTE 1: Department Find by Id");
        Department dep = departmentDao.findById(1);
        System.out.println(dep);
        System.out.println();

        System.out.println("TESTE 2: Department Find All");
        List<Department> list = departmentDao.findAll();
        for (Department d : list){
            System.out.println(d);
        }

        DB.closeConnection();
        sc.close();
    }
}
