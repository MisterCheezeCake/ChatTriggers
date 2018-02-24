package com.chattriggers.ctjs.utils.config;

import com.chattriggers.ctjs.CTJS;
import lombok.Getter;

public abstract class ConfigOption {
    @Getter
    protected String name;
    @Getter
    protected transient Type type;

    protected transient int x;
    protected transient int y;
    protected transient boolean hidden;

    ConfigOption(Type type) {
        this.type = type;

        CTJS.getInstance().getConfig().addConfigOption(this);
    }

    public abstract void init();
    public abstract void draw(int mouseX, int mouseY);
    public abstract void mouseClicked(int mouseX, int mouseY, int mouseButton);
    public void mouseReleased() {}
    public void keyTyped(char typedChar, int keyCode) {}

    public enum Type {
        STRING, STRING_SELECTOR, COLOR, BOOLEAN
    }
}