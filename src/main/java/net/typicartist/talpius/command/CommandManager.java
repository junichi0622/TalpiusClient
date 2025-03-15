package net.typicartist.talpius.command;

import java.util.ArrayList;

public class CommandManager {
    
    private static ArrayList<Command> commands = new ArrayList<>();

    public static void init() {

    }

    public static ArrayList<Command> getCommands() {
        return commands;
    }

    public static Command getCommandByName(String name) {
        for (Command command : commands) {
            if (name.startsWith(Command.prefix)) {
                name = name.substring(1);
            }

            if (isNameOrAlias(command, name)) {
                return command;
            }
        }

        return null;
    }

    public static boolean isNameOrAlias(Command command, String str) {
        if (command.getName().equalsIgnoreCase(str)) {
            return true;
        }

        for (String alias : command.getAliases()) {
            if (alias.equalsIgnoreCase(str)) {
                return true;
            }         
        }

        return false;
    }

    public static String complete(Command command, String str) {
        if (command.getName().toLowerCase().startsWith(str)) {
            return command.getName();
        }

        for (String alias : command.getAliases()) {
            if (alias.toLowerCase().startsWith(str)) {
                return alias;
            }
        }

        return null;
    }

}
