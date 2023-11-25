package executor.service.controller

import executor.service.controller.model.ApiError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException



@RestControllerAdvice
class RestExceptionHandler {
    @ExceptionHandler
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<ApiError> {
        val apiError = ApiError("illegal data", listOf(ex.message!!))
        return ResponseEntity(apiError, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<ApiError> {
        val errors = ex.bindingResult.allErrors.mapNotNull { it.defaultMessage }.toList()
        val apiError = ApiError("validation error", errors)
        return ResponseEntity(apiError, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    protected fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException): ResponseEntity<ApiError> {
        val apiError = ApiError("Malformed JSON Request", listOfNotNull(ex.message))
        return ResponseEntity(apiError, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    protected fun handleMethodArgumentTypeMismatchException(ex: MethodArgumentTypeMismatchException): ResponseEntity<ApiError> {
        val apiError = ApiError(
            "bad argument type",
            listOf("The parameter ${ex.name} of value ${ex.value} could not be converted to type ${ex.requiredType?.simpleName}")
        )
        return ResponseEntity(apiError, HttpStatus.BAD_REQUEST)
    }

}