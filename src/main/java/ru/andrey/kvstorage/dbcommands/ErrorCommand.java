package ru.andrey.kvstorage.dbcommands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;

public class ErrorCommand implements DatabaseCommand {
    private String message;

    public ErrorCommand(String message) {
        this.message = message;
    }

    public DatabaseCommandResult execute() {
        return DatabaseCommandResult.error(message);
    }
}
