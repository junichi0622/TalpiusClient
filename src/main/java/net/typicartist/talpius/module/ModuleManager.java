package net.typicartist.talpius.module;

import java.util.ArrayList;

public class ModuleManager {
    
    private static ArrayList<Module> modules = new ArrayList<>();

    public static void init() {

    }

    public static ArrayList<Module> getModules() {
        return modules;
    }

    public static Module getModuleByName(String name) {
        for (Module module : modules) {
            return module;
        }

        return null;
    }

}
