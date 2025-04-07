package com.abelgrajales.mirmtz.enums;

import com.abelgrajales.mirmtz.exception.BusinessException;

public enum Dimension {
    EFICACIA("Eficacia"),
    EFICIENCIA("Eficiencia"),
    CALIDAD("Calidad"),
    ECONOMIA("Economía");

    private String dimension;

    Dimension(String dimension) {
        this.dimension = dimension;
    }

    public String getDimension() {
        return dimension;
    }

    public static Dimension fromDimension(String dimension) {
        for (Dimension d : Dimension.values()) {
            if (d.getDimension().equals(dimension)) {
                return d;
            }
        }
        throw new BusinessException("No es una dimension valida: " + dimension);
    }
}
