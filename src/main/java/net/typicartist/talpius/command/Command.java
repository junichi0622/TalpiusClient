package net.typicartist.talpius.command;

import java.util.ArrayList;
import java.util.Collections;

import net.minecraft.client.Minecraft;

public abstract class Command {
    
    private String name;
    private String description;
    private String[] aliases;
    private ArrayList<SyntaxChunk> chunks = new ArrayList<>();

    public static String prefix = "?";

    public static final Minecraft mc = Minecraft.getInstance();

    public Command(String name, String description, SyntaxChunk... chunks) {
        this.name = name;
        this.description = description;
        this.aliases = new String[0];
        Collections.addAll(this.chunks, chunks);
    }

    public Command(String name, String description, String[] aliases, SyntaxChunk ...chunks) {
        this.name = name;
        this.description = description;
        this.aliases = aliases;
        Collections.addAll(this.chunks, chunks);
    }

    public void onFire(String[] args) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<SyntaxChunk> getChunks() {
        return chunks;
    }

    public String getChunksAsString() {
        StringBuilder str = new StringBuilder();

        chunks.forEach(syntaxChunk -> {
            str.append(syntaxChunk.getName() + " ");
        });

        return str.toString();
    }

    public static String getPrefix() {
        return prefix;
    }

    public static void setPrefix(String prefix) {
        Command.prefix = prefix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getAliases() {
        return aliases;
    }

    public void setAliases(String[] aliases) {
        this.aliases = aliases;
    } 

}
