package com.desafio.criptografia.service;

import com.desafio.criptografia.domain.Transaction;
import com.desafio.criptografia.dto.TransactionDTO;
import com.desafio.criptografia.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public TransactionDTO create(TransactionDTO dto) {
        Transaction entity = new Transaction(dto.getUserDocument(), dto.getCreditCardToken(), dto.getValue());
        entity = repository.save(entity);
        return mapToDTO(entity);
    }

    public TransactionDTO findById(Long id) {
        return repository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    public List<TransactionDTO> findAll() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public TransactionDTO update(Long id, TransactionDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setUserDocument(dto.getUserDocument());
            existing.setCreditCardToken(dto.getCreditCardToken());
            existing.setValue(dto.getValue());
            return mapToDTO(repository.save(existing));
        }).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private TransactionDTO mapToDTO(Transaction entity) {
        return new TransactionDTO(
                entity.getId(),
                entity.getUserDocument(),
                entity.getCreditCardToken(),
                entity.getValue()
        );
    }
}
