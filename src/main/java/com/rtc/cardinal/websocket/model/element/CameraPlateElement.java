/*
 * @fileoverview {FileName} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {FileName} fue realizada el 31/07/2022.
 * @Dev - La primera version de {FileName} fue escrita por Dyson A. Parra T.
 */
package com.rtc.cardinal.websocket.model.element;

import lombok.Builder;
import lombok.Data;

/**
 * TODO: Definición de {@code CameraPlateElement}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
//@AllArgsConstructor
//@Builder
@Data
//@NoArgsConstructor
public class CameraPlateElement extends GenericElement {

    // Non static block.
    {
        this.type = TYPE_CAMERA_PLATE;
    }
    private String txtPlate;
    private String imgPlate;

    /**
     * TODO: Definición de {@code CameraPlateElement}.
     *
     * @param sender
     * @param imgPlate
     * @param txtPlate
     */
    @Builder
    public CameraPlateElement(String sender, String txtPlate, String imgPlate) {
        super(sender);
        this.txtPlate = txtPlate;
        this.imgPlate = imgPlate;
    }

    /**
     * TODO: Definición de {@code getPrintTextTxtPlate}.
     *
     * @return
     */
    protected String getPrintTextTxtPlate() {
        return (txtPlate == null) ? "" : txtPlate;
    }

    /**
     * TODO: Definición de {@code getPrintTextImgPlate}.
     *
     * @return
     */
    protected String getPrintTextImgPlate() {
        return (imgPlate == null) ? "" : (imgPlate.length() > 12) ? "%image%" : imgPlate;
    }

    /**
     * Obtiene el valor en {String} del objeto actual.
     *
     * @return un {String} con la representación del objeto.
     */
    @Override
    public String toString() {
        String text = "";
        text += "{" + "'" + getPrintTextTxtPlate() + "'";
        text += ", " + "'" + getPrintTextImgPlate() + "'";
        if (recoveryDate != null)
            text += ", " + "'" + getPrintTextRecoveryDate() + "'";
        text += "}";
        return text;
    }

}
