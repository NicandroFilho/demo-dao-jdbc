package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "INSERT INTO seller "
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDate(3, new Date(obj.getBirthDate().getTime()));
            st.setDouble(4, obj.getBaseSalary());
            st.setInt(5, obj.getDepartment().getId());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            }else {
                throw new DbException("Unexpected Error! No rows affected!");
            }

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Seller obj) {
        // TODO: 17/09/2019
    }

    @Override
    public void deleteById(Integer id) {
        // TODO: 17/09/2019
    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT seller.* ,department.Name as DepName "
                    +"FROM seller INNER JOIN department "
                    +"ON seller.DepartmentId = departmentId "
                    +"WHERE seller.id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                Department dep = instantiateDepartment(rs);
                return instantiateSeller(rs, dep);
            }
            return null;

        }catch (SQLException e){
           throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> findAll() {

        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "select seller.*, department.Name as DepName "
                            +"from seller inner join department "
                            +"on seller.DepartmentId = department.Id "
                            +"order  by Name");

            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> mapDep = new HashMap<>();
            while (rs.next()){
                Department dep = mapDep.get(rs.getInt("DepartmentId"));
                if (dep == null){
                    dep = instantiateDepartment(rs);
                    mapDep.put(rs.getInt("DepartmentId"), dep);
                }
                Seller seller = instantiateSeller(rs, dep);
                list.add(seller);
            }
            return list;

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department department) {

        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "select seller.*, department.Name as DepName "
                    +"from seller inner join department "
                    +"on seller.DepartmentId = department.Id "
                    +"where DepartmentId = ? "
                    +"order  by Name");

            st.setInt(1, department.getId());
            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> mapDep = new HashMap<>();
            while (rs.next()){
                Department dep = mapDep.get(rs.getInt("DepartmentId"));
                if (dep == null){
                    dep = instantiateDepartment(rs);
                    mapDep.put(rs.getInt("DepartmentId"), dep);
                }
                Seller seller = instantiateSeller(rs, dep);
                list.add(seller);
            }
            return list;

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> findByDepartment(Integer depId) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "select seller.*, department.Name as DepName "
                            +"from seller inner join department "
                            +"on seller.DepartmentId = department.Id "
                            +"where DepartmentId = ? "
                            +"order  by Name");

            st.setInt(1, depId);
            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Department dep = null;
            while (rs.next()){
                if (dep == null) {
                    dep = instantiateDepartment(rs);
                }
                Seller seller = instantiateSeller(rs, dep);
                list.add(seller);
            }
            return list;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller obj = new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBirthDate(rs.getDate("BirthDate"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setDepartment(dep);
        return obj;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }
}
