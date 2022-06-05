package com.backbase.stream.compositions.transaction.cursor.http;

import com.backbase.stream.compositions.transaction.cursor.api.TransactionCursorApi;
import com.backbase.stream.compositions.transaction.cursor.core.service.TransactionCursorService;
import com.backbase.stream.compositions.transaction.cursor.model.TransactionCursorDeleteRequest;
import com.backbase.stream.compositions.transaction.cursor.model.TransactionCursorPatchRequest;
import com.backbase.stream.compositions.transaction.cursor.model.TransactionCursorResponse;
import com.backbase.stream.compositions.transaction.cursor.model.TransactionCursorUpsertRequest;
import com.backbase.stream.compositions.transaction.cursor.model.TransactionCursorUpsertResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@Slf4j
public class TransactionCursorController implements TransactionCursorApi {

  private final TransactionCursorService transactionCursorService;

  @Override
  public Mono<ResponseEntity<Void>> deleteCursor(
      Mono<TransactionCursorDeleteRequest> transactionCursorDeleteRequest,
      ServerWebExchange exchange) {
    if (log.isDebugEnabled()) {
      log.debug("TransactionCursorController :: deleteCursor");
    }
    return transactionCursorService.deleteCursor(transactionCursorDeleteRequest);
  }

  @Override
  public Mono<ResponseEntity<TransactionCursorResponse>> getByArrangementId(String arrangementId,
      ServerWebExchange exchange) {
    if (log.isDebugEnabled()) {
      log.debug("TransactionCursorController :: getByArrangementId {} ", arrangementId);
    }
    return transactionCursorService.findByArrangementId(arrangementId);
  }

  @Override
  public Mono<ResponseEntity<TransactionCursorResponse>> getById(String id,
      ServerWebExchange exchange) {
    if (log.isDebugEnabled()) {
      log.debug("TransactionCursorController :: getById {} ", id);
    }
    return transactionCursorService.findById(id);
  }

  @Override
  public Mono<ResponseEntity<Void>> patchByArrangementId(String arrangementId,
      Mono<TransactionCursorPatchRequest> transactionCursorPatchRequest,
      ServerWebExchange exchange) {
    if (log.isDebugEnabled()) {
      log.debug("TransactionCursorController :: patchByArrangementId {} ", arrangementId);
    }
    return transactionCursorService
        .patchByArrangementId(arrangementId, transactionCursorPatchRequest);
  }

  @Override
  public Mono<ResponseEntity<TransactionCursorUpsertResponse>> upsertCursor(
      Mono<TransactionCursorUpsertRequest> transactionCursorUpsertRequest,
      ServerWebExchange exchange) {
    if (log.isDebugEnabled()) {
      log.debug("TransactionCursorController :: upsertCursor");
    }
    return transactionCursorService.upsertCursor(transactionCursorUpsertRequest);
  }
}