package com.jdc.weekend.api.output;

public record DataModificationResult<T>(T id, String message) {

}
