package com.springback.cliente.config;

public enum ErrorCode {

    RUT_FOUND(100,"rut se encuentra registrado"),
    EMAIL_FOUND(200,"Email ya registrado"),
    EMAIL_UPDATE_FOUND(201,"email ya registrado, si es del cliente no indicar"),
    CLIENTE_NOT_FOUND(300,"No se encontro el rut de la persona registrada en la base de datos"),

    //Errores genericos
    ERROR_NO_CONTROLADO(10,"Error no controlado"),
    ERROR_INPUT(11,"Error en parametros de entrada"),
    ERROR_CONEXION(12,"Error conexion base de datos"),
    ENTIDAD_NO_ENCONTRADA(13,"No se encontro el modelo solicitada");

    private final int value;
    private final String reasonPhrase;


    ErrorCode(int value,String reasonPhrase){
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int value(){return this.value;}

    public String getReasonPhrase(){return this.reasonPhrase;}

}
