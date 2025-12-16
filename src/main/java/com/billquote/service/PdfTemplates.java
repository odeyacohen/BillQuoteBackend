package com.billquote.service;

public final class PdfTemplates {
  private PdfTemplates(){}

  public static String devis(Long idDevis, String titre, String montant, String dateIso) {
    return """
      <html><head><meta charset="UTF-8" />

      <style>body{font-family:sans-serif} h1{margin:0 0 8px} .box{border:1px solid #ccc;padding:12px;border-radius:8px}</style>
      </head><body>
        <h1>Devis %s</h1>
        <div class="box">
          <p><b>Titre:</b> %s</p>
          <p><b>Montant TTC:</b> %s</p>
          <p><b>Date:</b> %s</p>
        </div>
      </body></html>
    """.formatted(idDevis == null ? "" : idDevis, titre, montant, dateIso);
  }

  public static String facture(String numero, String montant, String dateIso, String statut) {
    return """
      <html><head><meta charset="UTF-8" />

      <style>body{font-family:sans-serif} h1{margin:0 0 8px} .box{border:1px solid #ccc;padding:12px;border-radius:8px}</style>
      </head><body>
        <h1>Facture %s</h1>
        <div class="box">
          <p><b>Montant TTC:</b> %s</p>
          <p><b>Date:</b> %s</p>
          <p><b>Statut:</b> %s</p>
        </div>
      </body></html>
    """.formatted(numero == null ? "" : numero, montant, dateIso, statut == null ? "" : statut);
  }
}
