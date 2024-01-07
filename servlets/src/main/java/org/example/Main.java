package org.example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.example.DBS.ItemS;
import org.example.DBS.PlayerS;
import org.example.DBS.WeaponS;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8081);
        tomcat.getConnector();

        Context ctx = tomcat.addContext("", null);

        Wrapper weaponServlet = Tomcat.addServlet(ctx, "weapon", new WeaponS());
        weaponServlet.setLoadOnStartup(1);
        weaponServlet.addMapping("/weapon/*");

        Wrapper armorServlet = Tomcat.addServlet(ctx, "itemS", new ItemS());
        armorServlet.setLoadOnStartup(1);
        armorServlet.addMapping("/item/*");

        Wrapper playerServlet = Tomcat.addServlet(ctx, "player", new PlayerS());
        playerServlet.setLoadOnStartup(1);
        playerServlet.addMapping("/player/*");

        tomcat.start();
    }
}