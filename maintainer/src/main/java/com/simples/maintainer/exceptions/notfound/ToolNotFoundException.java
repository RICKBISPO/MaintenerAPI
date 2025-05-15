package com.simples.maintainer.exceptions.notfound;

public class ToolNotFoundException extends NotFoundException {
    public ToolNotFoundException() {
        super("tool not found");
    }
}
