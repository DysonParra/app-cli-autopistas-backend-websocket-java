/*
 * @fileoverview    {PlcCommandProcessor}
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.NonNull;
import lombok.ToString;

/**
 * TODO: Description of {@code PlcCommandProcessor}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
@ToString(exclude = {"commandsDescription", "commandsDescriptionMap"})
public class PlcCommandProcessor implements GenericCommandProcessor<Boolean> {

    private final GenericCommandProcessor<Boolean> semaphoreCommandProcessor = new SemaphoreCommandProcessor();
    private final List<GenericCommandProcessor<Boolean>> commandsProcessors = new ArrayList<>();

    private final String[][][] commandsDescription = {
        {{"010"}, {"Estado Maquina", "Sin Estado"}},
        {{"011"}, {"Estado Maquina", "Solo Estatica"}},
        {{"012"}, {"Estado Maquina", "Solo Dinamica"}},
        {{"013"}, {"Estado Maquina", "Ambas"}},
        {{"014"}, {"Estado Maquina", "Manual"}},
        {{"015"}, {"Estado Maquina", "Inicializar"}},
        {{"160"}, {"TL1  (Talanquera 1 Desvio Dinamica)", "No acción"}},
        {{"161"}, {"TL1  (Talanquera 1 Desvio Dinamica)", "Bajando"}},
        {{"162"}, {"TL1  (Talanquera 1 Desvio Dinamica)", "Subiendo"}},
        {{"310"}, {"SF1 (Semaforo Rojo,Verde Entrada Dinamica)", "Nada"}},
        {{"311"}, {"SF1 (Semaforo Rojo,Verde Entrada Dinamica)", "Rojo"}},
        {{"312"}, {"SF1 (Semaforo Rojo,Verde Entrada Dinamica)", "Verde"}},
        {{"410"}, {"SF2 (Semaforo Cruce Flecha Dinamina a Nacional)", "Nada"}},
        {{"411"}, {"SF2 (Semaforo Cruce Flecha Dinamina a Nacional)", "X"}},
        {{"412"}, {"SF2 (Semaforo Cruce Flecha Dinamina a Nacional)", "Flecha"}},
        {{"510"}, {"SF3 (Semaforo Cruce Flecha Dinamina a Estatica)", "Nada"}},
        {{"511"}, {"SF3 (Semaforo Cruce Flecha Dinamina a Estatica)", "X"}},
        {{"512"}, {"SF3 (Semaforo Cruce Flecha Dinamina a Estatica)", "Flecha"}},
        {{"710"}, {"TL2  (Talanquera 2 Salida Estatica)", "No acción"}},
        {{"711"}, {"TL2  (Talanquera 2 Salida Estatica)", "Bajando"}},
        {{"712"}, {"TL2  (Talanquera 2 Salida Estatica)", "Subiendo"}},
        {{"810"}, {"SF4 (Semaforo Cruce Flecha Estatica a Nacional)", "Nada"}},
        {{"811"}, {"SF4 (Semaforo Cruce Flecha Estatica a Nacional)", "X"}},
        {{"812"}, {"SF4 (Semaforo Cruce Flecha Estatica a Nacional)", "Flecha"}},
        {{"910"}, {"SF5 (Semaforo Cruce Flecha Estatica a Patio)", "Nada"}},
        {{"911"}, {"SF5 (Semaforo Cruce Flecha Estatica a Patio)", "X"}},
        {{"912"}, {"SF5 (Semaforo Cruce Flecha Estatica a Patio)", "Flecha"}},
        {{"A44"}, {"Envio orden 1", "Iniciar Función 1"}},
        {{"B44"}, {"Envio orden 2", "Iniciar Función 2"}},
        {{"C44"}, {"Envio orden 3", "Iniciar Función 3"}},
        {{"D44"}, {"Envio orden 4", "Iniciar Función 4"}},
        {{"E44"}, {"Envio orden 5", "Iniciar Función 5"}},
        {{"F44"}, {"Envio orden 6", "Iniciar Función 6"}},
        {{"G44"}, {"Envio orden 7", "Iniciar Función 7"}},
        {{"H44"}, {"Envio orden 8", "Iniciar Función 8"}},
        {{"I44"}, {"Envio orden 9", "Iniciar Función 9"}},
        {{"J44"}, {"Envio orden 10", "Iniciar Función 10"}}
    };

    private final Map<String, String[]> commandsDescriptionMap
            = Stream.of(commandsDescription).collect(
                    Collectors.toMap(data -> data[0][0], data -> data[1]));

    /**
     * TODO: Description of method {@code PlcCommandProcessor}.
     *
     */
    public PlcCommandProcessor() {
        commandsProcessors.add(new DynamicDisplayCommandProcessor());
        commandsProcessors.add(new StaticDisplayCommandProcessor());
        commandsProcessors.add(new CustomCommadProcessor());
    }

    /**
     * TODO: Description of method {@code printCommandDescription}.
     *
     * @param plcCommand
     */
    public void printCommandDescription(String plcCommand) {
        String description[] = commandsDescriptionMap.get(plcCommand);
        if (description != null)
            System.out.println("PLC command: '" + plcCommand + "' (" + description[0] + ")");
        else if (plcCommand.matches(".?99"))
            System.out.println("PLC command: '" + plcCommand + "' (Respuesta)");
        else
            System.out.println("PLC command: '" + plcCommand + "' (Sin descripcion)");
    }

    /**
     * TODO: Description of method {@code printAllCommandsDescription}.
     *
     */
    public void printAllCommandsDescription() {
        commandsDescriptionMap.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + ":" + Arrays.toString(entry.getValue()));
        });
    }

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
        boolean result;
        printCommandDescription(plcCommand);
        result = semaphoreCommandProcessor.processCommand(plcCommand, sender, station);
        for (GenericCommandProcessor<Boolean> commandProcessor : commandsProcessors)
            if (commandProcessor.processCommand(plcCommand, sender, station)) {
                result = true;
                break;
            }
        return result;
    }

}
