/*
 * @fileoverview    {DynamicWeightClient}
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
package com.project.dev.websocket.peripheric.tcp.client;

import com.project.dev.api.dto.CategoriaDTO;
import com.project.dev.api.dto.TransitoDinamicaDTO;
import com.project.dev.api.dto.VehiculoDTO;
import com.project.dev.api.dto.VehiculoSobrepesoDTO;
import com.project.dev.dummy.websocket.tcp.generic.client.ClientMessageListener;
import com.project.dev.dummy.websocket.tcp.generic.client.GenericClient;
import com.project.dev.websocket.model.Station;
import com.project.dev.websocket.model.element.CameraPlateElement;
import com.project.dev.websocket.model.element.GenericElement;
import com.project.dev.websocket.model.element.InternCommandElement;
import com.project.dev.websocket.model.element.PlcCommandElement;
import com.project.dev.websocket.model.front.CameraMessage;
import com.project.dev.websocket.model.front.DynamicWeightMessage;
import com.project.dev.websocket.peripheric.PeriphericTcpClient;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Queue;
import lombok.Data;

/**
 * TODO: Description of {@code DynamicWeightClient}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
//@AllArgsConstructor
//@Builder
@Data
//@NoArgsConstructor
public class DynamicWeightClient extends PeriphericTcpClient {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    private static final SimpleDateFormat DATE_FORMAT_DB = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final ArrayList<String> weightInfo = new ArrayList<>();

    protected int lastTotalWeight = 0;

    /**
     * TODO: Description of {@code DynamicWeightClient}.
     *
     * @param serverIpAddress
     * @param serverPort
     */
    public DynamicWeightClient(String serverIpAddress, int serverPort) {
        super(serverIpAddress, serverPort, 2000, -1, true);
        super.setType(TYPE_DYNAMIC_WEIGHT);
        super.setName("Dynamic weight");

        super.setOnMessageListener(new ClientMessageListener() {
            @Override
            public void onMessage(GenericClient client, Socket server, String sentMessage) {
            }

            @Override
            public void onResponse(GenericClient client, Socket server, String response) {
                if (response != null)
                    System.out.println("<" + response + ">");
                if (response == null || response.isEmpty()) {
                    if (!weightInfo.isEmpty()) {
                        System.out.println("Dynamic weight info found in " + "'" + name + "'");
                        DynamicWeightMessage dynamicWeightMessage = getDynamicWeightAsMessage(weightInfo);

                        if (dynamicWeightMessage != null) {
                            try {
                                lastTotalWeight = Integer.parseInt(dynamicWeightMessage.getTotal());
                            } catch (NumberFormatException e) {
                                lastTotalWeight = 0;
                            }
                            System.out.println(dynamicWeightMessage);
                            station.sendMessageToFront(dynamicWeightMessage);
                            validateOverweightAndInsert(dynamicWeightMessage);
                        }
                        weightInfo.clear();
                    }
                } else
                    weightInfo.add(response);
            }
        });
    }

    /**
     * TODO: Description of {@code getDynamicWeightAsMessage}.
     *
     * @param weightInfo
     * @return
     */
    private DynamicWeightMessage getDynamicWeightAsMessage(ArrayList<String> weightInfo) {
        /*
         * -
         * System.out.println("Getting dynamic weight message..."); for (String aux: weightInfo)
         * System.out.println("<" + aux + ">"); System.out.println("");
         */

        String recoveryDate = null;
        ArrayList<String> axles = new ArrayList<>();
        String total = null;
        String nroClass = null;
        String numAxles = null;
        String speed = null;

        String[] lineSplits = {};
        String lineAux = "";
        Date recoveryDateDt = null;

        try {
            for (String line : weightInfo) {
                lineAux = line.substring(0, 4);

                //System.out.println(Arrays.toString(lineSplits));
                switch (lineAux) {

                    case "Tota":
                        //System.out.println("Total got.");
                        total = line.split(":")[1].replaceAll(" ", "");
                        //System.out.println("'" + total + "'");
                        break;

                    case "Axle":
                        //System.out.println("Axle got.");
                        lineAux = line.split(":")[1].replaceAll(" ", "");
                        //System.out.println("'" + lineAux + "'");
                        axles.add(lineAux);
                        break;

                    case "Clas":
                        //System.out.println("Class got.");
                        lineAux = line.replaceAll("Class:", "").replaceAll("Num Axles", "").replaceAll("Speed", "").replaceAll("(\\s+)*", "");
                        lineSplits = lineAux.split(":");
                        //System.out.println("'" + lineAux + "'");
                        //System.out.println(Arrays.toString(lineSplits));
                        nroClass = lineSplits[0];
                        numAxles = lineSplits[1];
                        speed = lineSplits[2].replaceAll(",", ".");
                        break;
                    default:
                        //System.out.println("Date got.");
                        //System.out.println("'" + lineAux + "'");
                        try {
                        recoveryDateDt = DATE_FORMAT.parse(line);
                        recoveryDate = DATE_FORMAT_DB.format(recoveryDateDt);
                    } catch (Exception e) {
                        recoveryDateDt = new Date();
                        recoveryDate = DATE_FORMAT_DB.format(recoveryDateDt);
                    }
                    break;
                }
            }
            return DynamicWeightMessage.builder()
                    .recoveryDate(recoveryDate)
                    .axles(axles.toArray(new String[]{}))
                    .total(total)
                    .nroClass(nroClass)
                    .numAxles(numAxles)
                    .speed(speed)
                    .build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return null;
    }

    /**
     * TODO: Description of {@code validateOverweightAndInsert}.
     *
     * @param dynamicWeightMessage
     */
    private void validateOverweightAndInsert(DynamicWeightMessage dynamicWeightMessage) {
        TransitoDinamicaDTO transitoDinamicaDTO = new TransitoDinamicaDTO();
        String vehicleState = "overweight";
        CameraPlateElement plate;
        String strPlaca = null;
        String imgPlaca = null;
        try {
            Queue<CameraPlateElement> dynamicPlates = (Queue<CameraPlateElement>) station.getPeriphericInfo(Station.ID_CAMERA + 1, GET_DYNAMIC_PLATES_QUEUE);
            plate = dynamicPlates.poll();
            try {
                strPlaca = plate.getTxtPlate();
                imgPlaca = plate.getImgPlate();
            } catch (Exception e) {
            }
            System.out.println("Plate is: '" + strPlaca + "'");
            System.out.println("Dynamic plates in queue (" + dynamicPlates.size() + "): [");
            dynamicPlates.forEach((e) -> System.out.println("  " + e));
            System.out.println("]");
            if (strPlaca == null) {
                System.out.println("The got plate is null.");
                station.sendMessageToFront(CameraMessage.builder()
                        .idCamara("dinamica")
                        .frame("")
                        .plate("")
                        .build());
            }
        } catch (ClassCastException | NullPointerException e) {
        }

        try {
            transitoDinamicaDTO.setIntIdDinamica(0l);
            transitoDinamicaDTO.setStrPlacaVehiculo(strPlaca);
            transitoDinamicaDTO.setStrBase64Placa(imgPlaca);
            transitoDinamicaDTO.setDtFechaHoraTransito(DATE_FORMAT_DB.parse(dynamicWeightMessage.getRecoveryDate()));
            transitoDinamicaDTO.setIntPesoGeneral(Integer.parseInt(dynamicWeightMessage.getTotal()));
            transitoDinamicaDTO.setStrPesoEjes(Arrays.toString(dynamicWeightMessage.getAxles()));
            transitoDinamicaDTO.setFltVelocidad(Float.parseFloat(dynamicWeightMessage.getSpeed()));
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }

        VehiculoDTO vehiculo = null;
        String nroClass = null;
        try {
            vehiculo = station.getServicioVehiculo().buscarEntidad(strPlaca);
            transitoDinamicaDTO.setIntIdCategoria(vehiculo.getIntIdCategoria().intValue());
        } catch (Exception e) {
            //e.printStackTrace(System.out);
        }

        if (vehiculo != null) {
            if (vehiculo.getIntIdCategoria() != null) {
                nroClass = String.valueOf(vehiculo.getIntIdCategoria());
                System.out.println("Vehicle found in database");
            } else {
                nroClass = dynamicWeightMessage.getNroClass();
                System.out.println("Vehicle found in database, but category not specified");
            }
        } else {
            nroClass = dynamicWeightMessage.getNroClass();
            System.out.println("Vehicle not found in database");
        }

        CategoriaDTO categoria = null;
        int tolerancia = 0;
        try {
            categoria = station.getServicioCategoria().buscarEntidad(nroClass);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        if (categoria != null)
            transitoDinamicaDTO.setIntIdCategoria(categoria.getIntIdCategoria().intValue());

        if (nroClass.equals("2") && vehiculo == null) {
            System.out.println("Vehicle is Class C2 (" + dynamicWeightMessage.getTotal() + " KG)...");
            vehicleState = "C2";
        } else if (nroClass.equals("15") && vehiculo == null)
            System.out.println("Vehicle Class unidentified...");
        else if (dynamicWeightMessage.getAxles().length != Integer.parseInt(dynamicWeightMessage.getNumAxles()) && vehiculo == null)
            System.out.println("Num Axles are different to found Axles.");
        else {
            System.out.println("Validating Class (" + nroClass + ")...");
            try {
                if (categoria != null) {
                    if (categoria.getIntTolerancia() != null)
                        tolerancia = categoria.getIntTolerancia();
                    if (transitoDinamicaDTO.getIntPesoGeneral() == null || categoria.getIntPesoMaximo() == null) {
                        System.out.println("Null values identified:");
                        System.out.println("PesoGeneral:" + transitoDinamicaDTO.getIntPesoGeneral());
                        System.out.println("PesoMaximo: " + categoria.getIntPesoMaximo());
                        System.out.println("Vehicle marked as overweight.");
                    } else if (transitoDinamicaDTO.getIntPesoGeneral() > categoria.getIntPesoMaximo() + tolerancia)
                        System.out.println("Vehicle is overweight (" + transitoDinamicaDTO.getIntPesoGeneral()
                                + " > " + (categoria.getIntPesoMaximo() + tolerancia) + ")");
                    else {
                        System.out.println("Vehicle is not overweight (" + transitoDinamicaDTO.getIntPesoGeneral()
                                + " < " + (categoria.getIntPesoMaximo() + tolerancia) + ")");
                        vehicleState = "not overWeight";
                    }
                } else
                    System.out.println("Vehicle Class unidentified...");

            } catch (Exception ex) {
                if (vehiculo != null)
                    System.out.println("Vehicle in database has not category specified");
            }
        }

        transitoDinamicaDTO.setBitBorrado(vehicleState.equals("not overWeight"));
        try {
            System.out.println("Saving in TransitoDinamica: " + transitoDinamicaDTO);
            transitoDinamicaDTO = station.getServicioTransitoDinamica().guardarActualizar(transitoDinamicaDTO);
            System.out.println("Saved in TransitoDinamica:  " + transitoDinamicaDTO);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        switch (vehicleState) {
            case "C2":
            case "overweight":
                System.out.println("Sending signal B41 (Go to static) to PLC...");
                VehiculoSobrepesoDTO vehiculoSobrepesoDTO = new VehiculoSobrepesoDTO();
                if (categoria != null) {
                    vehiculoSobrepesoDTO.setIntPesoMaximo(categoria.getIntPesoMaximo() + tolerancia);
                    if (transitoDinamicaDTO.getIntPesoGeneral() != null && categoria.getIntPesoMaximo() != null)
                        vehiculoSobrepesoDTO.setIntDiferenciaPeso(transitoDinamicaDTO.getIntPesoGeneral() - categoria.getIntPesoMaximo());
                }

                if (categoria == null || categoria.getIntPesoMaximo() == null)
                    vehiculoSobrepesoDTO.setIntDiferenciaPeso(transitoDinamicaDTO.getIntPesoGeneral());
                vehiculoSobrepesoDTO.setIntIdRepeso(0l);
                vehiculoSobrepesoDTO.setIntIdDinamica(transitoDinamicaDTO.getIntIdDinamica());
                vehiculoSobrepesoDTO.setStrPlacaVehiculo(strPlaca);
                vehiculoSobrepesoDTO.setBitBorrado(false);

                try {
                    System.out.println("Saving in VehiculoSobrepeso: " + vehiculoSobrepesoDTO);
                    vehiculoSobrepesoDTO = station.getServicioVehiculoSobrepeso().guardarActualizar(vehiculoSobrepesoDTO);
                    System.out.println("Saved in VehiculoSobrepeso:  " + vehiculoSobrepesoDTO);
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                }

                station.sendInfoToPeripheric(Station.ID_PLC + 1,
                        PlcCommandElement.builder()
                                .command("B41")
                                .sender("DynamicWeight")
                                .build());
                break;

            case "not overWeight":
                System.out.println("Sending signal B40 (Go to nacional) to PLC...");
                station.sendInfoToPeripheric(Station.ID_PLC + 1,
                        PlcCommandElement.builder()
                                .command("B40")
                                .sender("DynamicWeight")
                                .build());
                break;
        }
    }

    /**
     * TODO: Description of {@code validateAndClear}.
     *
     */
    @Override
    public void validateAndClear() {

    }

    /**
     * TODO: Description of {@code getInfo}.
     *
     * @param type
     * @return
     */
    @Override
    public Object getInfo(String type) {
        switch (type) {

            case GET_LAST_WEIGHT:
                return getLastTotalWeight();

            default:
                return null;
        }
    }

    /**
     * TODO: Description of {@code sendInfo}.
     *
     * @param info
     * @return
     */
    @Override
    public int sendInfo(GenericElement info) {
        int result = RESULT_SEND_INVALID_MESSAGE;
        try {
            switch (info.getType()) {
                case GenericElement.TYPE_PLC_INTERN_COMMAND:
                    InternCommandElement internCommandElement = (InternCommandElement) info;
                    switch (internCommandElement.getInternCommand()) {
                        case InternCommandElement.COMMAND_VALIDATE_AND_CLEAR:
                            this.validateAndClear();
                            result = RESULT_SEND_SUCCESS_PROCESSED;
                            break;
                    }
                    break;
            }
        } catch (Exception e) {
        }
        return result;
    }

}
