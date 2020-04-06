package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.dbcommands.*;

public enum DatabaseCommands {
    CREATE_DATABASE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, String... args) {
            return args.length != 1 ? new ErrorCommand("Illegal arguments count, 1 needed")
                                    : new CreateDatabase(environment, args[0]);
        }
    }, CREATE_TABLE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, String... args) {
            return args.length != 2 ? new ErrorCommand("Illegal arguments count, 2 needed")
                                    : new CreateTable(environment, args[0], args[1]);
        }
    }, UPDATE_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, String... args) {
            return args.length != 4 ? new ErrorCommand("Illegal arguments count, 4 needed")
                                    : new UpdateKey(environment, args[0], args[1], args[2], args[3]);
        }
    }, READ_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment environment, String... args) {
            return args.length != 3 ? new ErrorCommand("Illegal arguments count, 3 needed")
                                    : new ReadKey(environment, args[0], args[1], args[2]);
        }
    };

    public abstract DatabaseCommand getCommand(ExecutionEnvironment environment, String... args);
    }
