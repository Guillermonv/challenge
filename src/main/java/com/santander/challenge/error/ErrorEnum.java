package com.santander.challenge.error;

/**
 * @author guillermovarelli
 */
public enum ErrorEnum {
    USER_NOT_EXIST("No se encontro el usuario ingresado", 1000),
    MEETUP_NOT_EXIST("No se encontro el codigo de meetup ingresado", 1001),
    INVALID_DATE("La meet up se encuentra en un periodo invalido para obtener el clima, " +
                         "por favor ingrese un dia no menor a hoy y hasta 7 dias en el futuro", 1002),
    INVALID_USER_OR_PASSWORD("El usuario o la contraseña son incorrectos",1003);


    private int code;
    private String description;

    ErrorEnum(String description, int code) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

}
