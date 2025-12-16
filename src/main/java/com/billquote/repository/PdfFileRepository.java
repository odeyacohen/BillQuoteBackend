package com.billquote.repository;

import com.billquote.model.PdfFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PdfFileRepository extends JpaRepository<PdfFile, Long> {
}
