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
            if (module.getName().equalsIgnoreCase(name)) {
                return module;
            }

            for (String alias : module.getAliases()) {
                if (alias.toLowerCase().startsWith(name.toLowerCase())) {
                    return module;
                }
            }
        }

        return null;
    }

    public static Module getModuleByClass(Class<? extends Module> clazz) {
        for (Module module : modules) {
            if (module.getClass() == clazz) {
                return module;
            }  
        }

        return null;
    }

}
