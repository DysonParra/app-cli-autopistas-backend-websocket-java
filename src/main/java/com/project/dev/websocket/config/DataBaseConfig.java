/*
 * @fileoverview    {DataBaseConfig}
 *
 * @version         2.0
 *
 * @author          Dyson Arley Parra Tilano <dysontilano@gmail.com>
 *
 * @copyright       Dyson Parra
 * @see             github.com/DysonParra
 *
 * History
 * @version 1.0     Implementation done.
 * @version 2.0     Documentation added.
 */
package com.project.dev.websocket.config;

import com.project.dev.api.dto.ConfiguracionDTO;
import com.project.dev.api.servicio.ServicioConfiguracion;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Description of {@code DataBaseConfig}.
 *
 * @author Dyson Parra
 * @since 11
 */
public class DataBaseConfig {

    public static String IP_PLC = "localhost";
    public static String IP_BASCULA_DINAMICA = "192.168.1.8";
    public static String IP_BASCULA_ESTATICA = "192.168.1.7";
    public static String IP_DISPLAY_DINAMICA = "192.168.1.9";
    public static String IP_DISPLAY_ESTATICA = "192.168.1.5";
    public static String IP_CAMARA = "";
    public static String IP_IMPRESORA = "192.168.1.11";

    public static Integer PUERTO_PLC = Integer.parseInt("2002");
    public static Integer PUERTO_BASCULA_DINAMICA = Integer.parseInt("5000");
    public static Integer PUERTO_BASCULA_ESTATICA = Integer.parseInt("2336");
    public static Integer PUERTO_DISPLAY_DINAMICA = Integer.parseInt("2009");
    public static Integer PUERTO_DISPLAY_ESTATICA = Integer.parseInt("2005");
    public static Integer PUERTO_CAMARA = Integer.parseInt("3002");
    public static Integer PUERTO_IMPRESORA = Integer.parseInt("9100");
    public static List<String> PLC_INIT_COMMAND = new ArrayList<>();
    public static Integer SCHEDULED_TIME = 60000;
    public static Integer WAIT_PLC_RESPONSE = 1;
    public static Integer RESEND_TIME = 3000;
    public static Integer RESEND_TRIES = 5;
    public static final Object SYNCHRONIZER = new Object();

    public static void getParamConfigDB(ServicioConfiguracion servicioConfiguracion, String varName) {
        try {
            Field field = DataBaseConfig.class.getField(varName);
            String varType = field.getType().getSimpleName();
            //System.out.println(varType);
            try {
                List<ConfiguracionDTO> foundConfig = servicioConfiguracion.obtenerEntidades(varName);
                //System.out.println(varConfig);
                if (foundConfig == null || foundConfig.isEmpty())
                    throw new Exception("Can't get config in database");

                if (foundConfig.size() == 1)
                    System.out.println("Got config for '" + varName + "' in database (" + foundConfig.get(0) + ")");
                else
                    System.out.println("Got config for '" + varName + "' in database (" + foundConfig + ")");

                try {
                    switch (varType) {
                        case "Integer":
                            field.set(DataBaseConfig.class, Integer.parseInt(foundConfig.get(0).getStrValor()));
                            break;

                        case "String":
                            field.set(DataBaseConfig.class, foundConfig.get(0).getStrValor());
                            break;

                        case "List":
                            List<String> aux = new ArrayList<>();
                            foundConfig.forEach(config -> aux.add(config.getStrValor()));
                            field.set(DataBaseConfig.class, aux);
                            break;

                        default:
                            System.out.println("Type '" + field.getType().getSimpleName() + "' not allowed to get config from BD");
                            break;
                    }
                    System.out.println("Config found in database assigned to: '" + varName + "'");
                } catch (Exception e) {
                    System.out.println("Error assigning config found in database to: '" + varName + "', using actual value (" + field.get(varName) + ")");
                }
            } catch (Exception e) {
                System.out.println("Could not get config for '" + varName + "' in database, using actual value (" + field.get(varName) + ")");
            }

        } catch (Exception e) {
            System.out.println("Error getting field with name " + varName + " in '" + MethodHandles.lookup().lookupClass().getSimpleName() + "'");
        }

    }

    /**
     * TODO: Description of {@code getDataBaseConfig}.
     *
     * @param servicioConfiguracion
     */
    public static void getDataBaseConfig(ServicioConfiguracion servicioConfiguracion) {
        System.out.println("Getting config from database...");
        Field[] fields = DataBaseConfig.class.getFields();
        for (Field field : fields) {
            //System.out.println(field.getName());
            if (!field.getName().equals("SYNCHRONIZER"))
                getParamConfigDB(servicioConfiguracion, field.getName());
        }
        System.out.println("Got config from database.");
    }

    /**
     * TODO: Description of {@code updateInitList}.
     *
     * @param servicioConfiguracion
     */
    public static void updateInitList(ServicioConfiguracion servicioConfiguracion) {
        System.out.println("Updating init list from database...");
        Field[] fields = DataBaseConfig.class.getFields();
        for (Field field : fields) {
            String varType = field.getType().getSimpleName();
            if (varType.equals("List"))
                getParamConfigDB(servicioConfiguracion, field.getName());
        }
        System.out.println("Updated init list from database.");
    }

}
