package com.lignting.api.repositories

import com.lignting.api.models.ArticleInformation
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleInformationRepository : JpaRepository<ArticleInformation, Long>