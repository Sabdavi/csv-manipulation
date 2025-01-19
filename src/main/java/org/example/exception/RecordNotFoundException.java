package org.example.exception;

    public class RecordNotFoundException extends RuntimeException {
        public RecordNotFoundException(String code) {
            super("Record with code '" + code + "' not found");
        }
    }

