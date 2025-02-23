package com.lignting.api.repositories

import com.lignting.api.model.ArticleInformation
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleInformationRepository : JpaRepository<ArticleInformation, Long>