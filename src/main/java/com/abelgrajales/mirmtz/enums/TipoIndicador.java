package com.abelgrajales.mirmtz.enums;

import com.abelgrajales.mirmtz.exception.BusinessException;

public enum TipoIndicador {
    ESTRATEGICO("Estratégico"),
    GESTION("Gestión");

    private final String tipo;

    TipoIndicador(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public static TipoIndicador fromTipo(String tipo) {
        for (TipoIndicador t : TipoIndicador.values()) {
            if (t.getTipo().equals(tipo)) {
                return t;
            }
        }
        throw new BusinessException("No es un tipo valido: " + tipo);
    }
}
