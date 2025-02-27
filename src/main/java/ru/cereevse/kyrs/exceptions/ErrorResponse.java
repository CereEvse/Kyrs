package ru.cereevse.kyrs.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ErrorResponse {

    private final Long CodeID;

    private final String Message;
}
