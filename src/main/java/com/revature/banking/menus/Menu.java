package com.revature.banking.menus;

import java.io.BufferedReader;

public abstract class Menu {

    protected String name; // TODO: What on earth is protected!?
    protected String route;
    protected BufferedReader terminalReader;

    public Menu(String name, String route, BufferedReader terminalReader) {
        super();
        this.name = name;
        this.route = route;
        this.terminalReader = terminalReader;
    }

    public String getName() {
        return name;
    }

    public String getRoute() {
        return route;
    }

    // abstract within a method signature needs to be implemented in the sub-class
    // WE NEED TO IMPLEMENT SOME RENDER METHOD on ANY CHILD CLASS
    // 1 Pillar of OOP or Object Orient Programming known as none other than Abstraction
    public abstract void render() throws Exception;

}
