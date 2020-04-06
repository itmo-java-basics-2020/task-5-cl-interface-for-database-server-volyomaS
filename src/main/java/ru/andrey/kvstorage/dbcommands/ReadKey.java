package ru.andrey.kvstorage.dbcommands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class ReadKey implements DatabaseCommand {
    private ExecutionEnvironment environment;
    private String databaseName;
    private String tableName;
    private String key;

    public ReadKey(ExecutionEnvironment environment, String databaseName, String tableName, String key) {
        this.environment = environment;
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.key = key;
    }

    public DatabaseCommandResult execute() {
        Optional<Database> databaseOptional = environment.getDatabase(databaseName);
        String value;

        if (databaseOptional.isEmpty()) {
            return DatabaseCommandResult.error(databaseName + " database doesn't exist");
        }

        try {
            value = databaseOptional.get().read(tableName, key);
        } catch (DatabaseException e) {
            return DatabaseCommandResult.error(e.getMessage());
        }

        return DatabaseCommandResult.success("Key = " + key + "\nValue = " + value);
    }
}
