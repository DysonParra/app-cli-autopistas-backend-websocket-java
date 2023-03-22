/*
 * @fileoverview    {Peripheric}
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
package com.project.dev.websocket.peripheric;

import com.project.dev.websocket.model.Station;
import com.project.dev.websocket.model.element.GenericElement;

/**
 * TODO: Definición de {@code Peripheric}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
public interface Peripheric extends Runnable {

    public static final int RESULT_SEND_NOT_FOUND_PERIPHERIC = -1;
    public static final int RESULT_SEND_INVALID_MESSAGE = 0;
    public static final int RESULT_SEND_SUCCESS_PROCESSED = 1;

    public static final String GET_LAST_WEIGHT = "1";
    public static final String GET_LAST_DISPLAY_MESSAGE = "2";

    public static final String GET_PLC_COMMANDS_QUEUE = "11";
    public static final String GET_PLC_COMMANDS_QUEUE_SIZE = "12";
    public static final String GET_PLC_COMMANDS_QUEUE_FIRST_ELEMENT = "13";
    public static final String GET_PLC_COMMANDS_QUEUE_FIRTS_ELEMENT_AND_POLL = "14";

    public static final String GET_DISPLAY_QUEUE = "21";
    public static final String GET_DISPLAY_QUEUE_SIZE = "22";
    public static final String GET_DISPLAY_QUEUE_FIRST_ELEMENT = "23";
    public static final String GET_DISPLAY_QUEUE_FIRTS_ELEMENT_AND_POLL = "24";
    public static final String GET_DISPLAY_QUEUE_LAST_ELEMENT = "25";
    public static final String GET_DISPLAY_QUEUE_LAST_ELEMENT_AND_POLL = "26";

    public static final String GET_DYNAMIC_PLATES_QUEUE = "31";
    public static final String GET_DYNAMIC_PLATES_QUEUE_SIZE = "32";
    public static final String GET_DYNAMIC_PLATES_QUEUE_FIRST_ELEMENT = "33";
    public static final String GET_DYNAMIC_PLATES_QUEUE_FIRTS_ELEMENT_AND_POLL = "34";

    public static final String GET_STATIC_PLATES_QUEUE = "41";
    public static final String GET_STATIC_PLATES_QUEUE_SIZE = "42";
    public static final String GET_STATIC_PLATES_QUEUE_FIRST_ELEMENT = "43";
    public static final String GET_STATIC_PLATES_QUEUE_FIRTS_ELEMENT_AND_POLL = "44";

    public static final String TYPE_STATIC_DISPLAY = "STATIC_DISPLAY";
    public static final String TYPE_DYNAMIC_DISPLAY = "DYNAMIC_DISPLAY";
    public static final String TYPE_STATIC_WEIGHT = "STATIC_WEIGHT";
    public static final String TYPE_DYNAMIC_WEIGHT = "DYNAMIC_WEIGHT";
    public static final String TYPE_CAMERA = "CAMERA";
    public static final String TYPE_PLC = "PLC";

    /**
     * TODO: Definición de {@code getId}.
     *
     * @return
     */
    public abstract String getId();

    /**
     * TODO: Definición de {@code setId}.
     *
     * @param id
     */
    public abstract void setId(String id);

    /**
     * TODO: Definición de {@code getType}.
     *
     * @return
     */
    public abstract String getType();

    /**
     * TODO: Definición de {@code setType}.
     *
     * @param id
     */
    public abstract void setType(String id);

    /**
     * TODO: Definición de {@code getNumberOfThisType}.
     *
     * @return
     */
    public abstract int getNumberOfThisType();

    /**
     * TODO: Definición de {@code setNumberOfThisType}.
     *
     * @param numberOfThisType
     */
    public abstract void setNumberOfThisType(int numberOfThisType);

    /**
     * TODO: Definición de {@code getStation}.
     *
     * @return
     */
    public abstract Station getStation();

    /**
     * TODO: Definición de {@code setStation}.
     *
     * @param station
     */
    public abstract void setStation(Station station);

    /**
     * TODO: Definición de {@code validateAndClear}.
     *
     */
    public abstract void validateAndClear();

    /**
     * TODO: Definición de {@code getInfo}.
     *
     * @param getType
     * @return
     */
    public abstract Object getInfo(String getType);

    /**
     * TODO: Definición de {@code sendInfo}.
     *
     * @param info
     * @return
     */
    public abstract int sendInfo(GenericElement info);

}
