package com.lignting.api.services

import com.lignting.api.model.ArticleInformation
import com.lignting.api.repositories.ArticleInformationRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

interface IArticleInformationService {
    fun getList(): List<ArticleInformation>
    fun get(id: Long): ArticleInformation
    fun add(articleInformation: ArticleInformation): ArticleInformation
}

@Service
class ArticleInformationService(private val articleInformationRepository: ArticleInformationRepository) :
    IArticleInformationService {
    @Cacheable("ArticleInformationGetList")
    override fun getList(): List<ArticleInformation> = articleInformationRepository.findAll()
    @Cacheable("ArticleInformationGet")
    override fun get(id: Long) = articleInformationRepository.findById(id).get()
    override fun add(articleInformation: ArticleInformation) = articleInformationRepository.save(articleInformation)
}