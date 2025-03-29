package com.lignting.api.repositories

import com.lignting.api.models.ArticleDetail
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleDetailRepository : JpaRepository<ArticleDetail, Long>{
    fun findByArticleInformationId(entityAId: Long): ArticleDetail?
}