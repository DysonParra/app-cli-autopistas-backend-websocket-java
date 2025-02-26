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
 * @version 1.0     Implementation done.
 * @version 2.0     Documentation added.
 */
package com.project.dev.websocket.peripheric;

import com.project.dev.websocket.model.Station;
import com.project.dev.websocket.model.element.GenericElement;

/**
 * TODO: Description of {@code Peripheric}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
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
     * TODO: Description of method {@code getId}.
     *
     * @return
     */
    public abstract String getId();

    /**
     * TODO: Description of method {@code setId}.
     *
     * @param id
     */
    public abstract void setId(String id);

    /**
     * TODO: Description of method {@code getType}.
     *
     * @return
     */
    public abstract String getType();

    /**
     * TODO: Description of method {@code setType}.
     *
     * @param id
     */
    public abstract void setType(String id);

    /**
     * TODO: Description of method {@code getNumberOfThisType}.
     *
     * @return
     */
    public abstract int getNumberOfThisType();

    /**
     * TODO: Description of method {@code setNumberOfThisType}.
     *
     * @param numberOfThisType
     */
    public abstract void setNumberOfThisType(int numberOfThisType);

    /**
     * TODO: Description of method {@code getStation}.
     *
     * @return
     */
    public abstract Station getStation();

    /**
     * TODO: Description of method {@code setStation}.
     *
     * @param station
     */
    public abstract void setStation(Station station);

    /**
     * TODO: Description of method {@code validateAndClear}.
     *
     */
    public abstract void validateAndClear();

    /**
     * TODO: Description of method {@code getInfo}.
     *
     * @param getType
     * @return
     */
    public abstract Object getInfo(String getType);

    /**
     * TODO: Description of method {@code sendInfo}.
     *
     * @param info
     * @return
     */
    public abstract int sendInfo(GenericElement info);

}
