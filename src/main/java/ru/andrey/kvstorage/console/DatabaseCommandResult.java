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
        private String resultMessage;

        private DatabaseCommandResultImpl(DatabaseCommandStatus status, String resultMessage) {
            this.status = status;
            this.resultMessage = resultMessage;
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
            if (isSuccess()) {
                return Optional.of(resultMessage);
            }
            return Optional.empty();
        }

        @Override
        public Optional<String> getErrorMessage() {
            if (!isSuccess()) {
                return Optional.of(resultMessage);
            }
            return Optional.empty();
        }
    }
}