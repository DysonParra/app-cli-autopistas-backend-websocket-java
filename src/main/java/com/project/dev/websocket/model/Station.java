/*
 * @fileoverview    {Station} se encarga de realizar tareas específicas.
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
package com.project.dev.websocket.model;

import com.project.dev.backend.servicio.ServicioCategoria;
import com.project.dev.backend.servicio.ServicioConfiguracion;
import com.project.dev.backend.servicio.ServicioTransitoDinamica;
import com.project.dev.backend.servicio.ServicioVehiculo;
import com.project.dev.backend.servicio.ServicioVehiculoSobrepeso;
import com.project.dev.websocket.model.element.GenericElement;
import com.project.dev.websocket.model.front.FrontMessage;
import com.project.dev.websocket.peripheric.Peripheric;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * TODO: Definición de {@code Station}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Station {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static final int STATION_STATE_NOT_EMPTY = 0;
    public static final int STATION_STATE_EMPTY = 1;
    public static final int STATION_STATE_EMPTY_AGAIN = 2;

    public static final String ID_UNDEFINED_TYPE = "OTHER_";
    public static final String ID_STATIC_DISPLAY = "STATIC_DISPLAY_";
    public static final String ID_DYNAMIC_DISPLAY = "DYNAMIC_DISPLAY_";
    public static final String ID_STATIC_WEIGHT = "STATIC_WEIGHT_";
    public static final String ID_DYNAMIC_WEIGHT = "DYNAMIC_WEIGHT_";
    public static final String ID_CAMERA = "CAMERA_";
    public static final String ID_PLC = "PLC_";

    private final List<String> idsUndefinedType = new ArrayList<>();
    private final List<String> idsStaticDisplay = new ArrayList<>();
    private final List<String> idsDynamicDisplay = new ArrayList<>();
    private final List<String> idsStaticWeight = new ArrayList<>();
    private final List<String> idsDynamicWeight = new ArrayList<>();
    private final List<String> idsCamera = new ArrayList<>();
    private final List<String> idsPlc = new ArrayList<>();

    private SimpMessagingTemplate template;
    @Getter(AccessLevel.NONE)
    private final AtomicInteger stationState = new AtomicInteger();
    @Getter(AccessLevel.NONE)
    private final AtomicLong lastCommandQuantity = new AtomicLong();
    @Getter(AccessLevel.NONE)
    private final Map<String, Peripheric> peripherics = new LinkedHashMap<>();

    private ServicioConfiguracion servicioConfiguracion;
    private ServicioCategoria servicioCategoria;
    private ServicioTransitoDinamica servicioTransitoDinamica;
    private ServicioVehiculoSobrepeso servicioVehiculoSobrepeso;
    private ServicioVehiculo servicioVehiculo;

    /**
     * TODO: Definición de {@code getLastCommandQuantity}.
     *
     * @return
     */
    public long getLastCommandQuantity() {
        return lastCommandQuantity.get();
    }

    /**
     * TODO: Definición de {@code setLastCommandQuantity}.
     *
     * @param newCommandQuantity
     */
    public void setLastCommandQuantity(long newCommandQuantity) {
        long oldCommandQuantity = this.lastCommandQuantity.getAndSet(newCommandQuantity);
        System.out.println("Old command quantity: " + oldCommandQuantity);
        System.out.println("New command quantity: " + newCommandQuantity);
        if (oldCommandQuantity != newCommandQuantity)
            this.stationState.set(STATION_STATE_NOT_EMPTY);
        else {
            if (oldCommandQuantity != 0) {
                this.stationState.set(STATION_STATE_EMPTY);
                lastCommandQuantity.set(0);
            } else
                this.stationState.set(STATION_STATE_EMPTY_AGAIN);
        }
    }

    /**
     * TODO: Definición de {@code getStationState}.
     *
     * @return
     */
    public int getStationState() {
        return stationState.get();
    }

    /**
     * TODO: Definición de {@code updateStationState}.
     *
     */
    public void updateStationState() {
        System.out.println("\nStation: updating state (" + DATE_FORMAT.format(new Date()) + ")");
        try {
            setLastCommandQuantity((long) getPeriphericInfo(ID_PLC + 1, Peripheric.GET_PLC_COMMANDS_QUEUE_SIZE));
        } catch (ClassCastException | NullPointerException e) {
            setLastCommandQuantity(0);
        }
        System.out.println("Station: state updated.");
    }

    /**
     * TODO: Definición de {@code addPeripheric}.
     *
     * @param peripheric
     */
    public void addPeripheric(@NonNull Peripheric peripheric) {
        peripheric.setStation(this);
        switch (peripheric.getType()) {
            case Peripheric.TYPE_STATIC_DISPLAY:
                idsStaticDisplay.add(ID_STATIC_DISPLAY + (idsStaticDisplay.size() + 1));
                peripheric.setId(ID_STATIC_DISPLAY + idsStaticDisplay.size());
                peripheric.setNumberOfThisType(idsStaticDisplay.size());
                break;
            case Peripheric.TYPE_DYNAMIC_DISPLAY:
                idsDynamicDisplay.add(ID_DYNAMIC_DISPLAY + (idsDynamicDisplay.size() + 1));
                peripheric.setId(ID_DYNAMIC_DISPLAY + idsDynamicDisplay.size());
                peripheric.setNumberOfThisType(idsDynamicDisplay.size());
                break;
            case Peripheric.TYPE_STATIC_WEIGHT:
                idsStaticWeight.add(ID_STATIC_WEIGHT + (idsStaticWeight.size() + 1));
                peripheric.setId(ID_STATIC_WEIGHT + idsStaticWeight.size());
                peripheric.setNumberOfThisType(idsStaticWeight.size());
                break;
            case Peripheric.TYPE_DYNAMIC_WEIGHT:
                idsDynamicWeight.add(ID_DYNAMIC_WEIGHT + (idsDynamicWeight.size() + 1));
                peripheric.setId(ID_DYNAMIC_WEIGHT + idsDynamicWeight.size());
                peripheric.setNumberOfThisType(idsDynamicWeight.size());
                break;
            case Peripheric.TYPE_CAMERA:
                idsCamera.add(ID_CAMERA + (idsCamera.size() + 1));
                peripheric.setId(ID_CAMERA + idsCamera.size());
                peripheric.setNumberOfThisType(idsCamera.size());
                break;
            case Peripheric.TYPE_PLC:
                idsPlc.add(ID_PLC + (idsPlc.size() + 1));
                peripheric.setId(ID_PLC + idsPlc.size());
                peripheric.setNumberOfThisType(idsPlc.size());
                break;
            default:
                idsUndefinedType.add(ID_UNDEFINED_TYPE + (idsUndefinedType.size() + 1));
                peripheric.setId(ID_UNDEFINED_TYPE + idsUndefinedType.size());
                peripheric.setNumberOfThisType(idsUndefinedType.size());
                break;
        }
        peripherics.put(peripheric.getId(), peripheric);
    }

    /**
     * TODO: Definición de {@code getPeripheric}.
     *
     * @param id
     * @return
     */
    private Peripheric getPeripheric(@NonNull String id) {
        Peripheric peripheric = peripherics.get(id);
        if (peripheric != null)
            System.out.println("Station: peripheric '" + id + "' found.");
        else
            System.out.println("Station: peripheric '" + id + "' not found.");
        return peripheric;
    }

    /**
     * TODO: Definición de {@code getPeriphericInfo}.
     *
     * @param id
     * @param getType
     * @return
     */
    public Object getPeriphericInfo(@NonNull String id, @NonNull String getType) throws ClassCastException, NullPointerException {
        System.out.println("Station: getting info from '" + id + "'");
        Peripheric peripheric = getPeripheric(id);
        if (peripheric != null) {
            Object result = peripheric.getInfo(getType);
            if (result == null)
                System.out.println("Station: peripheric '" + id + "'" + " return " + "\"" + result + "\"");
            else
                System.out.println("Station: peripheric '" + id + "'" + " return " + result);
            return result;
        } else
            return null;
    }

    /**
     * TODO: Definición de {@code sendInfoToPeripheric}.
     *
     * @param id
     * @param info
     * @return
     */
    public int sendInfoToPeripheric(@NonNull String id, @NonNull GenericElement info) {
        System.out.println("Station: sending \"" + info + "\" to '" + id + "'");
        Peripheric peripheric = getPeripheric(id);
        if (peripheric == null)
            return Peripheric.RESULT_SEND_NOT_FOUND_PERIPHERIC;
        else {
            int result = peripheric.sendInfo(info);
            if (result == Peripheric.RESULT_SEND_SUCCESS_PROCESSED)
                System.out.println("Station: peripheric '" + id + "'" + " succes processed " + "\"" + info + "\"");
            else
                System.out.println("Station: peripheric '" + id + "'" + " could not be processed " + "\"" + info + "\"");
            return result;
        }

    }

    /**
     * TODO: Definición de {@code startPeripherics}.
     *
     */
    public void startPeripherics() {
        peripherics.forEach((k, v) -> {
            new Thread(v).start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        });
    }

    /**
     * TODO: Definición de {@code validateAndClearPeripherics}.
     *
     */
    public void validateAndClearPeripherics() {
        System.out.println("Station: validating and clear...");
        peripherics.forEach((k, v) -> v.validateAndClear());
        System.out.println("Station: validated and cleared.");
    }

    /**
     * TODO: Definición de {@code sendMessageToFront}.
     *
     * @param message
     */
    public void sendMessageToFront(FrontMessage message) {
        if (template != null) {
            System.out.println("Station: sending \"" + message + "\" to 'TEMPLATE_FRONTEND'");
            template.convertAndSend("/message", message);
        } else
            System.out.println("Cannot send \"" + message + "\", template is null");
    }

    /**
     * TODO: Definición de {@code sendMessageToFront}.
     *
     * @param message
     */
    public void sendMessageToFront(String message) {
        if (template != null) {
            System.out.println("Station: sending \"" + message + "\" to 'TEMPLATE_FRONTEND'");
            template.convertAndSend("/message", message);
        } else
            System.out.println("Cannot send \"" + message + "\", template is null");
    }
}
