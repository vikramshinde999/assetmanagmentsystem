package com.company.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InvalidEmployeeId.class)
	public ResponseEntity<ExceptionResponce> handleInvalidEmployeeId(InvalidEmployeeId e) {
		ExceptionResponce error = new ExceptionResponce("Invalid Employee Id", e.getMessage(), LocalDateTime.now());
		return new ResponseEntity<ExceptionResponce>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidAssetId.class)
	public ResponseEntity<ExceptionResponce> handleInvalidAssetId(InvalidAssetId e) {
		ExceptionResponce error = new ExceptionResponce("Invalid asset id", e.getMessage(), LocalDateTime.now());
		return new ResponseEntity<ExceptionResponce>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoAuthority.class)
	public ResponseEntity<ExceptionResponce> handleNoAuthority(NoAuthority e) {
		ExceptionResponce error = new ExceptionResponce("No Authority", e.getMessage(), LocalDateTime.now());
		return new ResponseEntity<ExceptionResponce>(error, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(InvalidOrderId.class)
	public ResponseEntity<ExceptionResponce> handleInvalidOrderId(InvalidOrderId e) {
		ExceptionResponce error = new ExceptionResponce("Invalid order id", e.getMessage(), LocalDateTime.now());
		return new ResponseEntity<ExceptionResponce>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DuplicateAsset.class)
	public ResponseEntity<ExceptionResponce> handleDuplicateAsset(DuplicateAsset e) {
		ExceptionResponce error = new ExceptionResponce("Asset already exist", e.getMessage(), LocalDateTime.now());
		return new ResponseEntity<ExceptionResponce>(error, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(InvalidEmployeeDetails.class)
	public ResponseEntity<ExceptionResponce> handleInvalidEmployeeDetails(InvalidEmployeeDetails e) {
		ExceptionResponce error = new ExceptionResponce("Employee details are invalid", e.getMessage(),
				LocalDateTime.now());
		return new ResponseEntity<ExceptionResponce>(error, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(NoAssetsFound.class)
	public ResponseEntity<ExceptionResponce> handleNoAssetsFound(NoAssetsFound e) {
		ExceptionResponce error = new ExceptionResponce("Not found", e.getMessage(), LocalDateTime.now());
		return new ResponseEntity<ExceptionResponce>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoOrderFound.class)
	public ResponseEntity<ExceptionResponce> handleNoOrderFound(NoOrderFound e) {
		ExceptionResponce error = new ExceptionResponce("Not found", e.getMessage(), LocalDateTime.now());
		return new ResponseEntity<ExceptionResponce>(error, HttpStatus.NOT_FOUND);
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request){
				
		Map<String, String> map = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach(error->{
			String fieldName = ((FieldError)error).getField();
			String msg = error.getDefaultMessage();
			
			map.put(fieldName, msg);
		});
		
		return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
	}
}
