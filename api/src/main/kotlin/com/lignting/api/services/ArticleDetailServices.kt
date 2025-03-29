package com.lignting.api.services

import com.lignting.api.models.ArticleDetail
import com.lignting.api.models.ArticleDetailDTO
import com.lignting.api.repositories.ArticleDetailRepository
import com.lignting.api.repositories.ArticleInformationRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.context.annotation.DependsOn
import org.springframework.stereotype.Service

interface IArticleDetailServices {
    fun getByArticleInformationId(id: Long): Any?

    fun getList(): List<ArticleDetail>
    fun add(articleDetailDTO: ArticleDetailDTO): Any?
}

@Service
@DependsOn("articleInformationService")
class ArticleDetailServices(
    private val articleDetailRepository: ArticleDetailRepository,
    private val articleInformationRepository: ArticleInformationRepository
) : IArticleDetailServices {
    @Cacheable("ArticleDetailGet")
    override fun getByArticleInformationId(id: Long) = articleDetailRepository.findByArticleInformationId(id)

    @Cacheable("ArticleDetailGetList")
    override fun getList(): List<ArticleDetail> = articleDetailRepository.findAll()

    override fun add(articleDetailDTO: ArticleDetailDTO): ArticleDetail {
        val articleInformation = articleInformationRepository.findById(articleDetailDTO.articleInformationId).get()
        val articleDetail = ArticleDetail(
            md = articleDetailDTO.md,
            html = articleDetailDTO.html,
            articleInformation = articleInformation
        )
        return articleDetailRepository.save(articleDetail)
    }
}