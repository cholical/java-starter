package com.rename.app.services;

import com.rename.app.TestNGWithSpringApplication;
import com.rename.app.models.Invoice;
import com.rename.app.models.InvoiceStatus;
import com.rename.app.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

@SpringBootTest(classes = TestNGWithSpringApplication.class)
public class InvoiceServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @BeforeMethod
    public void setup() {
        invoiceRepository.deleteAll();
    }

    @Test
    public void testInvoiceCrudFlow() {
        Invoice created = invoiceService.createInvoice(Invoice.builder()
                .customerName("Acme Corp")
                .amount(new BigDecimal("99.50"))
                .status(InvoiceStatus.DRAFT)
                .dueDate(LocalDate.of(2026, 3, 1))
                .build());

        assertNotNull(created.getId());
        assertEquals(invoiceService.getAllInvoices().size(), 1);

        Invoice fetched = invoiceService.getInvoiceById(created.getId());
        assertNotNull(fetched);
        assertEquals(fetched.getCustomerName(), "Acme Corp");

        Invoice updated = invoiceService.updateInvoice(created.getId(), Invoice.builder()
                .customerName("Acme Corp Updated")
                .amount(new BigDecimal("120.00"))
                .status(InvoiceStatus.SENT)
                .dueDate(LocalDate.of(2026, 3, 15))
                .build());

        assertNotNull(updated);
        assertEquals(updated.getStatus(), InvoiceStatus.SENT);
        assertEquals(updated.getCustomerName(), "Acme Corp Updated");

        boolean deleted = invoiceService.deleteInvoice(created.getId());
        assertTrue(deleted);
        assertEquals(invoiceService.getAllInvoices().size(), 0);
        assertNull(invoiceService.getInvoiceById(created.getId()));
    }
}