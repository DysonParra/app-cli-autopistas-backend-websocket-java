/*
 * @overview        {StaticWeightMessage}
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
package com.project.dev.websocket.model.front;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: Description of {@code StaticWeightMessage}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class StaticWeightMessage implements FrontMessage {

    private final String messageType = "BASCULA";
    private final String tipoBascula = "estatica";
    private int nroBascula;
    private int peso;

}
