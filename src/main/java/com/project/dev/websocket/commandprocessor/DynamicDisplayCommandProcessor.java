/*
 * @overview        {DynamicDisplayCommandProcessor}
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
package com.project.dev.websocket.commandprocessor;

import com.project.dev.websocket.model.Station;
import com.project.dev.websocket.model.element.DisplayMessageElement;
import com.project.dev.websocket.peripheric.Peripheric;
import java.util.LinkedList;
import java.util.Queue;
import lombok.NonNull;

/**
 * TODO: Description of {@code DynamicDisplayCommandProcessor}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
public class DynamicDisplayCommandProcessor implements GenericCommandProcessor<Boolean> {

    protected final Queue<String> dynamicDisplayQueue = new LinkedList<>();

    /**
     * TODO: Description of method {@code processCommand}.
     *
     * @param plcCommand
     * @param station
     * @param sender
     * @return
     */
    @Override
    public Boolean processCommand(String plcCommand, @NonNull String sender, @NonNull Station station) {
        String message = null;
        Integer dynamicWeight;
        System.out.println("Validating code (dynamic display): '" + plcCommand + "'");
        switch (plcCommand) {
            case "B40":
                dynamicWeight = 0;
                try {
                    dynamicWeight = (Integer) station.getPeriphericInfo(Station.ID_DYNAMIC_WEIGHT + 1, Peripheric.GET_LAST_WEIGHT);
                } catch (ClassCastException | NullPointerException e) {
                }
                message = (dynamicWeight != null && dynamicWeight != 0) ? "A VIA NACIONAL <-- " + dynamicWeight + " KG" : "A VIA NACIONAL";
                break;
            case "B41":
                dynamicWeight = 0;
                try {
                    dynamicWeight = (Integer) station.getPeriphericInfo(Station.ID_DYNAMIC_WEIGHT + 1, Peripheric.GET_LAST_WEIGHT);
                } catch (ClassCastException | NullPointerException e) {
                }
                message = (dynamicWeight != null && dynamicWeight != 0) ? dynamicWeight + " KG" + " --> A BASCULA ESTATICA" : "A BASCULA ESTATICA";
                break;
            case "411":
            case "511":
                message = "";
                break;

            case "DESENCOLAR":
                message = dynamicDisplayQueue.poll();
                break;
        }

        if (message != null) {
            if (!(plcCommand.equals("B40") || plcCommand.equals("B41"))) {
                station.sendInfoToPeripheric(Station.ID_DYNAMIC_DISPLAY + 1,
                        DisplayMessageElement.builder()
                                .message(message)
                                .sender(sender + ":" + plcCommand)
                                .build());
            } else {
                dynamicDisplayQueue.add(message);
                System.out.println("PROCESSING B40 or B41");
                processCommand("DESENCOLAR", sender, station);
                System.out.println("PROCESSED B40 or B41");
            }
            System.out.println("Found code (dynamic display): '" + plcCommand + "'");
            return true;
        } else {
            System.out.println("Not found code (dynamic display): '" + plcCommand + "'");
            return false;
        }
    }
}
