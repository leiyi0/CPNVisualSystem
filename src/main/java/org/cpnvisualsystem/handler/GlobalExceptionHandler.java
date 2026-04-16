package org.cpnvisualsystem.handler;

import org.cpnvisualsystem.entity.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<R<?>> handleValidation(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String msg = fieldErrors.stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining("; "));
        logger.warn("请求参数错误: {}", msg);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(R.error(400, msg));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<R<?>> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        String msg = "请求方法不支持: " + ex.getMethod();
        logger.warn(msg);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(R.error(405, msg));
    }


    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<R<?>> handleNotFound(NoResourceFoundException ex) {
        String msg = "资源未找到: " + ex.getLocalizedMessage();
        logger.info(msg);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(R.error(404, msg));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<R<?>> handleException(Exception ex) {
        logger.error("Unhandled exception caught: ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(R.error(500, "服务器内部错误"));
    }
}