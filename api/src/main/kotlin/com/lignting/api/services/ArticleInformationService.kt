package com.lignting.api.services

import com.lignting.api.models.ArticleInformation
import com.lignting.api.repositories.ArticleInformationRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

interface IArticleInformationService {
    fun getList(): Any?
    fun get(id: Long): Any?
    fun add(articleInformation: ArticleInformation): Any?
}

@Service
class ArticleInformationService(private val articleInformationRepository: ArticleInformationRepository) :
    IArticleInformationService {
    @Cacheable("ArticleInformationGetList")
    override fun getList() = articleInformationRepository.findAll()

    @Cacheable("ArticleInformationGet")
    override fun get(id: Long) = articleInformationRepository.findById(id).get()
    override fun add(articleInformation: ArticleInformation) = articleInformationRepository.save(articleInformation)
}