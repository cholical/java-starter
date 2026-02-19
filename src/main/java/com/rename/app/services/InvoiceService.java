package com.rename.app.services;

import com.rename.app.models.Invoice;
import com.rename.app.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice createInvoice(Invoice invoice) {
        invoice.setId(null);
        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    public Invoice updateInvoice(Long id, Invoice invoice) {
        Invoice existing = getInvoiceById(id);
        if (existing == null) {
            return null;
        }

        existing.setCustomerName(invoice.getCustomerName());
        existing.setAmount(invoice.getAmount());
        existing.setStatus(invoice.getStatus());
        existing.setDueDate(invoice.getDueDate());

        return invoiceRepository.save(existing);
    }

    public boolean deleteInvoice(Long id) {
        Invoice existing = getInvoiceById(id);
        if (existing == null) {
            return false;
        }

        invoiceRepository.delete(existing);
        return true;
    }
}