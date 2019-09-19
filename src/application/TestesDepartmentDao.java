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

        System.out.println("TEST 1: Department Find by Id");
        Department dep = departmentDao.findById(1);
        System.out.println(dep);
        System.out.println();

        System.out.println("TEST 2: Department Find All");
        List<Department> list = departmentDao.findAll();
        for (Department d : list){
            System.out.println(d);
        }
        System.out.println();

        System.out.println("TEST 3: Department Insert");
        dep = new Department(null, "Food");
        departmentDao.insert(dep);
        System.out.println("New Department Created! Department Id: " + dep.getId());
        System.out.println();

        System.out.println("TEST 4: Department Delete");
        System.out.print("Enter the Id to be Deleted: ");
        int id = sc.nextInt();
        departmentDao.deleteById(id);
        System.out.println();

        System.out.println("TEST 5: Department Update");
        System.out.println("Updating Department "+ dep.getName());
        System.out.print("Enter new Department Name: ");
        String newName = sc.nextLine();
        dep.setName(newName);
        departmentDao.update(dep);
        System.out.println("Retrieving new Data from DataBase:");
        System.out.println(departmentDao.findById(dep.getId()));
        System.out.println();

        DB.closeConnection();
        sc.close();
    }
}
