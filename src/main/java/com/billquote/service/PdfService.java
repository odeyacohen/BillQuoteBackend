package com.billquote.service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

  public byte[] renderHtmlToPdf(String html) {
    try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
      PdfRendererBuilder b = new PdfRendererBuilder();
      b.useFastMode();
      b.withHtmlContent(html, null);
      b.toStream(out);
      b.run();
      return out.toByteArray();
    } catch (Exception e) {
      throw new RuntimeException("Erreur génération PDF", e);
    }
  }
}
