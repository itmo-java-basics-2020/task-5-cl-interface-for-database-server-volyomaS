package ru.andrey.kvstorage.dbcommands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;

public class CreateDatabase implements DatabaseCommand {
    private ExecutionEnvironment environment;
    private String databaseName;

    public CreateDatabase(ExecutionEnvironment environment, String databaseName) {
        this.environment = environment;
        this.databaseName = databaseName;
    }

    public DatabaseCommandResult execute() {
        environment.addDatabase(null);

        return DatabaseCommandResult.success("Database " + databaseName + " successfully created");
    }
}
