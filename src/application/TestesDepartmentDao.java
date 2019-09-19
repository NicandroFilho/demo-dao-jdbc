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
        System.out.print("Enter the Department Id to be searched: ");
        int findId = sc.nextInt();
        sc.nextLine();
        Department dep = departmentDao.findById(findId);
        System.out.println(dep);
        System.out.println();

        System.out.println("TEST 2: Department Find All");
        List<Department> list = departmentDao.findAll();
        for (Department d : list){
            System.out.println(d);
        }
        System.out.println();

        System.out.println("TEST 3: Department Insert");
        System.out.print("Enter Department name to be Inserted into Data Base: ");
        String newDepName = sc.nextLine();
        Department newDep = new Department(null, newDepName);
        departmentDao.insert(newDep);
        System.out.println("New Department Created! Department Id: " + newDep.getId());
        System.out.println();

        System.out.println("TEST 4: Department Delete");
        System.out.print("Enter the Id to be Deleted: ");
        int delId = sc.nextInt();
        departmentDao.deleteById(delId);
        System.out.println();

        System.out.println("TEST 5: Department Update");
        System.out.print("Enter the Department Number which will be updated: ");
        int updateDepNum = sc.nextInt();
        sc.nextLine();
        Department depForUpdate = departmentDao.findById(updateDepNum);
        System.out.println("Updating Department "+ depForUpdate.getName());
        System.out.print("Enter new Department Name: ");
        String newName = sc.nextLine();
        depForUpdate.setName(newName);
        System.out.println("Updating DataBase");
        departmentDao.update(depForUpdate);
        System.out.println("Retrieving new Data from DataBase:");
        System.out.println(departmentDao.findById(depForUpdate.getId()));
        System.out.println();

        DB.closeConnection();
        sc.close();
    }
}
