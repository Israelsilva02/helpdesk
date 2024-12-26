package com.helpdesk.api.util;

import lombok.NonNull;

import java.time.OffsetDateTime;

public record ResponseError(
        @NonNull String error, @NonNull OffsetDateTime timestamp, int statusCode
) {
}
