package com.lignting.api.services

import com.lignting.api.model.ArticleDetail
import com.lignting.api.model.ArticleDetailDTO
import com.lignting.api.repositories.ArticleDetailRepository
import com.lignting.api.repositories.ArticleInformationRepository
import org.springframework.context.annotation.DependsOn
import org.springframework.stereotype.Service

interface IArticleDetailServices {
    fun getByArticleInformationId(id: Long): ArticleDetail?

    fun getList(): List<ArticleDetail>
    fun add(articleDetailDTO: ArticleDetailDTO): ArticleDetail
}

@Service
@DependsOn("articleInformationService")
class ArticleDetailServices(
    private val articleDetailRepository: ArticleDetailRepository,
    private val articleInformationRepository: ArticleInformationRepository
) : IArticleDetailServices {
    override fun getByArticleInformationId(id: Long) = articleDetailRepository.findByArticleInformationId(id)

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