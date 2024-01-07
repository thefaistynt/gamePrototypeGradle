package org.example.DBS;

import org.example.CRUD;
import org.example.Connect;
import org.example.Item;
import org.example.Player;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ItemS extends HttpServlet {

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
                int id = Integer.parseInt(req.getParameter("itemId"));
                Item item = crud.findItemById(id);
                writer.write(item.toString());
            } catch (SQLException e){
                e.printStackTrace();
            }
        } else if(pathParts[1].equals("getAll")){
            try{
                crud = new CRUD(Connect.get_connection());
                List<Item> items = crud.getAllItems();
                for (Item m : items) {
                    writer.write(m.toString());
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
                Item item = new Item(name);
                crud.addItem(item);
                writer.write(item.toString());
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
                Item item;
                if (id <= 80) {
                    item = Item.items[id];
                } else if (id <= 180) {
                    item = Item.weapons[id - Item.items.length];
                } else {
                    item = Item.backpacks[id - Item.weapons.length - Item.items.length];
                }
                crud.updateItem(item, name);
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
                crud.deleteItem(id);
                writer.write("Delete successful");
            } catch (SQLException e){
                e.printStackTrace();
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
