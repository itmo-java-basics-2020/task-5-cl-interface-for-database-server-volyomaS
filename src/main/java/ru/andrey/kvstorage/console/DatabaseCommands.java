package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.dbcommands.*;

public enum DatabaseCommands {
    CREATE_DATABASE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, String... args) {
            return args.length != 1 ? new ErrorCommand("Illegal arguments count, 1 needed")
                                    : new CreateDatabase(environment, args[DATABASE_INDEX]);
        }
    }, CREATE_TABLE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, String... args) {
            return args.length != 2 ? new ErrorCommand("Illegal arguments count, 2 needed")
                                    : new CreateTable(environment, args[DATABASE_INDEX], args[TABLE_INDEX]);
        }
    }, UPDATE_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, String... args) {
            return args.length != 4 ? new ErrorCommand("Illegal arguments count, 4 needed")
                                    : new UpdateKey(environment, args[DATABASE_INDEX], args[TABLE_INDEX], args[KEY_INDEX], args[VALUE_INDEX]);
        }
    }, READ_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, String... args) {
            return args.length != 3 ? new ErrorCommand("Illegal arguments count, 3 needed")
                                    : new ReadKey(environment, args[DATABASE_INDEX], args[TABLE_INDEX], args[KEY_INDEX]);
        }
    };

    public static final int DATABASE_INDEX = 0;
    public static final int TABLE_INDEX = 1;
    public static final int KEY_INDEX = 2;
    public static final int VALUE_INDEX = 3;

    public abstract DatabaseCommand getCommand(ExecutionEnvironment environment, String... args);
    }
