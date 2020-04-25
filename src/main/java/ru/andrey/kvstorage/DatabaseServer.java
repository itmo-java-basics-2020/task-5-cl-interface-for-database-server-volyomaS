package ru.andrey.kvstorage;

import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.DatabaseCommands;
import ru.andrey.kvstorage.console.ExecutionEnvironment;

import java.util.Arrays;

public class DatabaseServer {

    private final ExecutionEnvironment environment;

    public DatabaseServer(ExecutionEnvironment environment) {
        this.environment = environment;
    }

    public static void main(String[] args) {

    }

    public DatabaseCommandResult executeNextCommand(String commandText) {
        if (commandText == null) {
            return DatabaseCommandResult.error("You passed null as command");
        }
        if (commandText.isEmpty()) {
            return DatabaseCommandResult.error("You passed empty command");
        }

        String separator = " ";
        String[] terms = commandText.split(separator);

        try {
            return DatabaseCommands.valueOf(terms[0])
                    .getCommand(environment, Arrays.copyOfRange(terms, 1, terms.length))
                    .execute();
        } catch (IllegalArgumentException e) {
            return DatabaseCommandResult.error("Illegal command name: " + terms[0]);
        }
    }
}
