package com.abelgrajales.mirmtz.enums;

import com.abelgrajales.mirmtz.exception.BusinessException;

public enum Algoritmo {
    A_DIVIDIDO_B_POR_100("(A/B) * 100"),
    A_DIVIDIDO_B_MENOS_1_POR_100("((A/B) - 1) * 100"),
    A_DIVIDIDO_B("A/B"),
    A("A");

    private final String formula;

    Algoritmo(String formula) {
        this.formula = formula;
    }

    public String getFormula() {
        return formula;
    }

    public static Algoritmo fromFormula(String formula) {
        for (Algoritmo algoritmo : Algoritmo.values()) {
            if (algoritmo.getFormula().equals(formula)) {
                return algoritmo;
            }
        }
        throw new BusinessException("No es una formula valida: " + formula);
    }
}
