package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    Optional<String> getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    static DatabaseCommandResult success(String result) {
        return new DatabaseCommandResultImpl(DatabaseCommandStatus.SUCCESS, result);
    }

    static DatabaseCommandResult error(String message) {
        return new DatabaseCommandResultImpl(DatabaseCommandStatus.FAILED, message);
    }

    class DatabaseCommandResultImpl implements DatabaseCommandResult {
        private DatabaseCommandStatus status;
        private String result;
        private String message;

        private DatabaseCommandResultImpl(DatabaseCommandStatus status, String resultMessage) {
            this.status = status;
            if (status == DatabaseCommandStatus.SUCCESS) {
                this.result = resultMessage;
            } else {
                this.message = resultMessage;
            }
        }

        @Override
        public boolean isSuccess() {
            return status.equals(DatabaseCommandStatus.SUCCESS);
        }

        @Override
        public DatabaseCommandStatus getStatus() {
            return status;
        }

        @Override
        public Optional<String> getResult() {
            return Optional.ofNullable(result);
        }

        @Override
        public Optional<String> getErrorMessage() {
            return Optional.ofNullable(message);
        }
    }
}