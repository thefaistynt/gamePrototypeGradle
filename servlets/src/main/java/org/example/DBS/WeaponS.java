package org.example.DBS;

import org.example.CRUD;
import org.example.Connect;
import org.example.Item;
import org.example.Weapon;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WeaponS extends HttpServlet {

    private static final Scanner scanner = new Scanner(System.in);

    private static CRUD crud;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter writer = res.getWriter();
        String path = req.getPathInfo();
        String[] pathParts = path.split("/");

        if(!checkPath(path, pathParts, writer)){
            return;
        }

        if(pathParts[1].equals("get")){
            try{
                crud = new CRUD(Connect.get_connection());
                int id = Integer.parseInt(req.getParameter("playerId"));
                Weapon[] weapons = crud.findWeapons(id);
                writer.write("weapon 1 : " + weapons[0]);
                writer.write("weapon 2 : " + weapons[1]);
            } catch (SQLException e){
                e.printStackTrace();
            }
        } else if(pathParts[1].equals("getAll")){
            try{
                crud = new CRUD(Connect.get_connection());
                List<Weapon[]> Weapons = crud.getAllWeapons();
                for (Weapon[] m : Weapons) {
                    writer.write("weapon 1 : " + m[0]);
                    writer.write("weapon 2 : " + m[1]);
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter writer = res.getWriter();
        String path = req.getPathInfo();
        String[] pathParts = path.split("/");

        if(!checkPath(path, pathParts, writer)){
            return;
        }

        if(pathParts[1].equals("add")){
            try{
                crud = new CRUD(Connect.get_connection());
                String name = req.getParameter("name");
                double damage = Double.parseDouble(req.getParameter("damage"));
                Weapon weapon = new Weapon(name, damage);
                crud.addItem(weapon);
                writer.write(weapon.toString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter writer = res.getWriter();
        String path = req.getPathInfo();
        String[] pathParts = path.split("/");

        if(!checkPath(path, pathParts, writer)){
            return;
        }

        if(pathParts[1].equals("update")){
            try{
                crud = new CRUD(Connect.get_connection());
                int id = Integer.parseInt(req.getParameter("id"));
                String name = req.getParameter("name");
                Weapon weapon = Item.weapons[id - Item.items.length];
                crud.updateItem(weapon, name);
                writer.write("Update successful");
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter writer = res.getWriter();
        String path = req.getPathInfo();
        String[] pathParts = path.split("/");

        if(!checkPath(path, pathParts, writer)){
            return;
        }

        if(pathParts[1].equals("delete")){
            try{
                crud = new CRUD(Connect.get_connection());
                int id = Integer.parseInt(req.getParameter("id"));
                System.out.println(id);
                crud.deleteWeapon(id, 0);
                crud.deleteWeapon(id, 1);
                writer.write("Delete successful");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean checkPath(String path, String[] pathParts, PrintWriter writer) {
        if(path == null){
            writer.write("Path not specified");
            return false;
        }

        if(pathParts.length <= 1){
            writer.write("Invalid path");
            return false;
        }

        return true;
    }
}