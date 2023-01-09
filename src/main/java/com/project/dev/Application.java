/*
 * @fileoverview    {Application} se encarga de realizar tareas específicas.
 *
 * @version         2.0
 *
 * @author          Dyson Arley Parra Tilano <dysontilano@gmail.com>
 *
 * @copyright       Dyson Parra
 * @see             github.com/DysonParra
 *
 * History
 * @version 1.0     Implementación realizada.
 * @version 2.0     Documentación agregada.
 */
package com.project.dev;

import com.project.dev.backend.servicio.ServicioCategoria;
import com.project.dev.backend.servicio.ServicioConfiguracion;
import com.project.dev.backend.servicio.ServicioTransitoDinamica;
import com.project.dev.backend.servicio.ServicioVehiculo;
import com.project.dev.backend.servicio.ServicioVehiculoSobrepeso;
import com.project.dev.websocket.config.DataBaseConfig;
import com.project.dev.websocket.controller.WebSocketController;
import com.project.dev.websocket.model.Station;
import com.project.dev.websocket.peripheric.Peripheric;
import com.project.dev.websocket.peripheric.serial.StaticWeightSerialClient;
import com.project.dev.websocket.peripheric.tcp.client.DisplayClient;
import com.project.dev.websocket.peripheric.tcp.client.DynamicWeightClient;
import com.project.dev.websocket.peripheric.tcp.client.StaticWeightClient;
import com.project.dev.websocket.peripheric.tcp.server.CameraServer;
import com.project.dev.websocket.peripheric.tcp.server.PlcServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import static com.project.dev.websocket.config.DataBaseConfig.*;

/**
 * TODO: Definición de {@code Application}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
@SpringBootApplication
@EnableScheduling
public class Application implements CommandLineRunner {

    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private ServicioConfiguracion servicioConfiguracion;
    @Autowired
    private ServicioCategoria servicioCategoria;
    @Autowired
    private ServicioTransitoDinamica servicioTransitoDinamica;
    @Autowired
    private ServicioVehiculoSobrepeso servicioVehiculoSobrepeso;
    @Autowired
    private ServicioVehiculo servicioVehiculo;

    private Station station;

    /**
     * TODO: Definición de {@code startExecution}.
     *
     */
    public void startExecution() {

        DataBaseConfig.getDataBaseConfig(servicioConfiguracion);

        Peripheric staticDisplayClient;
        Peripheric dynamicDisplayClient;
        Peripheric staticWeightClient;
        Peripheric dynamicWeightClient;
        Peripheric cameraServer;
        Peripheric plcServer;
        Peripheric staticWeightSerial;

        staticDisplayClient = new DisplayClient(IP_DISPLAY_ESTATICA, PUERTO_DISPLAY_ESTATICA, false);
        dynamicDisplayClient = new DisplayClient(IP_DISPLAY_DINAMICA, PUERTO_DISPLAY_DINAMICA, true);
        staticWeightClient = new StaticWeightClient(IP_BASCULA_ESTATICA, PUERTO_BASCULA_ESTATICA);
        dynamicWeightClient = new DynamicWeightClient(IP_BASCULA_DINAMICA, PUERTO_BASCULA_DINAMICA);
        cameraServer = new CameraServer(PUERTO_CAMARA);
        plcServer = new PlcServer(PUERTO_PLC);
        staticWeightSerial = new StaticWeightSerialClient("COM3", 9600, 8, 1, 0);

        station = Station.builder()
                .template(template)
                .servicioCategoria(servicioCategoria)
                .servicioConfiguracion(servicioConfiguracion)
                .servicioTransitoDinamica(servicioTransitoDinamica)
                .servicioVehiculo(servicioVehiculo)
                .servicioVehiculoSobrepeso(servicioVehiculoSobrepeso)
                .build();
        WebSocketController.setStation(station);

        // Code for Tcp peripherics
        station.addPeripheric(staticDisplayClient);
        station.addPeripheric(dynamicDisplayClient);
        station.addPeripheric(staticWeightClient);
        station.addPeripheric(dynamicWeightClient);
        station.addPeripheric(cameraServer);
        station.addPeripheric(plcServer);
        // Code for Static weight serial.
        //station.addPeripheric(staticWeightSerial);
        station.startPeripherics();

        while (true) {
            try {
                System.out.println("Station state will updated in: " + DataBaseConfig.SCHEDULED_TIME + " ms");
                synchronized (DataBaseConfig.SYNCHRONIZER) {
                    DataBaseConfig.SYNCHRONIZER.wait(DataBaseConfig.SCHEDULED_TIME);
                }
                validateAndUpdateStationState();
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * Entrada principal del sistema.
     *
     * @param args argumentos de la linea de comandos.
     */
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
        builder.headless(false).run(args);
    }

    /**
     * TODO: Definición de {@code run}.
     *
     * @param args
     * @throws java.lang.Exception
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Execution started");
        this.startExecution();
    }

    /**
     * TODO: Definición de {@code validateAndUpdateStationState}.
     *
     */
    public void validateAndUpdateStationState() {
        station.updateStationState();
        switch (station.getStationState()) {
            case Station.STATION_STATE_NOT_EMPTY:
                System.out.println("The station is not empty.");
                break;
            case Station.STATION_STATE_EMPTY_AGAIN:
                System.out.println("The station is empty Again.");
                station.validateAndClearPeripherics();
                break;
            case Station.STATION_STATE_EMPTY:
                System.out.println("The station is empty.");
                station.validateAndClearPeripherics();
                break;
        }
    }

}
