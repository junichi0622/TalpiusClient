package net.typicartist.talpius.module;

import net.typicartist.talpius.setting.Bind;
import net.typicartist.talpius.setting.Setting;

import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Module {

    private String name;

    private String extraInfo = null;

    private String description = "";
    private Setting<Bind> keybind = new Setting<>("Bind", new Bind(GLFW.GLFW_KEY_UNKNOWN)).withDescription("Sets the module toggle key");
    private Setting<Boolean> holdKeybind = new Setting<>("Hold", false).withVisibility(() -> false).withDescription("Only activate while bind is being held down");
    private Category category;
    private ArrayList<String> aliases = new ArrayList<>();

    private boolean enabled;

    private boolean visible = true;

    public static final Minecraft mc = Minecraft.getInstance();

    private int minProtocol = 0;
    private int maxProtocol = 1000;

    public Module(String name, String description, int keybind, Category category, String... aliases) {
        this.name = name;
        this.description = description;
        this.keybind.getValue().setKeyCode(keybind);
        this.category = category;
        Collections.addAll(this.aliases, aliases);
    }

    public Module(String name, int keybind, Category category, String... aliases) {
        this.name = name;
        this.keybind.getValue().setKeyCode(keybind);
        this.category  = category;
        Collections.addAll(this.aliases, aliases);
    }

    public Module(String name, String description, Category category, String... aliases) {
        this.name = name;
        this.description = description;
        this.category = category;
        Collections.addAll(this.aliases, aliases);
    }

    public Module(String name, String descriptin, String... aliases) {
        this.name = name;
        this.description = descriptin;
        Collections.addAll(this.aliases, aliases);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getDescriptiion() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getKeybind() {
        return keybind.getValue().getKeyCode();
    }

    public void setKeybind(int key) {
        this.keybind.setValue(new Bind(key));
    }

    public boolean isHold() {
        return holdKeybind.getValue();
    }

    public void setHold(boolean hold) {
        this.holdKeybind.setValue(hold);
    }

    public Category getCategory() { 
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ArrayList<String> getAliases() {
        return aliases;
    }

    public void setAliases(ArrayList<String> aliases) {
        this.aliases = aliases;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    } 

    public void toggle() {
        if (!enabled) {
            this.enabled = true;
            onEnable();
        } else {
            this.enabled = false;
            onDisable();
        }
    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public static void onEnableMessage(Module module) {
        if (mc.player == null || mc.level == null) return;
    }

    public static void onDisableMessage(Module module) {
        if (mc.player == null || mc.level == null) return;
    }

    public void doNotification(String text) {

    }

    public enum Category {
        COMBAT,  MOVEMENT, PLAYER, RENDER, MISC, EXPLOIT, CLIENT
    }
    
}
