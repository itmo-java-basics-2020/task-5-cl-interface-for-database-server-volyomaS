package ru.andrey.kvstorage.dbcommands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class UpdateKey implements DatabaseCommand {
    private ExecutionEnvironment environment;
    private String databaseName;
    private String tableName;
    private String key;
    private String value;

    public UpdateKey(ExecutionEnvironment environment, String databaseName, String tableName, String key, String value) {
        this.environment = environment;
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.key = key;
        this.value = value;
    }

    public DatabaseCommandResult execute() {
        Optional<Database> databaseOptional = environment.getDatabase(databaseName);

        if (databaseOptional.isEmpty()) {
            return DatabaseCommandResult.error(databaseName + " database doesn't exist");
        }

        try {
            databaseOptional.get().write(tableName, key, value);
        } catch (DatabaseException e) {
            return DatabaseCommandResult.error(e.getMessage());
        }

        return DatabaseCommandResult.success("Key = " + key + "\nValue = " + value + "\nDone");
    }
}
