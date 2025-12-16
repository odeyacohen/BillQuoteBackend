package com.billquote.service;

import com.billquote.model.PdfFile;
import com.billquote.repository.PdfFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PdfStorageService {

  private final PdfService pdfService;
  private final PdfFileRepository pdfFileRepository;

  public PdfStorageService(PdfService pdfService, PdfFileRepository pdfFileRepository) {
    this.pdfService = pdfService;
    this.pdfFileRepository = pdfFileRepository;
  }

  @Transactional
  public PdfFile createDevisPdf(Long idDevis, String titre, String montant, String dateIso) {
    String html = PdfTemplates.devis(idDevis, titre, montant, dateIso);
    byte[] pdfBytes = pdfService.renderHtmlToPdf(html);

    String fileName = "devis-" + (idDevis != null ? idDevis : "nouveau") + ".pdf";

    PdfFile pdfFile = new PdfFile(
      fileName,
      "DEVIS",
      pdfBytes
    );
    return pdfFileRepository.save(pdfFile);
  }

  @Transactional
  public PdfFile createFacturePdf(String numero, String montant, String dateIso, String statut) {
    String html = PdfTemplates.facture(numero, montant, dateIso, statut);
    byte[] pdfBytes = pdfService.renderHtmlToPdf(html);

    String fileName = "facture-" + (numero != null ? numero : "nouvelle") + ".pdf";

    PdfFile pdfFile = new PdfFile(
      fileName,
      "FACTURE",
      pdfBytes
    );
    return pdfFileRepository.save(pdfFile);
  }

  @Transactional(readOnly = true)
  public PdfFile getPdf(Long id) {
    return pdfFileRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("PDF non trouvé (id=" + id + ")"));
  }
}
