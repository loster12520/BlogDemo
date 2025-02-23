package com.lignting.api.repositories

import com.lignting.api.model.ArticleDetail
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleDetailRepository : JpaRepository<ArticleDetail, Long>{
    fun findByArticleInformationId(entityAId: Long): ArticleDetail?
}