package fr.unice.polytech.dsl.sensilang.language;

import groovy.lang.Binding;
import groovy.lang.Script;

public class SensilangBinding extends Binding {
    private Script script;
    private SensilangModel model;

    public void setScript(Script script) {
        this.script = script;
    }

    void setModel(SensilangModel model) {
        this.model = model;
    }

    SensilangModel getModel() {
        return model;
    }
}
